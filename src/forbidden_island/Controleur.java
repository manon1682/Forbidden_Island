package forbidden_island;

import Enumeration.TypesMessages;
import Cartes.Deck_Innondation;
import Cartes.Deck_Tresor;
import Aventurier.*;
import Aventurier.Explorateur;
import Aventurier.Ingénieur;
import Aventurier.Messager;
import Cartes.CarteInnondation;
import Cartes.CarteTresor;
import Cartes.Deck;
import Enumeration.CarteUtilisable;
import Enumeration.Defaite;
import Enumeration.EtatTuile;
import Enumeration.Tresor;
import Enumeration.TypesNiveaux;

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
import view.IHMJeu;
import view.VuePanel_ActionAventurier;
import view.VuePanel_Carte;

public class Controleur implements Observateur {

    private Grille grille;
    private ArrayList<Aventurier> joueurs = new ArrayList<Aventurier>();
    private Deck_Tresor deck_T;
    private Deck_Innondation deck_I;
    private int jaugeInnondation; //débute à 1 et finit 10 > tête de mort
    private Aventurier joueurCourant;
    private IHMJeu vueIHMJeu;
    private int nbAction = 3;
    private boolean partiePerdue = false;
    private boolean defaussementEnCours;

    public Controleur() {
        initPlateau();
        initDeck();
        vueIHMJeu = new IHMJeu();
        vueIHMJeu.addObservateur(this);
    }

    public ArrayList<String> chargerNomTuile() {
        File fileNomTuile = new File("src/nomTuile");
        ArrayList<String> nomTuile = new ArrayList<>();
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
                        || (l == 5 && ((c == 0) || (c == 1) || (c == 4) || (c == 5)))) //On vérifie qu'on est dans les angles
                {
                    //On est dans un angles donc on met une tuile null
                    tuiles[l][c] = null;
                } else {
                    //Ici on modifie pour charger la map comme il faut pour la demo:
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
        deck_I = new Deck_Innondation();
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

            ArrayList<CarteTresor> main = new ArrayList<>();

            for (int j = 0; j < 2; j++) {
                //On pioche une carte
                CarteTresor carte = (CarteTresor) deck_T.pioche();
                //Tant que le joueur pioche une carte montée des eaux
                while (carte.getNom().equals(CarteUtilisable.MONTEE_EAU.toString())) {
                    //On remet cette carte dans la pioche
                    deck_T.getPioche().add(carte);
                    //On mélange la pioche
                    deck_T.melangerPioche();
                    //On pioche une nouvelle carte
                    carte = (CarteTresor) deck_T.pioche();
                }
                //On ajoute la carte piochée à la main
                main.add(carte);
            }

            //On lui ajoute une main sans "montée des eaux"
            joueurs.get(i).ajouterCartesMain(main);
            roles.remove(rand);
        }

