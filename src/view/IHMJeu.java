/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Aventurier.Aventurier;
import Cartes.CarteInnondation;
import Cartes.CarteTresor;
import Enumeration.Defaite;
import Enumeration.TypesMessages;
import forbidden_island.Grille;
import forbidden_island.Observe;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author blanquan
 */
public class IHMJeu extends Observe {

    //Composants fenêtre
    private final JFrame window;

    private JPanel mainPanel;
    private JPanel panelCentre1;
    private JPanel panelSud2;
    private JPanel sousPanel1;

    //Vues qu'elle possède
    private VuePanel_Initialisation vIni;
    private VuePanel_Niveau vNiveau;
    private VuePanel_Plateau vPlat;
    private VuePanel_EtatPartie vEtatPartie;
    private VuePanel_ActionAventurier vActionAven;
    private VuePanel_Main vMainAven;
    private VuePanel_Victoire vVictoire;
    private VuePanel_Defaite vDefaite;
    private VuePanel_MessageBox vMessage;
    private VuePanel_CartesPiochees vPioche;

    //Variables
    private ArrayList<Aventurier> joueurs;
    private Aventurier joueurCourant;
    private Grille grille;
    private int jaugeInnondation;
    private VuePanel_Carte sauvCarte;
    private TypesMessages sauvType;

    public IHMJeu() {

        //Ouverture fenêtre initialisation
        vIni = new VuePanel_Initialisation(this);
        vIni.setVisible(true);
        vIni.repaint();
        this.window = new JFrame();
        window.setLayout(new BorderLayout());
        window.pack();
        window.setVisible(false);

    }

    public void afficherInitiale(Grille g, ArrayList<Aventurier> joueurs, Aventurier a, int jauge, int nbAction) {
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(1400, 800);
        window.setTitle("L'île Interdite");
        //initialisation variable
        setGrille(g);
        setAventurier(a);
        this.joueurs = joueurs;
        jaugeInnondation = jauge;
        vNiveau = new VuePanel_Niveau(jaugeInnondation);
        vPlat = new VuePanel_Plateau(grille, this.joueurs, this);
        vPlat.majTuiles(joueurs);
        vEtatPartie = new VuePanel_EtatPartie(joueurCourant, this.joueurs, this);
        vActionAven = new VuePanel_ActionAventurier(this, nbAction);
        vMainAven = new VuePanel_Main(joueurCourant, this);
        vMessage = new VuePanel_MessageBox();
        //Fin de l'initialisation

        mainPanel = new JPanel(new BorderLayout());

        //Création 2 panels dans Panel Principale > "1" pour le "haut/centre", "2" pour le sud
        panelCentre1 = new JPanel(new BorderLayout());
        panelSud2 = new JPanel(new BorderLayout());

        //Ajout 3 panel au Panel Centre1 >
//        panelCentre1.add(vNiveau, BorderLayout.WEST);
        panelCentre1.add(new JLabel(""), BorderLayout.WEST); //On enleve vueNiveauLeTempsDe

        panelCentre1.add(vPlat, BorderLayout.CENTER);
        vPlat.majCourant(joueurCourant);
        
        sousPanel1 = new JPanel(new BorderLayout());
        sousPanel1.add(vEtatPartie, BorderLayout.CENTER);
        sousPanel1.add(vMessage, BorderLayout.SOUTH);
        panelCentre1.add(sousPanel1, BorderLayout.EAST);

        //Ajout 2 panel au Panel Sud2 > vueMainAven centre / gauche | vueActionAven > à droite
        panelSud2.add(vMainAven, BorderLayout.WEST);
        panelSud2.add(vActionAven, BorderLayout.CENTER);

        //Ajout panel au Panel Principale
        mainPanel.add(panelCentre1, BorderLayout.CENTER);
        mainPanel.add(panelSud2, BorderLayout.SOUTH);

        window.add(mainPanel);

        window.setVisible(true);
    }

