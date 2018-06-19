/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Aventurier.Aventurier;
import Cartes.CarteTresor;
import Enumeration.CarteUtilisable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author blanquan
 */
public class VueInventaireAventurier extends JPanel {

    private IHMJeu ihm;
    private Aventurier a;

    public VueInventaireAventurier(Aventurier aventurier, IHMJeu ihmJ) {

        //Initialisation
        ihm = ihmJ;
        a = aventurier;

        //Couleur pour les test
        this.setBackground(Color.red);

        setLayout(new GridLayout(1, 6));

        CarteUtilisable laCarte = CarteUtilisable.PIERRE_SACRE;

        for (int i = 0; i < 6; i++) {
            int n = 0;
            for (CarteTresor carte : a.getMainA()) {
                if (carte.getNom().equals(laCarte.toString())) {
                    n = n + 1;
                }
            }

            JPanel carte = new PanelCarte(n, laCarte);
            add(carte);
            laCarte = laCarte.getNext();

        }

    }
}
