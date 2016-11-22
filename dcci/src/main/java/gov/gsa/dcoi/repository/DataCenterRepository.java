package gov.gsa.dcoi.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gov.gsa.dcoi.entity.DataCenter;

@Repository
@Transactional
public interface DataCenterRepository extends CrudRepository<DataCenter, Long> {
	

}
