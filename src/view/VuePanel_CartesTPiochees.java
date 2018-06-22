/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Cartes.CarteTresor;
import Enumeration.TypesMessages;
import forbidden_island.Message;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author cabezama
 * Affiche les cartes trésors piochées.
 */
public class VuePanel_CartesTPiochees extends JPanel {

    
    public VuePanel_CartesTPiochees(ArrayList<CarteTresor> cartesTresors, IHMJeu ihm) {
        setLayout(new BorderLayout());
        setBackground(new Color(0, 0, 0, 210));
        
        JLabel pioche = new JLabel("VOus avez pioché!");
        this.add(pioche, BorderLayout.NORTH);
        
        JPanel affiche = new JPanel(new GridLayout(1,2));
        affiche.setOpaque(false);
        for(CarteTresor carte : cartesTresors){
            JLabel aff = new JLabel(carte.getNom());
            aff.setFont(new Font("Arial", Font.PLAIN, 20));
            affiche.add(aff);
        }
        
        this.add(affiche, BorderLayout.CENTER);
        JButton ok = new JButton("ok");
        this.add(ok, BorderLayout.SOUTH);
       
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
