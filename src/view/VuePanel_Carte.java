/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Cartes.CarteTresor;
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
    private CarteTresor carte;
    private int nb;
    private JButton donner;
    private JButton defausser;
    private JButton utiliser;
    private VuePanel_Main ihm;
    private VuePanel_Carte vueCarte;

    //Panel layout des boutons
    private JPanel mainPanel;

    public VuePanel_Carte(int n, CarteUtilisable c) {

        carte = new CarteTresor(c);
        nb = n;
        this.setPreferredSize(new Dimension(90, 120));

        if (n != 0) {
            donner = new JButton("Donner");
            defausser = new JButton("Défausser");
            utiliser = new JButton("Utiliser");

            //Layout des boutons sur la carte
            mainPanel = new JPanel(new GridLayout(3, 1));
            //  mainPanel.add(new JLabel());
            mainPanel.add(donner);
            mainPanel.add(defausser);
            // mainPanel.add(new JLabel());
            mainPanel.add(utiliser);
            //mainPanel.add(new JLabel());

            add(mainPanel);
            vueCarte = this;

            //ActionListener des boutons
            donner.addActionListener(
                    new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Message m = new Message(TypesMessages.DONNER_CARTE);
                    ihm.setSauvType(TypesMessages.DONNER_CARTE);
                    m.setVueCarte(vueCarte);
                    ihm.notifierObservateur(m);
                }
            });

            defausser.addActionListener(
                    new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Message m = new Message(TypesMessages.DEFAUSSER_CARTE);
                    ihm.setSauvType(TypesMessages.DEFAUSSER_CARTE);
                    m.setVueCarte(vueCarte);
                    ihm.notifierObservateur(m);
                }
            });

            utiliser.addActionListener(
                    new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    Message m;
                    if (carte.getNom().equals(CarteUtilisable.HELICO.toString())) {
                        m = new Message(TypesMessages.UTILISER_CARTE_HELICO);
                    } else {
                        m = new Message(TypesMessages.UTILISER_CARTE_SAC_SABLE);
                    }
                    ihm.setSauvType(m.getType());
                    m.setVueCarte(vueCarte);
                    ihm.notifierObservateur(m);
                }
            });
        }

    }

    @Override
    public void paint(Graphics g) {
        int size = ((this.getSize().width > this.getSize().height ? this.getSize().height : this.getSize().width)) - 2;
        setDim(new Dimension(size, size));

        if (nb != 0) {
            g.drawImage(carte.utilisation().getImage(), 0, 0, dim.width, dim.height, null);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, dim.height / 4));
            g.drawString("x " + nb, dim.width / 2, dim.height / 4);
        }
    }

    public void setDim(Dimension dim) {
        this.dim = dim;
    }

    public void setIhm(VuePanel_Main ihm) {
        this.ihm = ihm;
    }

    public JButton getDonner() {
        return donner;
    }

    public JButton getUtiliser() {
        return utiliser;
    }

    public CarteTresor getCarte() {
        return carte;
    }

    public JButton getDefausser() {
        return defausser;
    }

}
