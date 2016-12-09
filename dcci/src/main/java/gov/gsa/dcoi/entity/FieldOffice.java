package gov.gsa.dcoi.entity;

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
	private Integer fieldOfficeId;
	private Integer parentDataCenterInventoryId;

	// Facility Information
	private Integer grossFloorArea;
	private Integer customerFloorAreaTotal;
	private Double annualCostSqFt;
	private Integer powerCapacityTotal;
	private Double electricityUsageAvg;
	private Double itPowerCapacityTotal;
	private Double itElectricityUsageAvg;
	private Double fte;
	private Double fteCost;
	private Integer rackCount;

	// Server Information
	private Double serverUtilization;
	private Integer mainframeCount;
	private Integer windowsServerCount;
	private Integer hpcClusterNodeCount;
	private Integer otherServerCount;
	private Integer virtualHostCount;
	private Integer virtualOsCount;
	private Double storageTotal;
	private Double storageUsed;

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

	public Integer getHpcClusterNodeCount() {
		return hpcClusterNodeCount;
	}

	public void setHpcClusterNodeCount(Integer hpcClusterNodeCount) {
		this.hpcClusterNodeCount = hpcClusterNodeCount;
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
		return virtualOsCount;
	}

	public void setVirtualOSCount(Integer virtualOSCount) {
		this.virtualOsCount = virtualOSCount;
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

	public Long getDataCenterQuarterId() {
		return dataCenterQuarterId;
	}

	public void setDataCenterQuarterId(Long dataCenterQuarterId) {
		this.dataCenterQuarterId = dataCenterQuarterId;
	}

	public Integer getFieldOfficeId() {
		return fieldOfficeId;
	}

	public void setFieldOfficeId(Integer fieldOfficeId) {
		this.fieldOfficeId = fieldOfficeId;
	}

}
