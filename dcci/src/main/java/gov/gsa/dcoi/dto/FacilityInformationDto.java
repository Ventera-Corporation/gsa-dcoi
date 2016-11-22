package gov.gsa.dcoi.dto;

public class FacilityInformationDto {
	
	private int dataCenterInventoryId;
	private int grossFloorArea;
	private int customerFloorAreaTotal;
	private double annualCostSqFt;
	private String otherAgenciesServiced;
	private int powerCapacityTotal;
	private double electricityUsageAvg;
	private double itPowerCapacityTotal;
	private double itElectricityUsageAvg;
	private double costPerKwh;
	private double fte;
	private double fteCost;
	private int rackCount;
	
	public int getGrossFloorArea() {
		return grossFloorArea;
	}
	public void setGrossFloorArea(int grossFloorArea) {
		this.grossFloorArea = grossFloorArea;
	}
	public int getCustomerFloorAreaTotal() {
		return customerFloorAreaTotal;
	}
	public void setCustomerFloorAreaTotal(int customerFloorAreaTotal) {
		this.customerFloorAreaTotal = customerFloorAreaTotal;
	}
	public double getAnnualCostSqFt() {
		return annualCostSqFt;
	}
	public void setAnnualCostSqFt(double annualCostSqFt) {
		this.annualCostSqFt = annualCostSqFt;
	}
	public String getOtherAgenciesServiced() {
		return otherAgenciesServiced;
	}
	public void setOtherAgenciesServiced(String otherAgenciesServiced) {
		this.otherAgenciesServiced = otherAgenciesServiced;
	}
	public int getPowerCapacityTotal() {
		return powerCapacityTotal;
	}
	public void setPowerCapacityTotal(int powerCapacityTotal) {
		this.powerCapacityTotal = powerCapacityTotal;
	}
	public double getElectricityUsageAvg() {
		return electricityUsageAvg;
	}
	public void setElectricityUsageAvg(double electricityUsageAvg) {
		this.electricityUsageAvg = electricityUsageAvg;
	}
	public double getItPowerCapacityTotal() {
		return itPowerCapacityTotal;
	}
	public void setItPowerCapacityTotal(double itPowerCapacityTotal) {
		this.itPowerCapacityTotal = itPowerCapacityTotal;
	}
	public double getItElectricityUsageAvg() {
		return itElectricityUsageAvg;
	}
	public void setItElectricityUsageAvg(double itElectricityUsageAvg) {
		this.itElectricityUsageAvg = itElectricityUsageAvg;
	}
	public double getCostPerKwh() {
		return costPerKwh;
	}
	public void setCostPerKwh(double costPerKwh) {
		this.costPerKwh = costPerKwh;
	}
	public double getFte() {
		return fte;
	}
	public void setFte(double fte) {
		this.fte = fte;
	}
	public double getFteCost() {
		return fteCost;
	}
	public void setFteCost(double fteCost) {
		this.fteCost = fteCost;
	}
	public int getRackCount() {
		return rackCount;
	}
	public void setRackCount(int rackCount) {
		this.rackCount = rackCount;
	}
	public int getDataCenterInventoryId() {
		return dataCenterInventoryId;
	}
	public void setDataCenterInventoryId(int dataCenterInventoryId) {
		this.dataCenterInventoryId = dataCenterInventoryId;
	}
	


}
