/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Aventurier.Aventurier;
import Enumeration.TypesMessages;
import forbidden_island.Grille;
import forbidden_island.Observe;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
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
    
    //Panels victoires et défaites
    private JPanel panTransparent;
    private JPanel panVictoire;
    private JPanel panDefaite;

    //Vues qu'elle possède
    private VuePanel_Initialisation vIni;
    private VuePanel_Niveau vNiveau;
    private VuePanel_Plateau vPlat;
    private VuePanel_EtatPartie vAven;
    private VuePanel_ActionAventurier vActionAven;
    private VuePanel_Main vMainAven;
    //private VuePanel_Superposition vSup;

    //Variables
    private ArrayList<Aventurier> joueurs;
    private Aventurier joueurCourant;
    private Grille grille;
    private int jaugeInnondation;

    public IHMJeu() {

        //Ouverture fenêtre initialisation
        vIni = new VuePanel_Initialisation(this);
        vIni.setVisible(true);
        vIni.repaint();
        this.window = new JFrame();
        window.setLayout(new BorderLayout());
        window.pack();
        window.setVisible(false);

        //vSup = new VuePanel_Superposition();
        

    }

    public void afficherInitiale(Grille g, ArrayList<Aventurier> joueurs, Aventurier a, int jauge, int nbAction) {
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(1400, 800);
        
        //initialisation variable
        setGrille(g);
        setAventurier(a);
        this.joueurs = joueurs;
        jaugeInnondation = jauge;
        vNiveau = new VuePanel_Niveau(jaugeInnondation);
        vPlat = new VuePanel_Plateau(grille, this.joueurs, this);
        vPlat.majTuiles(joueurs);
        vAven = new VuePanel_EtatPartie(joueurCourant, this.joueurs, this);
        vActionAven = new VuePanel_ActionAventurier(this, nbAction);
        vMainAven = new VuePanel_Main(joueurCourant, this);
        //Fin de l'initialisation

        mainPanel = new JPanel(new BorderLayout());

        //Création 2 panels dans Panel Principale > "1" pour le "haut/centre", "2" pour le sud
        panelCentre1 = new JPanel(new BorderLayout());
        panelSud2 = new JPanel(new BorderLayout());

        //Ajout 3 panel au Panel Centre1 >
        panelCentre1.add(vNiveau, BorderLayout.WEST);
        panelCentre1.add(vPlat, BorderLayout.CENTER);
        panelCentre1.add(vAven, BorderLayout.EAST);

        //Ajout 2 panel au Panel Sud2 > vueMainAven centre / gauche | vueActionAven > à droite
        panelSud2.add(vMainAven, BorderLayout.CENTER);
        panelSud2.add(vActionAven, BorderLayout.EAST);

        //Ajout panel au Panel Principale
        mainPanel.add(panelCentre1, BorderLayout.CENTER);
        mainPanel.add(panelSud2, BorderLayout.SOUTH);

        window.add(mainPanel);
         

        //vSup.addPanel(mainPanel, 1,0);
        window.setVisible(true);
    }

    public void afficher(Grille g, Aventurier a, int jauge, int nbAction) {
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(1400, 800);

        //On enlève les panels liés à au joueur prédécent
        //panelCentre1.remove(vNiveau);
        panelCentre1.remove(vAven);
        panelSud2.remove(vMainAven);
        panelSud2.remove(vActionAven);

        //Mis à jour des variables
        setGrille(g);
        setAventurier(a);
        jaugeInnondation = jauge;
        //Mis à jour des vues
        //vNiveau = new VuePanel_Niveau(jaugeInnondation);
        vNiveau.setJauge(jaugeInnondation);
        vAven = new VuePanel_EtatPartie(joueurCourant, this.joueurs, this);
        vMainAven = new VuePanel_Main(joueurCourant, this);
        vActionAven = new VuePanel_ActionAventurier(this, nbAction);
        vPlat.majTuiles(joueurs);
        
        //Fin de l'initialisation

        //On replace les nouveaux panels créés
        panelCentre1.add(vNiveau, BorderLayout.WEST);
        panelCentre1.add(vAven, BorderLayout.EAST);
       // panelCentre1.add(vPlat, BorderLayout.CENTER);
        panelSud2.add(vMainAven, BorderLayout.CENTER);
        panelSud2.add(vActionAven, BorderLayout.EAST);
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

    public void miseAJourNbAction(int nbAction) {
        vActionAven.misAJourNbAction(nbAction);
    }

    // Getter
    public Aventurier getAventurier() {
        return joueurCourant;
    }

    public void setAventurier(Aventurier aventurier) {
        this.joueurCourant = aventurier;
    }

    public Grille getGrille() {
        return grille;
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    public VuePanel_Plateau getvPlat() {
        return vPlat;
    }

    public VuePanel_EtatPartie getvAven() {
        return vAven;
    }

    public VuePanel_Main getvMainAven() {
        return vMainAven;
    }

    public VuePanel_ActionAventurier getvActionAven() {
        return vActionAven;
    }

    public void miseAJour(Aventurier a) {
        joueurCourant = a;
        window.setVisible(true);
    }

    public void assechementIngenieur() {

    }

    public TypesMessages getSauvType() {
        return vActionAven.getSauvType();
    }
    
    //Affichage Victoire
    public void Victoire() {
        
        panVictoire = new JPanel(new BorderLayout());
        
        JLabel victoire = new JLabel("Victoire!");
        panVictoire.setBackground(new Color(0,0,0,30));
        panVictoire.add(victoire, BorderLayout.SOUTH);
       // vSup.addPanel(panVictoire, 2, 1);
      
        
    }
    
    //Affichage Défaite
    public void Defaite() {
        
        panDefaite = new JPanel(new BorderLayout());
        JLabel victoire = new JLabel("Défaite...");
        panDefaite.add(victoire, BorderLayout.SOUTH);
        panDefaite.setBackground(new Color(0,0,0,200));
       // vSup.addPanel(panDefaite, 2,1);
        
        
        
    }
}
