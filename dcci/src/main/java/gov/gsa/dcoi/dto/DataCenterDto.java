package gov.gsa.dcoi.dto;

import java.util.List;

public class DataCenterDto {
	
	//Possibly add this back in
	//private int dataCenterId;
	private String dataCenterName;
	private int dcoiDataCenterId;
	private String address;
	private String address2;
	private String city;
	private String zipCode;
	private int stateId;
	private int regionId;
	private int country_id;
	private List<FieldOfficeDto> fieldOffices;
	
	//ADD GETTER FOR STATENAME

	public String getDataCenterName() {
		return dataCenterName;
	}

	public void setDataCenterName(String dataCenterName) {
		this.dataCenterName = dataCenterName;
	}

	public int getDcoiDataCenterId() {
		return dcoiDataCenterId;
	}

	public void setDcoiDataCenterId(int dcoiDataCenterId) {
		this.dcoiDataCenterId = dcoiDataCenterId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
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

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public int getCountry_id() {
		return country_id;
	}

	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}




}
