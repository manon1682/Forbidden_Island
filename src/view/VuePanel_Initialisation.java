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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
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
public class VuePanel_Initialisation extends JPanel {

    // Variable Fenêtre
    private final JFrame window;
    private int height;
    private int width;

    private String[] nbjoueurs;

    private JPanel panelCentre;
    private JPanel panelHaut;
    private JPanel panelHautTitre;
    private JPanel panelHautCentre;
    private JPanel panHauCent1;
    private JPanel panHauCent2;
    private JPanel panelBas;

    private JButton btnValider;
    private JButton btnManuel;

    private JTextField saisieNom;
    private JLabel labTitre;
    private JLabel labNbJ;
    private JLabel labNomJ;

    //Selection des joueurs
    private JComboBox choixNbJoueur;
    private int nbJ;
    private ArrayList<JTextField> saisirJ = new ArrayList<>();

    //Sélection Niveau
    private JLabel labNiv;
    private ButtonGroup groupeNiv;
    private JRadioButton[] boutNiv;
    private JRadioButton bouton;

    //Image fond
    private Image image;

    //Manuel
    private VuePanel_Manuel vMan;

    public VuePanel_Initialisation(IHMJeu ihm) {
        //Taille de la fenêtre
        width = 787;
        height = 787;

        this.nbjoueurs = new String[]{"2", "3", "4"};
        this.window = new JFrame();
        window.setResizable(true);
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

        //PanelHautTitre (division 1/2 de PanelHaut)
        panelHautTitre = new JPanel();
        panelHautTitre.setOpaque(false);

        ImageIcon logoTitre = new ImageIcon("images/logo/ile-interdite-logo.png");
        JLabel logoT = new JLabel();
        logoT.setIcon(logoTitre);
        panelHautTitre.add(logoT);
        panelHaut.add(panelHautTitre, BorderLayout.NORTH);

        //PanelHautCentre : GridLayout 4,1 (division 2/2 de PanelHaut)
        panelHautCentre = new JPanel(new GridLayout(4, 1));
        panelHautCentre.setOpaque(false);

        //PanelHautCentre : Ligne 1 Saut de ligne
        panelHautCentre.add(new JLabel());

        //PanelHautCentre : Ligne 2 Sélection du niveau de difficulté 
        panHauCent1 = new JPanel(new GridLayout(1, 7));
        panHauCent1.setOpaque(false);
        labNiv = new JLabel("Difficulté : ", SwingConstants.RIGHT);
        labNiv.setForeground(Color.white);
        labNiv.setFont(new Font("Arial", Font.PLAIN, 20));
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

        //PanelHautCentre : Ligne 3 Nombre Joueur  
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

        //PanelHautCentre : Ligne 4 Saut de Ligne
        panelHautCentre.add(new JLabel());

        panelHaut.add(panelHautCentre, BorderLayout.CENTER);

        this.add(panelHaut, BorderLayout.NORTH);

        // **** Fin PanelHaut ****
        
        // **** Panel Centre ****
        panelCentre = new JPanel(new GridLayout(10, 3));
        panelCentre.setOpaque(false);
        
        //Par défaut, deux joueurs sont sélectionnés
        nbJ = 2;
        for (int i = 0; i < 2; i++) {
                    saisieNom = new JTextField("Joueur " + (i+1));
                    saisieNom.setBorder(null);
                    saisirJ.add(saisieNom);

                    labNomJ = new JLabel("Nom Aventurier : ", SwingConstants.RIGHT);
                    labNomJ.setForeground(Color.white);
                    labNomJ.setFont(new Font("Arial", Font.PLAIN, 20));
                    ajouterLabelVide(panelCentre, 3);
                    panelCentre.add(labNomJ);
                    panelCentre.add(saisirJ.get(i));
                    panelCentre.add(new JLabel());

                }
        //On rempli le panel de vide en attendant qu'un nombre de joueur soit sélectionné
        for (int i = 0; i < 18; i++) {
            panelCentre.add(new JLabel());
        }

        this.add(BorderLayout.CENTER, panelCentre);

        // **** Fin PanelCentre ****
        // **** Panel bas ****
        //Aspect bouton "Valider"
        btnValider = new JButton();
        ImageIcon logoValider = new ImageIcon("images/icones/iconChecked.png");
        JLabel logoVal = new JLabel();
        logoVal.setIcon(logoValider);
        btnValider.setIcon(logoValider);

        //Aspect bouton "Manuel"
        btnManuel = new JButton();
        ImageIcon logoManuel = new ImageIcon("images/icones/iconBook.png");
        JLabel logoMan = new JLabel();
        logoMan.setIcon(logoManuel);
        btnManuel.setIcon(logoManuel);
        panelBas = new JPanel(new GridLayout(2, 9));
        panelBas.setOpaque(false);

        panelBas.add(new JLabel());
        panelBas.add(btnManuel);
        ajouterLabelVide(panelBas, 5);
        panelBas.add(btnValider);
        panelBas.add(new JLabel());

        for (int i = 0; i < 9; i++) {
            panelBas.add(new JLabel());
        }

        this.add(BorderLayout.SOUTH, panelBas);

        // **** Fin PanelBas ****
        choixNbJoueur.addItemListener(
                new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                nbJ = choixNbJoueur.getSelectedIndex() + 2;
                for (int i = 0; i < 30; i++) {
                    panelCentre.remove(0);
                }
                //On vide l'ArrayList de saisie pour laisser place aux nouveaux textField 
                saisirJ.removeAll(saisirJ);
                
                //Préparation affichage des label et champs de saisie
                for (int i = 0; i < nbJ; i++) {
                    labNomJ = new JLabel("Nom Aventurier : ", SwingConstants.RIGHT);
                    labNomJ.setForeground(Color.white);
                    labNomJ.setFont(new Font("Arial", Font.PLAIN, 20));
                    
                    System.out.println("i = "+ i);
                    saisieNom = new JTextField("Joueur " + (i + 1));
                    saisieNom.setBorder(null);
                    saisirJ.add(saisieNom);

                    //saut de ligne
                    ajouterLabelVide(panelCentre, 3);
                    
                    //Ajout des label et champs de saisie
                    panelCentre.add(labNomJ);
                    panelCentre.add(saisirJ.get(i));
                    panelCentre.add(new JLabel());

                }
                
                //On complète le GridLayout
                for (int i = 0; i < 30 - 6 * nbJ; i++) {
                    panelCentre.add(new JLabel());

                }
                //Affichage rafraichie
                panelCentre.updateUI();
            }

        });

        //ActionListener du bouton Valider
        btnValider.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                //Envoie du nom des joueurs au controleur
                ArrayList<String> nom = new ArrayList<>();
                int res =0;
                    res = saisirJ.size()-nbJ;
                for (int i = 0; i<nbJ  ; i++) {
                    nom.add(saisirJ.get(i+res).getText());
                    System.out.println(saisirJ.get(i+res).getText());
                }

                Message m = new Message(TypesMessages.NOUVELLE_PARTIE);
                m.setNom(nom);

                //If pour créer le message avec le niveau de difficulté sélectionné
                if (boutNiv[0].isSelected()) {
                    m.setNiveau(TypesNiveaux.NOVICE);
                } else if (boutNiv[1].isSelected()) {
                    m.setNiveau(TypesNiveaux.NORMAL);
                } else if (boutNiv[2].isSelected()) {
                    m.setNiveau(TypesNiveaux.ELITE);
                } else {
                    m.setNiveau(TypesNiveaux.LEGENDAIRE);
                }

                ihm.notifierObservateur(m);

            }
        }
        );

        //ActionListener du Manuel
        btnManuel.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                vMan = new VuePanel_Manuel();
                vMan.afficher();
                vMan.repaint();
            }
        }
        );

        window.setVisible(
                true);

    }

    //Crée des label vide dans le label passé en paramètre
    public void ajouterLabelVide(JPanel panel, int nbLabel) {
        for (int i = 0; i < nbLabel; i++) {
            panel.add(new JLabel());
        }
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
