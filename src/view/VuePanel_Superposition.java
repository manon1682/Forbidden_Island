/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author cabezama
 */
public class VuePanel_Superposition extends JFrame {

    private JLayeredPane lp;

    public VuePanel_Superposition() {

        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        this.setSize(1410, 840);

        lp = this.getLayeredPane();

    }

    public void addPanel(JPanel panel, int niv, int ordre) {
        panel.setBounds(0, 0, 1400, 800);
        if (ordre == 0) {
            lp.remove(ordre);
        }

        if (niv == 2) {
            lp.add(panel, new Color(0, 0, 0, 240), JLayeredPane.DRAG_LAYER);

        } else {
            lp.add(panel, new Color(0, 0, 0, 0), niv);

        }

    }

}
