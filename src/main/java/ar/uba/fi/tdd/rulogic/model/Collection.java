package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Collection implements Constraint, Answerer {
	private List<Constraint> constraints;
	private AccumulationStrategy strategy;
	
	private Collection(List<Constraint> constraints,AccumulationStrategy strategy){
		this.constraints=constraints;
		this.strategy=strategy;
	}
	
	public boolean checkQuestion(Question q) {
		boolean result = this.strategy.checkQuestion(this.constraints,q);
		return result;
	}
	
	static public Collection createConjunction(List<Constraint> constraints){
		return new Collection(constraints,new ConjunctionStrategy());
	}
	
	static public Collection createDisjunction(List<Constraint> constraints){
		return new Collection(constraints,new DisjunctionStrategy());
	}

	@Override
	public boolean answer(Question q) {
		return checkQuestion(q);
	}

}
