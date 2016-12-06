package gov.gsa.dcoi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.gsa.dcoi.entity.QuarterReport;

/**
 * Repository for the quarterReport - all methods necessary to find information
 * by the specific quarter report (i.e. fiscalYearID, fiscalQuarterInfo, etc)
 * @author sgonthier
 *
 */
@Repository
public interface QuarterReportRepository extends CrudRepository<QuarterReport, Long> {

	public QuarterReport findByInProgressFlag(@Param("in_progress_flag") Integer inProgressFlag);

}
