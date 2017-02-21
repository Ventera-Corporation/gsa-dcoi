
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
	private ServerInformationDto serverInfo;
	private CostCalculationDto costCalc;
	private OtherCalculationDto otherCalc;

	// ADD GETTER FOR FIELD OFFICE NAME

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

	public CostCalculationDto getCostCalc() {
		return costCalc;
	}

	public void setCostCalc(CostCalculationDto costCalc) {
		this.costCalc = costCalc;
	}

	public OtherCalculationDto getOtherCalc() {
		return otherCalc;
	}

	public void setOtherCalc(OtherCalculationDto otherCalc) {
		this.otherCalc = otherCalc;
	}

}
