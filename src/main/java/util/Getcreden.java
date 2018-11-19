package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class Getcreden {
	
	String line="";
	String result="";
	
 public String getCredentials (String path) throws IOException {
	 
	 
	 BufferedReader br = new BufferedReader(new FileReader(path));
	 
	
	while ((line = br.readLine()) != null) {

		
	    result = line.trim().toString();
		 
			}
	
	br.close();
	 
	 return result;
	 
	 
 }

}
