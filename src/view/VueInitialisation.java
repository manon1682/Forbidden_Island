/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Aventurier.Aventurier;
import Aventurier.Explorateur;
import Enumeration.Couleur;
import forbidden_island.Grille;
import forbidden_island.Message;
import forbidden_island.Observe;
import forbidden_island.Tuile;
import forbidden_island.TypesMessages;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.MatteBorder;
import util.Utils.Pion;

/**
 *
 * @author cabezama
 */

public class VueInitialisation {
    
    
        private final JFrame window;
        private final JPanel mainPanel;        
        private JPanel panelCentre;
        private JComboBox choixNbJoueur;
        private String[] nbjoueurs;
        private JPanel panelBas;
        private JButton valider;
        
        
    public VueInitialisation(){
        this.nbjoueurs = new String[]{"1","2","3","4"};
        this.window = new JFrame();
        window.setSize(350, 200);
        
        window.setTitle("Ile Interdite");
        mainPanel = new JPanel(new BorderLayout());
        this.window.add(mainPanel);

        mainPanel.setBackground(new Color(230, 230, 230));  
        
        // NORD
        mainPanel.add(BorderLayout.NORTH, new JLabel("Ile Interdite"));
        
        //Panel Centre
        panelCentre = new JPanel (new GridLayout(1,2));
        panelCentre.add(new JLabel("Nombre de Joueur : "));
        choixNbJoueur = new JComboBox(nbjoueurs);
        panelCentre.add(choixNbJoueur);
        mainPanel.add(BorderLayout.CENTER, panelCentre);
        
        //Panel bas
        mainPanel.add(BorderLayout.SOUTH, valider = new JButton("Valider"));
        
        
    }
    
    public static void main(String[] args) {
        VueInitialisation ihm = new VueInitialisation();
            ihm.window.setVisible(true);
    }
    
}
    
    

