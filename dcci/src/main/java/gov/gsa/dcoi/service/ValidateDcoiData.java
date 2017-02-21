package gov.gsa.dcoi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import gov.gsa.dcoi.DcoiRestMessage;
import gov.gsa.dcoi.dto.DataCenterDto;
import gov.gsa.dcoi.dto.FacilityInformationDto;
import gov.gsa.dcoi.dto.FieldOfficeDto;
import gov.gsa.dcoi.dto.StatusDto;
import gov.gsa.dcoi.entity.DataCenterQuarter;
import gov.gsa.dcoi.refValueEntity.ReferenceValueConstants;
import gov.gsa.dcoi.repository.DataCenterQuarterRepository;
import gov.gsa.dcoi.security.User;

/**
 * Service class to perform the business logic validation rules on the data
 * 
 * @author sgonthier
 *
 */
@Component
public class ValidateDcoiData {

	private static final String MESSAGE_LIST = "messageList";
	public static final String ERROR_ALERT = "error.alert";

	@Autowired
	MessageSource messageSource;
	@Autowired
	DataCenterQuarterRepository dataCenterQuarterRepository;

	/**
	 * Add validations for the business logic rules
	 * 
	 * @param dataCenterDtos
	 * @param user
	 * @return
	 */
	public Map<String, Object> validateDataCenters(List<DataCenterDto> dataCenterDtos, User user) {

		Map<String, String> messages = new HashMap<>();
		Map<String, Object> errorData = new HashMap<>();
		for (DataCenterDto dataCenterDto : dataCenterDtos) {
			if (user.getRoleIds().contains(ReferenceValueConstants.ADMIN_ROLE)) {
				validateFacilityInformation(dataCenterDto, messages);
				validateServerSection(dataCenterDto, messages);
			} else {
				if (user.getRoleIds().contains(ReferenceValueConstants.FACILITY_ROLE)) {
					validateFacilityInformation(dataCenterDto, messages);
				}
				if (user.getRoleIds().contains(ReferenceValueConstants.SERVER_ROLE)) {
					for (FieldOfficeDto fieldOfficeDto : dataCenterDto.getFieldOffices()) {
						if (user.getFieldOfficeIds().contains(fieldOfficeDto.getComponentId())) {
							validateServerSection(dataCenterDto, messages);
						}
					}
				}
			}

		}
		errorData.put("messages", messages);
		if (!messages.isEmpty()) {
			DcoiRestMessage message = new DcoiRestMessage(ERROR_ALERT,
					messageSource.getMessage(ERROR_ALERT, null, null));
			List<DcoiRestMessage> messageList = new ArrayList<>();
			messageList.add(message);
			errorData.put(MESSAGE_LIST, messageList);
			errorData.put("error", Boolean.valueOf("true"));
		}
		return errorData;

	}

	/**
	 * Helper method to validate the information in the facility, general
	 * information and status sections of the data form
	 * 
	 * @param dataCenterDto
	 * @param messages
	 */
	private void validateFacilityInformation(DataCenterDto dataCenterDto, Map<String, String> messages) {
		Boolean isNotInvalid = dataCenterDto.getStatus()
				.getRecordValidityId() != ReferenceValueConstants.INVALID_FACILITY;
		Boolean isAgencyOwned = dataCenterDto.getStatus().getOwnershipTypeId() == ReferenceValueConstants.AGENCY_OWNED;
		Boolean isElectricityMeteredAndTiered = dataCenterDto.getStatus()
				.getElectricityIsMeteredId() == ReferenceValueConstants.YES && dataCenterIsTiered(dataCenterDto);

		if (isNotInvalid && isAgencyOwned) {
			fillMessagesIfFieldIsNull(dataCenterDto.getFacilityInfo().getGrossFloorArea(), "grossFloorAreaRequired",
					messages);

			fillMessagesIfFieldIsNull(dataCenterDto.getFacilityInfo().getFte(), "fteRequired", messages);
			fillMessagesIfFieldIsNull(dataCenterDto.getFacilityInfo().getRackCount(), "rackCountRequired", messages);

		}

		if (isNotInvalid && isAgencyOwned && isElectricityMeteredAndTiered) {
			fillMessagesIfFieldIsNull(dataCenterDto.getFacilityInfo().getAvgElectricityUsage(),
					"avgElectrictyUsageRequired", messages);
			fillMessagesIfFieldIsNull(dataCenterDto.getFacilityInfo().getAvgITElectricityUsage(),
					"avgITElectrictyUsageRequired", messages);

		}

		validateFacilitySection(dataCenterDto, messages);
		validateGeneralInfoSection(dataCenterDto, messages);
		validateStatusSection(dataCenterDto, messages);
	}

