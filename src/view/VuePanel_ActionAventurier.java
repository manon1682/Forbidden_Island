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
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
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
    private Image imageFond;

    private IHMJeu ihm;

    public VuePanel_ActionAventurier(IHMJeu ihmJeu, int nbAction) {

        //initialisation
        ihm = ihmJeu;

        //btnDeplacer
        btnDeplacer = new JButton();
        ImageIcon logoDeplacer = new ImageIcon("images/buttons/buttonDeplacer3.png");
        JLabel labelDep = new JLabel();
        Image imageDeplacer = logoDeplacer.getImage();
        Image newimgDeplacer = imageDeplacer.getScaledInstance(150, 75, java.awt.Image.SCALE_SMOOTH);
        logoDeplacer = new ImageIcon(newimgDeplacer);
        labelDep.setIcon(logoDeplacer);

        btnDeplacer.setIcon(logoDeplacer);
        btnDeplacer.setBackground(Color.red);
        btnDeplacer.setBorder(null);
        btnDeplacer.setOpaque(false);

        //btnAssecher
        btnAssecher = new JButton();
        ImageIcon logoAssecher = new ImageIcon("images/buttons/buttonAssecher3.png");
        JLabel labelAss = new JLabel();
        Image imageAssecher = logoAssecher.getImage();
        Image newimgAssecher = imageAssecher.getScaledInstance(150, 75, java.awt.Image.SCALE_SMOOTH);
        logoAssecher = new ImageIcon(newimgAssecher);
        labelAss.setIcon(logoAssecher);

        btnAssecher.setIcon(logoAssecher);
        btnAssecher.setBackground(Color.red);
        btnAssecher.setBorder(null);
        btnAssecher.setOpaque(false);

        //btn Action Spéciale
        btnActionSpeciale = new JButton();
        ImageIcon logoActionSpeciale = new ImageIcon("images/buttons/buttonActionSpeciale3.png");
        JLabel labelAP = new JLabel();
        Image imageActionSpeciale = logoActionSpeciale.getImage();
        Image newimgActionSpeciale = imageActionSpeciale.getScaledInstance(150, 75, java.awt.Image.SCALE_SMOOTH);
        logoActionSpeciale = new ImageIcon(newimgActionSpeciale);
        labelAP.setIcon(logoActionSpeciale);

        btnActionSpeciale.setIcon(logoActionSpeciale);
        btnActionSpeciale.setBackground(Color.red);
        btnActionSpeciale.setBorder(null);
        btnActionSpeciale.setOpaque(false);

        btnTerminerTour = new JButton();
        ImageIcon logoTerminerTour = new ImageIcon("images/buttons/buttonTerminerTour3.png");
        JLabel labelTT = new JLabel();
        Image imageTerminerTour = logoTerminerTour.getImage();
        Image newimgTerminerTour = imageTerminerTour.getScaledInstance(150, 75, java.awt.Image.SCALE_SMOOTH);
        logoTerminerTour = new ImageIcon(newimgTerminerTour);
        labelTT.setIcon(logoTerminerTour);

        btnTerminerTour.setIcon(logoTerminerTour);
        btnTerminerTour.setBackground(Color.red);
        btnTerminerTour.setBorder(null);
        btnTerminerTour.setOpaque(false);

        nbActionText = new JLabel();
        ImageIcon logoNbActionText = new ImageIcon("images/buttons/nbaction.png");
        Image imageNbActionText = logoNbActionText.getImage();
        Image newimgNbActionText = imageNbActionText.getScaledInstance(150, 75, java.awt.Image.SCALE_SMOOTH); // Redimension de l'icône 
        logoNbActionText = new ImageIcon(newimgNbActionText);
        nbActionText.setIcon(logoNbActionText);

//        nbActionInt = new JLabel(Integer.toString(nbAction));
        nbActionInt = new JLabel();
        ImageIcon logoNbActionInt = chargementImageNbAction(nbAction);
        Image imageNbActionInt = logoNbActionInt.getImage();
        Image newimgNbActionInt = imageNbActionInt.getScaledInstance(150, 75, java.awt.Image.SCALE_SMOOTH); // Redimension de l'icône 
        logoNbActionInt = new ImageIcon(newimgNbActionInt);
        nbActionInt.setIcon(logoNbActionInt);
        //fin initialisation
        
        this.setLayout(new GridLayout(2, 3));

        this.add(btnDeplacer);
        this.add(btnAssecher);
        this.add(nbActionText);
        this.add(btnActionSpeciale);
        this.add(btnTerminerTour);
        this.add(nbActionInt);

//        grillebtnAction.setBackground(new Color(32,32,32));
//        grillebtnAction.setBorder(BorderFactory.createLineBorder(Color.yellow, 3));
        try {
            this.imageFond = ImageIO.read(new FileInputStream("images/background/fondAction.png"));

        } catch (IOException ex) {
            System.err.println("Erreur de lecture de fondAction.png");
        }


//Action Listener
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
        //nbActionInt.setText(Integer.toString(nbAction));
        ImageIcon logoNbActionInt = chargementImageNbAction(nbAction);
        Image imageNbActionInt = logoNbActionInt.getImage();
        Image newimgNbActionInt = imageNbActionInt.getScaledInstance(150, 75, java.awt.Image.SCALE_SMOOTH); // Redimension de l'icône 
        logoNbActionInt = new ImageIcon(newimgNbActionInt);
        nbActionInt.setIcon(logoNbActionInt);
    }
    
    public ImageIcon chargementImageNbAction(int nb){
        ImageIcon img;
        switch(nb){
            case 0:{
                img = new ImageIcon("images/buttons/nombre0.png");
            } break;
            case 1:{
                img = new ImageIcon("images/buttons/nombre1.png");
            } break;
            case 2:{
                img = new ImageIcon("images/buttons/nombre2.png");
            } break;
            case 3:{
                img = new ImageIcon("images/buttons/nombre3.png");
            } break;
            default:{
                img = new ImageIcon("images/buttons/nombre4.png");
            } break;
        }
        return img;
        
    }
    
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(imageFond, 0, 0, this.getWidth(), this.getHeight(), null, this);
    }

}
