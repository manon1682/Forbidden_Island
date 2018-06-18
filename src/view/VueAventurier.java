package view;

import Aventurier.Aventurier;
import Aventurier.Ingénieur;
import Aventurier.Pilote;
import forbidden_island.Grille;
import forbidden_island.Message;
import forbidden_island.Observe;
import forbidden_island.Tuile;
import Enumeration.TypesMessages;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

public class VueAventurier extends Observe {

    private final JPanel panelBoutons;
    private final JPanel panelCentre;
    private final JFrame window;
    private final JPanel panelAventurier;
    private final JPanel mainPanel;
    private final JButton btnBouger;
    private final JButton btnAssecher;
    private final JButton btnAutreAction;
    private final JButton btnTerminerTour;
    private JButton btnValider;
    private JPanel panelChoixetVal;
    private JTextField position;
    private TypesMessages sauvType;
    private Tuile tuileSelect;
    private JLabel labelvide = new JLabel("");
    private JLabel labelPos;
    private JLabel labelPosDefaut;
    //comboBox des choix
    private JComboBox listeChoix;
    //Array List qui stock les possibilités de choix
    private String[] choixPoss = new String[36];
    protected Aventurier a;

    public VueAventurier(Aventurier aventurier, Grille gTuile) {

        a = aventurier;
        this.window = new JFrame();
        window.setSize(350, 200);
        //le titre = nom du joueur
        window.setTitle(a.getPseudo());
        mainPanel = new JPanel(new BorderLayout());
        this.window.add(mainPanel);

        mainPanel.setBackground(new Color(230, 230, 230));
        mainPanel.setBorder(BorderFactory.createLineBorder(a.getPion().getCouleur(), 2));

        // =================================================================================
        // NORD : le titre = nom de l'aventurier sur la couleurActive du pion
        this.panelAventurier = new JPanel();
        panelAventurier.setBackground(a.getPion().getCouleur());
        panelAventurier.add(new JLabel(a.getRole(), SwingConstants.CENTER));
        mainPanel.add(panelAventurier, BorderLayout.NORTH);

        // =================================================================================
        // CENTRE : 1 ligne pour position courante
        this.panelCentre = new JPanel(new GridLayout(2, 1));
        this.panelCentre.setOpaque(false);
        this.panelCentre.setBorder(new MatteBorder(0, 0, 2, 0, a.getPion().getCouleur()));
        mainPanel.add(this.panelCentre, BorderLayout.CENTER);

        //Position de départ
        Tuile[][] tuiles = gTuile.getTuiles();
        labelPos = new JLabel(tuiles[a.getL()][a.getC()].getNom(), SwingConstants.CENTER);

        panelCentre.add(labelPos);

        panelCentre.add(labelvide);

        // =================================================================================
        // SUD : les boutons
        this.panelBoutons = new JPanel(new GridLayout(2, 2));
        this.panelBoutons.setOpaque(false);
        mainPanel.add(this.panelBoutons, BorderLayout.SOUTH);

        this.btnBouger = new JButton("Bouger");
        this.btnAssecher = new JButton("Assecher");
        this.btnAutreAction = new JButton("Action Spéciale");
        this.btnTerminerTour = new JButton("Terminer Tour");
        this.btnValider = new JButton("Valider");

        this.panelBoutons.add(btnBouger);

        this.panelBoutons.add(btnAssecher);
        this.panelBoutons.add(btnAutreAction);
        this.panelBoutons.add(btnTerminerTour);

        btnAutreAction.setEnabled(btnAutreActionActive());

        //Action performed
        btnBouger.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sauvType = TypesMessages.DEPLACER;
                Message m = new Message(TypesMessages.DEPLACER, a);

                if (!(btnAssecher.isEnabled())) {
                    btnAssecher.setEnabled(true);
                    panelCentre.remove(panelChoixetVal);
                    panelCentre.add(labelvide);
                    panelCentre.updateUI();
                }
                btnAutreActionActive();

                notifierObservateur(m);
                btnBouger.setEnabled(false);

            }

        });

        btnAssecher.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sauvType = TypesMessages.ASSECHER;
                Message m = new Message(TypesMessages.ASSECHER, a);

                if (!(btnBouger.isEnabled())) {
                    btnBouger.setEnabled(true);
                    panelCentre.remove(panelChoixetVal);
                    panelCentre.add(labelvide);
                    panelCentre.updateUI();
                    if (a.getRole() == "Ingénieur") {
                        Ingénieur ingenieur = (Ingénieur) a;
                        btnBouger.setEnabled(!(ingenieur.getCapaciteUtilisee() >= 0));
                    }
                }

                btnAutreActionActive();

                btnAssecher.setEnabled(false);
                notifierObservateur(m);
            }

        });

        btnAutreAction.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(TypesMessages.SPECIALE, a);

                if (a.getRole() == "Pilote") {
                    sauvType = TypesMessages.DEPLACER;
                } else if (a.getRole() == "Messager") {
                    sauvType = TypesMessages.DONNER_CARTE;
                } else if (a.getRole() == "Navigateur") {
                    sauvType = TypesMessages.DEPLACER;
                } else {
                    sauvType = TypesMessages.ASSECHER;
                }

                if (!(btnBouger.isEnabled())) {
                    btnBouger.setEnabled(true);
                    panelCentre.remove(panelChoixetVal);
                    panelCentre.add(labelvide);
                    panelCentre.updateUI();
                } else if (!(btnAssecher.isEnabled())) {
                    btnAssecher.setEnabled(true);
                    panelCentre.remove(panelChoixetVal);
                    panelCentre.add(labelvide);
                    panelCentre.updateUI();
                }

                notifierObservateur(m);
                btnAutreAction.setEnabled(false);
            }

        });

        btnTerminerTour.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sauvType = TypesMessages.TOUR_SUIVANT;
                Message m = new Message(TypesMessages.TOUR_SUIVANT, a);
                notifierObservateur(m);
            }

        });

        btnValider.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(sauvType, a);

                m.setTuile((String) listeChoix.getSelectedItem());

                if (a.getRole() == "Pilote" && !(btnAutreAction.isEnabled())
                        || !(btnBouger.isEnabled())) {

                    panelCentre.remove(labelPos);
                    labelPos = new JLabel((String) listeChoix.getSelectedItem(), SwingConstants.CENTER);
                    panelCentre.add(labelPos);

                }
                panelCentre.remove(panelChoixetVal);
                panelCentre.add(labelvide);
                panelCentre.updateUI();

                btnAssecher.setEnabled(true);
                btnBouger.setEnabled(true);
                btnAutreAction.setEnabled(btnAutreActionActive());

                notifierObservateur(m);

            }
        });

        this.window.setVisible(true);
    }

    public JButton getBtnAutreAction() {
        return btnAutreAction;
    }

    public JButton getBtnBouger() {
        return btnBouger;
    }

    public boolean btnAutreActionActive() {
        if (a.getRole() != "Plongeur" && a.getRole() != "Explorateur") {
            if (a.getRole() == "Pilote") {
                Pilote pilote = (Pilote) a;
                return (!(pilote.capaciteUtilisee()));
            } else if (a.getRole() == "Ingénieur") {
                Ingénieur ingenieur = (Ingénieur) a;
                return ingenieur.getCapaciteUtilisee() >= 0;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    public void assechementIngenieur() {
        btnAutreAction.setEnabled(false);
        btnBouger.setEnabled(false);
    }

    //{Grille de boolean pour tuiles de déplacement possible + grille des tuiles} => {affiche les déplacements possible}
    public void afficherTuilePossible(boolean[][] gBool, Grille gTuile) {

        int n = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (gBool[i][j]) {
                    Tuile[][] tuiles = gTuile.getTuiles();
                    choixPoss[n] = (tuiles[i][j].getNom());
                    n = n + 1;
                }
            }
        }

        panelCentre.remove(labelvide);
        panelChoixetVal = new JPanel(new GridLayout(1, 2));

        if (n == 0) {
            panelChoixetVal.add(new JLabel("Action ", SwingConstants.RIGHT));
            panelChoixetVal.add(new JLabel("impossible", SwingConstants.LEFT));
        } else {
            listeChoix = new JComboBox();

            for (int i = 0; i < n; i++) {
                if (choixPoss[i] != null) {
                    listeChoix.addItem(choixPoss[i]);
                }
            }
            //Affichage de la liste de choix et du bouton valider
            panelChoixetVal.add(listeChoix);
            panelChoixetVal.add(btnValider);
        }

        panelCentre.add(panelChoixetVal);
        window.setVisible(true);

    }

    public void afficher() {
        window.setVisible(true);
        
    }

    public void desafficher() {
        window.setVisible(false);
    }

    public void finirTour() {
        btnBouger.setEnabled(false);
        btnAssecher.setEnabled(false);
        btnAutreAction.setEnabled(false);
        
    }
}
