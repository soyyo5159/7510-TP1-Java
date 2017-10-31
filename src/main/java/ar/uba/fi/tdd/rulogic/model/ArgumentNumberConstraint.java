package ar.uba.fi.tdd.rulogic.model;

public class ArgumentNumberConstraint implements Constraint {
	private int number;
	public ArgumentNumberConstraint(int number){
		this.number=number;
	}

	public boolean checkQuestion(Question q) {
		return q.argumentNumberIs(this.number);
	}

}
