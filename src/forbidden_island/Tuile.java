package forbidden_island;

import Enumeration.EtatTuile;
import Enumeration.Tresor;
import Enumeration.NomTuile;

public class Tuile {

	private NomTuile nom;
	private EtatTuile etat;
	private Tresor tresor;
	private int ligne;
	private int colonne;

	public void getLigne() {
		return ligne;
	}

	public void getColonne() {
		// TODO - implement Tuile.getColonne
                
    	}

	public boolean verifTuileA() {
		// TODO - implement Tuile.verifTuileA
		throw new UnsupportedOperationException();
	}

	public void asseche() {
		// TODO - implement Tuile.asseche
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param etat
	 */
	public void setEtat(EtatTuile etat) {
		this.etat = etat;
	}

	public void getEtat() {
		// TODO - implement Tuile.getEtat
		throw new UnsupportedOperationException();
	}

}