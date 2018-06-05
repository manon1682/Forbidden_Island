package forbidden_island;

import Enumeration.CarteUtilisable;
import Cartes.Deck_Innondation;
import Cartes.Deck_Tresor;
import Aventurier.Aventurier;
import Aventurier.Messager;
import Cartes.CarteTresor;
import Cartes.Deck;
import Enumeration.Tresor;
import java.awt.Color;

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
import view.VueAventurier;

public class Controleur implements Observateur {

    private Grille grille;
    private Collection<Aventurier> joueurs;
    private Deck_Tresor deck_T;
    private Deck_Innondation deck_I;
    private int jaugeInnondation;
    private Aventurier joueurCourant;
    private VueAventurier vueA;
    private int nbAction;

    public Controleur() {
        vueA = new VueAventurier("Joueur", "Explorateur", Color.red);
        vueA.addObservateur(this);
        initPlateau();
        initDeck();
    }

    public ArrayList<String> chargerNomString() {
        File fileNomTuile = new File("src/nomTuile");
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

    public Tresor assigneTresorTuile(String nomTuile) {
        Tresor t;
        switch (nomTuile) {
            case "Le Temple de La Lune":
            case "Le Temple du Soleil":
                t = Tresor.PIERRE_SACRE;
                break;
            case "Le Jardin des Murmures":
            case "Le Jardin des Hurlements":
                t = Tresor.STATUE_DU_ZEPHIR;
                break;
            case "La Caverne du Brasier":
            case "La Caverne des Ombres":
                t = Tresor.CRISTAL_ARDENT;
                break;
            case "Le Palais des Marees":
            case "Le Palais de Corail":
                t = Tresor.CALICE_DE_ORDRE;
                break;
            default:
                t = null;
                break;
        }
        return t;
    }

    public void initPlateau() {
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
                    tuile = new Tuile(nomTuile.get(rand), l, c);
                    Tresor t = assigneTresorTuile(nomTuile.get(rand));
                    if (t != null) {
                        tuile.setTresor(t);
                    }
                    tuiles[l][c] = tuile;
                    nomTuile.remove(rand);
                }
            }
        }
        grille = new Grille(tuiles);
    }

    public void initDeck() {

        //CarteTresor Deck deckTresor = new Deck_Tresor();

    }

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

        if (!(a.getTresor().contains(tr))) {
            ArrayList<CarteTresor> cartesTre = a.mainTresor(tr);

            if (cartesTre.size() >= 4) {
                for (int i = 0; i < 4; i++) {
                    a.removeMainA(cartesTre.get(i));
                    this.addDefausseT(cartesTre.get(i));
                }
                a.ajoutTresor(tr);
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }

    public ArrayList<Aventurier> getDonnerCartePossible(Aventurier a) {
        int li = a.getL();
        int co = a.getC();

        ArrayList<Aventurier> aventurierOK = new ArrayList<Aventurier>();

        for (Aventurier j : joueurs) {
            int l = j.getL();
            int c = j.getC();

            if (li == l && co == c) {
                aventurierOK.add(j);
            }
        }

        return aventurierOK;
    }

    public Deck getDeck_T() {
        return deck_T;
    }

    public void assecherSpecial(Grille grille) {
        // TODO - implement Controleur.assecherSpecial
        throw new UnsupportedOperationException();
    }

    public Grille getGrille() {
        return this.grille;
    }

    @Override
    public void traiterMessage(Message m) {
        boolean[][] g = new boolean[6][6];
        TypesMessages type = m.getType();
        switch (type) {
            
            case DEPLACER :
                if (m.getTuile() == null) {
                joueurCourant.getGrillePossibleD(g, grille);
                vueA.afficherTuilePossible(g, getGrille());
            } else {
                Tuile tuile = m.getTuile();
                int l = tuile.getLigne();
                int c = tuile.getColonne();
                joueurCourant.deplacer(l, c);
            }
                break;
                
                
            case ASSECHER :    
                 if (m.getTuile() == null) {
                joueurCourant.getGrillePossibleA(g, grille);
                vueA.afficherTuilePossible(g, getGrille());
            } else {
                Tuile tuile = m.getTuile();
                tuile.asseche();
            }
                 break;
                
                 
            case DONNER_CARTE :
                if (joueurCourant instanceof Messager) {
                //vueA.afficherJoueurPossible(joueurs);
            } else {
                ArrayList<Aventurier> aventurier = getDonnerCartePossible(joueurCourant);
                //vueA.afficherJoueurPossible(aventurier);
            }
                break;
                
                
            case PRENDRE_TRESOR :
                if (getTresor(joueurCourant)) {
                nbAction = nbAction - 1;
            }
                break;
                
                
            case UTILISER_CARTE :
                
                break;  
        }
    }
}
