/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

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
public class VuePlateau extends JPanel{

    public VuePlateau() {
    }
    
     
    
//    private VueTuile[][] tuiles;
//    
//    public VuePlateau(Grille grille){
//        tuiles = new VueTuile[6][6];
//        initTuiles(grille);
//        this.setLayout(new GridLayout(6,6));
//        Tuile tuile;
//        int l = 0;
//        int c = 0;
//        for(int i = 0; i<36 ; i++){
//            l = (c == 5? l+1 : l);
//            c = (c == 5? 0 : c+1);
//            if(grille.getTuiles()[l][c] != null){
//                tuile = grille.getTuiles()[l][c];
//                this.add(new VueTuile(tuile.getNom(),tuile.getEtat(), new Dimension(50, 50)));
//            } else {
//                JPanel pan = new JPanel();
//                pan.setBackground(Color.blue);
//                this.add(pan);
//            }
//        }
//        
//    }
//    
//    @Override
//    public void paintComponents(Graphics g){
//        
//        
//        
//    }
//    
//    public void majTuiles(Grille grille){
//        Tuile[][] ts = grille.getTuiles();
//        for (int l = 0; l < 6; l++) {
//            for (int c = 0; c < 6; c++) {
//                if(grille.getTuiles()[l][c] != null){
//                    tuiles[l][c].setNomTuile(ts[l][c].getNom());
//                    tuiles[l][c].setEtat(ts[l][c].getEtat());
//                }
//            }
//        }    
//    }
//    
//    public void initTuiles(Grille grille){
//        Tuile[][] ts = grille.getTuiles();
//        for (int l = 0; l < 6; l++) {
//            for (int c = 0; c < 6; c++) {
//                if(grille.getTuiles()[l][c] != null){
//                    tuiles[l][c] = new VueTuile(ts[l][c].getNom(),ts[l][c].getEtat(), new Dimension(50, 50));
//                } else {
//                    tuiles[l][c] = null;
//                }
//            }
//        }
//    }
}
