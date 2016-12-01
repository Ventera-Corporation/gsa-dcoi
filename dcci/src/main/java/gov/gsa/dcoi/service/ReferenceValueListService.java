package gov.gsa.dcoi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import gov.gsa.dcoi.refValueEntity.State;
import gov.gsa.dcoi.refValueRepository.StateReferenceRepository;

@Component
public class ReferenceValueListService {
	
	@Autowired
    CacheManager cacheManager;
	
	@Autowired
	StateReferenceRepository stateRefRepository;
	
	
	public void initRefValueLists(){
		initStateRefValues();
	}
	
	private void initStateRefValues(){
		List<State> allStates = stateRefRepository.findAll();
		for(State state : allStates){
			cacheManager.getCache("states").put(state.getStateId(), state);
		}
	}
	
	

}
