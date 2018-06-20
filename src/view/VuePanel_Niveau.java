/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Enumeration.EtatTuile;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author blanquan
 */
public class VuePanel_Niveau extends JPanel {

    private int jaugeInnondation;
    private int jaugeInnondationPrecedente;
    
    private double precedentRatio;
    private JButton btnManuel;
    private BufferedImage imageJauge;
    private BufferedImage imagePointeur;
    
    public VuePanel_Niveau(int jaugeInn) {


        //initialisation
        this.jaugeInnondation = jaugeInn;
        jaugeInnondationPrecedente = jaugeInnondation;
        //fin ini
        precedentRatio = 0.825-(0.083393)*(jaugeInn-1);
        
        this.setPreferredSize(new Dimension(120,260));
        
        try {
            imageJauge = ImageIO.read((new FileInputStream("images/Niveau.png")));
            imagePointeur = ImageIO.read((new FileInputStream("images/pointeur2.png")));
        } catch (IOException ex) {
                ex.fillInStackTrace();
        }
        
    }
    
    @Override
    public void paint(Graphics g){
        
        g.drawImage(imageJauge, 0, 0, this.getWidth(), this.getHeight(), null);
        g.drawImage(imagePointeur,poiteurPosX(), poiteurPosY(), 30, 30, null);
        
    }
    
    public int poiteurPosX(){
        return this.getWidth()/3;
    }
    
    public int poiteurPosY(){
        if(jaugeInnondation - jaugeInnondationPrecedente != 0){
            double ratio;
            if(jaugeInnondation == 8 && jaugeInnondationPrecedente == 7){
                ratio = 0.075;
            } else {
                ratio = 0.083393;
            }
            jaugeInnondationPrecedente = jaugeInnondation;
            precedentRatio = (precedentRatio -ratio);
        }
        return (int)(this.getHeight()*precedentRatio)-15;
    }
    
    public void setJauge(int jauge){
        this.jaugeInnondation = jauge;
    }
    
}
