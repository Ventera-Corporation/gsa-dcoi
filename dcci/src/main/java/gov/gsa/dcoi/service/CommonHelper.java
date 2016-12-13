package gov.gsa.dcoi.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

import gov.gsa.dcoi.refValueEntity.GenericReferenceValueObject;
import gov.gsa.dcoi.repository.ReferenceValueListRepository;

/**
 * POJO class to help convert the id's for a given ref value list into a value
 * 
 * @author sgonthier
 *
 */
public class CommonHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReferenceValueListRepository.class);

	private static Map<String, List<GenericReferenceValueObject>> refValueLists = ReferenceValueListService.refValueLists;

	private CommonHelper() {
	};

	/**
	 * Parse the fiscal quarter Id and return its string value
	 * 
	 * @param fiscalQuarterId
	 * @return
	 */
	@Cacheable("fiscalQuarters")
	public static String parseFiscalQuarterId(Integer fiscalQuarterId) {
		List<GenericReferenceValueObject> fiscalQuarters = refValueLists.get("fiscalQuartersRefValueList");
		for (GenericReferenceValueObject refValueObject : fiscalQuarters) {
			if (refValueObject.getId() == fiscalQuarterId) {
				return refValueObject.getValue();
			}
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Issue with getting fiscal quarter string from fiscal quarter Id");
		}

		return "NONE";

	}

	/**
	 * Parse the fiscal year id and return its string value
	 * 
	 * @param fiscalYearId
	 * @return
	 */
	@Cacheable("fiscalYears")
	public static String parseFiscalYearId(Integer fiscalYearId) {
		List<GenericReferenceValueObject> fiscalYears = refValueLists.get("fiscalYearsRefValueList");
		for (GenericReferenceValueObject refValueObject : fiscalYears) {
			if (refValueObject.getId() == fiscalYearId) {
				return refValueObject.getValue();
			}
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Issue with getting fiscal year string from fiscal quarter Id");
		}

		return "NONE";

	}

	/**
	 * Parse the iss position id and return its string value
	 * 
	 * @param issPositionId
	 * @return
	 */
	@Cacheable("issPositions")
	public static String parseIssPositionId(Integer issPositionId) {
		List<GenericReferenceValueObject> sharedServicesPosition = refValueLists.get("issPositionRefValueList");
		for (GenericReferenceValueObject refValueObject : sharedServicesPosition) {
			if (refValueObject.getId() == issPositionId) {
				return refValueObject.getValue();
			}
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Retrieving shared services position string based on shared services id");
		}

		return "NONE";
	}

	/**
	 * Parse the record status id and return its string value
	 * 
	 * @param recordStatusId
	 * @return
	 */
	@Cacheable("recordStatuses")
	public static String parseRecordStatusId(Integer recordStatusId) {
		List<GenericReferenceValueObject> recordStatus = refValueLists.get("recordStatusRefValueList");
		for (GenericReferenceValueObject refValueObject : recordStatus) {
			if (refValueObject.getId() == recordStatusId) {
				return refValueObject.getValue();
			}
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Retrieving record status string based on record status id");
		}

		return "NONE";
	}

	/**
	 * Parse the recordValidity id and return its string value
	 * 
	 * @param recordValidityId
	 * @return
	 */
	@Cacheable("recordValidities")
	public static String parseRecordValidityId(Integer recordValidityId) {
		List<GenericReferenceValueObject> recordValidity = refValueLists.get("recordValidityRefValueList");
		for (GenericReferenceValueObject refValueObject : recordValidity) {
			if (refValueObject.getId() == recordValidityId) {
				return refValueObject.getValue();
			}
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Retrieving record validity position string based on record validity id");
		}

		return "NONE";
	}

	/**
	 * Parse the ownership type id and return its string value
	 * 
	 * @param ownershipTypeId
	 * @return
	 */
	@Cacheable("ownershipTypes")
	public static String parseOwnershipTypeId(Integer ownershipTypeId) {
		List<GenericReferenceValueObject> ownershipType = refValueLists.get("ownershipTypeRefValueList");
		for (GenericReferenceValueObject refValueObject : ownershipType) {
			if (refValueObject.getId() == ownershipTypeId) {
				return refValueObject.getValue();
			}
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Retrieving ownership type string based on ownership type id");
		}

		return "NONE";
	}

	/**
	 * Parse the data center tier id and return its string value
	 * 
	 * @param dataCenterTierId
	 * @return
	 */
	@Cacheable("dataCenterTiers")
	public static String parseDataCenterTierId(Integer dataCenterTierId) {
		List<GenericReferenceValueObject> dataCenterTier = refValueLists.get("dataCenterTierRefValueList");
		for (GenericReferenceValueObject refValueObject : dataCenterTier) {
			if (refValueObject.getId() == dataCenterTierId) {
				return refValueObject.getValue();
			}
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Retrieving ownership type string based on ownership type id");
		}

		return "NONE";
	}

	/**
	 * Parse the closing stage id and return its string value
	 * 
	 * @param closingStageId
	 * @return
	 */
	@Cacheable("closingStages")
	public static String parseClosingStageId(Integer closingStageId) {
		List<GenericReferenceValueObject> closingStage = refValueLists.get("closingStageRefValueList");
		for (GenericReferenceValueObject refValueObject : closingStage) {
			if (refValueObject.getId() == closingStageId) {
				return refValueObject.getValue();
			}
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Retrieving closing stage string based on closing stage id");
		}

		return "NONE";
	}

	/**
	 * Parse the state id and return its string value
	 * 
	 * @param stateId
	 * @return
	 */
	@Cacheable("states")
	public static String parseStateId(Integer stateId) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Retrieving state string based on state id");
		}
		List<GenericReferenceValueObject> state = refValueLists.get("stateRefValueList");
		for (GenericReferenceValueObject refValueObject : state) {
			if (refValueObject.getId() == stateId) {
				return refValueObject.getValue();
			}
		}
		return null;

	}

	/**
	 * Parse the country id and return its string value
	 * 
	 * @param countryId
	 * @return
	 */
	@Cacheable("countries")
	public static String parseCountryId(Integer countryId) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Retrieving country string based on country id");
		}
		List<GenericReferenceValueObject> country = refValueLists.get("countryRefValueList");
		for (GenericReferenceValueObject refValueObject : country) {
			if (refValueObject.getId() == countryId) {
				return refValueObject.getValue();
			}
		}

		return "NONE";
	}

	/**
	 * Parse the component id and return its string value
	 * 
	 * @param componentId
	 * @return
	 */
	@Cacheable("components")
	public static String parseComponentId(Integer componentId) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Retrieving component string name based on component id");
		}
		List<GenericReferenceValueObject> component = refValueLists.get("componentRefValueList");
		for (GenericReferenceValueObject refValueObject : component) {
			if (refValueObject.getId() == componentId) {
				return refValueObject.getValue();
			}
		}

		return "NONE";
	}

}
