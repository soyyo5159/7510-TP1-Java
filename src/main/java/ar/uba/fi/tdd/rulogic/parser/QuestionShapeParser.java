package ar.uba.fi.tdd.rulogic.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionShapeParser {
	private String name;
	private List<String> arguments;
	
	public QuestionShapeParser(String s) throws ParseException{
		String prepared=s.replaceAll(" ","").replaceAll("\n", "");
		List<String> parts=Arrays.asList(prepared.split("\\("));
		
		if(parts.size()!=2){
			throw new ParseException("Can't parse as question:"+prepared);
		}
		
		this.name=parts.get(0);
		
		String ending=parts.get(1);
		String onlyArguments = ending.replaceAll("\\)", "");
		
		this.arguments =Arrays.asList( onlyArguments.split(",") )
				.stream()
				.filter( x -> !x.equals(""))
				.collect(Collectors.toList());
	}
	
	public String getName(){
		return name;
	}
	
	public List<String> getArguments(){
		return arguments;
	}

}
