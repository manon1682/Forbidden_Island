/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Enumeration.TypesMessages;
import forbidden_island.Message;
import forbidden_island.Observe;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author cabezama
 */
public class VueInitialisation extends JPanel{

    private final JFrame window;
    //private final JPanel mainPanel;
    private JPanel panelCentre;
    private JComboBox choixNbJoueur;
    private String[] nbjoueurs;
    private JPanel panelHaut;
    private JPanel panelBas;
    private JButton valider;
    private JButton manuel;
    private int nbJ;
    private ArrayList<JTextField> saisirJ = new ArrayList<>();

    
    private Image image ;

    public VueInitialisation(IHMJeu ihm) {
        this.nbjoueurs = new String[]{"2", "3", "4"};
        this.window = new JFrame();
        window.setSize(500, 350);

        window.setTitle("Ile Interdite");
        try {
            this.image = ImageIO.read(new FileInputStream("images/background/image_init_size.png"));
            
        } catch (IOException ex) {
            System.err.println("Erreur de lecture de image_init.jpg");
        }
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        this.window.add(this);
        
        

        // Panel Haut
        panelHaut = new JPanel(new GridLayout(4, 2));
        panelHaut.setOpaque(false);
        panelHaut.add(new JLabel("Ile Interdite"));
        manuel = new JButton("Manuel");
        panelHaut.add(manuel);
        
        panelHaut.add(new JLabel());
        panelHaut.add(new JLabel());

        panelHaut.add(new JLabel("Nombre de Joueur : "));
        choixNbJoueur = new JComboBox(nbjoueurs);
        panelHaut.add(choixNbJoueur);

        panelHaut.add(new JLabel());

        this.add(panelHaut, BorderLayout.NORTH);

        //Panel Centre
        panelCentre = new JPanel(new GridLayout(4, 2));
        panelCentre.setOpaque(false);
        
        for(int i = 0; i < 8; i++){
           panelCentre.add(new JLabel()); 
        }

        this.add(BorderLayout.CENTER, panelCentre);

        //Panel bas
        valider = new JButton("Valider");        
        this.add(BorderLayout.SOUTH, valider);

        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (choixNbJoueur.isEnabled()) {
                    nbJ = choixNbJoueur.getSelectedIndex() + 2;

                    for (int i = 0; i < 8; i++) {
                        panelCentre.remove(0);
                    }

                    for (int i = 0; i < nbJ; i++) {
                        saisirJ.add(new JTextField("Joueur " + (i + 1)));

                        panelCentre.add(new JLabel("Nom joueur " + (i + 1)));
                        panelCentre.add(saisirJ.get(i));

                    }
                    for (int i = 0; i < 4 - nbJ; i++) {
                        panelCentre.add(new JLabel());
                        panelCentre.add(new JLabel());
                    }
                    choixNbJoueur.setEnabled(false);
                    window.setVisible(true);
                } else {

                    ArrayList<String> nom = new ArrayList<>();

                    for (JTextField saisi : saisirJ) {
                        nom.add(saisi.getText());
                    }

                    Message m = new Message(TypesMessages.NOUVELLE_PARTIE, null);
                    m.setNom(nom);
                    m.setNbJoueur(nbJ);
                    ihm.notifierObservateur(m);
                }

            }
        });
       
        window.setVisible(true);
        this.repaint();
    }
    
    @Override
    /**
     * paintComponent permet de gérer l'affichage / la mise à jour des
     * images, à condition que le paintComponent de chaque objet soit appelé
     * avec le même contexte graphique (Graphics)
     */
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(image, 0, 0, 500, 350, null, this);
    }

    public void afficher() {
        window.setVisible(true);
    }

    public void desafficher() {
        window.setVisible(false);
    }

}
