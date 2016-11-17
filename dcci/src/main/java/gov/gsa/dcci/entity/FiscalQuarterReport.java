package gov.gsa.dcci.entity;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class FiscalQuarterReport {
	private int fiscalQuarterReportId;
	private int fiscalYear;
	private String fiscalQuarter;
	private Date quarterDueDate;
	private int quarterCompleteFlag;
	private int quarterSubmittedFlag;
	
	public int getFiscalQuarterReportId(){
		return fiscalQuarterReportId;
	}
	
	public void setFiscalQuarterReportId(int fiscalQuarterReportId){
		this.fiscalQuarterReportId = fiscalQuarterReportId;
	}
	
	public int getFiscalYear(){
		return fiscalYear;
	}
	
	public void setFiscalYear(int fiscalYear){
		this.fiscalYear = fiscalYear;
	}
	
	public String getFiscalQuarter(){
		return fiscalQuarter;
	}
	
	public void setFiscalQuarter(String fiscalQuarter){
		this.fiscalQuarter = fiscalQuarter;
	}
	
	public Date getQuarterDueDate(){
		return quarterDueDate;
	}
	
	public void setQuarterDueDate(Date quarterDueDate){
		this.quarterDueDate = quarterDueDate;
	}
	
	public int getQuarterCompleteFlag(){
		return quarterCompleteFlag;
	}
	
	public void setQuarterCompleteFlag(int quarterCompleteFlag){
		this.quarterCompleteFlag = quarterCompleteFlag;
	}
	
	public int getQuarterSubmittedFlag(){
		return quarterSubmittedFlag;
	}
	
	public void setQuarterSubmittedFlag(int quarterSubmittedFlag){
		this.quarterSubmittedFlag = quarterSubmittedFlag;
	}

}
