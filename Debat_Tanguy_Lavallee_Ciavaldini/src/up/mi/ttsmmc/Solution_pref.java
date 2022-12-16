package up.mi.ttsmmc;

import java.util.ArrayList;
import java.util.List;

public class Solution_pref {

	public List<List<ArgumentNoeud>>  getSolPref (List<List<ArgumentNoeud>> sol_admi) {

        List<List<ArgumentNoeud>> sol_pref = new ArrayList<>();
        
        
        for( int i=0;i<sol_admi.size();i++) {
            
            
            int taille  = sol_admi.get(i).size();    
            
            List<ArgumentNoeud> element = sol_admi.get(i);
            
            boolean est_contenu = true;
            for( int j=0;j<sol_admi.size();j++) {
                if(j!=i) {
                    if(sol_admi.get(j).containsAll(element)) {
                        est_contenu=false;
                    }
                }
            }
            if(est_contenu==true) {
                sol_pref.add(element);
            }
    
        
    }
    return sol_pref;  
	}
}