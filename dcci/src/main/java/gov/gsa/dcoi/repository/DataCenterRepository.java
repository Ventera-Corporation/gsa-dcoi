package gov.gsa.dcoi.repository;

import java.util.List;

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
	 * Find the list of data centers within a specific region
	 * 
	 * @param regionId
	 * @return
	 */
	public List<DataCenter> findByRegionId(@Param("region_id") Integer regionId);
	
	/**
	 * Find the list of data centers matching the Date Center String.
	 * 
	 * @param dataCenter
	 * @return
	 */
	public List<DataCenter> findByDcoiDataCenterId(@Param("dcoi_data_center_id") String dataCenter);
	
	/**
	 * Find the list of data centers matching the Data Center ID.
	 * 
	 * @param dataCenterId
	 * @return
	 */
	public List<DataCenter> findByDataCenterId(@Param("data_center_id") Integer dataCenterId);

}
