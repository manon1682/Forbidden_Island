/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author cabezama
 */
public class VuePanel_Manuel extends JPanel {

    //Variables Fenêtre
    private final JFrame window;
    private int height;
    private int width;

    public VuePanel_Manuel() {
        super();
        width = 720;
        height = 960;

        this.window = new JFrame();
        window.setSize(width, height);
        window.setTitle("Ile Interdite Manuel");

        
        JPanel panRegle = new JPanel(new GridLayout(8, 1));
        JScrollPane scrollPane = new JScrollPane(panRegle);        

        ImageIcon regle1 = new ImageIcon("images/regles/regle-page-001.jpg");
        Image imager1 = regle1.getImage(); 
        Image newimgr1 = imager1.getScaledInstance(720, 960, java.awt.Image.SCALE_SMOOTH); // Redimension de l'icône 
        regle1 = new ImageIcon(newimgr1); 
        JLabel rg1 = new JLabel();
        rg1.setIcon(regle1);
        panRegle.add(rg1);
        
        ImageIcon regle2 = new ImageIcon("images/regles/regle-page-002.jpg");
        Image imager2 = regle2.getImage(); 
        Image newimgr2 = imager2.getScaledInstance(720, 960, java.awt.Image.SCALE_SMOOTH); // Redimension de l'icône 
        regle2 = new ImageIcon(newimgr2); 
        JLabel rg2 = new JLabel();
        rg2.setIcon(regle2);
        panRegle.add(rg2);
        
        ImageIcon regle3 = new ImageIcon("images/regles/regle-page-003.jpg");
        Image imager3 = regle3.getImage(); 
        Image newimgr3 = imager3.getScaledInstance(720, 960, java.awt.Image.SCALE_SMOOTH); // Redimension de l'icône 
        regle3 = new ImageIcon(newimgr3); 
        JLabel rg3 = new JLabel();
        rg3.setIcon(regle3);
        panRegle.add(rg3);
        
        ImageIcon regle4 = new ImageIcon("images/regles/regle-page-004.jpg");
        Image imager4 = regle4.getImage(); 
        Image newimgr4 = imager4.getScaledInstance(720, 960, java.awt.Image.SCALE_SMOOTH); // Redimension de l'icône 
        regle4 = new ImageIcon(newimgr4); 
        JLabel rg4 = new JLabel();
        rg4.setIcon(regle4);
        panRegle.add(rg4);
        
        ImageIcon regle5 = new ImageIcon("images/regles/regle-page-005.jpg");
        Image imager5= regle5.getImage(); 
        Image newimgr5 = imager5.getScaledInstance(720, 960, java.awt.Image.SCALE_SMOOTH); // Redimension de l'icône 
        regle5 = new ImageIcon(newimgr5); 
        JLabel rg5 = new JLabel();
        rg5.setIcon(regle5);
        panRegle.add(rg5);
        
        ImageIcon regle6 = new ImageIcon("images/regles/regle-page-006.jpg");
        Image imager6= regle6.getImage(); 
        Image newimgr6 = imager6.getScaledInstance(720, 960, java.awt.Image.SCALE_SMOOTH); // Redimension de l'icône 
        regle6 = new ImageIcon(newimgr6); 
        JLabel rg6 = new JLabel();
        rg6.setIcon(regle6);
        panRegle.add(rg6);
        
        ImageIcon regle7 = new ImageIcon("images/regles/regle-page-007.jpg");
        Image imager7= regle7.getImage(); 
        Image newimgr7 = imager7.getScaledInstance(720, 960, java.awt.Image.SCALE_SMOOTH); // Redimension de l'icône 
        regle7 = new ImageIcon(newimgr7); 
        JLabel rg7 = new JLabel();
        rg7.setIcon(regle7);
        panRegle.add(rg7);
        
        ImageIcon regle8 = new ImageIcon("images/regles/regle-page-008.jpg");
        Image imager8= regle8.getImage(); 
        Image newimgr8 = imager8.getScaledInstance(720, 960, java.awt.Image.SCALE_SMOOTH); // Redimension de l'icône 
        regle8 = new ImageIcon(newimgr8); 
        JLabel rg8 = new JLabel();
        rg8.setIcon(regle8);
        panRegle.add(rg8);
        
        window.add(scrollPane);
    

    }

    public void afficher() {
        window.setVisible(true);
    }

    public void desafficher() {
        window.setVisible(false);
    }

}
