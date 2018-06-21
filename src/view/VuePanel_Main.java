/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Aventurier.Aventurier;
import Cartes.CarteTresor;
import Enumeration.CarteUtilisable;
import Enumeration.TypesMessages;
import forbidden_island.Message;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author blanquan
 */
public class VuePanel_Main extends JPanel {

    private IHMJeu ihm;
    private Aventurier a;
    private boolean clickable;

    public VuePanel_Main(Aventurier aventurier, IHMJeu ihmJ) {

        
        //Initialisation
        ihm = ihmJ;
        a = aventurier;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        this.setPreferredSize(new Dimension(768,188));
        
        setLayout(new BorderLayout());
        JPanel main = new JPanel(new GridLayout(1, 6));
        JPanel panelNord = new JPanel(new BorderLayout());

        panelNord.add(new JLabel(aventurier.getPseudo()), BorderLayout.WEST);
        panelNord.add(new JLabel(aventurier.getRole()), BorderLayout.EAST);

        add(panelNord, BorderLayout.NORTH);
        add(main, BorderLayout.CENTER);

        CarteUtilisable laCarte = CarteUtilisable.PIERRE_SACRE;

        for (int i = 0; i < 6; i++) {
            int n = 0;
            //On regarde le nombre de carte correspondant à laCarte
            for (CarteTresor carte : a.getMainA()) {
                if (carte.getNom().equals(laCarte.toString())) {
                    n = n + 1;
                }
            }

            //Créer le panel carte
            VuePanel_Carte carte = new VuePanel_Carte(n, laCarte);
            carte.setIhm(this);
            CarteTresor c = new CarteTresor(laCarte);

            if (n != 0) {
                carte.getUtiliser().setVisible(false);
                carte.getDonner().setVisible(false);
                carte.getDefausser().setVisible(false);

                carte.repaint();

                carte.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        Message m = new Message(TypesMessages.CARTE_CLICK);
                        m.setVueCarte((VuePanel_Carte) e.getSource());
                        ihm.notifierObservateur(m);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        //On vérifie qu'on sorte "réellement" de la carte,
                        //qu'on aille pas sur un bouton positionner sur la carte (ce qui entrainer ce mouseExited)
                        if (carte.getSize().width < e.getX() || 0 > e.getX()
                                || carte.getSize().height < e.getY() || 0 > e.getY()) {

                            carte.getUtiliser().setVisible(false);
                            carte.getDonner().setVisible(false);
                            carte.getDefausser().setVisible(false);
                            repaint();
                        }
                    }
                });
            }

            main.add(carte);
            laCarte = laCarte.getNext();

        }

    }

    public VuePanel_Main(Aventurier aventurier, IHMJeu ihmJ, boolean clickable) {
        //Initialisation
        this.setPreferredSize(new Dimension(768/2,188/2));
        a = aventurier;
        ihm = ihmJ;

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(new BorderLayout());
        JPanel main = new JPanel(new GridLayout(1, 6));
        JPanel panelNord = new JPanel(new BorderLayout());

        panelNord.add(new JLabel(aventurier.getPseudo()), BorderLayout.WEST);
        panelNord.add(new JLabel(aventurier.getRole()), BorderLayout.EAST);

        add(panelNord, BorderLayout.NORTH);
        add(main, BorderLayout.CENTER);

        CarteUtilisable laCarte = CarteUtilisable.PIERRE_SACRE;

        setClickable(clickable);

        if (isClickable()) {
            this.setBorder(BorderFactory.createLineBorder(Color.green, 4));

        }

        for (int i = 0; i < 6; i++) {
            int n = 0;
            for (CarteTresor carte : a.getMainA()) {
                if (carte.getNom().equals(laCarte.toString())) {
                    n = n + 1;
                }
            }

            VuePanel_Carte carte = new VuePanel_Carte(n, laCarte);
            
            if (n != 0) {
                carte.getUtiliser().setVisible(false);
                carte.getDonner().setVisible(false);
                carte.getDefausser().setVisible(false);
                carte.repaint();
            }
            carte.setSize(new Dimension(128/2, 188/2));
            main.add(carte);
            laCarte = laCarte.getNext();

            if (isClickable()) {
                this.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent me) {
                        if (isClickable()) {
                            Message m = new Message(TypesMessages.DONNER_CARTE);
                            m.setJoueur(((VuePanel_Main) me.getComponent()).getA());
                            m.setVueCarte(ihm.getSauvCarte());
                            ((VuePanel_Main)me.getComponent()).setClickable(false);
                            ihm.notifierObservateur(m);
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent me) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent me) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent me) {
                    }

                    @Override
                    public void mouseExited(MouseEvent me) {
                    }
                });

            }
        }
    }

    public void notifierObservateur(Message m) {
        ihm.notifierObservateur(m);
    }

    public void setSauvType(TypesMessages t) {
        ihm.setSauvType(t);
    }

    public Aventurier getA() {
        return a;
    }

    public void setA(Aventurier a) {
        this.a = a;
    }

    public boolean isClickable() {
        return clickable;
    }

    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }
    
    public IHMJeu getIHM(){
        return ihm;
    }

}
