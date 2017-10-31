package ar.uba.fi.tdd.rulogic.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.uba.fi.tdd.rulogic.model.Question;
import ar.uba.fi.tdd.rulogic.parser.ParseException;
import ar.uba.fi.tdd.rulogic.parser.ParsedQuestion;

public class ParsedQuestionTest {

	@Test
	public void questionParsedCorrecly() throws ParseException {
		Question q = new ParsedQuestion("what(a,b)");
		assertTrue(q.isNamed("what"));
		assertTrue(q.argumentNumberIs(2));
		assertTrue(q.argumentEquals(0, "a"));
		assertTrue(q.argumentEquals(1, "b"));
		
		assertFalse(q.argumentEquals(0, "b"));
		assertFalse(q.argumentEquals(1, "a"));
	}
	
	@Test
	public void capitalizedNounsAreQueries() throws ParseException {
		Question q = new ParsedQuestion("Nice(A,b)");
		assertTrue(q.isNamed("Nice"));
		assertTrue(q.argumentNumberIs(2));
		
		assertTrue(q.argumentEquals(0, "a"));
		assertTrue(q.argumentEquals(0, "b"));
		assertTrue(q.argumentEquals(0, "c"));
		assertTrue(q.argumentEquals(0, "woah"));
		
		
		assertTrue(q.argumentEquals(1, "b"));
		assertFalse(q.argumentEquals(1, "a"));
	}
	
	@Test(expected=ParseException.class)
	public void notAQuestion() throws ParseException {
		Question q = new ParsedQuestion("not a question!");
	}
	
	@Test
	public void strangeQuestion() throws ParseException {
		Question q = new ParsedQuestion("n o()");
		boolean e=q.argumentNumberIs(0);
		assertTrue(e);
		boolean n =q.isNamed("no");
		assertTrue(q.isNamed("no"));
	}
	
	

}
