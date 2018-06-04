package Aventurier;

import Enumeration.Couleur;
import forbidden_island.Grille;
import forbidden_island.Tuile;

public class Plongeur extends Aventurier {

    public Plongeur(Couleur coul, String nm, int l, int c) {
        super(coul, nm, l, c);
    }

    @Override
    public boolean[][] déplacementPossible(Grille grille) {
        boolean[][] gP = new boolean[6][6];
        boolean[][] gE = new boolean[6][6];
        
        casePossible(grille, gP, gE);
        
        super.initialisation(gE);
        super.initialisation(gP);

        return gP;
    }

    public void casePossible(Grille grille, boolean[][] gP, boolean[][] gE) {
        int l = getL();
        int c = getC();
        
        gE[l][c] = true;
        getGrillePossibleD(gP, grille);
        
        Tuile[][] tuiles = grille.getTuiles();
        
        if (l-1 >= 0) {
            Tuile tuile = tuiles[l-1][c];
            if (tuile != null && tuile.verifTuileDPlongeur() && !gE[l-1][c]){
                int sauvL = getL();
                setLigne(l-1);
                casePossible(grille, gP, gE);
                setLigne(sauvL);
            }
        }
        
        if (c+1 < 6) {
            Tuile tuile = tuiles[l][c+1];
            if (tuile != null && tuile.verifTuileDPlongeur() && !gE[l][c+1]){
                int sauvC = getC();
                setColonne(c+1);
                casePossible(grille, gP, gE);
                setColonne(sauvC);
            }   
        }
        
        if (l+1 < 6) {
            Tuile tuile = tuiles[l+1][c];
            if (tuile != null && tuile.verifTuileDPlongeur() && !gE[l+1][c]){
                int sauvL = getL();
                setLigne(l+1);
                casePossible(grille, gP, gE);
                setLigne(sauvL);
            }     
        }
        
        if (c-1 >= 0) {
            Tuile tuile = tuiles[l][c-1];
            if (tuile != null && tuile.verifTuileDPlongeur() && !gE[l][c-1]){
                int sauvC = getC();
                setColonne(c-1);
                casePossible(grille, gP, gE);
                setColonne(sauvC);
            }
        }
        
        gP[l][c] = false;
        
    }

}
