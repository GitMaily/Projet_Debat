package up.mi.ttsmmc;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

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
		
		//String fileName = "E:\\user\\Documents\\Documents descartes L3\\COURS\\POOA\\Projet debat\\pooa\\Debat_Tanguy_Lavallee_Ciavaldini\\src\\file1.txt";// remplacez par le chemin et le nom du fichier à lire
		//String fileNameWriter = "E:\\user\\Documents\\Documents descartes L3\\COURS\\POOA\\Projet debat\\pooa\\Debat_Tanguy_Lavallee_Ciavaldini\\src\\Newfile1.txt";// remplacez par le chemin et le nom du fichier à écrire
		
		String fileName = args[0]+File.separator+args[1];
		
		System.out.println("* * * * * Représentation du graphe * * * * * ");
		ListeAdjacence graphe = new ListeAdjacence();
		graphe.extractArgument(fileName);
		graphe.afficherGraphe();
		graphe.extractContradiction(fileName);
		graphe.afficherGrapheAvecContradictions();
		
		graphe.afficherGraphe();
		
		  /**
	     * à supprimer
	     */
	    ArrayList<ArgumentNoeud> list = new ArrayList<ArgumentNoeud>();
		list.add(new ArgumentNoeud("A1"));
		list.add(new ArgumentNoeud("A4"));
		list.add(new ArgumentNoeud("A3"));
		Scanner scanner = new Scanner(System.in);
	
		RechercheSolution recherche = new RechercheSolution(graphe);
		int choice;
		do {
		
			System.out.println("1) chercher une solution admissible");
			System.out.println("2) chercher une solution préférée");
			System.out.println("3) sauvegarder la solution");
			System.out.println("4) fin");
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Vous avez choisi l'option 1 : chercher une solution admissible");
				break;
			case 2:
				System.out.println("Vous avez choisi l'option 2 : chercher une solution préférée");
				break;
			case 3:
				System.out.println("Vous avez choisi l'option 3 : sauvegarder la solution");
				System.out.println("Entrez le chemin du fichier : ");
				Scanner scanner2 = new Scanner(System.in);
				String lien = scanner2.nextLine();
				recherche.sauvegarderLaSolution(list, lien);
				break;
			case 4:
				System.out.println("Vous avez choisi l'option 4 : fin");
				break;
			default:
				System.out.println("Choix non valide. Veuillez choisir une option entre 1 et 4.");
			}
		}while(choice != 4);
			scanner.close();
		
		
		
		
		//List<List<ArgumentNoeud>> ensemblesPossibles;

		
		//ensemblesPossibles = recherche.genererCombinaisons();
		//System.out.print(ensemblesPossibles);
		
		//System.out.println("Voici les ensembles admissibles qu'on devra proposer un par un : \n"+recherche.calculerSolutionsAdmissibles());
		
		
		
		
		
		/*List<List<ArgumentNoeud>> listeComb2 = recherche.combinaisons();
		System.out.println(listeComb2);
		ensemblesPossibles = recherche.calculerSolutionsAdmissibles2();
		
		System.out.println("Voici les ensembles admissibles qu'on devra proposer un par un : \n"+ensemblesPossibles.toString());
		*/
	}
}
