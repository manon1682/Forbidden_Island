package forbidden_island;

import Enumeration.CarteUtilisable;
import Cartes.Deck_Innondation;
import Cartes.Deck_Tresor;
import Aventurier.Aventurier;
import Cartes.Deck;
import java.util.*;

public class Controleur {

	private Grille grille;
	private Collection<Aventurier> joueurs;
	Deck_Tresor deck_T;
	Deck_Innondation deck_I;
	private int jaugeInnondation;

        
        public ArrayList<String> chargerNomString(){
            ArrayList<String> nomTuile = new ArrayList<String>();
            
            
            return nomTuile;
        }
        
        
        
        public Grille initPlateau(){
            Tuile[][] tuiles = new Tuile[6][6];
            ArrayList<String> nomTuile = chargerNomString();
            for (int l = 0; l < 6; l++) {
                for (int c = 0; c < 6; c++) {
                    
                    
                    
                    
                }
            }
            
            
            
            
            grille = new Grille(tuiles);
            
            
            return grille;
        }
        
        
	/**
	 * 
	 * @param a
	 */
	public void jouerTour(Aventurier a) {
		// TODO - implement Controleur.jouerTour
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nomCarte
	 */
	public void saisir(CarteUtilisable nomCarte) {
		// TODO - implement Controleur.saisir
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nomCarte
	 */
	public void addDefausseT(CarteUtilisable nomCarte) {
		// TODO - implement Controleur.addDefausseT
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param a
	 */
	public void actionPossible(Aventurier a) {
		// TODO - implement Controleur.actionPossible
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param choixAction
	 */
	public void saisirAction(String choixAction) {
		// TODO - implement Controleur.saisirAction
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param tuile
	 */
	public void choisir(Tuile tuile) {
		// TODO - implement Controleur.choisir
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param tuile
	 */
	public void asseche(Tuile tuile) {
		// TODO - implement Controleur.asseche
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param a
	 */
	public void getTresor(Aventurier a) {
		// TODO - implement Controleur.getTresor
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param jr
	 */
	public void choixJoueurPossible(Aventurier jr) {
		// TODO - implement Controleur.choixJoueurPossible
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param a
	 */
	public void getEchangePossible(Aventurier a) {
		// TODO - implement Controleur.getEchangePossible
		throw new UnsupportedOperationException();
	}

	public Deck getDeck_T() {
		// TODO - implement Controleur.getDeck_T
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param grille
	 */
	public void assecherSpecial(Grille grille) {
		// TODO - implement Controleur.assecherSpecial
		throw new UnsupportedOperationException();
	}

}