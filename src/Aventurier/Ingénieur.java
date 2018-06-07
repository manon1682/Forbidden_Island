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
     
     public boolean doubleAssechementPossible(Grille g){
         boolean[][] gBool = super.assechementPossible(g);
         int nbTuile = 0;
         for (int i = 0; i < 6; i++) {
             for (int j = 0; j < 6; j++) {
                 nbTuile = nbTuile + (gBool[i][j] ? 1:0);
             }
         }
         return nbTuile >= 2;
     }

}
