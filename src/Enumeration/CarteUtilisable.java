package Enumeration;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import javax.imageio.ImageIO;

public enum CarteUtilisable {
    HELICO("Helico","Helicoptere.png"),
    SAC_SABLE("Sac De Sable","SacsDeSable.png"),
    MONTEE_EAU("Montee Des Eaux","MonteeDesEaux.png"),
    PIERRE_SACRE("Pierre Sacré","Pierre.png"),
    STATUE_DU_ZEPHIR("Statue du Zéphir","Zephyr.png"),
    CRISTAL_ARDENT("Cristal Ardent","Cristal.png"),
    CALICE_DE_ORDRE("Calice de Ordre","Calice.png");

    private String label;
    private BufferedImage image;

    public CarteUtilisable getNext() {
        return this.ordinal() < CarteUtilisable.values().length - 1
                ? CarteUtilisable.values()[this.ordinal() + 1]
                : null;
    }

    private CarteUtilisable(String label, String nomImage) {
        this.label = label;
        
        BufferedImage tempImage = null;
        try {
             tempImage = ImageIO.read(new FileInputStream("images/cartes/"+nomImage));
        } catch (Exception e) {
             tempImage = null;
        }
        image = tempImage;
    }

    @Override
    public String toString() {
        return this.label;
    }

    public BufferedImage getImage() {
        return image;
    }
        
}
