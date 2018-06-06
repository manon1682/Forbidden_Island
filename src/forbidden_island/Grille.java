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
        /*
        écris ce que fais ton code ici
        whiler
        
        
        */
        
        
        
        int c = 0;
        int l = 0;
        
        while ((tuiles[l][c] != null ? (c < 6 && l < 6 && !tuiles[l][c].getNom().equalsIgnoreCase(nomTuile)) : (c < 6 && l < 6))) {
            if (c == 5) {
                l++;
                c = 0;
            } else {
                c++;
            }
        }
        return tuiles[l][c];

    }

    public void setTuiles(Tuile[][] tuiles) {
        this.tuiles = tuiles;
    }

}
