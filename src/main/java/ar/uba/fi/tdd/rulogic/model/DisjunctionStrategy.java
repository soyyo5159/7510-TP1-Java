/**
 * 
 */
package ar.uba.fi.tdd.rulogic.model;

/**
 * @author José
 *
 */
public class DisjunctionStrategy implements AccumulationStrategy {

	@Override
	public boolean accumulate(boolean x, boolean y) {
		return x || y;
	}
	
	@Override
	public Boolean getDefaultValue() {
		return false;
	}

}
