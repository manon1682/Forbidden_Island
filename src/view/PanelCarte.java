/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Enumeration.CarteUtilisable;
import Enumeration.EtatTuile;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author blancoma
 */
public class PanelCarte extends JPanel {

    private Dimension dim;
    private CarteUtilisable carte;
    private int nb;
    private JButton donner;
    private JButton utiliser;

    public PanelCarte(int n, CarteUtilisable c) {

        carte = c;
        nb = n;
        donner = new JButton("Donner");
        utiliser = new JButton("Utiliser");
        
        donner.setVisible(false);
        utiliser.setVisible(false);
    }

    @Override
    public void paint(Graphics g) {
        int size = ((this.getSize().width > this.getSize().height ? this.getSize().height : this.getSize().width)) - 2;
        setDim(new Dimension(size, size));
        
        

        if (nb != 0) {
            g.drawImage(carte.getImage(), 0, 0, dim.width, dim.height, null);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, dim.width/5));
            g.drawString("x " + nb, dim.width - 20, dim.width/5);
        }
    }

    public void setDim(Dimension dim) {
        this.dim = dim;
    }

    public JButton getDonner() {
        return donner;
    }

    public JButton getUtiliser() {
        return utiliser;
    }
    
    
}
