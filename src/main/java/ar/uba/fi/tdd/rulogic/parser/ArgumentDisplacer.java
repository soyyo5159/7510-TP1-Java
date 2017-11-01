package ar.uba.fi.tdd.rulogic.parser;

import java.util.List;

/**
 * A partir del header de la inferencia y la forma de la salida,
 * dice en que posicion del encabezado se encuentra una variable de la salida
 * @author José
 *
 */
public class ArgumentDisplacer {
	
	private QuestionShapeParser header;
	private ParsedQuestion output;
	
	public ArgumentDisplacer(QuestionShapeParser header, ParsedQuestion output){
		this.header=header;
		this.output=output;
	}

	public int positionOf(int argument) throws ParseException{ 
		String argumentName=this.output.nameAt(argument);
		List<String> arguments = this.header.getArguments();
		if(!arguments.contains(argumentName)){
			throw new ParseException(argumentName+" is not a part of the header of an inference");
		}
		
		return arguments.indexOf(argumentName);
	}

	public boolean hasDataAbout(int argument) {
		return this.output.hasNameAt(argument);
	}

}
