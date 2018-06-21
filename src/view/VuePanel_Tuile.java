/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Aventurier.Aventurier;
import Enumeration.EtatTuile;
import forbidden_island.Message;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import util.Utils.Pion;

/**
 *
 * @author cdlk
 */
public class VuePanel_Tuile extends JPanel{
    
    private EtatTuile etat;
    private String nomTuile;
    private Dimension dim;
    private boolean possible;
    private boolean cadre;
    private boolean possedeTresor = false;
    private String tresor;
    private ArrayList<Pion> joueur;
    private VuePanel_Plateau vPlat;
    
    private BufferedImage tuileNormale;
    private BufferedImage tuileInondee;
    
    private BufferedImage tuileNormaleNoTresor;
    private BufferedImage tuileInondeeNoTresor;
    
    
    public VuePanel_Tuile(String nom, EtatTuile etat, Dimension dim,VuePanel_Plateau plat){
        joueur = new ArrayList<>();
        setvPlat(plat);
        setPossible(false);
        setDim(dim);
        setNomTuile(nom);
        setEtat(etat);
        initImage();
        
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                System.out.println(((VuePanel_Tuile)me.getComponent()).getNomTuile());
                if(((VuePanel_Tuile)me.getComponent()).isPossible()){
                    Message m = new Message(vPlat.getType());
                    m.setTuile(((VuePanel_Tuile)me.getComponent()).getNomTuile());
                    vPlat.transmettreMessage(m); 
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                ((VuePanel_Tuile)me.getComponent()).setCadre(true);
                ((VuePanel_Tuile)me.getComponent()).repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                ((VuePanel_Tuile)me.getComponent()).setCadre(false);
                ((VuePanel_Tuile)me.getComponent()).repaint();
            }
        });
    }
    
    public VuePanel_Tuile(String nom, EtatTuile etat){
        joueur = new ArrayList<>();
        int size = ((this.getSize().width > this.getSize().height ? this.getSize().height : this.getSize().width))-2;
        setvPlat(null);
        setDim(new Dimension(size, size));
        setNomTuile(nom);
        setEtat(etat);
        initImage();
    }
    
    public void initImage(){
        if(getNomFichierTuile(nomTuile) != null){
            tuileNormaleNoTresor = null;
            tuileInondeeNoTresor = null;
            
            try {
                tuileNormale = ImageIO.read((new FileInputStream("images/tuiles/"+getNomFichierTuile(nomTuile)+".png")));
                tuileInondee = ImageIO.read((new FileInputStream("images/tuiles/"+getNomFichierTuile(nomTuile)+"_Innonde.png")));
                if(possedeTresor){
                    tuileNormaleNoTresor = ImageIO.read((new FileInputStream("images/tuiles/"+getNomFichierTuile(nomTuile)+"_NoTresor.png")));
                    tuileInondeeNoTresor = ImageIO.read((new FileInputStream("images/tuiles/"+getNomFichierTuile(nomTuile)+"_Innonde_NoTresor.png")));
                }
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
            g.drawImage((!possedeTresor ? (tuileNormaleNoTresor != null ? tuileNormaleNoTresor : tuileNormale) : tuileNormale), 0, 0, dim.width, dim.height, null);
            afficherPion(g);
        } else if(etat == EtatTuile.inondée){
            g.drawImage((!possedeTresor ? (tuileInondeeNoTresor != null ? tuileInondeeNoTresor : tuileInondee) : tuileInondee), 0, 0, dim.width, dim.height, null);
            afficherPion(g);
        }
        if(isPossible()){
            g.setColor(new Color(250,250,0,100));
            g.fillRect(0, 0, dim.width, dim.height);
        }
        
        if(isCadre() && etat != EtatTuile.coulée){
            /*BasicStroke nStrock = new BasicStroke(3.0f); //Augmente épaisseur du contour de la tuile
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(nStrock);
            g2.setColor(Color.GREEN);
            g2.drawRect(dim.width/28, dim.height/28, dim.width-dim.width/14, dim.height-dim.height/14);*/
            g.drawImage(vPlat.getContourPassage(), 0, 0, dim.width, dim.height, null);
        }
        
    }
    
    public void afficherPion(Graphics g){
        
        int cX = 0;
        int cY = 0;
        for(Pion j : joueur){
            g.drawImage(j.getImage(),cX,cY,dim.width/2,dim.height/2,null);
            cY = (cX == 0 ? cY : dim.height/2);
            cX = (cX == 0 ? dim.width/2 : 0);
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
                nom = "LaCaverneDesOmbres";
                this.possedeTresor = true;
                tresor = "Cristal Ardent";
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
                this.possedeTresor = true;
                tresor = "Calice de Ordre";
            } break;
            case "La Porte d’Argent":{
                nom = "LaPorteArgent";
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
                this.possedeTresor = true;
                tresor = "Statue du Zéphir";
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
                nom = "LaCaverneDuBrasier";
                this.possedeTresor = true;
                tresor = "Cristal Ardent";
            } break;
            case "Le Temple du Soleil":{
                nom = "LeTempleDuSoleil";
                this.possedeTresor = true;
                tresor = "Pierre Sacré";
            } break;
            case "Le Temple de La Lune":{
                nom = "LeTempleDeLaLune";
                this.possedeTresor = true;
                tresor = "Pierre Sacré";
            } break;
            case "Le Palais des Marees":{
                nom = "LePalaisDesMarees";
                this.possedeTresor = true;
                tresor = "Calice de Ordre";
            } break;
            case "Le Val du Crepuscule":{
                nom = "LeValDuCrepuscule";
            } break;
            case "La Tour du Guet":{
                nom = "LaTourDuGuet";
            } break;
            case "Le Jardin des Murmures":{
                nom = "LeJardinDesMurmures";
                this.possedeTresor = true;
                tresor = "Statue du Zéphir"; 
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

    public boolean isPossible() {
        return possible;
    }

    public void setPossible(boolean poss) {
        this.possible = poss;
    }
    
    public void addJoueur(Aventurier j){
        joueur.add(j.getPion());
    }
    
    public void retirerJoueur(Aventurier j){
        joueur.remove(j.getPion());
    }

    public ArrayList<Pion> getJoueur() {
        return joueur;
    }

    public VuePanel_Plateau getvPlat() {
        return vPlat;
    }

    public void setvPlat(VuePanel_Plateau vPlat) {
        this.vPlat = vPlat;
    }

    public boolean isCadre() {
        return cadre;
    }

    public void setCadre(boolean cadre) {
        this.cadre = cadre;
    }
    
    public void setPossedeTresor(boolean tr){
        possedeTresor = tr;
    }
    
    public boolean possedeTresor(){
        return possedeTresor;
    }
    
    public String getTresor(){
        return tresor;
    }
    
}
