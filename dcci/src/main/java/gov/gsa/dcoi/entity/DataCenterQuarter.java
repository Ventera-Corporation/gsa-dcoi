package gov.gsa.dcoi.entity;

import javax.persistence.Column;
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
	@Column(name = "customer_floor_area_total")
	private Integer totalCustomerFloorArea;
	@Column(name = "annual_cost_sq_ft")
	private Double annualCostPerSqFt;
	private String otherAgenciesServiced;
	@Column(name = "electricity_included_in_cost_flag")
	private Integer electricityIncludedInCost;
	@Column(name = "electricity_metered_flag")
	private Integer electricityIsMetered;
	@Column(name = "power_capacity_total")
	private Double totalPowerCapacity;
	@Column(name = "it_power_capacity_total")
	private Double totalITPowerCapacity;
	@Column(name = "electricity_usage_avg")
	private Double avgElectricityUsage;
	@Column(name = "it_electricity_usage_avg")
	private Double avgITElectricityUsage;
	@Column(name = "cost_per_kwh")
	private Integer costPerkWh;
	@Column(name = "automated_monitoring_flag")
	private Integer automatedMonitoring;
	private Double serverUtilization;
	private Double fte;
	private Integer fteCost;
	private Integer rackCount;
	@Column(name = "mainframe_count")
	private Integer totalMainframes;
	@Column(name = "windows_server_count")
	private Integer totalWindowsServers;
	@Column(name = "hpc_cluster_node_count")
	private Integer totalHPCClusterNodes;
	@Column(name = "other_server_count")
	private Integer totalOtherServers;
	@Column(name = "virtual_host_count")
	private Integer totalVirtualHosts;
	@Column(name = "virtual_os_count")
	private Integer totalVirtualOS;
	@Column(name = "storage_total")
	private Double totalStorage;
	@Column(name = "storage_used")
	private Double usedStorage;
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

	public Integer getTotalCustomerFloorArea() {
		return totalCustomerFloorArea;
	}

	public void setTotalCustomerFloorArea(Integer totalCustomerFloorArea) {
		this.totalCustomerFloorArea = totalCustomerFloorArea;
	}

	public String getOtherAgenciesServiced() {
		return otherAgenciesServiced;
	}

	public void setOtherAgenciesServiced(String otherAgenciesServiced) {
		this.otherAgenciesServiced = otherAgenciesServiced;
	}

	public Integer getElectricityIncludedInCost() {
		return electricityIncludedInCost;
	}

	public void setElectricityIncludedInCost(Integer electricityIncludedInCost) {
		this.electricityIncludedInCost = electricityIncludedInCost;
	}

	public Integer getElectricityIsMetered() {
		return electricityIsMetered;
	}

	public void setElectricityIsMetered(Integer electricityIsMetered) {
		this.electricityIsMetered = electricityIsMetered;
	}

	public Integer getAutomatedMonitoring() {
		return automatedMonitoring;
	}

	public void setAutomatedMonitoring(Integer automatedMonitoring) {
		this.automatedMonitoring = automatedMonitoring;
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

	public Double getAnnualCostPerSqFt() {
		return annualCostPerSqFt;
	}

	public void setAnnualCostPerSqFt(Double annualCostPerSqFt) {
		this.annualCostPerSqFt = annualCostPerSqFt;
	}

	public Double getAvgElectricityUsage() {
		return avgElectricityUsage;
	}

	public void setAvgElectricityUsage(Double avgElectricityUsage) {
		this.avgElectricityUsage = avgElectricityUsage;
	}

	public Double getAvgITElectricityUsage() {
		return avgITElectricityUsage;
	}

	public void setAvgITElectricityUsage(Double avgITElectricityUsage) {
		this.avgITElectricityUsage = avgITElectricityUsage;
	}

	public Double getTotalPowerCapacity() {
		return totalPowerCapacity;
	}

	public void setTotalPowerCapacity(Double totalPowerCapacity) {
		this.totalPowerCapacity = totalPowerCapacity;
	}

	public Double getTotalITPowerCapacity() {
		return totalITPowerCapacity;
	}

	public void setTotalITPowerCapacity(Double totalITPowerCapacity) {
		this.totalITPowerCapacity = totalITPowerCapacity;
	}

	public Integer getTotalMainframes() {
		return totalMainframes;
	}

	public void setTotalMainframes(Integer totalMainframes) {
		this.totalMainframes = totalMainframes;
	}

	public Integer getTotalWindowsServers() {
		return totalWindowsServers;
	}

	public void setTotalWindowsServers(Integer totalWindowsServers) {
		this.totalWindowsServers = totalWindowsServers;
	}

	public Integer getTotalHPCClusterNodes() {
		return totalHPCClusterNodes;
	}

	public void setTotalHPCClusterNodes(Integer totalHPCClusterNodes) {
		this.totalHPCClusterNodes = totalHPCClusterNodes;
	}

	public Integer getTotalOtherServers() {
		return totalOtherServers;
	}

	public void setTotalOtherServers(Integer totalOtherServers) {
		this.totalOtherServers = totalOtherServers;
	}

	public Integer getTotalVirtualHosts() {
		return totalVirtualHosts;
	}

	public void setTotalVirtualHosts(Integer totalVirtualHosts) {
		this.totalVirtualHosts = totalVirtualHosts;
	}

	public Integer getTotalVirtualOS() {
		return totalVirtualOS;
	}

	public void setTotalVirtualOS(Integer totalVirtualOS) {
		this.totalVirtualOS = totalVirtualOS;
	}

	public Double getTotalStorage() {
		return totalStorage;
	}

	public void setTotalStorage(Double totalStorage) {
		this.totalStorage = totalStorage;
	}

	public Double getUsedStorage() {
		return usedStorage;
	}

	public void setUsedStorage(Double usedStorage) {
		this.usedStorage = usedStorage;
	}

	public Integer getCostPerkWh() {
		return costPerkWh;
	}

	public void setCostPerkWh(Integer costPerkWh) {
		this.costPerkWh = costPerkWh;
	}

}
