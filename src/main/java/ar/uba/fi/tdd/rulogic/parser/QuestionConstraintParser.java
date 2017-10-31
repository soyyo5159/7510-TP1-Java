package ar.uba.fi.tdd.rulogic.parser;

import ar.uba.fi.tdd.rulogic.model.Answerer;
import ar.uba.fi.tdd.rulogic.model.QuestionConstraint;

public class QuestionConstraintParser{
	
	private Answerer answerer;
	private QuestionTranslatorFactory translator;
	
	public QuestionConstraintParser(Answerer answerer, QuestionTranslatorFactory translator){
		this.answerer=answerer;
		this.translator=translator;
	}

	public QuestionConstraint parse(String s) throws ParseException{
		ParsedQuestion q = new ParsedQuestion(s);
		return new QuestionConstraint(this.translator.withOutput(q),this.answerer);
	}

}
