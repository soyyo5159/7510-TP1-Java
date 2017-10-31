package ar.uba.fi.tdd.rulogic.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.uba.fi.tdd.rulogic.model.Answerer;
import ar.uba.fi.tdd.rulogic.model.Collection;
import ar.uba.fi.tdd.rulogic.model.Question;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import ar.uba.fi.tdd.rulogic.parser.ParseException;

public class InferenceParserTest {

	@Test(expected=ParseException.class)
	public void testPremiseWithNoBody() throws ParseException {
		Answerer answerer=mock(Answerer.class);
		
		InferenceParser parser=new InferenceParser(answerer);
		parser.parse("hola(1,2,3)");
	}
	
	@Test
	public void testPremiseDoesCorrectQuestionsReturnsTrue() throws ParseException {
		FastQuestion  amigoJuan=new FastQuestion(Arrays.asList("amigo","juan"));
		FastQuestion  amigoMaria=new FastQuestion(Arrays.asList("amigo","maria"));
		Answerer answerer=mock(Answerer.class);
		
		when(answerer.answer(amigoJuan)).thenReturn(true);
		when(answerer.answer(amigoMaria)).thenReturn(true);
		
		InferenceParser parser=new InferenceParser(answerer);
		Collection inference = parser.parse("amigos(X,Y):-amigo(X),amigo(Y)");
		boolean result = inference.checkQuestion(new FastQuestion(Arrays.asList("amigos","juan","maria")));
		assertTrue(result);
	}
	
	@Test
	public void testPremiseDoesCorrectQuestionsReturnsFalse1() throws ParseException {
		FastQuestion  amigoJuan=new FastQuestion(Arrays.asList("amigo","juan"));
		FastQuestion  amigoMaria=new FastQuestion(Arrays.asList("amigo","maria"));
		Answerer answerer=mock(Answerer.class);
		
		when(answerer.answer(amigoJuan)).thenReturn(false);
		when(answerer.answer(amigoMaria)).thenReturn(true);
		
		InferenceParser parser=new InferenceParser(answerer);
		Collection inference = parser.parse("amigos(X,Y):-amigo(X),amigo(Y)");
		boolean result = inference.checkQuestion(new FastQuestion(Arrays.asList("amigos","juan","maria")));
		assertFalse(result);
	}
	
	@Test
	public void testPremiseDoesCorrectQuestionsReturnsFalse2() throws ParseException {
		FastQuestion  amigoJuan=new FastQuestion(Arrays.asList("amigo","juan"));
		FastQuestion  amigoMaria=new FastQuestion(Arrays.asList("amigo","maria"));
		Answerer answerer=mock(Answerer.class);
		
		when(answerer.answer(amigoJuan)).thenReturn(true);
		when(answerer.answer(amigoMaria)).thenReturn(false);
		
		InferenceParser parser=new InferenceParser(answerer);
		Collection inference = parser.parse("amigos(X,Y):-amigo(X),amigo(Y)");
		boolean result = inference.checkQuestion(new FastQuestion(Arrays.asList("amigos","juan","maria")));
		assertFalse(result);
	}
	
	@Test
	public void testPremiseDoesCorrectQuestionsReturnsFalseBoth() throws ParseException {
		FastQuestion  amigoJuan=new FastQuestion(Arrays.asList("amigo","juan"));
		FastQuestion  amigoMaria=new FastQuestion(Arrays.asList("amigo","maria"));
		Answerer answerer=mock(Answerer.class);
		
		when(answerer.answer(amigoJuan)).thenReturn(false);
		when(answerer.answer(amigoMaria)).thenReturn(false);
		
		InferenceParser parser=new InferenceParser(answerer);
		Collection inference = parser.parse("amigos(X,Y):-amigo(X),amigo(Y)");
		boolean result = inference.checkQuestion(new FastQuestion(Arrays.asList("amigos","juan","maria")));
		assertFalse(result);
	}
	
	@Test
	public void testPremiseDoesCorrectQuestionsFixedConstraint() throws ParseException {
		FastQuestion  amigoJuan=new FastQuestion(Arrays.asList("amigo","juan"));
		FastQuestion  amigoMaria=new FastQuestion(Arrays.asList("amigo","maria"));
		Answerer answerer=mock(Answerer.class);
		
		when(answerer.answer(amigoJuan)).thenReturn(true);
		when(answerer.answer(amigoMaria)).thenReturn(true);
		
		InferenceParser parser=new InferenceParser(answerer);
		Collection inference = parser.parse("amigos(perez,X,Y):-amigo(X),amigo(Y)");
		//boolean result = inference.checkQuestion(new FastQuestion(Arrays.asList("amigos","juan","maria")));
		//assertFalse(result);
		boolean result2 = inference.checkQuestion(new FastQuestion(Arrays.asList("amigos","perez","juan","maria")));
		assertTrue(result2);
	}

}