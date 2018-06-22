package forbidden_island;

import Enumeration.EtatTuile;
import Enumeration.Tresor;

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
        this.setEtat(EtatTuile.SECHE);
        this.setLigne(ligne);
        this.setColonne(colonne);
    }

    public boolean verifTuileD() {
        return getEtat() != EtatTuile.COULEE;
    }

    public boolean verifTuileDPlongeur() {
        return getEtat() != EtatTuile.SECHE;
    }

    //Vérifie que la tuile n'est pas sèche et pas coulée pour l'asséchement
    public boolean verifTuileA() {
        return getEtat() == EtatTuile.INONDEE;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public Tresor getTresor() {
        return tresor;
    }

    public void asseche() {
        setEtat(EtatTuile.SECHE);
    }

    public void setEtat(EtatTuile etat) {
        this.etat = etat;
    }

    public EtatTuile getEtat() {
        return this.etat;
    }

    public String getNom() {
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

}
