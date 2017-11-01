package ar.uba.fi.tdd.rulogic.parser;

import ar.uba.fi.tdd.rulogic.model.Answerer;
import ar.uba.fi.tdd.rulogic.model.Question;

public class FutureAnswerer implements Answerer {
	
	private Answerer answerer;

	public void setAnswerer(Answerer a){
		this.answerer=a;
	}

	@Override
	public boolean answer(Question q) {
		return this.answerer.answer(q);
	}

}
