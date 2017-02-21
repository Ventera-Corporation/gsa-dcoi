package gov.gsa.dcoi.dto;

/**
 * Dto to hold information the front-end display info about the other
 * calculations
 * 
 * @author mreed
 *
 */
public class OtherCalculationDto {

	private Double pue;
	private String tierClassification;
	private String floorAreaClassification;
	private Double wattsSqFt;
	private Integer physicalServerCount;
	private Integer osCount;
	private Double storageUtilization;
	private String closingTargetDate;

	public Double getPue() {
		return pue;
	}

	public void setPue(Double pue) {
		this.pue = pue;
	}

	public String getTierClassification() {
		return tierClassification;
	}

	public void setTierClassification(String tierClassification) {
		this.tierClassification = tierClassification;
	}

	public String getFloorAreaClassification() {
		return floorAreaClassification;
	}

	public void setFloorAreaClassification(String floorAreaClassification) {
		this.floorAreaClassification = floorAreaClassification;
	}

	public Double getStorageUtilization() {
		return storageUtilization;
	}

	public void setStorageUtilization(Double storageUtilization) {
		this.storageUtilization = storageUtilization;
	}

	public String getClosingTargetDate() {
		return closingTargetDate;
	}

	public void setClosingTargetDate(String closingTargetDate) {
		this.closingTargetDate = closingTargetDate;
	}

	public Double getWattsSqFt() {
		return wattsSqFt;
	}

	public void setWattsSqFt(Double wattsSqFt) {
		this.wattsSqFt = wattsSqFt;
	}

	public Integer getPhysicalServerCount() {
		return physicalServerCount;
	}

	public void setPhysicalServerCount(Integer physicalServerCount) {
		this.physicalServerCount = physicalServerCount;
	}

	public Integer getOsCount() {
		return osCount;
	}

	public void setOsCount(Integer osCount) {
		this.osCount = osCount;
	}
}
