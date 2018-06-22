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
    private boolean courant;
    private String tresor;
    private ArrayList<Pion> joueur;
    private VuePanel_Plateau vPlat;
    
    private BufferedImage tuileNormale;
    private BufferedImage tuileInondee;
    
    private BufferedImage tuileNormaleNoTresor;
    private BufferedImage tuileInondeeNoTresor;
    
    private BufferedImage imgCoulee;
    
    
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
                if(((VuePanel_Tuile)me.getComponent()).isPossible()){
                    Message m = new Message(vPlat.getType());
                    m.setTuile(((VuePanel_Tuile)me.getComponent()).getNomTuile());
                    vPlat.transmettreMessage(m); 
                    //On génère un message contenant un tuile pour le deplacement d'un joueur
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
                //On affiche un cadre autour des tuiles sur lesquels la souris passe
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
    
    public void initImage(){ //Charge les images des tuiles
        if(getNomFichierTuile(nomTuile) != null){
            tuileNormaleNoTresor = null;
            tuileInondeeNoTresor = null;
            
            try { //Inondée ,séche et coulée
                imgCoulee = ImageIO.read(new FileInputStream("images/Tile_Flood_Water@2x.png"));
                tuileNormale = ImageIO.read(new FileInputStream("images/tuiles/"+getNomFichierTuile(nomTuile)+".png"));
                tuileInondee = ImageIO.read(new FileInputStream("images/tuiles/"+getNomFichierTuile(nomTuile)+"_Innonde.png"));
                if(possedeTresor){ //Si la tuile a un amplacement a tresor on charge les version sans tresor
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
        
        //En fonction de l'etat de la tuile on affiche l'image correspondante
       //Si la tuile possede un emplacement a tresor on affiche sa version avec ou sans le tresor 
       //en fonction du fait que les aventurier l'est deja pris ou non
        if(etat == EtatTuile.SECHE){
            g.drawImage((!possedeTresor ? (tuileNormaleNoTresor != null ? tuileNormaleNoTresor : tuileNormale) : tuileNormale), (this.getSize().width - dim.width)/2, 0, dim.width, dim.height, null);
            afficherPion(g);
        } else if(etat == EtatTuile.INONDEE) {
            g.drawImage((!possedeTresor ? (tuileInondeeNoTresor != null ? tuileInondeeNoTresor : tuileInondee) : tuileInondee), (this.getSize().width - dim.width)/2, 0, dim.width, dim.height, null);
            afficherPion(g);
        } else {
            //g.drawImage(imgCoulee, 0, 0, dim.width, dim.height, null);
        }
        
        //Filtre jaune sur les tuile possible pour le deplacement et l'assèchement
        if(isPossible()){
            g.setColor(new Color(250,250,0,100));
            g.fillRect((this.getSize().width - dim.width)/2, 0, dim.width, dim.height);
        }
        
        //Cadre jaune si joueur courant est sur cette tuile
        if(isCourant()){
            g.drawImage(vPlat.getContourCourant(), (this.getSize().width - dim.width)/2, 0, dim.width, dim.height, null);
        }
        
        //Cadre vert si tuile non coulée et la souris est passer dessus 
        if(isCadre() && etat != EtatTuile.COULEE){
            g.drawImage(vPlat.getContourPassage(), (this.getSize().width - dim.width)/2, 0, dim.width, dim.height, null);
        }
    }
    
    public void afficherPion(Graphics g){
        int cX = (this.getSize().width - dim.width)/2;
        int cY = 0;
        for(Pion j : joueur){
            g.drawImage(j.getImage(),cX,cY,dim.width/2,dim.height/2,null);
            cY = (cX == (this.getSize().width - dim.width)/2 ? cY : dim.height/2);
            cX = (cX == (this.getSize().width - dim.width)/2 ? (this.getSize().width - dim.width)/2 + dim.width/2 : (this.getSize().width - dim.width)/2);
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

    public boolean isCourant() {
        return courant;
    }

    public void setCourant(boolean courant) {
        this.courant = courant;
    }
    
}
