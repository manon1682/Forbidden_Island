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
    private JLabel J1carte1; // à modif une fois qu'on a image
    private JLabel J2carte1; // à modif une fois qu'on a image
    private JLabel J3carte1; // à modif une fois qu'on a image
    private JLabel J1nbcarte1; //Joueur 1 nombres de cartes
    private JLabel J2nbcarte1; //Joueur 2 nombres de cartes
    private JLabel J3nbcarte1; //Joueur 3 nombres de cartes

    private JLabel J1carte2;// à modif une fois qu'on a image
    private JLabel J2carte2;// à modif une fois qu'on a image
    private JLabel J3carte2;// à modif une fois qu'on a image
    private JLabel J1nbcarte2;
    private JLabel J2nbcarte2;
    private JLabel J3nbcarte2;

    private JLabel J1carte3;// à modif une fois qu'on a image
    private JLabel J2carte3;// à modif une fois qu'on a image
    private JLabel J3carte3;// à modif une fois qu'on a image
    private JLabel J1nbcarte3;
    private JLabel J2nbcarte3;
    private JLabel J3nbcarte3;

    private JLabel J1carte4;// à modif une fois qu'on a image
    private JLabel J2carte4;// à modif une fois qu'on a image
    private JLabel J3carte4;// à modif une fois qu'on a image
    private JLabel J1nbcarte4;
    private JLabel J2nbcarte4;
    private JLabel J3nbcarte4;

    private JLabel J1carte5;// à modif une fois qu'on a image
    private JLabel J2carte5;// à modif une fois qu'on a image
    private JLabel J3carte5;// à modif une fois qu'on a image
    private JLabel J1nbcarte5;
    private JLabel J2nbcarte5;
    private JLabel J3nbcarte5;

    private JLabel J1carte6;// à modif une fois qu'on a image
    private JLabel J2carte6;// à modif une fois qu'on a image
    private JLabel J3carte6;// à modif une fois qu'on a image
    private JLabel J1nbcarte6;
    private JLabel J2nbcarte6;
    private JLabel J3nbcarte6;

    //nouvelle case
    private VuePanel_InventaireCoequipier vInv1;
    private VuePanel_InventaireCoequipier vInv2;
    private VuePanel_InventaireCoequipier vInv3;

    //case 5
    private VueMessageBox vText;

    //=====
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

        //nouvelle case 2 à 4
        vInv1 = new VuePanel_InventaireCoequipier();
        vInv2 = new VuePanel_InventaireCoequipier();
        vInv3 = new VuePanel_InventaireCoequipier();

        //case 5 
        vText = new VueMessageBox();

        a = aventurier;
        //fin initialisation

        //couleur pour les test
        this.setBackground(Color.ORANGE);

        JPanel grilleCoequipier = new JPanel(new GridLayout(5, 1));

        //Case 1 de la grilleCoequipier (Liste Tresor obtenus des Aventuriers et bouton fermerJeu)
        JPanel containerCase1 = new JPanel(new BorderLayout());
        JPanel grilleTresor = new JPanel(new GridLayout(1, 4));

        grilleTresor.add(tresorCristalArdent);
        grilleTresor.add(tresorStatueZephir);
        grilleTresor.add(tresorCaliceOrdre);
        grilleTresor.add(tresorPierreSacre);

        containerCase1.add(grilleTresor, BorderLayout.CENTER);
        containerCase1.add(btnFermerJeu, BorderLayout.EAST);

        grilleCoequipier.add(containerCase1);

        //nouvelle case 2 à 4
        grilleCoequipier.add(vInv1);
        grilleCoequipier.add(vInv2);
        grilleCoequipier.add(vInv3);


        //Case 5 de la grilleCoequipier (MessageBox)
        JPanel case5Temporaire = new JPanel(); //à suppr pour ihmText dès que possible
        case5Temporaire.setBackground(Color.GREEN);

        JLabel msgboxTempo = new JLabel("msg box");
        case5Temporaire.add(msgboxTempo);

        grilleCoequipier.add(case5Temporaire);

        //grilleCoequipier.add(ihm.getvText()); active dès que possible
        //Ajout des éléments à la fenêtre principale;
        this.add(grilleCoequipier);

    }

}
