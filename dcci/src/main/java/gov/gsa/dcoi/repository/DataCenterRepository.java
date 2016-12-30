package gov.gsa.dcoi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
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
public interface DataCenterRepository extends PagingAndSortingRepository<DataCenter, Long> {

	/**
	 * Find the list of data centers matching the Data Center ID. EXPECT ONLY
	 * ONE
	 * 
	 * @param dataCenterId
	 * @return
	 */
	public DataCenter findByDataCenterId(@Param("data_center_id") Integer dataCenterId);

}
