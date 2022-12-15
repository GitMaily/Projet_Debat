package up.mi.ttsmmc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class TestCombination {
	
	
	public static ArrayList<ArrayList<ArgumentNoeud>> generateCombinationsa(ListeAdjacence graphe) {
	    ArrayList<ArrayList<ArgumentNoeud>> combinations = new ArrayList<ArrayList<ArgumentNoeud>>();

	    // Récupérer la liste des clés du HashMap
	    ArrayList<ArgumentNoeud> keys = new ArrayList<ArgumentNoeud>(graphe.getGraphMap().keySet());
	    
	    int totalCombinations = (int) Math.pow(2, keys.size()); //jsp quoi faire avec ça ALEEEEEEEED
	    
	    // Générer toutes les combinaisons possibles de clés
	    for (int i = 0; i < keys.size(); i++) {
	        ArrayList<ArgumentNoeud> combination = new ArrayList<ArgumentNoeud>();
	        combination.add(keys.get(i));
	        combinations.add(combination);

	        for (int j = i + 1; j < keys.size(); j++) {
	            ArrayList<ArgumentNoeud> newCombination = new ArrayList<ArgumentNoeud>(combination);
	            newCombination.add(keys.get(j));
	            combinations.add(newCombination);
	            
	            for (int k = j + 1; k < keys.size(); k++) {
		            ArrayList<ArgumentNoeud> newCombination2 = new ArrayList<ArgumentNoeud>(newCombination);
		            newCombination2.add(keys.get(k));
		            combinations.add(newCombination2);
	            }
	        }
	    }

	    return combinations;
	}
	
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
	    String fileName = "E:\\user\\Documents\\Documents descartes L3\\COURS\\POOA\\Projet debat\\pooa\\Debat_Tanguy_Lavallee_Ciavaldini\\src\\file.txt";
    	System.out.println("* * * * * Représentation du graphe * * * * * ");
		ListeAdjacence graphe = new ListeAdjacence();
		graphe.extractArgument(fileName);
		graphe.afficherGraphe();
		graphe.extractContradiction(fileName);
		graphe.afficherGrapheAvecContradictions();
		
		graphe.afficherGraphe();
		
		ArrayList<ArrayList<ArgumentNoeud>> combinations = generateCombinationsa(graphe);
		 for(ArrayList<ArgumentNoeud> combination : combinations) {
		    	System.out.println(combination);
		    		
				
		    }
		
	}

}
