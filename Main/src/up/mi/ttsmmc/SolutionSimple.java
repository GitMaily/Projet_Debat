package up.mi.ttsmmc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

/**
 * Représente l'ensemble E des solutions simple 
 * (l'utilisateur peut essayer de trouver une solution au problème)
 * @author Thomas
 * @author Samuel
 * @author Maily
 */
public class SolutionSimple {

	private ListeAdjacence graphe;
	private List<ArgumentNoeud> listeSolutions;
	
	public SolutionSimple(ListeAdjacence graphe) {
		this.graphe = graphe;
		listeSolutions = new ArrayList<ArgumentNoeud>();
	}
	
	
	
	
	/**
	 * Ajoute un argument dans la solution potentielle E après avoir vérifié qu'il n'est pas déjà présent
	 * @param nomArg L'argument à ajouter dans E
	 * @return false si l'argument n'existe pas dans le graphe et si l'argument apparait déjà dans la solution potentielle E, true sinon
	 */
	public boolean ajouterArgumentSolution(String nomArg) {
		
		boolean duplicate = false;
		boolean trouve = false;
		ArgumentNoeud ajoutArg = null;
		
		
		// Vérifie si l'argument est déjà dans la liste
		for(int i =0;i<listeSolutions.size();i++) {
			if(listeSolutions.get(i).getNomArgument().equals(nomArg)) {
				System.out.println("Cet argument est déjà dans la solution potentielle E.");
				duplicate = true;
				return false;
			}
			
		}
		if(duplicate == false) {
			// On stocke l'argument dont le nom est nomArg
			for(ArgumentNoeud solu : graphe.getGraphMap().keySet()) {
				if(solu.getNomArgument().equals(nomArg)) {
					ajoutArg = solu;
					trouve = true;
				}
			}
			if(trouve == false) {
				System.out.println("Cet argument n'existe pas dans le graphe.");
				return false;
			}
			listeSolutions.add(ajoutArg);
	
		}
		
		
		return true;
	}
	
	/**
	 * Retire un argument de E après avoir vérifié son existance.
	 * @param nomArg L'argument à retirer de E
	 * @return false si l'argument n'existe pas, true sinon
	 */
	public boolean retirerArgumentSolution(String nomArg) {
		ArgumentNoeud argu = null;

		// On stocke l'argument dont le nom est nomArg
		for(ArgumentNoeud solu : graphe.getGraphMap().keySet()) {
			if(solu.getNomArgument().equals(nomArg)) {
				argu = solu;
			}
		}
		
		// On vérifie si l'argument n'existe pas dans la liste
		if(!listeSolutions.contains(argu)) {
			System.out.println("Cet argument n'existe pas dans E.");
			return false;
		}
		else {
			// Cherche l'argument à retirer
			for(int i = 0;i<listeSolutions.size();i++) {
				if(listeSolutions.get(i).getNomArgument().equals(nomArg)) {
					listeSolutions.remove(i);

				}
			}
		}
		return true;
	}
	
	
	/**
	 * Vérifie si la solution E est admissible.
	 * Un ensemble d'arguments E est une solution admissible si et seulement si :
	 * - il n'y a pas deux arguments dans E qui se contredisent;
	 * - pour tout argument a qui contredit un élément de E, il existe un élément de E qui contredit a. (E se défend contre a)
	 */
	public boolean solutionAdmissible() {
		// Cas ensemble vide, toujours admissible
		if(listeSolutions.size() == 0) {
			System.out.println("La solution E est vide, donc elle est admissible.");
			return true;
		}
		
		// Cas contradiction interne
		for(ArgumentNoeud arg : listeSolutions) {
			for(int i = 0;i<listeSolutions.size();i++) {
				// Vérifie si il existe un arc entre chaque argument dans la solution
				// Plus techniquepent, vérifie si la liste des arguments que contredit l'argument contradicteur contient un argument dans la liste
				if(graphe.getGraphMap().get(arg).contains(listeSolutions.get(i))) {
					System.out.println("Contradication interne, la solution n'est pas admissible.");
					System.out.println("En effet, "+arg.getNomArgument()+" contredit "+listeSolutions.get(i).getNomArgument());
					// estAdmissible = false;
					// i = listeSolutions.size()
					return false;
				}
		
			}
		}
		
		ArrayList<ArgumentNoeud> listeCibles = new ArrayList<ArgumentNoeud>();
		// Cas argument non défendu dans la solution
		// On identifie un argument dans l’ensemble qui n’est pas défendu contre tous ses contradicteurs
		/*for(int i = 0;i<listeSolutions.size();i++) {
			if(graphe.getGraphMap().containsValue(listeSolutions)) {
				
				
				
			}
		}*/
		boolean estAdmissible = false;
		boolean defended = false;
		// Identifier qui contredit A1
		for(int i = 0;i<listeSolutions.size();i++) { // i= 0 : A1
			//for(int j = 0;j<listeSolutions.size();j++) {
				for(ArgumentNoeud noeud : graphe.getGraphMap().keySet()) {
					for(ArgumentNoeud cible : graphe.getGraphMap().get(noeud)) {
						if(cible.equals(listeSolutions.get(i))) {
							listeCibles.add(noeud); // liste de qui contredit A1
						}
					}
				}
				
				// Identifier si l'un des arguments de la solution E défend A1
				//int count = 0;
				for(ArgumentNoeud arg : listeCibles) {
					for(int j = 0;j<listeSolutions.size();j++) {

						// Vérifie si la cible est contenue dans les values d'un des arguments de E
						if(graphe.getGraphMap().get(listeSolutions.get(j)).contains(arg)) {
							defended = true;
							System.out.println("L'argument "+listeSolutions.get(j).getNomArgument()+" contredit l'argument "+arg.getNomArgument());
							
							//count++;
							j = listeSolutions.size();
							//return true;
						}
						
						if(j == listeSolutions.size()-1 && defended == false) {
							System.out.println("Aucune défense pour le contradicteur "+arg.getNomArgument());
							
							return false;
						}
					}
				}
				
				if(defended == true) {
					estAdmissible = true;
					//return true;
				}
				listeCibles.clear();
				
	
			//}
		}
		
		return true;
	}
	
	
	public static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
	    Set<T> keys = new HashSet<T>();
	    for (Entry<T, E> entry : map.entrySet()) {
	        if (Objects.equals(value, entry.getValue())) {
	            keys.add(entry.getKey());
	        }
	    }
	    return keys;
	}
	
	/**
	 * Affichage simple de la solution E
	 */
	public void afficherSolution() {
		System.out.println("Affichage de la solution E :");
		for(ArgumentNoeud solu : listeSolutions) {
			System.out.print(solu.toString());
		}
	}

	
	public List<ArgumentNoeud> getListeSolutions() {
		return listeSolutions;
	}

	public void setListeSolutions(List<ArgumentNoeud> listeArguments) {
		this.listeSolutions = listeArguments;
	}
	
	
}
