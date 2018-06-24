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
import java.util.ArrayList;
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

    private JButton btnManuel;
    
    private ArrayList<BufferedImage> imgsJauge;
    
    public VuePanel_Niveau(int jaugeInn) {

        //initialisation
        setJauge(jaugeInn);
        imgsJauge = new ArrayList<>();
        //fin ini;
        
        this.setPreferredSize(new Dimension(120,260));
        
        try {
            imgsJauge.add(ImageIO.read((new FileInputStream("images/jauge/tubeJauge1-1.png"))));
            imgsJauge.add(ImageIO.read((new FileInputStream("images/jauge/tubeJauge2-1.png"))));
            imgsJauge.add(ImageIO.read((new FileInputStream("images/jauge/tubeJauge3-2.png"))));
            imgsJauge.add(ImageIO.read((new FileInputStream("images/jauge/tubeJauge4-1.png"))));
            imgsJauge.add(ImageIO.read((new FileInputStream("images/jauge/tubeJauge5.png"))));
            imgsJauge.add(ImageIO.read((new FileInputStream("images/jauge/tubeJauge6.png"))));
            imgsJauge.add(ImageIO.read((new FileInputStream("images/jauge/tubeJauge7.png"))));
            imgsJauge.add(ImageIO.read((new FileInputStream("images/jauge/tubeJauge8.png"))));
            imgsJauge.add(ImageIO.read((new FileInputStream("images/jauge/tubeJauge9.png"))));
            imgsJauge.add(ImageIO.read((new FileInputStream("images/jauge/tubeJauge10.png"))));
        } catch (IOException ex) {
            ex.fillInStackTrace();
        }
    }
    
    @Override
    public void paint(Graphics g){
        
        g.drawImage(imgsJauge.get(jaugeInnondation-1), 0, 0, this.getWidth(), this.getHeight(), null);
    }
    
    public void setJauge(int jauge){
        this.jaugeInnondation = jauge;
    }
    
}