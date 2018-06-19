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
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
    private JPanel imagePan;

    public VueNiveau(int jaugeInn) {

        System.out.println("koalapanda");

        //initialisation
        this.jaugeInnondation = jaugeInn;
        //fin ini

        this.setBackground(Color.PINK);

        JPanel mainPanelNiveau = new JPanel(new BorderLayout());

        JLabel labelTitre = new JLabel("Niveau", JLabel.CENTER);
        mainPanelNiveau.add(labelTitre, BorderLayout.NORTH);
        labelTitre.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 14));

        ImageIcon imgNiveauEau = new ImageIcon("images/Niveau.png"); // load the image to a imageIcon
        Image image = imgNiveauEau.getImage(); // transform it 
        Image newimg = image.getScaledInstance(200, 600, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imgNiveauEau = new ImageIcon(newimg);  // transform it back
        JLabel testLabel = new JLabel();
        testLabel.setIcon(imgNiveauEau);

        mainPanelNiveau.add(testLabel, BorderLayout.CENTER); // img

        JPanel grilleStickJauge = new JPanel(new GridLayout(40, 1));
        grilleStickJauge.setBackground(Color.cyan);
        for (int i = 0; i < 40; i++) {
            if (i % 2 == 0) {

                grilleStickJauge.add(new JLabel("CaseJauge n= " + i));

            } else {
                grilleStickJauge.add(new JLabel("---"));
            }
        }

        mainPanelNiveau.add(grilleStickJauge, BorderLayout.WEST);

        this.add(mainPanelNiveau);
    }

}
