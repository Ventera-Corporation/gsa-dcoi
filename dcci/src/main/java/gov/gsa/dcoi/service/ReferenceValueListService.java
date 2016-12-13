package gov.gsa.dcoi.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.gsa.dcoi.refValueEntity.GenericReferenceValueObject;
import gov.gsa.dcoi.refValueRepository.StateReferenceRepository;
import gov.gsa.dcoi.repository.ReferenceValueListRepository;

/**
 * Possible service class to pull information from the database for the various
 * reference value lists
 * 
 * TODO: refactor
 * 
 * @author sgonthier
 *
 */
@Component
public class ReferenceValueListService {

	@Autowired
	StateReferenceRepository stateRefRepository;

	@Autowired
	ReferenceValueListRepository refValueListRepository;

	public static Map<String, List<GenericReferenceValueObject>> refValueLists = Collections
			.synchronizedMap(new HashMap<>());

	/**
	 * Initialize all reference value lists into caches
	 */
	public void initRefValueLists() {

		initStateRefValues();
		initRecordValidity();
		initRecordStatus();
		initFiscalYear();
		initFiscalQuarter();
		initRegion();
		initISSPosition();
		initOwnershipType();
		initDataCenterTier();
		initCountry();
		initCoreClassification();
		initClosingStage();
		initComponents();

	}

	/**
	 * Initialize state reference value list
	 */
	private void initStateRefValues() {
		refValueLists.put("stateRefValueList", refValueListRepository.findAllStates());
	}

	/**
	 * Initialize record validity ref value list
	 */
	private void initRecordValidity() {
		refValueLists.put("recordValidityRefValueList", refValueListRepository.findAllRecordValidities());

	}

	/**
	 * Initalize record status ref value list
	 */
	private void initRecordStatus() {

		refValueLists.put("recordStatusRefValueList", refValueListRepository.findAllRecordStatuses());

	}

	/**
	 * Initialize fiscal years ref value list
	 */
	private void initFiscalYear() {

		refValueLists.put("fiscalYearsRefValueList", refValueListRepository.findAllFiscalYears());

	}

	/**
	 * Initialize fiscal qaurters ref value list
	 */
	private void initFiscalQuarter() {
		refValueLists.put("fiscalQuartersRefValueList", refValueListRepository.findAllFiscalQuarters());

	}

	/**
	 * Initialize regions ref value list
	 */
	private void initRegion() {
		refValueLists.put("regionRefValueList", refValueListRepository.findAllRegions());
	}

	/**
	 * Initliaze ownership type ref value list
	 */
	private void initOwnershipType() {
		refValueLists.put("ownershipTypeRefValueList", refValueListRepository.findAllOwnershipTypes());
	}

	/**
	 * Initialize iss position ref value list
	 */
	private void initISSPosition() {
		refValueLists.put("issPositionRefValueList", refValueListRepository.findAllISSPositions());
	}

	/**
	 * Initialize data center tier ref value list
	 */
	private void initDataCenterTier() {
		refValueLists.put("dataCenterTierRefValueList", refValueListRepository.findAllDataCenterTiers());
	}

	/**
	 * Initialize country ref value list
	 */
	private void initCountry() {
		refValueLists.put("countryRefValueList", refValueListRepository.findAllCountries());
	}

	/**
	 * Initialize core classification ref value list
	 */
	private void initCoreClassification() {
		refValueLists.put("coreClassificationRefValueList", refValueListRepository.findAllCoreClassifications());
	}

	/**
	 * Initialize closing stage ref value list
	 */
	private void initClosingStage() {
		refValueLists.put("closingStageRefValueList", refValueListRepository.findAllClosingStages());
	}

	/**
	 * Initialize components ref value list
	 */
	private void initComponents() {
		refValueLists.put("componentRefValueList", refValueListRepository.findAllComponents());
	}

}
