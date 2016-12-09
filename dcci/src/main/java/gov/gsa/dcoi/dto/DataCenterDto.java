package gov.gsa.dcoi.dto;

import java.util.List;

/**
 * Overall dataCenterDto that holds information common to one entire dataCenter
 * including generalInfo and statusInfo as well as dataCenter name and location.
 * 
 * Also includes a list of the field offices which are part of that specific
 * data center
 * 
 * @author sgonthier
 *
 */
public class DataCenterDto {

	private String dataCenterName;
	private String dcoiDataCenterId;
	private Integer dataCenterId;
	private String address;
	private String address2;
	private String city;
	private String zipCode;
	private Integer stateId;
	private Integer regionId;
	private Integer countryId;
	private Integer totalNumDataCenters;
	private GeneralInformationDto generalInfo;
	private StatusDto statusInfo;
	private List<FieldOfficeDto> fieldOffices;

	// ADD GETTER FOR STATENAME

	public String getDataCenterName() {
		return dataCenterName;
	}

	public void setDataCenterName(String dataCenterName) {
		this.dataCenterName = dataCenterName;
	}

	public String getDcoiDataCenterId() {
		return dcoiDataCenterId;
	}

	public void setDcoiDataCenterId(String dcoiDataCenterId) {
		this.dcoiDataCenterId = dcoiDataCenterId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public List<FieldOfficeDto> getFieldOffices() {
		return fieldOffices;
	}

	public void setFieldOffices(List<FieldOfficeDto> fieldOffices) {
		this.fieldOffices = fieldOffices;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getDataCenterId() {
		return dataCenterId;
	}

	public void setDataCenterId(Integer dataCenterId) {
		this.dataCenterId = dataCenterId;
	}

	public GeneralInformationDto getGeneralInfo() {
		return generalInfo;
	}

	public void setGeneralInfo(GeneralInformationDto generalInfo) {
		this.generalInfo = generalInfo;
	}

	public StatusDto getStatusInfo() {
		return statusInfo;
	}

	public void setStatusInfo(StatusDto statusInfo) {
		this.statusInfo = statusInfo;
	}

	public Integer getTotalNumDataCenters() {
		return totalNumDataCenters;
	}

	public void setTotalNumDataCenters(Integer totalNumDataCenters) {
		this.totalNumDataCenters = totalNumDataCenters;
	}

}
