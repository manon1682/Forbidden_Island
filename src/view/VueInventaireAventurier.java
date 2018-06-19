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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author blanquan
 */
public class VueInventaireAventurier extends JPanel {
    
    private IHMJeu ihm;
    private Aventurier a;
    
    public VueInventaireAventurier(Aventurier aventurier, IHMJeu ihmJ) {

        //Initialisation
        ihm = ihmJ;
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
            //On regarde le nombre de carte correspondant à laCarte
            for (CarteTresor carte : a.getMainA()) {
                if (carte.getNom().equals(laCarte.toString())) {
                    n = n + 1;
                }
            }
            
            //Créer le panel carte
            JPanel carte = new PanelCarte(n, laCarte);
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
            
            main.add(carte);
            laCarte = laCarte.getNext();
            
        }
        
    }
    
    public VueInventaireAventurier(Aventurier aventurier) {
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
            
            JPanel carte = new PanelCarte(n, laCarte);
            
            main.add(carte);
            laCarte = laCarte.getNext();
            
        }
    }
}
