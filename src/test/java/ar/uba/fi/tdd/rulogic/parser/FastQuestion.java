package ar.uba.fi.tdd.rulogic.parser;

import java.util.List;

import ar.uba.fi.tdd.rulogic.model.Question;

public class FastQuestion implements Question{
	
	private String name;
	private List<String> arguments;

	public FastQuestion (List<String> parts){
		this.name=parts.get(0);
		this.arguments=parts.subList(1, parts.size());
	}

	@Override
	public boolean equals(Object arg0) {
		Question q=(Question) arg0;
		
		if(!q.isNamed(this.name)){
			return false;
		}
		
		if(!q.argumentNumberIs(this.arguments.size())){
			return false;
		}
		
		boolean allTrue=true;
		for (int i=0;i<this.arguments.size();i++){
			
			allTrue=allTrue && q.argumentEquals(i, this.arguments.get(i));
		}
		return allTrue;
	}

	@Override
	public boolean isNamed(String name) {
		return name.equals(this.name);
	}

	@Override
	public boolean argumentNumberIs(int length) {
		return length==this.arguments.size();
	}

	@Override
	public boolean argumentEquals(int argument, String value) {
		
		if(!(argument<this.arguments.size())){
			return false;
		}

		return this.arguments.get(argument).equals(value);
		
	}
	
}
