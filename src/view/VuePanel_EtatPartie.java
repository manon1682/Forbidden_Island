/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Aventurier.Aventurier;
import Enumeration.Tresor;
import Enumeration.TypesMessages;
import forbidden_island.Message;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Clock;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author blanquan
 */
public class VuePanel_EtatPartie extends JPanel {

    //Case 1 de la grilleCoequipier
    private JButton btnPrendreTresor;
    private JLabel tresorCristalArdent; //sera transformé en image
    private JLabel tresorStatueZephir;  // "
    private JLabel tresorCaliceOrdre;   // "
    private JLabel tresorPierreSacre;   // "

    //créer 4 images supplémentaires si on peut pas modif transparance sur les images
    //=====
    private IHMJeu ihm;

    private Aventurier a;
    private ArrayList<Aventurier> joueurs;

    public VuePanel_EtatPartie(Aventurier aventurier, ArrayList<Aventurier> as, IHMJeu ihm) {

        //Initialisation
        this.ihm = ihm;
        a = aventurier;
        joueurs = as;
        //Case 1 de la grilleCoequipier
        btnPrendreTresor = new JButton("Prendre trésor");
        tresorCristalArdent = new JLabel();
        tresorStatueZephir = new JLabel();
        tresorCaliceOrdre = new JLabel();
        tresorPierreSacre = new JLabel();

        for (Tresor tr : aventurier.getTresor()) {
            ImageIcon logo;
            if (tr == Tresor.CALICE_DE_ORDRE) {
                logo = new ImageIcon("images/tresors/calice_old.png");
                tresorCaliceOrdre.setIcon(logo);
            } else if (tr == Tresor.CRISTAL_ARDENT) {
                logo = new ImageIcon("images/tresors/cristal_old.png");
                tresorCristalArdent.setIcon(logo);
            } else if (tr == Tresor.PIERRE_SACRE) {
                logo = new ImageIcon("images/tresors/pierre_old.png");
                tresorPierreSacre.setIcon(logo);
            } else {
                logo = new ImageIcon("images/tresors/zephyr_old.png");
                tresorStatueZephir.setIcon(logo);
            }
        }

        a = aventurier;
        //fin initialisation

        //couleur pour les test
        this.setBackground(Color.ORANGE);

        this.generation(null);
        
        //Fermeture du jeu
        btnPrendreTresor.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypesMessages.PRENDRE_TRESOR);
                ihm.notifierObservateur(m);
            }
        });

    }

    public JButton getBtnPrendreTresor() {
        return btnPrendreTresor;
    }

    public void donnerCarte(ArrayList<Aventurier> js) {
        this.removeAll();
        this.generation(js);
    }

    public void desactiverDonCarte() {
        this.removeAll();
        this.generation(null);
    }

    public void generation(ArrayList<Aventurier> js) {
        this.removeAll();
        JPanel grilleCoequipier = new JPanel(new GridLayout(4, 1));

        //Case 1 de la grilleCoequipier (Liste Tresor obtenus des Aventuriers et bouton fermerJeu)
        JPanel containerCase1 = new JPanel(new BorderLayout());
        JPanel grilleTresor = new JPanel(new GridLayout(1, 4));

        grilleTresor.add(tresorPierreSacre);
        grilleTresor.add(tresorStatueZephir);
        grilleTresor.add(tresorCristalArdent);
        grilleTresor.add(tresorCaliceOrdre);

        containerCase1.add(grilleTresor, BorderLayout.CENTER);
        containerCase1.add(btnPrendreTresor, BorderLayout.WEST);

        grilleCoequipier.add(containerCase1);

        //nouvelle case 2 à 4
        for (Aventurier joueur : joueurs) {
            if (!(joueur.equals(a))) {
                VuePanel_Main vInv = new VuePanel_Main(joueur, ihm, (js != null ? (js.contains(joueur) ? true : false) : false));
                grilleCoequipier.add(vInv);
            }
        }
        //Ajout des éléments à la fenêtre principale;
        this.add(grilleCoequipier);
    }
}
