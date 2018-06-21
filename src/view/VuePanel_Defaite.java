/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Enumeration.Defaite;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author cabezama
 */
public class VuePanel_Defaite extends JPanel {

    public VuePanel_Defaite(Defaite d) {
        setLayout(new BorderLayout());
        setBackground(new Color(0, 0, 0, 210));
        JLabel defaite = new JLabel("DEFAITE...");
        add(defaite, BorderLayout.NORTH);
        ImageIcon logoDef = new ImageIcon("images/defaite/defaite.png");
        JLabel def = new JLabel();
        def.setIcon(logoDef);
        this.add(def, BorderLayout.CENTER);

        //Traitement des différentes défaites
        if (null != d) {
            switch (d) {
                case HELIPORT_COULE: {

                    JLabel motif = new JLabel("Votre Héliport à coulé! Vous n'avez pas pu vous échaper!");
                    this.add(motif, BorderLayout.SOUTH);
                    break;
                }
                case INONDATION_ELEVEE: {
                    JLabel motif = new JLabel("Niveau d'inondation trop élevé! vous vous êtes noyés!");
                    this.add(motif, BorderLayout.SOUTH);
                    break;
                }
                case JOUEUR_NOYE: {
                    JLabel motif = new JLabel("Un aventurier s'est noyé!");
                    this.add(motif, BorderLayout.SOUTH);
                    break;
                }
                case TRESOR_COULE: {
                    JLabel motif = new JLabel("Un trésor a sombré!");
                    this.add(motif, BorderLayout.SOUTH);
                    break;
                }
                default:
                    break;
            }
        }

        setVisible(true);
    }

}
