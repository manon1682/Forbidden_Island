package Cartes;

import Cartes.Deck;
import java.util.*;

public class Deck_Tresor extends Deck {

    public Deck_Tresor(Stack<Carte> p, Stack<Carte> d) {
        super(p, d);
    }

    @Override
    public Carte pioche() {
        return getPioche().pop();
    }


}