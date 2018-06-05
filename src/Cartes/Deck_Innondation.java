package Cartes;

import Cartes.Deck;
import java.util.ArrayList;
import java.util.Stack;

public class Deck_Innondation extends Deck {

    public Deck_Innondation(ArrayList<String> nomTuile) {
        super.setDefausse(new Stack<Carte>());
        setPioche(nomTuile);
    }

    @Override
    public Carte pioche() {
        return getPioche().pop();
    }

    private void setPioche(ArrayList<String> nomTuiles) {
        for (String nomTuile : nomTuiles) { //remember francis <3
            super.getPioche().add(new CarteInnondation(nomTuile));
        }
    }

}