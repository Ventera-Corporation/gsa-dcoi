package gov.gsa.dcoi.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import gov.gsa.dcoi.service.CommonHelper;

/**
 * Dto that holds information regarding the data center status. This information
 * is held at the data center level
 * 
 * @author sgonthier
 *
 */
public class StatusDto {

	private Integer dataCenterInventoryId;
	@NotNull
	private Integer recordStatusId;
	@NotNull
	private Integer recordValidityId;
	private Integer ownershipTypeId;
	private Integer dataCenterTierId;
	private Integer electricityIncludedInCost;
	private Integer electricityIsMeteredId;
	private Integer automatedMonitoringId;
	private Integer issPositionId;
	private Integer coreClassificationId;
	@NotNull
	private Integer closingStageId;
	private Integer closingFiscalYearId;
	private Integer closingFiscalQuarterId;
	@Pattern(regexp = "(DCOI-DC-([0-9]{5})|DCOI-DC-([0-9]{6})|^$)")
	private String issProvider;

	public String getSharedServicesPosition() {
		if (this.issPositionId == null) {
			return "NONE";
		}
		return CommonHelper.parseIssPositionId(this.issPositionId);
	}

	public String getRecordStatus() {
		if (this.recordStatusId == null) {
			return "NONE";
		}
		return CommonHelper.parseRecordStatusId(this.recordStatusId);
	}

	public String getRecordValidity() {
		if (this.recordValidityId == null) {
			return "NONE";
		}
		return CommonHelper.parseRecordValidityId(this.recordValidityId);
	}

	public String getOwnershipType() {
		if (this.ownershipTypeId == null) {
			return "NONE";
		}
		return CommonHelper.parseOwnershipTypeId(this.ownershipTypeId);
	}

	public String getDataCenterTier() {
		if (this.dataCenterTierId == null) {
			return "NONE";
		}
		return CommonHelper.parseDataCenterTierId(this.dataCenterTierId);
	}

	public String getClosingStage() {
		if (this.getClosingStageId() == null) {
			return "NONE";
		}
		return CommonHelper.parseClosingStageId(this.closingStageId);
	}

	public String getClosingFiscalYear() {
		if (this.closingFiscalYearId == null) {
			return "NONE";
		}
		return CommonHelper.parseFiscalYearId(this.closingFiscalYearId);
	}

	public String getClosingQuarter() {
		if (this.closingFiscalQuarterId == null) {
			return null;
		}
		return CommonHelper.parseFiscalQuarterId(this.closingFiscalQuarterId);
	}

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

	public Integer getElectricityIsMeteredId() {
		return electricityIsMeteredId;
	}

	public void setElectricityIsMeteredId(Integer electricityIsMeteredId) {
		if (electricityIsMeteredId == Integer.valueOf(1)) {
			this.electricityIsMeteredId = Integer.valueOf(0);
		} else if (electricityIsMeteredId == Integer.valueOf(2)) {
			this.electricityIsMeteredId = Integer.valueOf(1);
		} else {
			this.electricityIsMeteredId = electricityIsMeteredId;
		}
	}

	public Integer getAutomatedMonitoringId() {
		return automatedMonitoringId;
	}

	public void setAutomatedMonitoringId(Integer automatedMonitoringId) {
		this.automatedMonitoringId = automatedMonitoringId;
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

	public String getIssProvider() {
		return issProvider;
	}

	public void setIssProvider(String issProvider) {
		this.issProvider = issProvider;
	}

	public Integer getElectricityIncludedInCost() {
		return electricityIncludedInCost;
	}

	public void setElectricityIncludedInCost(Integer electricityIncludedInCost) {
		this.electricityIncludedInCost = electricityIncludedInCost;
	}

}
