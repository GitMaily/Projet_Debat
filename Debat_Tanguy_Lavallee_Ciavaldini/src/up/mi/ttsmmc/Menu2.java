package up.mi.ttsmmc;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Menu2 {
	
	

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
		
		//String fileName = args[0]+File.separator+args[1];
		 
		
		System.out.println("* * * * * Représentation du graphe * * * * * ");
		ListeAdjacence graphe = new ListeAdjacence();
		graphe.extractArgument(args[0]);
		graphe.afficherGraphe();
		graphe.extractContradiction(args[0]);
		graphe.afficherGrapheAvecContradictions();
		
		graphe.afficherGraphe();
		
		
	   
		Scanner scanner = new Scanner(System.in);
	
		RechercheSolution recherche = new RechercheSolution(graphe);
		int choice;
		List<List<ArgumentNoeud>> sortie =new ArrayList<>();//solution a afficher a la fin et dans la save qui sera actualiser a chaque affichage soit d admi soit de pref
		List<List<ArgumentNoeud>> entree_admi=new ArrayList<>();
		
		
		do {
		
			System.out.println("1) chercher une solution admissible");
			System.out.println("2) chercher une solution préférée");
			System.out.println("3) sauvegarder la solution");
			System.out.println("4) fin");
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Vous avez choisi l'option 1 : chercher une solution admissible");
				
				RechercheSolution sol_admi = new RechercheSolution(graphe);
				entree_admi = sol_admi.calculerSolutionsAdmissibles();
				
				Random random = new Random();

			    // Générez un index au hasard dans la plage des index de la liste
			    int index = random.nextInt(entree_admi.size());

			    // Récupérez l'élément à l'index généré au hasard
			    List<ArgumentNoeud> element = entree_admi.get(index);

			    // Affichez l'élément
			    System.out.println(element);
			  
			
			



				
				
				
				if(sortie.size()!=0) {
					sortie.clear();
					for(List<ArgumentNoeud> argu : entree_admi) {
						sortie.add(argu);
						}
				}
				
				
				
				break;
			case 2:
				System.out.println("Vous avez choisi l'option 2 : chercher une solution préférée");
				
				List<List<ArgumentNoeud>> entree_pref=new ArrayList<>();
				Solution_pref sp = new Solution_pref();
				entree_pref= sp.getSolPref(entree_admi);
				
				System.out.println(entree_pref);
				
				if(sortie.size()!=0) {
					sortie.clear();
					for(List<ArgumentNoeud> argu : entree_pref) {
						sortie.add(argu);
						}
				}
				
				break;
			case 3:
				System.out.println("Vous avez choisi l'option 3 : sauvegarder la solution");
				System.out.println("Solution a sauvegarder "+sortie.toString());
				System.out.println("Entrez le chemin du fichier : ");
				Scanner scanner2 = new Scanner(System.in);
				String lien = scanner2.nextLine();
				recherche.sauvegarderLaSolution(sortie, lien);
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
