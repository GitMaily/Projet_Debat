package up.mi.ttsmmc;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Main2 {

	/**
	 * Ce programme permet de résoudre un débat.
	 * @author Thomas_Tanguy
	 * @author Samuel_Lavallée
	 * @author Maily_Ciavaldini
	 * @version PHASE_2
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		//String fileName = "E:\\user\\Documents\\Documents descartes L3\\COURS\\POOA\\Projet debat\\pooa\\Debat_Tanguy_Lavallee_Ciavaldini\\src\\file.txt"; // remplacez par le chemin et le nom du fichier à lire
		
		String fileName = args[0]+File.separator+args[1];
		
		System.out.println("* * * * * Représentation du graphe * * * * * ");
		ListeAdjacence graphe = new ListeAdjacence();
		graphe.extractArgument(fileName);
		graphe.afficherGraphe();
		graphe.extractContradiction(fileName);
		graphe.afficherGrapheAvecContradictions();
		
		graphe.afficherGraphe();
		
		RechercheSolution recherche = new RechercheSolution(graphe);
		
		ArrayList<ArrayList<ArgumentNoeud>> ensemblesPossibles;


		recherche.enumeration();
		ensemblesPossibles = recherche.calculeSolutionsAdm();
		
		System.out.println(ensemblesPossibles.toString());
		
		/*
		HashMap<ArgumentNoeud,ArrayList<ArgumentNoeud>> ensemblesPossibles2;
		ensemblesPossibles2 = recherche.enumerationEnsemblesPossibles();
		System.out.println("avec ensemblePossibles2"+ensemblesPossibles2.toString());
		*/
		
		
		/*System.out.println("Affichage de la liste des propositions");
		recherche.afficherListePropositions();*/
		//ArrayList<ArgumentNoeud> listeAdmissible = recherche.calculeSolutionsAdm();
	}
}
