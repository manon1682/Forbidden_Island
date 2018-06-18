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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    private JPanel panelBas;
    private JButton valider;
    private int nbJ;
    private ArrayList<JTextField> saisirJ = new ArrayList<>();

    public VueInitialisation() {
        this.nbjoueurs = new String[]{"2", "3", "4", "5", "6"};
        this.window = new JFrame();
        window.setSize(500, 350);

        window.setTitle("Ile Interdite");
        mainPanel = new JPanel(new BorderLayout());
        this.window.add(mainPanel);

        mainPanel.setBackground(new Color(230, 230, 230));

        // NORD
        mainPanel.add(BorderLayout.NORTH, new JLabel("Ile Interdite"));

        //Panel Centre
        panelCentre = new JPanel(new GridLayout(7, 2));
        panelCentre.add(new JLabel("Nombre de Joueur : "));
        choixNbJoueur = new JComboBox(nbjoueurs);
        panelCentre.add(choixNbJoueur);

        panelCentre.add(new JLabel());
        panelCentre.add(new JLabel());

        panelCentre.add(new JLabel());
        panelCentre.add(new JLabel());

        panelCentre.add(new JLabel());
        panelCentre.add(new JLabel());

        panelCentre.add(new JLabel());
        panelCentre.add(new JLabel());
        
        panelCentre.add(new JLabel());
        panelCentre.add(new JLabel());
        
        panelCentre.add(new JLabel());
        panelCentre.add(new JLabel());

        mainPanel.add(BorderLayout.CENTER, panelCentre);

        //Panel bas
        mainPanel.add(BorderLayout.SOUTH, valider = new JButton("Valider"));

        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (choixNbJoueur.isEnabled()) {
                    nbJ = choixNbJoueur.getSelectedIndex() + 2;

                    for (int i = 0; i < nbJ; i++) {
                        saisirJ.add(new JTextField("Joueur " + (i + 1)));

                        panelCentre.remove(3);
                        panelCentre.remove(2);
                        panelCentre.add(new JLabel("Nom joueur " + (i + 1)));
                        panelCentre.add(saisirJ.get(i));

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
                    notifierObservateur(m);
                }

            }
        });

        window.setVisible(true);
    }

    public void afficher() {
        window.setVisible(true);
    }

    public void desafficher() {
        window.setVisible(false);
    }

}
