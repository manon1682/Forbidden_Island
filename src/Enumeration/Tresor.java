package Enumeration;

public enum Tresor {
	Pierre_Sacré,
	Statue_du_Zéphir,
	Cristal_Ardent,
	Calice_de_Ordre;
        
        
    private String label;
    
    private Tresor(String label) {
        this.label = label;
    }
        
        public String toString() {
        return this.label;
    }

        
}