/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Aventurier.Aventurier;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author blanquan
 */
public class VueCoequipierAventurier extends JPanel {

    //Case 1 de la grilleCoequipier
    private JButton btnFermerJeu;
    private JLabel tresorCristalArdent; //sera transformé en image
    private JLabel tresorStatueZephir;  // "
    private JLabel tresorCaliceOrdre;   // "
    private JLabel tresorPierreSacre;   // "
    //créer 4 images supplémentaires si on peut pas modif transparance sur les images

    //Case 2 de la grilleCoequipier
    private JLabel carte1; // à modif une fois qu'on a image
    private JLabel J1nbcarte1; //Joueur 1 nombres de cartes
    private JLabel J2nbcarte1; //Joueur 2 nombres de cartes
    private JLabel J3nbcarte1; //Joueur 3 nombres de cartes

    private JLabel carte2;// à modif une fois qu'on a image
    private JLabel J1nbcarte2;
    private JLabel J2nbcarte2;
    private JLabel J3nbcarte2;

    private JLabel carte3;// à modif une fois qu'on a image
    private JLabel J1nbcarte3;
    private JLabel J2nbcarte3;
    private JLabel J3nbcarte3;

    private JLabel carte4;// à modif une fois qu'on a image
    private JLabel J1nbcarte4;
    private JLabel J2nbcarte4;
    private JLabel J3nbcarte4;

    private JLabel carte5;// à modif une fois qu'on a image
    private JLabel J1nbcarte5;
    private JLabel J2nbcarte5;
    private JLabel J3nbcarte5;

    private JLabel carte6;// à modif une fois qu'on a image
    private JLabel J1nbcarte6;
    private JLabel J2nbcarte6;
    private JLabel J3nbcarte6;

    //case 5
    private IHMJeu ihm;

    protected Aventurier a;

