package ar.uba.fi.tdd.rulogic.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.uba.fi.tdd.rulogic.model.Collection;
import ar.uba.fi.tdd.rulogic.model.Question;
import static org.mockito.Mockito.*;

public class PremiseParserTest {

	@Test
	public void testPremiseParserParsesPremisesGoodQuestion() throws ParseException {
		Collection premise=PremiseParser.parse("amigos(ana,maria)");
		
		Question correct=mock(Question.class);//amigos(ana,maria)
		when(correct.argumentEquals(0, "ana")).thenReturn(true);
		when(correct.argumentEquals(1, "maria")).thenReturn(true);
		when(correct.argumentNumberIs(2)).thenReturn(true);
		when(correct.isNamed("amigos")).thenReturn(true);
		
		assertTrue(premise.checkQuestion(correct));
		verify(correct).argumentEquals(0, "ana");
		verify(correct).argumentEquals(1, "maria");
		verify(correct).argumentNumberIs(2);
		verify(correct).isNamed("amigos");
	}
	
	@Test
	public void testPremiseParserParsesPremisesWithVariables() throws ParseException {
		Collection premise=PremiseParser.parse("amigos(ana,X)");
		
		Question correct=mock(Question.class);//amigos(ana,maria)
		when(correct.argumentEquals(0, "ana")).thenReturn(true);
		when(correct.argumentEquals(1, "maria")).thenReturn(true);
		when(correct.argumentNumberIs(2)).thenReturn(true);
		when(correct.isNamed("amigos")).thenReturn(true);
		
		assertTrue(premise.checkQuestion(correct));
		verify(correct).argumentEquals(0, "ana");
		verify(correct).argumentEquals(1, "maria");
		verify(correct).argumentNumberIs(2);
		verify(correct).isNamed("amigos");
	}
	
	@Test
	public void testPremiseParserParsesPremisesBadArguments() throws ParseException {
		Collection premise=PremiseParser.parse("amigos(ana,maria)");
		
		Question correct=mock(Question.class);//amigos(juarez,maria)
		when(correct.argumentEquals(0, "juarez")).thenReturn(true);
		when(correct.argumentEquals(1, "maria")).thenReturn(true);
		when(correct.argumentNumberIs(2)).thenReturn(true);
		when(correct.isNamed("amigos")).thenReturn(true);
		
		assertFalse(premise.checkQuestion(correct));
		verify(correct).argumentEquals(0, "ana");
		verify(correct).argumentEquals(1, "maria");
		verify(correct).argumentNumberIs(2);
		verify(correct).isNamed("amigos");
	}
	
	@Test
	public void testPremiseParserParsesPremisesBadName() throws ParseException {
		Collection premise=PremiseParser.parse("amigos(ana,maria)");
		
		Question correct=mock(Question.class);//guachos(ana,maria)
		when(correct.argumentEquals(0, "ana")).thenReturn(true);
		when(correct.argumentEquals(1, "maria")).thenReturn(true);
		when(correct.argumentNumberIs(2)).thenReturn(true);
		when(correct.isNamed("guachos")).thenReturn(true);
		
		assertFalse(premise.checkQuestion(correct));
		verify(correct).argumentEquals(0, "ana");
		verify(correct).argumentEquals(1, "maria");
		verify(correct).argumentNumberIs(2);
		verify(correct).isNamed("amigos");
	}
	
	@Test
	public void testPremiseParserParsesPremisesBadLength() throws ParseException {
		Collection premise=PremiseParser.parse("amigos(ana,maria)");
		
		Question correct=mock(Question.class);//amigos(ana,maria,jenio)
		when(correct.argumentEquals(0, "ana")).thenReturn(true);
		when(correct.argumentEquals(1, "maria")).thenReturn(true);
		when(correct.argumentEquals(2, "jenio")).thenReturn(true);
		when(correct.argumentNumberIs(3)).thenReturn(true);
		when(correct.isNamed("amigos")).thenReturn(true);
		
		assertFalse(premise.checkQuestion(correct));
		verify(correct).argumentEquals(0, "ana");
		verify(correct).argumentEquals(1, "maria");
		verify(correct).argumentNumberIs(2);
		verify(correct).isNamed("amigos");
	}

}
