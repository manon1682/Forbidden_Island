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
import java.awt.Color;
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
public class VueInitialisation extends Observe {

    private final JFrame window;
    private final JPanel mainPanel;
    private JPanel panelCentre;
    private JComboBox choixNbJoueur;
    private String[] nbjoueurs;
    private JPanel panelHaut;
    private JPanel panelBas;
    private JButton valider;
    private JButton manuel;
    private int nbJ;
    private ArrayList<JTextField> saisirJ = new ArrayList<>();
    private Graphics g;

    public VueInitialisation() {
        this.nbjoueurs = new String[]{"2", "3", "4"};
        System.out.println("Le fashisme");
        this.window = new JFrame() {
            @Override
            public void paintComponents(Graphics g) {
                try {
                    Image img = ImageIO.read(new FileInputStream("images/background/image_init.jpg"));
                    g.drawImage(img, 0, 0, null);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        window.setSize(500, 350);

        window.setTitle("Ile Interdite");

        JLabel image = new JLabel(new ImageIcon("image_init.jpg"));
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(image, BorderLayout.EAST);
        mainPanel.setBackground(new Color(0,0,0));
        this.window.add(mainPanel);
        System.out.println("BITE");

        // Panel Haut
        panelHaut = new JPanel(new GridLayout(4, 2));
        panelHaut.setBackground(new Color(0,0,0));
        panelHaut.add(new JLabel("Ile Interdite"));
        manuel = new JButton("Manuel");
        panelHaut.add(manuel);

        panelHaut.add(new JLabel());
        panelHaut.add(new JLabel());

        panelHaut.add(new JLabel("Nombre de Joueur : "));
        choixNbJoueur = new JComboBox(nbjoueurs);
        panelHaut.add(choixNbJoueur);

        panelHaut.add(new JLabel());

        mainPanel.add(panelHaut, BorderLayout.NORTH);

        //Panel Centre
        panelCentre = new JPanel(new GridLayout(4, 2));

        panelCentre.add(new JLabel());
        panelCentre.add(new JLabel());

        panelCentre.add(new JLabel());
        panelCentre.add(new JLabel());

        panelCentre.add(new JLabel());
        panelCentre.add(new JLabel());

        panelCentre.add(new JLabel());
        panelCentre.add(new JLabel());
        
        panelCentre.setBackground(new Color(0,0,0));

        mainPanel.add(BorderLayout.CENTER, panelCentre);

        //Panel bas
        mainPanel.add(BorderLayout.SOUTH, valider = new JButton("Valider"));

        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (choixNbJoueur.isEnabled()) {
                    nbJ = choixNbJoueur.getSelectedIndex() + 2;

                    for (int i = 0; i < 7; i++) {
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
                    m.setNbJoueur(nbJ);
                    m.setNom(nom);
                    notifierObservateur(m);
                }

            }
        });

        window.setVisible(true);
    }

    public void paint(Graphics g) {
        try {
            Image img = ImageIO.read(new FileInputStream("images/background/image_init.jpg"));
            g.drawImage(img, 0, 0, null);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void afficher() {
        window.repaint();
        window.setVisible(true);
    }

    public void desafficher() {
        window.setVisible(false);
    }

}
