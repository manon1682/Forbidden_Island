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
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author blanquan
 */
public class VueNiveau extends JPanel {

    private Integer jaugeInnondation;
    private BufferedImage imgInnondation;
    private String nomImgTuile;

    public VueNiveau(int jaugeInn) {

        //initialisation
        this.jaugeInnondation = jaugeInn;
        //fin ini

        this.setBackground(Color.PINK);

        JPanel mainPanelNiveau = new JPanel(new BorderLayout());

        JLabel labelTitre = new JLabel("Niveau", JLabel.CENTER);
        mainPanelNiveau.add(labelTitre, BorderLayout.NORTH);
        labelTitre.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 14));

        JPanel grilleNiveau = new JPanel(new GridLayout(1, 2));

        JPanel grilleStickJauge = new JPanel(new GridLayout(10, 1));
        initImageNiveau();

        grilleNiveau.add(grilleStickJauge); //à gauche
        //grilleNiveau.add(imgInnondation); // img
        
                
        JPanel caseNiveauTemporaire = new JPanel(); //à suppr pour une image dès que possible        
        JLabel msgboxTempo = new JLabel("niveau eau");
        caseNiveauTemporaire.add(msgboxTempo);
        mainPanelNiveau.add(caseNiveauTemporaire);

        mainPanelNiveau.add(grilleNiveau, BorderLayout.CENTER);

        this.add(mainPanelNiveau);

    }

    public void initImageNiveau() {
        try {
            imgInnondation = ImageIO.read((new FileInputStream("images/Niveau.png")));
        } catch (IOException ex) {
            ex.fillInStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(imgInnondation, 0, 0, 10, 10, null);
    }
    
    
}
