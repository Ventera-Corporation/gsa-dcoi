package gov.gsa.dcoi.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entity class for the data Center quarter table - this is the MAIN class for a
 * data center for a quarter
 * 
 * @author sgonthier
 *
 */
@Entity
public class DataCenterQuarter {

	@Id
	private Long dataCenterQuarterId;
	private Long quarterReportId;
	private Integer dataCenterId;
	private Integer parentDataCenterQuarterId;
	private String agencyDataCenterId;
	private String publishedName;
	private Integer recordStatusId;
	private Integer recordValidityId;
	private Integer ownershipTypeId;
	private Integer dataCenterTierId;
	private Integer grossFloorArea;
	private Integer customerFloorAreaTotal;
	private Double annualCostSqFt;
	private String otherAgenciesServiced;
	private Integer electricityIncludedInCostFlag;
	private Integer electricityMeteredFlag;
	private Double powerCapacityTotal;
	private Double itPowerCapacityTotal;
	private Double electricityUsageAvg;
	private Double itElectricityUsageAvg;
	private Integer automatedMonitoringFlag;
	private Double serverUtilization;
	private Double fte;
	private Integer fteCost;
	private Integer rackCount;
	private Integer mainframeCount;
	private Integer windowsServerCount;
	private Integer otherServerCount;
	private Integer hpcClusterNodeCount;
	private Integer virtualHostCount;
	private Integer virtualOsCount;
	private Double storageTotal;
	private Double storageUsed;
	private Integer coreClassificationId;
	private Integer closingStageId;
	private Integer closingFiscalYearId;
	private Integer closingFiscalQuarterId;
	private String closingTargetDate;
	private Integer issPositionId;
	private String issProvider;
	private String tierClassification;
	private String floorAreaClassification;
	private Double wattsSqFt;
	private Double pue;
	private Integer physicalServerCount;
	private Integer osCount;
	private Double storageUtilization;
	private Integer adminCompleteFlag;
	private Integer ssoCompleteFlag;

	public Long getDataCenterQuarterId() {
		return dataCenterQuarterId;
	}

	public void setDataCenterQuarterId(Long dataCenterQuarterId) {
		this.dataCenterQuarterId = dataCenterQuarterId;
	}

	public Long getQuarterReportId() {
		return quarterReportId;
	}

	public void setQuarterReportId(Long quarterReportId) {
		this.quarterReportId = quarterReportId;
	}

	public Integer getDataCenterId() {
		return dataCenterId;
	}

	public void setDataCenterId(Integer dataCenterId) {
		this.dataCenterId = dataCenterId;
	}

	public Integer getParentDataCenterQuarterId() {
		return parentDataCenterQuarterId;
	}

	public void setParentDataCenterQuarterId(Integer parentDataCenterQuarterId) {
		this.parentDataCenterQuarterId = parentDataCenterQuarterId;
	}

	public String getAgencyDataCenterId() {
		return agencyDataCenterId;
	}

	public void setAgencyDataCenterId(String agencyDataCenterId) {
		this.agencyDataCenterId = agencyDataCenterId;
	}

	public String getPublishedName() {
		return publishedName;
	}

