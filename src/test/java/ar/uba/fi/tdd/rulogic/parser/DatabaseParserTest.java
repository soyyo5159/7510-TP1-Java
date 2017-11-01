package ar.uba.fi.tdd.rulogic.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.uba.fi.tdd.rulogic.model.Answerer;
import ar.uba.fi.tdd.rulogic.model.Question;

public class DatabaseParserTest {

	@Test
	public void testDatabaseOfPremises() throws ParseException {
		String db="pais(argentina,trigo)."
				+ "pais(argentina,soja)."
				+ "pais(argentina,carne)."
				+ "pais(india,soja)."
				+ "pais(india,trigo)."
				+ "pais(india,honestos)."
				+ "pais(sudafrica,trigo)."
				+ "pais(sudafrica,honestos).";
		Answerer parsedDb=DatabaseParser.parse(db);
		
		Question fullyInstantiatedPositive=new ParsedQuestion("pais(argentina,carne)");
		Question fullyInstantiatedNegative=new ParsedQuestion("pais(argentina,honestos)");
		Question partialPositive=new ParsedQuestion("pais(X,carne)");
		Question partialNegative=new ParsedQuestion("pais(X,fusionnuclear)");
		
		assertTrue(parsedDb.answer(fullyInstantiatedPositive));
		assertFalse(parsedDb.answer(fullyInstantiatedNegative));
		assertTrue(parsedDb.answer(partialPositive));
		assertFalse(parsedDb.answer(partialNegative));
	}
	
	@Test
	public void testDatabaseOfPremisesAndInferences() throws ParseException {
		String db="pais(india,honestos)."
				+ "pais(sudafrica,honestos)."
				+ "producen(X,Y,P):-pais(X,P),pais(Y,P).";
		Answerer parsedDb=DatabaseParser.parse(db);
		
		Question fullyInstantiatedPositive=new ParsedQuestion("producen(india,sudafrica,honestos)");
		Question fullyInstantiatedNegative=new ParsedQuestion("producen(india,sudafrica,extraterrestres)");
		Question partialPositive=new ParsedQuestion("producen(X,sudafrica,honestos)");
		Question partialNegative=new ParsedQuestion("producen(X,sudafrica,fusionnuclear)");
		
		assertTrue(parsedDb.answer(fullyInstantiatedPositive));
		assertFalse(parsedDb.answer(fullyInstantiatedNegative));
		assertTrue(parsedDb.answer(partialPositive));
		assertFalse(parsedDb.answer(partialNegative));
	}

	@Test
	public void testDatabaseOfPremisesAndRepeatedInferences() throws ParseException {
		String db="pais(india,honestos)."
				+ "alguno(X,Y,Prod):-pais(X,Prod)."
				+ "alguno(X,Y,Prod):-pais(Y,Prod).";
				
		Answerer parsedDb=DatabaseParser.parse(db);
		
		Question fullyInstantiatedPositive1=new ParsedQuestion("alguno(india,argentina,honestos)");
		Question fullyInstantiatedPositive2=new ParsedQuestion("alguno(argentina,india,honestos)");
		Question fullyInstantiatedNegative=new ParsedQuestion("alguno(india,sudafrica,extraterrestres)");
		Question partialPositive=new ParsedQuestion("alguno(X,Y,honestos)");
		Question partialNegative=new ParsedQuestion("alguno(X,sudafrica,fusionnuclear)");
		
		assertTrue(parsedDb.answer(fullyInstantiatedPositive1));
		
		assertTrue(parsedDb.answer(fullyInstantiatedPositive2));
		assertFalse(parsedDb.answer(fullyInstantiatedNegative));
		assertTrue(parsedDb.answer(partialPositive));
		assertFalse(parsedDb.answer(partialNegative));
	}

}
