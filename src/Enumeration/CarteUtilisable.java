package Enumeration;

import java.util.ArrayList;

public enum CarteUtilisable {
    hélico,
    sac_sable,
    montée_eau,
    Pierre_Sacré,
    Statue_du_Zéphir,
    Cristal_Ardent,
    Calice_de_Ordre;

    public String toString() {
        return name().toLowerCase();
    }
    
    public CarteUtilisable next(CarteUtilisable c){
  
        
        
        
        //on recup l'int de c;
        //on marque enum de c +1
        CarteUtilisable[] carte = CarteUtilisable.values();
        int i = 0;
        //compareTO renvoie :
            //-1 si objet inf
            // 0 si objet est le même
            // 1 si l'objet est supérieur
        
        while (c.compareTo(carte[i])>=0||i<carte.length){
            i++;
            //si la carte est supérieur OU la même alors on incrémente
            
        }
        c = carte[(i == carte.length ? 0:i)]; //soit 0 (hélico) soit i si la carte est pas à la fin
        return c;
    }
    
    public CarteUtilisable getNext() {
     return this.ordinal() < CarteUtilisable.values().length - 1
         ? CarteUtilisable.values()[this.ordinal() + 1]
         : null;
   }
    
    
    
}
