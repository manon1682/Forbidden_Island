package Aventurier;

import Cartes.CarteTresor;
import Cartes.CarteUtilisable;
import Enumeration.Couleur;
import forbidden_island.Grille;
import Enumeration.Tresor;
import forbidden_island.Tuile;
import java.util.ArrayList;

public abstract class Aventurier {

    ArrayList<CarteTresor> mainAventurier;
    private Couleur couleur;
    private String pseudo;
    private int posLigne;
    private int posColonne;
    private static Tresor tresorObtenu;

    public void getMainA() {
        // TODO - implement Aventurier.getMainA
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param nomCarte
     */
    public void removeMainA(CarteUtilisable nomCarte) {
        // TODO - implement Aventurier.removeMainA
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param grille
     */
    public void déplacementPossible(Grille grille) {
        // TODO - implement Aventurier.déplacementPossible
        throw new UnsupportedOperationException();
    }

    public void déplacer(int l, int c) {
        setLigne(l);
        setColonne(c);
    }

    /**
     *
     * @param grille
     */
    public void assechementPossible(Grille grille) {
        // TODO - implement Aventurier.assechementPossible
        throw new UnsupportedOperationException();
    }

    public void asseche() {
        // TODO - implement Aventurier.asseche
        throw new UnsupportedOperationException();
    }

    public int getL() {
        return posLigne;
    }

    public int getC() {
        return posColonne;
    }

    public void getTresor() {
        // TODO - implement Aventurier.getTresor
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param tr
     */
    public void mainTresor(Tresor tr) {
        // TODO - implement Aventurier.mainTresor
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param tr
     */
    public void ajoutTresor(Tresor tr) {
        // TODO - implement Aventurier.ajoutTresor
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param jr
     * @param carte
     */
    public void donnerCarte(Aventurier jr, CarteUtilisable carte) {
        // TODO - implement Aventurier.donnerCarte
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param nomCarte
     */
    public void addMain(CarteUtilisable nomCarte) {
        // TODO - implement Aventurier.addMain
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param g
     */
    public void initialisation(boolean[][] g) {
        for (int l = 0; l < 6; l++) {
            for (int c = 0; c < 6; c++) {
                g[l][c] = false;
            }
        }
    }

    public void getGrillePossible(boolean[][] g, Grille grille) {

        Tuile[][] tuiles = grille.getTuiles();
        
        int l = getL();
        int c = getC();

        if (l-1 >= 0) {
            Tuile tuile = tuiles[l-1][c];
            g[l-1][c] = tuile.verifTuileA();
        }
        
        if (c+1 < 6) {
            Tuile tuile = tuiles[l][c+c];
            g[l][c+1] = tuile.verifTuileA();
        }
        
        if (l+1 < 6) {
            Tuile tuile = tuiles[l+1][c];
            g[l+1][c] = tuile.verifTuileA();
        }
        
        if (c-1 >= 0) {
            Tuile tuile = tuiles[l][c-1];
            g[l][c-1] = tuile.verifTuileA();
        }
    }

    public void setLigne(int l) {
        posLigne = l;
    }


    public void setColonne(int c) {
        posColonne = c;
    }

}
