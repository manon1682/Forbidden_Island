/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Enumeration.TypesMessages;
import Enumeration.TypesNiveaux;
import forbidden_island.Message;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
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
import javax.swing.SwingConstants;

/**
 *
 * @author cabezama
 */
public class VueInitialisation extends JPanel {

    // Variable Fenêtre
    private final JFrame window;
    private int height;
    private int width;
    
    
    private JPanel panelCentre;
    private JComboBox choixNbJoueur;
    private String[] nbjoueurs;
    private JPanel panelHaut;
    private JPanel panelHautTitre;
    private JPanel panelHautCentre;
    private JPanel panHauCent1;
    private JPanel panHauCent2;
    private JPanel panelBas;
    private JPanel panGridBas;
    private JButton valider;
    private JButton manuel;
    private JLabel labTitre;
    private JLabel labNiv;
    private JLabel labNbJ;
    private JLabel labNomJ;
    private ButtonGroup groupeNiv;
    private JRadioButton[] boutNiv;
    private JRadioButton bouton;
    private int nbJ;
    private ArrayList<JTextField> saisirJ = new ArrayList<>();
    

    private Image image;

    public VueInitialisation(IHMJeu ihm) {
        width = 787;
        height = 787;

        this.nbjoueurs = new String[]{"2", "3", "4"};
        this.window = new JFrame();
        window.setResizable(false);
        window.setSize(width, height);
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
        labTitre.setFont(new Font("Serif", Font.ROMAN_BASELINE, 100));
        panelHautTitre = new JPanel();
        panelHautTitre.setOpaque(false);
        panelHautTitre.add(labTitre);
        panelHaut.add(panelHautTitre, BorderLayout.NORTH);

        //PanelHautCentre
        panelHautCentre = new JPanel(new GridLayout(4, 1));
        panelHautCentre.setOpaque(false);
        //Ligne 1 Saut de ligne
        panelHautCentre.add(new JLabel());

        //Ligne 2 Sélection du niveau + Choix Role Aléatoire/Choisis
        panHauCent1 = new JPanel(new GridLayout(1, 6));
        panHauCent1.setOpaque(false);
        labNiv = new JLabel("Niveau : ", SwingConstants.RIGHT);
        labNiv.setForeground(Color.white);
        labNiv.setFont(new Font("Arial", Font.PLAIN, 18));
        panHauCent1.add(labNiv);

        groupeNiv = new ButtonGroup();

        boutNiv = new JRadioButton[4];
        
        bouton = new JRadioButton("Novice");
        bouton.setOpaque(false);
        bouton.setForeground(Color.white);
        bouton.setFont(new Font("Arial", Font.PLAIN, 18));
        boutNiv[0] = bouton;
        groupeNiv.add(bouton);

        bouton = new JRadioButton("Normal");
        bouton.setOpaque(false);
        bouton.setForeground(Color.white);
        bouton.setFont(new Font("Arial", Font.PLAIN, 18));
        boutNiv[1] = bouton;
        groupeNiv.add(bouton);

        bouton = new JRadioButton("Elite");
        bouton.setOpaque(false);
        bouton.setForeground(Color.white);
        bouton.setFont(new Font("Arial", Font.PLAIN, 18));
        boutNiv[2] = bouton;
        groupeNiv.add(bouton);

        bouton = new JRadioButton("Légendaire");
        bouton.setOpaque(false);
        bouton.setForeground(Color.white);
        bouton.setFont(new Font("Arial", Font.PLAIN, 18));
        boutNiv[3] = bouton;
        groupeNiv.add(bouton);

        boutNiv[1].setSelected(true);

        panHauCent1.add(boutNiv[0]);
        panHauCent1.add(boutNiv[1]);
        panHauCent1.add(boutNiv[2]);
        panHauCent1.add(boutNiv[3]);
        panHauCent1.add(new JLabel());
        panelHautCentre.add(panHauCent1);
        

        //Ligne 4 Nombre Joueur  
        panHauCent2 = new JPanel(new GridLayout(1, 3));
        panHauCent2.setOpaque(false);
        labNbJ = new JLabel("Nombre de joueurs : ", SwingConstants.RIGHT);
        labNbJ.setForeground(Color.white);
        labNbJ.setFont(new Font("Arial", Font.PLAIN, 20));
        panHauCent2.add(labNbJ);
        choixNbJoueur = new JComboBox(nbjoueurs);
        panHauCent2.add(choixNbJoueur);
        panHauCent2.add(new JLabel());
        panelHautCentre.add(panHauCent2);

        //Ligne 5 Saut de Ligne
        panelHautCentre.add(new JLabel());

        panelHaut.add(panelHautCentre, BorderLayout.CENTER);

        this.add(panelHaut, BorderLayout.NORTH);

        // **** Fin PanelHaut ****
        
        // **** Panel Centre ****
        panelCentre = new JPanel(new GridLayout(18, 3));
        panelCentre.setOpaque(false);

        for (int i = 0; i < 54; i++) {
            panelCentre.add(new JLabel());
        }

        this.add(BorderLayout.CENTER, panelCentre);
        
        // **** Fin PanelCentre ****

        // **** Panel bas ****
        valider = new JButton("Valider");
        manuel = new JButton("Manuel");
        panGridBas = new JPanel(new GridLayout(1,3));
        panGridBas.setOpaque(false);
        
        panGridBas.add(manuel);
        panGridBas.add(new JLabel());
        panGridBas.add(valider);
        
        this.add(BorderLayout.SOUTH, panGridBas);
        
        // **** Fin PanelBas ****
        

        
        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (choixNbJoueur.isEnabled()) {
                    
                    nbJ = choixNbJoueur.getSelectedIndex() + 2;

                    for (int i = 0; i < 54; i++) {
                        panelCentre.remove(0);
                    }

                    for (int i = 0; i < nbJ; i++) {
                        saisirJ.add(new JTextField("Joueur " + (i + 1)));
                        
                        labNomJ = new JLabel("Nom Joueur " + (i+1) + " : ", SwingConstants.RIGHT);
                        labNomJ.setForeground(Color.white);
                        labNomJ.setFont(new Font("Arial", Font.PLAIN, 20));
                        panelCentre.add(new JLabel());
                        panelCentre.add(new JLabel());
                        panelCentre.add(new JLabel());
                        panelCentre.add(labNomJ);
                        panelCentre.add(saisirJ.get(i));
                        panelCentre.add(new JLabel());

                    }
                    for (int i = 0; i < 54 - 6*nbJ; i++) {
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

                    //If pour renvoye le niveau sélectionné
                    if (boutNiv[0].isSelected()) {
                        m.setNiveau(TypesNiveaux.NOVICE);
                    }else if(boutNiv[1].isSelected()){
                        m.setNiveau(TypesNiveaux.NORMAL);
                    }else if(boutNiv[2].isSelected()){
                        m.setNiveau(TypesNiveaux.ELITE);
                    }else{
                        m.setNiveau(TypesNiveaux.LEGENDAIRE);
                    }
                 
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
        g.drawImage(image, 0, 0, width, height, null, this);
    }

    public void afficher() {
        window.setVisible(true);
    }

    public void desafficher() {
        window.setVisible(false);
    }

}
