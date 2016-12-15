package gov.gsa.dcoi.entity;

/**
 * POJO class to hold the read-only data created from the DB view that we will
 * need to display in the final export as well as the search results
 * 
 * @author sgonthier
 *
 */
public class DataCenterView {
	private Integer quarterReportId;
	private String dataCenterName;
	private String dcoiDataCenterId;
	private String streetAddress;
	private String streetAddress2;
	private String city;
	private String zipCode;
	private String stateName;
	private String countryName;
	private String agencyDataCenterName;
	private String publishedName;
	private String recordStatusName;
	private String recordValidityName;
	private String ownershipTypeName;
	private String dataCenterTierName;
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
	private Double costPerkWh;
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
	private String fiscalQuarter;
	private String issPositionName;
	private String issProvider;

	public Integer getQuarterReportId() {
		return quarterReportId;
	}

	public void setQuarterReportId(Integer quarterReportId) {
		this.quarterReportId = quarterReportId;
	}
	
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

	public String getAgencyDataCenterName() {
		return agencyDataCenterName;
	}

	public void setAgencyDataCenterName(String agencyDataCenterName) {
		this.agencyDataCenterName = agencyDataCenterName;;
	}
	
	public String getPublishedName() {
		return publishedName;
	}

	public void setPublishedName(String publishedName) {
		this.publishedName = publishedName;
	}

	public String getRecordStatusName() {
		return recordStatusName;
	}

	public void setRecordStatusName(String recordStatusName) {
		this.recordStatusName = recordStatusName;
	}

	public String getRecordValidityName() {
		return recordValidityName;
	}

	public void setRecordValidityName(String recordValidityName) {
		this.recordValidityName = recordValidityName;
	}

	public String getOwnershipTypeName() {
		return ownershipTypeName;
	}

	public void setOwnershipTypeName(String ownershipTypeName) {
		this.ownershipTypeName = ownershipTypeName;
	}

	public String getDataCenterTierName() {
		return dataCenterTierName;
	}

	public void setDataCenterTierName(String dataCenterTierName) {
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

	public Double getCostPerkWh() {
		return costPerkWh;
	}

	public void setCostPerkWh(Double costPerkWh) {
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

	public String getFiscalQuarter() {
		return fiscalQuarter;
	}

	public void setFiscalQuarter(String fiscalQuarter) {
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
	
	private Object getDisplayValue(Object object){
		return null;
	}

}
