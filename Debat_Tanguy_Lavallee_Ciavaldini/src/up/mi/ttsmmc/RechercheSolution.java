package up.mi.ttsmmc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * cette classe sert a rechercher les solution admissible et préféré ainsi que de sauvegarder
 * dans un fichier une solution
 * @author Thomas_Tanguy
 * @author Samuel_Lavallée
 * @author Maily_Ciavaldini
 * @version PHASE_2
 */
public class RechercheSolution{
	
	HashMap<ArgumentNoeud,ArrayList<ArrayList<ArgumentNoeud>>> ensemblesPossibles;
	
	//variable
	private ListeAdjacence graphe;
	private ArrayList<ArgumentNoeud> proposition;
	private List<ArrayList<ArgumentNoeud>> listePropositions;
	
	//constructeur
	public RechercheSolution(ListeAdjacence graphe) {
		this.graphe = graphe;
		ensemblesPossibles = new HashMap<ArgumentNoeud,ArrayList<ArrayList<ArgumentNoeud>>>();
		
		
		proposition = new ArrayList<ArgumentNoeud>();
		listePropositions = new ArrayList<ArrayList<ArgumentNoeud>>();

	}
	/*
	 * Affiche la liste des propositions
	 */
	public void afficherListePropositions() {
		for(int i =0;i<listePropositions.size();i++) {
			listePropositions.get(i).toString();
		}
	}
	/**
	 * Sauvegarde la liste de solution dans un fichier.
	 * @param sortie la liste de solution
	 * @param filePath le chemin du fichier
	 */
	public void sauvegarderLaSolution(List<List<ArgumentNoeud>> sortie, String filePath) {
	    
		// Créer un nouveau fichier en utilisant le chemin spécifié
	    
		File file = new File(filePath);
	    
		// On regarde si le fichier existe ou pas, si il n'existe pas on en créer un
		if(!file.exists()) {
			try {
				file.createNewFile();
			}catch(IOException e) {
				System.err.println("Le fichier n'a pas pu être créer");
				System.exit(0);
			}
		}
		
	    try {
	      // Créer un écrivain pour écrire dans le fichier
	      FileWriter writer = new FileWriter(file);
	      BufferedWriter bw = new BufferedWriter(writer); // création d'un tampon pour optimiser
	      
	      // Écrire chaque élément de la liste dans le fichier, en ajoutant un saut de ligne à la fin de chaque élément
	      for (List<ArgumentNoeud> item : sortie) {
	    	  for(ArgumentNoeud arg : item) {
	    		  bw.write("argument("+arg.getNomArgument()+").\n");
	    	  }
	        
	      }
	      
	      System.out.println("La liste de solution à bien été écrit dans le fichier : " + file.getName() + "\n");
	      
	      bw.close();
	      writer.close();
	    } catch (IOException e) {
	    	System.err.println("Le fichier n'a pas pu être lu correctement");
	    	System.exit(0);
	    }
	  }
	
	/**
	 * Génère toutes les combinaisons possibles de manière croissante. Les doublons sont supprimés.
	 * @return la liste de toutes les combinaisons possible du graphe.
	 */
    public List<List<ArgumentNoeud>> genererCombinaisons() {
    	
    	/**
		 * Liste des arguments du graphe
		 */
		ArrayList<ArgumentNoeud> listeArguments = new ArrayList<ArgumentNoeud>();
		for(ArgumentNoeud noeud : graphe.getGraphMap().keySet()) {
			listeArguments.add(noeud);
		}
    	
    	List<List<ArgumentNoeud>> combinaisons = new ArrayList<>();

    	/**
    	 * Ajouter l'ensemble vide en premier
    	 */
    	combinaisons.add(new ArrayList<>());
    	
        // Ajouter tous les objets individuellement à la liste des combinaisons
        for (ArgumentNoeud arg : listeArguments) {
            List<ArgumentNoeud> combination = new ArrayList<>();
            combination.add(arg);
            combinaisons.add(combination);
        }

        // Répéter jusqu'à ce qu'il n'y ait plus de combinaisons à ajouter
        boolean manqueCombinaisons = true;
        boolean dernier = false;
        while (manqueCombinaisons) {
            manqueCombinaisons = false;

            for (int i = 0; i < combinaisons.size(); i++) {
                List<ArgumentNoeud> uneCombinaison = combinaisons.get(i);

                for (ArgumentNoeud arg : listeArguments) {
                	
                    if (!uneCombinaison.contains(arg)) {
                        List<ArgumentNoeud> nouvCombinaison = new ArrayList<>(uneCombinaison);
                        nouvCombinaison.add(arg);
                        

                        if (!combinaisons.contains(nouvCombinaison) && dernier == false) {
                        	dernier = false;
                        	combinaisons.add(nouvCombinaison);

                            manqueCombinaisons = true;
                            
                            if(nouvCombinaison.size() == listeArguments.size()) {
                            	dernier = true;
                            }
                        }
                    }
                }
        		supprimerDoublons(combinaisons);

        		
            }
            
        }
        
        
        return combinaisons;
    }
	
    /**
     * Calcule toutes les solutions admissibles possibles du graphe représenté.
     * @return L'ensemble de toutes les solutions admissibles possibles
     */
	public List<List<ArgumentNoeud>> calculerSolutionsAdmissibles(){
		List<List<ArgumentNoeud>> listeDesCombinaisons = genererCombinaisons();
		
		List<List<ArgumentNoeud>> res = new ArrayList<>();
		
		for(List<ArgumentNoeud> ensemble : listeDesCombinaisons) {
			SolutionSimple solution = new SolutionSimple(ensemble,graphe);

			if(solution.solutionAdmissibleNew(false)) {
				res.add(ensemble);
			}
		}
		
		return res;
		
		
	}
	
	 /**
     * Calcule toutes les solutions préférées possibles du graphe représenté.
     * @return L'ensemble de toutes les solutions préférées possibles
     */
	public List<List<ArgumentNoeud>>  calculerSolutionsPreferees (List<List<ArgumentNoeud>> solutionsAdmissibles) {

        List<List<ArgumentNoeud>> sol_pref = new ArrayList<>();
        
        
        for( int i=0;i<solutionsAdmissibles.size();i++) {
            
            List<ArgumentNoeud> element = solutionsAdmissibles.get(i);
            
            boolean est_contenu = true;
            
            for( int j=0;j<solutionsAdmissibles.size();j++) {
                if(j!=i) {
                    if(solutionsAdmissibles.get(j).containsAll(element)) {
                        est_contenu=false;
                    }
                }
            }
            
            if(est_contenu==true) {
                sol_pref.add(element);
            }
    
        }
        
    return sol_pref;  
    
	}
	
	/**
	 * Supprime tous les doublons d'un ensemble de liste, de sorte que chaque combinaison de liste soit unique.
	 * @param listeDeListes un ensemble de liste
	 * @return un ensemble de liste d'arguments
	 */
	private List<List<ArgumentNoeud>> supprimerDoublons(List<List<ArgumentNoeud>> listeDeListes){
		

		for(int i = 0;i<listeDeListes.size()-1;i++) {
			for(int j = 0;j<listeDeListes.size();j++) {
				if((listeDeListes.get(i).size()!= 0) && (listeDeListes.get(j).size() != 0) && (listeDeListes.get(i).size() == listeDeListes.get(j).size())) {

					if(!listeDeListes.get(i).equals(listeDeListes.get(j))) {
						if(listeDeListes.get(i).containsAll(listeDeListes.get(j))) {
							listeDeListes.remove(j);
						}
					
					}
				}
			}
		}
		
		return listeDeListes;
	}
	
}
