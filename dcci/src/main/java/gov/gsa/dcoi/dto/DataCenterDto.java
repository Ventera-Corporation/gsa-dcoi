package gov.gsa.dcoi.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import gov.gsa.dcoi.service.CommonHelper;

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

	@Size(max = 255)
	private String dataCenterName;
	@Pattern(regexp = "DCOI-DC-[0-9]{5}")
	private String dcoiDataCenterId;
	private Integer dataCenterId;
	private String city;
	private Integer stateId;
	private Integer regionId;
	private Integer totalNumDataCenters;
	private Integer adminCompleteFlag;
	private Integer ssoCompleteFlag;
	@Valid
	private GeneralInformationDto generalInfo;
	@Valid
	private StatusDto status;
	@Valid
	private List<FieldOfficeDto> fieldOffices;
	private String stateName;

	// ADD GETTER FOR STATENAME

	public String getStateName() {
		if (this.stateId == null) {
			return stateName;
		}
		return CommonHelper.parseStateId(this.stateId);
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

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
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

	public StatusDto getStatus() {
		return status;
	}

	public void setStatus(StatusDto status) {
		this.status = status;
	}

	public Integer getTotalNumDataCenters() {
		return totalNumDataCenters;
	}

	public void setTotalNumDataCenters(Integer totalNumDataCenters) {
		this.totalNumDataCenters = totalNumDataCenters;
	}

	public Integer getAdminCompleteFlag() {
		return adminCompleteFlag;
	}

	public void setAdminCompleteFlag(Integer adminCompleteFlag) {
		this.adminCompleteFlag = adminCompleteFlag;
	}

	public Integer getSsoCompleteFlag() {
		return ssoCompleteFlag;
	}

	public void setSsoCompleteFlag(Integer ssoCompleteFlag) {
		this.ssoCompleteFlag = ssoCompleteFlag;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

}
