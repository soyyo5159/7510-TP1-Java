package ar.uba.fi.tdd.rulogic.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.uba.fi.tdd.rulogic.model.Question;
import static org.mockito.Mockito.*;

public class TranslatedQuestionTest {

	@Test
	public void testTranslationCorrect() throws ParseException {
		Question output = mock(Question.class);//amigo(perez,X)
		when(output.isNamed("amigo")).thenReturn(true);
		when(output.argumentNumberIs(2)).thenReturn(true);
		when(output.argumentEquals(0,"perez")).thenReturn(true);
		
		
		Question source = mock(Question.class);//amigos(ana,juan)
		when(source.argumentEquals(0, "ana")).thenReturn(true);
		when(source.argumentEquals(0, "perez")).thenReturn(false);
		when(source.argumentEquals(1, "juan")).thenReturn(true);
		
		//el header seria amigos(X,Y)
		ArgumentDisplacer ad=mock(ArgumentDisplacer.class);
		when(ad.positionOf(1)).thenReturn(0);//ask for X
		when(ad.positionOf(0)).thenThrow(new ParseException("NO PEREZ"));//ask for perez
		
		
		TranslatedQuestion tq=new TranslatedQuestion(ad,output,source);
		assertTrue(tq.isNamed("amigo"));
		assertTrue(tq.argumentNumberIs(2));
		assertTrue(tq.argumentEquals(1,"ana"));
		assertTrue(tq.argumentEquals(0,"perez"));
		
		verify(output).argumentEquals(0, "perez");
		
		
	}

}