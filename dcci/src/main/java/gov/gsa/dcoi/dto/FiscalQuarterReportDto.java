package gov.gsa.dcoi.dto;

import java.util.Date;

public class FiscalQuarterReportDto {
	
	private int quarterReportId;
	private int fiscalYearId;
	private String fiscalQuarterId;
	private Date quarterDueDate;
	private int activeFlag;
	private int inProgressFlag;
	private int quarterCompleteFlag;
	private int quarterSubmittedFlag;
	
	public int getQuarterReportId() {
		return quarterReportId;
	}
	public void setQuarterReportId(int quarterReportId) {
		this.quarterReportId = quarterReportId;
	}
	public int getFiscalYearId() {
		return fiscalYearId;
	}
	public void setFiscalYearId(int fiscalYearId) {
		this.fiscalYearId = fiscalYearId;
	}
	public String getFiscalQuarterId() {
		return fiscalQuarterId;
	}
	public void setFiscalQuarterId(String fiscalQuarterId) {
		this.fiscalQuarterId = fiscalQuarterId;
	}
	public Date getQuarterDueDate() {
		return quarterDueDate;
	}
	public void setQuarterDueDate(Date quarterDueDate) {
		this.quarterDueDate = quarterDueDate;
	}
	public int getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}
	public int getInProgressFlag() {
		return inProgressFlag;
	}
	public void setInProgressFlag(int inProgressFlag) {
		this.inProgressFlag = inProgressFlag;
	}
	public int getQuarterCompleteFlag() {
		return quarterCompleteFlag;
	}
	public void setQuarterCompleteFlag(int quarterCompleteFlag) {
		this.quarterCompleteFlag = quarterCompleteFlag;
	}
	public int getQuarterSubmittedFlag() {
		return quarterSubmittedFlag;
	}
	public void setQuarterSubmittedFlag(int quarterSubmittedFlag) {
		this.quarterSubmittedFlag = quarterSubmittedFlag;
	}
	
	

}