    public void afficher(Grille g, Aventurier a, int jauge, int nbAction) {
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(1400, 800);
        window.setResizable(true);
        //On enlève les panels liés à au joueur prédécent
        //panelCentre1.remove(vNiveau);
        mainPanel.removeAll();

        panelCentre1.remove(sousPanel1);
        sousPanel1.remove(vEtatPartie);
        sousPanel1.remove(vMessage);
        panelSud2.remove(vMainAven);
        panelSud2.remove(vActionAven);

        sousPanel1 = new JPanel(new BorderLayout());
        vEtatPartie = new VuePanel_EtatPartie(joueurCourant, this.joueurs, this);

        sousPanel1.add(vEtatPartie, BorderLayout.CENTER);
        sousPanel1.add(vMessage, BorderLayout.SOUTH);
        panelCentre1.add(sousPanel1, BorderLayout.EAST);

        //Mis à jour des variables
        setGrille(g);
        setAventurier(a);
        jaugeInnondation = jauge;

        //Mis à jour des vues
        //vNiveau.setJauge(jaugeInnondation);
        vEtatPartie = new VuePanel_EtatPartie(joueurCourant, this.joueurs, this);
        vMainAven = new VuePanel_Main(joueurCourant, this);
        vActionAven = new VuePanel_ActionAventurier(this, nbAction);
        vPlat.majTuiles(grille);
        vPlat.majTuiles(joueurs);
        vPlat.majCourant(joueurCourant);
        
        //On replace les nouveaux panels créés
        // panelCentre1.add(vNiveau, BorderLayout.WEST);
        panelCentre1.add(sousPanel1, BorderLayout.EAST);
        sousPanel1.add(vEtatPartie, BorderLayout.CENTER);
        sousPanel1.add(vMessage, BorderLayout.SOUTH);
        panelSud2.add(vMainAven, BorderLayout.WEST);
        panelSud2.add(vActionAven, BorderLayout.CENTER);

        mainPanel.add(panelCentre1, BorderLayout.CENTER);
        mainPanel.add(panelSud2, BorderLayout.SOUTH);

        mainPanel.updateUI();
        window.setVisible(true);

    }

    public void desafficher() {
        window.setVisible(false);
    }

    public void desafficherIni() {
        vIni.desafficher();
    }

    public void afficherTuilePossible(boolean[][] grille) {
        vPlat.afficherPossible(grille);
        vPlat.repaint();
    }

    public void afficherJoueursPossible(ArrayList<Aventurier> js) {
        vEtatPartie.donnerCarte(js);
        window.setVisible(true);
    }

    public void desafficherJoueursPossible() {
        vEtatPartie.desactiverDonCarte();
    }

    public void miseAJourNbAction(int nbAction) {
        vActionAven.misAJourNbAction(nbAction);
    }

    // Getter
    public void setAventurier(Aventurier aventurier) {
        this.joueurCourant = aventurier;
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    public VuePanel_Plateau getvPlat() {
        return vPlat;
    }

    public VuePanel_EtatPartie getvAven() {
        return vEtatPartie;
    }

    public VuePanel_Main getvMainAven() {
        return vMainAven;
    }

    public VuePanel_MessageBox getVText() {
        return vMessage;
    }

    public VuePanel_ActionAventurier getvActionAven() {
        return vActionAven;
    }

    //Getter et Setter de SauvType
    public TypesMessages getSauvType() {
        return sauvType;
    }

    public void setSauvType(TypesMessages t) {
        sauvType = t;
    }

    //Getter et Setter de SauvCarte
    public void setSauvCarte(VuePanel_Carte carte) {
        this.sauvCarte = carte;
    }

    public VuePanel_Carte getSauvCarte() {
        return sauvCarte;
    }

    //Affichage Victoire
    public void victoire() {
        window.setResizable(false);
        mainPanel.removeAll();
        vVictoire = new VuePanel_Victoire();
        mainPanel.add(vVictoire);

        window.setVisible(true);

    }

    //Affichage Défaite
    public void defaite(Defaite d) {
        window.setResizable(false);
        mainPanel.removeAll();
        vDefaite = new VuePanel_Defaite(d);
        mainPanel.add(vDefaite);

        window.setVisible(true);

    }

    //Appel de la vue d'affichage des carte piochées
    public void afficherCartePiochees(ArrayList<CarteTresor> cartesTresors, ArrayList<CarteInnondation> cartesInnondation, String joueur) {

        window.setResizable(false);
        mainPanel.removeAll();
        vPioche = new VuePanel_CartesPiochees(cartesTresors, cartesInnondation, joueur, this);
        mainPanel.add(vPioche);

        window.setVisible(true);

    }

}