	public void setPublishedName(String publishedName) {
		this.publishedName = publishedName;
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

	public Double getPowerCapacityTotal() {
		return powerCapacityTotal;
	}

	public void setPowerCapacityTotal(Double powerCapacityTotal) {
		this.powerCapacityTotal = powerCapacityTotal;
	}

	public Double getItPowerCapacityTotal() {
		return itPowerCapacityTotal;
	}

	public void setItPowerCapacityTotal(Double itPowerCapacityTotal) {
		this.itPowerCapacityTotal = itPowerCapacityTotal;
	}

	public Double getElectricityUsageAvg() {
		return electricityUsageAvg;
	}

	public void setElectricityUsageAvg(Double electricityUsageAvg) {
		this.electricityUsageAvg = electricityUsageAvg;
	}

	public Double getItElectricityUsageAvg() {
		return itElectricityUsageAvg;
	}

	public void setItElectricityUsageAvg(Double itElectricityUsageAvg) {
		this.itElectricityUsageAvg = itElectricityUsageAvg;
	}

	public Integer getAutomatedMonitoringFlag() {
		return automatedMonitoringFlag;
	}

	public void setAutomatedMonitoringFlag(Integer automatedMonitoringFlag) {
		this.automatedMonitoringFlag = automatedMonitoringFlag;
	}

	public Double getServerUtilization() {
		return serverUtilization;
	}

	public void setServerUtilization(Double serverUtilization) {
		this.serverUtilization = serverUtilization;
	}

	public Double getFte() {
		return fte;
	}

	public void setFte(Double fte) {
		this.fte = fte;
	}

	public Integer getFteCost() {
		return fteCost;
	}

	public void setFteCost(Integer fteCost) {
		this.fteCost = fteCost;
	}

	public Integer getRackCount() {
		return rackCount;
	}

	public void setRackCount(Integer rackCount) {
		this.rackCount = rackCount;
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

	public Integer getOtherServerCount() {
		return otherServerCount;
	}

	public void setOtherServerCount(Integer otherServerCount) {
		this.otherServerCount = otherServerCount;
	}

	public Integer getHpcClusterNodeCount() {
		return hpcClusterNodeCount;
	}

	public void setHpcClusterNodeCount(Integer hpcClusterNodeCount) {
		this.hpcClusterNodeCount = hpcClusterNodeCount;
	}

	public Integer getVirtualHostCount() {
		return virtualHostCount;
	}

	public void setVirtualHostCount(Integer virtualHostCount) {
		this.virtualHostCount = virtualHostCount;
	}

	public Integer getVirtualOsCount() {
		return virtualOsCount;
	}

	public void setVirtualOsCount(Integer virtualOsCount) {
		this.virtualOsCount = virtualOsCount;
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

	public String getClosingTargetDate() {
		return closingTargetDate;
	}

	public void setClosingTargetDate(String closingTargetDate) {
		this.closingTargetDate = closingTargetDate;
	}

	public Integer getIssPositionId() {
		return issPositionId;
	}

	public void setIssPositionId(Integer issPositionId) {
		this.issPositionId = issPositionId;
	}

	public String getIssProvider() {
		return issProvider;
	}

	public void setIssProvider(String issProvider) {
		this.issProvider = issProvider;
	}

	public String getTierClassification() {
		return tierClassification;
	}

	public void setTierClassification(String tierClassification) {
		this.tierClassification = tierClassification;
	}

	public String getFloorAreaClassification() {
		return floorAreaClassification;
	}

	public void setFloorAreaClassification(String floorAreaClassification) {
		this.floorAreaClassification = floorAreaClassification;
	}

	public Double getWattsSqFt() {
		return wattsSqFt;
	}

	public void setWattsSqFt(Double wattsSqFt) {
		this.wattsSqFt = wattsSqFt;
	}

	public Double getPue() {
		return pue;
	}

	public void setPue(Double pue) {
		this.pue = pue;
	}

	public Integer getPhysicalServerCount() {
		return physicalServerCount;
	}

	public void setPhysicalServerCount(Integer physicalServerCount) {
		this.physicalServerCount = physicalServerCount;
	}

	public Integer getOsCount() {
		return osCount;
	}

	public void setOsCount(Integer osCount) {
		this.osCount = osCount;
	}

	public Double getStorageUtilization() {
		return storageUtilization;
	}

	public void setStorageUtilization(Double storageUtilization) {
		this.storageUtilization = storageUtilization;
	}

	public Integer getAdminCompleteFlag() {
		return adminCompleteFlag;
	}

	public void setAdminCompleteFlag(Integer adminCompleteFlag) {
		this.adminCompleteFlag = adminCompleteFlag;
	}

	public Integer getSsoCompleteFlag() {
		return ssoCompleteFlag;
	}

	public void setSsoCompleteFlag(Integer ssoCompleteFlag) {
		this.ssoCompleteFlag = ssoCompleteFlag;
	}

}
