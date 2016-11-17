package gov.gsa.dcoi.dto;

import java.util.List;

public class DataCenterDto {
	
	//Possibly add this back in
	//private int dataCenterId;
	private String dataCenterName;
	private int dcoiDataCenterId;
	private String city;
	private int stateId;
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




}
