package ar.uba.fi.tdd.rulogic.model;

import java.util.function.BinaryOperator;

public interface AccumulationStrategy {
	public boolean accumulate(boolean x, boolean y);
	public Boolean getDefaultValue();
}
