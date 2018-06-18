package forbidden_island;

import Enumeration.TypesMessages;
import Cartes.Deck_Innondation;
import Cartes.Deck_Tresor;
import Aventurier.*;
import Aventurier.Explorateur;
import Aventurier.Ingénieur;
import Aventurier.Messager;
import Cartes.CarteTresor;
import Cartes.Deck;
import Enumeration.CarteUtilisable;
import Enumeration.EtatTuile;
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
import view.VueAventurier;
import view.VueInitialisation;

public class Controleur implements Observateur {

    private Grille grille;
    private ArrayList<Aventurier> joueurs = new ArrayList<Aventurier>();
    private Deck_Tresor deck_T;
    private Deck_Innondation deck_I;
    private int jaugeInnondation; //débute à 1 et finit 10 > tête de mort
    private Aventurier joueurCourant;
    private VueAventurier vueA;
    private VueInitialisation vueI;
    private int nbAction = 3;
    private boolean partiePerdue = false;

    public Controleur() {
        initPlateau();
        initDeck();

//        //Pour vérifier que la grille est charger correctement et correspond a celle de la demo
//        Tuile[][] ts = grille.getTuiles();
//        for (int i = 0; i < 6; i++) {
//            for (int j = 0; j < 6; j++) {
//                if(ts[i][j] != null){
//                    System.out.print("Nom :" + ts[i][j].getNom() + " Etat :" + ts[i][j].getEtat() + "|");
//                } else {
//                    System.out.print("|          X           |");
//                }
//            }
//            System.out.println("");
//        }
        vueI = new VueInitialisation();
        vueI.addObservateur(this);
        vueI.afficher();

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

    public EtatTuile etatTuileDemo(Tuile tuile) {
        EtatTuile etat;
        switch (tuile.getNom()) {
            case "Les Dunes de l’Illusion":
            case "Le Marais Brumeux":
            case "Le Temple de La Lune":
            case "Le Rocher Fantome":
                etat = EtatTuile.coulée;
                break;
            case "La Porte de Bronze":
            case "Le Lagon Perdu":
            case "Observatoire":
            case "La Caverne du Brasier":
            case "Le Jardin des Murmures":
                etat = EtatTuile.inondée;
                break;
            default:
                etat = EtatTuile.sèche;
                break;

        }
        return etat;
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
        int rand = 0; //Pas permanent juste pour demo
        for (int l = 0; l < 6; l++) {
            for (int c = 0; c < 6; c++) {
                if ((l == 0 && ((c == 0) || (c == 1) || (c == 4) || (c == 5)))
                        || (l == 1 && ((c == 0) || (c == 5)))
                        || (l == 4 && ((c == 0) || (c == 5)))
                        || (l == 5 && ((c == 0) || (c == 1) || (c == 4) || (c == 5)))) //On verifie qu'on est dans les angles
                {
                    //On est dans un angles donc on met une tuile null
                    tuiles[l][c] = null;
                } else {
                    //Ici on modifie pour charger la map comme il faut pour la demo:
                    //int rand = ThreadLocalRandom.current().nextInt(0, nomTuile.size());
                    //On genere un nombre aleatoire compris entre 0 et le nombre de nomtuile qu'il reste

                    tuile = new Tuile(nomTuile.get(rand), l, c);
                    Tresor t = assigneTresorTuile(nomTuile.get(rand));
                    if (t != null) {
                        tuile.setTresor(t);
                    }
                    tuile.setEtat(etatTuileDemo(tuile)); //Juste pour la demo
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
                    break;

                case "Ingénieur":
                    t = grille.getTuileAvecNom("La Porte de Bronze");
                    joueurs.add(new Ingénieur(nom.get(i), t.getLigne(), t.getColonne()));
                    break;

                case "Pilote":
                    t = grille.getTuileAvecNom("Heliport");
                    joueurs.add(new Pilote(nom.get(i), t.getLigne(), t.getColonne()));
                    break;

                case "Messager":
                    t = grille.getTuileAvecNom("La Porte d’Argent");
                    joueurs.add(new Messager(nom.get(i), t.getLigne(), t.getColonne()));
                    break;

                case "Navigateur":
                    t = grille.getTuileAvecNom("La Porte d’Or");
                    joueurs.add(new Navigateur(nom.get(i), t.getLigne(), t.getColonne()));
                    break;

                case "Plongeur":
                    t = grille.getTuileAvecNom("La Porte de Fer");
                    joueurs.add(new Plongeur(nom.get(i), t.getLigne(), t.getColonne()));
                    break;
            }
            joueurs.get(i).ajouterCartesMain(deck_T.piocher());
            roles.remove(rand);
        }

        joueurCourant = joueurs.get(0);

    }

    public void jouerTour(Aventurier a) {
        //code présent dans traiterMessage
    }

    public void addDefausseT(CarteTresor carte) {
        getDeck_T().defausser(carte);
    }

    public void asseche(Tuile tuile) {
        tuile.asseche();
    }

    public void prendreTresor(Aventurier a) {
        int l = a.getL();
        int c = a.getC();

        Tuile[][] tuiles = grille.getTuiles();

        //On récupère le trésor étant sur la tuile
        Tresor tr = tuiles[l][c].getTresor();

        //On récupère les 4 cartes Trésor (ou plus) correspondant au trésor de la tuile
        ArrayList<CarteTresor> cartesTre = a.mainTresor(tr);

        //On supprime ces 4 cartes de la main du joueur et on l'ajoute à la défausse du jeu
        for (int i = 0; i < 4; i++) {
            a.removeMainA(cartesTre.get(i));
            this.addDefausseT(cartesTre.get(i));
        }

        //On ajoute le trésor au joueur
        a.ajoutTresor(tr);

    }

    public Deck getDeck_T() {
        return deck_T;
    }

    public Grille getGrille() {
        return this.grille;
    }

    public int niveauInnondation() {
        //renvoie le niveau d'innondation en fonction de la jauge d'innondation

        if (jaugeInnondation >= 1 && jaugeInnondation < 3) {
            return 2;
        } else if (jaugeInnondation >= 3 && jaugeInnondation < 6) {
            return 3;
        } else if (jaugeInnondation >= 6 && jaugeInnondation < 8) {
            return 4;
        } else if (jaugeInnondation >= 8 && jaugeInnondation < 10) {
            return 5;
        } else {
            return 6; // tête de mort
        }
    }

    public boolean gagnerPartie() {
        /*
        Une fois que vous avez récupéré les quatre 
trésors, chacun doit déplacer son pion jusqu’à la tuile « l’héliport ». 
Ensuite, l’un des joueurs doit défausser une carte Hélicoptère pour 
que votre équipe décolle de l’Île Interdite et gagne ! OU ALORS IL FAUT UN ROLE PILOTE
        NB : vous pouvez gagner même si la tuile « l’héliport » est inondée.
        contre NB : Ahah, si héliport est innondée alors la partie est déjà terminé (en théorie... cf PerdrePartie();
         */

        //1.si tous les joueurs sont présent sur la case héliport
        Tuile tuileHelico = grille.getTuileAvecNom("Heliport");

        boolean joueursPresentsHeliport = true;
        while (nbAction != 0) {

        }
        for (Aventurier j : joueurs) {
            if (j.getL() != tuileHelico.getLigne() || j.getC() != tuileHelico.getColonne()) {
                joueursPresentsHeliport = false;
            } //joueursPresentsHeliport n'est jamais mis à false si tous les joueurs sont présent dans la case.
        }

        //2. si la liste des tresorsObtenus des aventurier est complète
        boolean listeTresorComplete = false;
        if (Aventurier.getTresorsObtenus().size() == 3) {
            listeTresorComplete = true;
        }

        //3. si y'a un role hélico, alors c'est good, sinon check si il y a une carte hélico
        boolean pilotePresent = false;
        for (Aventurier j : joueurs) {
            if (j.estRole("Pilote")) {
                pilotePresent = true;
            } //pilotePresent n'est jamais mis à true si parmi tous les joueurs il y a aucun pilote. 
        }

        boolean carteHelicoPresente = false;
        for (Aventurier j : joueurs) {
            for (CarteTresor cT : j.getMainA()) {
                if (cT.utilisation() == CarteUtilisable.hélico) {
                    carteHelicoPresente = true;
                }
            }
        }

        // IF GENERALE
        if (joueursPresentsHeliport && listeTresorComplete && (pilotePresent || carteHelicoPresente)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean inondee(String nomTuile) {
        Tuile tuile = grille.getTuileAvecNom(nomTuile);
        if (tuile.getEtat() != EtatTuile.coulée) {
            tuile.setEtat((tuile.getEtat() == EtatTuile.sèche ? EtatTuile.inondée : EtatTuile.coulée));
            if (tuile.getEtat() == EtatTuile.coulée) {
                coule(tuile);
            }
            return true;
        } else {
            return false;
        }
    }

    public void coule(Tuile tuile) {    //Si une tuile coule on vérifie que ça ne tue pas un aventurier si sa en tue un la partie est perdu
        for (Aventurier joueur : joueurs) {
            if (joueur.getC() == tuile.getColonne() && joueur.getL() == tuile.getLigne()) {
                partiePerdue = !evasion(joueur);
            }
        }
    }

    public boolean evasion(Aventurier a) { //Vérifie qu'un aventurier coincer sur une tuile qui coule peut s'échaper
        int l = 0;
        int c = 0;

        boolean[][] gBool = a.deplacementPossible(grille);
        while (l < 6 && gBool[l][c] != false) {
            if (c == 5) {
                l++;
                c = 0;
            } else {
                c++;
            }
        }
        return (l < 6) || (a.getRole() == "Pilote");
    }

    public Aventurier joueurSuivant() { //Plutot clair comme méthode !
        int n = joueurs.indexOf(joueurCourant);
        if (n < joueurs.size() - 1) {
            return joueurs.get(n + 1);
        } else {
            return joueurs.get(0);
        }
    }

    public boolean perdrePartie() {
        /*
        1. Si les 2 tuiles « Temple », « Caverne », « Palais» ou « Jardin » (sur lesquelles sont placés les 
symboles des trésors) sombrent avant que vous n’ayez pris leurs trésors respectifs ;
        2. Si « l’héliport » sombre ;
        3. Si un joueur est sur une tuile Île qui sombre et qu’il n’y a pas de tuile adjacente où nager ;
        4. Si le Marqueur de niveau atteint la tête de mort.        
         */

        //cas 1
        if (grille.getTuileAvecNom("Le Temple du Soleil").getEtat() == EtatTuile.coulée
                && grille.getTuileAvecNom("Le Temple de La Lune").getEtat() == EtatTuile.coulée
                && Aventurier.TresorsObtenus(grille.getTuileAvecNom("Le Temple du Soleil").getTresor()) == false
                || grille.getTuileAvecNom("La Caverne des Ombres").getEtat() == EtatTuile.coulée
                && grille.getTuileAvecNom("La Caverne du Brasier").getEtat() == EtatTuile.coulée
                && Aventurier.TresorsObtenus(grille.getTuileAvecNom("La Caverne des Ombres").getTresor()) == false
                || grille.getTuileAvecNom("Le Palais des Marees").getEtat() == EtatTuile.coulée
                && grille.getTuileAvecNom("Le Palais de Corail").getEtat() == EtatTuile.coulée
                && Aventurier.TresorsObtenus(grille.getTuileAvecNom("Le Palais des Marees").getTresor()) == false
                || grille.getTuileAvecNom("Le Jardin des Murmures").getEtat() == EtatTuile.coulée
                && grille.getTuileAvecNom("Le Jardin des Hurlements").getEtat() == EtatTuile.coulée
                && Aventurier.TresorsObtenus(grille.getTuileAvecNom("Le Jardin des Murmures").getTresor()) == false) {
            return true;
        }

        //Cas 2
        if (grille.getTuileAvecNom("Heliport").getEtat() == EtatTuile.coulée) {
            return true;
        }

        //cas 3 : 3. Si un joueur est sur une tuile Île qui sombre 
        //et qu’il n’y a pas de tuile adjacente où nager ;
        //PLONGEUR & HELICO DIFF 
        if (partiePerdue) { //modifié dans la méthode evasions<coulee<inonde
            return true;
        }

        //Cas 4
        if (niveauInnondation() == 6) { // 6 correspond à la tête de mort
            return true;
        }

        return false;
    }

    public boolean deplacementPossible() {
        boolean[][] gPossible = joueurCourant.deplacementPossible(grille);
        boolean[][] gInitiale = new boolean[6][6];
        joueurCourant.initialisation(gInitiale);

        if (gPossible.equals(gInitiale)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean assechementPossible() {
        boolean[][] gPossible = joueurCourant.assechementPossible(grille);
        boolean[][] gInitiale = new boolean[6][6];
        joueurCourant.initialisation(gInitiale);

        if (gPossible.equals(gInitiale)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean prendreTresorPossible() {
        int l = joueurCourant.getL();
        int c = joueurCourant.getC();

        Tuile[][] tuiles = grille.getTuiles();

        //On récupère le trésor étant sur la tuile
        Tresor tr = tuiles[l][c].getTresor();

        // S'il y a bien un trésor sur la tuile && les joueurse ne possède pas déjà ce trésor
        if (tr != null && !(joueurCourant.getTresor().contains(tr))) {
            //On récupère les cartes Trésor correspondant au trésor de la tuile
            ArrayList<CarteTresor> cartesTre = joueurCourant.mainTresor(tr);
            //Si le joueur possède plus de 4 cartes de ce trésor il peut le récuper
            if (cartesTre.size() >= 4) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public ArrayList<Aventurier> aventuriersPourDonnerCarte(Aventurier a) {
        int li = a.getL();
        int co = a.getC();

        ArrayList<Aventurier> aventurierOK = new ArrayList<Aventurier>();

        if (a.estRole("Messager")) {
            aventurierOK = joueurs;
        } else {
            for (Aventurier j : joueurs) {
                int l = j.getL();
                int c = j.getC();

                if (li == l && co == c && !(j.equals(joueurCourant))) {
                    aventurierOK.add(j);
                }
            }
        }

        return aventurierOK;
    }

    public boolean donnerCartePossible() {
        ArrayList<Aventurier> joueursReception = aventuriersPourDonnerCarte(joueurCourant);
        if (joueursReception.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean utiliserCartePossible(CarteTresor carte) {
        String nom = carte.getNom();
        //La carte est une carte qu'on peut utliser (autre qu'une carte trésor)
        if (nom.equals("sac_sable") || nom.equals("hélico") || nom.equals("montée_eau")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean actionSpecialePossible() {
        //Si le joueur est un ingénieur
        if (joueurCourant.estRole("Ingénieur")) {
            Ingénieur ingenieur = (Ingénieur) joueurCourant;
            //Si le double asséchement est impossible
            if (!(ingenieur.doubleAssechementPossible(grille))) {
                //On met à jour sa capacité spéciale
                ingenieur.setCapaciteUtilisee(-1);
                joueurCourant = ingenieur;
                return false;
            } else {
                //On met à jour sa capacité spéciale
                ingenieur.setCapaciteUtilisee(0);
                joueurCourant = ingenieur;
                return true;
            }
        } else if (joueurCourant.estRole("Pilote")) {
            return !((Pilote) joueurCourant).capaciteUtilisee();

        } else {
            return false;
        }
    }

    public void actionPossible() {

        //Pour le bouton "Déplacer"
        vueA.getBtnBouger().setEnabled(deplacementPossible()); // fois le nombre de bouton --> à compléter

        //Pour le bouton "Assécher"
        //Pour le bouton "Prendre trésor"
        //Pour le bouton "Donner carte"
        // Pour le bouton "Utiliser carte"
        //Pour le bouton "Action spéciale"
    }

    @Override
    public void traiterMessage(Message m) {
        boolean[][] g = new boolean[6][6];
        TypesMessages type = m.getType();

        switch (type) {

            case DEPLACER:
                if (m.getTuile() == null) {
                    g = joueurCourant.deplacementPossible(grille);
                    // On affiche l'IHM avec les tuiles possibles
                    vueA.afficherTuilePossible(g, getGrille());
                } else {
                    String nom = m.getTuile();
                    Tuile tuile = grille.getTuileAvecNom(nom);

                    int l = tuile.getLigne();
                    int c = tuile.getColonne();

                    joueurCourant.deplacer(l, c);

                    //On décrémente le nombre d'action
                    nbAction = nbAction - 1;

                    //Si le joueur n'a plus d'action on fini son tour
                    actionPossible();
                    if (nbAction == 0) {
                        vueA.finirTour();
                    } else {
                        //Sinon on affiche les actions possibles
                        actionPossible();
                    }
                }

                break;

            case ASSECHER:
                // Si la tuile est null cela signifie qu'on vient d'appuyer sur le bouton "Assécher"
                if (m.getTuile() == null) {
                    g = joueurCourant.assechementPossible(grille);
                    // On affiche l'IHM avec les tuiles possibles
                    vueA.afficherTuilePossible(g, getGrille());
                } else {
                    //Sinon on asséche la tuile choisie
                    String nom = m.getTuile();
                    Tuile tuile = grille.getTuileAvecNom(nom);
                    tuile.asseche();

                    nbAction = nbAction - 1;

                    //Si le joueur n'a plus d'action on fini son tour
                    if (nbAction == 0) {
                        vueA.finirTour();
                    } else {
                        //Sinon on affiche les actions possibles
                        actionPossible();
                    }

                }
                break;

            case SPECIALE:
                if (joueurCourant.estRole("Pilote")) {
                    // Si la tuile est null cela signifie qu'on vient d'appuyer sur le bouton "Action spéciale"
                    if (m.getTuile() == null) {
                        // On affiche l'IHM avec les tuiles possibles
                        g = ((Pilote) joueurCourant).deplacementPossibleSpecial(grille);
                        vueA.afficherTuilePossible(g, getGrille());
                    } else {
                        //Sinon on déplace le pilote et on met à jour sa capacité utilisée
                        String nom = m.getTuile();
                        Tuile tuile = grille.getTuileAvecNom(nom);

                        int l = tuile.getLigne();
                        int c = tuile.getColonne();

                        joueurCourant.deplacer(l, c);
                        ((Pilote) joueurCourant).setCapaciteUtilisee(true);
                    }
                } else {
                    //Sinon il s'agit de l'ingénieur
                    // Si la tuile est null cela signifie qu'on vient d'appuyer sur le bouton "Action spéciale"
                    if (m.getTuile() == null) {
                        // On met à jour sa capacité utilisée
                        ((Ingénieur) joueurCourant).setCapaciteUtilisee(1);
                        vueA.assechementIngenieur();
                    } else {
                        //Sinon on assèche la tuile choisie
                        String nom = m.getTuile();
                        Tuile tuile = grille.getTuileAvecNom(nom);
                        tuile.asseche();
                        Ingénieur ingenieur = (Ingénieur) joueurCourant;

                        //Si sa capacité utilisée = 1, le joueur en est à son 1er asséchement
                        if (ingenieur.getCapaciteUtilisee() == 1) {
                            //On incrémente son nombre d'action pour qu'une fois décrémenté cela n'ai pas d'incidence
                            nbAction = nbAction + 1;
                            ingenieur.setCapaciteUtilisee(2);
                            vueA.assechementIngenieur();
                        } else if (ingenieur.getCapaciteUtilisee() == 2) {
                            //Si sa capacité utilisée = 2, le joueur en est à son 2eme asséchement
                            //On met à jour sa capacité spéciale
                            ingenieur.setCapaciteUtilisee(0);
                            actionPossible();
                        }
                        joueurCourant = ingenieur;
                    }
                }

                //On décrémente le nombre d'action
                nbAction = nbAction - 1;
                
                break;

            case DONNER_CARTE:
                /* if (joueurCourant instanceof Messager) {
                    //vueA.afficherJoueurPossible(joueurs);
                } else {
                    ArrayList<Aventurier> aventurier = getDonnerCartePossible(joueurCourant);
                    //vueA.afficherJoueurPossible(aventurier);
                } */

                if (nbAction == 0) {
                    vueA.finirTour();
                }
                break;

            case PRENDRE_TRESOR:
                prendreTresor(joueurCourant);
                nbAction = nbAction - 1;

                if (nbAction == 0) {
                    vueA.finirTour();
                }
                break;

            case UTILISER_CARTE:

                break;

            case NOUVELLE_PARTIE:
                initJoueur(m.getNbJoueur(), m.getNom());
                vueI.desafficher();
                vueA = new VueAventurier(joueurCourant, grille);

                actionPossible();

                vueA.addObservateur(
                        this);
                actionPossible();

                vueA.afficher();
                break;

            case TOUR_SUIVANT:
                //On désaffiche la vue
                vueA.desafficher();

                //Si le joueur était un pilote, on met à jour sa capacité spéciale
                if (joueurCourant.estRole("Pilote")) {
                    ((Pilote) joueurCourant).setCapaciteUtilisee(false);
                }

                // Ici on verifie que la partie n'est ni perdu ni gagner pour continuer
                if (partiePerdue) {
                    vueA.perdu();
                } else if (gagnerPartie()) {
                    vueA.gagner();
                } else {
                    //Le joueur courant est le joueur suivant
                    int n = joueurs.indexOf(joueurCourant);
                    if (n < joueurs.size() - 1) {
                        joueurCourant = joueurs.get(n + 1);
                    } else {
                        joueurCourant = joueurs.get(0);
                    }
                    actionPossible();

                    //On initialise le nombre d'actions selon si c'est un navigateur ou non
                    nbAction = (joueurCourant.estRole("Navigateur") ? 4 : 3);

                    //On créer une nouvelle vue Aventurier
                    vueA = new VueAventurier(joueurCourant, grille);
                    vueA.addObservateur(this);
                    vueA.afficher();

                }

                break;

        }
    }

}
