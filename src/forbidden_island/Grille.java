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
        /* int c = 0;
        int l = 0;
        
        while (c < 6 && l < 6 && tuiles[l][c].getNom() != nomTuile) {

            if (c == 5) {
                l++;
                c = 0;
            } else {
                c++;
            }
        }
        return tuiles[l][c]; */

        Tuile tuile = new Tuile();

        for (int l = 0; l < 6; l++) {
            for (int c = 0; c < 6; c++) {
                if (tuiles[l][c].getNom().equalsIgnoreCase(nomTuile)) {
                    tuile.setColonne(c);
                    tuile.setLigne(l);
                    tuile.setNom(tuiles[l][c].getNom());
                }
            }
        }

        return tuile;

    }

    public void setTuiles(Tuile[][] tuiles) {
        this.tuiles = tuiles;
    }

}
