package ar.uba.fi.tdd.rulogic.parser;

import ar.uba.fi.tdd.rulogic.model.QuestionModifier;

public class QuestionTranslatorFactory {
	
	private QuestionShapeParser header;
	
	public QuestionTranslatorFactory(QuestionShapeParser header){
		this.header=header;
	}
	
	
	public QuestionModifier withOutput(ParsedQuestion output){
		return new QuestionTranslator(new ArgumentDisplacer(this.header,output),output);
	}

}
