/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Aventurier.Aventurier;
import Cartes.CarteInnondation;
import Cartes.CarteTresor;
import Enumeration.TypesMessages;
import forbidden_island.Message;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author cabezama Affiche les cartes trésors piochées.
 */
public class VuePanel_CartesPiochees extends JPanel {

    public VuePanel_CartesPiochees(ArrayList<CarteTresor> cartesTresors, ArrayList<CarteInnondation> cartesInnondation, String joueur, IHMJeu ihm) {
        setLayout(new BorderLayout());
        setBackground(new Color(0, 0, 0, 210));

        JLabel pioche = new JLabel("Pioche de " + joueur,SwingConstants.CENTER);
        pioche.setFont(new Font("Serif", Font.ITALIC, 35));
        pioche.setForeground(new Color(225, 221, 136));
        this.add(pioche, BorderLayout.NORTH);

        //Affichage cartes trésors
        JPanel affiche = new JPanel(new GridLayout(4, 1));
        affiche.setOpaque(false);
        JLabel piocheT = new JLabel("Cartes Trésor piochées : ");
        piocheT.setFont(new Font("Serif", Font.ITALIC, 25));
        piocheT.setForeground(new Color(225, 221, 136));
        affiche.add(piocheT);

        JPanel affl2 = new JPanel(new GridLayout(1, 6));
        affl2.setOpaque(false);
        affl2.add(new JLabel());
        affl2.add(new JLabel());
        for (CarteTresor carte : cartesTresors) {
            ImageIcon imgCarte = new ImageIcon(carte.utilisation().getImage());
            Image imageCT = imgCarte.getImage();
            Image newimgCT = imageCT.getScaledInstance(128, 188, java.awt.Image.SCALE_SMOOTH); // Redimension de l'icône 
            imgCarte = new ImageIcon(newimgCT);
            JLabel aff = new JLabel("",SwingConstants.CENTER);            
            aff.setIcon(imgCarte);
            aff.setForeground(Color.white);
            aff.setFont(new Font("Arial", Font.PLAIN, 20));
            affl2.add(aff);
        }
        affl2.add(new JLabel());
        affl2.add(new JLabel());
        
        affiche.add(affl2);

        //Affichage cartes inondation
        JLabel piocheI = new JLabel("Cartes Inondation piochées : ");
        piocheI.setFont(new Font("Serif", Font.ITALIC, 25));
        piocheI.setForeground(new Color(225, 221, 136));
        affiche.add(piocheI);
        JPanel affl4 = new JPanel(new GridLayout(1, cartesTresors.size()));
        affl4.setOpaque(false);
        for (CarteInnondation carte : cartesInnondation) {
            JLabel aff = new JLabel(carte.getLieu());
            aff.setForeground(Color.white);
            aff.setFont(new Font("Arial", Font.PLAIN, 20));
            affl4.add(aff);
        }
        affiche.add(affl4);

        this.add(affiche, BorderLayout.CENTER);

        //Bouton Ok
        JButton ok = new JButton("OK");
        ok.setFont(new Font("Serif", Font.ITALIC, 20));
        
        JPanel panOk = new JPanel(new GridLayout(1, 5));
        panOk.add(new JLabel());
        panOk.add(new JLabel());
        panOk.add(ok);
        panOk.add(new JLabel());
        panOk.add(new JLabel());
        panOk.setOpaque(false);
        
        this.add(panOk, BorderLayout.SOUTH);

        
        //ActionListener de ok
        ok.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypesMessages.TOUR_SUIVANT);
                ihm.notifierObservateur(m);
            }
        });

        setVisible(true);
    }

}
