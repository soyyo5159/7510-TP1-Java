package ar.uba.fi.tdd.rulogic.model;

public class NoModifier implements QuestionModifier {

	@Override
	public Question from(Question q) {
		return q;
	}

}
