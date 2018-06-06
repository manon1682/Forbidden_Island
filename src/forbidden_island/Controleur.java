package forbidden_island;

import Enumeration.CarteUtilisable;
import Cartes.Deck_Innondation;
import Cartes.Deck_Tresor;
import Aventurier.*;
import Aventurier.Explorateur;
import Aventurier.Ingénieur;
import Aventurier.Messager;
import Cartes.CarteInnondation;
import Cartes.CarteTresor;
import Cartes.Deck;
import Enumeration.EtatTuile;
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
import view.VueExplorateur;
import view.VueIngénieur;
import view.VueMessager;
import view.VueNavigateur;
import view.VuePilote;
import view.VuePlongeur;

public class Controleur implements Observateur {

    private Grille grille;
    private ArrayList<Aventurier> joueurs = new ArrayList<Aventurier>();
    private Deck_Tresor deck_T;
    private Deck_Innondation deck_I;
    private int jaugeInnondation;
    private Aventurier joueurCourant;
    private VueAventurier vueA;
    private int nbAction;

    public Controleur() {
        //vueA = new VueExplorateur("Joueur", "Explorateur", Color.red);
        //vueA.addObservateur(this);
        initPlateau();
        initDeck();
        //vueA.afficher();
    }

    public ArrayList<String> chargerNomTuile() {
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
        ArrayList<String> nomTuile = chargerNomTuile();
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
        deck_T = new Deck_Tresor();
        deck_I = new Deck_Innondation(chargerNomTuile());
    }

    public void initJoueur(int n, ArrayList<String> nom) {

        ArrayList<String> roles = new ArrayList<>();
        roles.add("Explorateur");
        roles.add("Ingénieur");
        roles.add("Pilote");
        roles.add("Plongeur");
        roles.add("Messager");
        roles.add("Navigateur");

        for (int i = 0; i < n; i++) {
            int rand = ThreadLocalRandom.current().nextInt(0, roles.size());
            Tuile t;

            switch (roles.get(rand)) {
                case "Explorateur":
                    t = grille.getTuileAvecNom("La Porte de Cuivre");
                    joueurs.add(new Explorateur(nom.get(i), t.getLigne(), t.getColonne()));
                    nom.remove(i);
                    break;

                case "Ingénieur":
                    t = grille.getTuileAvecNom("La Porte de Bronze");
                    joueurs.add(new Ingénieur(nom.get(i), t.getLigne(), t.getColonne()));
                    nom.remove(i);
                    break;

                case "Pilote":
                    t = grille.getTuileAvecNom("Heliport");
                    joueurs.add(new Pilote(nom.get(i), t.getLigne(), t.getColonne()));
                    nom.remove(i);
                    break;

                case "Messager":
                    t = grille.getTuileAvecNom("La Porte d’Argent");
                    joueurs.add(new Messager(nom.get(i), t.getLigne(), t.getColonne()));
                    nom.remove(i);
                    break;

                case "Navigateur":
                    t = grille.getTuileAvecNom("La Porte d’Or");
                    joueurs.add(new Navigateur(nom.get(i), t.getLigne(), t.getColonne()));
                    nom.remove(i);
                    break;

                case "Plongeur":
                    t = grille.getTuileAvecNom("La Porte de Fer");
                    joueurs.add(new Plongeur(nom.get(i), t.getLigne(), t.getColonne()));
                    nom.remove(i);
                    break;
            }
            roles.remove(rand);
        }
        
        joueurCourant = joueurs.get(0);

    }

    public void jouerTour(Aventurier a) {

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

    // Antoine note : à coder après perdrePartie(); après check pour jaugeInnondation
    public boolean gagnerPartie() {
        return false;
    }
    
    public boolean perdrePartie(){
    /*
        1. Si les 2 tuiles « Temple », « Caverne », « Palais» ou « Jardin » (sur lesquelles sont placés les 
symboles des trésors) sombrent avant que vous n’ayez pris leurs trésors respectifs ;
        2. Si « l’héliport » sombre ;
        3. Si un joueur est sur une tuile Île qui sombre et qu’il n’y a pas de tuile adjacente où nager ;
        4. Si le Marqueur de niveau atteint la tête de mort.        
    */
    
        //cas 1
    if( 
        grille.getTuileAvecNom("Le Temple du Soleil").getEtat() == EtatTuile.inondée 
     && grille.getTuileAvecNom("Le Temple du Soleil").getTresor() != null
        &&
        grille.getTuileAvecNom("Le Temple de La Lune").getEtat() == EtatTuile.inondée 
     && grille.getTuileAvecNom("Le Temple de La Lune").getTresor() != null
     
     ||
        grille.getTuileAvecNom("La Caverne des Ombres").getEtat() == EtatTuile.inondée 
     && grille.getTuileAvecNom("La Caverne des Ombres").getTresor() != null
        &&
        grille.getTuileAvecNom("La Caverne du Brasier").getEtat() == EtatTuile.inondée 
     && grille.getTuileAvecNom("La Caverne du Brasier").getTresor() != null
     
     ||
        grille.getTuileAvecNom("Le Palais de Corail").getEtat() == EtatTuile.inondée 
     && grille.getTuileAvecNom("Le Palais de Corail").getTresor() != null
        &&
        grille.getTuileAvecNom("Le Palais des Marees").getEtat() == EtatTuile.inondée 
     && grille.getTuileAvecNom("Le Palais des Marees").getTresor() != null
     
     ||
        grille.getTuileAvecNom("Le Jardin des Murmures").getEtat() == EtatTuile.inondée 
     && grille.getTuileAvecNom("Le Jardin des Murmures").getTresor() != null
        &&
        grille.getTuileAvecNom("Le Jardin des Hurlements").getEtat() == EtatTuile.inondée 
     && grille.getTuileAvecNom("Le Jardin des Hurlements").getTresor() != null 
      ){
        return true;
       }
        
        //Cas 2
    if (grille.getTuileAvecNom("Heliport").getEtat() == EtatTuile.inondée ) {
        return true;
    }
    
    
        //cas 3 : 3. Si un joueur est sur une tuile Île qui sombre 
        //et qu’il n’y a pas de tuile adjacente où nager ;
    
//        for (int i = 0; i < n; i++) {
//            
//        }
    
        
        
        
    if (grille.getTuileAvecNom(nomTuile)) {
            
    
    }
        
        //Cas 4
    if (jaugeInnondation > 9) {
        return true;
    }
        
        
                
        
        
        
        
            
            
            
            return false;
    }

    @Override
    public void traiterMessage(Message m) {
        boolean[][] g = new boolean[6][6];
        TypesMessages type = m.getType();

        if (m.getJoueur() != null) {
            joueurCourant = m.getJoueur();
        }

        switch (type) {

            case DEPLACER:
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

            case ASSECHER:
                if (m.getTuile() == null) {
                    joueurCourant.getGrillePossibleA(g, grille);
                    vueA.afficherTuilePossible(g, getGrille());
                } else {
                    Tuile tuile = m.getTuile();
                    tuile.asseche();
                }
                break;

            case DONNER_CARTE:
                if (joueurCourant instanceof Messager) {
                    //vueA.afficherJoueurPossible(joueurs);
                } else {
                    ArrayList<Aventurier> aventurier = getDonnerCartePossible(joueurCourant);
                    //vueA.afficherJoueurPossible(aventurier);
                }
                break;

            case PRENDRE_TRESOR:
                if (getTresor(joueurCourant)) {
                    nbAction = nbAction - 1;
                }
                break;

            case UTILISER_CARTE:

                break;

            case TOUR_SUIVANT:

                break;
        }
    }
}
