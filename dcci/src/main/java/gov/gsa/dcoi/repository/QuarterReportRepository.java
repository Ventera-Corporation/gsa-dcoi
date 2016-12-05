package gov.gsa.dcoi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.gsa.dcoi.entity.QuarterReport;

@Repository
public interface QuarterReportRepository extends CrudRepository<QuarterReport, Long> {

	public QuarterReport findByInProgressFlag(@Param("in_progress_flag") Integer inProgressFlag);

}
