/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author blancoma
 */
public class PanelCarte extends JPanel {

    public PanelCarte(int n) {

        setLayout(new BorderLayout());
        
        JPanel panelhaut = new JPanel(new BorderLayout());
        JLabel nombre = new JLabel("x " + n);
        panelhaut.add(nombre, BorderLayout.EAST);
        add(panelhaut, BorderLayout.NORTH);
        // + ajouter l'image mais wallah je sais pas comment on fait
    }

}
