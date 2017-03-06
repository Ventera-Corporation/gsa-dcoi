package gov.gsa.dcoi.entity;

/**
 * POJO class to hold the read-only data created from the DB view that we will
 * need to display in the final export as well as the search results
 * 
 * @author sgonthier
 *
 */
public class DataCenterView {
	private String quarterReportId;
	private String dataCenterId;
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
	private String grossFloorArea;
	private String totalCustomerFloorArea;
	private String annualCostPerSqFt;
	private String otherAgenciesServiced;
	private String electricityIncludedInCost;
	private String electricityIsMetered;
	private String totalPowerCapacity;
	private String totalITPowerCapacity;
	private String avgElectricityUsage;
	private String avgITElectricityUsage;
	private String costPerkWh;
	private String automatedMonitoring;
	private String serverUtilization;
	private String fte;
	private String fteCost;
	private String rackCount;
	private String totalMainframes;
	private String totalWindowsServers;
	private String totalHPCClusterNodes;
	private String totalOtherServers;
	private String totalVirtualHosts;
	private String totalVirtualOS;
	private String totalStorage;
	private String usedStorage;
	private String coreClassificationName;
	private String closingStageName;
	private String fiscalYear;
	private String fiscalQuarter;
	private String issPositionName;
	private String issProvider;
	private String fieldOffices;

	// Cost model
	private String serverAmount;
	private String storageAmount;
	private String otherAmount;
	private String totalAmount;
	private String savingsAmount;
	private String avoidanceYear1;
	private String avoidanceAmountYear1;
	private String avoidanceYear2;
	private String avoidanceAmountYear2;
	private String avoidanceYear3;
	private String avoidanceAmountYear3;

	private static final String DISPLAY_TYPE_STRING = "String";

	public String getQuarterReportId() {
		return quarterReportId;
	}

	public void setQuarterReportId(String quarterReportId) {
		this.quarterReportId = quarterReportId;
	}

	public String getDataCenterId() {
		return dataCenterId;
	}

	public void setDataCenterId(String dataCenterId) {
		this.dataCenterId = dataCenterId;
	}

	public String getDataCenterName() {
		return (String) getDisplayValue(this.dataCenterName, DISPLAY_TYPE_STRING);
	}

	public void setDataCenterName(String dataCenterName) {
		this.dataCenterName = dataCenterName;
	}

	public String getDcoiDataCenterId() {
		return (String) getDisplayValue(this.dcoiDataCenterId, DISPLAY_TYPE_STRING);
	}

	public void setDcoiDataCenterId(String dcoiDataCenterId) {
		this.dcoiDataCenterId = dcoiDataCenterId;
	}

