package gov.gsa.dcoi.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "data_center_inventory")
public class FieldOffice {
	
	@Id
	private int dataCenterInventoryId;
	
	//Status
	private int recordStatusId;
	private int recordValidityId;
	private int ownershipTypeId;
	private int dataCenterTierId;
	private int electricityIncludedInCostFlag;
	private int electricityMeteredFlag;
	private int automatedMonitoring;
	private int coreClassificationId;
	private int closingStageId;
	private int closingFiscalYearId;
	private int closingFiscalQuarterId;
	private int issPositionId;
	private int issProvider;
	
	//General Information
	private int dataCenterId;
	private int fiscalQuarterReportId;
	private int parentDataCenterInventoryId;
	private int agencyDataCenterId;
	private String dcoiDataCenterId;
	private String publishedName;
	private String streetAddress;
	private String streetAddress2;
	private String city;
	private String zipCode;
	private int countryId;
	private String dataCenterName;
	
	//Favility Information
	private int grossFloorArea;
	private int customerFloorAreaTotal;
	private double annualCostSqFt;
	private String otherAgenciesServiced;
	private int powerCapacityTotal;
	private double electricityUsageAvg;
	private double itPowerCapacityTotal;
	private double itElectricityUsageAvg;
	private double costPerKwh;
	private double fte;
	private double fteCost;
	private int rackCount;
	
	//Server Information
	private double serverUtilization;
	private int mainframeCount;
	private int windowsServerCount;
	private int hpcClusterCount;
	private int otherServerCount;
	private int virtualHostCount;
	private int virtualOSCount;
	private double storageTotal;
	private double storageUsed;
	
	public double getServerUtilization() {
		return serverUtilization;
	}
	public void setServerUtilization(double serverUtilization) {
		this.serverUtilization = serverUtilization;
	}
	public int getMainframeCount() {
		return mainframeCount;
	}
	public void setMainframeCount(int mainframeCount) {
		this.mainframeCount = mainframeCount;
	}
	public int getWindowsServerCount() {
		return windowsServerCount;
	}
	public void setWindowsServerCount(int windowsServerCount) {
		this.windowsServerCount = windowsServerCount;
	}
	public int getHpcClusterCount() {
		return hpcClusterCount;
	}
	public void setHpcClusterCount(int hpcClusterCount) {
		this.hpcClusterCount = hpcClusterCount;
	}
	public int getOtherServerCount() {
		return otherServerCount;
	}
	public void setOtherServerCount(int otherServerCount) {
		this.otherServerCount = otherServerCount;
	}
	public int getVirtualHostCount() {
		return virtualHostCount;
	}
	public void setVirtualHostCount(int virtualHostCount) {
		this.virtualHostCount = virtualHostCount;
	}
	public int getVirtualOSCount() {
		return virtualOSCount;
	}
	public void setVirtualOSCount(int virtualOSCount) {
		this.virtualOSCount = virtualOSCount;
	}
	public double getStorageTotal() {
		return storageTotal;
	}
	public void setStorageTotal(double storageTotal) {
		this.storageTotal = storageTotal;
	}
	public double getStorageUsed() {
		return storageUsed;
	}
	public void setStorageUsed(double storageUsed) {
		this.storageUsed = storageUsed;
	}
	
	public int getGrossFloorArea() {
		return grossFloorArea;
	}
	public void setGrossFloorArea(int grossFloorArea) {
		this.grossFloorArea = grossFloorArea;
	}
	public int getCustomerFloorAreaTotal() {
		return customerFloorAreaTotal;
	}
	public void setCustomerFloorAreaTotal(int customerFloorAreaTotal) {
		this.customerFloorAreaTotal = customerFloorAreaTotal;
	}
	public double getAnnualCostSqFt() {
		return annualCostSqFt;
	}
	public void setAnnualCostSqFt(double annualCostSqFt) {
		this.annualCostSqFt = annualCostSqFt;
	}
	public String getOtherAgenciesServiced() {
		return otherAgenciesServiced;
	}
	public void setOtherAgenciesServiced(String otherAgenciesServiced) {
		this.otherAgenciesServiced = otherAgenciesServiced;
	}
	public int getPowerCapacityTotal() {
		return powerCapacityTotal;
	}
	public void setPowerCapacityTotal(int powerCapacityTotal) {
		this.powerCapacityTotal = powerCapacityTotal;
	}
	public double getElectricityUsageAvg() {
		return electricityUsageAvg;
	}
	public void setElectricityUsageAvg(double electricityUsageAvg) {
		this.electricityUsageAvg = electricityUsageAvg;
	}
	public double getItPowerCapacityTotal() {
		return itPowerCapacityTotal;
	}
	public void setItPowerCapacityTotal(double itPowerCapacityTotal) {
		this.itPowerCapacityTotal = itPowerCapacityTotal;
	}
	public double getItElectricityUsageAvg() {
		return itElectricityUsageAvg;
	}
	public void setItElectricityUsageAvg(double itElectricityUsageAvg) {
		this.itElectricityUsageAvg = itElectricityUsageAvg;
	}
	public double getCostPerKwh() {
		return costPerKwh;
	}
	public void setCostPerKwh(double costPerKwh) {
		this.costPerKwh = costPerKwh;
	}
	public double getFte() {
		return fte;
	}
	public void setFte(double fte) {
		this.fte = fte;
	}
	public double getFteCost() {
		return fteCost;
	}
	public void setFteCost(double fteCost) {
		this.fteCost = fteCost;
	}
	public int getRackCount() {
		return rackCount;
	}
	public void setRackCount(int rackCount) {
		this.rackCount = rackCount;
	}
	

	
	
