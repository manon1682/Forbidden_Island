package Cartes;

import Enumeration.CarteUtilisable;

public class CarteTresor extends Carte {

    private CarteUtilisable nom;

    /*Constructeurs (initialisation) */
    public CarteTresor(CarteUtilisable nom) {
        setNom(nom);
    }

    /*Methodes*/
    public String getNom() {
        return nom.toString();
    }

    public void setNom(CarteUtilisable nom) {
        this.nom = nom;
    }

}
