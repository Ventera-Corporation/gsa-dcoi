package gov.gsa.dcoi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.gsa.dcoi.entity.FieldOffice;

/**
 * Repository to handle data access for different field offices (i.e:
 * components)
 * 
 * @author sgonthier
 *
 */
@Repository
public interface FieldOfficeRepository extends CrudRepository<FieldOffice, Long> {

	/**
	 * Gets a list of field offices that are part of that data center quarter
	 * 
	 * @param dataCenterQuarterId
	 * @return
	 */
	public List<FieldOffice> findByDataCenterQuarterId(@Param("data_center_quarter_id") Long dataCenterQuarterId);

}
