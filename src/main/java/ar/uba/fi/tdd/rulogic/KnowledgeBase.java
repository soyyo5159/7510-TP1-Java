package ar.uba.fi.tdd.rulogic;

import ar.uba.fi.tdd.rulogic.model.Collection;
import ar.uba.fi.tdd.rulogic.parser.DatabaseParser;
import ar.uba.fi.tdd.rulogic.parser.ParseException;
import ar.uba.fi.tdd.rulogic.parser.ParsedQuestion;

public class KnowledgeBase {
	
	private Collection db;

	public KnowledgeBase(String db) throws ParseException{
		this.db=DatabaseParser.parse(db);
	}

	public boolean answer(String query) throws ParseException {
		return this.db.answer(new ParsedQuestion(query));
	}

}
