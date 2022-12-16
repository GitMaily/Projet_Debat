package up.mi.ttsmmc;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente l'ensemble E des solutions simple.
 * (l'utilisateur va essayer de trouver une solution au problème)
 * @author Thomas_Tanguy
 * @author Samuel_Lavallée
 * @author Maily_Ciavaldini
 * @version PHASE_1
 */
public class SolutionSimple {
	
	/**
	 * Représente le graphe orienté
	 * @type ListeAdjacence
	 */
	private ListeAdjacence graphe; // Le graphe orienté
	/**
	 * Représente la liste des solutions
	 * @type une liste d'ArgumentNoeud
	 */
	private List<ArgumentNoeud> listeSolutions; // La liste des solutions
	
	/**
	 * Constructeur qui crée un arrayList vide pour entrer des arguments dans la solution E selon la graphe entré
	 * @param proposition 
	 * @param graphe Le graphe crée au préalable
	 */
	public SolutionSimple(ListeAdjacence graphe) {
		this.graphe = graphe;
		listeSolutions = new ArrayList<ArgumentNoeud>();
	}
	
	
	public SolutionSimple(List<ArgumentNoeud> proposition, ListeAdjacence graphe) {
		this.listeSolutions = proposition;
		this.graphe = graphe;
	}
	
	public SolutionSimple(ArrayList<ArgumentNoeud> listeArgumentsPossibles, ListeAdjacence graphe) {
		this.listeSolutions = listeArgumentsPossibles;
		this.graphe = graphe;
	}
	
	/**
	 * Ajoute un argument dans la solution potentielle E après avoir vérifié qu'il n'est pas déjà présent
	 * @param nomArg L'argument à ajouter dans E
	 * @return false si l'argument n'existe pas dans le graphe et si l'argument apparait déjà dans la solution potentielle E, true sinon
	 */
	public boolean ajouterArgumentSolution(String nomArg) {
		
		boolean duplicate = false;
		boolean trouve = false;
		ArgumentNoeud ajoutArg = null;
		
		// Vérifie si l'argument est déjà dans la liste
		for(int i =0;i<listeSolutions.size();i++) {
			if(listeSolutions.get(i).getNomArgument().equals(nomArg)) {
				System.out.println("Cet argument est déjà dans la solution potentielle E.");
				duplicate = true;
				return false;
			}
		}
		if(duplicate == false) {
			// On stocke l'argument dont le nom est nomArg
			for(ArgumentNoeud solu : graphe.getGraphMap().keySet()) {
				if(solu.getNomArgument().equals(nomArg)) {
					ajoutArg = solu;
					trouve = true;
				}
			}
			// Cas où l'argument n'existe pas dans le graphe
			if(trouve == false) {
				System.out.println("Cet argument n'existe pas.");
				return false;
			}
			listeSolutions.add(ajoutArg);
		}
		
		return true;
	}
	
	/**
	 * Retire un argument de E après avoir vérifié son existence.
	 * @param nomArg L'argument à retirer de E
	 * @return false si l'argument n'existe pas, true sinon
	 */
	public boolean retirerArgumentSolution(String nomArg) {
		ArgumentNoeud argu = null;

		// On stocke l'argument dont le nom est nomArg
		for(ArgumentNoeud solu : graphe.getGraphMap().keySet()) {
			if(solu.getNomArgument().equals(nomArg)) {
				argu = solu;
			}
		}
		
		// On vérifie si l'argument n'existe pas dans la liste
		if(!listeSolutions.contains(argu)) {
			System.out.println("Cet argument n'existe pas dans E.");
			return false;
		}
		else {
			// Cherche l'argument à retirer
			for(int i = 0;i<listeSolutions.size();i++) {
				if(listeSolutions.get(i).getNomArgument().equals(nomArg)) {
					listeSolutions.remove(i);
				}
			}
		}
		return true;
	}
	
	
	/**
	 * verifie si la liste des argument que contredit l'argument contradicteur contient un argument dans la liste
	 * dans le contexte : verifie si il existeun arc entre chaque argument dans la solution
	 * @return false si il y a une contradiction interne, sinon true
	 */
	private boolean saCasContraInterne() {
		for(ArgumentNoeud arg : listeSolutions) {
			for(int i = 0;i<listeSolutions.size();i++) {
				// Vérifie si il existe un arc entre chaque argument dans la solution
				// Plus techniquepent, vérifie si la liste des arguments que contredit l'argument contradicteur contient un argument dans la liste
				if(graphe.getGraphMap().get(arg).contains(listeSolutions.get(i))) {
					System.out.println("Contradication interne, la solution n'est pas admissible.");
					System.out.println("En effet, "+arg.getNomArgument()+" contredit "+listeSolutions.get(i).getNomArgument());
					return false;
				
				}
			}
		}
		return true;
	}
	
	/**
	 * Methode qui identifie quel argument contredit l'argument courant de la solution E en les plaçant dans une liste "cicles"
	 * @param listeCibles Une liste cibles contenant les argument qui contredit l'argument courant dans la solution E
	 * @param i indice de la boucle for
	 */
	private void ArgContraDansCible(ArrayList<ArgumentNoeud> listeCibles, int i) {
		for(ArgumentNoeud noeud : graphe.getGraphMap().keySet()) {
			for(ArgumentNoeud cible : graphe.getGraphMap().get(noeud)) {
				if(cible.equals(listeSolutions.get(i))) {
					listeCibles.add(noeud); // liste des arguments qui contredisent l'argument courant
				}
			}
		}	
	}
	
	/**
	 * Méthode qui identifie si l'un des arguments de la solution E défend l'argument courant de la solution E
	 * @param listeCibles Une liste cibles contenant les argument qui contredit l'argument courant dans la solution E
	 * @param defended le boolean pour savoir si un défendeur a été trouvé
	 * @param i indice de la boucle for
	 * @return false si aucun defendeur n'a été trouvé
	 */
	private boolean DefendUnAutreArgDansE(ArrayList<ArgumentNoeud> listeCibles, boolean defended, int i) {
		for(ArgumentNoeud arg : listeCibles) {
			for(int j = 0;j<listeSolutions.size();j++) {
				//System.out.println("Identification si le contradicteur "+arg.getNomArgument()+" est contredit par "+listeSolutions.get(j).getNomArgument());

				// Vérifie si la cible courante se trouve dans les arguments contredits par l'argument de la solution E
				if(graphe.getGraphMap().get(listeSolutions.get(j)).contains(arg)) {
					defended = true;
					System.out.println("Défense de "+listeSolutions.get(i).getNomArgument()+" : l'argument "+listeSolutions.get(j).getNomArgument()+" contredit l'argument "+arg.getNomArgument());
					
					j = listeSolutions.size(); // On sort de la boucle, l'argument est déjà défendu pour le contradicteur courant.
				}
				
				// On vérifie si on n'a effectivement pas trouvé de défendeur, donc non admissible
				if(j == listeSolutions.size()-1 && defended == false) {
					System.out.println("Aucune défense pour le contradicteur "+arg.getNomArgument());
					
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * MÊME METHODE QUE solutionAdmissible() MAIS DECOMPOSER EN PLUSIEURS METHODES
	 * Vérifie si la solution E est admissible.
	 * Un ensemble d'arguments E est une solution admissible si et seulement si :
	 * - il n'y a pas deux arguments dans E qui se contredisent;
	 * - pour tout argument a qui contredit un élément de E, il existe un élément de E qui contredit a (E se défend contre a).
	 * Afin de vérifier que la solution est admissible ou non ainsi que de fournir des explications,
	 * on va vérifier 4 cas : cas ensemble vide, cas contradiction interne, cas argument non défendu dans la solution, et cas tous les arguments de la solution ne sont contredits par personne
	 * @return true si la solution est admissible, false sinon
	 */
	public boolean solutionAdmissibleNew() {
		
		/* Cas ensemble vide, toujours admissible */
		
		if(listeSolutions.size() == 0) {
			System.out.println("La solution E est vide.");
			return true;
		}
		
		/* Cas contradiction interne */
	
		if(saCasContraInterne() == false)
			return false;
		
		
		/* Cas argument non défendu dans la solution */
		
		
		
		int personne = 0; // Va servir a compter le nombre d'arguments dans la solution qui ne sont contredits par personne
		boolean defended = false;
		ArrayList<ArgumentNoeud> listeCibles = new ArrayList<ArgumentNoeud>();
		
		// On identifie un argument dans l’ensemble qui n’est pas défendu contre tous ses contradicteurs
		// NB : L'_argument courant_ correspond à l'argument qu'on itère dans la liste des solutions, donc i.
		for(int i = 0;i<listeSolutions.size();i++) { 
			
			// On identifie qui est-ce qui contredit l'argument courant de la solution E, en les mettant dans une liste "cibles"
			ArgContraDansCible(listeCibles, i);
		
			// Affichage de la liste des arguments qui attaquent (contredisent) l'argument courant de la solution E
			StringBuilder bb = new StringBuilder();
			
			if(listeCibles.size() != 0) {
				
				for(int cibles = 0; cibles < listeCibles.size();cibles++) {
					bb.append(listeCibles.get(cibles).getNomArgument().toString());
					if(cibles != listeCibles.size()-1) {
						bb.append(", ");
					}
				}
				System.out.println(listeSolutions.get(i).getNomArgument()+" est attaqué par les arguments "+bb.toString());
			}
			else {
				personne++; // La liste est vide, aucune contradiction pour l'argument courant.
			}
			
			// On identifie si l'un des arguments de la solution E défend l'argument courant de la solution E
			if(DefendUnAutreArgDansE(listeCibles, defended, i) == false)
				return false;
			
			listeCibles.clear(); // On vide la liste pour entrer les prochains arguments qui contredisent l'argument courant
		}
		
		/* Cas où les arguments de la solution ne sont contredits par personne */
		
		if(personne == listeSolutions.size()) {
			System.out.println("Les arguments ne sont contredits par personne.");
		}
		
		return true;
	}
	
	/**
	 * Vérifie si la solution E est admissible.
	 * Un ensemble d'arguments E est une solution admissible si et seulement si :
	 * - il n'y a pas deux arguments dans E qui se contredisent;
	 * - pour tout argument a qui contredit un élément de E, il existe un élément de E qui contredit a (E se défend contre a).
	 * Afin de vérifier que la solution est admissible ou non ainsi que de fournir des explications,
	 * on va vérifier 4 cas : cas ensemble vide, cas contradiction interne, cas argument non défendu dans la solution, et cas tous les arguments de la solution ne sont contredits par personne
	 * @return true si la solution est admissible, false sinon
	 */
	public boolean solutionAdmissible() {
		
		/* Cas ensemble vide, toujours admissible */
		
		if(listeSolutions.size() == 0) {
			System.out.println("La solution E est vide.");
			return true;
		}
		
		/* Cas contradiction interne */
	
		
		for(ArgumentNoeud arg : listeSolutions) {
			for(int i = 0;i<listeSolutions.size();i++) {
				// Vérifie si il existe un arc entre chaque argument dans la solution
				// Plus techniquepent, vérifie si la liste des arguments que contredit l'argument contradicteur contient un argument dans la liste
				//System.out.println(graphe.getGraphMap().get(arg).toString()+" contains?"+listeSolutions.get(i).toString());
				
				if(graphe.getGraphMap().get(arg).contains(listeSolutions.get(i))) {
					System.out.println("Contradication interne, la solution n'est pas admissible.");
					System.out.println("En effet, "+arg.getNomArgument()+" contredit "+listeSolutions.get(i).getNomArgument());
					return false;
				}
			}
		}
		
		/* Cas argument non défendu dans la solution */
		
		int personne = 0; // Va servir a compter le nombre d'arguments dans la solution qui ne sont contredits par personne
		boolean defended = false;
		ArrayList<ArgumentNoeud> listeCibles = new ArrayList<ArgumentNoeud>();
		
		// On identifie un argument dans l’ensemble qui n’est pas défendu contre tous ses contradicteurs
		// NB : L'_argument courant_ correspond à l'argument qu'on itère dans la liste des solutions, donc i.
		for(int i = 0;i<listeSolutions.size();i++) { 
			
			// On identifie qui est-ce qui contredit l'argument courant de la solution E, en les mettant dans une liste "cibles"
			for(ArgumentNoeud noeud : graphe.getGraphMap().keySet()) {
				for(ArgumentNoeud cible : graphe.getGraphMap().get(noeud)) {
					if(cible.equals(listeSolutions.get(i))) {
						listeCibles.add(noeud); // liste des arguments qui contredisent l'argument courant
					}
				}
			}
		
			// Affichage de la liste des arguments qui attaquent (contredisent) l'argument courant de la solution E
			StringBuilder bb = new StringBuilder();
			if(listeCibles.size() != 0) {
				for(int cibles = 0; cibles < listeCibles.size();cibles++) {
					bb.append(listeCibles.get(cibles).getNomArgument().toString());
					if(cibles != listeCibles.size()-1) {
						bb.append(", ");
					}
				}
				System.out.println(listeSolutions.get(i).getNomArgument()+" est attaqué par les arguments "+bb.toString());
			}
			else {
				personne++; // La liste est vide, aucune contradiction pour l'argument courant.
			}
			
			// On identifie si l'un des arguments de la solution E défend l'argument courant de la solution E
			for(ArgumentNoeud arg : listeCibles) {
				for(int j = 0;j<listeSolutions.size();j++) {
					//System.out.println("Identification si le contradicteur "+arg.getNomArgument()+" est contredit par "+listeSolutions.get(j).getNomArgument());

					// Vérifie si la cible courante se trouve dans les arguments contredits par l'argument de la solution E
					if(graphe.getGraphMap().get(listeSolutions.get(j)).contains(arg)) {
						defended = true;
						System.out.println("Défense de "+listeSolutions.get(i).getNomArgument()+" : l'argument "+listeSolutions.get(j).getNomArgument()+" contredit l'argument "+arg.getNomArgument());
						
						j = listeSolutions.size(); // On sort de la boucle, l'argument est déjà défendu pour le contradicteur courant.
					}
					
					// On vérifie si on n'a effectivement pas trouvé de défendeur, donc non admissible
					if(j == listeSolutions.size()-1 && defended == false) {
						System.out.println("Aucune défense pour le contradicteur "+arg.getNomArgument());
						
						return false;
					}
				}
			}
			listeCibles.clear(); // On vide la liste pour entrer les prochains arguments qui contredisent l'argument courant
		}
		
		/* Cas où les arguments de la solution ne sont contredits par personne */
		
		if(personne == listeSolutions.size()) {
			System.out.println("Les arguments ne sont contredits par personne.");
		}
		
		return true;
	}
	
	
	
	
	/**
	 * Vérifie si la solution E est admissible ou non, sans aucune explications.
	 * @return true si la solution est admissible, false sinon
	 */
	public boolean solutionAdmissible2() {
		
		/* Cas ensemble vide, toujours admissible */
		
		if(listeSolutions.size() == 0) {
			return true;
		}

		/* Cas contradiction interne */

		for(ArgumentNoeud arg : listeSolutions) {
			for(int i = 0;i<listeSolutions.size();i++) {
				// Vérifie si il existe un arc entre chaque argument dans la solution
				// Plus techniquepent, vérifie si la liste des arguments que contredit l'argument contradicteur contient un argument dans la liste
				if(graphe.getGraphMap().get(arg).contains(listeSolutions.get(i))) {
					return false;
				}
			}
		}
		
		/* Cas argument non défendu dans la solution */
		
		boolean defended = false;
		ArrayList<ArgumentNoeud> listeCibles = new ArrayList<ArgumentNoeud>();
		
		// On identifie un argument dans l’ensemble qui n’est pas défendu contre tous ses contradicteurs
		// NB : L'_argument courant_ correspond à l'argument qu'on itère dans la liste des solutions, donc i.
		for(int i = 0;i<listeSolutions.size();i++) { 
			
			// On identifie qui est-ce qui contredit l'argument courant de la solution E, en les mettant dans une liste "cibles"
			for(ArgumentNoeud noeud : graphe.getGraphMap().keySet()) {
				for(ArgumentNoeud cible : graphe.getGraphMap().get(noeud)) {
					if(cible.equals(listeSolutions.get(i))) {
						listeCibles.add(noeud); // liste des arguments qui contredisent l'argument courant
					}
				}
			}
		
			// Affichage de la liste des arguments qui attaquent (contredisent) l'argument courant de la solution E
			StringBuilder bb = new StringBuilder();
			if(listeCibles.size() != 0) {
				for(int cibles = 0; cibles < listeCibles.size();cibles++) {
					bb.append(listeCibles.get(cibles).getNomArgument().toString());
					if(cibles != listeCibles.size()-1) {
						bb.append(", ");
					}
				}
			}
			
			// On identifie si l'un des arguments de la solution E défend l'argument courant de la solution E
			for(ArgumentNoeud arg : listeCibles) {
				defended = false;
				for(int j = 0;j<listeSolutions.size();j++) {
					// Vérifie si la cible courante se trouve dans les arguments contredits par l'argument de la solution E
					if(graphe.getGraphMap().get(listeSolutions.get(j)).contains(arg)) {
						defended = true;
						j = listeSolutions.size(); // On sort de la boucle, l'argument est déjà défendu pour le contradicteur courant.
					}
					
					// On vérifie si on n'a effectivement pas trouvé de défendeur, donc non admissible
					if(j == listeSolutions.size()-1 && defended == false) {						
						return false;
					}
				}
			}
			listeCibles.clear(); // On vide la liste pour entrer les prochains arguments qui contredisent l'argument courant
		}
		
		return true;
	}
	
	/**
	 * Affichage simple de la solution E.
	 */
	public void afficherSolution() {
		System.out.println("Affichage de la solution E :");
		for(ArgumentNoeud solu : listeSolutions) {
			System.out.print(solu.toString());
		}
	}

	/**
	 * Permet de get une la liste des solutions
	 * @return Une liste d'argumentNoeud
	 */
	public List<ArgumentNoeud> getListeSolutions() {
		return listeSolutions;
	}
	
	/**
	 * Permet de set la liste des solutions
	 * @param listeArguments la nouvelle liste des solution
	 */
	public void setListeSolutions(List<ArgumentNoeud> listeArguments) {
		this.listeSolutions = listeArguments;
	}
	
	
}
