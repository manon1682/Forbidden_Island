/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Enumeration.EtatTuile;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
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
    
    private BufferedImage tuileNormale;
    private BufferedImage tuileInondee;
    
    public VueTuile(){
        initImage();
        repaint();
        
        
        
    }
    
    public void initImage(){
        try {
            tuileNormale = ImageIO.read(getClass().getResourceAsStream("../../images/tuiles/Heliport.png"));
        } catch (IOException ex) {
            Logger.getLogger(VueTuile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void paint(Graphics g){
        g.drawImage(tuileNormale, 100, 100, tuileNormale.getWidth(), tuileNormale.getHeight(), null);
    }
    
    
}
