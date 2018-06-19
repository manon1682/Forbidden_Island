/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Enumeration.TypesMessages;
import forbidden_island.Message;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author cabezama
 */
public class VueInitialisation extends JPanel {

    private final JFrame window;
    //private final JPanel mainPanel;
    private JPanel panelCentre;
    private JComboBox choixNbJoueur;
    private String[] nbjoueurs;
    private JPanel panelHaut;
    private JPanel panelHautTitre;
    private JPanel panelHautCentre;
    private JPanel panHauCent1;
    private JPanel panHauCent2;
    private JPanel panHauCent3;
    private JPanel panelBas;
    private JButton valider;
    private JButton manuel;
    private JLabel labTitre;
    private JLabel labNiv;
    private ButtonGroup groupeNiv;
    private JRadioButton [] boutNiv;
    private JRadioButton bouton;
    private int nbJ;
    private ArrayList<JTextField> saisirJ = new ArrayList<>();

    private Image image;

    public VueInitialisation(IHMJeu ihm) {
       
        
        this.nbjoueurs = new String[]{"2", "3", "4"};
        this.window = new JFrame();
        window.setResizable(false);
        window.setSize(500, 600);
        window.setLocationRelativeTo(null);

        window.setTitle("Ile Interdite");
        try {
            this.image = ImageIO.read(new FileInputStream("images/background/image_init.jpg"));

        } catch (IOException ex) {
            System.err.println("Erreur de lecture de image_init.jpg");
        }
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        this.window.add(this);

        // **** Panel Haut ****
        panelHaut = new JPanel(new BorderLayout());
        panelHaut.setOpaque(false);
        
        //PanelHautTitre
        labTitre = new JLabel("Ile Interdite");
        labTitre.setForeground(Color.white);
        labTitre.setFont(new Font("Arial Black", Font.PLAIN, 40));
        panelHautTitre = new JPanel();
        panelHautTitre.setOpaque(false);
        panelHautTitre.add(labTitre);
        panelHaut.add(panelHautTitre, BorderLayout.NORTH);
        
        panelHautCentre = new JPanel(new GridLayout(5, 1));
        panelHautCentre.setOpaque(false);
        //Ligne 1 Saut de ligne
        panelHautCentre.add(new JLabel());
      
        //Ligne 2 Sélection du niveau
        panHauCent1 = new JPanel(new GridLayout(1, 5));
        panHauCent1.setOpaque(false);
        labNiv= new JLabel("Niveau");
        labNiv.setForeground(Color.white);
        panHauCent1.add(labNiv);
        
        groupeNiv = new ButtonGroup();
        
        boutNiv = new JRadioButton[4];
        
        bouton = new JRadioButton("Novice");
        boutNiv[0] = bouton;
        groupeNiv.add(bouton);
        
        bouton = new JRadioButton("Normal");
        boutNiv[1] = bouton;
        groupeNiv.add(bouton);
        
        bouton = new JRadioButton("Elite");
        boutNiv[2] = bouton;
        groupeNiv.add(bouton);
        
        bouton = new JRadioButton("Légendaire");
        boutNiv[3] = bouton;
        groupeNiv.add(bouton);
        
        boutNiv[1].setSelected(true);
        
        panHauCent1.add(boutNiv[0]);
        panHauCent1.add(boutNiv[1]);
        panHauCent1.add(boutNiv[2]);
        panHauCent1.add(boutNiv[3]);
        panelHautCentre.add(panHauCent1);
        
        //Ligne 3 Choix Role Aléatoire/Choisis
        
        //Ligne 4 Nombre Joueur  
        panelHautCentre.add(new JLabel());
        panelHautCentre.add(new JLabel("Nombre de Joueur : "));
        choixNbJoueur = new JComboBox(nbjoueurs);
        panelHautCentre.add(choixNbJoueur);
        panelHautCentre.add(new JLabel());
        
        
        //Ligne 5 Saut de Ligne
        panelHautCentre.add(new JLabel());
        panelHautCentre.add(new JLabel());
        panelHautCentre.add(new JLabel());
        panelHautCentre.add(new JLabel());

        panelHaut.add(panelHautCentre, BorderLayout.CENTER);
        
        this.add(panelHaut, BorderLayout.NORTH);

        // **** Fin PanelHaut ****
        
        //Panel Centre
        panelCentre = new JPanel(new GridLayout(4, 2));
        panelCentre.setOpaque(false);

        for (int i = 0; i < 8; i++) {
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

                    Message m = new Message(TypesMessages.NOUVELLE_PARTIE);
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
     * paintComponent permet de gérer l'affichage / la mise à jour des images, à
     * condition que le paintComponent de chaque objet soit appelé avec le même
     * contexte graphique (Graphics)
     */
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(image, 0, 0, 500, 600, null, this);
    }

    public void afficher() {
        window.setVisible(true);
    }

    public void desafficher() {
        window.setVisible(false);
    }

}
