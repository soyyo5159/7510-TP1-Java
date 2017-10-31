package ar.uba.fi.tdd.rulogic.model;

public class QuestionConstraint implements Constraint {
	
	private QuestionModifier questionModifier;
	private Answerer answerer;
	public QuestionConstraint(QuestionModifier questionModifier, Answerer answerer){
		this.questionModifier=questionModifier;
		this.answerer=answerer;
	}

	public boolean checkQuestion(Question q) {
		return answerer.answer(questionModifier.from(q));
	}

}
