package up.mi.ttsmmc;

import java.util.Scanner;

public class Main {

	public static void main(String []args) {
		
		/*
		 * Est-ce grave si l'ordre du graphMap est inversé?
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
				System.out.println("Veuillez saisir le nom de l'argument contradicteur.");
				String nomArg1 = sc.next().toUpperCase();
				
				System.out.println("Veuillez saisir le nom de l'argument contredit.");
				String nomArg2 = sc.next().toUpperCase();

				graphe.ajouterContradiction(nomArg1, nomArg2);
				graphe.afficherGrapheAvecContradictions();

				break;
				
			case 2:
				System.out.println("Vous avez terminé de représenter le graphe.");
				System.out.println("Vous allez à présent proposer une solution au problème. On appelera l'ensemble E la solution.");
				break;
			}
			
			
			
		}while((choix== 1 && choix !=2));
		menuSolutions(sc, graphe);
		
		//String nomArgument = sc.nextLine();
		//ArgumentNoeud arg = new ArgumentNoeud(nomArgument);

		//graphe.ajouterArgument(arg);
		//graphe.retirerArgument(arg);
		
	}
	
	public static void menuSolutions(Scanner sc, ListeAdjacence graphe) {
		SolutionSimple E = new SolutionSimple(graphe);
		int choix;

		do {
			System.out.println();
			System.out.println("(1) Ajouter un argument;");
			System.out.println("(2) Retirer un argument;");
			System.out.println("(3) Vérifier la solution;");
			System.out.println("(4) Fin.");
			choix  = sc.nextInt();


			boolean estAdmissible = false;
			//boolean duplicate = false;
			switch(choix) {
			case 1: System.out.println("Veuillez saisir le nom de l'argument que vous souhaitez ajouter dans la solution");
					String argSol = sc.next().toUpperCase();
					E.ajouterArgumentSolution(argSol);
					/*if(duplicate == false) {
						System.out.println("Cet argument se trouve déjà dans la solution E. La solution E n'est donc pas modifiée.");
					}*/
					E.afficherSolution();
					break;
			
			case 2: System.out.println("Veuillez saisir le nom de l'argument que vous souhaitez retirer de la solution");
					String argRetirer = sc.next().toUpperCase();
					E.retirerArgumentSolution(argRetirer);
					E.afficherSolution();
					break;
					
			case 3: System.out.println("Vérification de la solution");
					estAdmissible = E.solutionAdmissible();
					if(estAdmissible) {
						System.out.println("La solution est donc admissible.");
					}
					else {
						System.out.println("La solution n'est donc pas admissible.");
					}
					E.afficherSolution();

					break;
					
			case 4: E.afficherSolution();
					/*
					 * Vérification si solution admissible ou non A FAIRE
					 */
					break;

			default: System.out.println("Ce n'est pas un choix valide, veuillez réessayer.");
					break;
			

			}
		}while(choix != 4);
		
	

		
	}
}
