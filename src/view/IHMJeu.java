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
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author blanquan
 */
public class IHMJeu extends Observe {

    //composants fenêtre
    private final JFrame window;

    private VueInitialisation vIni;
    private VueNiveau vNiveau;
    private VuePlateau vPlat;
    private VueCoequipierAventurier vAven;
    private VueActionAventurier vActionAven;
    private VueInventaireAventurier vMainAven;

    private ArrayList<Aventurier> joueurs;
    private Aventurier joueurCourant;
    private Grille grille;
    private int jaugeInnondation;

    public IHMJeu() {

        //Ouverture fenêtre initialisation
        vIni = new VueInitialisation(this);

        vIni.setVisible(true);
        vIni.repaint();
        this.window = new JFrame();
        window.setLayout(new BorderLayout());
        window.setVisible(false);

    }

    public void afficherInitiale(Grille g, ArrayList<Aventurier> joueurs, Aventurier a, int jauge) {
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(1400, 800);

        //initialisation variable
        setGrille(g);
        setAventurier(a);
        this.joueurs = joueurs;
        jaugeInnondation = jauge;
        vNiveau = new VueNiveau(jaugeInnondation);
        vPlat = new VuePlateau(grille, this.joueurs, this);
        vPlat.majTuiles(joueurs);
        vAven = new VueCoequipierAventurier(joueurCourant, this.joueurs, this);
        vActionAven = new VueActionAventurier(joueurCourant, this);
        vMainAven = new VueInventaireAventurier(joueurCourant, this);
        joueurCourant = a;
        //Fin de l'initialisation

        JPanel mainPanel = new JPanel(new BorderLayout());

        //Création 2 panels dans Panel Principale > "1" pour le "haut/centre", "2" pour le sud
        JPanel panelCentre1 = new JPanel(new BorderLayout());
        JPanel panelSud2 = new JPanel(new BorderLayout());

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
        window.setVisible(true);
    }

    public void afficher(Grille g, ArrayList<Aventurier> joueurs, Aventurier a, int jauge) {
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(1400, 800);

        //initialisation variable
        setGrille(g);
        setAventurier(a);
        this.joueurs = joueurs;
        jaugeInnondation = jauge;
        vNiveau = new VueNiveau(jaugeInnondation);
        vPlat.majTuiles(joueurs);
        vAven = new VueCoequipierAventurier(joueurCourant, this.joueurs, this);
        vMainAven = new VueInventaireAventurier(joueurCourant, this);
        joueurCourant = a;
        //Fin de l'initialisation

     //   JPanel mainPanel = new JPanel(new BorderLayout());

      /*  //Création 2 panels dans Panel Principale > "1" pour le "haut/centre", "2" pour le sud
        JPanel panelCentre1 = new JPanel(new BorderLayout());
        JPanel panelSud2 = new JPanel(new BorderLayout());

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

        window.add(mainPanel);*/
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

    public VuePlateau getvPlat() {
        return vPlat;
    }

    public VueActionAventurier getvActionAven() {
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
}
