package gov.gsa.dcoi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import gov.gsa.dcoi.DcoiExceptionHandler;
import gov.gsa.dcoi.DcoiRestMessage;
import gov.gsa.dcoi.dto.CostCalculationDto;
import gov.gsa.dcoi.dto.DataCenterDto;
import gov.gsa.dcoi.dto.FiscalQuarterReportDto;
import gov.gsa.dcoi.dto.QuarterDto;
import gov.gsa.dcoi.dto.RegionDto;
import gov.gsa.dcoi.dto.ServerInformationDto;
import gov.gsa.dcoi.entity.CostCalculation;
import gov.gsa.dcoi.entity.DataCenterQuarter;
import gov.gsa.dcoi.entity.DataCenterView;
import gov.gsa.dcoi.entity.QuarterReport;
import gov.gsa.dcoi.refValueEntity.GenericReferenceValueObject;
import gov.gsa.dcoi.repository.CostCalculationRepository;
import gov.gsa.dcoi.repository.DataCenterQuarterRepository;
import gov.gsa.dcoi.repository.DataCenterViewRepository;
import gov.gsa.dcoi.repository.QuarterStoredProcedure;
import gov.gsa.dcoi.repository.QuarterReportRepository;

/**
 * Service class functionality for functionality related to the quarter overall
 * 
 * @author sgonthier
 *
 */
@Component
public class QuarterService {

	private static final Logger LOGGER = LoggerFactory.getLogger(QuarterService.class);

	@Autowired
	QuarterStoredProcedure quarterRepository;

	@Autowired
	QuarterReportRepository quarterReportRepository;

	@Autowired
	DataCenterQuarterRepository dataCenterQuarterRepository;

	@Autowired
	DataCenterService dataCenterService;

	@Autowired
	DataCenterViewRepository exportRepository;

	@Autowired
	MessageSource messageSource;

	@Autowired
	CostCalculationRepository costCalcRepository;

	/**
	 * This call creates the new quarter report and, will then populate all the
	 * datacenters with the "new" info - will eventually pass back a fully
	 * created
	 * 
	 * @return
	 */
	@Transactional
	public Map<String, Object> initQuarter() {

		quarterRepository.initQuarter();
		Map<String, Object> returnMap = new HashMap<String, Object>();

		QuarterReport quarterReport = quarterReportRepository.findByQuarterInProgressFlag(1);
		returnMap.put("quarterReport", quarterReport);
		return returnMap;

	}

	/**
	 * Complete quarter report - sets the quarterActiveFlag to 0 and the quarter
	 * submitted flag to 1
	 * 
	 * @return
	 */
	@Transactional
	public Map<String, Object> completeQuarter() {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		QuarterReport quarterReport = quarterReportRepository.findByQuarterActiveFlag(1);
		if (quarterReport == null) {
			returnMap.put("errorMessage",
					new DcoiRestMessage(messageSource.getMessage("completeQuarterError", null, null)));
			return returnMap;
		}
		quarterReport.setQuarterActiveFlag(0);
		quarterReport.setQuarterSubmittedFlag(1);
		quarterReportRepository.save(quarterReport);
		return returnMap;

	}

	/**
	 * Method to view any past (non-active/non-in progress) OR
	 * active/in-progress quarter
	 * 
	 * @param quarterId
	 * @return
	 */
	@Transactional(readOnly = true)
	public QuarterDto viewQuarter(Long quarterId) {
		QuarterDto viewQuarter = new QuarterDto();
		QuarterReport quarterReport = quarterReportRepository.findOne(quarterId);
		viewQuarter.setFiscalQuarterReport(populateFiscalQuarterReportDto(quarterReport));
		List<RegionDto> regions = populateRegionDtosList(quarterId);
		viewQuarter.setRegions(regions);
		return viewQuarter;

	}

	/**
	 * Populate the fiscal quarter report dto for use on the front end from the
	 * quarter report information we received
	 * 
	 * @param quarterReport
	 * @return
	 */
	private FiscalQuarterReportDto populateFiscalQuarterReportDto(QuarterReport quarterReport) {
		FiscalQuarterReportDto quarterReportDto = new FiscalQuarterReportDto();
		CommonHelper.modelMapper.map(quarterReport, quarterReportDto);
		return quarterReportDto;
	}

