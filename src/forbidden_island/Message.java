/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forbidden_island;

import Enumeration.TypesMessages;
import Aventurier.Aventurier;
import Cartes.CarteTresor;
import java.util.ArrayList;

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
    private ArrayList<String> nom; //Liste de joueur pour nouvelle partie
    private int nbJoueur; //Nombre de joueurs pour nouvelle partie

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

    public ArrayList<String> getNom() {
        return nom;
    }

    public int getNbJoueur() {
        return nbJoueur;
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

    public void setNom(ArrayList<String> nom) {
        this.nom = nom;
    }

    public void setNbJoueur(int nbJoueur) {
        this.nbJoueur = nbJoueur;
    }
    
}
