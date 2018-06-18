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
    private VuePlateau vPlat;
    private vueAventurier2 vAven;
    private VueMessageBox vText;
    
    private VueActionAventurier vActionAven;
    private VueMainAventurier vMainAven;
    

    public IHMJeu() {
        
        //initialisation variable
        vNiveau = new VueNiveau();
        vPlat = new VuePlateau();
        vAven = new vueAventurier2();
        vText = new VueMessageBox();
        vActionAven = new VueActionAventurier();
        vMainAven = new VueMainAventurier();
        
        
        
        this.window = new JFrame();        
        getFenetre().setLayout(new BorderLayout());
        this.afficher();
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        //Création 2 panels dans Panel Principale > "1" pour le "haut/centre", "2" pour le sud
        JPanel panelCenter1 = new JPanel();
        JPanel panelSud2 = new JPanel(new BorderLayout());
        
        //Création 2 panels dans Panel Sud, "2_1" pour le "centre/gauche", "2_2" pour l'est
        JPanel panelCenter2_1 = new JPanel();
        JPanel panelEst2_2 = new JPanel();
        //
        panelCenter2_1 = vue;
        panelEst2_2 = vueActionAven;
        
        //Ajout 2 panel au Panel Sud
        panelSud2.add(panelCenter2_1,BorderLayout.CENTER);
        panelSud2.add(panelEst2_2, BorderLayout.EAST);
        
        
        //Ajout panel au Panel Principale
        mainPanel.add(panelCenter1, BorderLayout.CENTER);
        mainPanel.add(panelSud2, BorderLayout.SOUTH);
        
        
                
                
                
        window.add(mainPanel);
        afficher();
        

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
