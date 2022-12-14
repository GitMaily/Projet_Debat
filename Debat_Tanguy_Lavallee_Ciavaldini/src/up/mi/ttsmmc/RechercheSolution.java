package up.mi.ttsmmc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RechercheSolution{
	
	HashMap<ArgumentNoeud,ArrayList<ArrayList<ArgumentNoeud>>> ensemblesPossibles;
	
	
	private ListeAdjacence graphe;
	private ArrayList<ArgumentNoeud> proposition;
	private ArrayList<ArrayList<ArgumentNoeud>> listePropositions;
	
	public RechercheSolution(ListeAdjacence graphe) {
		this.graphe = graphe;
		ensemblesPossibles = new HashMap<ArgumentNoeud,ArrayList<ArrayList<ArgumentNoeud>>>();
		
		
		proposition = new ArrayList<ArgumentNoeud>();
		listePropositions = new ArrayList<ArrayList<ArgumentNoeud>>();

	}
	
	/*public HashMap<ArgumentNoeud,ArrayList<ArgumentNoeud>> enumerationEnsemblesPossibles(){
		
		
		for(ArgumentNoeud noeud : graphe.getGraphMap().keySet()) {
			ArrayList<ArgumentNoeud> combinaisonsPossibles = new ArrayList<ArgumentNoeud>();
			ensemblesPossibles.put(noeud, combinaisonsPossibles);
		}
		
		boolean verif = false;
		for(ArgumentNoeud arg1 : ensemblesPossibles.keySet()) {

			for(ArgumentNoeud arg2 : ensemblesPossibles.keySet()) {
				if(arg1.getNomArgument().equals(arg2.getNomArgument())) {
					ensemblesPossibles.get(arg1).add(arg2);
					SolutionSimple solution = new SolutionSimple(ensemblesPossibles.get(arg1),graphe);
					verif = solution.solutionAdmissible();
					
					if(verif == false) {
						//solution.retirerArgumentSolution(arg2.getNomArgument());
					}
					else {
						for(ArgumentNoeud arg : ensemblesPossibles.get(arg1)) {
							proposition.add(arg);
							listePropositions.add(new ArrayList<ArgumentNoeud>());

						}
					}
				}
				else {
					System.out.println("On ne veut pas ajouter lui-même");
				}
			}
		}
		
		System.out.println(ensemblesPossibles);
		return ensemblesPossibles;
	}*/
	
	public void afficherListePropositions() {
		for(int i =0;i<listePropositions.size();i++) {
			listePropositions.get(i).toString();
		}
	}
	
	public void enumeration() {
		ArrayList<ArrayList<ArgumentNoeud>> combinaisonsPossibles;
		for(ArgumentNoeud noeud : graphe.getGraphMap().keySet()) {
			ArrayList<ArgumentNoeud> listeInterne = new ArrayList<ArgumentNoeud>();

			combinaisonsPossibles = new ArrayList<ArrayList<ArgumentNoeud>>();
			//combinaisonsPossibles.add(listeInterne);
			ensemblesPossibles.put(noeud, combinaisonsPossibles);
			
			/*for(int i = 0;i<combinaisonsPossibles.size();i++) {
				ArrayList<ArgumentNoeud> listeInterne = new ArrayList<ArgumentNoeud>();
				combinaisonsPossibles.add(listeInterne);
			}*/
		}
		
		int count = 0;
		ArrayList<ArgumentNoeud> listePrecedente;
		
		/*for(ArgumentNoeud arg1 : ensemblesPossibles.keySet()) {
			for(ArgumentNoeud arg2 : ensemblesPossibles.keySet()) {
				listePrecedente = ensemblesPossibles.get(arg1).get(count);
				ensemblesPossibles.get(arg1).add(listePrecedente);
				
			}
		}*/
		
		
		
		
		
		/*for(ArgumentNoeud arg1 : ensemblesPossibles.keySet()) {
			for(int i = 0;i<ensemblesPossibles.size();i++) {
				ArrayList<ArgumentNoeud> listeInterne = new ArrayList<ArgumentNoeud>();
				ensemblesPossibles.get(arg1).add(listeInterne);
				listeInterne.add(arg1);
				
			}
			
			count++;

		}*/
		

		
		
		System.out.println(ensemblesPossibles.toString());
		
		
		/*for(ArgumentNoeud noeud : ensemblesPossibles.keySet()) {
			listePropositions.add()
		}*/
		//listePropositions = ensemblesPossibles;
	}
	
	public ArrayList<ArrayList<ArgumentNoeud>> calculeSolutionsAdm(){

		
		ArrayList<ArrayList<ArgumentNoeud>> res = new ArrayList<ArrayList<ArgumentNoeud>>(); // = listeAdmissible
		
		for(ArrayList<ArgumentNoeud> proposition : listePropositions) {
			SolutionSimple solution = new SolutionSimple(proposition,graphe);
			
			System.out.println("***** Vérification de cet ensemble :"+proposition.toString()+" *****");
			
			if(solution.solutionAdmissible()) {
				System.out.println("trouvé un ensemble admissible !");
				res.add(proposition);
			}
		}
		
		
		
		return res;
	}
	
	
	
	
}
