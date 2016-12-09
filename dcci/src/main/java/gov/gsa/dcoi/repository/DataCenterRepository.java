package gov.gsa.dcoi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.gsa.dcoi.entity.DataCenter;

/**
 * Repository class to handle data access for dataCenters
 * 
 * @author sgonthier
 *
 */
@Repository
public interface DataCenterRepository extends CrudRepository<DataCenter, Long> {

	/**
	 * Find the list of data centers within a specific region
	 * 
	 * @param regionId
	 * @return
	 */
	public List<DataCenter> findByRegionId(@Param("region_id") Integer regionId);

}
