package ar.uba.fi.tdd.rulogic.parser;

import ar.uba.fi.tdd.rulogic.model.Question;
import ar.uba.fi.tdd.rulogic.model.QuestionModifier;


public class QuestionTranslator implements QuestionModifier {
	
	private ArgumentDisplacer argumentDisplacer;
	private Question output;
	
	public QuestionTranslator (ArgumentDisplacer argumentDisplacer, Question output){
		this.argumentDisplacer=argumentDisplacer;
		this.output=output;
	}

	@Override
	public Question from(Question q) {
		return new TranslatedQuestion(argumentDisplacer,output,q);
	}

}
