package gov.gsa.dcoi.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gov.gsa.dcoi.dto.DashboardDto;
import gov.gsa.dcoi.dto.FiscalQuarterReportDto;
import gov.gsa.dcoi.entity.QuarterReport;
import gov.gsa.dcoi.service.DashboardService;
import gov.gsa.dcoi.service.QuarterService;

/**
 * Controller class to handle requests to initialize and populate the dashboard
 * 
 * @author sgonthier
 *
 */
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

	@Autowired
	DashboardService dashboardService;

	@Autowired
	QuarterService quarterService;

	/**
	 * Method to initialize the dashboard screen with the information about the
	 * various quarters
	 * 
	 * @return Map<String, Object> returnMap
	 */
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public Map<String, Object> initDashboard() {
		Map<String, Object> returnMap = new HashMap<>();
		DashboardDto dashboardData = new DashboardDto();
		List<FiscalQuarterReportDto> quarterReportDtos;

		quarterReportDtos = bulkConvertEntityToDto(dashboardService.initDashboard());
		if (!quarterService.findQuarterByActiveOrInProgressFlag()) {
			quarterReportDtos.add(new FiscalQuarterReportDto(quarterReportDtos.get(quarterReportDtos.size() - 1)));
		}

		dashboardData.setQuarters(quarterReportDtos);
		dashboardData.setYears(findYearsRepresentedOnDashboard(quarterReportDtos));

		returnMap.put("dashboardData", dashboardData);
		return returnMap;

	}

	/**
	 * Compile the data about the fiscal years that are represented within the
	 * report
	 * 
	 * @param quarterReportDtos
	 * @return
	 */
	private List<Integer> findYearsRepresentedOnDashboard(List<FiscalQuarterReportDto> quarterReportDtos) {
		List<Integer> yearsList = new ArrayList<>();
		for (FiscalQuarterReportDto quarterReportDto : quarterReportDtos) {
			Integer year = Integer.valueOf(quarterReportDto.getFiscalYear());
			if (!yearsList.contains(year)) {
				yearsList.add(year);
			}
		}
		Collections.reverse(yearsList);
		return yearsList;
	}

	/**
	 * Convert a single quarter report entity into a fiscalQuarterReportDto
	 * 
	 * @param quarterReportEntity
	 * @return
	 */
	private FiscalQuarterReportDto convertEntityToDto(QuarterReport quarterReportEntity) {
		FiscalQuarterReportDto quarterReportDto = new FiscalQuarterReportDto();
		BeanUtils.copyProperties(quarterReportEntity, quarterReportDto);
		return quarterReportDto;
	}

	/**
	 * Convert a List of QuarterReport entities into a list of
	 * FiscalQuarterReportDtos
	 * 
	 * @param quarterReportEntities
	 * @return
	 */
	private List<FiscalQuarterReportDto> bulkConvertEntityToDto(List<QuarterReport> quarterReportEntities) {
		List<FiscalQuarterReportDto> quarterReportDtos = new ArrayList<>();
		for (QuarterReport quarterReportEntity : quarterReportEntities) {
			quarterReportDtos.add(convertEntityToDto(quarterReportEntity));
		}

		return quarterReportDtos;
	}

}
