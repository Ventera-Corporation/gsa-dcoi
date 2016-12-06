package gov.gsa.dcoi.repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;

/**
 * Class that contains the information necessary to call
 * the stored procedure that creates the information for a new quarter report to begin
 * @author sgonthier
 *
 */
@Repository
public class QuarterStoredProcedure {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Initializes a new quarter and sets all old quarters to be inactive (read-only)
	 * @param activateQuarter
	 * @return
	 */
	public Integer initQuarter(Integer activateQuarter) {
		// Create call stored procedure
		StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("usp_DCOI_CreateNewQuarter");
		// set parameters

		storedProcedure.registerStoredProcedureParameter("vActivateQuarter", Integer.class, ParameterMode.IN);

		storedProcedure.setParameter("vActivateQuarter", activateQuarter);
		// execute SP
		storedProcedure.execute();

		return 5;
	}

}
