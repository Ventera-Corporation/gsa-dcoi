package gov.gsa.dcoi.dto;

import java.util.List;

public class QuarterDto {

	private FiscalQuarterReportDto fiscalQuarterReport;
	private List<RegionDto> regions;

	public FiscalQuarterReportDto getFiscalQuarterReport() {
		return fiscalQuarterReport;
	}

	public void setFiscalQuarterReport(FiscalQuarterReportDto fiscalQuarterReport) {
		this.fiscalQuarterReport = fiscalQuarterReport;
	}

	public List<RegionDto> getRegions() {
		return regions;
	}

	public void setRegions(List<RegionDto> regions) {
		this.regions = regions;
	}

}