	public String getStreetAddress() {
		return (String) getDisplayValue(this.streetAddress, DISPLAY_TYPE_STRING);
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getStreetAddress2() {
		return (String) getDisplayValue(this.streetAddress2, DISPLAY_TYPE_STRING);
	}

	public void setStreetAddress2(String streetAddress2) {
		this.streetAddress2 = streetAddress2;
	}

	public String getCity() {
		return (String) getDisplayValue(this.city, DISPLAY_TYPE_STRING);
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return (String) getDisplayValue(this.zipCode, DISPLAY_TYPE_STRING);
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getStateName() {
		return (String) getDisplayValue(this.stateName, DISPLAY_TYPE_STRING);
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCountryName() {
		return (String) getDisplayValue(this.countryName, DISPLAY_TYPE_STRING);
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getAgencyDataCenterName() {
		return (String) getDisplayValue(this.agencyDataCenterName, DISPLAY_TYPE_STRING);
	}

	public void setAgencyDataCenterName(String agencyDataCenterName) {
		this.agencyDataCenterName = agencyDataCenterName;
	}

	public String getPublishedName() {
		return (String) getDisplayValue(this.publishedName, DISPLAY_TYPE_STRING);
	}

	public void setPublishedName(String publishedName) {
		this.publishedName = publishedName;
	}

	public String getRecordStatusName() {
		return (String) getDisplayValue(this.recordStatusName, DISPLAY_TYPE_STRING);
	}

	public void setRecordStatusName(String recordStatusName) {
		this.recordStatusName = recordStatusName;
	}

	public String getRecordValidityName() {
		return (String) getDisplayValue(this.recordValidityName, DISPLAY_TYPE_STRING);
	}

	public void setRecordValidityName(String recordValidityName) {
		this.recordValidityName = recordValidityName;
	}

	public String getOwnershipTypeName() {
		return (String) getDisplayValue(this.ownershipTypeName, DISPLAY_TYPE_STRING);
	}

	public void setOwnershipTypeName(String ownershipTypeName) {
		this.ownershipTypeName = ownershipTypeName;
	}

	public String getDataCenterTierName() {
		return (String) getDisplayValue(this.dataCenterTierName, DISPLAY_TYPE_STRING);
	}

	public void setDataCenterTierName(String dataCenterTierName) {
		this.dataCenterTierName = dataCenterTierName;
	}

	public String getGrossFloorArea() {
		return (String) getDisplayValue(this.grossFloorArea, DISPLAY_TYPE_STRING);
	}

	public void setGrossFloorArea(String grossFloorArea) {
		this.grossFloorArea = grossFloorArea;
	}

	public String getTotalCustomerFloorArea() {
		return (String) getDisplayValue(this.totalCustomerFloorArea, DISPLAY_TYPE_STRING);
	}

	public void setTotalCustomerFloorArea(String totalCustomerFloorArea) {
		this.totalCustomerFloorArea = totalCustomerFloorArea;
	}

	public String getAnnualCostPerSqFt() {
		return (String) getDisplayValue(this.annualCostPerSqFt, DISPLAY_TYPE_STRING);
	}

	public void setAnnualCostPerSqFt(String annualCostPerSqFt) {
		this.annualCostPerSqFt = annualCostPerSqFt;
	}

	public String getOtherAgenciesServiced() {
		return (String) getDisplayValue(this.otherAgenciesServiced, DISPLAY_TYPE_STRING);
	}

	public void setOtherAgenciesServiced(String otherAgenciesServiced) {
		this.otherAgenciesServiced = otherAgenciesServiced;
	}

	public String getElectricityIncludedInCost() {
		return (String) getDisplayValue(this.electricityIncludedInCost, DISPLAY_TYPE_STRING);
	}

	public void setElectricityIncludedInCost(String electricityIncludedInCost) {
		this.electricityIncludedInCost = electricityIncludedInCost;
	}

	public String getElectricityIsMetered() {
		return (String) getDisplayValue(this.electricityIsMetered, DISPLAY_TYPE_STRING);
	}

	public void setElectricityIsMetered(String electricityIsMetered) {
		this.electricityIsMetered = electricityIsMetered;
	}

	public String getTotalPowerCapacity() {
		return (String) getDisplayValue(this.totalPowerCapacity, DISPLAY_TYPE_STRING);
	}

	public void setTotalPowerCapacity(String totalPowerCapacity) {
		this.totalPowerCapacity = totalPowerCapacity;
	}

	public String getTotalITPowerCapacity() {
		return (String) getDisplayValue(this.totalITPowerCapacity, DISPLAY_TYPE_STRING);
	}

	public void setTotalITPowerCapacity(String totalITPowerCapacity) {
		this.totalITPowerCapacity = totalITPowerCapacity;
	}

	public String getAvgElectricityUsage() {
		return (String) getDisplayValue(this.avgElectricityUsage, DISPLAY_TYPE_STRING);
	}

	public void setAvgElectricityUsage(String avgElectricityUsage) {
		this.avgElectricityUsage = avgElectricityUsage;
	}

	public String getAvgITElectricityUsage() {
		return (String) getDisplayValue(this.avgITElectricityUsage, DISPLAY_TYPE_STRING);
	}

	public void setAvgITElectricityUsage(String avgITElectricityUsage) {
		this.avgITElectricityUsage = avgITElectricityUsage;
	}

	public String getCostPerkWh() {
		return (String) getDisplayValue(this.costPerkWh, DISPLAY_TYPE_STRING);
	}

	public void setCostPerkWh(String costPerkWh) {
		this.costPerkWh = costPerkWh;
	}

	public String getAutomatedMonitoring() {
		return (String) getDisplayValue(this.automatedMonitoring, DISPLAY_TYPE_STRING);
	}

	public void setAutomatedMonitoring(String automatedMonitoring) {
		this.automatedMonitoring = automatedMonitoring;
	}

	public String getServerUtilization() {
		return (String) getDisplayValue(this.serverUtilization, DISPLAY_TYPE_STRING);
	}

	public void setServerUtilization(String serverUtilization) {
		this.serverUtilization = serverUtilization;
	}

	public String getFte() {
		return (String) getDisplayValue(this.fte, DISPLAY_TYPE_STRING);
	}

	public void setFte(String fte) {
		this.fte = fte;
	}

	public String getFteCost() {
		return (String) getDisplayValue(this.fteCost, DISPLAY_TYPE_STRING);
	}

	public void setFteCost(String fteCost) {
		this.fteCost = fteCost;
	}

	public String getRackCount() {
		return (String) getDisplayValue(this.rackCount, DISPLAY_TYPE_STRING);
	}

	public void setRackCount(String rackCount) {
		this.rackCount = rackCount;
	}

	public String getTotalMainframes() {
		return (String) getDisplayValue(this.totalMainframes, DISPLAY_TYPE_STRING);
	}

	public void setTotalMainframes(String totalMainframes) {
		this.totalMainframes = totalMainframes;
	}

	public String getTotalWindowsServers() {
		return (String) getDisplayValue(this.totalWindowsServers, DISPLAY_TYPE_STRING);
	}

	public void setTotalWindowsServers(String totalWindowsServers) {
		this.totalWindowsServers = totalWindowsServers;
	}

	public String getTotalHPCClusterNodes() {
		return (String) getDisplayValue(this.totalHPCClusterNodes, DISPLAY_TYPE_STRING);
	}

	public void setTotalHPCClusterNodes(String totalHPCClusterNodes) {
		this.totalHPCClusterNodes = totalHPCClusterNodes;
	}

	public String getTotalOtherServers() {
		return (String) getDisplayValue(this.totalOtherServers, DISPLAY_TYPE_STRING);
	}

	public void setTotalOtherServers(String totalOtherServers) {
		this.totalOtherServers = totalOtherServers;
	}

	public String getTotalVirtualHosts() {
		return (String) getDisplayValue(this.totalVirtualHosts, DISPLAY_TYPE_STRING);
	}

	public void setTotalVirtualHosts(String totalVirtualHosts) {
		this.totalVirtualHosts = totalVirtualHosts;
	}

	public String getTotalVirtualOS() {
		return (String) getDisplayValue(this.totalVirtualOS, DISPLAY_TYPE_STRING);
	}

	public void setTotalVirtualOS(String totalVirtualOS) {
		this.totalVirtualOS = totalVirtualOS;
	}

	public String getTotalStorage() {
		return (String) getDisplayValue(this.totalStorage, DISPLAY_TYPE_STRING);
	}

	public void setTotalStorage(String totalStorage) {
		this.totalStorage = totalStorage;
	}

	public String getUsedStorage() {
		return (String) getDisplayValue(this.usedStorage, DISPLAY_TYPE_STRING);
	}

	public void setUsedStorage(String usedStorage) {
		this.usedStorage = usedStorage;
	}

	public String getCoreClassificationName() {
		return (String) getDisplayValue(this.coreClassificationName, DISPLAY_TYPE_STRING);
	}

	public void setCoreClassificationName(String coreClassificationName) {
		this.coreClassificationName = coreClassificationName;
	}

	public String getClosingStageName() {
		return (String) getDisplayValue(this.closingStageName, DISPLAY_TYPE_STRING);
	}

	public void setClosingStageName(String closingStageName) {
		this.closingStageName = closingStageName;
	}

	public String getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public String getFiscalQuarter() {
		return (String) getDisplayValue(this.fiscalQuarter, DISPLAY_TYPE_STRING);
	}

	public void setFiscalQuarter(String fiscalQuarter) {
		this.fiscalQuarter = fiscalQuarter;
	}

	public String getIssPositionName() {
		return (String) getDisplayValue(this.issPositionName, DISPLAY_TYPE_STRING);
	}

	public void setIssPositionName(String issPositionName) {
		this.issPositionName = issPositionName;
	}

	public String getIssProvider() {
		return (String) getDisplayValue(this.issProvider, DISPLAY_TYPE_STRING);
	}

	public void setIssProvider(String issProvider) {
		this.issProvider = issProvider;
	}

	private Object getDisplayValue(Object object, String type) {
		if (type.equals(DISPLAY_TYPE_STRING)) {
			if (object == null || object.toString() == "") {
				return "";
			}
		}
		return object;
	}

	public String getServerAmount() {
		return serverAmount;
	}

	public void setServerAmount(String serverAmount) {
		this.serverAmount = serverAmount;
	}

	public String getStorageAmount() {
		return storageAmount;
	}

	public void setStorageAmount(String storageAmount) {
		this.storageAmount = storageAmount;
	}

	public String getOtherAmount() {
		return otherAmount;
	}

	public void setOtherAmount(String otherAmount) {
		this.otherAmount = otherAmount;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getSavingsAmount() {
		return savingsAmount;
	}

	public void setSavingsAmount(String savingsAmount) {
		this.savingsAmount = savingsAmount;
	}

	public String getAvoidanceYear1() {
		return avoidanceYear1;
	}

	public void setAvoidanceYear1(String avoidanceYear1) {
		this.avoidanceYear1 = avoidanceYear1;
	}

	public String getAvoidanceAmountYear1() {
		return avoidanceAmountYear1;
	}

	public void setAvoidanceAmountYear1(String avoidanceAmountYear1) {
		this.avoidanceAmountYear1 = avoidanceAmountYear1;
	}

	public String getAvoidanceYear2() {
		return avoidanceYear2;
	}

	public void setAvoidanceYear2(String avoidanceYear2) {
		this.avoidanceYear2 = avoidanceYear2;
	}

	public String getAvoidanceAmountYear2() {
		return avoidanceAmountYear2;
	}

	public void setAvoidanceAmountYear2(String avoidanceAmountYear2) {
		this.avoidanceAmountYear2 = avoidanceAmountYear2;
	}

	public String getAvoidanceYear3() {
		return avoidanceYear3;
	}

	public void setAvoidanceYear3(String avoidanceYear3) {
		this.avoidanceYear3 = avoidanceYear3;
	}

	public String getAvoidanceAmountYear3() {
		return avoidanceAmountYear3;
	}

	public void setAvoidanceAmountYear3(String avoidanceAmountYear3) {
		this.avoidanceAmountYear3 = avoidanceAmountYear3;
	}

	public String getFieldOffices() {
		return (String) getDisplayValue(this.fieldOffices, DISPLAY_TYPE_STRING);
	}

	public void setFieldOffices(String fieldOffices) {
		this.fieldOffices = fieldOffices;
	}

}
