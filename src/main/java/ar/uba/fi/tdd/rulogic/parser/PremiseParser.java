package ar.uba.fi.tdd.rulogic.parser;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.uba.fi.tdd.rulogic.model.Constraint;
import ar.uba.fi.tdd.rulogic.model.NameConstraint;
import ar.uba.fi.tdd.rulogic.model.NoModifier;
import ar.uba.fi.tdd.rulogic.model.NthArgumentConstraint;
import ar.uba.fi.tdd.rulogic.model.ArgumentNumberConstraint;
import ar.uba.fi.tdd.rulogic.model.Collection;

public class PremiseParser {
	public static Collection parse(String s) throws ParseException{
		QuestionShapeParser q = new QuestionShapeParser(s);
		Set<Constraint> constraints=new HashSet<Constraint>();
		
		List<String> arguments = q.getArguments();
		constraints.add(new NameConstraint(q.getName()));
		constraints.add(new ArgumentNumberConstraint(arguments.size()));
		
		
		for(int i=0; i<arguments.size();i++){
			String argument = arguments.get(i);
			if(!argument.matches("^[A-Z]") && !argument.equals("_")){
				constraints.add(new NthArgumentConstraint(i,argument));
			}
		}
		
		return Collection.createConjunction(constraints);
	}

}
