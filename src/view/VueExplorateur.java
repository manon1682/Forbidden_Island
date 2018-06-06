/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Aventurier.Aventurier;
import Aventurier.Explorateur;
import Enumeration.Couleur;
import java.awt.Color;

/**
 *
 * @author blancoma
 */
public class VueExplorateur extends VueAventurier {
    
    public VueExplorateur(String nomJoueur) {
        super(nomJoueur, "Explorateur", Color.GREEN);
    }
    
}
