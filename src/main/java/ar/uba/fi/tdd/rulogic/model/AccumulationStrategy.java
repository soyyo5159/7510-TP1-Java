package ar.uba.fi.tdd.rulogic.model;

import java.util.List;
import java.util.function.BinaryOperator;

public interface AccumulationStrategy {
	public boolean checkQuestion(List<Constraint> constraints, Question q);
}
