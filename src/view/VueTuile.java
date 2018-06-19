/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Enumeration.EtatTuile;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author cdlk
 */
public class VueTuile extends JPanel{
    
    private EtatTuile etat;
    private String nomTuile;
    private Dimension dim;
    private boolean cadre;
    
    private BufferedImage tuileNormale;
    private BufferedImage tuileInondee;
    
    public VueTuile(String nom, EtatTuile etat, Dimension dim){
        setCadre(false);
        setDim(dim);
        setNomTuile(nom);
        setEtat(etat);
        initImage();
    }
    
    public VueTuile(String nom, EtatTuile etat){
        int size = ((this.getSize().width > this.getSize().height ? this.getSize().height : this.getSize().width))-2;
        setDim(new Dimension(size, size));
        setNomTuile(nom);
        setEtat(etat);
        initImage();
    }
    
    public void initImage(){
        if(getNomFichierTuile(nomTuile) != null){
            try {
                tuileNormale = ImageIO.read((new FileInputStream("images/tuiles/"+getNomFichierTuile(nomTuile)+".png")));
                tuileInondee = ImageIO.read((new FileInputStream("images/tuiles/"+getNomFichierTuile(nomTuile)+"_Inonde.png")));
            } catch (IOException ex) {
                ex.fillInStackTrace();
            }   
        }
    }
    
    @Override
    public void paint(Graphics g){
        int size = ((this.getSize().width > this.getSize().height ? this.getSize().height : this.getSize().width))-2;
        setDim(new Dimension(size, size));
        if(etat == EtatTuile.sèche){
            g.drawImage(tuileNormale, 0, 0, dim.width, dim.height, null);
        } else if(etat == EtatTuile.inondée){
            g.drawImage(tuileInondee, 0, 0, dim.width, dim.height, null);
        } else {
            g.setColor(Color.blue);
            g.fillRect(0, 0, dim.width, dim.height);
        }
        if(isCadre()){
            BasicStroke nStrock = new BasicStroke(4.0f); //Augmente epaissuer du contour de la tuile
            g.setColor(Color.yellow);
            g.drawRect(0, 0, dim.width, dim.height);
        }
        
    }
    
    public String getNomFichierTuile(String nomTuile){
        String nom;
        switch(nomTuile){
            case "Le Pont des Abimes":{
                nom = "LePontDesAbimes";
            } break;
            case "La Porte de Bronze":{
                nom = "LaPorteDeBronze";
            } break;
            case "La Caverne des Ombres":{
                nom = "LaCarverneDesOmbres";
            } break;
            case "La Porte de Fer":{
                nom = "LaPorteDeFer";
            } break;
            case "La Porte d’Or":{
                nom = "LaPortedOr";
            } break;
            case "Les Falaises de l’Oubli":{
                nom = "LesFalaisesDeLOubli";
            } break;
            case "Le Palais de Corail":{
                nom = "LePalaisDeCorail";
            } break;
            case "La Porte d’Argent":{
                nom = "LaPortedArgent";
            } break;
            case "Les Dunes de l’Illusion":{
                nom = "LesDunesDeLIllusion";
            } break;
            case "Heliport":{
                nom = "Heliport";
            } break;
            case "La Porte de Cuivre":{
                nom = "LaPorteDeCuivre";
            } break;
            case "Le Jardin des Hurlements":{
                nom = "LeJardinDesHurlements";
            } break;
            case "La Foret Pourpre":{
                nom = "LaForetPourpre";
            } break;
            case "Le Lagon Perdu":{
                nom = "LeLagonPerdu";
            } break;
            case "Le Marais Brumeux":{
                nom = "LeMaraisBrumeux";
            } break;
            case "Observatoire":{
                nom = "Observatoire";
            } break;
            case "Le Rocher Fantome":{
                nom = "LeRocherFantome";
            } break;
            case "La Caverne du Brasier":{
                nom = "LaCarverneDuBrasier";
            } break;
            case "Le Temple du Soleil":{
                nom = "LeTempleDuSoleil";
            } break;
            case "Le Temple de La Lune":{
                nom = "LeTempleDeLaLune";
            } break;
            case "Le Palais des Marees":{
                nom = "LePalaisDesMarees";
            } break;
            case "Le Val du Crepuscule":{
                nom = "LeValDuCrepuscule";
            } break;
            case "La Tour du Guet":{
                nom = "LaTourDuGuet";
            } break;
            case "Le Jardin des Murmures":{
                nom = "LeJardinDesMurmures";
            } break;
            default :{
                nom = null;
            }
        }
        return nom;
    }

    public EtatTuile getEtat() {
        return etat;
    }

    public void setEtat(EtatTuile etat) {
        this.etat = etat;
    }

    public String getNomTuile() {
        return nomTuile;
    }

    public void setNomTuile(String nomTuile) {
        this.nomTuile = nomTuile;
    }

    public Dimension getDim() {
        return dim;
    }

    public void setDim(Dimension dim) {
        this.dim = dim;
    }

    public boolean isCadre() {
        return cadre;
    }

    public void setCadre(boolean cadre) {
        this.cadre = cadre;
    }
    
    
}
