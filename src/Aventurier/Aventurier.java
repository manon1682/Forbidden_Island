package Aventurier;

import Cartes.CarteTresor;
import Enumeration.CarteUtilisable;
import Enumeration.Couleur;
import forbidden_island.Grille;
import Enumeration.Tresor;
import forbidden_island.Tuile;
import java.util.ArrayList;

public abstract class Aventurier {

    private ArrayList<CarteTresor> mainAventurier;
    private Couleur couleur;
    private String pseudo;
    private int posLigne;
    private int posColonne;
    private static ArrayList<Tresor> tresorsObtenus;

    public ArrayList<CarteTresor> getMainA() {
       return mainAventurier;
    }

    public void removeMainA(CarteUtilisable nomCarte) {
        mainAventurier.remove(nomCarte);
    }

    public void déplacementPossible(Grille grille) {
        boolean[][] g = new boolean[6][6];
        
        initialisation(g);
        getGrillePossible(g, grille);
    }

    public void déplacer(int l, int c) {
        setLigne(l);
        setColonne(c);
    }

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

    public ArrayList<Tresor> getTresor() {
        return tresorsObtenus;
    }

    public ArrayList<CarteTresor> mainTresor(Tresor tr) {
        ArrayList<CarteTresor> cartes = getMainA();
        ArrayList<CarteTresor> cartesOK = new ArrayList<>();
        
        for (CarteTresor c : cartes){
            if (tr.toString() == c.getNom()) {
                cartesOK.add(c);
            }
        }
        
        return cartesOK;
    }

    public void ajoutTresor(Tresor tr) {
        // TODO - implement Aventurier.ajoutTresor
        throw new UnsupportedOperationException();
    }

    public void donnerCarte(Aventurier jr, CarteUtilisable carte) {
        // TODO - implement Aventurier.donnerCarte
        throw new UnsupportedOperationException();
    }

    public void addMain(CarteUtilisable nomCarte) {
        // TODO - implement Aventurier.addMain
        throw new UnsupportedOperationException();
    }

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
