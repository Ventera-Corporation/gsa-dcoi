package gov.gsa.dcoi.dto;

import java.util.Date;

/**
 * The quarter report contains all the information at a high-level about the
 * fiscal quarter that is being created including the dueDate of the report, the
 * year and the quarter, as well as tracking information for the report such as
 * quarterComplete, inProgress and submitted flags
 * 
 * @author sgonthier
 *
 */
public class FiscalQuarterReportDto {

	private long quarterId;
	private int fiscalYearId;
	private int fiscalQuarterId;
	private Date quarterDueDate;
	private int quarterActiveFlag;
	private int quarterInProgressFlag;
	private int quarterCompleteFlag;
	private int quarterSubmittedFlag;
	private int fiscalYear;
	private String fiscalQuarter;

	/**
	 * Constructor for fiscalQuarterReportDto - used to initialize create new
	 * quarter button
	 * 
	 * @param FiscalQuarterReportDto
	 */
	public FiscalQuarterReportDto(FiscalQuarterReportDto quarterReportDto) {
		quarterActiveFlag = 0;
		quarterInProgressFlag = 0;
		quarterCompleteFlag = 0;
		if (quarterReportDto.getFiscalQuarter().equals("Q4")) {
			fiscalYear = quarterReportDto.getFiscalYear() + 1;
			fiscalQuarter = "Q1";
		} else {
			fiscalYear = quarterReportDto.getFiscalYear();
			fiscalQuarterId = quarterReportDto.getFiscalQuarterId() + 1;
			setFiscalQuarter();
			fiscalQuarter = getFiscalQuarter();
		}
	}

	public FiscalQuarterReportDto() {
		// TODO Auto-generated constructor stub
	}

	public long getQuarterId() {
		return quarterId;
	}

	public void setQuarterId(long quarterId) {
		this.quarterId = quarterId;
	}

	public void setFiscalYear() {
		if (fiscalYearId == 5) {
			fiscalYear = 2014;
		} else if (fiscalYearId == 6) {
			fiscalYear = 2015;
		} else if (fiscalYearId == 7) {
			fiscalYear = 2016;
		} else if (fiscalYearId == 8) {
			fiscalYear = 2017;
		}
	}

	public int getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalQuarter() {
		if (fiscalQuarterId == 1) {
			fiscalQuarter = "Q1";
		} else if (fiscalQuarterId == 2) {
			fiscalQuarter = "Q2";
		} else if (fiscalQuarterId == 3) {
			fiscalQuarter = "Q3";
		} else if (fiscalQuarterId == 4) {
			fiscalQuarter = "Q4";
		}
	}

	public String getFiscalQuarter() {
		return fiscalQuarter;
	}

	// TO DO add parser from ref value list
	public int getFiscalYearId() {

		return fiscalYearId;

	}

	public void setFiscalYearId(int fiscalYearId) {
		this.fiscalYearId = fiscalYearId;
		setFiscalYear();
	}

	public int getFiscalQuarterId() {
		return fiscalQuarterId;
	}

	public void setFiscalQuarterId(int fiscalQuarterId) {
		this.fiscalQuarterId = fiscalQuarterId;
		setFiscalQuarter();
	}

	public Date getQuarterDueDate() {
		return quarterDueDate;
	}

	public void setQuarterDueDate(Date quarterDueDate) {
		this.quarterDueDate = quarterDueDate;
	}

	public int getQuarterActiveFlag() {
		return quarterActiveFlag;
	}

	public void setQuarterActiveFlag(int quarterActiveFlag) {
		this.quarterActiveFlag = quarterActiveFlag;
	}

	public int getQuarterInProgressFlag() {
		return quarterInProgressFlag;
	}

	public void setQuarterInProgressFlag(int quarterInProgressFlag) {
		this.quarterInProgressFlag = quarterInProgressFlag;
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
