package ar.uba.fi.tdd.rulogic.model;

public class ConjunctionStrategy implements AccumulationStrategy {

	@Override
	public boolean accumulate(boolean x, boolean y) {
		return x && y;
	}

	@Override
	public Boolean getDefaultValue() {
		return true;
	}

}
