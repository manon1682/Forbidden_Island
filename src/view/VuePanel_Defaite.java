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
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
        ImageIcon logoIle= new ImageIcon("images/logo/ile-interdite-logo.png");
        JLabel ile = new JLabel();
        ile.setIcon(logoIle);
        JPanel panNord = new JPanel(new GridLayout(1, 3));
        panNord.add(new JLabel());
        panNord.add(ile);
        panNord.add(new JLabel());
        panNord.setOpaque(false);
        add(panNord, BorderLayout.NORTH);
        
        //CENTRE
        ImageIcon logoDef = new ImageIcon("images/defaite/defaite.png");
        JLabel def = new JLabel();
        def.setIcon(logoDef);
        JPanel panCentre = new JPanel(new GridLayout(1,3));
        panCentre.setOpaque(false);
        panCentre.add(new JLabel());
        panCentre.add(def);
        panCentre.add(new JLabel());
        
        
        this.add(panCentre, BorderLayout.CENTER);

        //SUD : Traitement des différentes défaites
        JPanel panSud = new JPanel(new GridLayout(1,3));
        if (null != d) {
            switch (d) {
                case HELIPORT_COULE: {

                    motif = new JLabel("Votre Héliport à coulé! Vous n'avez pas pu vous échaper!");
                    motif.setFont(new Font("Serif", Font.ITALIC, 30));
                    motif.setForeground(new Color(225,221,136));
                    break;
                }
                case INONDATION_ELEVEE: {
                    motif = new JLabel("Niveau d'inondation trop élevé! vous vous êtes noyés!");
                    motif.setFont(new Font("Serif", Font.ITALIC, 30));
                    motif.setForeground(new Color(225,221,136));
                    break;
                }
                case JOUEUR_NOYE: {
                    motif = new JLabel("Un aventurier s'est noyé!");
                    motif.setFont(new Font("Serif", Font.ITALIC, 30));
                    motif.setForeground(new Color(225,221,136));
                    break;
                }
                case TRESOR_COULE: {
                    motif = new JLabel("Un trésor a sombré!");
                    motif.setFont(new Font("Serif", Font.ITALIC, 30));
                    motif.setForeground(new Color(225,221,136));
                    break;
                }
                default:
                    break;
            }
        }
        panSud.add(new JLabel());
        panSud.add(motif);
        panSud.add(new JLabel());
        panSud.setOpaque(false);
        this.add(panSud, BorderLayout.SOUTH);

        setVisible(true);
    }

}
