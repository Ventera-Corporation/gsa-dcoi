package gov.gsa.dcci.entity;

import javax.persistence.Entity;

@Entity
public class DataCenterInventory {
	
	private int dataCenterInventoryId;
	private int dataCenterId;
	private int fiscalQuarterReportId;
	private int parentDataCenterInventoryId;
	private int agencyComponentId;
	private String dcoiDataCenterId;
	private String publishedName;
	private int recordStatusId;
	private int recordValidityId;
	private int ownershipTypeId;
	private int dataCenterTierId;
	private int countryId;
	private int grossFloorArea;
	private int customerFloorAreaTotal;
	private int electricityMeteredFg;
	private double electricityUsageAvg;
	private double itElectricityUsageAvg;
	private int automatedMonitoring;
	private double fte;
	private int rackCount;
	private int mainframeCount;
	private int windowsServerCount;
	private int hpcClusterCount;
	private int otherServerCount;
	private int virtualHostCount;
	private int virtualOSCount;
	private double storageTotal;
	private double storageUsed;
	private int closingStageId;
	private int closingFiscalYearId;
	private int closingFiscalQuarterId;
	private int issPositionId;
	private int issProvider;
	private String dataCenterName;
	
	public int getDataCenterInventoryId(){
		return dataCenterInventoryId;
	}
	
	public void setDataCenterInventoryId(int dataCenterInventoryId){
		this.dataCenterInventoryId = dataCenterInventoryId;
	}
	
	public int getDataCenterId(){
		return dataCenterId;
	}
	
	public void setDataCenterId(int dataCenterId){
		this.dataCenterId = dataCenterId;
	}
	
	public int getFiscalQuarterReportId(){
		return fiscalQuarterReportId;
	}
	
	public void setFiscalQuarterReportId(int fiscalQuarterReportId){
		this.fiscalQuarterReportId = fiscalQuarterReportId;
	}
	
	public int getParentDataCenterInventoryId(){
		return parentDataCenterInventoryId;
	}
	
	public void setParentDataCenterInventoryId(int parentDataCenterInventoryId){
		this.parentDataCenterInventoryId = parentDataCenterInventoryId;
	}
	
	public int getAgencyComponentId(){
		return agencyComponentId;
	}
	
	public void setAgencyComponentId(int agencyComponentId){
		this.agencyComponentId = agencyComponentId;
	}
	public String getDcoiDataCenterId(){
		return dcoiDataCenterId;
	}
	
	public void setDcoiDataCenterId(String dcoiDataCenterId){
		this.dcoiDataCenterId = dcoiDataCenterId;
	}
	
	public String getPublishedName(){
		return publishedName;
	}
	
	public void setPublishedName(String publishedName){
		this.publishedName = publishedName;
	}
	
	public int getRecordStatusId(){
		return recordStatusId;
	}
	
	public void setRecordStatusId(int recordStatusId){
		this.recordStatusId = recordStatusId;
	}
	
	public int getRecordValidityId(){
		return recordValidityId;
	}
	
	public void setRecordValidityId(int recordValidityId){
		this.recordValidityId = recordValidityId;
	}
	
	public int getOwnershipTypeId(){
		return ownershipTypeId;
	}
	
	public void setOwnershipTypeId(int ownershipTypeId){
		this.ownershipTypeId = ownershipTypeId;
	}
	public int getDataCenterTierId(){
		return dataCenterTierId;
	}
	
	public void setDataCenterTierId(int dataCenterTierId){
		this.dataCenterTierId = dataCenterTierId;
	}
	
	public int getCountryId(){
		return countryId;
	}
	
	public void setCountryId(int countryId){
		this.countryId = countryId;
	}
	
	public int getGrossFloorArea(){
		return grossFloorArea;
	}
	
	public void setGrossFloorArea(int grossFloorArea){
		this.grossFloorArea = grossFloorArea;
	}
	
	public int getCustomerFloorAreaTotal(){
		return customerFloorAreaTotal;
	}
	
