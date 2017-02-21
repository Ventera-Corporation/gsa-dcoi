package gov.gsa.dcoi.dto;

import java.util.List;

/**
 * The main dto of the application - this holds information for the fiscal
 * quarter report as well as the information for every dataCenter to be
 * displayed (stored within/by regions)
 * 
 * @author sgonthier
 *
 */
public class QuarterDto {

	private FiscalQuarterReportDto fiscalQuarterReport;
	private List<RegionDto> regions;
	private String quarterDueDate;

	/**
	 * The FiscalQuarterReport to return
	 * 
	 * @return
	 */
	public FiscalQuarterReportDto getFiscalQuarterReport() {
		return fiscalQuarterReport;
	}

	/**
	 * The fiscalQuarterReport to be set
	 * 
	 * @param fiscalQuarterReport
	 */
	public void setFiscalQuarterReport(FiscalQuarterReportDto fiscalQuarterReport) {
		this.fiscalQuarterReport = fiscalQuarterReport;
	}

	/**
	 * The list of regions, which by ultimately gets the list of data centers
	 * 
	 * @return
	 */
	public List<RegionDto> getRegions() {
		return regions;
	}

	/**
	 * The regions information to be save (again, this is ultimately the data
	 * centers that are being set)
	 * 
	 * @param regions
	 */
	public void setRegions(List<RegionDto> regions) {
		this.regions = regions;
	}

	public String getQuarterDueDate() {
		return quarterDueDate;
	}

	public void setQuarterDueDate(String quarterDueDate) {
		this.quarterDueDate = quarterDueDate;
	}

}
