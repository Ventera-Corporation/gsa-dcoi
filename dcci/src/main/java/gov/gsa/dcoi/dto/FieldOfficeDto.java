
package gov.gsa.dcoi.dto;

import javax.validation.Valid;

/**
 * Dto that holds information regarding a field office. One data center is made
 * up of many field offices, and in turn one field office is made up of 2
 * different "categories" that represent specific information about that field
 * office (ex: FacilityInformationDto and ServerInformationDto)
 * 
 * @author sgonthier
 *
 */
public class FieldOfficeDto {

	private Long dataCenterInventoryId;
	private Long dataCenterQuarterId;
	private Integer componentId;
	private String fieldOfficeName;

	@Valid
	private FacilityInformationDto facilityInfo;
	@Valid
	private ServerInformationDto serverInfo;

	// ADD GETTER FOR FIELD OFFICE NAME

	public FacilityInformationDto getFacilityInfo() {
		return facilityInfo;
	}

	public void setFacilityInfo(FacilityInformationDto facilityInfo) {
		this.facilityInfo = facilityInfo;
	}

	public ServerInformationDto getServerInfo() {
		return serverInfo;
	}

	public void setServerInfo(ServerInformationDto serverInfo) {
		this.serverInfo = serverInfo;
	}

	public String getFieldOfficeName() {
		return fieldOfficeName;
	}

	public void setFieldOfficeName(String fieldOfficeName) {
		this.fieldOfficeName = fieldOfficeName;
	}

	public Long getDataCenterInventoryId() {
		return dataCenterInventoryId;
	}

	public void setDataCenterInventoryId(Long dataCenterInventoryId) {
		this.dataCenterInventoryId = dataCenterInventoryId;
	}

	public Long getDataCenterQuarterId() {
		return dataCenterQuarterId;
	}

	public void setDataCenterQuarterId(Long dataCenterQuarterId) {
		this.dataCenterQuarterId = dataCenterQuarterId;
	}

	public Integer getComponentId() {
		return componentId;
	}

	public void setComponentId(Integer componentId) {
		this.componentId = componentId;
	}

}
