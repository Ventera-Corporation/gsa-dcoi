package gov.gsa.dcoi.repository;

import java.util.List;

import javax.persistence.Table;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.gsa.dcoi.entity.CostCalculation;

/**
 * Cost Calculation repository to handle getting the information about the cost
 * totals for a quarter
 */
@Repository
@Table(name = "data_center_cost")
public interface CostCalculationRepository extends CrudRepository<CostCalculation, Long> {

	/**
	 * Find cost calc by data center id
	 * 
	 * @param dataCenterQuarterId
	 * @return
	 */
	public List<CostCalculation> findByDataCenterQuarterId(@Param("data_center_quarter_id") Long dataCenterQuarterId);

}
