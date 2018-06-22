/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Enumeration.Defaite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author cabezama
 */
public class VuePanel_Defaite extends JPanel {

    private JLabel motif;

    public VuePanel_Defaite(Defaite d) {
        setLayout(new BorderLayout());
        setBackground(new Color(0, 0, 0, 210));

        // NORD
        ImageIcon logoIle = new ImageIcon("images/logo/ile-interdite-logo.png");
        JLabel ile = new JLabel("", SwingConstants.CENTER);
        ile.setIcon(logoIle);

        this.add(ile, BorderLayout.NORTH);

        //CENTRE
        ImageIcon logoDef = new ImageIcon("images/defaite/defaite_photoshop.png");
        JLabel def = new JLabel("", SwingConstants.CENTER);
        def.setIcon(logoDef);

        this.add(def, BorderLayout.CENTER);
        motif = new JLabel("", SwingConstants.CENTER);
        motif.setFont(new Font("Serif", Font.ITALIC, 35));
        motif.setForeground(new Color(225, 221, 136));

        //SUD : Traitement des différentes défaites
        if (null != d) {
            switch (d) {
                case HELIPORT_COULE: {
                    motif.setText("Votre Héliport a coulé ! Vous n'avez pas pu vous échapper !");
                    break;
                }
                case INONDATION_ELEVEE: {
                    motif.setText("Niveau d'inondation trop élevé ! Vous vous êtes noyés !");
                    break;
                }
                case JOUEUR_NOYE: {
                    motif.setText("Un aventurier s'est noyé !");
                    break;
                }
                case TRESOR_COULE: {
                    motif.setText("Un trésor a sombré !");
                    break;
                }
                default:
                    break;
            }
        }

        this.add(motif, BorderLayout.SOUTH);

        setVisible(true);
    }

}
