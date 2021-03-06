package Cartes;

import java.util.Collections;
import java.util.Stack;

public abstract class Deck {

    private Stack<Carte> pioche;
    private Stack<Carte> defausse;

    public Deck() {
        pioche = new Stack<Carte>();
        defausse = new Stack<Carte>();
    }

    public void setPioche(Stack<Carte> pioche) {
        this.pioche = pioche;
    }

    public void setDefausse(Stack<Carte> defausse) {
        this.defausse = defausse;
    }

    public void melangerPioche() {
        Collections.shuffle(pioche);
    }

    public void melangerDefausse() {
        Collections.shuffle(defausse);
    }

    public Carte pioche() {
        //Si la pioche est vide
        if (pioche.isEmpty()) {
            //On mélange la défausse et la met dans la pioche
            melangerDefausse();
            pioche.addAll(defausse);
        }
        return pioche.pop();
    }

    public void defausser(Carte carte) {
        this.defausse.push(carte);
    }

    public Stack<Carte> getPioche() {
        return pioche;
    }

    public Stack<Carte> getDefausse() {
        return defausse;
    }

}
