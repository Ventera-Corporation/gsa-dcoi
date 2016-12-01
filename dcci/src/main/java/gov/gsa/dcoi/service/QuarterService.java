package gov.gsa.dcoi.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.gsa.dcoi.dto.DataCenterDto;
import gov.gsa.dcoi.dto.FieldOfficeDto;
import gov.gsa.dcoi.dto.FiscalQuarterReportDto;
import gov.gsa.dcoi.dto.QuarterDto;
import gov.gsa.dcoi.dto.RegionDto;
import gov.gsa.dcoi.entity.DataCenterQuarter;
import gov.gsa.dcoi.entity.QuarterReport;
import gov.gsa.dcoi.repository.DataCenterQuarterRepository;
import gov.gsa.dcoi.repository.QuarterStoredProcedure;
import gov.gsa.dcoi.repository.QuarterReportRepository;

@Component
public class QuarterService {
	
	@Autowired 
	QuarterStoredProcedure quarterRepository;
	
	@Autowired
	QuarterReportRepository quarterReportRepository;
	
	@Autowired
	DataCenterQuarterRepository dataCenterQuarterRepository;
	
	@Autowired 
	FieldOfficeService fieldOfficeService;

	/**
	 * This call creates the new quarter report and, will then populate all the datacenters
	 * with the "new" info - will eventually pass back a fully created 
	 */
	@Transactional
	public QuarterDto initQuarter(){
		//Will call the initQuarter with a 0 and then a 1 - depending on the output from these
		// will then look for quarter report ID
		// If this is a non-zero value then we will proceed with capturing all the field offices
		// for NOW it is just data centers 
		
		//quarterRepository.initQuarter(0); //Create the new entry in quarter_report
		//quarterRepository.initQuarter(1); //move forward the data from last quarter to this one
		QuarterDto newQuarter = new QuarterDto();
		QuarterReport quarterReport = quarterReportRepository.findByInProgressFlag(1);
		if(quarterReport != null){
			newQuarter.setFiscalQuarterReport(populateFiscalQuarterReportDto(quarterReport));
			List<DataCenterQuarter> dataCentersForQuarter = dataCenterQuarterRepository.findByQuarterReportId(quarterReport.getQuarterReportId());
			List<RegionDto> regionDtos = new ArrayList<>();
			RegionDto regionDto = new RegionDto();
			List<DataCenterDto> dataCenterDtos = new ArrayList<>();
			for(DataCenterQuarter dcq : dataCentersForQuarter){
				DataCenterDto dataCenterDto = new DataCenterDto();
				dataCenterDto.setDataCenterName("Data Center Name: " + dcq.getDataCenterId());
				dataCenterDto.setDcoiDataCenterId(dcq.getDataCenterId());
				dataCenterDto.setDataCenterId(dcq.getDataCenterId());

				//set general info
				List<FieldOfficeDto> fieldOfficesDto = new ArrayList<>();
				fieldOfficesDto.add(fieldOfficeService.copyEntityToDto(dcq));
				dataCenterDto.setFieldOffices(fieldOfficesDto);
				dataCenterDtos.add(dataCenterDto);
			}
			
			regionDto.setName("New England");
			regionDto.setCode("newEngland");
			regionDto.setId(0);
			regionDto.setDataCenters(dataCenterDtos);
			regionDtos.add(regionDto);
			newQuarter.setRegions(regionDtos);
			return newQuarter;
		}
		
		return new QuarterDto();

	}
	
	/**
	 * Populate the fiscal quarter report dto for use on the front end from the quarter report 
	 * information we received 
	 * @param quarterReport
	 * @return
	 */
	private FiscalQuarterReportDto populateFiscalQuarterReportDto(QuarterReport quarterReport){
		FiscalQuarterReportDto quarterReportDto = new FiscalQuarterReportDto();
		BeanUtils.copyProperties(quarterReport, quarterReportDto);
		return quarterReportDto;
	}
	
	
	/**
	 * Update the active flag to one so that we know this is the active quarter (the datacenters will be saved through
	 * the field office service)
	 * TO-DO set the in progress flag to 0??
	 * @param quarterReport
	 */
	@Transactional
	public void createQuarter(QuarterReport quarterReport){
		quarterReport.setActiveFlag(1);
		quarterReportRepository.save(quarterReport);
	}
}
