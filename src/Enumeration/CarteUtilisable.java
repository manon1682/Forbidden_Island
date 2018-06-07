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
    
    
    //methodes next et getNext n'ont pas été utiles pour le moment, à supprimer à la fin.
    
    public CarteUtilisable getNext() {
     return this.ordinal() < CarteUtilisable.values().length - 1
            ? CarteUtilisable.values()[this.ordinal() + 1]
            : null;
   }
    
    
    
}
