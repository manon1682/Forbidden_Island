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
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author blancoma
 */
public class PanelCarte extends JPanel {

    private Dimension dim;
    private CarteUtilisable carte;

    public PanelCarte(int n, CarteUtilisable c) {

        carte = c;
        setLayout(new BorderLayout());
        
        if (n != 0) {
            JPanel panelhaut = new JPanel(new BorderLayout());
            JLabel nombre = new JLabel("x " + n);
            panelhaut.add(nombre, BorderLayout.EAST);
            add(panelhaut, BorderLayout.NORTH);
        } else {
            add(new JLabel());
        }
    }

    @Override
    public void paint(Graphics g) {
        int size = ((this.getSize().width > this.getSize().height ? this.getSize().height : this.getSize().width)) - 2;
        setDim(new Dimension(size, size));
        g.drawImage(carte.getImage(), 0, 0, dim.width, dim.height, null);

    }

    public void setDim(Dimension dim) {
        this.dim = dim;
    }
}
