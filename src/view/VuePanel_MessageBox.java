/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author blanquan
 */
public class VuePanel_MessageBox extends JPanel {
    
    private JLabel msg;

    public VuePanel_MessageBox() {
        
        this.setBackground(new Color(225, 221, 136));
        msg = new JLabel("Bienvenue sur l'île Interdite");
        msg.setFont(new Font("Serif", Font.ITALIC, 18));
        this.add(msg);
        this.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.black));

    }

    public void ajoutMessage(String text) {
        msg.setText(text);
    }

    public String getMessage() {
        return msg.getText();
    }

}
