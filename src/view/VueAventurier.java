package view;

import forbidden_island.Grille;
import forbidden_island.Message;
import forbidden_island.Observe;
import forbidden_island.Tuile;
import forbidden_island.TypesMessages;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.MatteBorder;
import util.Utils.Pion;

public class VueAventurier extends Observe{

    private final JPanel panelBoutons;
    private final JPanel panelCentre;
    private final JFrame window;
    private final JPanel panelAventurier;
    private final JPanel mainPanel;
    private final JButton btnBouger;
    private final JButton btnAssecher;
    private final JButton btnAutreAction;
    private final JButton btnTerminerTour;
    private JTextField position;
    
    //comboBox des choix
    private JComboBox listeChoix;
    //Array List qui stock les possibilités de choix
    private ArrayList<String> choixPoss = new ArrayList<>();
   

    public VueAventurier(String nomJoueur, String nomAventurier, Color couleur) {

        this.window = new JFrame();
        window.setSize(350, 200);
        //le titre = nom du joueur 
        window.setTitle(nomJoueur);
        mainPanel = new JPanel(new BorderLayout());
        this.window.add(mainPanel);

        mainPanel.setBackground(new Color(230, 230, 230));
        mainPanel.setBorder(BorderFactory.createLineBorder(couleur, 2));

        // =================================================================================
        // NORD : le titre = nom de l'aventurier sur la couleurActive du pion
        this.panelAventurier = new JPanel();
        panelAventurier.setBackground(couleur);
        panelAventurier.add(new JLabel(nomAventurier, SwingConstants.CENTER));
        mainPanel.add(panelAventurier, BorderLayout.NORTH);

        // =================================================================================
        // CENTRE : 1 ligne pour position courante
        this.panelCentre = new JPanel(new GridLayout(2, 1));
        this.panelCentre.setOpaque(false);
        this.panelCentre.setBorder(new MatteBorder(0, 0, 2, 0, couleur));
        mainPanel.add(this.panelCentre, BorderLayout.CENTER);

        panelCentre.add(new JLabel("Position", SwingConstants.CENTER));
        position = new JTextField(30);
        position.setHorizontalAlignment(CENTER);
        panelCentre.add(position);

        // =================================================================================
        // SUD : les boutons
        this.panelBoutons = new JPanel(new GridLayout(2, 2));
        this.panelBoutons.setOpaque(false);
        mainPanel.add(this.panelBoutons, BorderLayout.SOUTH);

        this.btnBouger = new JButton("Bouger");
        this.btnAssecher = new JButton("Assecher");
        this.btnAutreAction = new JButton("AutreAction");
        this.btnTerminerTour = new JButton("Terminer Tour");

        this.panelBoutons.add(btnBouger);

        this.panelBoutons.add(btnAssecher);
        this.panelBoutons.add(btnAutreAction);
        this.panelBoutons.add(btnTerminerTour);

        //Action performed
        btnBouger.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.type = TypesMessages.DEPLACER;
                notifierObservateur(m);
            }

            });
        
        btnAssecher.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            });
        
        btnAutreAction.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            });
        
        btnTerminerTour.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            });

        this.window.setVisible(true);
    }

    public void setPosition(String pos) {
        this.position.setText(pos);
    }

    public JButton getBtnAutreAction() {
        return btnAutreAction;
    }

    public String getPosition() {
        return position.getText();
    }

    public JButton getBtnBouger() {
        return btnBouger;
    }

    public JButton getBtnAssecher() {
        return btnAssecher;
    }

    public JButton getBtnTerminerTour() {
        return btnTerminerTour;
    }
    
    //{Grille de boolean pour tuiles de déplacement possible + grille des tuiles} => {affiche les déplacements possible}
    public void afficherTuilePossible(Boolean[][] gBool, Grille gTuile){
        listeChoix = new JComboBox;
        for(int i = 0; i < gBool.length; i++ ){
            for(int j = 0; j < gBool[i].length; i++){
                if(gBool[i][j]){
                    
                   
                    Tuile[][] tuiles = gTuile.getTuiles();
                    tuiles[i][j].getNom();
                    
                }
            }
        }
    }

    public static void main(String[] args) {
        // Instanciation de la fenêtre 
        VueAventurier vueAventurier = new VueAventurier("Manon", "Explorateur", Pion.ROUGE.getCouleur());

    }
}
