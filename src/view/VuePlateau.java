/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Aventurier.Aventurier;
import Enumeration.EtatTuile;
import Enumeration.TypesMessages;
import forbidden_island.Grille;
import forbidden_island.Message;
import forbidden_island.Tuile;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author cdlk
 */
public class VuePlateau extends JPanel {

    private Grille grille;
    private boolean[][] gPossible;
    private VueTuile[][] tuiles;
    private IHMJeu ihmJeu;
    private ArrayList<Aventurier> joueurs;
    private boolean activer;
    private VuePlateau plat;
    
    public VuePlateau(Grille grille, ArrayList<Aventurier> js, IHMJeu ihm) {
        activer = false;
        this.grille = grille;
        ihmJeu = ihm;
        joueurs = js;
        tuiles = new VueTuile[6][6];
        initTuiles(grille);
        
        this.setBackground(Color.blue);
        this.setLayout(new GridLayout(6, 6));
        for (int l = 0; l < 6; l++) {
            for (int c = 0; c < 6; c++) {
                this.add(tuiles[l][c]);
            }
        }
        plat = this;
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if(activer){
                    int l = getLigne(me.getY());
                    int c = getColonne(me.getX());
                    if(!tuiles[l][c].getNomTuile().equals("Ocean") && gPossible[l][c]){
                        plat.desaficherPossible();
                        Message m = new Message(TypesMessages.DEPLACER);
                        m.setTuile(tuiles[l][c].getNomTuile());
                        ihm.notifierObservateur(m);
                    }
                    activer = false;
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
        
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
    
    public void majTuiles(ArrayList<Aventurier> joueurs) {
        for (int l = 0; l < 6; l++) {
            for (int c = 0; c < 6; c++) {
                tuiles[l][c].getJoueur().clear();
            }
        }
        for(Aventurier j : joueurs){
            tuiles[j.getL()][j.getC()].getJoueur().add(j.getPion());
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
        setgPossible(gBool);
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
    
    private int getColonne(int x) { 
        return (x * 6) / this.getWidth();
    }
        
    private int getLigne(int y) { 
        return (y * 6) / this.getHeight();
    }

    public boolean[][] getgPossible() {
        return gPossible;
    }

    public void setgPossible(boolean[][] gPossible) {
        this.gPossible = gPossible;
    }
    
    
    
}
