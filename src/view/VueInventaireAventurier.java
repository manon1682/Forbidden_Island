/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Aventurier.Aventurier;
import Cartes.CarteTresor;
import Enumeration.CarteUtilisable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author blanquan
 */
public class VueInventaireAventurier extends JPanel {

    private IHMJeu ihm;
    private Dimension dim;
    private Aventurier a;

    public VueInventaireAventurier(Aventurier aventurier, IHMJeu ihmJ) {

        //Initialisation
        ihm = ihmJ;
        a = aventurier;

        //Couleur pour les test
        this.setBackground(Color.red);
        
        setLayout(new GridLayout(1, 6));
        
        //On créer une liste contenant toute les cartes que l'on veut afficher
        ArrayList<CarteUtilisable> listeNom = new ArrayList<>();
        listeNom.add(CarteUtilisable.CRISTAL_ARDENT);
        listeNom.add(CarteUtilisable.CALICE_DE_ORDRE);
        listeNom.add(CarteUtilisable.STATUE_DU_ZEPHIR);
        listeNom.add(CarteUtilisable.PIERRE_SACRE);
        listeNom.add(CarteUtilisable.HELICO);
        listeNom.add(CarteUtilisable.SAC_SABLE);

        for (CarteUtilisable nom : listeNom) {
            int n = 0;
            for (CarteTresor carte : a.getMainA()) {
                if (carte.getNom().equals(nom.toString())) {
                    n = n + 1;
                }
            }
            
            JPanel carte = new PanelCarte(n, nom);
            add(carte);

        }

    }
}