	/**
	 * Update the active flag to one so that we know this is the active quarter
	 * (the data centers will be saved through the field office service) TO-DO
	 * set the in progress flag to 0??
	 * 
	 * @param dueDate
	 * @return
	 */
	@Transactional
	public void createQuarter(String dueDate) {
		try {
			QuarterReport quarterReport = quarterReportRepository.findByQuarterInProgressFlag(1);

			quarterReport.setQuarterDueDate(dueDate);
			quarterReport.setQuarterActiveFlag(1);
			quarterReport.setQuarterInProgressFlag(0);
			quarterReportRepository.save(quarterReport);
		} catch (DataAccessException dae) {
			LOGGER.error(dae.getMessage());
			throw DcoiExceptionHandler.throwDcoiException(
					"Exception creating quarter with updated active flag" + "and due date: " + dae.getMessage());
		}
	}

	/**
	 * Find the data centers by the active flag OR the in progress flag
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public Boolean findQuarterByActiveOrInProgressFlag() {
		QuarterReport quarterReport = quarterReportRepository.findByQuarterActiveFlagOrQuarterInProgressFlag(1, 1);
		if (quarterReport == null) {
			return false;
		}
		return true;

	}

	/**
	 * Find quarter reports by the in progress flag
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public QuarterReport findByQuarterInProgressFlag() {

		return quarterReportRepository.findByQuarterInProgressFlag(1);

	}

	/**
	 * Find the data centers by the active flag
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public Boolean findQuarterByActiveFlag() {
		QuarterReport quarterReport = quarterReportRepository.findByQuarterActiveFlag(1);
		if (quarterReport == null) {
			return false;
		}
		return true;

	}

	/**
	 * Get the data center view results that are necessary for the final report
	 * 
	 * @param quarterId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<DataCenterView> findViewResultsByQuarterId(Long quarterId) {
		return exportRepository.findViewResultsByQuarterId(quarterId);
	}

	/**
	 * Cost calculation function to save information to back end
	 * 
	 * @param dataCenterDtos
	 * @return
	 */
	@Transactional
	public List<Map<String, Object>> costCalculation(List<DataCenterDto> dataCenterDtos) {
		List<Map<String, Object>> dataCenterIdTotalsPairs = new ArrayList<Map<String, Object>>();
		for (DataCenterDto dataCenter : dataCenterDtos) {
			CostCalculation costCalcEntity;
			List<CostCalculation> costCalcList = costCalcRepository
					.findByDataCenterQuarterId(dataCenter.getDataCenterQuarterId());
			if (costCalcList != null && !costCalcList.isEmpty()) {
				costCalcEntity = costCalcList.get(costCalcList.size() - 1);
			} else {
				costCalcEntity = new CostCalculation();
			}
			Double serverCostTotal = findServerDifferenceAndCost(dataCenter);
			if (serverCostTotal == null) {
				Map<String, Object> costCalcMap = new HashMap<String, Object>();
				costCalcMap.put("dataCenterId", dataCenter.getDataCenterId());
				costCalcMap.put("totals", 0);
				return dataCenterIdTotalsPairs;
			}
			CommonHelper.modelMapper.map(dataCenter.getTotals().getCostCalc(), costCalcEntity);
			costCalcEntity.setDataCenterQuarterId(dataCenter.getDataCenterQuarterId());
			costCalcEntity.setServerCost(serverCostTotal);
			Double totalCost = serverCostTotal + setTotalCost(dataCenter);
			costCalcEntity.setTotal(totalCost);
			costCalcEntity = costCalcRepository.save(costCalcEntity);

			CommonHelper.modelMapper.map(costCalcEntity, dataCenter.getTotals().getCostCalc());
			Map<String, Object> costCalcMap = new HashMap<String, Object>();
			costCalcMap.put("dataCenterId", dataCenter.getDataCenterId());
			costCalcMap.put("totals", dataCenter.getTotals());
			dataCenterIdTotalsPairs.add(costCalcMap);
		}
		return dataCenterIdTotalsPairs;
	}

	/**
	 * Helper method to return the total cost
	 * 
	 * @param dataCenterDto
	 * @return
	 */
	private Double setTotalCost(DataCenterDto dataCenterDto) {
		CostCalculationDto costCalcDto = dataCenterDto.getTotals().getCostCalc();
		Double costTotals = 0d;
		if (costCalcDto.getMiscellaneous() != null) {
			costTotals += costCalcDto.getMiscellaneous();
		}
		if (costCalcDto.getStorageCost() != null) {
			costTotals += costCalcDto.getStorageCost();
		}
		return costTotals;
	}

