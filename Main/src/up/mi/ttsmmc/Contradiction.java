package up.mi.tsm;

public class Contradiction {
	
	private ArgumentNoeud argumentCible;
	
	public Contradiction(ArgumentNoeud argumentCible) {
		this.argumentCible = argumentCible;
	}
	
	
	public ArgumentNoeud getArgumentCible() {
		return argumentCible;
	}

	public void setArgumentCible(ArgumentNoeud argumentCible) {
		this.argumentCible = argumentCible;
	}
	
	public String toString() {
		return "["+this.argumentCible.getNomArgument()+"]";
	}

}
