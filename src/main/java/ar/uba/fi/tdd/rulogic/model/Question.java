package ar.uba.fi.tdd.rulogic.model;

public interface Question {
	public boolean isNamed(String name);
	public boolean argumentNumberIs(int length);
	public boolean argumentEquals(int argument,String value);
}
