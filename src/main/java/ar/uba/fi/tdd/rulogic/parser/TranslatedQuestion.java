package ar.uba.fi.tdd.rulogic.parser;

import ar.uba.fi.tdd.rulogic.model.Question;

public class TranslatedQuestion implements Question {
	
	private Question output;
	private Question source;
	private ArgumentDisplacer argumentDisplacer;
	
	public TranslatedQuestion(ArgumentDisplacer argumentDisplacer, Question output, Question source){
		this.argumentDisplacer=argumentDisplacer;
		this.output=output;
		this.source=source;
	}

	@Override
	public boolean isNamed(String name) {
		return this.output.isNamed(name);
	}

	@Override
	public boolean argumentNumberIs(int length) {
		return this.output.argumentNumberIs(length);
	}

	@Override
	public boolean argumentEquals(int argument, String value) {
		try {
			int displaced = this.argumentDisplacer.positionOf(argument);
			return this.source.argumentEquals(displaced,value);
		} catch (ParseException e) {
			return this.output.argumentEquals(argument,value);
		}
	}

}