	/**
	 * Helper method to validate the information in the server section of the
	 * data form
	 * 
	 * @param dataCenterDto
	 * @param messages
	 */
	private void validateServerSection(DataCenterDto dataCenterDto, Map<String, String> messages) {

		Boolean isNotInvalid = dataCenterDto.getStatus()
				.getRecordValidityId() != ReferenceValueConstants.INVALID_FACILITY;
		Boolean isAgencyOwned = dataCenterDto.getStatus().getOwnershipTypeId() == ReferenceValueConstants.AGENCY_OWNED;
		validateStorageFields(dataCenterDto, messages);

		if (dataCenterDto.getStatus().getAutomatedMonitoringId() == ReferenceValueConstants.YES
				&& dataCenterDto.getFieldOffices() != null) {
			for (FieldOfficeDto fieldOfficeDto : dataCenterDto.getFieldOffices()) {
				if (fieldOfficeDto.getServerInfo() != null
						&& (fieldOfficeDto.getServerInfo().getServerUtilization() == null
								|| fieldOfficeDto.getServerInfo().getServerUtilization().isEmpty())) {
					messages.put("serverUtilizationRequired",
							messageSource.getMessage("serverUtilizationRequired", null, null));
				}
			}

		}
		if (isNotInvalid && isAgencyOwned && dataCenterDto.getFieldOffices() != null) {
			for (FieldOfficeDto fieldOfficeDto : dataCenterDto.getFieldOffices()) {
				if (fieldOfficeDto.getServerInfo() != null) {
					fillMessagesIfFieldIsNull(fieldOfficeDto.getServerInfo().getTotalMainframes(),
							"totalMainframesRequired", messages);
					fillMessagesIfFieldIsNull(fieldOfficeDto.getServerInfo().getTotalWindowsServers(),
							"totalWindowsServersRequired", messages);
					fillMessagesIfFieldIsNull(fieldOfficeDto.getServerInfo().getTotalHPCClusterNodes(),
							"totalHPCClusterNodesRequired", messages);
					fillMessagesIfFieldIsNull(fieldOfficeDto.getServerInfo().getTotalOtherServers(),
							"totalOtherServersRequired", messages);
					fillMessagesIfFieldIsNull(fieldOfficeDto.getServerInfo().getTotalVirtualHosts(),
							"totalVirtualHostsRequired", messages);
					fillMessagesIfFieldIsNull(fieldOfficeDto.getServerInfo().getTotalVirtualOS(),
							"totalVirtualOSRequired", messages);
				}
			}
		}

	}

	/**
	 * Helper method to validate the facility information
	 * 
	 * @param dataCenterDto
	 * @param messages
	 */
	public void validateFacilitySection(DataCenterDto dataCenterDto, Map<String, String> messages) {
		FacilityInformationDto facilityInfo = dataCenterDto.getFacilityInfo();
		StatusDto statusDto = dataCenterDto.getStatus();
		if (isFieldOneLTEFieldTwo(facilityInfo.getAvgElectricityUsage(), facilityInfo.getAvgITElectricityUsage())) {
			messages.put("avgItElectricityIncorrect",
					messageSource.getMessage("avgItElectricityIncorrect", null, null));
		}
		if (isFieldOneLTEFieldTwo(facilityInfo.getTotalPowerCapacity(), facilityInfo.getTotalITPowerCapacity())) {
			messages.put("totalItPowerCapacityIncorrect",
					messageSource.getMessage("totalItPowerCapacityIncorrect", null, null));
		}
		if ((statusDto.getOwnershipTypeId() != null
				&& statusDto.getOwnershipTypeId().equals(ReferenceValueConstants.CLOUD_PROVIDER_OWNERSHIP_TYPE))
				|| statusDto.getRecordValidityId().equals(ReferenceValueConstants.INVALID_FACILITY)) {
			if (facilityInfo.getGrossFloorArea() != null && !facilityInfo.getGrossFloorArea().isEmpty()) {
				Integer tmpGFA = Integer.valueOf(facilityInfo.getGrossFloorArea());
				if (tmpGFA == 0) {
					messages.put("grossFloorAreaMustNotBeZero",
							messageSource.getMessage("grossFloorAreaMustNotBeZero", null, null));
				}
			}
		}
	}

