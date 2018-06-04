package Cartes;

import java.util.Collections;
import java.util.Stack;

public abstract class Deck {
    
    private Stack<Carte> pioche;
    private Stack<Carte> defausse;
    
    public Deck(Stack<Carte> p, Stack<Carte> d){
        pioche = p;
        defausse = d;
    }

    public void seMelanger(Stack stack) {
        Collections.shuffle(stack);
    }

    public abstract Carte pioche();
    
    public Stack<Carte> getPioche() {
        return pioche;
    }
    
    public Stack<Carte> getDefausse() {
        return defausse;
    }

}
