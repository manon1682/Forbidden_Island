/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enumeration;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author cdlk
 */
public enum Lieu {
    LE_PONT_DES_ABIMES("Le Pont des Abimes","LePontDesAbimes"),
    LA_PORTE_DE_BRONZE("La Porte de Bronze","LaPorteDeBronze"),
    LA_CAVERNE_DES_OMBRES("La Caverne des Ombres","LaCaverneDesOmbres"),
    LA_PORTE_DE_FER("La Porte de Fer","LaPorteDeFer"),
    LA_PORTE_D_OR("La Porte d’Or","LaPorteDOr"),
    LES_FALAISES_DE_L_OUBLI("Les Falaises de l’Oubli","LesFalaisesDeLOubli"),
    LE_PALAIS_DE_CORAIL("Le Palais de Corail","LePalaisDeCorail"),
    LA_PORTE_D_ARGENT("La Porte d’Argent","LaPortedArgent"),
    LES_DUNES_DE_L_ILLUSION("Les Dunes de l’Illusion","LesDunesDeLIllusion"),
    HELIPORT("Heliport","Heliport"),
    LA_PORTE_DE_CUIVRE("La Porte de Cuivre","LaPorteDeCuivre"),
    LE_JARDIN_DES_HURLEMENTS("Le Jardin des Hurlements","LeJardinDesHurlements"),
    LA_FORET_POURPRE("La Foret Pourpre","LaForetPourpre"),
    LE_LAGON_PERDU("Le Lagon Perdu","LeLagonPerdu"),
    LE_MARAIS_BRUMEUX("Le Marais Brumeux","LeMaraisBrumeux"),
    OBSERVATOIRE("Observatoire","Observatoire"),
    LE_ROCHER_FANTOME("Le Rocher Fantome","LeRocherFantome"),
    LA_CAVERNE_DU_BRASIER("La Caverne du Brasier","LaCaverneDuBrasier"),
    LE_TEMPLE_DU_SOLEIL("Le Temple du Soleil","LeTempleDuSoleil"),
    LE_TEMPLE_DE_LA_LUNE("Le Temple de La Lune","LeTempleDeLaLune"),
    LE_PALAIS_DES_MAREES("Le Palais des Marees","LePalaisDesMarees"),
    LE_VAL_DU_CREPUSCULE("Le Val du Crepuscule","LeValDuCrepuscule"),
    LA_TOUR_DU_GUET("La Tour du Guet","LaTourDeGuet"),
    LE_JARDIN_DES_MURMURES("Le Jardin des Murmures","LeJardinDesMurmures");
            
    private String label;
    private BufferedImage imageCarte;

    public Lieu getNext() {
        return this.ordinal() < Lieu.values().length - 1
                ? Lieu.values()[this.ordinal() + 1]
                : null;
    }
    
    
    private Lieu(String label, String nomImage) {
        this.label = label;
        
        BufferedImage tempImageCarte = null;
        try {
             tempImageCarte = ImageIO.read(new FileInputStream("images/cartes/"+nomImage+".png"));
        } catch (Exception e) {
             tempImageCarte = null;
        }
        imageCarte = tempImageCarte;
    }

    @Override
    public String toString() {
        return this.label;
    }

    public BufferedImage getImage() {
        return imageCarte;
    }
}
