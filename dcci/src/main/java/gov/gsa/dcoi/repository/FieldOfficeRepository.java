package gov.gsa.dcoi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gov.gsa.dcoi.entity.FieldOffice;

@Repository
@Transactional
public interface FieldOfficeRepository extends CrudRepository<FieldOffice, Long> {
	
	FieldOffice save(FieldOffice fieldOffice);

}
