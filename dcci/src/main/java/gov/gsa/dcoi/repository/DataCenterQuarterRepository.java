package gov.gsa.dcoi.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.gsa.dcoi.entity.DataCenterQuarter;

/**
 * Repository for handling data access to dataCenterQuarter information
 * 
 * @author sgonthier
 *
 */
@Repository
public interface DataCenterQuarterRepository extends PagingAndSortingRepository<DataCenterQuarter, Long> {

	/**
	 * Gets a list of dataCenterQuarter information based on the quarter report
	 * id
	 * 
	 * @param quarterReportId
	 * @return
	 */
	public List<DataCenterQuarter> findByQuarterReportId(@Param("quarter_report_id") Long quarterReportId);

	/**
	 * Gets a list of dataCenterQuarter information based on the quarter report
	 * id AND the data center id
	 * 
	 * @param quarterReportId
	 * @param dataCenterId
	 *            EXPECT ONE
	 * @return
	 */
	public DataCenterQuarter findByQuarterReportIdAndDataCenterId(@Param("quarter_report_id") Long quarterReportId,
			@Param("data_center_id") Integer dataCenterId);

	/**
	 * Find the list of data centers within a specific region
	 * 
	 * @param regionId
	 * @param quarterReportId
	 * @return
	 */
	public List<DataCenterQuarter> findByRegionIdAndQuarterReportId(@Param("region_id") Integer regionId,
			@Param("quarter_report_id") Long quarterReportId);

	/**
	 * Find the list of data center quarter entries for a given data center
	 * 
	 * @param dataCenterId
	 * @return
	 */
	public List<DataCenterQuarter> findByDataCenterId(@Param("data_center_id") Integer dataCenterId);
}