        //On initialise le joueur courant au 1er joueur
        joueurCourant = joueurs.get(0);

    }

    public void initJauge(TypesNiveaux type) {
        switch (type) {
            case NOVICE:
                jaugeInnondation = 1;
                break;
            case NORMAL:
                jaugeInnondation = 2;
                break;
            case ELITE:
                jaugeInnondation = 3;
                break;
            case LEGENDAIRE:
                jaugeInnondation = 4;
                break;
        }
    }

    public void addDefausseT(CarteTresor carte) {
        getDeck_T().defausser(carte);
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

        //On affiche la fenêtre de jeu pour mettre à jour la main du joueur et les trésors
        vueIHMJeu.getvPlat().majTresor(tr.toString());
        vueIHMJeu.afficher(grille, joueurCourant, jaugeInnondation, nbAction);
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

        //1. si tous les joueurs sont présent sur la case héliport
        Tuile tuileHelico = grille.getTuileAvecNom("Heliport");

        boolean joueursPresentsHeliport = true;

        for (Aventurier j : joueurs) {
            if (j.getL() != tuileHelico.getLigne() || j.getC() != tuileHelico.getColonne()) {
                joueursPresentsHeliport = false;
            } //joueursPresentsHeliport n'est jamais mis à false si tous les joueurs sont présent dans la case.
        }

        //2. si la liste des tresorsObtenus des aventurier est complète
        boolean listeTresorComplete = Aventurier.getTresorsObtenus().size() == 4;

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
                if (cT.utilisation() == CarteUtilisable.HELICO) {
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
        if (tuile.getEtat() != EtatTuile.COULEE) {
            tuile.setEtat((tuile.getEtat() == EtatTuile.SECHE ? EtatTuile.INONDEE : EtatTuile.COULEE));
            if (tuile.getEtat() == EtatTuile.COULEE) {
                coule(tuile);
            }
            return true;
        } else {
            return false;
        }
    }

    public void coule(Tuile tuile) {    //Si une tuile coule on vérifie que ça ne tue pas un aventurier si ça en tue un la partie est perdue
        for (Aventurier joueur : joueurs) {
            if (joueur.getC() == tuile.getColonne() && joueur.getL() == tuile.getLigne()) {
                evasion(joueur);
            }
        }

    }

    public void evasion(Aventurier a) { //Vérifie qu'un aventurier coincé sur une tuile qui coule peut s'échaper

        //Méthode pour les déplacer aléatoirement autour d'eux
        boolean[][] gBool = a.deplacementPossible(grille);
        ArrayList<int[]> pos = new ArrayList<>();
        for (int l = 0; l < 6; l++) {
            for (int c = 0; c < 6; c++) {
                if (gBool[l][c]) {
                    int[] i = new int[2];
                    i[0] = l;
                    i[1] = c;
                    pos.add(i);
                }
            }
        }
        if (!pos.isEmpty()) {
            int rand = ThreadLocalRandom.current().nextInt(0, pos.size());
            a.deplacer(pos.get(rand)[0], pos.get(rand)[1]);
        } else if (a.estRole("Pilote")) {
            boolean[][] gBoolPilote = ((Pilote) a).deplacementPossibleSpecial(grille);
            ArrayList<int[]> posPilote = new ArrayList<>();
            for (int l = 0; l < 6; l++) {
                for (int c = 0; c < 6; c++) {
                    if (gBoolPilote[l][c]) {
                        int[] i = new int[2];
                        i[0] = l;
                        i[1] = c;
                        posPilote.add(i);
                    }
                }
            }
            if (!posPilote.isEmpty()) {
                int rand = ThreadLocalRandom.current().nextInt(0, posPilote.size());
                a.deplacer(posPilote.get(rand)[0], posPilote.get(rand)[1]);
            } else {
                partiePerdue = true;
            }
        } else {
            partiePerdue = true;
        }
        vueIHMJeu.afficher(grille, joueurCourant, jaugeInnondation, nbAction);
    }

    public Aventurier joueurSuivant() {
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

        //Cas 1 : un trésor a coulé
        if ((grille.getTuileAvecNom("Le Temple du Soleil").getEtat() == EtatTuile.COULEE
                && grille.getTuileAvecNom("Le Temple de La Lune").getEtat() == EtatTuile.COULEE
                && Aventurier.TresorsObtenus(grille.getTuileAvecNom("Le Temple du Soleil").getTresor()) == false)
                || (grille.getTuileAvecNom("La Caverne des Ombres").getEtat() == EtatTuile.COULEE
                && grille.getTuileAvecNom("La Caverne du Brasier").getEtat() == EtatTuile.COULEE
                && Aventurier.TresorsObtenus(grille.getTuileAvecNom("La Caverne des Ombres").getTresor()) == false)
                || (grille.getTuileAvecNom("Le Palais des Marees").getEtat() == EtatTuile.COULEE
                && grille.getTuileAvecNom("Le Palais de Corail").getEtat() == EtatTuile.COULEE
                && Aventurier.TresorsObtenus(grille.getTuileAvecNom("Le Palais des Marees").getTresor()) == false)
                || (grille.getTuileAvecNom("Le Jardin des Murmures").getEtat() == EtatTuile.COULEE
                && grille.getTuileAvecNom("Le Jardin des Hurlements").getEtat() == EtatTuile.COULEE
                && Aventurier.TresorsObtenus(grille.getTuileAvecNom("Le Jardin des Murmures").getTresor()) == false)) {
            vueIHMJeu.defaite(Defaite.TRESOR_COULE);
            return true;
        }

        //Cas 2 : l'héliport a coulé
        if (grille.getTuileAvecNom("Heliport").getEtat() == EtatTuile.COULEE) {
            vueIHMJeu.defaite(Defaite.HELIPORT_COULE);
            return true;
        }

        //cas 3 : Si un joueur est sur une tuile Île qui sombre 
        //et qu’il n’y a pas de tuile adjacente où nager ;
        //PLONGEUR & HELICO DIFF 
        if (partiePerdue) { //modifié dans la méthode evasions<coulee<inonde
            vueIHMJeu.defaite(Defaite.JOUEUR_NOYE);
            return true;
        }

        //Cas 4 : le niveau d'eau est trop haut
        if (niveauInnondation() == 6) { // 6 correspond à la tête de mort
            vueIHMJeu.defaite(Defaite.INONDATION_ELEVEE);
            return true;
        }

        return false;
    }

    public boolean deplacementPossible() {
        boolean[][] gPossible = joueurCourant.deplacementPossible(grille);

        for (int l = 0; l < 6; l++) {
            for (int c = 0; c < 6; c++) {
                if (gPossible[l][c]) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean assechementPossible() {
        boolean[][] gPossible = joueurCourant.assechementPossible(grille);

        for (int l = 0; l < 6; l++) {
            for (int c = 0; c < 6; c++) {
                if (gPossible[l][c]) {
                    return true;
                }
            }
        }

        return false;
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
        if ((nom.equals(CarteUtilisable.HELICO.toString())) || nom.equals(CarteUtilisable.SAC_SABLE.toString())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean actionSpecialePossible() {
        //Si le joueur est un ingénieur
        if (joueurCourant.estRole("Ingénieur")) {
            return ((Ingénieur) joueurCourant).doubleAssechementPossible(grille);
        } else if (joueurCourant.estRole("Pilote")) {
            return !((Pilote) joueurCourant).capaciteUtilisee();
        } else {
            return false;
        }
    }

    public void actionPossible() {

        defaussementEnCours = joueurCourant.getMainA().size() > 5;

        if (defaussementEnCours) {
            //On affiche un message
            vueIHMJeu.getVText().ajoutMessage("Défaussez vous de " + (joueurCourant.getMainA().size() - 5) + " carte(s)");

            //On récupère la vue des action de la vue de l'IHM Jeu
            VuePanel_ActionAventurier vueTemp = vueIHMJeu.getvActionAven();

            //Desactivation du bouton "Déplacer"
            vueTemp.getBtnDeplacer().setEnabled(false);

            //Desativation du bouton "Assécher"
            vueTemp.getBtnAssecher().setEnabled(false);

            //Desativation du bouton "Action spéciale"
            vueTemp.getBtnActionSpeciale().setEnabled(false);

            //Desativation du bouton "Terminer tour"
            vueTemp.getBtnTerminerTour().setEnabled(false);

            //Desativation du bouton "Prendre trésor"
            vueIHMJeu.getvAven().getBtnPrendreTresor().setEnabled(false);
        } else {
            if (vueIHMJeu.getVText().getMessage().equals("Défaussez vous de 1 carte(s)")) {
                vueIHMJeu.getVText().ajoutMessage("Plus besoin de vous défaussez");
            }
            //On récupère la vue des action de la vue de l'IHM Jeu
            VuePanel_ActionAventurier vueTemp = vueIHMJeu.getvActionAven();

            //Activation ou non du bouton "Déplacer"
            vueTemp.getBtnDeplacer().setEnabled(deplacementPossible());

            //Activation ou non du bouton "Assécher"
            vueTemp.getBtnAssecher().setEnabled(assechementPossible());

            //Activation ou non du bouton "Action spéciale"
            vueTemp.getBtnActionSpeciale().setEnabled(actionSpecialePossible());

            //Activation ou non du bouton "Prendre trésor"
            vueIHMJeu.getvAven().getBtnPrendreTresor().setEnabled(prendreTresorPossible());
            //Le bouton "Donner carte" sera activé uniquement lors d'un clic sur une carte (Donc dans traiter message)
            //Le bouton "Utiliser carte" sera activé uniquement lors d'un clic sur une carte (Donc dans traiter message)
        }

    }

    public void finirTour() {
        //On désactive les boutons des action sauf "Finir tour"
        vueIHMJeu.getvActionAven().finirTour();

        //On désactive le bouton prendre trésor
        vueIHMJeu.getvAven().getBtnPrendreTresor().setEnabled(false);
    }

    public ArrayList<CarteInnondation> tirageCarteInnondation() {
        ArrayList<CarteInnondation> cartes = new ArrayList<>();

        //Pioche autant de carte que la jauge l'indique
        for (int i = 0; i < niveauInnondation(); i++) {

            CarteInnondation carte = (CarteInnondation) deck_I.pioche();

            while (!(inondee(carte.getLieu().toString()))) {
                carte = (CarteInnondation) deck_I.pioche();
            }

            cartes.add(carte);
        }

        //Ajoute ces cartes à la défausse
        deck_I.getDefausse().addAll(cartes);

        return cartes;
    }

    public void tirageCarte() {
        //TIRAGE DES CARTES TRESORS
        //Le joueur pioche 2 cartes Trésors
        ArrayList<CarteTresor> cartesTresors = deck_T.piocher();

        //On ajoute les cartes à la main du joueur
        joueurCourant.getMainA().addAll(cartesTresors);

        //PREND EN COMPTE LA MONTEE DES EAUX
        //On regarde le nombre de carte montée des eaux que le joueur a pioché
        for (CarteTresor carte : cartesTresors) {
            if (carte.getNom().equals(CarteUtilisable.MONTEE_EAU.toString())) {
                //Monte la jauge d'un cran
                jaugeInnondation = jaugeInnondation + 1;
                //On supprime la carte de la main du joueur et on l'ajoute à la défausse
                joueurCourant.removeMainA(carte);
                deck_T.getDefausse().push(carte);

                //Si la défausse Innondation n'est pas vide
                if (!(deck_I.getDefausse().isEmpty())) {
                    //On mélange la défausse et la met dans la pioche
                    deck_I.melangerDefausse();
                    deck_I.getPioche().addAll(deck_I.getDefausse());
                }
            }
        }

        //TIRAGE DES CARTES INONDATIONS
        //Tire les carte inondations
        ArrayList<CarteInnondation> cartesInnondation = tirageCarteInnondation();
        //Pour repeindre la plateau avec les nouvelles cartes inondées
        vueIHMJeu.setGrille(grille);
        vueIHMJeu.getvPlat().majTuiles(grille);
        //On affiche les cartes piochées
        vueIHMJeu.afficherCartePiochees(cartesTresors, cartesInnondation, joueurCourant.getPseudo());

    }

    @Override
    public void traiterMessage(Message m) {
        boolean[][] g = new boolean[6][6];
        TypesMessages type = m.getType();

        if (type != TypesMessages.NOUVELLE_PARTIE
                && type != TypesMessages.CARTE_CLICK) {
            if (type != TypesMessages.DONNER_CARTE) {
                //On désaffiche les récepteurs précédemment encadrés
                vueIHMJeu.getvAven().desactiverDonCarte();
            }
            //On désaffiche les cartes précédemment encadrées
            vueIHMJeu.getvPlat().desaficherPossible();
        }

        switch (type) {

            case DEPLACER:
                // Si la tuile est null cela signifie qu'on vient d'appuyer sur le bouton "Déplacer"
                if (m.getTuile() == null) {
                    //On affiche un message
                    vueIHMJeu.getVText().ajoutMessage("Choisissez une tuile");

                    g = joueurCourant.deplacementPossible(grille);
                    // On affiche l'IHM avec les tuiles possibles
                    vueIHMJeu.afficherTuilePossible(g);
                } else {
                    //On affiche un message
                    vueIHMJeu.getVText().ajoutMessage("Déplacement fait");

                    String nom = m.getTuile();
                    Tuile tuile = grille.getTuileAvecNom(nom);

                    int l = tuile.getLigne();
                    int c = tuile.getColonne();

                    joueurCourant.deplacer(l, c);
                    vueIHMJeu.getvPlat().majTuiles(joueurs);
                }

                break;

            case ASSECHER:
                // Si la tuile est null cela signifie qu'on vient d'appuyer sur le bouton "Assécher"
                if (m.getTuile() == null) {
                    //On affiche un message
                    vueIHMJeu.getVText().ajoutMessage("Choisissez une tuile");

                    g = joueurCourant.assechementPossible(getGrille());
                    // On affiche l'IHM avec les tuiles possibles
                    vueIHMJeu.afficherTuilePossible(g);
                } else {
                    //On affiche un message
                    vueIHMJeu.getVText().ajoutMessage("Assèchement fait");

                    String nom = m.getTuile();
                    Tuile tuile = grille.getTuileAvecNom(nom);

                    tuile.asseche();
                    vueIHMJeu.getvPlat().majTuiles(grille);
                }

                break;

            case SPECIALE:
                if (joueurCourant.estRole("Pilote")) {
                    // Si la tuile est null cela signifie qu'on vient d'appuyer sur le bouton "Action spéciale"
                    if (m.getTuile() == null) {
                        //On affiche un message
                        vueIHMJeu.getVText().ajoutMessage("Choisissez une tuile");

                        g = ((Pilote) joueurCourant).deplacementPossibleSpecial(grille);
                        vueIHMJeu.afficherTuilePossible(g);
                    } else {
                        //On affiche un message
                        vueIHMJeu.getVText().ajoutMessage("Déplacement fait");

                        String nom = m.getTuile();
                        Tuile tuile = grille.getTuileAvecNom(nom);

                        int l = tuile.getLigne();
                        int c = tuile.getColonne();

                        joueurCourant.deplacer(l, c);
                        //On met à jour sa capacité spéciale
                        ((Pilote) joueurCourant).setCapaciteUtilisee(true);
                        vueIHMJeu.getvPlat().majTuiles(joueurs);
                    }
                } //Sinon il s'agit de l'ingénieur
                // Si la tuile est null cela signifie qu'on vient d'appuyer sur le bouton "Action spéciale"
                else if (m.getTuile() == null) {
                    //On affiche un message
                    vueIHMJeu.getVText().ajoutMessage("Choisissez deux tuile");

                    // On met à jour sa capacité utilisée
                    ((Ingénieur) joueurCourant).setCapaciteUtilisee(1);
                    g = joueurCourant.assechementPossible(getGrille());
                    vueIHMJeu.afficherTuilePossible(g);
                } else {
                    //On affiche un message
                    vueIHMJeu.getVText().ajoutMessage("Assèchement fait");

                    String nom = m.getTuile();
                    Tuile tuile = grille.getTuileAvecNom(nom);
                    tuile.asseche();

                    g = joueurCourant.assechementPossible(getGrille());
                    Ingénieur ingenieur = (Ingénieur) joueurCourant;

                    //Si sa capacité utilisée = 1, le joueur en est à son 1er asséchement
                    if (ingenieur.getCapaciteUtilisee() == 1) {
                        //On affiche un message
                        vueIHMJeu.getVText().ajoutMessage("Choisissez une 2ème tuile");

                        //On incrémente son nombre d'action pour qu'une fois décrémenté cela n'ai pas d'incidence
                        nbAction = nbAction + 1;
                        ingenieur.setCapaciteUtilisee(2);

                        //On désaffiche les anciennes tuiles et réaffiche les nouvelles
                        vueIHMJeu.getvPlat().desaficherPossible();
                        vueIHMJeu.afficherTuilePossible(g);
                        //On force le joueur a choisir une 2ème tuile
                        finirTour();
                    } else if (ingenieur.getCapaciteUtilisee() == 2) {
                        //Si sa capacité utilisée = 2, le joueur en est à son 2ème asséchement
                        //On met à jour sa capacité spéciale
                        ingenieur.setCapaciteUtilisee(0);

                        vueIHMJeu.afficher(grille, joueurCourant, jaugeInnondation, nbAction);
                    }
                    joueurCourant = ingenieur;
                    vueIHMJeu.getvPlat().majTuiles(grille);
                }

                break;

            case DONNER_CARTE:
                // Si le joueur est null cela signifie qu'on vient d'appuyer sur le bouton "Donner carte"
                if (m.getJoueur() == null) {
                    //On affiche un message
                    vueIHMJeu.getVText().ajoutMessage("Choisissez le joueur destinataire");

                    ArrayList<Aventurier> recepteurPossible = aventuriersPourDonnerCarte(joueurCourant);
                    vueIHMJeu.afficherJoueursPossible(recepteurPossible);
                } else {
                    //On affiche un message
                    vueIHMJeu.getVText().ajoutMessage("Carte donnée");

                    //Sinon on donne la carte au joueur choisi
                    joueurCourant.donnerCarte(m.getJoueur(), m.getVueCarte().getCarte());
                    vueIHMJeu.afficher(grille, joueurCourant, jaugeInnondation, nbAction);
                }

                break;

            case PRENDRE_TRESOR:
                //On affiche un message
                vueIHMJeu.getVText().ajoutMessage("Vous avez pris le trésor !");
                prendreTresor(joueurCourant);

                break;

            case UTILISER_CARTE_HELICO:
                //On vient juste d'appuyer sur le bouton "Utiliser"
                if (m.getTuile() == null) {
                    //On affiche un message
                    vueIHMJeu.getVText().ajoutMessage("Choisissez une tuile");
                    Tuile[][] tuiles = grille.getTuiles();

                    for (int l = 0; l < 6; l++) {
                        for (int c = 0; c < 6; c++) {
                            Tuile tuile = tuiles[l][c];
                            if (tuile != null) {
                                //On vérifie s'il est possible de se déplacer sur la tuile
                                g[l][c] = tuile.verifTuileD();
                            }
                        }
                    }

                    //Le joueur ne peut pas se déplacer sur sa propre tuile
                    g[joueurCourant.getL()][joueurCourant.getC()] = false;

                    vueIHMJeu.afficherTuilePossible(g);

                } else {
                    //On affiche un message
                    vueIHMJeu.getVText().ajoutMessage("Déplacement fait");

                    String nom = m.getTuile();
                    Tuile tuile = grille.getTuileAvecNom(nom);

                    int l = tuile.getLigne();
                    int c = tuile.getColonne();

                    joueurCourant.deplacer(l, c);

                    //On supprime la carte de la main du joueur et la met dans la défausse
                    CarteTresor ca = new CarteTresor(CarteUtilisable.HELICO);
                    joueurCourant.removeMainA(ca);
                    deck_T.getDefausse().push(ca);

                    vueIHMJeu.afficher(grille, joueurCourant, jaugeInnondation, nbAction);
                    vueIHMJeu.getvPlat().majTuiles(joueurs);
                }

                break;

            case UTILISER_CARTE_SAC_SABLE:
                //On vient juste d'appuyer sur le bouton "Utiliser"
                if (m.getTuile() == null) {
                    //On affiche un message
                    vueIHMJeu.getVText().ajoutMessage("Choisissez une tuile");
                    Tuile[][] tuiles = grille.getTuiles();

                    for (int l = 0; l < 6; l++) {
                        for (int c = 0; c < 6; c++) {
                            Tuile tuile = tuiles[l][c];
                            if (tuile != null) {
                                //On vérifie s'il est possible d'assècher la tuile
                                g[l][c] = tuile.verifTuileA();
                            }
                        }
                    }

                    vueIHMJeu.afficherTuilePossible(g);

                } else {
                    //On affiche un message
                    vueIHMJeu.getVText().ajoutMessage("Assèchement fait");

                    String nom = m.getTuile();
                    Tuile tuile = grille.getTuileAvecNom(nom);

                    tuile.asseche();

                    //On supprime la carte de la main du joueur et la met dans la défausse
                    CarteTresor c = new CarteTresor(CarteUtilisable.SAC_SABLE);
                    joueurCourant.removeMainA(c);
                    deck_T.getDefausse().push(c);

                    vueIHMJeu.afficher(grille, joueurCourant, jaugeInnondation, nbAction);
                    vueIHMJeu.getvPlat().majTuiles(grille);

                }

                break;

            case CARTE_CLICK:
                //Afficher le bouton "Utiliser carte" et "Donner carte" et "Défausser" avec la méthode qui renvoie un boolean
                VuePanel_Carte carte = m.getVueCarte();
                //Si ce n'est pas l'ingénieur lors de son action Spéciale, on affiche les actions possibles
                if (!(joueurCourant.estRole("Ingénieur") && ((Ingénieur) joueurCourant).getCapaciteUtilisee() == 2)) {
                    if (nbAction != 0) {
                        if (defaussementEnCours) {
                            // affiche le bouton Défausser
                            carte.getDefausser().setVisible(true);
                        } else {
                            //afficher le bouton Donner
                            carte.getDonner().setVisible(donnerCartePossible());
                        }
                    }
                    //Dans tous les cas on regarde si on peut l'utiliser
                    carte.getUtiliser().setVisible(utiliserCartePossible(carte.getCarte()));
                }

                break;

            case DEFAUSSER_CARTE:
                //On récupère la carte choisie
                CarteTresor c = m.getVueCarte().getCarte();
                //On l'enlève de la main du joueur et on la met dans la défausse
                joueurCourant.removeMainA(c);
                deck_T.getDefausse().push(c);
                //On affiche l'IHM qui sera mise à jour
                vueIHMJeu.afficher(grille, joueurCourant, jaugeInnondation, nbAction);

                break;

            case NOUVELLE_PARTIE:
                //On initialise les joueurs
                initJoueur(m.getNom().size(), m.getNom());
                //On initialise le niveau du jeu
                initJauge(m.getNiveau());
                //On désaffiche la fenêtre d'initialisation
                vueIHMJeu.desafficherIni();

                defaussementEnCours = false;

                //On initialise le nombre d'actions selon si c'est un navigateur ou non
                nbAction = (joueurCourant.estRole("Navigateur") ? 4 : 3);

                //Tirage de 6 cartes Inondation
                for (int i = 0; i < 6; i++) {
                    CarteInnondation crt = (CarteInnondation) deck_I.pioche();
                    //Inonde la carte
                    inondee(crt.getLieu().toString());
                    //L'ajoute à la défausse
                    deck_I.getDefausse().add(crt);
                }

                //On affiche la fenêtre de jeu
                vueIHMJeu.afficherInitiale(grille, joueurs, joueurCourant, jaugeInnondation, nbAction);
                //On affiche un message
                vueIHMJeu.getVText().ajoutMessage(joueurCourant.getRole() + " : " + joueurCourant.getPseudo() + " à vous de jouer");

                break;

            case TERMINER_TOUR:

                //Si le joueur était un pilote, on met à jour sa capacité spéciale
                if (joueurCourant.estRole("Pilote")) {
                    ((Pilote) joueurCourant).setCapaciteUtilisee(false);
                } else if(joueurCourant.estRole("Ingénieur")){
                    ((Ingénieur) joueurCourant).setCapaciteUtilisee(0);
                }

                tirageCarte();

                break;

            case TOUR_SUIVANT:
                // Ici on vérifie que la partie n'est ni perdu ni gagner pour continuer
                if (gagnerPartie()) {
                    //demande à l'IHM d'afficher la victoire
                    vueIHMJeu.victoire();
                } else if (!perdrePartie()) {
                    joueurCourant = joueurSuivant();

                    vueIHMJeu.getVText().ajoutMessage(joueurCourant.getRole() + " : " + joueurCourant.getPseudo() + " à vous de joueur");
                    //On initialise le nombre d'actions selon si c'est un navigateur ou non
                    nbAction = (joueurCourant.estRole("Navigateur") ? 4 : 3);

                    //On affiche l'IHM qui sera mise à jour
                    vueIHMJeu.afficher(grille, joueurCourant, jaugeInnondation, nbAction);
                }

                break;


        }

        if (type == TypesMessages.DEPLACER || type == TypesMessages.SPECIALE) {
            vueIHMJeu.getvPlat().majCourant(joueurCourant);
        }

        if (((type == TypesMessages.ASSECHER
                || type == TypesMessages.DEPLACER
                || type == TypesMessages.SPECIALE)
                && m.getTuile() != null) || (type == TypesMessages.DONNER_CARTE && m.getJoueur() != null)
                || type == TypesMessages.PRENDRE_TRESOR) {
            //On décrémente le nombre d'action
            nbAction = nbAction - 1;

            vueIHMJeu.miseAJourNbAction(nbAction);
        }

        //Si le joueur n'a plus d'action on fini son tour
        if (nbAction == 0) {
            finirTour();
        } //Si ce n'est pas l'ingénieur lors de son action Spéciale, on affiche les actions possibles
        else if (!(joueurCourant.estRole("Ingénieur") && ((Ingénieur) joueurCourant).getCapaciteUtilisee() == 2)) {
            actionPossible();
        }
    }
}
