package forbidden_island;

import Enumeration.EtatTuile;
import static Enumeration.EtatTuile.coulée;
import static Enumeration.EtatTuile.inondée;
import static Enumeration.EtatTuile.sèche;
import Enumeration.Tresor;
import Enumeration.NomTuile;

public class Tuile {

    private String nom;
    private EtatTuile etat;
    private Tresor tresor;
    private int ligne;
    private int colonne;

    public Tuile() {
    }

    public Tuile(String nom, int ligne, int colonne) {
        this.setNom(nom);
        this.setEtat(etat.sèche);
        this.setLigne(ligne);
        this.setColonne(colonne);
    }

    public boolean verifTuileD() {
        return getEtat() != inondée;
    }

    public boolean verifTuileDPlongeur() {
        return getEtat() == coulée || getEtat() == inondée;
    }

    //Vérifie que la tuile n'est pas sèche et pas coulée
    public boolean verifTuileA() {
        return getEtat() != sèche && getEtat() != coulée;

    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public void asseche() {
        setEtat(sèche);
    }

    public void setEtat(EtatTuile etat) {
        this.etat = etat;
    }

    public EtatTuile getEtat() {
        return this.etat;
    }
    
    public String getNom(){
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTresor(Tresor tresor) {
        this.tresor = tresor;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public Tresor getTresor() {
        return tresor;
    }


}
