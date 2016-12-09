package gov.gsa.dcoi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Class that holds overall information about the quarter, including the fiscal
 * year and quarter as well as flags that help document the workflow of the
 * quarter
 * 
 * @author sgonthier
 *
 */
@Entity
public class QuarterReport {

	@Id
	@Column(name = "quarter_report_id")
	private Long quarterId;
	private Integer fiscalYearId;
	private Integer fiscalQuarterId;
	private Date quarterDueDate;
	@Column(name = "active_flag")
	private Integer quarterActiveFlag;
	@Column(name = "in_progress_flag")
	private Integer quarterInProgressFlag;
	private Integer quarterCompleteFlag;
	private Integer quarterSubmittedFlag;

	public Long getQuarterId() {
		return quarterId;
	}

	public void setQuarterId(Long quarterId) {
		this.quarterId = quarterId;
	}

	public Integer getFiscalYearId() {
		return fiscalYearId;
	}

	public void setFiscalYearId(Integer fiscalYearId) {
		this.fiscalYearId = fiscalYearId;
	}

	public Integer getFiscalQuarterId() {
		return fiscalQuarterId;
	}

	public void setFiscalQuarterId(Integer fiscalQuarterId) {
		this.fiscalQuarterId = fiscalQuarterId;
	}

	public Date getQuarterDueDate() {
		return quarterDueDate;
	}

	public void setQuarterDueDate(Date quarterDueDate) {
		this.quarterDueDate = quarterDueDate;
	}

	public Integer getQuarterActiveFlag() {
		return quarterActiveFlag;
	}

	public void setQuarterActiveFlag(Integer quarterActiveFlag) {
		this.quarterActiveFlag = quarterActiveFlag;
	}

	public Integer getQuarterInProgressFlag() {
		return quarterInProgressFlag;
	}

	public void setQuarterInProgressFlag(Integer quarterInProgressFlag) {
		this.quarterInProgressFlag = quarterInProgressFlag;
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
