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
public class VueNiveau extends JPanel {

    private int jaugeInnondation;
    private JButton btnManuel;

    public VueNiveau(int jaugeInn) {


        //initialisation
        this.jaugeInnondation = jaugeInn;
        //fin ini

        this.setBackground(Color.PINK);

        JPanel mainPanelNiveau = new JPanel(new BorderLayout());

        JLabel labelTitre = new JLabel("Niveau", JLabel.CENTER);
        mainPanelNiveau.add(labelTitre, BorderLayout.NORTH);
        labelTitre.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 14));

        ImageIcon imgNiveauEau = new ImageIcon("images/Niveau.png"); // load the image to a imageIcon
        Image imageNiveauEau = imgNiveauEau.getImage(); // transform it 
        Image newimageNiveauEau = imageNiveauEau.getScaledInstance(178, 466, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imgNiveauEau = new ImageIcon(newimageNiveauEau);  // transform it back
        JLabel labelNiveauEau = new JLabel();
        labelNiveauEau.setIcon(imgNiveauEau);

        mainPanelNiveau.add(labelNiveauEau, BorderLayout.CENTER); // img

        JPanel grilleStickJauge = new JPanel(new GridLayout(11, 1));
        grilleStickJauge.setBackground(Color.cyan);
        
              

        ImageIcon imgSticker = new ImageIcon("images/pointeur.png");
        Image imageSticker = imgSticker.getImage(); // transform it 
        Image newimgSticker = imageSticker.getScaledInstance(58, 45, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imgSticker = new ImageIcon(newimgSticker);  // transform it back 
        
        JLabel labelSticker = new JLabel();
        labelSticker.setIcon(imgSticker);
        
        System.out.println(jaugeInnondation);
        
        for (int i = 0; i < 11; i++) {
            
            if (i == 10-jaugeInnondation) {
                grilleStickJauge.add(labelSticker);
            } else {
                grilleStickJauge.add(new JLabel(""));
            }
              
        }

        mainPanelNiveau.add(grilleStickJauge, BorderLayout.WEST);
        
        // Bouton manuel
        btnManuel = new JButton();
        ImageIcon logoManuel = new ImageIcon("images/icones/iconBook.png");
        JLabel logoMan = new JLabel();
        logoMan.setIcon(logoManuel);
        btnManuel.setIcon(logoManuel);
        btnManuel.setBackground(Color.gray);
        btnManuel.setBorderPainted(false); 
        btnManuel.setOpaque(false);

        mainPanelNiveau.add(btnManuel, BorderLayout.SOUTH);
        
        //Action du bouton manuel
        btnManuel.addActionListener(
            new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VueManuel vMan = new VueManuel();
                vMan.afficher();
            }
        });
        
        this.add(mainPanelNiveau);
    }

}