	public int getDataCenterId() {
		return dataCenterId;
	}
	public void setDataCenterId(int dataCenterId) {
		this.dataCenterId = dataCenterId;
	}
	public int getFiscalQuarterReportId() {
		return fiscalQuarterReportId;
	}
	public void setFiscalQuarterReportId(int fiscalQuarterReportId) {
		this.fiscalQuarterReportId = fiscalQuarterReportId;
	}
	public int getParentDataCenterInventoryId() {
		return parentDataCenterInventoryId;
	}
	public void setParentDataCenterInventoryId(int parentDataCenterInventoryId) {
		this.parentDataCenterInventoryId = parentDataCenterInventoryId;
	}
	public int getAgencyDataCenterId() {
		return agencyDataCenterId;
	}
	public void setAgencyDataCenterId(int agencyDataCenterId) {
		this.agencyDataCenterId = agencyDataCenterId;
	}
	public String getDcoiDataCenterId() {
		return dcoiDataCenterId;
	}
	public void setDcoiDataCenterId(String dcoiDataCenterId) {
		this.dcoiDataCenterId = dcoiDataCenterId;
	}
	public String getPublishedName() {
		return publishedName;
	}
	public void setPublishedName(String publishedName) {
		this.publishedName = publishedName;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getStreetAddress2() {
		return streetAddress2;
	}
	public void setStreetAddress2(String streetAddress2) {
		this.streetAddress2 = streetAddress2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getDataCenterName() {
		return dataCenterName;
	}
	public void setDataCenterName(String dataCenterName) {
		this.dataCenterName = dataCenterName;
	}
	
	public int getRecordStatusId() {
		return recordStatusId;
	}
	public void setRecordStatusId(int recordStatusId) {
		this.recordStatusId = recordStatusId;
	}
	public int getRecordValidityId() {
		return recordValidityId;
	}
	public void setRecordValidityId(int recordValidityId) {
		this.recordValidityId = recordValidityId;
	}
	public int getOwnershipTypeId() {
		return ownershipTypeId;
	}
	public void setOwnershipTypeId(int ownershipTypeId) {
		this.ownershipTypeId = ownershipTypeId;
	}
	public int getDataCenterTierId() {
		return dataCenterTierId;
	}
	public void setDataCenterTierId(int dataCenterTierId) {
		this.dataCenterTierId = dataCenterTierId;
	}
	public int getElectricityIncludedInCostFlag() {
		return electricityIncludedInCostFlag;
	}
	public void setElectricityIncludedInCostFlag(int electricityIncludedInCostFlag) {
		this.electricityIncludedInCostFlag = electricityIncludedInCostFlag;
	}
	public int getElectricityMeteredFlag() {
		return electricityMeteredFlag;
	}
	public void setElectricityMeteredFlag(int electricityMeteredFlag) {
		this.electricityMeteredFlag = electricityMeteredFlag;
	}
	public int getAutomatedMonitoring() {
		return automatedMonitoring;
	}
	public void setAutomatedMonitoring(int automatedMonitoring) {
		this.automatedMonitoring = automatedMonitoring;
	}
	public int getCoreClassificationId() {
		return coreClassificationId;
	}
	public void setCoreClassificationId(int coreClassificationId) {
		this.coreClassificationId = coreClassificationId;
	}
	public int getClosingStageId() {
		return closingStageId;
	}
	public void setClosingStageId(int closingStageId) {
		this.closingStageId = closingStageId;
	}
	public int getClosingFiscalYearId() {
		return closingFiscalYearId;
	}
	public void setClosingFiscalYearId(int closingFiscalYearId) {
		this.closingFiscalYearId = closingFiscalYearId;
	}
	public int getClosingFiscalQuarterId() {
		return closingFiscalQuarterId;
	}
	public void setClosingFiscalQuarterId(int closingFiscalQuarterId) {
		this.closingFiscalQuarterId = closingFiscalQuarterId;
	}
	public int getIssPositionId() {
		return issPositionId;
	}
	public void setIssPositionId(int issPositionId) {
		this.issPositionId = issPositionId;
	}
	public int getIssProvider() {
		return issProvider;
	}
	public void setIssProvider(int issProvider) {
		this.issProvider = issProvider;
	}
	
	public int getDataCenterInventoryId(){
		return dataCenterInventoryId;
	}
	
	public void setDataCenterInventoryId(int dataCenterInventoryId){
		this.dataCenterInventoryId = dataCenterInventoryId;
	}
}
