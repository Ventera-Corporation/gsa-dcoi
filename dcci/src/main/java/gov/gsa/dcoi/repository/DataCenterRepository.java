package gov.gsa.dcoi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gov.gsa.dcoi.entity.DataCenter;

@Repository
public interface DataCenterRepository extends CrudRepository<DataCenter, Long> {

}
