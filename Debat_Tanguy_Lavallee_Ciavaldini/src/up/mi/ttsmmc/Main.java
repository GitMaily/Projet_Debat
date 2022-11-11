package up.mi.ttsmmc;

import java.util.Scanner;

/**
 * Ce programme permet de résoudre un débat.
 * @author Thomas_Tanguy
 * @author Samuel_Lavallée
 * @author Maily_Ciavaldini
 * @version PHASE_1
 */
public class Main {

	public static void main(String []args) {
		
		
		Scanner sc = new Scanner(System.in);
		
		/* Message d'accueil et présentation de la version */
		
		System.out.println("* * * * * Programme de résolution de débat (version PHASE_1) * * * * *");
		System.out.println("La version actuelle de ce programme permet de :");
		System.out.println("1) Représenter les arguments et les contradictions entre eux;");
		System.out.println("2) Vérifier si un ensemble d'arguments est une solution admissible ou non, avec des explications.");
		System.out.println();
		
		/* Réprésentation du graphe */
		System.out.println("* * * * * Représentation du graphe * * * * * ");
		System.out.println("Combien d'arguments possède le débat ?");
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
				String nomArg1 = "";
				String nomArg2 = "";
				
				do {
					System.out.println("Veuillez saisir le nom de l'argument contradicteur.");
					nomArg1 = sc.next().toUpperCase();
					
					System.out.println("Veuillez saisir le nom de l'argument contredit.");
					nomArg2 = sc.next().toUpperCase();
					
					if(nomArg1.equals(nomArg2)) {
						System.out.println("Un argument ne peut pas se contredire soi-même. Veuillez réessayer");
					}
					
				}while(nomArg1.equals(nomArg2));
				
				graphe.ajouterContradiction(nomArg1, nomArg2);
				graphe.afficherGrapheAvecContradictions();

				break;
				
			case 2:
				System.out.println("Vous avez terminé de représenter le graphe.");
				System.out.println("Vous allez à présent proposer une solution au problème.");
				break;
				
			default: System.out.println("Ce n'est pas un choix valide, veuillez réessayer.");
				break;
	
			}
			
		}while((choix== 1 || choix !=2));
		
		/* L'utilisateur propose une solution potentielle E*/
		
		menuSolutions(sc, graphe);
		
		sc.close();
		
	}
	
	/**
	 * Menu pour proposer une solution potentielle E.
	 * @param sc Le Scanner
	 * @param graphe Le graphe
	 */
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
			switch(choix) {
			case 1: System.out.println("Veuillez saisir le nom de l'argument que vous souhaitez ajouter dans la solution");
					String argSol = sc.next().toUpperCase();
					E.ajouterArgumentSolution(argSol);
					
					E.afficherSolution();
					System.out.println();

					break;
			
			case 2: System.out.println("Veuillez saisir le nom de l'argument que vous souhaitez retirer de la solution");
					String argRetirer = sc.next().toUpperCase();
					E.retirerArgumentSolution(argRetirer);
					E.afficherSolution();
					System.out.println();

					break;
					
			case 3: System.out.println("/!\\ Vérification de la solution /!\\");
					estAdmissible = E.solutionAdmissible();
					System.out.println();
					if(estAdmissible) {
						System.out.println("La solution est donc admissible.");
					}
					else {
						System.out.println("La solution n'est donc pas admissible.");
					}
					E.afficherSolution();
					System.out.println();

					break;
					
			case 4: E.afficherSolution();
					System.out.println();
					estAdmissible = E.solutionAdmissible2();
					if(estAdmissible) {
						System.out.println("La solution est admissible.");
					}
					else {
						System.out.println("La solution n'est pas admissible.");
					}
					break;

			default: System.out.println("Ce n'est pas un choix valide, veuillez réessayer.");
					break;
			

			}
		}while(choix != 4);
	}
}
