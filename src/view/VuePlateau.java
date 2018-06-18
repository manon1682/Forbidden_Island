/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Enumeration.EtatTuile;
import forbidden_island.Grille;
import forbidden_island.Tuile;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author cdlk
 */
public class VuePlateau extends JPanel {

    private JPanel[][] tuiles;

    public VuePlateau(Grille grille) {
        tuiles = new VueTuile[6][6];
        initTuiles(grille);
        this.setLayout(new GridLayout(6, 6));
        for (int l = 0; l < 6; l++) {
            for (int c = 0; c < 6; c++) {
            this.add(tuiles[l][c]);
            }
        }

    }

    @Override
    public void paintComponents(Graphics g) {
        for (int l = 0; l < 6; l++) {
            for (int c = 0; c < 6; c++) {
                if (tuiles[l][c] != null) {
                    tuiles[l][c].repaint();
                }
            }
        }
        this.repaint();
    }

    public void majTuiles(Grille grille) {
        Tuile[][] ts = grille.getTuiles();
        for (int l = 0; l < 6; l++) {
            for (int c = 0; c < 6; c++) {
                if (grille.getTuiles()[l][c] != null && grille.getTuiles()[l][c].getEtat() != EtatTuile.coulée) {
                    ((VueTuile) tuiles[l][c]).setNomTuile(ts[l][c].getNom());
                    ((VueTuile) tuiles[l][c]).setEtat(ts[l][c].getEtat());
                } else {
                    JPanel pan = new JPanel();
                    pan.setBackground(Color.blue);
                    tuiles[l][c] = pan;
                }
            }
        }
    }

    public void initTuiles(Grille grille) {
        Tuile[][] ts = grille.getTuiles();
        for (int l = 0; l < 6; l++) {
            for (int c = 0; c < 6; c++) {
                if (grille.getTuiles()[l][c] != null) {
                    tuiles[l][c] = new VueTuile(ts[l][c].getNom(), ts[l][c].getEtat(), new Dimension(50, 50));
                } else {
                    JPanel pan = new JPanel();
                    pan.setBackground(Color.blue);
                    tuiles[l][c] = pan;
                }
            }
        }
    }
}
