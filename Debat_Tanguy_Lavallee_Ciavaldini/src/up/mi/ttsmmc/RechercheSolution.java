package up.mi.ttsmmc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RechercheSolution{
	
	HashMap<ArgumentNoeud,ArrayList<ArrayList<ArgumentNoeud>>> ensemblesPossibles;
	
	
	private ListeAdjacence graphe;
	private ArrayList<ArgumentNoeud> proposition;
	private List<ArrayList<ArgumentNoeud>> listePropositions;
	
	public RechercheSolution(ListeAdjacence graphe) {
		this.graphe = graphe;
		ensemblesPossibles = new HashMap<ArgumentNoeud,ArrayList<ArrayList<ArgumentNoeud>>>();
		
		
		proposition = new ArrayList<ArgumentNoeud>();
		listePropositions = new ArrayList<ArrayList<ArgumentNoeud>>();

	}
	
	public void afficherListePropositions() {
		for(int i =0;i<listePropositions.size();i++) {
			listePropositions.get(i).toString();
		}
	}
	/**
	 * cette méthode sauvegarde la liste de solution dans un fichier
	 * @param solution la liste de solution
	 * @param filePath le chemin du fichier
	 */
	public void sauvegarderLaSolution(ArrayList<ArgumentNoeud> solution, String filePath) {
	    
		// Créer un nouveau fichier en utilisant le chemin spécifié
	    
		File file = new File(filePath);
	    
		// On regarde si le fichier existe ou pas, si il n'existe pas on en créer un
		if(!file.exists()) {
			try {
				file.createNewFile();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	    try {
	      // Créer un écrivain pour écrire dans le fichier
	      FileWriter writer = new FileWriter(file);
	      
	      // Écrire chaque élément de la liste dans le fichier, en ajoutant un saut de ligne à la fin de chaque élément
	      for (ArgumentNoeud item : solution) {
	        writer.write("argument("+item.getNomArgument()+").\n");
	      }
	      
	      System.out.println("La liste de solution à bien été écrit dans le fichier : " + file.getName());
	      
	      // Fermer l'écrivain une fois que l'on a fini d'écrire dans le fichier
	      writer.close();
	      
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }
	
	// Cette méthode génère toutes les combinaisons possibles sans duplicata
    // et les ajoute à la liste des combinaisons
	
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
    	
        // Commencer par ajouter tous les objets individuellement à la liste des combinaisons
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

            // Pour chaque combinaison existante...
            for (int i = 0; i < combinaisons.size(); i++) {
                List<ArgumentNoeud> uneCombinaison = combinaisons.get(i);

                // ...ajouter tous les objets qui ne sont pas déjà dans la combinaison...
                for (ArgumentNoeud arg : listeArguments) {
                	
                    if (!uneCombinaison.contains(arg)) {
                        List<ArgumentNoeud> nouvCombinaison = new ArrayList<>(uneCombinaison);
                        nouvCombinaison.add(arg);
                        

                        // ...mais seulement si la nouvelle combinaison n'existe pas déjà
                        if (!combinaisons.contains(nouvCombinaison) && dernier == false) {
                        	dernier = false;
                        	combinaisons.add(nouvCombinaison);
                            
                            
                            manqueCombinaisons = true;
                            
                            if(nouvCombinaison.size() == listeArguments.size()) {
                            	//dernier = true;
                            }
                        }
                    }
                }
            }
        }
        
        supprimerDoublons(combinaisons);

        System.out.println("apres");

        for (List<ArgumentNoeud> combination : combinaisons) {
  		  System.out.println(combination);
  		}
        return combinaisons;
    }
	
	
	public List<List<ArgumentNoeud>> combinaisons() {
		
		/**
		 * Liste des arguments du graphe
		 */
		ArrayList<ArgumentNoeud> listeArguments = new ArrayList<ArgumentNoeud>();
		for(ArgumentNoeud noeud : graphe.getGraphMap().keySet()) {
			listeArguments.add(noeud);
		}
		
	    List<ArgumentNoeud> args = listeArguments;

	    //int count = 0;
	    
	    /**
	     * On génère toutes les combinaisons possibles de la liste des arguments
	     */
	    List<List<ArgumentNoeud>> combs = new ArrayList<>();
	    for (int i = 0; i < args.size(); i++) {
	      for (int j = i + 1; j <= args.size(); j++) {
	        List<ArgumentNoeud> subList = args.subList(i, j);
	        combs.add(subList);
	        
	      }
	      /*if(i != 0) {
	  	      for (int k = 0; k < count ; k++) {
	  	    	  args.get(k);
	  	    	List<ArgumentNoeud> subList2 = args.subList(k, count);
		        combs.add(subList2);
	  	      }
          }*/
	    }

	    // Affichage des combinaisons
	    for (List<ArgumentNoeud> c : combs) {
	      System.out.println(c);
	    }
	  return combs;
	}
	
	
	
	
	
	
	public List<List<ArgumentNoeud>> calculerSolutionsAdmissibles(){
		List<List<ArgumentNoeud>> listeDesCombinaisons = genererCombinaisons();
		
		List<List<ArgumentNoeud>> res = new ArrayList<>();
		
		for(List<ArgumentNoeud> ensemble : listeDesCombinaisons) {
			SolutionSimple solution = new SolutionSimple(ensemble,graphe);

			if(solution.solutionAdmissible2()) {
				res.add(ensemble);
			}
		}
		
		
		List<List<ArgumentNoeud>> ensemblesASupprimer = new ArrayList<>();

		
		boolean duplicata = false;
		//for(List<ArgumentNoeud> admissible : res) {
			
		
		supprimerDoublons(res);
		
		/*for(int i = 0;i<res.size();i++) {
				for(int j = 0;j<res.size();j++) {
					if((res.get(i).size()!= 0) && (res.get(j).size() != 0) && res.get(i).size() == res.get(j).size()) {

						if(!res.get(i).equals(res.get(j))) {
							if(res.get(i).containsAll(res.get(j))) {
								duplicata = true;
								//ensemblesASupprimer.add(res.get(i));
								res.remove(j);
						}
						
						
					}
				}
			}
		}*/
		
		
		return res;
		
		
	}
	
	
	private List<List<ArgumentNoeud>> supprimerDoublons(List<List<ArgumentNoeud>> listeDeListes){
		
		List<Integer> indiceDesEnsemblesASupprimer = new ArrayList<Integer>();

		
		for(int i = 0;i<listeDeListes.size();i++) {
			System.out.println(listeDeListes.size());
			System.out.println("index"+i);
			for(int j = 0;j<listeDeListes.size();j++) {
				if((listeDeListes.get(i).size()!= 0) && (listeDeListes.get(j).size() != 0) && (listeDeListes.get(i).size() == listeDeListes.get(j).size())) {

					if(!listeDeListes.get(i).equals(listeDeListes.get(j))) {
						if(listeDeListes.get(i).containsAll(listeDeListes.get(j))) {
							//duplicata = true;
							//ensemblesASupprimer.add(res.get(i));
							//indiceDesEnsemblesASupprimer.add(j);
							listeDeListes.remove(j);
					}
					
					
					}
				}
			}
		}
		
		return listeDeListes;
	}
	
	
	
}
