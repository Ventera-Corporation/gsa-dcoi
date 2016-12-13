package gov.gsa.dcoi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Field Office Entity object to hold facility and server information for each
 * field office within a data center
 * 
 * @author sgonthier
 *
 */
@Entity
@Table(name = "data_center_inventory")
public class FieldOffice {

	@Id
	private Integer dataCenterInventoryId;

	private Long dataCenterQuarterId;
	@Column(name = "field_office_id")
	private Integer componentId;
	private Integer parentDataCenterInventoryId;

	// Facility Information
	private Integer grossFloorArea;
	@Column(name = "customer_floor_area_total")
	private Integer totalCustomerFloorArea;
	@Column(name = "annual_cost_sq_ft")
	private Double annualCostPerSqFt;
	@Column(name = "power_capacity_total")
	private Integer totalPowerCapacity;
	@Column(name = "electricity_usage_avg")
	private Double avgElectricityUsage;
	@Column(name = "it_power_capacity_total")
	private Double totalITPowerCapacity;
	@Column(name = "it_electricity_usage_avg")
	private Double avgITElectricityUsage;
	private Double fte;
	private Double fteCost;
	private Integer rackCount;
	// @Column(name = "cost_per_kwh")
	// private Integer costPerkWh;

	// Server Information
	private Double serverUtilization;
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

	public Integer getDataCenterInventoryId() {
		return dataCenterInventoryId;
	}

	public void setDataCenterInventoryId(Integer dataCenterInventoryId) {
		this.dataCenterInventoryId = dataCenterInventoryId;
	}

	public Integer getParentDataCenterInventoryId() {
		return parentDataCenterInventoryId;
	}

	public void setParentDataCenterInventoryId(Integer parentDataCenterInventoryId) {
		this.parentDataCenterInventoryId = parentDataCenterInventoryId;
	}

	public Integer getGrossFloorArea() {
		return grossFloorArea;
	}

	public void setGrossFloorArea(Integer grossFloorArea) {
		this.grossFloorArea = grossFloorArea;
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

	public Long getDataCenterQuarterId() {
		return dataCenterQuarterId;
	}

	public void setDataCenterQuarterId(Long dataCenterQuarterId) {
		this.dataCenterQuarterId = dataCenterQuarterId;
	}

	public Integer getComponentId() {
		return componentId;
	}

	public void setComponentId(Integer componentId) {
		this.componentId = componentId;
	}

	/**
	 * public Integer getCostPerkWh() { return costPerkWh; }
	 * 
	 * public void setCostPerkWh(Integer costPerkWh) { this.costPerkWh =
	 * costPerkWh; }
	 **/

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

	public Integer getTotalPowerCapacity() {
		return totalPowerCapacity;
	}

	public void setTotalPowerCapacity(Integer totalPowerCapacity) {
		this.totalPowerCapacity = totalPowerCapacity;
	}

	public Double getAvgElectricityUsage() {
		return avgElectricityUsage;
	}

	public void setAvgElectricityUsage(Double avgElectricityUsage) {
		this.avgElectricityUsage = avgElectricityUsage;
	}

	public Double getTotalITPowerCapacity() {
		return totalITPowerCapacity;
	}

	public void setTotalITPowerCapacity(Double totalITPowerCapacity) {
		this.totalITPowerCapacity = totalITPowerCapacity;
	}

	public Double getAvgITElectricityUsage() {
		return avgITElectricityUsage;
	}

	public void setAvgITElectricityUsage(Double avgITElectricityUsage) {
		this.avgITElectricityUsage = avgITElectricityUsage;
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

}
