package up.mi.ttsmmc;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
	public static void getMenu2(String args) throws Exception {
		
		//String cheminFichier = "C:\\Users\\samue\\Documents\\COURS\\Projet Debat\\pooa\\Debat_Tanguy_Lavallee_Ciavaldini\\src\\file1.txt";// remplacez par le chemin et le nom du fichier à lire
		//String fileNameWriter = "E:\\user\\Documents\\Documents descartes L3\\COURS\\POOA\\Projet debat\\pooa\\Debat_Tanguy_Lavallee_Ciavaldini\\src\\Newfile1.txt";// remplacez par le chemin et le nom du fichier à écrire
		
		String cheminFichier = args;
		 
		
		//System.out.println("* * * * * Représentation du graphe * * * * * ");
		ListeAdjacence graphe = new ListeAdjacence();
		graphe.lireFile(cheminFichier);
		graphe.extractArgument(cheminFichier);
		graphe.argumentExistePas(cheminFichier, graphe.argumentList(cheminFichier));
		graphe.afficherGraphe();
		graphe.extractContradiction(cheminFichier);
		graphe.afficherGrapheAvecContradictions();
		
		graphe.afficherGraphe();
		
		
	   
		Scanner sc = new Scanner(System.in);
	
		RechercheSolution recherche = new RechercheSolution(graphe);
		int choix = 0;
		List<List<ArgumentNoeud>> sortie =new ArrayList<>();//solution a afficher a la fin et dans la save qui sera actualiser a chaque affichage soit d admi soit de pref
		List<List<ArgumentNoeud>> entree_admi=new ArrayList<>();
		List<List<ArgumentNoeud>> entree_pref=new ArrayList<>();
		
		Random random = new Random();
		List<List<ArgumentNoeud>> randomAdmissible = new ArrayList<>();
		List<List<ArgumentNoeud>> randomPreferee = new ArrayList<>();
		int indexSolution = 0;

		boolean estUtilise = false;
		
		do {
		
			System.out.println("1) chercher une solution admissible");
			System.out.println("2) chercher une solution préférée");
			System.out.println("3) sauvegarder la solution");
			System.out.println("4) fin");
			
			
			try {
				choix =sc.nextInt();
			} catch (InputMismatchException e) {
		        System.out.println("La valeur entrée n'est pas un entier. Veuillez réessayer.\n");
		        sc.next();
			}
			

			switch (choix) {
			case 1:
				System.out.println("Vous avez choisi l'option 1 : chercher une solution admissible");
				
				if(entree_admi.isEmpty()) {
					//RechercheSolution solutionAdmissible = new RechercheSolution(graphe);
					entree_admi = recherche.calculerSolutionsAdmissibles();
				}
				
				/**
				 * Sélection aléatoire grâce au tirage sans remise
				 */
				
				 // Si la liste est vide, on la remplit à nouveau avec la liste complète des solutions admissibles ou préférées
		        if (randomAdmissible.isEmpty()) {
		        	
		        	randomAdmissible.addAll(entree_admi);
		        }
		        
		        indexSolution = random.nextInt(randomAdmissible.size());
		        List<ArgumentNoeud> solutionAdmissible = randomAdmissible.get(indexSolution);
		        randomAdmissible.remove(indexSolution);
				
			    System.out.println(solutionAdmissible);

				sortie.clear();
				sortie.add(solutionAdmissible);
				estUtilise = true;
				
				break;
			case 2:
				System.out.println("Vous avez choisi l'option 2 : chercher une solution préférée");
				
				
				if(entree_admi.isEmpty()) {
					entree_admi = recherche.calculerSolutionsAdmissibles();

				}
				if(entree_pref.isEmpty()) {
					//SolutionPreferee sp = new SolutionPreferee();
					entree_pref= recherche.calculerSolutionsPreferees(entree_admi);
				}
				/**
				 * Sélection aléatoire grâce au tirage sans remise
				 */
				
				 // Si la liste est vide, on la remplit à nouveau avec la liste complète des solutions admissibles ou préférées
		        if (randomPreferee.isEmpty()) {
		        	
		        	randomPreferee.addAll(entree_pref);
		        }
		        
		        indexSolution = random.nextInt(randomPreferee.size());
		        List<ArgumentNoeud> solutionPreferee = randomPreferee.get(indexSolution);
		        randomPreferee.remove(indexSolution);
				
			    System.out.println(solutionPreferee);
				
				sortie.clear();
				sortie.add(solutionPreferee);
			
				estUtilise = true;
					
				break;
			case 3:
				if(estUtilise) {
					System.out.println("Vous avez choisi l'option 3 : sauvegarder la solution");
					System.out.println("Solution a sauvegarder "+sortie.toString());
					System.out.println("Entrez le chemin du fichier : ");
					
					sc.nextLine();
					String chemin = sc.nextLine();
					
					recherche.sauvegarderLaSolution(sortie, chemin);
				}
				else {
					System.out.println("Il faut choisir l'option 1 ou 2 avant\n");
				}
				break;
				
			case 4:
				System.out.println("Vous avez choisi l'option 4 : fin");
				break;
			default:
				System.out.println("Choix non valide. Veuillez choisir une option entre 1 et 4.");
			}
		}while(choix != 4);
			sc.close();

	}
}
