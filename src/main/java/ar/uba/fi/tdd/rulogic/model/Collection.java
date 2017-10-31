package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Collection implements Constraint {
	private Set<Constraint> constraints;
	private AccumulationStrategy strategy;
	
	private Collection(Set<Constraint> constraints,AccumulationStrategy strategy){
		this.constraints=constraints;
		this.strategy=strategy;
	}
	
	public boolean checkQuestion(Question q) {
		List<Boolean> results = new ArrayList<Boolean>();
		for(Constraint c:constraints){
			Boolean result = c.checkQuestion(q);
			results.add(result);
		}
		
		boolean ret = results.stream()
				.reduce(this.strategy.getDefaultValue(),(x,y)->this.strategy.accumulate(x,y));
		return ret;
		
	}
	
	static public Collection createConjunction(Set<Constraint> constraints){
		return new Collection(constraints,new ConjunctionStrategy());
	}
	
	static public Collection createDisjunction(Set<Constraint> constraints){
		return new Collection(constraints,new DisjunctionStrategy());
	}

}
