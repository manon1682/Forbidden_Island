/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forbidden_island;

import view.IHMJeu;
import view.VueAventurier;

/**
 *
 * @author blanquan
 */
public class TestIHM {
    
    
    public TestIHM(){
        Controleur c =  new Controleur();
        new IHMJeu(c.getGrille());
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new TestIHM();
        
    }
    
}
