/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import Aventurier.Aventurier;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
//import model.aventuriers.Aventurier;

/**
 *
 * @author Eric
 */
public class Utils {

//    public static enum EtatTuile {
//        ASSECHEE("Asséchée"), 
//        INONDEE("Inondée"),
//        COULEE("Coulée");
//
//        String libelle;
//        
//        EtatTuile(String libelle) {
//            this.libelle = libelle ;
//        }
//
//        @Override
//        public String toString() {
//            return this.libelle ;
//        }
//    }
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

    public static ArrayList<Aventurier> melangerAventuriers(ArrayList<Aventurier> arrayList) {
        if (Parameters.ALEAS) {
            Collections.shuffle(arrayList);
        }
        return arrayList;
    }

    /**
     * Permet de poser une question à laquelle l'utilisateur répond par oui ou
     * non
     *
     * @param question texte à afficher
     * @return true si l'utilisateur répond oui, false sinon
     */
    public static Boolean poserQuestion(String question) {
        System.out.println("Divers.poserQuestion(" + question + ")");
        int reponse = JOptionPane.showConfirmDialog(null, question, "", JOptionPane.YES_NO_OPTION);
        System.out.println("\tréponse : " + (reponse == JOptionPane.YES_OPTION ? "Oui" : "Non"));
        return reponse == JOptionPane.YES_OPTION;
    }

    /**
     * Permet d'afficher un message d'information avec un bouton OK
     *
     * @param message Message à afficher
     */
    public static void afficherInformation(String message) {
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.OK_OPTION);
    }
}
