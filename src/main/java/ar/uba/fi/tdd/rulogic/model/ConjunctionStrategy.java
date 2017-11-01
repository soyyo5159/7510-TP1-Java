package ar.uba.fi.tdd.rulogic.model;

import java.util.List;

public class ConjunctionStrategy implements AccumulationStrategy {
	@Override
	public boolean checkQuestion(List<Constraint> constraints, Question q) {
		for(Constraint c:constraints){
			if(!c.checkQuestion(q)){
				return false;
			}
		}
		return true;
	}

}
