package up.mi.ttsmmc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListeAdjacence {
	
	private Map <ArgumentNoeud,ArrayList<ArgumentNoeud>> graphMap;
	private int nbArguments;

	public ListeAdjacence(int nbArguments) {
		this.nbArguments = nbArguments;
		graphMap = new HashMap<ArgumentNoeud,ArrayList<ArgumentNoeud>>(nbArguments);
		init();
		
	}
	
	/**
	 * Initialise le graphe orienté avec nbArguments arguments, dont le nom est de Ax avec x allant de 1 jusqu'à nbArguments
	 */
	public void init() {
		String nomInit = "A";
		for(int i = 0;i<nbArguments;i++) {
			// A1, A2, ..., AnbArguments
			StringBuilder sb = new StringBuilder();
			sb.append(nomInit);
			sb.append(i+1);
			//System.out.println(sb.toString());
			ArrayList<ArgumentNoeud> argumentsInit = new ArrayList<ArgumentNoeud>(); // ArrayList vide
			graphMap.put(new ArgumentNoeud(sb.toString()), argumentsInit);
			
			
		}
	}
	
	public Map <ArgumentNoeud,ArrayList<ArgumentNoeud>> getGraphMap() {
		return graphMap;
	}

	public void setGraphMap(Map <ArgumentNoeud,ArrayList<ArgumentNoeud>> graphMap) {
		this.graphMap = graphMap;
	}
	
	
	/**
	 * Ajoute un argument dans le graphe
	 * L'utilisateur aura, au préalable, rentré le nom de l'argument
	 * @param noeud L'argument à ajouter
	 */
	/*public void ajouterArgument(ArgumentNoeud noeud) {
		
	}*/
	
	
	
	/**
	 * Retire un argument dans le graphe
	 * @param noeud L'argument à retirer
	 */
	public void retirerArgument(ArgumentNoeud noeud) {
		ArrayList<ArgumentNoeud> listeArcs;
		if(graphMap.containsKey(noeud)) {
			listeArcs = graphMap.remove(noeud);
			System.out.println("Argument retiré avec succès ! Cet argument contredisait les arguments suivants :"+listeArcs.toString());
			
		}
		else {
			System.out.println("L'argument n'existe pas.");
		}
		
	}
	
	/**
	 * Ajoute une contradiction (= un arc) au graphe. 
	 * @param nomArg1 Le nom de l'argument contradicteur
	 * @param nomArg2 Le nom de l'argument contredit (cible)
	 */
	public void ajouterContradiction(String nomArg1, String nomArg2) {
		
		// On cherche l'argument contradicteur
		ArgumentNoeud argContradicteur = null;
		for(ArgumentNoeud solu : graphMap.keySet()) {
			if(solu.getNomArgument().equals(nomArg1)) {
				argContradicteur = solu;
			}
		}
		
		// On cherche l'argument cible
		ArgumentNoeud argCible = null;
		for(ArgumentNoeud solu : graphMap.keySet()) {
			if(solu.getNomArgument().equals(nomArg2)) {
				argCible = solu;
			}
		}
		
		// On ajoute le nouvel argument dans la liste des arguments contredits de l'argument contradicteur.
		ArrayList<ArgumentNoeud> listeArcs;
		if(graphMap.containsKey(argContradicteur)) {
			listeArcs = graphMap.get(argContradicteur);
		}
		else {
			listeArcs = new ArrayList<ArgumentNoeud>();
		}
		
		listeArcs.add(argCible);
		
		
	}
	
	/**
	 * Affichage du graphe simple sans les contradictions
	 */
	public void afficherGraphe() {
		System.out.println("Affichage du graphe :");
		for(ArgumentNoeud noeud : graphMap.keySet()) {
			System.out.print(noeud.toString());
		}
		System.out.println();
	}
	
	
	/**
	 * Affichage du graphe avec les contradictions
	 */
	public void afficherGrapheAvecContradictions() {
		System.out.println("Affichage du graphe avec les contradictions :");
		for(ArgumentNoeud noeud : graphMap.keySet()) {
			for(ArgumentNoeud cibles : graphMap.get(noeud)) {
				System.out.println(noeud.toString()+"-->"+cibles.toString());

			}
		}
		System.out.println();
	}
	
	public int getNbArguments() {
		return nbArguments;
	}

	public void setNbArguments(int nbArguments) {
		this.nbArguments = nbArguments;
	}
	
}
