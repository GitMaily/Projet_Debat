package up.mi.ttsmmc;

/**
 * Cette classe représente un argument dans un débat, qu'on appelle aussi un noeud dans un graphe orienté.
 * @author Thomas_Tanguy
 * @author Samuel_Lavallée
 * @author Maily_Ciavaldini
 * @version PHASE_1
 */
public class ArgumentNoeud {
	/**
	 * le nom de l'argument(nom du noeud dans le graphe)
	 */
	private String nomArgument;

	/**
	 * constructeur permettant de set le nom de l'argument
	 * @param argument nom de l'argument
	 */
	public ArgumentNoeud(String argument) {
		this.nomArgument = argument;
	}
	
	/**
	 * méthode toString redéfinie : ("nomArgument")
	 */
	public String toString() {
		return "("+this.nomArgument+")";
	}
	
	/**
	 * pour get le nom de l'argument
	 * @return String : le nom de l'argument
	 */
	public String getNomArgument() {
		return nomArgument;
	}
	
	/**
	 * pour set le nom de l'argument
	 * @param argument String : le nouveau nom de l'argument
	 */
	public void setNomArgument(String argument) {
		this.nomArgument = argument;
	}


}
