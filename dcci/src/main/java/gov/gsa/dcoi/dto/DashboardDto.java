package gov.gsa.dcoi.dto;

import java.util.List;

/**
 * Data transfer object to hold the info for the admin dashboard
 * 
 * @author sgonthier
 *
 */
public class DashboardDto {

	private List<Integer> years;
	private List<FiscalQuarterReportDto> quarters;

	/**
	 * Get list of years that are represented on the dashboard
	 * 
	 * @return
	 */
	public List<Integer> getYears() {
		return years;
	}

	/**
	 * The list of years to set
	 * 
	 * @param years
	 */
	public void setYears(List<Integer> years) {
		this.years = years;
	}

	/**
	 * Gets the list of quarterReports that will be shown on dashboard
	 * 
	 * @return
	 */
	public List<FiscalQuarterReportDto> getQuarters() {
		return quarters;
	}

	/**
	 * The list of QuarterReports to set
	 * 
	 * @param quarters
	 */
	public void setQuarters(List<FiscalQuarterReportDto> quarters) {
		this.quarters = quarters;
	}

}
