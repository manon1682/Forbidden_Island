package Aventurier;

import Enumeration.Couleur;
import forbidden_island.Grille;
import forbidden_island.Tuile;

public class Pilote extends Aventurier {

    public Pilote(Couleur coul, String nm, int l, int c) {
        super(coul, nm, l, c);
    }

    public boolean[][] déplacementPossibleSpecial(Grille grille) {
        boolean[][] g = new boolean[6][6];

        Tuile[][] tuiles = grille.getTuiles();

        for (int l = 0; l < 6; l++) {
            for (int c = 0; c < 6; c++) {
                Tuile tuile = tuiles[l][c];
                if (tuile != null) {
                    g[l][c] = tuile.verifTuileD();
                }
            }
        }

        return g;
    }

}
