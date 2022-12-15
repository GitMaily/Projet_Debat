package up.mi.ttsmmc;

import java.util.List;

public class Solution_pref {

	public List<List<ArgumentNoeud>>  getSolPref (List<List<ArgumentNoeud>> sol_admi) {
		List<List<ArgumentNoeud>> sol_pref = null;
		boolean est_contenu = false;
		for( List<ArgumentNoeud> element : sol_admi) {
			int taille = element.size();
			if(taille==0) {
				continue;// si ensemble vide passage au prochain element
			}
			
			else {
				
				for (List<ArgumentNoeud> compare : sol_admi) {
					if(compare.equals(element)) {
						continue;
					}
					if(compare.contains(element)) {
						est_contenu = true;
						break; //arret de la seconde boucle for, l'argument element n est pas une solution preferable
					}
					
				}
			}
			
			if(est_contenu == false) {
				sol_pref.add(element);
			}
			
		}
		
		return sol_pref;
	}
}
