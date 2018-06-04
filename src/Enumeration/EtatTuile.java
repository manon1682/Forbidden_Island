package Enumeration;

public enum EtatTuile {
	sèche,
	coulée,
	inondée;
        
        

    public String toString() {
        return name().toLowerCase();
    }
}