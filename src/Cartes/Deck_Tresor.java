package Cartes;

import Cartes.Deck;
import java.util.*;

public class Deck_Tresor extends Deck {

    //Pile pioche, defausse

    public Deck_Tresor() {
        super.setPioche(initPioche());
        super.setDefausse(new Stack<Carte>());

        //defausse = 0
    }
    
    


    
    
    public Stack<Carte> initPioche(){
       
        Stack<Carte> cartesPioche = new Stack<Carte>();
        
        for (int i = 0; i < 5; i++) {
            
        }
        
        
        cartesPioche.add(e)
        
        
        
        
        
        
        return cartesPioche;
       
    }
    
    @Override
    public Carte pioche() {
        return getPioche().pop();
    }


}