package Cartes;

import Enumeration.Lieu;
import java.util.ArrayList;
import java.util.Stack;

public class Deck_Innondation extends Deck {

    public Deck_Innondation() {
        super.setDefausse(new Stack<Carte>());
        setPioche();
    }

    private void setPioche() {
        Lieu lieu = Lieu.LE_PONT_DES_ABIMES;
        while (lieu != null) {
            CarteInnondation carte = new CarteInnondation(lieu);
            getPioche().push(carte);
            lieu = lieu.getNext();
        }
    }
}
