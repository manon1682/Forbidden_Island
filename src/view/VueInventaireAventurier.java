/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Aventurier.Aventurier;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author blanquan
 */
public class VueInventaireAventurier extends JPanel {
    
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
    private IHMJeu ihm;
    
    protected Aventurier a;
    
    public VueInventaireAventurier(Aventurier aventurier, IHMJeu ihmJ) {

        ihm = ihmJ;
        //initialisation
        carte1 = new JLabel("img Cristal Ardent");
        carte2 = new JLabel("img Statue zéphir");
        carte3 = new JLabel("img Calice de l'onde");
        carte4 = new JLabel("img la Pierre sacre");
        carte5 = new JLabel("img sac de sable");
        carte6 = new JLabel("img helico");
        //nb cartes initialisé à 0
        nbcarte1 = new JLabel("x0");
        nbcarte2 = new JLabel("x0");
        nbcarte3 = new JLabel("x0");
        nbcarte4 = new JLabel("x0");
        nbcarte5 = new JLabel("x0");
        nbcarte6 = new JLabel("x0");
        
        a = aventurier;
        //fin initialisation
        
        //couleur pour les test
        this.setBackground(Color.red);

        JPanel grilleInventaire = new JPanel(new GridLayout(1, 6));
        
        JPanel containerCarte1 = new JPanel(new GridLayout(2, 1));
        JPanel containerCarte2 = new JPanel(new GridLayout(2, 1));
        JPanel containerCarte3 = new JPanel(new GridLayout(2, 1));
        JPanel containerCarte4 = new JPanel(new GridLayout(2, 1));
        JPanel containerCarte5 = new JPanel(new GridLayout(2, 1));
        JPanel containerCarte6 = new JPanel(new GridLayout(2, 1));
        
        for (int i = 0; i < 6; i++) {
            
            if (i == 0) {
                containerCarte1.add(carte1);
                containerCarte1.add(nbcarte1);
                grilleInventaire.add(containerCarte1);
            } else if (i == 1) {
                containerCarte2.add(carte2);
                containerCarte2.add(nbcarte2);
                grilleInventaire.add(containerCarte2);
            } else if (i == 2) {
                containerCarte3.add(carte3);
                containerCarte3.add(nbcarte3);
                grilleInventaire.add(containerCarte3);
            } else if (i == 3) {
                containerCarte4.add(carte4);
                containerCarte4.add(nbcarte4);
                grilleInventaire.add(containerCarte4);
            } else if (i == 4) {
                containerCarte5.add(carte5);
                containerCarte5.add(nbcarte5);
                grilleInventaire.add(containerCarte5);
            } else if (i == 5) {
                containerCarte6.add(carte6);
                containerCarte6.add(nbcarte6);
                grilleInventaire.add(containerCarte6);
            } else {
                grilleInventaire.add(new JLabel("")); //inutile ici
            }
            
        }
        
        this.add(grilleInventaire);
        
    }
}