    public VueCoequipierAventurier(Aventurier aventurier) {

        //Initialisation
        //Case 1 de la grilleCoequipier
        btnFermerJeu = new JButton("Fermer Jeu");
        tresorCristalArdent = new JLabel("tresorCA");
        tresorStatueZephir = new JLabel("tresorSZ");
        tresorCaliceOrdre = new JLabel("tresorCO");
        tresorPierreSacre = new JLabel("tresorPS");
        
        //Case 2 de à 4
        carte1 = new JLabel("CA");
        carte2 = new JLabel("SZ");
        carte3 = new JLabel("CO");
        carte4 = new JLabel("PS");
        carte5 = new JLabel("SS");
        carte6 = new JLabel("H");
        
        //nb cartes Joueur1 initialisé à 0
        J1nbcarte1 = new JLabel("x0");
        J1nbcarte2 = new JLabel("x0");
        J1nbcarte3 = new JLabel("x0");
        J1nbcarte4 = new JLabel("x0");
        J1nbcarte5 = new JLabel("x0");
        J1nbcarte6 = new JLabel("x0");
        
        //nb cartes Joueur1 initialisé à 0
        J2nbcarte1 = new JLabel("x0");
        J2nbcarte2 = new JLabel("x0");
        J2nbcarte3 = new JLabel("x0");
        J2nbcarte4 = new JLabel("x0");
        J2nbcarte5 = new JLabel("x0");
        J2nbcarte6 = new JLabel("x0");
        
        //nb cartes Joueur1 initialisé à 0
        J3nbcarte1 = new JLabel("x0");
        J3nbcarte2 = new JLabel("x0");
        J3nbcarte3 = new JLabel("x0");
        J3nbcarte4 = new JLabel("x0");
        J3nbcarte5 = new JLabel("x0");
        J3nbcarte6 = new JLabel("x0");
        
        a = aventurier;
        //fin initialisation
        
        //couleur pour les test
        this.setBackground(Color.ORANGE);

        JPanel grilleCoequipier = new JPanel(new GridLayout(5, 1));

        //Case 1 de la grilleCoequipier (Liste Tresor obtenus des Aventuriers et bouton fermerJeu)
        JPanel containerCase1 = new JPanel(new BorderLayout());
        JPanel grilleTresor = new JPanel(new GridLayout(1, 4));

        grilleTresor.add(tresorCristalArdent);
        grilleTresor.add(tresorCristalArdent);
        grilleTresor.add(tresorCristalArdent);
        grilleTresor.add(tresorCristalArdent);

        containerCase1.add(containerCase1, BorderLayout.CENTER);
        containerCase1.add(btnFermerJeu, BorderLayout.EAST);

        grilleCoequipier.add(containerCase1);

        //Case 2 à 4 de la grilleCoequipier (inventaires coequipiers)
        JPanel J1grilleInventaire = new JPanel(new GridLayout(1, 6));
        JPanel J1containerCarte1 = new JPanel(new GridLayout(2, 1));
        JPanel J1containerCarte2 = new JPanel(new GridLayout(2, 1));
        JPanel J1containerCarte3 = new JPanel(new GridLayout(2, 1));
        JPanel J1containerCarte4 = new JPanel(new GridLayout(2, 1));
        JPanel J1containerCarte5 = new JPanel(new GridLayout(2, 1));
        JPanel J1containerCarte6 = new JPanel(new GridLayout(2, 1));
        
        JPanel J2grilleInventaire = new JPanel(new GridLayout(1, 6));
        JPanel J2containerCarte1 = new JPanel(new GridLayout(2, 1));
        JPanel J2containerCarte2 = new JPanel(new GridLayout(2, 1));
        JPanel J2containerCarte3 = new JPanel(new GridLayout(2, 1));
        JPanel J2containerCarte4 = new JPanel(new GridLayout(2, 1));
        JPanel J2containerCarte5 = new JPanel(new GridLayout(2, 1));
        JPanel J2containerCarte6 = new JPanel(new GridLayout(2, 1));
        
        JPanel J3grilleInventaire = new JPanel(new GridLayout(1, 6));
        JPanel J3containerCarte1 = new JPanel(new GridLayout(2, 1));
        JPanel J3containerCarte2 = new JPanel(new GridLayout(2, 1));
        JPanel J3containerCarte3 = new JPanel(new GridLayout(2, 1));
        JPanel J3containerCarte4 = new JPanel(new GridLayout(2, 1));
        JPanel J3containerCarte5 = new JPanel(new GridLayout(2, 1));
        JPanel J3containerCarte6 = new JPanel(new GridLayout(2, 1));
        
        //simplifiable 100%, nomdevariable avec variable d'après Matthias   
        for (int i = 0; i < 6; i++) {
            
            if (i == 0) {
                J1containerCarte1.add(carte1);
                J1containerCarte1.add(J1nbcarte1);
                J1grilleInventaire.add(J1containerCarte1);
            } else if (i == 1) {
                J1containerCarte2.add(carte2);
                J1containerCarte2.add(J1nbcarte2);
                J1grilleInventaire.add(J1containerCarte2);
            } else if (i == 2) {
                J1containerCarte3.add(carte3);
                J1containerCarte3.add(J1nbcarte3);
                J1grilleInventaire.add(J1containerCarte3);
            } else if (i == 3) {
                J1containerCarte4.add(carte4);
                J1containerCarte4.add(J1nbcarte4);
                J1grilleInventaire.add(J1containerCarte4);
            } else if (i == 4) {
                J1containerCarte5.add(carte5);
                J1containerCarte5.add(J1nbcarte5);
                J1grilleInventaire.add(J1containerCarte5);
            } else if (i == 5) {
                J1containerCarte6.add(carte6);
                J1containerCarte6.add(J1nbcarte6);
                J1grilleInventaire.add(J1containerCarte6);
            } else {
                J1grilleInventaire.add(new JLabel("")); //inutile ici
            }
            
        }
        
        for (int i = 0; i < 6; i++) {
            
            if (i == 0) {
                J2containerCarte1.add(carte1);
                J2containerCarte1.add(J2nbcarte1);
                J2grilleInventaire.add(J2containerCarte1);
            } else if (i == 1) {
                J2containerCarte2.add(carte2);
                J2containerCarte2.add(J2nbcarte2);
                J2grilleInventaire.add(J2containerCarte2);
            } else if (i == 2) {
                J2containerCarte3.add(carte3);
                J2containerCarte3.add(J2nbcarte3);
                J2grilleInventaire.add(J2containerCarte3);
            } else if (i == 3) {
                J2containerCarte4.add(carte4);
                J2containerCarte4.add(J2nbcarte4);
                J2grilleInventaire.add(J2containerCarte4);
            } else if (i == 4) {
                J2containerCarte5.add(carte5);
                J2containerCarte5.add(J2nbcarte5);
                J2grilleInventaire.add(J2containerCarte5);
            } else if (i == 5) {
                J2containerCarte6.add(carte6);
                J2containerCarte6.add(J2nbcarte6);
                J2grilleInventaire.add(J2containerCarte6);
            } else {
                J2grilleInventaire.add(new JLabel("")); //inutile ici
            }
            
        }
        
        for (int i = 0; i < 6; i++) {
            
            if (i == 0) {
                J3containerCarte1.add(carte1);
                J3containerCarte1.add(J3nbcarte1);
                J3grilleInventaire.add(J3containerCarte1);
            } else if (i == 1) {
                J3containerCarte2.add(carte2);
                J3containerCarte2.add(J3nbcarte2);
                J3grilleInventaire.add(J3containerCarte2);
            } else if (i == 2) {
                J3containerCarte3.add(carte3);
                J3containerCarte3.add(J3nbcarte3);
                J3grilleInventaire.add(J3containerCarte3);
            } else if (i == 3) {
                J3containerCarte4.add(carte4);
                J3containerCarte4.add(J3nbcarte4);
                J3grilleInventaire.add(J3containerCarte4);
            } else if (i == 4) {
                J3containerCarte5.add(carte5);
                J3containerCarte5.add(J3nbcarte5);
                J3grilleInventaire.add(J3containerCarte5);
            } else if (i == 5) {
                J3containerCarte6.add(carte6);
                J3containerCarte6.add(J3nbcarte6);
                J3grilleInventaire.add(J3containerCarte6);
            } else {
                J3grilleInventaire.add(new JLabel("")); //inutile ici
            }
            
        }
        
        grilleCoequipier.add(J1grilleInventaire);
        grilleCoequipier.add(J1grilleInventaire);
        grilleCoequipier.add(J1grilleInventaire);

        
        //Case 5 de la grilleCoequipier (MessageBox)
        grilleCoequipier.add(ihm.getvText());

        //Ajout des éléments à la fenêtre principale;
        this.add(grilleCoequipier);

    }

}
