package Cartes;

import Enumeration.Lieu;


public class CarteInnondation extends Carte {

    private Lieu lieu;

    public CarteInnondation(Lieu lieu) {
        setLieu(lieu);
    }

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

}
