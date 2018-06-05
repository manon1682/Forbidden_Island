/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forbidden_island;

import Aventurier.Aventurier;
import Cartes.CarteTresor;

/**
 *
 * @author cabezama
 */
public class Message {
    // Liste des types de messages
    private TypesMessages type;  // type de message
    private Tuile tuile;    // lorsqu'on selectionne une tuile
    private Aventurier joueur; // pour savoir quel joueur joue
    private CarteTresor carte; // si on donne une carte

    public Message(TypesMessages t, Aventurier a) {
        type = t;
        joueur = a;
    }

    public TypesMessages getType() {
        return type;
    }

    public Tuile getTuile() {
        return tuile;
    }

    public Aventurier getJoueur() {
        return joueur;
    }

    public CarteTresor getCarte() {
        return carte;
    }

    public void setTuile(Tuile tuile) {
        this.tuile = tuile;
    }

    public void setJoueur(Aventurier joueur) {
        this.joueur = joueur;
    }

    public void setCarte(CarteTresor carte) {
        this.carte = carte;
    }
    
    
    
  
}
