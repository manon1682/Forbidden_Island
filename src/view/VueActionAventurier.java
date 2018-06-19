/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Aventurier.Aventurier;
import Aventurier.Ingénieur;
import Aventurier.Pilote;
import Enumeration.TypesMessages;
import forbidden_island.Message;
import forbidden_island.Observe;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author blanquan
 */
public class VueActionAventurier extends JPanel {

    private JButton btnDeplacer, btnAssecher, btnActionSpeciale, btnTerminerTour;
    private JLabel nbActionText, nbActionInt;
    private TypesMessages sauvType;
    protected Aventurier a;

    private IHMJeu ihm;

    public VueActionAventurier(Aventurier aventurier) {

        //initialisation
        btnDeplacer = new JButton("Déplacer");
        btnAssecher = new JButton("Assécher");
        btnActionSpeciale = new JButton("Action Spécial");
        btnTerminerTour = new JButton("Terminer Tour");
        nbActionText = new JLabel("Nombre action dispo : ");
        nbActionInt = new JLabel("3"); //valeur de base

        a = aventurier;
        //fin initialisation

        //test pour couleur :
        this.setBackground(Color.CYAN);

        JPanel grilleAction = new JPanel(new GridLayout(2, 3));

        for (int i = 0; i < 6; i++) {

            if (i == 0) {
                grilleAction.add(btnDeplacer);
            } else if (i == 1) {
                grilleAction.add(btnAssecher);
            } else if (i == 2) {
                grilleAction.add(btnActionSpeciale);
            } else if (i == 3) {
                grilleAction.add(nbActionText);
            } else if (i == 4) {
                grilleAction.add(nbActionInt);
            } else if (i == 5) {
                grilleAction.add(btnTerminerTour);
            } else {
                grilleAction.add(new JLabel("")); //inutile ici
            }

        }

        this.add(grilleAction);

        btnDeplacer.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sauvType = TypesMessages.DEPLACER;
                Message m = new Message(TypesMessages.DEPLACER);
                ihm.notifierObservateur(m);
                
                btnDeplacer.setEnabled(false);
            }

        });

        btnAssecher.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sauvType = TypesMessages.ASSECHER;
                Message m = new Message(TypesMessages.ASSECHER);
                ihm.notifierObservateur(m);
                
                btnDeplacer.setEnabled(false);
            }

        });

        btnActionSpeciale.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypesMessages.SPECIALE);
                sauvType = TypesMessages.SPECIALE;
                ihm.notifierObservateur(m);
                
                btnActionSpeciale.setEnabled(false);
            }

        });

        btnTerminerTour.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sauvType = TypesMessages.TOUR_SUIVANT;
                Message m = new Message(TypesMessages.TOUR_SUIVANT);
                ihm.notifierObservateur(m);
                
                btnActionSpeciale.setEnabled(false);
            }
        });
    }

    public JButton getBtnAssecher() {
        return btnAssecher;
    }

    public JButton getBtnDeplacer() {
        return btnDeplacer;
    }

    public JButton getBtnActionSpeciale() {
        return btnActionSpeciale;
    }
}
