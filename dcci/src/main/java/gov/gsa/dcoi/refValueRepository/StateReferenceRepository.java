package gov.gsa.dcoi.refValueRepository;


import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gov.gsa.dcoi.refValueEntity.State;

@Repository
public interface StateReferenceRepository extends CrudRepository<State, Long> {
	
	
	public List<State> findAll();
	
	@Cacheable(value = "states", key="#states.stateId")
	public State findByStateId(Integer stateId);

}
