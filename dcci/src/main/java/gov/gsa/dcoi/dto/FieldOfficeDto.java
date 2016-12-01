package gov.gsa.dcoi.dto;

public class FieldOfficeDto {
	
	private Integer fieldOfficeId;
	private String fieldOfficeName;
	
	private GeneralInformationDto generalInformation;
	
	private StatusDto status;
	
	private FacilityInformationDto facilityInformation;
	
	private ServerInformationDto serverInformation;
	
	//ADD GETTER FOR FIELD OFFICE NAME
	
	
	public Integer getFieldOfficeId() {
		return fieldOfficeId;
	}
	public void setFieldOfficeId(Integer fieldOfficeId) {
		this.fieldOfficeId = fieldOfficeId;
	}
	public GeneralInformationDto getGeneralInformation() {
		return generalInformation;
	}
	public void setGeneralInformation(GeneralInformationDto generalInformation) {
		this.generalInformation = generalInformation;
	}
	public StatusDto getStatus() {
		return status;
	}
	public void setStatus(StatusDto status) {
		this.status = status;
	}
	public FacilityInformationDto getFacilityInformation() {
		return facilityInformation;
	}
	public void setFacilityInformation(FacilityInformationDto facilityInformation) {
		this.facilityInformation = facilityInformation;
	}
	public ServerInformationDto getServerInformation() {
		return serverInformation;
	}
	public void setServerInformation(ServerInformationDto serverInformation) {
		this.serverInformation = serverInformation;
	}
	public String getFieldOfficeName() {
		return fieldOfficeName;
	}
	public void setFieldOfficeName(String fieldOfficeName) {
		this.fieldOfficeName = fieldOfficeName;
	}


}
