/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author cabezama
 */
public class VuePanel_Victoire extends JPanel {
    
    private JLabel motif;
    
    public VuePanel_Victoire() {
        setLayout(new BorderLayout());
        setBackground(new Color(0, 0, 0, 210));

        // NORD
        ImageIcon logoIle = new ImageIcon("images/logo/ile-interdite-logo.png");
        JLabel ile = new JLabel("",SwingConstants.CENTER);
        ile.setIcon(logoIle);
        
        this.add(ile, BorderLayout.NORTH);

        //CENTRE
        ImageIcon logoVic = new ImageIcon("images/victoire/victoire.png");
        JLabel vic = new JLabel("", SwingConstants.CENTER);
        vic.setIcon(logoVic);

        this.add(vic, BorderLayout.CENTER);

        //SUD


        motif = new JLabel("Vous avez récupéré tous les trésors et vous vous êtes enfuis en Hélicoptère!", SwingConstants.CENTER);
        motif.setFont(new Font("Serif", Font.ITALIC, 35));
        motif.setForeground(new Color(225, 221, 136));
        
        this.add(motif, BorderLayout.SOUTH);

        setVisible(true);
    }

}
