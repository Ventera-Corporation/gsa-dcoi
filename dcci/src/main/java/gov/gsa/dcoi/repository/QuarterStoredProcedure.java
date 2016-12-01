package gov.gsa.dcoi.repository;


import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;

@Repository
public class QuarterStoredProcedure {
	
	@PersistenceContext
    private EntityManager em;
	
	public Integer initQuarter(Integer activateQuarter){
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
