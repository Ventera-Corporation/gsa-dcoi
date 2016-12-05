package gov.gsa.dcoi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.gsa.dcoi.entity.DataCenterQuarter;

@Repository
public interface DataCenterQuarterRepository extends CrudRepository<DataCenterQuarter, Long> {

	public List<DataCenterQuarter> findByQuarterReportId(@Param("quarter_report_id") Integer quarterReportId);
}
