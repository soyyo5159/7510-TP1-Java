/**
 * 
 */
package ar.uba.fi.tdd.rulogic.model;

import java.util.List;

/**
 * @author José
 *
 */
public class DisjunctionStrategy implements AccumulationStrategy {

	@Override
	public boolean checkQuestion(List<Constraint> constraints, Question q) {
		for(Constraint c:constraints){
			if(c.checkQuestion(q)){
				return true;
			}
		}
		return false;
	}


}
