package gov.gsa.dcoi.dto;

public class GeneralInformationDto {
	
	
	private int dataCenterInventoryId;
	private int dataCenterId;
	private int fiscalQuarterReportId;
	private int parentDataCenterInventoryId;
	private String dcoiDataCenterId;
	private String publishedName;
	private String streetAddress;
	private String streetAddress2;
	private String city;
	private String zipCode;
	private int countryId;
	private String dataCenterName;
	
	
	public int getDataCenterId() {
		return dataCenterId;
	}
	public void setDataCenterId(int dataCenterId) {
		this.dataCenterId = dataCenterId;
	}
	public int getFiscalQuarterReportId() {
		return fiscalQuarterReportId;
	}
	public void setFiscalQuarterReportId(int fiscalQuarterReportId) {
		this.fiscalQuarterReportId = fiscalQuarterReportId;
	}
	public int getParentDataCenterInventoryId() {
		return parentDataCenterInventoryId;
	}
	public void setParentDataCenterInventoryId(int parentDataCenterInventoryId) {
		this.parentDataCenterInventoryId = parentDataCenterInventoryId;
	}
	
	public String getDcoiDataCenterId() {
		return dcoiDataCenterId;
	}
	public void setDcoiDataCenterId(String dcoiDataCenterId) {
		this.dcoiDataCenterId = dcoiDataCenterId;
	}
	public String getPublishedName() {
		return publishedName;
	}
	public void setPublishedName(String publishedName) {
		this.publishedName = publishedName;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getStreetAddress2() {
		return streetAddress2;
	}
	public void setStreetAddress2(String streetAddress2) {
		this.streetAddress2 = streetAddress2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getDataCenterName() {
		return dataCenterName;
	}
	public void setDataCenterName(String dataCenterName) {
		this.dataCenterName = dataCenterName;
	}
	public int getDataCenterInventoryId() {
		return dataCenterInventoryId;
	}
	public void setDataCenterInventoryId(int dataCenterInventoryId) {
		this.dataCenterInventoryId = dataCenterInventoryId;
	}
	
	public String getCategoryName(){
		return this.getClass().getSimpleName();
	}

}
