package ar.uba.fi.tdd.rulogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Console application.
 *
 */
public class App
{
	public static void main(String[] args) throws Exception {
		
		boolean reading = true;
		
		if(args.length==0){
			System.out.println("Put the file name in the first argument");
		}else{
			System.out.println("I shall answer all your questions!");
			String text =new String(Files.readAllBytes(Paths.get(args[0])),StandardCharsets.UTF_8);
			KnowledgeBase base=new KnowledgeBase(text);
			while(reading){
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String line=reader.readLine();
				boolean res = base.answer(line);
				if(res){
					System.out.println("true");
				}else{
					System.out.println("false");
				}	
			}
			
		}
		
		
    }
}
