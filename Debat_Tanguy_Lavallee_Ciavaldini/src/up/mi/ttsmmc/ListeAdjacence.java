package up.mi.ttsmmc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Cette classe va permettre de représenter le graphe orienté d'un débat. La méthode utilisée est celle de la liste d'adjacence, dans laquelle l'utilisation des HashMap est pertinente.
 * elle permet aussi d'extraire d'un fichier les arguments et les contradiction pour les lire
 * @author Thomas_Tanguy
 * @author Samuel_Lavallée
 * @author Maily_Ciavaldini
 * @version PHASE_2
 */
public class ListeAdjacence {
	
	/**
	 * le graphe avec l'argument et sa liste d'argument qu'il contredit
	 */
	private Map <ArgumentNoeud,ArrayList<ArgumentNoeud>> graphMap;
	/**
	 * le nombre d'arguments en tout
	 */
	private int nbArguments;
	/**
	 * Constructeur permmettant la création d'un graphe mais sans son initialisation
	 * ce constructeur est utiliser lorsque l'on a besoin de lire un fichier au préalable déjà remplie
	 */
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
	 * dans le graphe en initialisant une liste vide
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
	
	
	/**
	 * methode qui permet de voir si le fichier est bien former avec la nomenclature suivante :
	 * argument(N). //N une chaine de caractère
	 * contradiction(N,M). //M une chaine de caractère
	 * @param pathFile le chemin du fichier
	 */
	public void lireFile(String pathFile) {
		 try {
		      // Créer un objet BufferedReader pour lire le fichier
		      BufferedReader reader = new BufferedReader(new FileReader(pathFile));

		      // Lire chaque ligne du fichier
		      String line;
		      while ((line = reader.readLine()) != null) {
		        // Vérifier si la ligne respecte la nomenclature
		        if (line.matches("^argument\\(([^,()]+)\\)\\.$")) {
		        	
		        }
		        else if (line.matches("^contradiction\\(([^,()]+),([^,()]+)\\)\\.$")){
		        	
		        }
		        else {
		        	System.err.println("Le fichier est mal formé");
		        	System.exit(0);
		        	
		        	reader.close();
		        }
		      }

		      reader.close();
		    } catch (IOException e) {
		    	System.err.println("Le fichier n'existe pas");
		    	System.exit(0);
		    }
	}
	
	/**
	 * verifie si dans une contradiction, un argument n'existe pas
	 * et arrete le programme "proprement"
	 * verifie si le nom d'un argument ne contient pas "argument" ou "contradiction"
	 * @param filePath le chemin du fichier
	 * @param arguments la liste d'argument en string
	 */
	public void argumentExistePas(String filePath,ArrayList<String> arguments) {
		String line;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			while((line = reader.readLine())!= null) {
				if(line.contains("contradiction(")) {
					int start = line.indexOf("(");
					int end = line.indexOf(",");
					
					int start2 = line.indexOf(",");
					int end2 = line.indexOf(")");
					
					String argument1 = line.substring(start + 1, end).trim();
					String argument2 = line.substring(start2 + 1, end2).trim();
					
					if(!(arguments.contains(argument1))) {
						System.err.println("Le fichier est mal formé");
						System.exit(0);
					}
					if(!(arguments.contains(argument2))) {
						System.err.println("Le fichier est mal formé");
						System.exit(0);
					}
					for(String s : arguments) {
						if(s.contains("argument") || s.contains("contradiction")) {
							System.err.println("Le fichier est mal formé");
							System.exit(0);
						}
					}
					
				}
				
			}
			reader.close();
		}catch (IOException e) {
			System.err.println("Le fichier n'a pas pu être lu correctement");
			System.exit(0);
		}
		
	}
	
	/**
	 * retourne la liste des argument dans une liste de string
	 * @param filePath le chemin du fichier
	 * @return la liste des arguments
	 */
	public ArrayList<String> argumentList(String filePath) {
		String line;
		ArrayList<String> arguments = new ArrayList<String>();
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
					String argument = line.substring(start, end).trim();
					
					
					arguments.add(argument);
				}	
			}
			
			reader.close();
			
		} catch (IOException e) {
			System.err.println("Le fichier n'a pas pu être lu correctement");
			System.exit(0);
		}
		return arguments;
	}
	/**
	 * Récupère les contradiction dans un fichier de la forme : "contradiction(N1,N2)
	 * avec N1 qui contredit N2
	 * @param filePath le chemin du fichier
	 * @throws Exception 
	 */
	public void extractContradiction(String filePath) throws Exception {
		String line;
		try {	
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			while((line = reader.readLine()) != null) {
				if(line.contains("contradiction(")) {
					int start = line.indexOf("(");
					int end = line.indexOf(",");
					
					int start2 = line.indexOf(",");
					int end2 = line.indexOf(")");
					String argument1 = line.substring(start + 1, end).trim();
					String argument2 = line.substring(start2 + 1, end2).trim();
					
					
					ajouterContradiction(argument1, argument2);
					
				}
			}
			reader.close();
		}catch (IOException e) {
			System.err.println("Le fichier n'a pas pu être lu correctement");
			System.exit(0);
		}
	}
	
	/**
	 * Récupère tous les argument d'un fichier de la forme : "Argument(N)" et les met
	 * dans le graphe en initialisant une liste vide
	 * @param filePath le chemin du fichier
	 */
	public void extractArgument(String filePath) {
		String line;
		
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
					String argument = line.substring(start, end).trim();
					ArrayList<ArgumentNoeud> argumentsInit = new ArrayList<ArgumentNoeud>();
					
					
					graphMap.put(new ArgumentNoeud(argument), argumentsInit);
				}
				
			}
			reader.close();
		} catch (IOException e) {
			System.err.println("Le fichier n'a pas pu être lu correctement");
			System.exit(0);
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
			System.exit(0);
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