	private void validateGeneralInfoSection(DataCenterDto dataCenterDto, Map<String, String> messages) {
		if (dataCenterDto.getStatus().getRecordStatusId().equals(ReferenceValueConstants.EXISTING_FACILITY)
				&& (dataCenterDto.getGeneralInfo().getDcoiDataCenterId() == null
						|| dataCenterDto.getGeneralInfo().getDcoiDataCenterId().isEmpty())) {
			messages.put("dataCenterIdRequired", messageSource.getMessage("dataCenterIdRequired", null, null));
		}
	}

	/**
	 * Helper method to validate some of the fields within the status
	 * information section
	 * 
	 * @param dataCenterDto
	 * @param messages
	 */
	private void validateStatusSection(DataCenterDto dataCenterDto, Map<String, String> messages) {
		StatusDto statusDto = dataCenterDto.getStatus();

		Boolean isNotInvalid = dataCenterDto.getStatus()
				.getRecordValidityId() != ReferenceValueConstants.INVALID_FACILITY;
		Boolean isAgencyOwned = dataCenterDto.getStatus().getOwnershipTypeId() == ReferenceValueConstants.AGENCY_OWNED;

		if (isNotInvalid) {
			fillMessagesIfFieldIsNull(dataCenterDto.getStatus().getOwnershipTypeId(), "ownershipTypeRequired",
					messages);
			fillMessagesIfFieldIsNull(dataCenterDto.getStatus().getDataCenterTierId(), "dataCenterTierRequired",
					messages);
		}
		if (isNotInvalid && isAgencyOwned) {
			fillMessagesIfFieldIsNull(dataCenterDto.getStatus().getElectricityIsMeteredId(),
					"electricityMeteredRequired", messages);
			fillMessagesIfFieldIsNull(dataCenterDto.getStatus().getAutomatedMonitoringId(),
					"automatedMonitoringRequired", messages);
		}

		if (statusDto.getDataCenterTierId() != null
				&& statusDto.getDataCenterTierId().equals(ReferenceValueConstants.DC_TIER_CLOUD_PROVIDER)) {
			if (statusDto.getOwnershipTypeId() != null
					&& !statusDto.getOwnershipTypeId().equals(ReferenceValueConstants.CLOUD_PROVIDER_OWNERSHIP_TYPE)) {
				messages.put("ownershipTypeMustBeCloud",
						messageSource.getMessage("ownershipTypeMustBeCloud", null, null));
			}
		}

		if (statusDto.getOwnershipTypeId() != null
				&& statusDto.getOwnershipTypeId().equals(ReferenceValueConstants.CLOUD_PROVIDER_OWNERSHIP_TYPE)) {
			if (statusDto.getDataCenterTierId() != null
					&& !statusDto.getDataCenterTierId().equals(ReferenceValueConstants.DC_TIER_CLOUD_PROVIDER)) {
				messages.put("dataCenterTierMustBeCloud",
						messageSource.getMessage("dataCenterTierMustBeCloud", null, null));
			}
		}

		validateClosingFields(dataCenterDto, messages);
		validateRecordValidityFields(dataCenterDto, messages);
	}

	/**
	 * Helper method to validate the storage information in the server info
	 * section
	 * 
	 * @param dataCenterDto
	 * @param messages
	 */
	public void validateStorageFields(DataCenterDto dataCenterDto, Map<String, String> messages) {
		if (dataCenterDto.getFieldOffices() != null) {
			for (FieldOfficeDto fieldOfficeDto : dataCenterDto.getFieldOffices()) {
				if (fieldOfficeDto.getServerInfo() != null) {
					if (isFieldOneLTEFieldTwo(fieldOfficeDto.getServerInfo().getTotalStorage(),
							fieldOfficeDto.getServerInfo().getUsedStorage())) {
						messages.put("usedStorageIncorrect",
								messageSource.getMessage("usedStorageIncorrect", null, null));
					}
				}
			}
		}
	}

