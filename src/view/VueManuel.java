/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author cabezama
 */
public class VueManuel extends JPanel {

    //Variables Fenêtre
    private final JFrame window;
    private int height;
    private int width;

    public VueManuel() {
        width = 787;
        height = 787;

        this.window = new JFrame();
        window.setResizable(false);
        window.setSize(width, height);
    }

}
