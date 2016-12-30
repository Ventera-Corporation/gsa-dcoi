package gov.gsa.dcoi.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * Dto that holds information regarding a field office's facility information.
 * 
 * @author sgonthier
 *
 */
public class FacilityInformationDto {

	private Integer dataCenterInventoryId;
	@Min(0)
	private String grossFloorArea;
	@Min(0)
	private String totalCustomerFloorArea;
	@DecimalMin("0.00")
	@DecimalMax("800000.00")
	private String annualCostPerSqFt;
	@Size(max = 2048)
	private String otherAgenciesServiced;
	@DecimalMin("0.01")
	private String totalPowerCapacity;
	@DecimalMin("0.01")
	private String avgElectricityUsage;
	@DecimalMin("0.00")
	private String totalITPowerCapacity;
	@DecimalMin("0.00")
	private String avgITElectricityUsage;
	@DecimalMin("0.00")
	private String fte;
	@Min(1)
	private String fteCost;
	@Min(0)
	private String rackCount;
	@DecimalMin("0.01")
	private String costPerkWh;

	// @AssertTrue
	// @JsonIgnore
	// public boolean isGrossFloorAreaRequired(){

	// }

	public Integer getDataCenterInventoryId() {
		return dataCenterInventoryId;
	}

	public void setDataCenterInventoryId(Integer dataCenterInventoryId) {
		this.dataCenterInventoryId = dataCenterInventoryId;
	}

	public String getGrossFloorArea() {
		return grossFloorArea;
	}

	public void setGrossFloorArea(String grossFloorArea) {
		this.grossFloorArea = grossFloorArea;
	}

	public String getAnnualCostPerSqFt() {
		return annualCostPerSqFt;
	}

	public void setAnnualCostPerSqFt(String annualCostPerSqFt) {
		this.annualCostPerSqFt = annualCostPerSqFt;
	}

	public String getOtherAgenciesServiced() {
		return otherAgenciesServiced;
	}

	public void setOtherAgenciesServiced(String otherAgenciesServiced) {
		this.otherAgenciesServiced = otherAgenciesServiced;
	}

	public String getFte() {
		return fte;
	}

	public void setFte(String fte) {
		this.fte = fte;
	}

	public String getFteCost() {
		return fteCost;
	}

	public void setFteCost(String fteCost) {
		this.fteCost = fteCost;
	}

	public String getRackCount() {
		return rackCount;
	}

	public void setRackCount(String rackCount) {
		this.rackCount = rackCount;
	}

	public String getTotalCustomerFloorArea() {
		return totalCustomerFloorArea;
	}

	public void setTotalCustomerFloorArea(String totalCustomerFloorArea) {
		this.totalCustomerFloorArea = totalCustomerFloorArea;
	}

	public String getAvgElectricityUsage() {
		return avgElectricityUsage;
	}

	public void setAvgElectricityUsage(String avgElectricityUsage) {
		this.avgElectricityUsage = avgElectricityUsage;
	}

	public String getAvgITElectricityUsage() {
		return avgITElectricityUsage;
	}

	public void setAvgITElectricityUsage(String avgITElectricityUsage) {
		this.avgITElectricityUsage = avgITElectricityUsage;
	}

	public String getTotalPowerCapacity() {
		return totalPowerCapacity;
	}

	public void setTotalPowerCapacity(String totalPowerCapacity) {
		this.totalPowerCapacity = totalPowerCapacity;
	}

	public String getTotalITPowerCapacity() {
		return totalITPowerCapacity;
	}

	public void setTotalITPowerCapacity(String totalITPowerCapacity) {
		this.totalITPowerCapacity = totalITPowerCapacity;
	}

	public String getCostPerkWh() {
		return costPerkWh;
	}

	public void setCostPerkWh(String costPerkWh) {
		this.costPerkWh = costPerkWh;
	}

}
