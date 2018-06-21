package Cartes;

import java.util.ArrayList;
import java.util.Stack;

public class Deck_Innondation extends Deck {

    public Deck_Innondation(ArrayList<String> nomTuile) {
        super.setDefausse(new Stack<Carte>());
        setPioche(nomTuile);
    }

    private void setPioche(ArrayList<String> nomTuiles) {
            
        for (String nomTuile : nomTuiles) { //remember francis <3
            CarteInnondation carte = new CarteInnondation(nomTuile); 
            getPioche().push(carte);
            melangerPioche();
        }
    }
}
