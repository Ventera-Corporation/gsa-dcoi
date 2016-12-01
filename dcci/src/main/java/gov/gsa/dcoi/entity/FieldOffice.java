package gov.gsa.dcoi.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "data_center_inventory")
public class FieldOffice {
	
	@Id
	private Integer dataCenterInventoryId;
	
	//Status
	private Integer recordStatusId;
	private Integer recordValidityId;
	private Integer ownershipTypeId;
	private Integer dataCenterTierId;
	private Integer electricityIncludedInCostFlag;
	private Integer electricityMeteredFlag;
	private Integer automatedMonitoring;
	private Integer coreClassificationId;
	private Integer closingStageId;
	private Integer closingFiscalYearId;
	private Integer closingFiscalQuarterId;
	private Integer issPositionId;
	private Integer issProvider;
	
	//General Information
	private Integer dataCenterId;
	private Integer fiscalQuarterReportId;
	private Integer parentDataCenterInventoryId;
	private Integer agencyDataCenterId;
	private String dcoiDataCenterId;
	private String publishedName;
	private String streetAddress;
	private String streetAddress2;
	private String city;
	private String zipCode;
	private Integer countryId;
	private String dataCenterName;
	
	//Favility Information
	private Integer grossFloorArea;
	private Integer customerFloorAreaTotal;
	private Double annualCostSqFt;
	private String otherAgenciesServiced;
	private Integer powerCapacityTotal;
	private Double electricityUsageAvg;
	private Double itPowerCapacityTotal;
	private Double itElectricityUsageAvg;
	private Double costPerKwh;
	private Double fte;
	private Double fteCost;
	private Integer rackCount;
	
