package Cartes;

import Enumeration.CarteUtilisable;

public class CarteTresor extends Carte {

    private CarteUtilisable nom;

    public CarteTresor(CarteUtilisable nom) {
        setNom(nom);
    }

    public String getNom() {
        return nom.toString();
    }

    public CarteUtilisable utilisation() {
        return nom;
    }

    public void setNom(CarteUtilisable nom) {
        this.nom = nom;
    }

}