	/**
	 * Private helper method to add validation messages for the closing
	 * information
	 * 
	 * @param dataCenterDto
	 * @param messages
	 */
	private void validateClosingFields(DataCenterDto dataCenterDto, Map<String, String> messages) {
		if (!(dataCenterDto.getStatus().getClosingStageId() == ReferenceValueConstants.NOT_CLOSING
				|| dataCenterDto.getStatus().getClosingStageId() == ReferenceValueConstants.CONSIDERING)) {
			fillMessagesIfFieldIsNull(dataCenterDto.getStatus().getClosingFiscalYearId(), "closingFiscalYearRequired",
					messages);
			fillMessagesIfFieldIsNull(dataCenterDto.getStatus().getClosingFiscalQuarterId(),
					"closingFiscalQuarterRequired", messages);
		}
		if (dataCenterDto.getStatus().getClosingFiscalYearId() != null) {
			fillMessagesIfFieldIsNull(dataCenterDto.getStatus().getClosingFiscalQuarterId(),
					"closingFiscalQuarterRequired2", messages);
		}
		if (dataCenterDto.getStatus().getClosingFiscalQuarterId() != null) {
			fillMessagesIfFieldIsNull(dataCenterDto.getStatus().getClosingFiscalYearId(), "closingFiscalYearRequired2",
					messages);
		}

	}

	/**
	 * Private helper method to add validation messages for record validity
	 * 
	 * @param dataCenterDto
	 * @param messages
	 */
	private void validateRecordValidityFields(DataCenterDto dataCenterDto, Map<String, String> messages) {
		List<DataCenterQuarter> pastQuarterDataCenterList = dataCenterQuarterRepository
				.findByDataCenterId(dataCenterDto.getDataCenterId());

		if (pastQuarterDataCenterList.size() > 1) {
			DataCenterQuarter pastQuarterDataCenter = pastQuarterDataCenterList
					.get(pastQuarterDataCenterList.size() - 2);
			if (pastQuarterDataCenter.getRecordValidityId() == ReferenceValueConstants.INVALID_FACILITY
					&& dataCenterDto.getStatus().getRecordValidityId() == ReferenceValueConstants.VALID_FACILITY) {
				messages.put("recordValidityCannotChange",
						messageSource.getMessage("recordValidityCannotChange", null, null));
			}
		}
		if (dataCenterDto.getStatus().getClosingStageId() == ReferenceValueConstants.CLOSED
				&& dataCenterDto.getStatus().getRecordValidityId() == ReferenceValueConstants.INVALID_FACILITY) {
			messages.put("recordValidityIncorrect", messageSource.getMessage("recordValidityIncorrect", null, null));
		}

	}

	/**
	 * Helper method to determine if the first field given is less than or equal
	 * to the second field given
	 * 
	 * @param fieldOne
	 * @param fieldTwo
	 * @return
	 */
	public boolean isFieldOneLTEFieldTwo(String fieldOne, String fieldTwo) {
		if (fieldOne != null && !fieldOne.isEmpty()) {
			if (fieldTwo != null && !fieldTwo.isEmpty()) {
				Double fieldOneDouble = Double.valueOf(fieldOne);
				Double fieldTwoDouble = Double.valueOf(fieldTwo);
				if (fieldOneDouble <= fieldTwoDouble) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Helper method to add to the messages list if the given fieldValue is null
	 * 
	 * @param fieldValue
	 * @param fieldName
	 * @param messages
	 */
	private void fillMessagesIfFieldIsNull(Object fieldValue, String fieldName, Map<String, String> messages) {

		if (fieldValue == null || fieldValue.toString().isEmpty()) {
			messages.put(fieldName, messageSource.getMessage(fieldName, null, null));
		}

	}

	/**
	 * Private helper method to determine if a data center is tiered or not
	 */
	private Boolean dataCenterIsTiered(DataCenterDto dataCenterDto) {
		Integer dataCenterTierId = dataCenterDto.getStatus().getDataCenterTierId();
		if (dataCenterTierId != null && (dataCenterTierId == ReferenceValueConstants.TIER_1
				|| dataCenterTierId == ReferenceValueConstants.TIER_2
				|| dataCenterTierId == ReferenceValueConstants.TIER_3
				|| dataCenterTierId == ReferenceValueConstants.TIER_4)) {
			return true;
		} else {
			return false;
		}
	}

}
