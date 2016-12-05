package gov.gsa.dcoi.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DataCenter {

	@Id
	private Integer dataCenterId;
	private String dataCenterName;
	private Integer dcoiDataCenterId;
	private String address;
	private String address2;
	private String city;
	private Integer zipCode;
	private Integer stateId;
	private Integer regionId;
	private Integer coutnryId;

	public Integer getDataCenterId() {
		return dataCenterId;
	}

	public void setDataCenterId(Integer dataCenterId) {
		this.dataCenterId = dataCenterId;
	}

	public String getDataCenterName() {
		return dataCenterName;
	}

	public void setDataCenterName(String dataCenterName) {
		this.dataCenterName = dataCenterName;
	}

	public Integer getDcoiDataCenterId() {
		return dcoiDataCenterId;
	}

	public void setDcoiDataCenterId(Integer dcoiDataCenterId) {
		this.dcoiDataCenterId = dcoiDataCenterId;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getCoutnryId() {
		return coutnryId;
	}

	public void setCoutnryId(Integer coutnryId) {
		this.coutnryId = coutnryId;
	}

}
