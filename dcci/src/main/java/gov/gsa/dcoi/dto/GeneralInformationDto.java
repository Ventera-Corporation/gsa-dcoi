package gov.gsa.dcoi.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import gov.gsa.dcoi.service.CommonHelper;

/**
 * Dto that holds information regarding the general information of the data
 * center. This information is held at the data center level
 * 
 * @author sgonthier
 *
 */
public class GeneralInformationDto {

	private Integer dataCenterInventoryId;
	private Integer dataCenterId;
	@Pattern(regexp = "DCOI-DC-[0-9]{5}")
	private String dcoiDataCenterId;
	private Integer parentDataCenterInventoryId;
	private String agencyDataCenterId;
	@NotNull
	private String agencyAbbreviation;
	@Size(max = 255)
	private String publishedName;
	@Size(max = 1024)
	private String streetAddress;
	@Size(max = 1024)
	private String streetAddress2;
	@Size(max = 100)
	private String city;
	private Integer stateId;
	private Integer zipCode;
	private Integer countryId;
	@Size(max = 255)
	private String dataCenterName;
	@NotNull
	@Size(max = 1024)
	private String component;
	private String country;
	private String stateName;

	public String getStateName() {
		if (this.stateId == null) {
			return null;
		}
		return CommonHelper.parseStateId(this.stateId);
	}

	public String getCountry() {
		if (this.countryId == null) {
			return "NONE";
		}
		return CommonHelper.parseCountryId(this.countryId);
	}

	// public String getComponent() {
	// if(this.componentId == null){
	// return "NONE";
	// }
	// return CommonHelper.parseComponentId(this.componentId);
	// }

	public Integer getDataCenterInventoryId() {
		return dataCenterInventoryId;
	}

	public void setDataCenterInventoryId(Integer dataCenterInventoryId) {
		this.dataCenterInventoryId = dataCenterInventoryId;
	}

	public Integer getDataCenterId() {
		return dataCenterId;
	}

	public void setDataCenterId(Integer dataCenterId) {
		this.dataCenterId = dataCenterId;
	}

	public Integer getParentDataCenterInventoryId() {
		return parentDataCenterInventoryId;
	}

	public void setParentDataCenterInventoryId(Integer parentDataCenterInventoryId) {
		this.parentDataCenterInventoryId = parentDataCenterInventoryId;
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

	public String getDataCenterName() {
		return dataCenterName;
	}

	public void setDataCenterName(String dataCenterName) {
		this.dataCenterName = dataCenterName;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getAgencyAbbreviation() {
		return agencyAbbreviation;
	}

	public void setAgencyAbbreviation(String agencyAbbreviation) {
		this.agencyAbbreviation = agencyAbbreviation;
	}

	public String getDcoiDataCenterId() {
		return dcoiDataCenterId;
	}

	public void setDcoiDataCenterId(String dcoiDataCenterId) {
		this.dcoiDataCenterId = dcoiDataCenterId;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getAgencyDataCenterId() {
		return agencyDataCenterId;
	}

	public void setAgencyDataCenterId(String agencyDataCenterId) {
		this.agencyDataCenterId = agencyDataCenterId;
	}

	public String getComponent() {
		return component;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

}