	/**
	 * Find the server totals for this quarter and the previous, and then
	 * calculate the overall server cost
	 * 
	 * @param dataCenterDto
	 * @return
	 */
	private Double findServerDifferenceAndCost(DataCenterDto dataCenterDto) {

		if (dataCenterDto.getTotals() == null) {
			return null;
		}

		ServerInformationDto serverInfo = dataCenterDto.getTotals().getServerInfo();
		Double curQServerTotal = addServerCounts(serverInfo);

		// Get the past quarter
		DataCenterQuarter pastQuarterDataCenter = dataCenterQuarterRepository.findByQuarterReportIdAndDataCenterId(
				dataCenterDto.getQuarterReportId() - 1, dataCenterDto.getDataCenterId());

		Double pastQServerTotal;
		if (pastQuarterDataCenter == null) {
			pastQServerTotal = 0d;
		} else {
			pastQServerTotal = addServerCounts(pastQuarterDataCenter);
		}

		// Compute the difference
		Double serverDifference = pastQServerTotal - curQServerTotal;

		return serverDifference * 3000;
	}

	/**
	 * Find information about the past quarter
	 * 
	 * @param quarterId
	 * @return Long
	 */
	@Transactional(readOnly = true)
	public QuarterReport findPastQuarter(Long quarterId) {
		QuarterReport curQuarterReport = quarterReportRepository.findOne(quarterId);

		Integer pastQuarterYear;
		Integer pastQuarterQuarter;

		if (curQuarterReport.getFiscalQuarterId() == 1) {
			pastQuarterYear = curQuarterReport.getFiscalYearId() - 1;
			pastQuarterQuarter = 4;
		} else {
			pastQuarterYear = curQuarterReport.getFiscalYearId();
			pastQuarterQuarter = curQuarterReport.getFiscalQuarterId() - 1;
		}

		return quarterReportRepository.findByFiscalYearIdAndFiscalQuarterId(pastQuarterYear, pastQuarterQuarter);
	}

	/**
	 * Add server counts together
	 * 
	 * @param pastQuarterDataCenter
	 * @return
	 */
	private Double addServerCounts(DataCenterQuarter pastQuarterDataCenter) {
		Double serverCount = 0d;
		if (pastQuarterDataCenter.getTotalWindowsServers() != null) {
			serverCount += pastQuarterDataCenter.getTotalWindowsServers();
		}
		if (pastQuarterDataCenter.getTotalMainframes() != null) {
			serverCount += pastQuarterDataCenter.getTotalMainframes();
		}
		if (pastQuarterDataCenter.getTotalHPCClusterNodes() != null) {
			serverCount += pastQuarterDataCenter.getTotalHPCClusterNodes();
		}
		if (pastQuarterDataCenter.getTotalOtherServers() != null) {
			serverCount += pastQuarterDataCenter.getTotalOtherServers();
		}
		return serverCount;
	}

	/**
	 * Add server counts together
	 * 
	 * @param pastQuarterDataCenter
	 * @return
	 */
	private Double addServerCounts(ServerInformationDto serverInfo) {
		Double serverCount = 0d;
		if (serverInfo.getTotalWindowsServers() != null) {
			serverCount += Integer.valueOf(serverInfo.getTotalWindowsServers());
		}
		if (serverInfo.getTotalMainframes() != null) {
			serverCount += Integer.valueOf(serverInfo.getTotalMainframes());
		}
		if (serverInfo.getTotalHPCClusterNodes() != null) {
			serverCount += Integer.valueOf(serverInfo.getTotalHPCClusterNodes());
		}
		if (serverInfo.getTotalOtherServers() != null) {
			serverCount += Integer.valueOf(serverInfo.getTotalOtherServers());
		}
		return serverCount;
	}

	/**
	 * Populate the regionsDto Lists to display back for a quarter
	 * 
	 * @param quarterReportId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<RegionDto> populateRegionDtosList(Long quarterReportId) {
		List<RegionDto> regionDtos = populateInformationAboutRegions();
		for (RegionDto region : regionDtos) {
			region.setDataCenters(dataCenterService.populateDataCenterDtosList(region.getRegionId(), quarterReportId));
		}
		return regionDtos;
	}

	/**
	 * Temp method to populate the information about each of the regions
	 * 
	 * @return
	 */
	private List<RegionDto> populateInformationAboutRegions() {
		List<RegionDto> regionDtos = new ArrayList<RegionDto>();
		for (GenericReferenceValueObject valueObject : ReferenceValueListService.refValueLists
				.get("regionRefValueList")) {
			RegionDto regionDto = new RegionDto();
			regionDto.setName(valueObject.getValue());
			regionDto.setCode(valueObject.getValue().toLowerCase().replace(" ", ""));
			regionDto.setRegionId(valueObject.getId());
			regionDtos.add(regionDto);
		}
		return regionDtos;
	}

}
