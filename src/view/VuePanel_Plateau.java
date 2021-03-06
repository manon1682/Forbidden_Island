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
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author cdlk
 */
public class VuePanel_Plateau extends JPanel {

    private Grille grille;
    private boolean[][] gPossible;
    private VuePanel_Tuile[][] tuiles;
    private IHMJeu ihmJeu;
    private ArrayList<Aventurier> joueurs;
    private boolean activer;
    private VuePanel_Plateau plat;
    private BufferedImage fond;

    private BufferedImage contourPassage;
    private BufferedImage contourCourant;
    
    
    public VuePanel_Plateau(Grille grille, ArrayList<Aventurier> js, IHMJeu ihm) {
        activer = false;
        this.grille = grille;
        ihmJeu = ihm;
        joueurs = js;
        tuiles = new VuePanel_Tuile[6][6];
        initTuiles(grille);
        this.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, Color.black));
        
        try{
            contourPassage = ImageIO.read((new FileInputStream("images/hover_movement.png")));
            contourCourant = ImageIO.read((new FileInputStream("images/hover_assecher.png")));
            fond = ImageIO.read((new FileInputStream("images/background/test.jpg")));
        } catch (IOException ex) {
            ex.fillInStackTrace();
        }
        
        this.setLayout(new GridLayout(6, 6));
        for (int l = 0; l < 6; l++) {
            for (int c = 0; c < 6; c++) {
                this.add(tuiles[l][c]);
            }
        }
        plat = this;
    }

    public void majTuiles(Grille grille) {
        Tuile[][] ts = grille.getTuiles();
        for (int l = 0; l < 6; l++) {
            for (int c = 0; c < 6; c++) {
                if (grille.getTuiles()[l][c] != null){
                    ((VuePanel_Tuile) tuiles[l][c]).setEtat(ts[l][c].getEtat());
                } else {
                    tuiles[l][c] = new VuePanel_Tuile("Ocean", EtatTuile.COULEE);
                }
            }
        }
        repaintTuiles();
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
       repaintTuiles();
    }
    
    public void initTuiles(Grille grille) {
        Tuile[][] ts = grille.getTuiles();
        for (int l = 0; l < 6; l++) {
            for (int c = 0; c < 6; c++) {
                if (grille.getTuiles()[l][c] != null) {
                    tuiles[l][c] = new VuePanel_Tuile(ts[l][c].getNom(), ts[l][c].getEtat(), new Dimension(100, 100),this);
                } else {
                    tuiles[l][c] = new VuePanel_Tuile("Ocean", EtatTuile.COULEE);
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
                    tuiles[l][c].setPossible(true);
                }
            }
        }
    }
    
    public void desaficherPossible(){
        for(int l = 0; l<6 ; l++){
            for(int c = 0; c<6 ; c++){
                tuiles[l][c].setPossible(false);
            }
        }
        this.repaint();
    }

    public void repaintTuiles(){
        for(int l = 0; l<6 ; l++){
            for(int c = 0; c<6 ; c++){
                tuiles[l][c].repaint();
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(fond, 0, 0, plat.getWidth() , plat.getHeight(),null);
        repaintTuiles();
    }

    public boolean[][] getgPossible() {
        return gPossible;
    }

    public void setgPossible(boolean[][] gPossible) {
        this.gPossible = gPossible;
    }
    
    public void transmettreMessage(Message m){
        this.ihmJeu.notifierObservateur(m);
    }
    
    public TypesMessages getType(){
        return ihmJeu.getSauvType();
    }

    public BufferedImage getContourPassage() {
        return contourPassage;
    }

    public BufferedImage getContourCourant() {
        return contourCourant;
    }
    
    //Méthode utilisée pour changer l'image des tuiles ne ayant un emplacement tresor vide
    public void majTresor(String tr){
        for(int l = 0; l<6 ; l++){
            for(int c = 0; c<6 ; c++){
                if((tuiles[l][c].possedeTresor() ? tuiles[l][c].getTresor().equals(tr): false)){
                    tuiles[l][c].setPossedeTresor(false);
                }
            }
        }
    }
    
    //Méthode utilisée pour afficher un cadre autour du joueur courant
    public void majCourant(Aventurier a){
        for(int l = 0; l<6 ; l++){
            for(int c = 0; c<6 ; c++){
                if(!tuiles[l][c].getJoueur().isEmpty()){
                    tuiles[l][c].setCourant(tuiles[l][c].getJoueur().contains(a.getPion()));
                } else {
                    tuiles[l][c].setCourant(false);
                }
            }
        }
    }
}
