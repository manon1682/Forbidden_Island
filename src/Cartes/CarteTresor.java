package Cartes;

public class CarteTresor {

    private CarteUtilisable nom;
    
    /*Constructeurs (initialisation) */
    public CarteTresor(CarteUtilisable nom) {
        setNom(nom);
    }

    /*Methodes*/
    
    public CarteUtilisable getNom() {
        return nom;
    }

    public void setNom(CarteUtilisable nom) {
        this.nom = nom;
    }

}
