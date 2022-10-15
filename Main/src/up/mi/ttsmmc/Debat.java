package up.mi.ttsmmc;

import java.util.ArrayList;
import java.util.Scanner;

public class Debat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("entrez le nombre d'argument n =");
		int n;
		Scanner sc = new Scanner(System.in);
		n =sc.nextInt();
		
		int choix;
		do {
			
			System.out.println("ajouter une contradiction tapez 1");
			System.out.println("fin tapez 2");
			choix =sc.nextInt();
			if (choix==1) {
				
				System.out.println(n);
				System.out.println("Entre quels arguments ajouter la contradiction ?");
				n--;
			}
			if(n==0) {
				System.out.println("plus de contradiction possible, le choix 2 es choisis par défaut");
				choix =2;
			}
			
			
		}while((choix!= 1 && choix !=2) || n!=0);
		
		
		
		if(choix==2) {
			ArrayList<Integer> E = new ArrayList<Integer>();
			do {
				
				System.out.println("ajouter un argument tapez 1");
				System.out.println("retirer un argument tapez 2");
				System.out.println("verifiez la solution tapez 3");
				System.out.println("fin tapez 4");
				choix =sc.nextInt();
				int arg;
				switch(choix) {
				case 1 :  
						System.out.println("veuillez entrez un argument arg = ");
						arg = sc.nextInt();
						if(E.contains(arg)==false) {
							System.out.println("ajout de l'argument dans la solution");
							E.add(arg);
						}
						else {
							System.out.println("Argument déja présent dans la solution");
						}
					break;
				case 2 : System.out.println("Quelle argument voulez vous retirez ? ");
						
						arg = sc.nextInt();
						if(E.contains(arg)==false) {
							System.out.println("Argument non contenu dans la solution");
						}
						else {
							System.out.println("Argument trouvé dans la solution, suppression de l'argument");
							E.remove(arg);
						}
						break;
				case 3 :System.out.println("verification de la solution");
				/*
				 * if ( solution E  valide){
				 * 	affiche("solution  valide)
				 * }
				 * else{
				 * affiche("solution non valide car INSERER JUSTIFICATION");
				 * (par exemple, en indiquant deux arguments dans l’ensemble qui se contredisent, ou en identifiant un argument dans l’ensemble qui n’est pas défendu contre tous ses contradicteurs)
				 * 
				 * }
				 */
				}
				
			}while(choix!= 1 && choix !=2 && choix!=3 && choix !=4);
			
		}
		
		sc.close();		
	}

}
