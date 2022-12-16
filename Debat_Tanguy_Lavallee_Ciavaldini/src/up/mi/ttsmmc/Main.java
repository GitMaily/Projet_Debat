package up.mi.ttsmmc;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		/* Message d'accueil et présentation de la version */
		
		System.out.println("* * * * * Choisissez le menu auquelle vous souhaitez accèder * * * * *");
		int choix = 0;
		
		
		
		do {
			
			System.out.println("1- menu phase 1");
			System.out.println("2- menu phase 2");
			
			
			
			try {
				choix =sc.nextInt();
			} catch (InputMismatchException e) {
		        System.out.println("La valeur entrée n'est pas un entier. Veuillez réessayer.\n");
		        sc.next();
			}
			
			
			switch(choix) {
			case 1:System.out.println("Affichage du menu de la phase 1");
			Menu1.getMenu1();
			break;
			case 2:System.out.println("Affichage du menu de la phase 2");
			Menu2.getMenu2(args[0]);
			break;
			default:System.out.println("Choisissez entre le menu de la phase 1 ou 2");
			}
		}while(choix!= 1 || choix !=2);
		sc.close();

	}
	
	
		

}
