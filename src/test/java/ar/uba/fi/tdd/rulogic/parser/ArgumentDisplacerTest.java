package ar.uba.fi.tdd.rulogic.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArgumentDisplacerTest {

	@Test
	public void testPositionOfVariable() throws ParseException {
		//algo(X,Y,Z)
		QuestionShapeParser header = mock(QuestionShapeParser.class);
		List<String> l=Arrays.asList("X","Y","Z");
		when(header.getArguments()).thenReturn(l);
		
		//algomas(ana,Z,peru,X)
		ParsedQuestion output = mock(ParsedQuestion.class);
		when(output.nameAt(0)).thenReturn("ana");
		when(output.nameAt(1)).thenReturn("Z");
		when(output.nameAt(2)).thenReturn("peru");
		when(output.nameAt(3)).thenReturn("X");
		
		ArgumentDisplacer ad=new ArgumentDisplacer(header,output);
		assertEquals(ad.positionOf(1),2);
		assertEquals(ad.positionOf(3),0);
	}
	
	@Test(expected=ParseException.class)
	public void testPositionOfNonexistentVariable() throws ParseException {
		//algo(X,Y,Z)
		QuestionShapeParser header = mock(QuestionShapeParser.class);
		List<String> l=Arrays.asList("X","Y","Z");
		when(header.getArguments()).thenReturn(l);
		
		//algomas(ana,Z,peru,X)
		ParsedQuestion output = mock(ParsedQuestion.class);
		when(output.nameAt(0)).thenReturn("ana");
		when(output.nameAt(1)).thenReturn("Z");
		when(output.nameAt(2)).thenReturn("peru");
		when(output.nameAt(3)).thenReturn("X");
		
		ArgumentDisplacer ad=new ArgumentDisplacer(header,output);
		ad.positionOf(0);//ana, no se encuentra en el encabezado
	}

}
