/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Aventurier.Aventurier;
import forbidden_island.Grille;
import forbidden_island.Observe;
import javax.swing.JFrame;

/**
 *
 * @author blanquan
 */
public class IHMJeu extends Observe {

    //composants fenêtre
    private final JFrame window;

    public IHMJeu() {

        this.window = new JFrame();
        

    }

    public void afficher() {
        getFenetre().setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        getFenetre().setSize(1400, 800);
        getFenetre().setVisible(true);
    }
    
  


    public void desafficher() {
        getFenetre().setVisible(false);
    }
    
    // Getter
      public JFrame getFenetre() {
        return window;
    }

}
