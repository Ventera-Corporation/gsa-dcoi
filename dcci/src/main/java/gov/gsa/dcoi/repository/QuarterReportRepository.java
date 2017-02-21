package gov.gsa.dcoi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.gsa.dcoi.entity.QuarterReport;

/**
 * Repository for the quarterReport - all methods necessary to find information
 * by the specific quarter report (i.e. fiscalYearID, fiscalQuarterInfo, etc)
 * 
 * @author sgonthier
 *
 */
@Repository
public interface QuarterReportRepository extends CrudRepository<QuarterReport, Long> {

	/**
	 * Pulls back information about ONE quarter report based on the
	 * inProgressFlag of the overall report
	 * 
	 * @param inProgressFlag
	 * @return
	 */
	public QuarterReport findByQuarterInProgressFlag(@Param("in_progress_flag") Integer inProgressFlag);

	/**
	 * Pulls back information about ONE quarter report based on the activeFlag
	 * of the overall report
	 * 
	 * @param inProgressFlag
	 * @return
	 */
	public QuarterReport findByQuarterActiveFlag(@Param("active_flag") Integer activeFlag);

	/**
	 * Pulls back information about ONE quarter report based on either the
	 * active flag or in progress flag being set to 1
	 * 
	 * @param activeFlag
	 * @param inProgressFlag
	 * @return
	 */
	public QuarterReport findByQuarterActiveFlagOrQuarterInProgressFlag(@Param("active_flag") Integer activeFlag,
			@Param("in_progress_flag") Integer inProgressFlag);

	/**
	 * Pulls back information about ONE quarter based on the fiscal year and
	 * fiscal quarter of that quarter
	 * 
	 * @param fiscalYearId
	 * @param fiscalQuarterId
	 * @return
	 */
	public QuarterReport findByFiscalYearIdAndFiscalQuarterId(@Param("fiscal_year_id") Integer fiscalYearId,
			@Param("fiscal_quarter_id") Integer fiscalQuarterId);

}
