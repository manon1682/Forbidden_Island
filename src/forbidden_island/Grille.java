package forbidden_island;

import java.util.*;

public class Grille {
    
    private Tuile[][] tuiles = new Tuile[6][6];
    
    public Grille(Tuile[][] tuile) {
        setTuiles(tuile);
    }
    
    public Tuile[][] getTuiles() {
        return tuiles;
    }
    
    public Tuile getTuileAvecNom(String nomTuile) {
        int c = 0;
        int l = 0;
        while(tuiles[l][c].getNom() != nomTuile && c<6 && l<6){
            l = (c == 5 ? l++:l);
            c = (c == 5 ? 0:c++);
        }
        return (l== 6 || c == 6 ? tuiles[0][0]:tuiles[l][c]);
    }
    
    public void setTuiles(Tuile[][] tuiles) {
        this.tuiles = tuiles;
    }
    
}