	//Server Information
	private Double serverUtilization;
	private Integer mainframeCount;
	private Integer windowsServerCount;
	private Integer hpcClusterCount;
	private Integer otherServerCount;
	private Integer virtualHostCount;
	private Integer virtualOSCount;
	private Double storageTotal;
	private Double storageUsed;
	public Integer getDataCenterInventoryId() {
		return dataCenterInventoryId;
	}
	public void setDataCenterInventoryId(Integer dataCenterInventoryId) {
		this.dataCenterInventoryId = dataCenterInventoryId;
	}
	public Integer getRecordStatusId() {
		return recordStatusId;
	}
	public void setRecordStatusId(Integer recordStatusId) {
		this.recordStatusId = recordStatusId;
	}
	public Integer getRecordValidityId() {
		return recordValidityId;
	}
	public void setRecordValidityId(Integer recordValidityId) {
		this.recordValidityId = recordValidityId;
	}
	public Integer getOwnershipTypeId() {
		return ownershipTypeId;
	}
	public void setOwnershipTypeId(Integer ownershipTypeId) {
		this.ownershipTypeId = ownershipTypeId;
	}
	public Integer getDataCenterTierId() {
		return dataCenterTierId;
	}
	public void setDataCenterTierId(Integer dataCenterTierId) {
		this.dataCenterTierId = dataCenterTierId;
	}
	public Integer getElectricityIncludedInCostFlag() {
		return electricityIncludedInCostFlag;
	}
	public void setElectricityIncludedInCostFlag(Integer electricityIncludedInCostFlag) {
		this.electricityIncludedInCostFlag = electricityIncludedInCostFlag;
	}
	public Integer getElectricityMeteredFlag() {
		return electricityMeteredFlag;
	}
	public void setElectricityMeteredFlag(Integer electricityMeteredFlag) {
		this.electricityMeteredFlag = electricityMeteredFlag;
	}
	public Integer getAutomatedMonitoring() {
		return automatedMonitoring;
	}
	public void setAutomatedMonitoring(Integer automatedMonitoring) {
		this.automatedMonitoring = automatedMonitoring;
	}
	public Integer getCoreClassificationId() {
		return coreClassificationId;
	}
	public void setCoreClassificationId(Integer coreClassificationId) {
		this.coreClassificationId = coreClassificationId;
	}
	public Integer getClosingStageId() {
		return closingStageId;
	}
	public void setClosingStageId(Integer closingStageId) {
		this.closingStageId = closingStageId;
	}
	public Integer getClosingFiscalYearId() {
		return closingFiscalYearId;
	}
	public void setClosingFiscalYearId(Integer closingFiscalYearId) {
		this.closingFiscalYearId = closingFiscalYearId;
	}
	public Integer getClosingFiscalQuarterId() {
		return closingFiscalQuarterId;
	}
	public void setClosingFiscalQuarterId(Integer closingFiscalQuarterId) {
		this.closingFiscalQuarterId = closingFiscalQuarterId;
	}
	public Integer getIssPositionId() {
		return issPositionId;
	}
	public void setIssPositionId(Integer issPositionId) {
		this.issPositionId = issPositionId;
	}
	public Integer getIssProvider() {
		return issProvider;
	}
	public void setIssProvider(Integer issProvider) {
		this.issProvider = issProvider;
	}
	public Integer getDataCenterId() {
		return dataCenterId;
	}
	public void setDataCenterId(Integer dataCenterId) {
		this.dataCenterId = dataCenterId;
	}
	public Integer getFiscalQuarterReportId() {
		return fiscalQuarterReportId;
	}
	public void setFiscalQuarterReportId(Integer fiscalQuarterReportId) {
		this.fiscalQuarterReportId = fiscalQuarterReportId;
	}
	public Integer getParentDataCenterInventoryId() {
		return parentDataCenterInventoryId;
	}
	public void setParentDataCenterInventoryId(Integer parentDataCenterInventoryId) {
		this.parentDataCenterInventoryId = parentDataCenterInventoryId;
	}
	public Integer getAgencyDataCenterId() {
		return agencyDataCenterId;
	}
	public void setAgencyDataCenterId(Integer agencyDataCenterId) {
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
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	public String getDataCenterName() {
		return dataCenterName;
	}
	public void setDataCenterName(String dataCenterName) {
		this.dataCenterName = dataCenterName;
	}
	public Integer getGrossFloorArea() {
		return grossFloorArea;
	}
	public void setGrossFloorArea(Integer grossFloorArea) {
		this.grossFloorArea = grossFloorArea;
	}
	public Integer getCustomerFloorAreaTotal() {
		return customerFloorAreaTotal;
	}
	public void setCustomerFloorAreaTotal(Integer customerFloorAreaTotal) {
		this.customerFloorAreaTotal = customerFloorAreaTotal;
	}
	public Double getAnnualCostSqFt() {
		return annualCostSqFt;
	}
	public void setAnnualCostSqFt(Double annualCostSqFt) {
		this.annualCostSqFt = annualCostSqFt;
	}
	public String getOtherAgenciesServiced() {
		return otherAgenciesServiced;
	}
	public void setOtherAgenciesServiced(String otherAgenciesServiced) {
		this.otherAgenciesServiced = otherAgenciesServiced;
	}
	public Integer getPowerCapacityTotal() {
		return powerCapacityTotal;
	}
	public void setPowerCapacityTotal(Integer powerCapacityTotal) {
		this.powerCapacityTotal = powerCapacityTotal;
	}
	public Double getElectricityUsageAvg() {
		return electricityUsageAvg;
	}
	public void setElectricityUsageAvg(Double electricityUsageAvg) {
		this.electricityUsageAvg = electricityUsageAvg;
	}
	public Double getItPowerCapacityTotal() {
		return itPowerCapacityTotal;
	}
	public void setItPowerCapacityTotal(Double itPowerCapacityTotal) {
		this.itPowerCapacityTotal = itPowerCapacityTotal;
	}
	public Double getItElectricityUsageAvg() {
		return itElectricityUsageAvg;
	}
	public void setItElectricityUsageAvg(Double itElectricityUsageAvg) {
		this.itElectricityUsageAvg = itElectricityUsageAvg;
	}
	public Double getCostPerKwh() {
		return costPerKwh;
	}
	public void setCostPerKwh(Double costPerKwh) {
		this.costPerKwh = costPerKwh;
	}
	public Double getFte() {
		return fte;
	}
	public void setFte(Double fte) {
		this.fte = fte;
	}
	public Double getFteCost() {
		return fteCost;
	}
	public void setFteCost(Double fteCost) {
		this.fteCost = fteCost;
	}
	public Integer getRackCount() {
		return rackCount;
	}
	public void setRackCount(Integer rackCount) {
		this.rackCount = rackCount;
	}
	public Double getServerUtilization() {
		return serverUtilization;
	}
	public void setServerUtilization(Double serverUtilization) {
		this.serverUtilization = serverUtilization;
	}
	public Integer getMainframeCount() {
		return mainframeCount;
	}
	public void setMainframeCount(Integer mainframeCount) {
		this.mainframeCount = mainframeCount;
	}
	public Integer getWindowsServerCount() {
		return windowsServerCount;
	}
	public void setWindowsServerCount(Integer windowsServerCount) {
		this.windowsServerCount = windowsServerCount;
	}
	public Integer getHpcClusterCount() {
		return hpcClusterCount;
	}
	public void setHpcClusterCount(Integer hpcClusterCount) {
		this.hpcClusterCount = hpcClusterCount;
	}
	public Integer getOtherServerCount() {
		return otherServerCount;
	}
	public void setOtherServerCount(Integer otherServerCount) {
		this.otherServerCount = otherServerCount;
	}
	public Integer getVirtualHostCount() {
		return virtualHostCount;
	}
	public void setVirtualHostCount(Integer virtualHostCount) {
		this.virtualHostCount = virtualHostCount;
	}
	public Integer getVirtualOSCount() {
		return virtualOSCount;
	}
	public void setVirtualOSCount(Integer virtualOSCount) {
		this.virtualOSCount = virtualOSCount;
	}
	public Double getStorageTotal() {
		return storageTotal;
	}
	public void setStorageTotal(Double storageTotal) {
		this.storageTotal = storageTotal;
	}
	public Double getStorageUsed() {
		return storageUsed;
	}
	public void setStorageUsed(Double storageUsed) {
		this.storageUsed = storageUsed;
	}
	

}
