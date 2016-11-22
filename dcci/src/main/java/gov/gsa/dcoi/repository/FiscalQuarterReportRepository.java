package gov.gsa.dcoi.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gov.gsa.dcoi.entity.FiscalQuarterReport;

@Repository
@Transactional
public interface FiscalQuarterReportRepository extends CrudRepository<FiscalQuarterReport, Long> {

	 FiscalQuarterReport save(FiscalQuarterReport fiscalQuarterReport);
	
	

}
