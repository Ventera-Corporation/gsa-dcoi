package gov.gsa.dcoi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entity class to hold the basic/static information about a data center,
 * including its address, region/location and name
 * 
 * @author sgonthier
 *
 */
@Entity
public class DataCenter {

	@Id
	private Integer dataCenterId;
	private String dataCenterName;
	private String dcoiDataCenterId;
	@Column(name = "address")
	private String streetAddress;
	@Column(name = "address2")
	private String streetAddress2;
	private String city;
	private Integer zipCode;
	private Integer stateId;
	private Integer regionId;
	private Integer countryId;

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

	public String getDcoiDataCenterId() {
		return dcoiDataCenterId;
	}

	public void setDcoiDataCenterId(String dcoiDataCenterId) {
		this.dcoiDataCenterId = dcoiDataCenterId;
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

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

}
