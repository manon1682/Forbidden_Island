/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Aventurier.Aventurier;
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
public class VueCoequipierAventurier extends JPanel {

    //Case 1 de la grilleCoequipier
    private JButton btnPrendreTresor;
    private JLabel tresorCristalArdent; //sera transformé en image
    private JLabel tresorStatueZephir;  // "
    private JLabel tresorCaliceOrdre;   // "
    private JLabel tresorPierreSacre;   // "
    //créer 4 images supplémentaires si on peut pas modif transparance sur les images

    //nouvelle case
    private VuePanel_InventaireCoequipier vInv1;
    private VuePanel_InventaireCoequipier vInv2;
    private VuePanel_InventaireCoequipier vInv3;

    //case 5
    private VuePanel_MessageBox vText;

    //=====
    private IHMJeu ihm;

    private Aventurier a;
    private ArrayList<Aventurier> joueurs;

    public VueCoequipierAventurier(Aventurier aventurier, ArrayList<Aventurier> js, IHMJeu ihm) {

        //Initialisation
        this.ihm = ihm;
        a = aventurier;
        joueurs = js;
        //Case 1 de la grilleCoequipier
        btnPrendreTresor = new JButton("Prendre trésor");
        /*ImageIcon logoFermer = new ImageIcon("images/icones/iconClose.png");
        JLabel logoF = new JLabel();
        logoF.setIcon(logoFermer);*/
        tresorCristalArdent = new JLabel("tresorCA");
        tresorStatueZephir = new JLabel("tresorSZ");
        tresorCaliceOrdre = new JLabel("tresorCO");
        tresorPierreSacre = new JLabel("tresorPS");

        //nouvelle case 2 à 4
        vInv1 = new VuePanel_InventaireCoequipier();
        vInv2 = new VuePanel_InventaireCoequipier();
        vInv3 = new VuePanel_InventaireCoequipier();

        //case 5 
        vText = new VuePanel_MessageBox();

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
        containerCase1.add(btnPrendreTresor, BorderLayout.WEST);

        grilleCoequipier.add(containerCase1);

        //nouvelle case 2 à 4
        for (Aventurier joueur : joueurs) {
            if (!(joueur.equals(a))) {
                VuePanel_Main vInv = new VuePanel_Main(joueur);
                grilleCoequipier.add(vInv);
            }
        }
        /*grilleCoequipier.add(vInv1);
        grilleCoequipier.add(vInv2);
        grilleCoequipier.add(vInv3);*/

        //Case 5 de la grilleCoequipier (MessageBox)
        grilleCoequipier.add(vText);

        //Ajout des éléments à la fenêtre principale;
        this.add(grilleCoequipier);
        
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
    
    

}
