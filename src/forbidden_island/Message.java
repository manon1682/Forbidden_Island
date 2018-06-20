/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forbidden_island;

import Enumeration.TypesMessages;
import Aventurier.Aventurier;
import Cartes.CarteTresor;
import Enumeration.CarteUtilisable;
import Enumeration.TypesNiveaux;
import java.util.ArrayList;

/**
 *
 * @author cabezama
 */
public class Message {

    private TypesMessages type;
    
    //Pour les déplacement et assèchement
    private String tuile;
    
    //Pour donner une carte
    private Aventurier joueur; // Le joueur destinataire
    private CarteTresor carte;

    //Pour commencer une partie
    private TypesNiveaux niveau; 
    private ArrayList<String> nom; 

    public Message(TypesMessages t) {
        type = t;
    }

    public TypesMessages getType() {
        return type;
    }

    public String getTuile() {
        return tuile;
    }

    public Aventurier getJoueur() {
        return joueur;
    }

    public CarteTresor getCarte() {
        return carte;
    }

    public ArrayList<String> getNom() {
        return nom;
    }

    public void setTuile(String tuile) {
        this.tuile = tuile;
    }

    public void setJoueur(Aventurier joueur) {
        this.joueur = joueur;
    }

    public void setCarte(CarteTresor carte) {
        this.carte = carte;
    }

    public void setNom(ArrayList<String> nom) {
        this.nom = nom;
    }

    public TypesNiveaux getNiveau() {
        return niveau;
    }

    public void setNiveau(TypesNiveaux niveau) {
        this.niveau = niveau;
    }
  
}
