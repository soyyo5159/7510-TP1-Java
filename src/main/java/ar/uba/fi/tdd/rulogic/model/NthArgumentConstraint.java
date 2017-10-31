package ar.uba.fi.tdd.rulogic.model;

public class NthArgumentConstraint implements Constraint {
	private String value;
	private int argument;
	
	public NthArgumentConstraint(int argument,String value){
		this.value=value;
		this.argument=argument;
	}

	public boolean checkQuestion(Question q) {
		return q.argumentEquals(this.argument, this.value);
	}

}
