package up.mi.ttsmmc;

/**
 * Cette classe représente un argument dans un débat, qu'on appelle aussi un noeud dans un graphe orienté.
 * @author Thomas_Tanguy
 * @author Samuel_Lavallée
 * @author Maily_Ciavaldini
 * @version PHASE_1
 */
public class ArgumentNoeud {

	private String nomArgument;

	public ArgumentNoeud(String argument) {
		this.nomArgument = argument;
	}
	
	public String toString() {
		return "("+this.nomArgument+")";
	}
	

	public String getNomArgument() {
		return nomArgument;
	}

	public void setNomArgument(String argument) {
		this.nomArgument = argument;
	}


}
