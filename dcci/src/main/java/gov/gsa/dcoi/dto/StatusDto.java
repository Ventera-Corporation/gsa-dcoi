package gov.gsa.dcoi.dto;

/**
 * Dto that holds information regarding the data center status.
 * This information is held at the data center level
 * @author sgonthier
 *
 */
public class StatusDto {

	private Integer dataCenterInventoryId;
	private Integer recordStatusId;
	private Integer recordValidityId;
	private Integer ownershipTypeId;
	private Integer dataCenterTierId;
	private Integer electricityIncludedInCostFlag;
	private Integer electricityMeteredFlag;
	private Integer automatedMonitoring;
	private Integer coreClassificationId;
	private Integer closingStageId;
	private Integer closingFiscalYearId;
	private Integer closingFiscalQuarterId;
	private Integer issPositionId;
	private Integer issProvider;

	public Integer getDataCenterInventoryId() {
		return dataCenterInventoryId;
	}

	public void setDataCenterInventoryId(Integer dataCenterInventoryId) {
		this.dataCenterInventoryId = dataCenterInventoryId;
	}

	public Integer getRecordStatusId() {
		return recordStatusId;
	}

	public void setRecordStatusId(Integer recordStatusId) {
		this.recordStatusId = recordStatusId;
	}

	public Integer getRecordValidityId() {
		return recordValidityId;
	}

	public void setRecordValidityId(Integer recordValidityId) {
		this.recordValidityId = recordValidityId;
	}

	public Integer getOwnershipTypeId() {
		return ownershipTypeId;
	}

	public void setOwnershipTypeId(Integer ownershipTypeId) {
		this.ownershipTypeId = ownershipTypeId;
	}

	public Integer getDataCenterTierId() {
		return dataCenterTierId;
	}

	public void setDataCenterTierId(Integer dataCenterTierId) {
		this.dataCenterTierId = dataCenterTierId;
	}

	public Integer getElectricityIncludedInCostFlag() {
		return electricityIncludedInCostFlag;
	}

	public void setElectricityIncludedInCostFlag(Integer electricityIncludedInCostFlag) {
		this.electricityIncludedInCostFlag = electricityIncludedInCostFlag;
	}

	public Integer getElectricityMeteredFlag() {
		return electricityMeteredFlag;
	}

	public void setElectricityMeteredFlag(Integer electricityMeteredFlag) {
		this.electricityMeteredFlag = electricityMeteredFlag;
	}

	public Integer getAutomatedMonitoring() {
		return automatedMonitoring;
	}

	public void setAutomatedMonitoring(Integer automatedMonitoring) {
		this.automatedMonitoring = automatedMonitoring;
	}

	public Integer getCoreClassificationId() {
		return coreClassificationId;
	}

	public void setCoreClassificationId(Integer coreClassificationId) {
		this.coreClassificationId = coreClassificationId;
	}

	public Integer getClosingStageId() {
		return closingStageId;
	}

	public void setClosingStageId(Integer closingStageId) {
		this.closingStageId = closingStageId;
	}

	public Integer getClosingFiscalYearId() {
		return closingFiscalYearId;
	}

	public void setClosingFiscalYearId(Integer closingFiscalYearId) {
		this.closingFiscalYearId = closingFiscalYearId;
	}

	public Integer getClosingFiscalQuarterId() {
		return closingFiscalQuarterId;
	}

	public void setClosingFiscalQuarterId(Integer closingFiscalQuarterId) {
		this.closingFiscalQuarterId = closingFiscalQuarterId;
	}

	public Integer getIssPositionId() {
		return issPositionId;
	}

	public void setIssPositionId(Integer issPositionId) {
		this.issPositionId = issPositionId;
	}

	public Integer getIssProvider() {
		return issProvider;
	}

	public void setIssProvider(Integer issProvider) {
		this.issProvider = issProvider;
	}

}
