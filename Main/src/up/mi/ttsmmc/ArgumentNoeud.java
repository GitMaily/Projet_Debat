package up.mi.tsm;

public class ArgumentNoeud {

	private String nomArgument;
	private int nbArgument;
	private ArgumentNoeud arc;
	
	public ArgumentNoeud(String argument, int nbArgument, ArgumentNoeud arc) {
		this.nomArgument = argument;
		this.nbArgument = nbArgument;
		//this.aContredit = aContredit;
		this.arc = arc;
	}
	
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

	public int getNbArgument() {
		return nbArgument;
	}

	public void setNbArgument(int nbArgument) {
		this.nbArgument = nbArgument;
	}

	/*public boolean isaContredit() {
		return aContredit;
	}

	public void setaContredit(boolean aContredit) {
		this.aContredit = aContredit;
	}*/

	public ArgumentNoeud getArc() {
		return arc;
	}

	public void setArc(ArgumentNoeud arc) {
		this.arc = arc;
	}
}
