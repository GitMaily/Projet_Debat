package up.mi.ttsmmc;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Correspond au menu de la phase 2 du projet.
 * @author Thomas_Tanguy
 * @author Samuel_Lavallée
 * @author Maily_Ciavaldini
 * @version PHASE_2
 */
public class Menu2 {
	
	/**
	 * Lis un fichier texte et insère les données dans un graphe.
	 * @param graphe le graphe du débat
	 * @param cheminFichier le chemin du fichier texte
	 * @throws Exception une erreur de fichier se produit si le chemin du fichier n'existe pas. Le programme s'arrête avec un message pour avertir l'utilisateur.
	 */
	public static void lectureDeFichier(ListeAdjacence graphe, String cheminFichier) throws Exception {
		graphe.lireFile(cheminFichier);
		graphe.extractArgument(cheminFichier);
		graphe.argumentExistePas(cheminFichier, graphe.argumentList(cheminFichier));
		graphe.afficherGraphe();
		System.out.println();
		graphe.extractContradiction(cheminFichier);
		graphe.afficherGrapheAvecContradictions();
		
	}

	/**
	 * Appel au menu 2 du programme.
	 * @param args Le chemin du fichier que l'on veut lire
	 * @param sc le scanner unique
	 * @throws Exception une erreur de fichier se produit si le chemin du fichier n'existe pas. Le programme s'arrête avec un message pour avertir l'utilisateur.
	 */
	public static void getMenu2(String args, Scanner sc) throws Exception {
		
		/* Message d'accueil et présentation de la version */
		
		System.out.println("* * * * * Programme de résolution de débat (version PHASE_2) * * * * *");
		System.out.println("Ce menu permet de :");
		System.out.println("1) Utiliser un fichier pour représenter un graphe de débat. Possibilité de lire, et sauvegarder.");
		System.out.println("2) La recherche d'une solution admissible, ou préférée.");
		System.out.println();
		
		String cheminFichier = args;
		 
		
		ListeAdjacence graphe = new ListeAdjacence();
		
		lectureDeFichier(graphe, cheminFichier);
		
	
		RechercheSolution recherche = new RechercheSolution(graphe);
		int choix = 0;
		List<List<ArgumentNoeud>> sortie =new ArrayList<>();
		List<List<ArgumentNoeud>> entree_admi=new ArrayList<>();
		List<List<ArgumentNoeud>> entree_pref=new ArrayList<>();
		
		Random random = new Random();
		List<List<ArgumentNoeud>> randomAdmissible = new ArrayList<>();
		List<List<ArgumentNoeud>> randomPreferee = new ArrayList<>();
		int indexSolution = 0;

		boolean estUtilise = false;
		
		do {
		
			System.out.println("(1) Chercher une solution admissible");
			System.out.println("(2) Chercher une solution préférée");
			System.out.println("(3) Sauvegarder la solution");
			System.out.println("(4) Fin");
			
			
			try {
				choix =sc.nextInt();
			} catch (InputMismatchException e) {
		        System.out.println("La saisie n'est pas correcte, veuillez réessayer.\n");
		        sc.next();
			}
			

			switch (choix) {
			case 1:
				System.out.println("Le programme va maintenant vous proposer une solution admissible.");
				
				if(entree_admi.isEmpty()) {
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
		        
		        System.out.println("Voici une solution admissible du débat :");
		        afficherSolution(solutionAdmissible);

				sortie.clear();
				sortie.add(solutionAdmissible);
				estUtilise = true;
				
				break;
			case 2:
				System.out.println("Le programme va maintenant vous proposer une solution préférée.");
				
				
				if(entree_admi.isEmpty()) {
					entree_admi = recherche.calculerSolutionsAdmissibles();

				}
				if(entree_pref.isEmpty()) {
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
		        
		        System.out.println("Voici une solution préférée du débat :");
		        afficherSolution(solutionPreferee);
				
				sortie.clear();
				sortie.add(solutionPreferee);
			
				estUtilise = true;
					
				break;
			case 3:
				if(estUtilise) {
					System.out.println("Sauvegarde de la solution.");
					System.out.println("Entrez le chemin du fichier : ");
					sc.nextLine();
					String chemin = sc.nextLine();
					
					recherche.sauvegarderLaSolution(sortie, chemin);

				}
				else {
					System.out.println("Il faut choisir l'option 1 ou 2 avant.\n");
				}
				break;
				
			case 4:
				System.out.println("Fin du programme.");
				break;
			default:
				System.out.println("Choix non valide. Veuillez choisir une option entre 1 et 4.");
			}
		}while(choix != 4);

	}
	
	/**
	 * Affiche la solution proposée à l'utilisateur.
	 * @param solutionArguments une solution d'arguments
	 */
	private static void afficherSolution(List<ArgumentNoeud> solutionArguments) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0;i<solutionArguments.size();i++){
            sb.append(solutionArguments.get(i).getNomArgument());
            if(i < solutionArguments.size()-1) {
                sb.append(",");
            }
            
        }
        sb.append("\n");
        
        System.out.println(sb.toString());
    }
	
}
