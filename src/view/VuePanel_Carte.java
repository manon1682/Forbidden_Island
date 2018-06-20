/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Enumeration.CarteUtilisable;
import Enumeration.EtatTuile;
import Enumeration.TypesMessages;
import forbidden_island.Message;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author blancoma
 */
public class VuePanel_Carte extends JPanel {

    private Dimension dim;
    private CarteUtilisable carte;
    private int nb;
    private JButton donner;
    private JButton defausser;
    private JButton utiliser;
    private IHMJeu ihm;
    
    //Panel layout des boutons
    private JPanel mainPanel;

    public VuePanel_Carte(int n, CarteUtilisable c) {

        carte = c;
        nb = n;
        donner = new JButton("Donner");
        defausser = new JButton("Défausser");
        utiliser = new JButton("Utiliser");
        
        
        donner.setVisible(false);
        utiliser.setVisible(false);
        this.setPreferredSize(new Dimension(90, 120));
        
        
        //Layout des boutons sur la carte
        mainPanel = new JPanel(new GridLayout(6, 1));
        mainPanel.add(new JLabel());
        mainPanel.add(donner);
        mainPanel.add(defausser);
        mainPanel.add(new JLabel());
        mainPanel.add(utiliser);
        mainPanel.add(new JLabel());        
        
        //ActionListener des boutons
        donner.addActionListener(
            new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypesMessages.DONNER_CARTE);
                ihm.notifierObservateur(m);    
            }
        });
        defausser.addActionListener(
            new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypesMessages.DEFAUSSER_CARTE);
                ihm.notifierObservateur(m);
            }
        });
        utiliser.addActionListener(
            new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypesMessages.UTILISER_CARTE);
                ihm.notifierObservateur(m);
            }
        });
        
    }
    
    

    @Override
    public void paint(Graphics g) {
        int size = ((this.getSize().width > this.getSize().height ? this.getSize().height : this.getSize().width)) - 2;
        setDim(new Dimension(size, size));
        
        

        if (nb != 0) {
            g.drawImage(carte.getImage(), 0, 0, dim.width, dim.height, null);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, dim.height/4));
            g.drawString("x " + nb, dim.width/2, dim.height/4);
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
