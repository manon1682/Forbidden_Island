package Aventurier;

import forbidden_island.Grille;
import util.Utils.Pion;

public class Ingénieur extends Aventurier {
    
    private int capaciteUtilisee;

    public Ingénieur(String nm, int l, int c) {
        super(Pion.ROUGE, nm, l, c);
        capaciteUtilisee = 0;
    }
    
     public void setCapaciteUtilisee(int capaciteUtilisee) {
        this.capaciteUtilisee = capaciteUtilisee;
    }

     public int capaciteUtilisee() {
        return capaciteUtilisee;
    }

}
