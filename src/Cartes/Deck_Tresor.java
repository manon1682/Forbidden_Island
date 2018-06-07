package Cartes;

import Cartes.Deck;
import Enumeration.CarteUtilisable;
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
        
        CarteUtilisable laCarte;
        
        //28 Cartes >
        //  3 montées des eaux
        //  2 sac de sable
        //  3 hélicos
        //  5 de chaque Tresor
        
        //hélicos
        for (int i = 0; i <= 3; i++) {
            cartesPioche.add( new CarteTresor(CarteUtilisable.hélico));
        }
        //sac de sable
        for (int i = 0; i <= 2; i++) {
            cartesPioche.add( new CarteTresor(CarteUtilisable.sac_sable));
        }
        //montées des eaux
        for (int i = 0; i <= 3; i++) {
            cartesPioche.add( new CarteTresor(CarteUtilisable.montée_eau));
        }
        
        laCarte = CarteUtilisable.Pierre_Sacré;
        for (int i = 0; i <= 4; i++) {   
            for (int j = 0; j <= 5; j++) {
                cartesPioche.add( new CarteTresor(laCarte));
            }
            laCarte.getNext();
        }
        super.seMelanger(cartesPioche);
        return cartesPioche;
    }
    
    @Override
    public Carte pioche() {
        return getPioche().pop();
    }
    
    public ArrayList<CarteTresor> piocher(){
        ArrayList<CarteTresor> cartePiocher = new ArrayList<CarteTresor>();
        cartePiocher.add((CarteTresor)this.pioche());
        cartePiocher.add((CarteTresor)this.pioche());
        return cartePiocher;
    }

}