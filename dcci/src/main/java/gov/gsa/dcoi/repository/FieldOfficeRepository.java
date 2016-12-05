package gov.gsa.dcoi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gov.gsa.dcoi.entity.FieldOffice;

@Repository
public interface FieldOfficeRepository extends CrudRepository<FieldOffice, Long> {

}
