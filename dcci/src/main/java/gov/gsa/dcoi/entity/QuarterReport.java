package gov.gsa.dcoi.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class QuarterReport {
	
	@Id
	private Integer quarterReportId;
	private Integer fiscalYearId;
	private String fiscalQuarterId;
	private Date quarterDueDate;
	private Integer activeFlag;
	private Integer inProgressFlag;
	private Integer quarterCompleteFlag;
	private Integer quarterSubmittedFlag;
	
	public Integer getQuarterReportId() {
		return quarterReportId;
	}
	public void setQuarterReportId(Integer quarterReportId) {
		this.quarterReportId = quarterReportId;
	}
	public Integer getFiscalYearId() {
		return fiscalYearId;
	}
	public void setFiscalYearId(Integer fiscalYearId) {
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
	public Integer getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}
	public Integer getInProgressFlag() {
		return inProgressFlag;
	}
	public void setInProgressFlag(Integer inProgressFlag) {
		this.inProgressFlag = inProgressFlag;
	}
	public Integer getQuarterCompleteFlag() {
		return quarterCompleteFlag;
	}
	public void setQuarterCompleteFlag(Integer quarterCompleteFlag) {
		this.quarterCompleteFlag = quarterCompleteFlag;
	}
	public Integer getQuarterSubmittedFlag() {
		return quarterSubmittedFlag;
	}
	public void setQuarterSubmittedFlag(Integer quarterSubmittedFlag) {
		this.quarterSubmittedFlag = quarterSubmittedFlag;
	}
	
}
