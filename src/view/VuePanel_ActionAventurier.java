/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Enumeration.TypesMessages;
import forbidden_island.Message;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author blanquan
 */
public class VuePanel_ActionAventurier extends JPanel {

    private JButton btnDeplacer, btnAssecher, btnActionSpeciale, btnTerminerTour;
    private JLabel nbActionText, nbActionInt;

    private IHMJeu ihm;

    public VuePanel_ActionAventurier(IHMJeu ihmJ, int nbAction) {

        //initialisation
        ihm = ihmJ;

        //btnDeplacer
        btnDeplacer = new JButton();
        ImageIcon logoDeplacer = new ImageIcon("images/buttons/buttonDeplacer.png");
        JLabel labelDep = new JLabel();
        Image imageDeplacer = logoDeplacer.getImage();
        Image newimgDeplacer = imageDeplacer.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
        logoDeplacer = new ImageIcon(newimgDeplacer);
        labelDep.setIcon(logoDeplacer);

        btnDeplacer.setIcon(logoDeplacer);
        btnDeplacer.setBackground(Color.red);
        btnDeplacer.setBorder(null);
        btnDeplacer.setOpaque(false);

        //btnAssecher
        btnAssecher = new JButton();
        ImageIcon logoAssecher = new ImageIcon("images/buttons/buttonAssecher.png");
        JLabel labelAss = new JLabel();
        Image imageAssecher = logoAssecher.getImage();
        Image newimgAssecher = imageAssecher.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
        logoAssecher = new ImageIcon(newimgAssecher);
        labelAss.setIcon(logoAssecher);

        btnAssecher.setIcon(logoAssecher);
        btnAssecher.setBackground(Color.red);
        btnAssecher.setBorder(null);
        btnAssecher.setOpaque(false);

        //btn Action Spé
        btnActionSpeciale = new JButton();
        ImageIcon logoActionSpeciale = new ImageIcon("images/buttons/buttonActionSpeciale.png");
        JLabel labelAP = new JLabel();
        Image imageActionSpeciale = logoActionSpeciale.getImage();
        Image newimgActionSpeciale = imageActionSpeciale.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
        logoActionSpeciale = new ImageIcon(newimgActionSpeciale);
        labelAP.setIcon(logoActionSpeciale);

        btnActionSpeciale.setIcon(logoActionSpeciale);
        btnActionSpeciale.setBackground(Color.red);
        btnActionSpeciale.setBorder(null);
        btnActionSpeciale.setOpaque(false);

        btnTerminerTour = new JButton("Terminer Tour");
        nbActionText = new JLabel("Nombre action dispo : ");
        nbActionInt = new JLabel(Integer.toString(nbAction));
        //fin initialisation

        //test pour couleur :
        this.setBackground(Color.CYAN);

        JPanel grilleAction = new JPanel(new GridLayout(2, 3));

        grilleAction.add(btnDeplacer);

        grilleAction.add(btnAssecher);

        grilleAction.add(btnActionSpeciale);

        grilleAction.add(nbActionText);

        grilleAction.add(nbActionInt);

        grilleAction.add(btnTerminerTour);

        // grilleAction.add(new JLabel("")); //inutile ici
        grilleAction.setOpaque(false);
        this.add(grilleAction);

        btnDeplacer.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ihm.setSauvType(TypesMessages.DEPLACER);
                Message m = new Message(TypesMessages.DEPLACER);
                ihm.notifierObservateur(m);

                btnDeplacer.setEnabled(false);
            }

        });

        btnAssecher.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ihm.setSauvType(TypesMessages.ASSECHER);
                Message m = new Message(TypesMessages.ASSECHER);
                ihm.notifierObservateur(m);

                btnAssecher.setEnabled(false);
            }

        });

        btnActionSpeciale.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypesMessages.SPECIALE);
                ihm.setSauvType(TypesMessages.SPECIALE);
                ihm.notifierObservateur(m);

                btnActionSpeciale.setEnabled(false);
            }

        });

        btnTerminerTour.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypesMessages.TERMINER_TOUR);
                ihm.notifierObservateur(m);
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

    public JButton getBtnTerminerTour() {
        return btnTerminerTour;
    }

    public void finirTour() {
        //Activation ou non du bouton "Déplacer"
        getBtnDeplacer().setEnabled(false);

        //Activation ou non du bouton "Assécher"
        getBtnAssecher().setEnabled(false);

        //Activation ou non du bouton "Action spéciale"
        getBtnActionSpeciale().setEnabled(false);
    }

    public void misAJourNbAction(int nbAction) {
        nbActionInt.setText(Integer.toString(nbAction));
    }

}
