package up.mi.tsm;

import java.util.ArrayList;
import java.util.List;

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
	
	public SolutionSimple() {
		graphe = new ListeAdjacence(16);
		listeSolutions = new ArrayList<ArgumentNoeud>();
	}
	
	/**
	 * Ajoute un argument dans la solution potentielle E après avoir vérifié qu'il n'est pas déjà présent
	 * @param nomArg L'argument à ajouter dans E
	 * @return false si l'argument apparait déjà dans la solution potentielle E, true sinon
	 */
	public boolean ajouterArgumentSolution(String nomArg) {
		
		boolean duplicate = false;
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
				}
			}
			listeSolutions.add(ajoutArg);
	
		}
		
		return true;
	}
	
	/**
	 * Retire un argument de E après avoir vérifié son existance
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
	
	public void afficherSolution() {
		System.out.println("Affichage de E :");
		for(ArgumentNoeud solu : listeSolutions) {
			System.out.println(solu.toString());
		}
	}

	
	public List<ArgumentNoeud> getListeSolutions() {
		return listeSolutions;
	}

	public void setListeSolutions(List<ArgumentNoeud> listeArguments) {
		this.listeSolutions = listeArguments;
	}
	
	
}
