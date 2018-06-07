package Aventurier;

import forbidden_island.Grille;
import util.Utils.Pion;

public class Ingénieur extends Aventurier {
    
    private boolean capaciteUtilisee ;

    public Ingénieur(String nm, int l, int c) {
        super(Pion.ROUGE, nm, l, c);
        capaciteUtilisee = false;
    }
    
     public void setCapaciteUtilisee(boolean capaciteUtilisee) {
        this.capaciteUtilisee = capaciteUtilisee;
    }

     public boolean capaciteUtilisee() {
        return capaciteUtilisee;
    }

}
