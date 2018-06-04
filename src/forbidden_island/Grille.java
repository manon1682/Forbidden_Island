package forbidden_island;

import java.util.*;

public class Grille {

	private Tuile[][] tuiles = new Tuile[6][6];

        public Grille(Tuile[][] tuile){
            setTuiles(tuile);
        }
        
	public Tuile[][] getTuiles() {
		return tuiles;
	}

        public void setTuiles(Tuile[][] tuiles) {
            this.tuiles = tuiles;
        }
        

}