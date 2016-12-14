package gov.gsa.dcoi.entity;

/**
 * POJO class to hold the read-only data created from the DB view that we will
 * need to display in the final export as well as the search results
 * 
 * @author sgonthier
 *
 */
public class DataCenterView {

	private String dataCenterName;
	private String dcoiDataCenterId;
	private String streetAddress;
	private String streetAddress2;
	private String city;
	private String zipCode;
	private String stateName;
	private String countryName;
	private String publishedName;
	private Integer recordStatusName;
	private Integer recordValidityName;
	private Integer ownershipTypeName;
	private Integer dataCenterTierName;
	private Integer grossFloorArea;
	private Integer totalCustomerFloorArea;
	private Double annualCostPerSqFt;
	private String otherAgenciesServiced;
	private Integer electricityIncludedInCost;
	private Integer electricityIsMetered;
	private Double totalPowerCapacity;
	private Double totalITPowerCapacity;
	private Double avgElectricityUsage;
	private Double avgITElectricityUsage;
	private Integer costPerkWh;
	private Integer automatedMonitoring;
	private Double serverUtilization;
	private Double fte;
	private Integer fteCost;
	private Integer rackCount;
	private Integer totalMainframes;
	private Integer totalWindowsServers;
	private Integer totalHPCClusterNodes;
	private Integer totalOtherServers;
	private Integer totalVirtualHosts;
	private Integer totalVirtualOS;
	private Double totalStorage;
	private Double usedStorage;
	private String coreClassificationName;
	private String closingStageName;
	private Integer fiscalYear;
	private Integer fiscalQuarter;
	private String issPositionName;
	private String issProvider;

	public String getDataCenterName() {
		return dataCenterName;
	}

	public void setDataCenterName(String dataCenterName) {
		this.dataCenterName = dataCenterName;
	}

	public String getDcoiDataCenterId() {
		return dcoiDataCenterId;
	}

	public void setDcoiDataCenterId(String dcoiDataCenterId) {
		this.dcoiDataCenterId = dcoiDataCenterId;
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

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getPublishedName() {
		return publishedName;
	}

	public void setPublishedName(String publishedName) {
		this.publishedName = publishedName;
	}

	public Integer getRecordStatusName() {
		return recordStatusName;
	}

	public void setRecordStatusName(Integer recordStatusName) {
		this.recordStatusName = recordStatusName;
	}

	public Integer getRecordValidityName() {
		return recordValidityName;
	}

	public void setRecordValidityName(Integer recordValidityName) {
		this.recordValidityName = recordValidityName;
	}

	public Integer getOwnershipTypeName() {
		return ownershipTypeName;
	}

	public void setOwnershipTypeName(Integer ownershipTypeName) {
		this.ownershipTypeName = ownershipTypeName;
	}

	public Integer getDataCenterTierName() {
		return dataCenterTierName;
	}

	public void setDataCenterTierName(Integer dataCenterTierName) {
		this.dataCenterTierName = dataCenterTierName;
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

	public Double getAnnualCostPerSqFt() {
		return annualCostPerSqFt;
	}

	public void setAnnualCostPerSqFt(Double annualCostPerSqFt) {
		this.annualCostPerSqFt = annualCostPerSqFt;
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

	public Integer getCostPerkWh() {
		return costPerkWh;
	}

	public void setCostPerkWh(Integer costPerkWh) {
		this.costPerkWh = costPerkWh;
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

	public String getCoreClassificationName() {
		return coreClassificationName;
	}

	public void setCoreClassificationName(String coreClassificationName) {
		this.coreClassificationName = coreClassificationName;
	}

	public String getClosingStageName() {
		return closingStageName;
	}

	public void setClosingStageName(String closingStageName) {
		this.closingStageName = closingStageName;
	}

	public Integer getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(Integer fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public Integer getFiscalQuarter() {
		return fiscalQuarter;
	}

	public void setFiscalQuarter(Integer fiscalQuarter) {
		this.fiscalQuarter = fiscalQuarter;
	}

	public String getIssPositionName() {
		return issPositionName;
	}

	public void setIssPositionName(String issPositionName) {
		this.issPositionName = issPositionName;
	}

	public String getIssProvider() {
		return issProvider;
	}

	public void setIssProvider(String issProvider) {
		this.issProvider = issProvider;
	}

}
