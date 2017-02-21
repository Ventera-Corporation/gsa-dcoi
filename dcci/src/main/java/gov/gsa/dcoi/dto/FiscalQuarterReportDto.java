package gov.gsa.dcoi.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.dcoi.DcoiExceptionHandler;
import gov.gsa.dcoi.service.CommonHelper;

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

	private static final Logger LOGGER = LoggerFactory.getLogger(FiscalQuarterReportDto.class);

	private Long quarterId;
	private Integer fiscalYearId;
	private Integer fiscalQuarterId;
	private String quarterDueDate;
	private Integer quarterActiveFlag;
	private Integer quarterInProgressFlag;
	private Integer quarterCompleteFlag;
	private Integer totalNumDataCenters;
	private Integer dataCentersInProgress;
	private Integer dataCentersCompleted;

	/**
	 * Constructor for fiscalQuarterReportDto - used to initialize create new
	 * quarter button
	 * 
	 * @param quarterReportDto
	 */
	public FiscalQuarterReportDto(FiscalQuarterReportDto quarterReportDto) {
		quarterActiveFlag = 0;
		quarterInProgressFlag = 0;
		quarterCompleteFlag = 0;
		if (quarterReportDto.getFiscalQuarterId() == 4) {
			fiscalYearId = quarterReportDto.getFiscalYearId() + 1;
			fiscalQuarterId = 1;
		} else {
			fiscalYearId = quarterReportDto.getFiscalYearId();
			fiscalQuarterId = quarterReportDto.getFiscalQuarterId() + 1;
		}
	}

	public FiscalQuarterReportDto() {
		// TODO Auto-generated constructor stub
	}

	public Long getQuarterId() {
		return quarterId;
	}

	public void setQuarterId(Long quarterId) {
		this.quarterId = quarterId;
	}

	public String getFiscalQuarter() {
		return CommonHelper.parseFiscalQuarterId(this.fiscalQuarterId);
	}

	public String getFiscalYear() {
		return CommonHelper.parseFiscalYearId(this.fiscalYearId);
	}

	// TO DO add parser from ref value list
	public int getFiscalYearId() {
		return fiscalYearId;
	}

	public void setFiscalYearId(int fiscalYearId) {
		this.fiscalYearId = fiscalYearId;
	}

	public int getFiscalQuarterId() {
		return fiscalQuarterId;
	}

	public void setFiscalQuarterId(int fiscalQuarterId) {
		this.fiscalQuarterId = fiscalQuarterId;
	}

	public String getQuarterDueDate() {
		return quarterDueDate;
	}

	public void setQuarterDueDate(String quarterDueDate) {
		if (quarterDueDate != null && quarterDueDate.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}.*")) {
			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy");

			try {
				this.quarterDueDate = outputFormat.format(inputFormat.parse(quarterDueDate));
			} catch (ParseException e) {
				LOGGER.error(e.getMessage());
				throw DcoiExceptionHandler.throwDcoiException("Exception parsing due date from DB: " + e.getMessage());
			}

		} else {
			this.quarterDueDate = quarterDueDate;
		}
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

	public Integer getDataCentersInProgress() {
		return dataCentersInProgress;
	}

	public void setDataCentersInProgress(Integer dataCentersInProgress) {
		this.dataCentersInProgress = dataCentersInProgress;
	}

	public Integer getDataCentersCompleted() {
		return dataCentersCompleted;
	}

	public void setDataCentersCompleted(Integer dataCentersCompleted) {
		this.dataCentersCompleted = dataCentersCompleted;
	}

	public void setFiscalQuarterId(Integer fiscalQuarterId) {
		this.fiscalQuarterId = fiscalQuarterId;
	}

	public Integer getTotalNumDataCenters() {
		return totalNumDataCenters;
	}

	public void setTotalNumDataCenters(Integer totalNumDataCenters) {
		this.totalNumDataCenters = totalNumDataCenters;
	}

}
