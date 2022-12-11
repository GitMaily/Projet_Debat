package up.mi.ttsmmc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestArgumentExtractor {
	
	public static void main(String[] args) throws IOException {
	    
		  
		String fileName = "\\chemin\\du\\fichier\\file.txt"; // remplacez par le chemin et le nom du fichier à lire
	    String line;

	    // utilisez un BufferedReader pour lire le fichier ligne par ligne
	    BufferedReader reader = new BufferedReader(new FileReader(fileName));
	    while ((line = reader.readLine()) != null) {
	      // recherchez le mot "Argument(" dans chaque ligne
	      int index = line.indexOf("Argument("); // -1 si existe pas
	      if (index != -1) {
	        // si le mot est présent, récupérez la chaîne entre les parenthèses
	        int start = index + "Argument(".length();
	        int end = line.indexOf(")", start); //
	        String argument = line.substring(start, end);
	        
	        // affichez le résultat
	        System.out.println(argument);
	        System.out.println(index);
	        System.out.println("end" + end);
	        System.out.println("start" + start);
	        
	      }
	    }
	    reader.close();
	  }

}
