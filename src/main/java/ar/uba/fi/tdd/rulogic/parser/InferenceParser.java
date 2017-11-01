package ar.uba.fi.tdd.rulogic.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.uba.fi.tdd.rulogic.model.Answerer;
import ar.uba.fi.tdd.rulogic.model.Collection;
import ar.uba.fi.tdd.rulogic.model.Constraint;

public class InferenceParser {
	private Answerer answerer;
	public InferenceParser(Answerer answerer){
		this.answerer=answerer;
	}
	
	public Collection parse(String s) throws ParseException{
		String clean = s.replace("\n", "").replace(" ", "");
		List<String> parts = Arrays.asList(clean.split(":-"));
		
		if(parts.size()!=2){
			
			throw new ParseException("Not an inference: "+s);
		}
		
		String header=parts.get(0);
		String innerPremises=parts.get(1);
		
		
		QuestionShapeParser parsedHeader=new QuestionShapeParser(header);
		QuestionTranslatorFactory translatorFactory = new QuestionTranslatorFactory(parsedHeader);
		QuestionConstraintParser constraintParser=new QuestionConstraintParser(this.answerer,translatorFactory);
		
		
		List<Constraint> constraints=new ArrayList<Constraint>();
		
		//header constraints
		Collection headerConstraint = PremiseParser.parse(header);
		constraints.add(headerConstraint);
		
		
		//body constraints
		for(String text: innerPremises.split("\\),")){
			Constraint constraint = constraintParser.parse(text+")");
			constraints.add(constraint);
		}
		
		return Collection.createConjunction(constraints);
		
		
	}

}
