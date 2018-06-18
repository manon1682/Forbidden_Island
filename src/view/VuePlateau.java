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

    private VueTuile[][] tuiles;

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
                tuiles[l][c].repaint();
            }
        }
        this.repaint();
    }

    public void majTuiles(Grille grille) {
        Tuile[][] ts = grille.getTuiles();
        for (int l = 0; l < 6; l++) {
            for (int c = 0; c < 6; c++) {
                if (grille.getTuiles()[l][c] != null && grille.getTuiles()[l][c].getEtat() != EtatTuile.coulée) {
                    ((VueTuile) tuiles[l][c]).setEtat(ts[l][c].getEtat());
                    int size = ((this.getSize().width > this.getSize().height ? this.getSize().height : this.getSize().width)/6)-20;
                    ((VueTuile) tuiles[l][c]).setDim(new Dimension(size,size));
                } else {
                    tuiles[l][c] = new VueTuile("Ocean", EtatTuile.coulée, new Dimension(100, 100));
                }
            }
        }
    }

    public void initTuiles(Grille grille) {
        Tuile[][] ts = grille.getTuiles();
        for (int l = 0; l < 6; l++) {
            for (int c = 0; c < 6; c++) {
                if (grille.getTuiles()[l][c] != null) {
                    tuiles[l][c] = new VueTuile(ts[l][c].getNom(), ts[l][c].getEtat(), new Dimension(100, 100));
                } else {
                    tuiles[l][c] = new VueTuile("Ocean", EtatTuile.coulée, new Dimension(100, 100));
                }
            }
        }
    }
}
