package Aventurier;

import Enumeration.Couleur;
import forbidden_island.Grille;
import forbidden_island.Tuile;

public class Explorateur extends Aventurier {

    public Explorateur(Couleur coul, String nm, int l, int c) {
        super(coul, nm, l, c);
    }

    @Override
    public boolean[][] deplacementPossible(Grille grille) {

        boolean[][] g = new boolean[6][6];
        g = super.deplacementPossible(grille);

        Tuile[][] tuiles = grille.getTuiles();

        int l = getL();
        int c = getC();

        if (l - 1 >= 0 && c - 1 >= 0) {
            Tuile tuile = tuiles[l - 1][c - 1];
            if (tuile != null) {
                g[l - 1][c - 1] = tuile.verifTuileD();
            }
        }

        if (l - 1 >= 0 && c + 1 < 6) {
            Tuile tuile = tuiles[l - 1][c + 1];
            if (tuile != null) {
                g[l - 1][c + 1] = tuile.verifTuileD();
            }
        }

        if (l + 1 < 6 && c + 1 < 6) {
            Tuile tuile = tuiles[l + 1][c + 1];
            if (tuile != null) {
                g[l + 1][c + 1] = tuile.verifTuileD();
            }
        }

        if (l + 1 < 6 && c - 1 >= 0) {
            Tuile tuile = tuiles[l + 1][c - 1];
            if (tuile != null) {
                g[l + 1][c - 1] = tuile.verifTuileD();
            }
        }

        return g;
    }

    @Override
    public boolean[][] assechementPossible(Grille grille) {
        boolean[][] g = new boolean[6][6];
        g = super.assechementPossible(grille);
        
        Tuile[][] tuiles = grille.getTuiles();

        int l = getL();
        int c = getC();

        if (l-1 >= 0 && c-1 >= 0) {
            Tuile tuile = tuiles[l-1][c-1];
            if (tuile != null) {
                g[l-1][c-1] = tuile.verifTuileA();
            }
        }

        if (l-1 >= 0 && c+1 < 6) {
            Tuile tuile = tuiles[l-1][c+1];
            if (tuile != null) {
                g[l-1][c+1] = tuile.verifTuileA();
            }
        }

        if (l+1 < 6 && c+1 < 6) {
            Tuile tuile = tuiles[l+1][c+1];
            if (tuile != null) {
                g[l+1][c+1] = tuile.verifTuileA();
            }
        }

        if (l+1 < 6 && c-1 >= 0) {
            Tuile tuile = tuiles[l+1][c-1];
            if (tuile != null) {
                g[l+1][c-1] = tuile.verifTuileA();
            }
        }
        
        return g;
    }

}
