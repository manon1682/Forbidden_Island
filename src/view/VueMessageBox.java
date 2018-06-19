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
public class VueMessageBox extends JPanel {

    public VueMessageBox() {
        this.setBackground(Color.GREEN);
        
        JPanel caseMsgTemporaire = new JPanel();    
        JLabel msgboxTempo = new JLabel("niveau eaud");
        caseMsgTemporaire.add(msgboxTempo);

        this.add(caseMsgTemporaire);
    }
        
    
    
}
