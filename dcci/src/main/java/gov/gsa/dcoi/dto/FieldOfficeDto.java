package gov.gsa.dcoi.dto;


/**
 * Dto that holds information regarding a field office.
 * One data center is made up of many field offices, and in turn
 * one field office is made up of 2 different "catagories" that 
 * represent specific information about that field office (ex: FacilityInformationDto
 * and ServerInformationDto)
 * @author sgonthier
 *
 */
public class FieldOfficeDto {

	private Integer fieldOfficeId;
	private String fieldOfficeName;
	private FacilityInformationDto facilityInfo;
	private ServerInformationDto serverInfo;

	// ADD GETTER FOR FIELD OFFICE NAME

	public Integer getFieldOfficeId() {
		return fieldOfficeId;
	}

	public void setFieldOfficeId(Integer fieldOfficeId) {
		this.fieldOfficeId = fieldOfficeId;
	}

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

}
