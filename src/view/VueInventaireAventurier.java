/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Aventurier.Aventurier;
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

    public VueInventaireAventurier(Aventurier aventurier) {

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

        JPanel grilleInventaire = new JPanel(new GridLayout(1, 6));

        for (int i = 0; i < 6; i++) {

            if (i == 0) {
                grilleInventaire.add(carte1);
                grilleInventaire.add(nbcarte1);
            } else if (i == 1) {
                grilleInventaire.add(carte2);
                grilleInventaire.add(nbcarte2);
            } else if (i == 2) {
                grilleInventaire.add(carte3);
                grilleInventaire.add(nbcarte3);
            } else if (i == 3) {
                grilleInventaire.add(carte4);
                grilleInventaire.add(nbcarte4);
            } else if (i == 4) {
                grilleInventaire.add(carte5);
                grilleInventaire.add(nbcarte5);
            } else if (i == 5) {
                grilleInventaire.add(carte6);
                grilleInventaire.add(nbcarte6);
            } else {
                grilleInventaire.add(new JLabel("")); //inutile ici
            }

        }
        
        this.add(grilleInventaire);

    }
}
