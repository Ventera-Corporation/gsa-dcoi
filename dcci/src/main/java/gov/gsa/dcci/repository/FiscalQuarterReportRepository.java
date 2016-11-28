package gov.gsa.dcci.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import gov.gsa.dcci.entity.DataCenter;

@Transactional
public interface FiscalQuarterReportRepository extends CrudRepository<DataCenter, Long> {
	


}