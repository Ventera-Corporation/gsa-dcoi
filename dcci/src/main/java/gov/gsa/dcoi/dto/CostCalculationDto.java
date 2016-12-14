package gov.gsa.dcoi.dto;

/**
 * Dto to hold information the front-end display info about the cost calculation
 * @author sgonthier
 *
 */
public class CostCalculationDto {
	
	private Double serverCost;
	private Double storageCost;
	private Double miscellaneous;
	private Double total;
	
	public Double getServerCost() {
		return serverCost;
	}
	public void setServerCost(Double serverCost) {
		this.serverCost = serverCost;
	}
	public Double getStorageCost() {
		return storageCost;
	}
	public void setStorageCost(Double storageCost) {
		this.storageCost = storageCost;
	}
	public Double getMiscellaneous() {
		return miscellaneous;
	}
	public void setMiscellaneous(Double miscellaneous) {
		this.miscellaneous = miscellaneous;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	

}
