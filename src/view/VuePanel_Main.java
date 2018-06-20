/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Aventurier.Aventurier;
import Cartes.CarteTresor;
import Enumeration.CarteUtilisable;
import Enumeration.TypesMessages;
import forbidden_island.Message;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author blanquan
 */
public class VuePanel_Main extends JPanel {

    private IHMJeu ihm;
    private Aventurier a;

    public VuePanel_Main(Aventurier aventurier, IHMJeu ihmJ) {

        //Initialisation
        ihm = ihmJ;
        a = aventurier;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        setLayout(new BorderLayout());
        JPanel main = new JPanel(new GridLayout(1, 6));
        JPanel panelNord = new JPanel(new BorderLayout());

        panelNord.add(new JLabel(aventurier.getPseudo()), BorderLayout.WEST);
        panelNord.add(new JLabel(aventurier.getRole()), BorderLayout.EAST);

        add(panelNord, BorderLayout.NORTH);
        add(main, BorderLayout.CENTER);

        CarteUtilisable laCarte = CarteUtilisable.PIERRE_SACRE;

        for (int i = 0; i < 6; i++) {
            int n = 0;
            //On regarde le nombre de carte correspondant à laCarte
            for (CarteTresor carte : a.getMainA()) {
                if (carte.getNom().equals(laCarte.toString())) {
                    n = n + 1;
                }
            }

            //Créer le panel carte
            JPanel carte = new VuePanel_Carte(n, laCarte);
            CarteTresor c = new CarteTresor(laCarte);

            carte.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Message m = new Message(TypesMessages.CARTE_CLICK);
                    m.setCarte(c);
                    ihm.notifierObservateur(m);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });

            JButton donner = new JButton("Donner");
            JButton defausser = new JButton("Défausser");
            JButton utiliser = new JButton("Utiliser");

            donner.setVisible(false);
            utiliser.setVisible(false);
            this.setPreferredSize(new Dimension(90, 120));

            //Layout des boutons sur la carte
            JPanel mainPanel = new JPanel(new GridLayout(6, 1));
            mainPanel.add(new JLabel());
            mainPanel.add(donner);
            mainPanel.add(defausser);
            mainPanel.add(new JLabel());
            mainPanel.add(utiliser);
            mainPanel.add(new JLabel());

            add(mainPanel);

            //ActionListener des boutons
            donner.addActionListener(
                    new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Message m = new Message(TypesMessages.DONNER_CARTE);
                    m.setCarte(c);
                    ihm.notifierObservateur(m);
                }
            });

            defausser.addActionListener(
                    new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Message m = new Message(TypesMessages.DEFAUSSER_CARTE);
                    m.setCarte(c);
                    ihm.notifierObservateur(m);
                }
            });

            utiliser.addActionListener(
                    new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Message m = new Message(TypesMessages.UTILISER_CARTE);
                    m.setCarte(c);
                    ihm.notifierObservateur(m);
                }
            });

            main.add(carte);
            laCarte = laCarte.getNext();

        }
        this.setPreferredSize(new Dimension(10, 120));

    }

    public VuePanel_Main(Aventurier aventurier) {
        //Initialisation
        a = aventurier;

        setLayout(new BorderLayout());
        JPanel main = new JPanel(new GridLayout(1, 6));
        JPanel panelNord = new JPanel(new BorderLayout());

        panelNord.add(new JLabel(aventurier.getPseudo()), BorderLayout.WEST);
        panelNord.add(new JLabel(aventurier.getRole()), BorderLayout.EAST);

        add(panelNord, BorderLayout.NORTH);
        add(main, BorderLayout.CENTER);

        CarteUtilisable laCarte = CarteUtilisable.PIERRE_SACRE;

        for (int i = 0; i < 6; i++) {
            int n = 0;
            for (CarteTresor carte : a.getMainA()) {
                if (carte.getNom().equals(laCarte.toString())) {
                    n = n + 1;
                }
            }

            JPanel carte = new VuePanel_Carte(n, laCarte);

            main.add(carte);
            laCarte = laCarte.getNext();

        }
    }

}
