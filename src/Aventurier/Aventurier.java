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
    
    /* Construsteur*/
    public Aventurier(Couleur coul, String nm, int l, int c){
        couleur = coul;
        pseudo = nm;
        posLigne = l;
        posColonne = c;
        
        tresorsObtenus = new ArrayList<>();
        mainAventurier = new ArrayList<>();
    }

    public ArrayList<CarteTresor> getMainA() {
       return mainAventurier;
    }

    public void removeMainA(CarteTresor nomCarte) {
        mainAventurier.remove(nomCarte);
    }

    public void déplacementPossible(Grille grille) {
        boolean[][] g = new boolean[6][6];
        
        initialisation(g);
        getGrillePossibleD(g, grille);
    }

    public void déplacer(int l, int c) {
        setLigne(l);
        setColonne(c);
    }

    public void assechementPossible(Grille grille) {
        boolean[][] g = new boolean [6][6];
        
        initialisation(g);
        getGrillePossibleA(g, grille);
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
        tresorsObtenus.add(tr);
    }

    public void donnerCarte(Aventurier j, CarteTresor carte) {
        j.addMain(carte);
        removeMainA(carte);
    }

    public void addMain(CarteTresor nomCarte) {
        mainAventurier.add(nomCarte);
    }

    public void initialisation(boolean[][] g) {
        for (int l = 0; l < 6; l++) {
            for (int c = 0; c < 6; c++) {
                g[l][c] = false;
            }
        }
    }

    public void getGrillePossibleD(boolean[][] g, Grille grille) {

        Tuile[][] tuiles = grille.getTuiles();
        
        int l = getL();
        int c = getC();

        if (l-1 >= 0) {
            Tuile tuile = tuiles[l-1][c];
            g[l-1][c] = tuile.verifTuileD();
        }
        
        if (c+1 < 6) {
            Tuile tuile = tuiles[l][c+c];
            g[l][c+1] = tuile.verifTuileD();
        }
        
        if (l+1 < 6) {
            Tuile tuile = tuiles[l+1][c];
            g[l+1][c] = tuile.verifTuileD();
        }
        
        if (c-1 >= 0) {
            Tuile tuile = tuiles[l][c-1];
            g[l][c-1] = tuile.verifTuileD();
        }
    }
    
    public void getGrillePossibleA(boolean[][] g, Grille grille) {

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
