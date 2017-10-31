package ar.uba.fi.tdd.rulogic.model;

public class NameConstraint implements Constraint {
	private String name;
	public NameConstraint(String name){
		this.name=name;
	}
	public boolean checkQuestion(Question q) {
		return q.isNamed(this.name);
	}
}
