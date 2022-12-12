package up.mi.ttsmmc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Cette classe va permettre de représenter le graphe orienté d'un débat. La méthode utilisée est celle de la liste d'adjacence, dans laquelle l'utilisation des HashMap est pertinente.
 * @author Thomas_Tanguy
 * @author Samuel_Lavallée
 * @author Maily_Ciavaldini
 * @version PHASE_1
 */
public class ListeAdjacence {
	
	/**-
	 * le graphe avec l'argument et sa liste d'argument qu'il contredit
	 */
	private Map <ArgumentNoeud,ArrayList<ArgumentNoeud>> graphMap;
	/**
	 * le nombre d'arguments en tout
	 */
	private int nbArguments;
	
	public ListeAdjacence() {
		graphMap = new HashMap<ArgumentNoeud,ArrayList<ArgumentNoeud>>();
	}

	/**
	 * Constructeur permettant l'initialisation d'un graphe dans un HashMap selon le nombre total d'argument. Utilisation de init().
	 * @param nbArguments Le nombre d'arguments total dans le débat
	 */
	public ListeAdjacence(int nbArguments) {
		this.nbArguments = nbArguments;
		graphMap = new HashMap<ArgumentNoeud,ArrayList<ArgumentNoeud>>(nbArguments);
		init();
	}
	
	/**
	 * Initialise le graphe avec nbArguments arguments, dont le nom est de Ax avec x allant de 1 jusqu'à nbArguments
	 */
	public void init() {
		String nomInit = "A";
		for(int i = 0;i<nbArguments;i++) {
			// A1, A2, ..., AnbArguments
			StringBuilder sb = new StringBuilder();
			sb.append(nomInit);
			sb.append(i+1);
			ArrayList<ArgumentNoeud> argumentsInit = new ArrayList<ArgumentNoeud>(); // ArrayList vide
			graphMap.put(new ArgumentNoeud(sb.toString()), argumentsInit);
			
		}
	}
	
	public void extractContradiction2(String filePath) {
		String line;
		ArrayList<ArgumentNoeud> argumentsArray;
		Pattern pattern = Pattern.compile("contradiction\\((.+),(.+)\\)");
		try {	
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			while((line = reader.readLine()) != null) {
				Matcher matcher = pattern.matcher(line);
				if(matcher.find()) {
					String element1 = matcher.group(1);
                    String element2 = matcher.group(2);
                    
                    ArgumentNoeud arg = new ArgumentNoeud(element1);
                    ArgumentNoeud arg2 = new ArgumentNoeud(element2);
					
					if(graphMap.containsKey(arg)) {
						argumentsArray = graphMap.get(arg);
						argumentsArray.add(arg2);
					}
					else {
						System.out.println("L'argument n'existe pas");
					}
				}
			}
			reader.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param filePath
	 */
	public void extractContradiction(String filePath) {
		String line;
		ArrayList<ArgumentNoeud> argumentsArray;
		try {	
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			while((line = reader.readLine()) != null) {
				if(line.contains("contradiction(")) {
					int start = line.indexOf("(");
					int end = line.indexOf(",");
					
					int start2 = line.indexOf(",");
					int end2 = line.indexOf(")");
					String argument1 = line.substring(start + 1, end);;
					String argument2 = line.substring(start2 + 1, end2);
					
					ArgumentNoeud arg = new ArgumentNoeud("("+argument1+")");
					ArgumentNoeud arg2 = new ArgumentNoeud("("+argument2+")");
					
					System.out.println(argument1);
					System.out.println(argument2);
					System.out.println("arg : " + arg.getNomArgument());
					Set<ArgumentNoeud> keys = graphMap.keySet();
					for (ArgumentNoeud key : keys) {
					    System.out.println(key);
					}
					if(graphMap.containsKey(arg)) {
						argumentsArray = graphMap.get(arg);
						argumentsArray.add(arg2);
					}
					else {
						System.out.println("L'argument n'existe pas");
					}
				}
			}
			reader.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Récupère tous les argument d'un fichier de la forme : "Argument(N)" et les met
	 * dans le graphe en initialisant une liste vide
	 * @param filePath le chemin du fichier
	 */
	public void extractArgument(String filePath) {
		String line;
		ArrayList<ArgumentNoeud> argumentsInit = new ArrayList<ArgumentNoeud>();
		try {
			// on créer un bufferReader qui va lire les lignes dans le fichier
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			while((line = reader.readLine())!= null) {
				// recherchez le mot "Argument(" dans chaque ligne
				int index = line.indexOf("argument(");
				if(index != -1) {
					//si le mot est présent, récupérer la chaine entre les parenthèses
					int start = index + "argument(".length();
					int end = line.indexOf(")", start);
					String argument = line.substring(start, end);
					graphMap.put(new ArgumentNoeud(argument), argumentsInit);
					}
				
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * get le graphe avec l'argument et sa liste d'argument qu'il contredit
	 * @return graphMap : correspond au graphe avec l'argument et sa liste d'argument qu'il contredit
	 */
	public Map <ArgumentNoeud,ArrayList<ArgumentNoeud>> getGraphMap() {
		return graphMap;
	}
	
	/**
	 * set le graphe avec l'argument et sa liste d'argument qu'il contredit
	 * @param graphMap le nouveau graph avec l'argument et sa liste d'argument qu'il contredit
	 */
	public void setGraphMap(Map <ArgumentNoeud,ArrayList<ArgumentNoeud>> graphMap) {
		this.graphMap = graphMap;
	}

	/**
	 * (non utilisée) Retire un argument dans le graphe. Au cas où nous voulions retirer un argument initial dans le graphe.
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
	 * Affichage du graphe simple sans les contradictions.
	 */
	public void afficherGraphe() {
		System.out.println("Affichage du graphe :");
		for(ArgumentNoeud noeud : graphMap.keySet()) {
			System.out.print(noeud.toString());
		}
		System.out.println();
	}
	
	/**
	 * Affichage du graphe avec les contradictions.
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
	
	/**
	 * get le nombre d'argument
	 * @return nbArguments : le nombre d'argument en tout
	 */
	public int getNbArguments() {
		return nbArguments;
	}
	/**
	 * set le nombre d'argmument
	 * @param nbArguments : le nombre d'argument en tout
	 */
	public void setNbArguments(int nbArguments) {
		this.nbArguments = nbArguments;
	}
	
}
