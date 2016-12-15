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
	private Integer dataCenterId;
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
	
	private static final String DISPLAY_TYPE_STRING = "String";
	private static final String DISPLAY_TYPE_INTEGER = "Integer";
	private static final String DISPLAY_TYPE_DOUBLE = "Double";

	public Integer getQuarterReportId() {
		return quarterReportId;
	}

	public void setQuarterReportId(Integer quarterReportId) {
		this.quarterReportId = quarterReportId;
	}
	
	public Integer getDataCenterId() {
		return dataCenterId;
	}

	public void setDataCenterId(Integer dataCenterId) {
		this.dataCenterId = dataCenterId;
	}

	public String getDataCenterName() {
		return (String)getDisplayValue(this.dataCenterName,DISPLAY_TYPE_STRING);
	}

	public void setDataCenterName(String dataCenterName) {
		this.dataCenterName = dataCenterName;
	}

	public String getDcoiDataCenterId() {
		return (String)getDisplayValue(this.dcoiDataCenterId,DISPLAY_TYPE_STRING);
	}

	public void setDcoiDataCenterId(String dcoiDataCenterId) {
		this.dcoiDataCenterId = dcoiDataCenterId;
	}

	public String getStreetAddress() {
		return (String)getDisplayValue(this.streetAddress,DISPLAY_TYPE_STRING);
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getStreetAddress2() {
		return (String)getDisplayValue(this.streetAddress2,DISPLAY_TYPE_STRING);
	}

	public void setStreetAddress2(String streetAddress2) {
		this.streetAddress2 = streetAddress2;
	}

	public String getCity() {
		return (String)getDisplayValue(this.city,DISPLAY_TYPE_STRING);
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return (String)getDisplayValue(this.zipCode,DISPLAY_TYPE_STRING);
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getStateName() {
		return (String)getDisplayValue(this.stateName,DISPLAY_TYPE_STRING);
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCountryName() {
		return (String)getDisplayValue(this.countryName,DISPLAY_TYPE_STRING);
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getAgencyDataCenterName() {
		return (String)getDisplayValue(this.agencyDataCenterName,DISPLAY_TYPE_STRING);
	}

	public void setAgencyDataCenterName(String agencyDataCenterName) {
		this.agencyDataCenterName = agencyDataCenterName;
	}
	
	public String getPublishedName() {
		return (String)getDisplayValue(this.publishedName,DISPLAY_TYPE_STRING);
	}

	public void setPublishedName(String publishedName) {
		this.publishedName = publishedName;
	}

	public String getRecordStatusName() {
		return (String)getDisplayValue(this.recordStatusName,DISPLAY_TYPE_STRING);
	}

	public void setRecordStatusName(String recordStatusName) {
		this.recordStatusName = recordStatusName;
	}

	public String getRecordValidityName() {
		return (String)getDisplayValue(this.recordValidityName,DISPLAY_TYPE_STRING);
	}

	public void setRecordValidityName(String recordValidityName) {
		this.recordValidityName = recordValidityName;
	}

	public String getOwnershipTypeName() {
		return (String)getDisplayValue(this.ownershipTypeName,DISPLAY_TYPE_STRING);
	}

	public void setOwnershipTypeName(String ownershipTypeName) {
		this.ownershipTypeName = ownershipTypeName;
	}

	public String getDataCenterTierName() {
		return (String)getDisplayValue(this.dataCenterTierName,DISPLAY_TYPE_STRING);
	}

	public void setDataCenterTierName(String dataCenterTierName) {
		this.dataCenterTierName = dataCenterTierName;
	}

	public Integer getGrossFloorArea() {
		return (Integer)getDisplayValue(this.grossFloorArea,DISPLAY_TYPE_INTEGER);
	}

	public void setGrossFloorArea(Integer grossFloorArea) {
		this.grossFloorArea = grossFloorArea;
	}

	public Integer getTotalCustomerFloorArea() {
		return (Integer)getDisplayValue(this.totalCustomerFloorArea,DISPLAY_TYPE_INTEGER);
	}

	public void setTotalCustomerFloorArea(Integer totalCustomerFloorArea) {
		this.totalCustomerFloorArea = totalCustomerFloorArea;
	}

	public Double getAnnualCostPerSqFt() {
		return (Double)getDisplayValue(this.annualCostPerSqFt,DISPLAY_TYPE_DOUBLE);
	}

	public void setAnnualCostPerSqFt(Double annualCostPerSqFt) {
		this.annualCostPerSqFt = annualCostPerSqFt;
	}

	public String getOtherAgenciesServiced() {
		return (String)getDisplayValue(this.otherAgenciesServiced,DISPLAY_TYPE_STRING);
	}

	public void setOtherAgenciesServiced(String otherAgenciesServiced) {
		this.otherAgenciesServiced = otherAgenciesServiced;
	}

	public Integer getElectricityIncludedInCost() {
		return (Integer)getDisplayValue(this.electricityIncludedInCost,DISPLAY_TYPE_INTEGER);
	}

	public void setElectricityIncludedInCost(Integer electricityIncludedInCost) {
		this.electricityIncludedInCost = electricityIncludedInCost;
	}

	public Integer getElectricityIsMetered() {
		return (Integer)getDisplayValue(this.electricityIsMetered,DISPLAY_TYPE_INTEGER);
	}

	public void setElectricityIsMetered(Integer electricityIsMetered) {
		this.electricityIsMetered = electricityIsMetered;
	}

	public Double getTotalPowerCapacity() {
		return (Double)getDisplayValue(this.totalPowerCapacity,DISPLAY_TYPE_DOUBLE);
	}

	public void setTotalPowerCapacity(Double totalPowerCapacity) {
		this.totalPowerCapacity = totalPowerCapacity;
	}

	public Double getTotalITPowerCapacity() {
		return (Double)getDisplayValue(this.totalITPowerCapacity,DISPLAY_TYPE_DOUBLE);
	}

	public void setTotalITPowerCapacity(Double totalITPowerCapacity) {
		this.totalITPowerCapacity = totalITPowerCapacity;
	}

	public Double getAvgElectricityUsage() {
		return (Double)getDisplayValue(this.avgElectricityUsage,DISPLAY_TYPE_DOUBLE);
	}

	public void setAvgElectricityUsage(Double avgElectricityUsage) {
		this.avgElectricityUsage = avgElectricityUsage;
	}

	public Double getAvgITElectricityUsage() {
		return (Double)getDisplayValue(this.avgITElectricityUsage,DISPLAY_TYPE_DOUBLE);
	}

	public void setAvgITElectricityUsage(Double avgITElectricityUsage) {
		this.avgITElectricityUsage = avgITElectricityUsage;
	}

	public Double getCostPerkWh() {
		return (Double)getDisplayValue(this.costPerkWh,DISPLAY_TYPE_DOUBLE);
	}

	public void setCostPerkWh(Double costPerkWh) {
		this.costPerkWh = costPerkWh;
	}

	public Integer getAutomatedMonitoring() {
		return (Integer)getDisplayValue(this.automatedMonitoring,DISPLAY_TYPE_INTEGER);
	}

	public void setAutomatedMonitoring(Integer automatedMonitoring) {
		this.automatedMonitoring = automatedMonitoring;
	}

	public Double getServerUtilization() {
		return (Double)getDisplayValue(this.serverUtilization,DISPLAY_TYPE_DOUBLE);
	}

	public void setServerUtilization(Double serverUtilization) {
		this.serverUtilization = serverUtilization;
	}

	public Double getFte() {
		return (Double)getDisplayValue(this.fte,DISPLAY_TYPE_DOUBLE);
	}

	public void setFte(Double fte) {
		this.fte = fte;
	}

	public Integer getFteCost() {
		return (Integer)getDisplayValue(this.fteCost,DISPLAY_TYPE_INTEGER);
	}

	public void setFteCost(Integer fteCost) {
		this.fteCost = fteCost;
	}

	public Integer getRackCount() {
		return (Integer)getDisplayValue(this.rackCount,DISPLAY_TYPE_INTEGER);
	}

	public void setRackCount(Integer rackCount) {
		this.rackCount = rackCount;
	}

	public Integer getTotalMainframes() {
		return (Integer)getDisplayValue(this.totalMainframes,DISPLAY_TYPE_INTEGER);
	}

	public void setTotalMainframes(Integer totalMainframes) {
		this.totalMainframes = totalMainframes;
	}

	public Integer getTotalWindowsServers() {
		return (Integer)getDisplayValue(this.totalWindowsServers,DISPLAY_TYPE_INTEGER);
	}

	public void setTotalWindowsServers(Integer totalWindowsServers) {
		this.totalWindowsServers = totalWindowsServers;
	}

	public Integer getTotalHPCClusterNodes() {
		return (Integer)getDisplayValue(this.totalHPCClusterNodes,DISPLAY_TYPE_INTEGER);
	}

	public void setTotalHPCClusterNodes(Integer totalHPCClusterNodes) {
		this.totalHPCClusterNodes = totalHPCClusterNodes;
	}

	public Integer getTotalOtherServers() {
		return (Integer)getDisplayValue(this.totalOtherServers,DISPLAY_TYPE_INTEGER);
	}

	public void setTotalOtherServers(Integer totalOtherServers) {
		this.totalOtherServers = totalOtherServers;
	}

	public Integer getTotalVirtualHosts() {
		return (Integer)getDisplayValue(this.totalVirtualHosts,DISPLAY_TYPE_INTEGER);
	}

	public void setTotalVirtualHosts(Integer totalVirtualHosts) {
		this.totalVirtualHosts = totalVirtualHosts;
	}

	public Integer getTotalVirtualOS() {
		return (Integer)getDisplayValue(this.totalVirtualOS,DISPLAY_TYPE_INTEGER);
	}

	public void setTotalVirtualOS(Integer totalVirtualOS) {
		this.totalVirtualOS = totalVirtualOS;
	}

	public Double getTotalStorage() {
		return (Double)getDisplayValue(this.totalStorage,DISPLAY_TYPE_DOUBLE);
	}

	public void setTotalStorage(Double totalStorage) {
		this.totalStorage = totalStorage;
	}

	public Double getUsedStorage() {
		return (Double)getDisplayValue(this.usedStorage,DISPLAY_TYPE_DOUBLE);
	}

	public void setUsedStorage(Double usedStorage) {
		this.usedStorage = usedStorage;
	}

	public String getCoreClassificationName() {
		return (String)getDisplayValue(this.coreClassificationName,DISPLAY_TYPE_STRING);
	}
	
	public void setCoreClassificationName(String coreClassificationName) {
		this.coreClassificationName = coreClassificationName;
	}

	public String getClosingStageName() {
		return (String)getDisplayValue(this.closingStageName,DISPLAY_TYPE_STRING);
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
		return (String)getDisplayValue(this.fiscalQuarter,DISPLAY_TYPE_STRING);
	}

	public void setFiscalQuarter(String fiscalQuarter) {
		this.fiscalQuarter = fiscalQuarter;
	}

	public String getIssPositionName() {
		return (String)getDisplayValue(this.issPositionName,DISPLAY_TYPE_STRING);
	}

	public void setIssPositionName(String issPositionName) {
		this.issPositionName = issPositionName;
	}

	public String getIssProvider() {
		return (String)getDisplayValue(this.issProvider,DISPLAY_TYPE_STRING);
	}

	public void setIssProvider(String issProvider) {
		this.issProvider = issProvider;
	}
	
	private Object getDisplayValue(Object object,String type){
		if(type.equals(DISPLAY_TYPE_STRING)){
			if(object==null || object.toString()==""){
				return "None";
			}
		}
		else if(type.equals(DISPLAY_TYPE_INTEGER)){
			if(object==null){
				return Integer.valueOf(0);
			}
		}
		else if(type.equals(DISPLAY_TYPE_DOUBLE)){
			if(object==null){
				return Double.valueOf(0);
			}
		}
		return object;
	}

}
