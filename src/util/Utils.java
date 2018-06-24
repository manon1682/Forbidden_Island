/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
//import model.aventuriers.Aventurier;

/**
 *
 * @author Eric
 */
public class Utils {

    public static enum Pion {
        ROUGE("Rouge", new Color(255, 0, 0), "pionRouge.png"),
        VERT("Vert", new Color(0, 195, 0), "pionVert.png"),
        BLEU("Bleu", new Color(55, 194, 198), "pionBleu.png"),
        BLANC("Blanc", new Color(255, 255, 255), "pionGris.png"),
        NOIR("Noir", new Color(150, 150, 150), "pionNoir.png"),
        JAUNE("Jaune", new Color(255, 255, 0), "pionJaune.png");

        private final String libelle;
        private final Color couleur;
        private BufferedImage image;

        private Pion() {
            this.libelle = null;
            this.couleur = null;
        }

        Pion(String libelle, Color couleur, String nomPion) {
            this.libelle = libelle;
            this.couleur = couleur;

            BufferedImage tempImage = null;
            try {
                tempImage = ImageIO.read(new FileInputStream("images/pions/" + nomPion));
            } catch (Exception e) {
                tempImage = null;
            }
            image = tempImage;
        }

        @Override
        public String toString() {
            return this.libelle;
        }

        public Color getCouleur() {
            return this.couleur;
        }

        static Pion getFromName(String name) {
            if (ROUGE.name().equals(name)) {
                return ROUGE;
            }
            if (VERT.name().equals(name)) {
                return VERT;
            }
            if (BLEU.name().equals(name)) {
                return BLEU;
            }
            if (NOIR.name().equals(name)) {
                return NOIR;
            }
            if (BLANC.name().equals(name)) {
                return BLANC;
            }
            if (JAUNE.name().equals(name)) {
                return JAUNE;
            }
            return null;
        }

        public BufferedImage getImage() {
            return image;
        }
    }
}
