package gov.gsa.dcoi.dto;

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
	private Integer grossFloorArea;
	@Min(0)
	private Integer totalCustomerFloorArea;
	@DecimalMin("0.00")
	private Double annualCostPerSqFt;
	@Size(max = 2048)
	private String otherAgenciesServiced;
	@DecimalMin("0.00")
	private Integer totalPowerCapacity;
	@DecimalMin("0.00")
	private Double avgElectricityUsage;
	@DecimalMin("0.00")
	private Double totalITPowerCapacity;
	@DecimalMin("0.00")
	private Double avgITElectricityUsage;
	@DecimalMin("0.00")
	private Double fte;
	@Min(0)
	private Double fteCost;
	@Min(0)
	private Integer rackCount;
	@DecimalMin("0.00")
	private Double costPerkWh;

	public Integer getDataCenterInventoryId() {
		return dataCenterInventoryId;
	}

	public void setDataCenterInventoryId(Integer dataCenterInventoryId) {
		this.dataCenterInventoryId = dataCenterInventoryId;
	}

	public Integer getGrossFloorArea() {
		return grossFloorArea;
	}

	public void setGrossFloorArea(Integer grossFloorArea) {
		this.grossFloorArea = grossFloorArea;
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

	public Integer getTotalCustomerFloorArea() {
		return totalCustomerFloorArea;
	}

	public void setTotalCustomerFloorArea(Integer totalCustomerFloorArea) {
		this.totalCustomerFloorArea = totalCustomerFloorArea;
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

	public Integer getTotalPowerCapacity() {
		return totalPowerCapacity;
	}

	public void setTotalPowerCapacity(Integer totalPowerCapacity) {
		this.totalPowerCapacity = totalPowerCapacity;
	}

	public Double getTotalITPowerCapacity() {
		return totalITPowerCapacity;
	}

	public void setTotalITPowerCapacity(Double totalITPowerCapacity) {
		this.totalITPowerCapacity = totalITPowerCapacity;
	}

	public Double getCostPerkWh() {
		return costPerkWh;
	}

	public void setCostPerkWh(Double costPerkWh) {
		this.costPerkWh = costPerkWh;
	}

}
