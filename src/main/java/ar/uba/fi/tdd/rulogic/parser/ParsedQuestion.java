package ar.uba.fi.tdd.rulogic.parser;

import ar.uba.fi.tdd.rulogic.model.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ParsedQuestion implements Question{
	private String name;
	private List<String> arguments;
	private String originalString;
	
	public ParsedQuestion(String s) throws ParseException{
		QuestionShapeParser parser=new QuestionShapeParser(s);
		this.name=parser.getName();
		this.arguments=parser.getArguments();
		this.originalString=s;
	}

	@Override
	public boolean isNamed(String name) {
		return this.name.equals(name);
	}

	@Override
	public boolean argumentNumberIs(int length) {
		int l =arguments.size();
		return length==l;
	}

	@Override
	public boolean argumentEquals(int argument, String value) {
		if(argument>=arguments.size()){
			return false;
		}else{
			String arg=arguments.get(argument);
			return arg.equals(value) || arg.matches("^[A-Z]");
		}
	}

	public String nameAt(int argument) {
		return this.arguments.get(argument);
	}

	public boolean hasNameAt(int argument) {
		return argument < this.arguments.size();
	}
	
	@Override
	public String toString(){
		return originalString;
	}

	@Override
	public boolean hasArgument(int argument) {
		return this.arguments.size()<argument;
	}

}
