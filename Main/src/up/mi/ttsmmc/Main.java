package up.mi.tsm;

import java.util.Scanner;

public class Main {

	public static void main(String []args) {
		//1) ajouter un argument;
		//2) retirer un argument;
		//3) vérifier la solution;
		//4) fin
		
		//1 : String nomArgument = Scanner.nextLine();
		//ArgumentNoeud arg = new ArgumentNoeud(nomArgument);
		
		/*
		 * Questions : Gestion des erreur avec des print, ou des exceptions ?
		 * Est-ce grave si l'ordre du graphMap est inversé?
		 * 
		 */
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Combien d'arguments voulez vous créer ?");
		int nbArguments = sc.nextInt();
		ListeAdjacence graphe = new ListeAdjacence(nbArguments);
		graphe.afficherGraphe();
		int choix;
		do {
			
			System.out.println("(1) Ajouter une contradiction;");
			System.out.println("(2) Fin.");
			choix =sc.nextInt();
			
			switch(choix) {
			case 1: 
				System.out.println("Veuillez saisir l'argument contradicteur.");
				String nomArg1 = sc.next();
				
				System.out.println("Veuillez saisir l'argument contredit.");
				String nomArg2 = sc.next();

				graphe.ajouterContradiction(nomArg1, nomArg2);
				graphe.afficherGrapheAvecContradictions();

				break;
				
			case 2:
				System.out.println("Vous avez terminé de représenter le graphe.");
				System.out.println("Vous allez à présent proposer une solution au problème. On appelera l'ensemble E la solution.");
				
				break;
			}
			
			
			
		}while((choix== 1 && choix !=2));
		menuSolutions(sc);
		
		//String nomArgument = sc.nextLine();
		//ArgumentNoeud arg = new ArgumentNoeud(nomArgument);

		//graphe.ajouterArgument(arg);
		//graphe.retirerArgument(arg);
		
	}
	
	public static void menuSolutions(Scanner sc) {
		SolutionSimple E = new SolutionSimple();
		int choix;

		do {
			System.out.println("(1) Ajouter un argument;");
			System.out.println("(2) Retirer un argument;");
			System.out.println("(3) Vérifier la solution;");
			System.out.println("(4) Fin.");
			choix  = sc.nextInt();


			
			switch(choix) {
			case 1: System.out.println("Veuillez saisir le nom de l'argument que vous souhaitez ajouter dans la solution");
					String argSol = sc.next();
					E.ajouterArgumentSolution(argSol);
					break;
			
			case 2: System.out.println("Veuillez saisir le nom de l'argument que vous souhaitez retirer de la solution");
					String argRetirer = sc.next();
					E.retirerArgumentSolution(argRetirer);
					break;
					
			case 3: System.out.println("Vérification de la solution");
					/*
					 * A FAIRE
					 */
					break;
					
			case 4: E.afficherSolution();
					/*
					 * Vérification si solution admissible ou non A FAIRE
					 */
					break;

			

			}
		}while(choix != 4);
		
	

		
	}
}
