package gov.gsa.dcoi.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.QueryTimeoutException;
import javax.persistence.StoredProcedureQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import gov.gsa.dcoi.DcoiExceptionHandler;

/**
 * Class that contains the information necessary to call the stored procedure
 * that creates the information for a new quarter report to begin
 * 
 * @author sgonthier
 *
 */
@Repository
public class QuarterStoredProcedure {

	private static final Logger LOGGER = LoggerFactory.getLogger(QuarterStoredProcedure.class);

	@PersistenceContext
	private EntityManager em;

	/**
	 * Initializes a new quarter and sets all old quarters to be inactive
	 * (read-only)
	 * 
	 * @param activateQuarter
	 * @return
	 */
	public void initQuarter() {
		// Create call stored procedure
		try {
			StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("usp_DCOI_CreateNewQuarter");

			// execute SP
			storedProcedure.execute();
		} catch (IllegalArgumentException iae) {
			LOGGER.error(iae.getMessage());
			throw DcoiExceptionHandler.throwDcoiException("Exception in Stored Procedure: " + iae.getMessage());
		} catch (QueryTimeoutException qte) {
			LOGGER.error(qte.getMessage());
			throw DcoiExceptionHandler.throwDcoiException("Exception in Stored Procedure: " + qte.getMessage());
		} catch (PersistenceException pe) {
			LOGGER.error(pe.getMessage());
			throw DcoiExceptionHandler.throwDcoiException("Exception in Stored Procedure: " + pe.getMessage());
		}

	}

}
