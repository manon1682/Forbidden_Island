package Cartes;

import Cartes.Deck;
import Enumeration.CarteUtilisable;
import java.util.*;

public class Deck_Tresor extends Deck {

    //Pile pioche, defausse

    public Deck_Tresor() {
        super.setPioche(initPioche());
        melangerPioche();
        super.setDefausse(new Stack<Carte>());
        //defausse = 0
    }
    
    public Stack<Carte> initPioche(){
       
        Stack<Carte> cartesPioche = new Stack<Carte>();
        
        CarteUtilisable laCarte;
        //28 Cartes >
        //  3 montées des eaux
        //  2 sac de sable
        //  3 hélicos
        //  5 de chaque Tresor
        
        //hélicos
        for (int i = 0; i < 3; i++) {
            cartesPioche.add( new CarteTresor(CarteUtilisable.HELICO));
        }
        //sac de sable
        for (int i = 0; i < 2; i++) {
            cartesPioche.add( new CarteTresor(CarteUtilisable.SAC_SABLE));
        }
        //montées des eaux
        for (int i = 0; i < 3; i++) {
            cartesPioche.add( new CarteTresor(CarteUtilisable.MONTEE_EAU));
        }
        
        laCarte = CarteUtilisable.PIERRE_SACRE;
        for (int i = 0; i < 1; i++) {   
            for (int j = 0; j < 50; j++) {
                cartesPioche.add( new CarteTresor(laCarte));
            }
            laCarte = laCarte.getNext();
        }
        return cartesPioche;
    }
    
    public ArrayList<CarteTresor> piocher(){
        ArrayList<CarteTresor> cartePiocher = new ArrayList<CarteTresor>();
        cartePiocher.add((CarteTresor)this.pioche());
        cartePiocher.add((CarteTresor)this.pioche());
        return cartePiocher;
    }

}