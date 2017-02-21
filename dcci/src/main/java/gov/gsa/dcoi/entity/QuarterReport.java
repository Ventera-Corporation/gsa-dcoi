package gov.gsa.dcoi.entity;

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
	private String quarterDueDate;
	@Column(name = "active_flag")
	private Integer quarterActiveFlag;
	@Column(name = "in_progress_flag")
	private Integer quarterInProgressFlag;
	private Integer quarterCompleteFlag;

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

	public String getQuarterDueDate() {
		return quarterDueDate;
	}

	public void setQuarterDueDate(String quarterDueDate) {
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
}
