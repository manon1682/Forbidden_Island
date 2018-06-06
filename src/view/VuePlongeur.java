/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Aventurier.Plongeur;
import Enumeration.Couleur;
import java.awt.Color;

/**
 *
 * @author blancoma
 */
public class VuePlongeur extends VueAventurier {
    
    public VuePlongeur(String nomJoueur, String nomAventurier, Color couleur) {
        super(nomJoueur, nomAventurier, couleur);
        a = new Plongeur(Couleur.NOIR, "Plongeur", 1, 2);
    }
    
}
