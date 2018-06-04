package forbidden_island;

import Enumeration.EtatTuile;
import static Enumeration.EtatTuile.coulée;
import static Enumeration.EtatTuile.sèche;
import Enumeration.Tresor;
import Enumeration.NomTuile;

public class Tuile {

	private NomTuile nom;
	private EtatTuile etat;
	private Tresor tresor;
	private int ligne;
	private int colonne;

	public int getLigne() {
		return ligne;
	}

	public int getColonne() {
                return colonne;
    	}
        
        //Vérifie que la tuile n'est pas null, pas sèche et pas coulée
	public boolean verifTuileA() {
            return getEtat() != sèche && getEtat() != coulée;
                    
	}

	public void asseche() {
		setEtat(sèche);
	}

	/**
	 * 
	 * @param etat
	 */
	public void setEtat(EtatTuile etat) {
		this.etat = etat;
	}

	public EtatTuile getEtat() {
		return this.etat;
	}

}