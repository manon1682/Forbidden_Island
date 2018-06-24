/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author blanquan
 */
public class VuePanel_MessageBox extends JPanel {
    
    private JLabel msg;

    public VuePanel_MessageBox() {
        
        //this.setBackground(Color.gray);
        
        //this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        
        msg = new JLabel("Bienvenue sur l'île Interdite");
        msg.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
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
