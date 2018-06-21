/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author cabezama
 */
public class VuePanel_Victoire extends JPanel{
    private IHMJeu ihm;

    public VuePanel_Victoire() {
        ihm.vidangeIhm();
        this.setLayout(new BorderLayout());
      
        ImageIcon logoTrophe = new ImageIcon("images/victoire/trophe.png");
        JLabel logoTroph = new JLabel();
        logoTroph.setIcon(logoTrophe);
        
        this.add(logoTroph, BorderLayout.CENTER);
        
    }
    
    
    
}
