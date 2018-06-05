package Cartes;

import Cartes.Deck;
import java.util.Stack;

public class Deck_Innondation extends Deck {

    public Deck_Innondation(Stack<Carte> p, Stack<Carte> d) {
        super();
        setPioche(p);
        setDefausse(d);
    }

    @Override
    public Carte pioche() {
        return getPioche().pop();
    }

}