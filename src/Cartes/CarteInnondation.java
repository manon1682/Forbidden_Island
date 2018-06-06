package Cartes;


public class CarteInnondation extends Carte {

    private String lieu;

    public CarteInnondation(String lieu) {
        setLieu(lieu);
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

}
