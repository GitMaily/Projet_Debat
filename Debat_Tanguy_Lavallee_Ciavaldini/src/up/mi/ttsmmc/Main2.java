package up.mi.ttsmmc;

public class Main2 {

	/**
	 * Ce programme permet de résoudre un débat.
	 * @author Thomas_Tanguy
	 * @author Samuel_Lavallée
	 * @author Maily_Ciavaldini
	 * @version PHASE_2
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String fileName = "E:\\user\\Documents\\Documents descartes L3\\COURS\\POOA\\Projet debat\\pooa\\Debat_Tanguy_Lavallee_Ciavaldini\\src\\file.txt"; // remplacez par le chemin et le nom du fichier à lire
		
		System.out.println("* * * * * Représentation du graphe * * * * * ");
		ListeAdjacence graphe = new ListeAdjacence();
		graphe.extractArgument(fileName);
		graphe.afficherGraphe();
		graphe.extractContradiction(fileName);
		graphe.afficherGrapheAvecContradictions();
	}
}
