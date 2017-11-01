package ar.uba.fi.tdd.rulogic.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.uba.fi.tdd.rulogic.model.Collection;
import ar.uba.fi.tdd.rulogic.model.Constraint;

public class DatabaseParser {
	public static Collection parse(String s) throws ParseException{
		FutureAnswerer futureAnswerer=new FutureAnswerer();
		
		String clean = s.replace(" ", "").replaceAll("\n", "");
		List<String> parts = Arrays.asList(clean.split("\\."));
		
		List<Constraint> constraints=new ArrayList<Constraint>();
		InferenceParser inferenceParser=new InferenceParser(futureAnswerer);
		for(String part:parts){
			try{
				constraints.add(PremiseParser.parse(part));
				
			}catch(ParseException pe){
				constraints.add(inferenceParser.parse(part));
				
			}
		}
		Collection ret = Collection.createDisjunction(constraints);
		futureAnswerer.setAnswerer(ret);
		return ret;
	}
}
