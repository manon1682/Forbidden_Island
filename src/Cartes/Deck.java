package Cartes;

import java.util.Collections;
import java.util.Stack;

public abstract class Deck {
    
    private Stack<Carte> pioche;
    private Stack<Carte> defausse;
    

    public Deck(){
        pioche = new Stack<Carte>();
        defausse = new Stack<Carte>();
    }

    public void setPioche(Stack<Carte> pioche) {
        this.pioche = pioche;
    }

    public void setDefausse(Stack<Carte> defausse) {
        this.defausse = defausse;
    }

    public void seMelanger(Stack stack) {
        Collections.shuffle(stack);
    }

    public abstract Carte pioche();
    
    public void defausser(Carte carte){
        this.defausse.push(carte);
    }
    
    public Stack<Carte> getPioche() {
        return pioche;
    }
    
    public Stack<Carte> getDefausse() {
        return defausse;
    }

}
