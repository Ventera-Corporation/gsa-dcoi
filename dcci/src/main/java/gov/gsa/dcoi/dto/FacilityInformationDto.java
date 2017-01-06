package gov.gsa.dcoi.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import gov.gsa.dcoi.validator.DcoiDecimalMax;
import gov.gsa.dcoi.validator.DcoiDecimalMin;
import gov.gsa.dcoi.validator.DcoiMin;

/**
 * Dto that holds information regarding a field office's facility information.
 * 
 * @author sgonthier
 *
 */
public class FacilityInformationDto {

	private Integer dataCenterInventoryId;
	@DcoiMin(0)
	private String grossFloorArea;
	@DcoiMin(0)
	private String totalCustomerFloorArea;
	@DcoiDecimalMin(0.00)
	@DcoiDecimalMax(800000.00)
	private String annualCostPerSqFt;
	@Size(max = 2048)
	private String otherAgenciesServiced;
	@DcoiDecimalMin(0.01)
	private String totalPowerCapacity;
	@DcoiDecimalMin(0.01)
	private String avgElectricityUsage;
	@DcoiDecimalMin(0.00)
	private String totalITPowerCapacity;
	@DcoiDecimalMin(0.01)
	private String avgITElectricityUsage;
	@DcoiDecimalMin(0.00)
	private String fte;
	@DcoiDecimalMin(0.01)
	@Pattern(regexp = "([0-9]*.[0-9]?[0-9]?|^$)")
	private String fteCost;
	@DcoiMin(0)
	private String rackCount;
	@DcoiDecimalMin(0.01)
	private String costPerkWh;

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
