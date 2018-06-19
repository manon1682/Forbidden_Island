/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author blanquan
 */
public class VuePanel_InventaireCoequipier extends JPanel {
    
    private JLabel carte1; // à modif une fois qu'on a image
    private JLabel nbcarte1;
    private JLabel carte2;// à modif une fois qu'on a image
    private JLabel nbcarte2;
    private JLabel carte3;// à modif une fois qu'on a image
    private JLabel nbcarte3;
    private JLabel carte4;// à modif une fois qu'on a image
    private JLabel nbcarte4;    
    private JLabel carte5;// à modif une fois qu'on a image
    private JLabel nbcarte5;
    private JLabel carte6;// à modif une fois qu'on a image
    private JLabel nbcarte6;
    
    
    
    public VuePanel_InventaireCoequipier() {
        
        //initialisation
        carte1 = new JLabel("CA");
        carte2 = new JLabel("SZ");
        carte3 = new JLabel("CO");
        carte4 = new JLabel("PS");
        carte5 = new JLabel("SS");
        carte6 = new JLabel("H");
        
        //nb cartes Joueur j initialisé à 0

        nbcarte1 = new JLabel("x0");
        nbcarte2 = new JLabel("x0");
        nbcarte3 = new JLabel("x0");
        nbcarte4 = new JLabel("x0");
        nbcarte5 = new JLabel("x0");
        nbcarte6 = new JLabel("x0");
        //fin initialisation
        
        
        JPanel grilleInventaire = new JPanel(new GridLayout(1, 8));
        JPanel containerCarte1 = new JPanel(new GridLayout(2, 1));
        JPanel containerCarte2 = new JPanel(new GridLayout(2, 1));
        JPanel containerCarte3 = new JPanel(new GridLayout(2, 1));
        JPanel containerCarte4 = new JPanel(new GridLayout(2, 1));
        JPanel containerCarte5 = new JPanel(new GridLayout(2, 1));
        JPanel containerCarte6 = new JPanel(new GridLayout(2, 1));
        
        for (int i = 0; i < 8; i++) {
            
            if (i == 0) {
                JLabel Pseudo = new JLabel("Pseudo");
                grilleInventaire.add(Pseudo);                
            } else if (i == 1) {
                containerCarte1.add(carte1);
                containerCarte1.add(nbcarte1);
                grilleInventaire.add(containerCarte1);
            } else if (i == 2) {
                containerCarte2.add(carte2);
                containerCarte2.add(nbcarte2);
                grilleInventaire.add(containerCarte2);
            } else if (i == 3) {
                containerCarte3.add(carte3);
                containerCarte3.add(nbcarte3);
                grilleInventaire.add(containerCarte3);
            } else if (i == 4) {
                containerCarte4.add(carte4);
                containerCarte4.add(nbcarte4);
                grilleInventaire.add(containerCarte4);
            } else if (i == 5) {
                containerCarte5.add(carte5);
                containerCarte5.add(nbcarte5);
                grilleInventaire.add(containerCarte5);
            } else if (i == 6) {
                containerCarte6.add(carte6);
                containerCarte6.add(nbcarte6);
                grilleInventaire.add(containerCarte6);
            } else if (i == 7) {
                JLabel logoRole = new JLabel("LogoRole");
                grilleInventaire.add(logoRole);
            } else {
                grilleInventaire.add(new JLabel("")); //inutile ici
            }

        }
        
        this.add(grilleInventaire);
        
    }
    
    
    
}
