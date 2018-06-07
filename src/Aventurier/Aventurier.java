package Aventurier;

import Cartes.CarteTresor;
import forbidden_island.Grille;
import Enumeration.Tresor;
import forbidden_island.Tuile;
import java.util.ArrayList;
import util.Utils.Pion;

public abstract class Aventurier {

    private ArrayList<CarteTresor> mainAventurier;
    private Pion couleur;
    private final String pseudo;
    private int posLigne;
    private int posColonne;
    private static ArrayList<Tresor> tresorsObtenus;

    /* Construsteur*/
    public Aventurier(Pion coul, String nm, int l, int c) {
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

    public boolean[][] deplacementPossible(Grille grille) {
        boolean[][] g = new boolean[6][6];

        initialisation(g);
        getGrillePossibleD(g, grille);

        return g;
    }

    public void deplacer(int l, int c) {
        setLigne(l);
        setColonne(c);
    }

    public boolean[][] assechementPossible(Grille grille) {
        boolean[][] g = new boolean[6][6];

        initialisation(g);
        getGrillePossibleA(g, grille);

        return g;
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

        for (CarteTresor c : cartes) {
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
    
    public void ajouterCartesMain(ArrayList<CarteTresor> cartes){
        mainAventurier.addAll(cartes);
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

        if (l - 1 >= 0) {
            Tuile tuile = tuiles[l - 1][c];
            if (tuile != null) {
                g[l - 1][c] = tuile.verifTuileD();
            }
        }

        if (c + 1 < 6) {
            Tuile tuile = tuiles[l][c + 1];
            if (tuile != null) {
                g[l][c + 1] = tuile.verifTuileD();
            }
        }

        if (l + 1 < 6) {
            Tuile tuile = tuiles[l + 1][c];
            if (tuile != null) {
                g[l + 1][c] = tuile.verifTuileD();
            }
        }

        if (c - 1 >= 0) {
            Tuile tuile = tuiles[l][c - 1];
            if (tuile != null) {
                g[l][c - 1] = tuile.verifTuileD();
            }
        }
    }

    public void getGrillePossibleA(boolean[][] g, Grille grille) {

        Tuile[][] tuiles = grille.getTuiles();

        int l = getL();
        int c = getC();

        if (l-1 >= 0) {
            Tuile tuile = tuiles[l-1][c];
            if (tuile != null) {
                g[l-1][c] = tuile.verifTuileA();
            }
        }

        if (c+1 < 6) {
            Tuile tuile = tuiles[l][c+1];
            if (tuile != null) {
                g[l][c+1] = tuile.verifTuileA();
            }
        }

        if (l+1 < 6) {
            Tuile tuile = tuiles[l+1][c];
            if (tuile != null) {
                g[l+1][c] = tuile.verifTuileA();
            }
        }

        if (c-1 >= 0) {
            Tuile tuile = tuiles[l][c-1];
            if (tuile != null) {
                g[l][c-1] = tuile.verifTuileA();
            }
        }
    }

    public void setLigne(int l) {
        posLigne = l;
    }

    public void setColonne(int c) {
        posColonne = c;
    }

    public String getPseudo() {
        return pseudo;
    }

    public Pion getPion() {
        return couleur;
    }

    public void setCouleur(Pion couleur) {
        this.couleur = couleur;
    }
    
    public String getRole(){
        String role;
        if(this instanceof Ingénieur){
            role = "Ingénieur";
        } else if(this instanceof Messager){
            role = "Messager";
        } else if(this instanceof Pilote){
            role = "Pilote";
        } else if(this instanceof Navigateur){
            role = "Navigateur";
        } else if(this instanceof Plongeur){
            role = "Plongeur";
        } else {
            role = "Explorateur";
        }
        return role;
    }

    public static ArrayList<Tresor> getTresorsObtenus() {
        return tresorsObtenus;
    }
    
    public static boolean TresorsObtenus(Tresor t){
        return tresorsObtenus.contains(t);
        //ça renvoie true si le trésor est présent.
    }
}

