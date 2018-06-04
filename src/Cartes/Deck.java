package Cartes;

import java.util.Collections;
import java.util.Stack;

public abstract class Deck {

    public void seMelanger(Stack stack) {

        Collections.shuffle(stack);

    }

    public void getTasPioche_Tresor() {
        // TODO - implement Deck.getTasPioche_Tresor
        throw new UnsupportedOperationException();
    }

}
