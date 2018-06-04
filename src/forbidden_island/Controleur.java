package forbidden_island;

import Enumeration.CarteUtilisable;
import Cartes.Deck_Innondation;
import Cartes.Deck_Tresor;
import Aventurier.Aventurier;
import Cartes.CarteTresor;
import Cartes.Deck;
import Enumeration.Tresor;

/*Charger fichier tuiles*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controleur {

    private Grille grille;
    private Collection<Aventurier> joueurs;
    private Deck_Tresor deck_T;
    private Deck_Innondation deck_I;
    private int jaugeInnondation;

    public ArrayList<String> chargerNomString() {
        File fileNomTuile = new File("/Fichier_Texte/nomTuile.txt");
        ArrayList<String> nomTuile = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileNomTuile))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                nomTuile.add(ligne);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nomTuile;
    }

    public Grille initPlateau() {
        Tuile[][] tuiles = new Tuile[6][6];
        ArrayList<String> nomTuile = chargerNomString();
        Tuile tuile;
        for (int l = 0; l < 6; l++) {
            for (int c = 0; c < 6; c++) {
                if ((l == 0 && ((c == 0) || (c == 1) || (c == 4) || (c == 5)))
                        || (l == 1 && ((c == 0) || (c == 5)))
                        || (l == 4 && ((c == 0) || (c == 5)))
                        || (l == 5 && ((c == 0) || (c == 1) || (c == 4) || (c == 5)))) //On verifie qu'on est dans les angles
                {
                    //On est dans un angles donc on met une tuile null
                    tuiles[l][c] = new Tuile();
                } else {
                    int rand = ThreadLocalRandom.current().nextInt(0, nomTuile.size()); 
                    //On genere un nombre aleatoire compris entre 0 et le nombre de nomtuile qu'il reste
                    tuile = new Tuile();
  
                }
            }
        }

        grille = new Grille(tuiles);

        return grille;
    }

    /**
     *
     * @param a
     */
    public void jouerTour(Aventurier a) {
        // TODO - implement Controleur.jouerTour
        throw new UnsupportedOperationException();
    }

    public void addDefausseT(CarteTresor carte) {
        getDeck_T().getDefausse().add(carte);
    }


    public void asseche(Tuile tuile) {
        tuile.asseche();
    }

    public boolean getTresor(Aventurier a) {
        int l = a.getL();
        int c = a.getC();
        
        Tuile[][] tuiles = grille.getTuiles();
        
        Tresor tr = tuiles[l][c].getTresor();
        ArrayList<CarteTresor> cartesTre = a.mainTresor(tr);
        
        if (cartesTre.size() >= 4){
            for (int i = 0; i < 4; i++){
                a.removeMainA(cartesTre.get(i));
                this.addDefausseT(cartesTre.get(i));
            }
            a.ajoutTresor(tr);
            return true;
        } else return false;
        
    }
    
    public void choixJoueurPossible(Aventurier jr) {
        // TODO - implement Controleur.choixJoueurPossible
        throw new UnsupportedOperationException();
    }

    public void getEchangePossible(Aventurier a) {
        // TODO - implement Controleur.getEchangePossible
        throw new UnsupportedOperationException();
    }

    public Deck getDeck_T() {
        // TODO - implement Controleur.getDeck_T
        throw new UnsupportedOperationException();
    }

    public void assecherSpecial(Grille grille) {
        // TODO - implement Controleur.assecherSpecial
        throw new UnsupportedOperationException();
    }
}
