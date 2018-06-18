/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Aventurier.Aventurier;
import forbidden_island.Grille;
import forbidden_island.Observe;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author blanquan
 */
public class IHMJeu extends Observe {

    //composants fenêtre
    private final JFrame window;
    
//        private VueIninialisation vIni;
    
    private VueNiveau vNiveau;
    private VueAventurier vAven;
    private VuePlateau vPlat;
    private VueMessageBox vMB;
    
    private VueActionAventurier vActionAven;
    private VueMainAventurier vMainAven;
    

    public IHMJeu() {
        

        
        this.window = new JFrame();        
        getFenetre().setLayout(new BorderLayout());
        this.afficher();
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        JPanel panelCenter1 = new JPanel();
        JPanel panelSud1 = new JPanel(new BorderLayout());
        
        panelSud1.add(vueMainAven,BorderLayout.CENTER);
        panelSud1.add(vueActionAven, BorderLayout.EAST);
        
        mainPanel.add(vue)
   
        window.add(mainPanel);
        
        
        

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