	public void setCustomerFloorAreaTotal(int customerFloorAreaTotal){
		this.customerFloorAreaTotal = customerFloorAreaTotal;
	}
	
	public int getElectricityMeteredFg(){
		return electricityMeteredFg;
	}
	
	public void setElectricityMeteredFg(int electricityMeteredFg){
		this.electricityMeteredFg = electricityMeteredFg;
	}
	public double getElectricityUsageAvg(){
		return electricityUsageAvg;
	}
	
	public void setElectricityUsageAvg(double electricityUsageAvg){
		this.electricityUsageAvg = electricityUsageAvg;
	}
	
	public double getItElectricityUsageAvg(){
		return itElectricityUsageAvg;
	}
	
	public void setItElectricityUsageAvg(double itElectricityUsageAvg){
		this.itElectricityUsageAvg = itElectricityUsageAvg;
	}
	
	public int getAutomatedMonitoring(){
		return automatedMonitoring;
	}
	
	public void setAutomatedMonitoring(int automatedMonitoring){
		this.automatedMonitoring = automatedMonitoring;
	}
	
	public double getFte(){
		return fte;
	}
	
	public void setFte(double fte){
		this.fte = fte;
	}
	
	public int getRackCount(){
		return rackCount;
	}
	
	public void setRackCount(int rackCount){
		this.rackCount = rackCount;
	}
	
	public int getMainframeCount(){
		return mainframeCount;
	}
	
	public void setMainframeCount(int mainframeCount){
		this.mainframeCount = mainframeCount;
	}
	
	public int getWindowsServerCount(){
		return windowsServerCount;
	}
	
	public void setWindowsServerCount(int windowsServerCount){
		this.windowsServerCount = windowsServerCount;
	}
	
	public int getHpcClusterCount(){
		return hpcClusterCount;
	}
	
	public void setHpcClusterCount(int hpcClusterCount){
		this.hpcClusterCount = hpcClusterCount;
	}
	
	public int getOtherServerCount(){
		return otherServerCount;
	}
	
	public void setOtherServerCount(int otherServerCount){
		this.otherServerCount = otherServerCount;
	}
	
	public int getVirtualHostCount(){
		return virtualHostCount;
	}
	
	public void setVirtualHostCount(int virtualHostCount){
		this.virtualHostCount = virtualHostCount;
	}
	
	public int getVirtualOSCount(){
		return virtualOSCount;
	}
	
	public void setVirtualOSCount(int virtualOSCount){
		this.virtualOSCount = virtualOSCount;
	}
	
	public double getStorageTotal(){
		return storageTotal;
	}
	
	public void setStorageTotal(double storageTotal){
		this.storageTotal = storageTotal;
	}
	
	public double getStorageUsed(){
		return storageUsed;
	}
	
	public void setStorageUsed(double storageUsed){
		this.storageUsed = storageUsed;
	}
	
	public int getClosingStageId(){
		return closingStageId;
	}
	
	public void setClosingStageId(int closingStageId){
		this.closingStageId = closingStageId;
	}
	
	public int getClosingFiscalYearId(){
		return closingFiscalYearId;
	}
	
	public void setClosingFiscalYearId(int closingFiscalYearId){
		this.closingFiscalYearId = closingFiscalYearId;
	}
	
	public int getClosingFiscalQuarterId(){
		return closingFiscalQuarterId;
	}
	
	public void setClosingFiscalQuarterId(int closingFiscalQuarterId){
		this.closingFiscalQuarterId = closingFiscalQuarterId;
	}
	
	
	public int getIssPositionId(){
		return issPositionId;
	}
	
	public void setIssPositionId(int issPositionId){
		this.issPositionId = issPositionId;
	}
	
	public int getIssProvider(){
		return issProvider;
	}
	
	public void setIssProvider(int issProvider){
		this.issProvider = issProvider;
	}
	
	public String getDataCenterName(){
		return dataCenterName;
	}
	
	public void setDataCenterName(String dataCenterName){
		this.dataCenterName = dataCenterName;
	}


}
