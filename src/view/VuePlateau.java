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

    private Grille grille;
    private VueTuile[][] tuiles;
    private boolean activer;
    
    public VuePlateau(Grille grille) {
        activer = false;
        this.grille = grille;
        tuiles = new VueTuile[6][6];
        initTuiles(grille);
        this.setLayout(new GridLayout(6, 6));
        for (int l = 0; l < 6; l++) {
            for (int c = 0; c < 6; c++) {
                this.add(tuiles[l][c]);
            }
        }
    }

    public void majTuiles(Grille grille) {
        Tuile[][] ts = grille.getTuiles();
        for (int l = 0; l < 6; l++) {
            for (int c = 0; c < 6; c++) {
                if (grille.getTuiles()[l][c] != null && grille.getTuiles()[l][c].getEtat() != EtatTuile.coulée) {
                    ((VueTuile) tuiles[l][c]).setEtat(ts[l][c].getEtat());
                } else {
                    tuiles[l][c] = new VueTuile("Ocean", EtatTuile.coulée);
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
    
    public void afficherPossible(boolean[][] gBool){
        activer = true;
        for(int l = 0; l<6 ; l++){
            for(int c = 0; c<6 ; c++){
                if(gBool[l][c]){
                    tuiles[l][c].setCadre(true);
                }
            }
        }
    }
    
    public void desaficherPossible(){
        for(int l = 0; l<6 ; l++){
            for(int c = 0; c<6 ; c++){
                tuiles[l][c].setCadre(false);
            }
        }
    }
    
    
}
