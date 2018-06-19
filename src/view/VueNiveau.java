/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author blanquan
 */
public class VueNiveau extends JPanel {
    
    
     private Integer jaugeInnondation ;

    public VueNiveau(int jaugeInn) {
        
        //initialisation
        this.jaugeInnondation = jaugeInn;

        
        
        this.setBackground(Color.PINK);
        
        JPanel caseNiveauTemporaire = new JPanel(); //à suppr pour une image dès que possible        
        JLabel msgboxTempo = new JLabel("niveau eau");
        caseNiveauTemporaire.add(msgboxTempo);

        this.add(caseNiveauTemporaire);
        
    }
    
    
    
    
    
    
    
}
