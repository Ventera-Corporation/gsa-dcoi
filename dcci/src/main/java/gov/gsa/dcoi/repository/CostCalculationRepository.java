package gov.gsa.dcoi.repository;

import javax.persistence.Table;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gov.gsa.dcoi.entity.CostCalculation;

@Repository
@Table(name = "data_center_cost")
public interface CostCalculationRepository extends CrudRepository<CostCalculation, Long>{

}
