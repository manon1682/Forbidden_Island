package Enumeration;

public enum Tresor {
    PIERRE_SACRE("Pierre Sacré"),
    STATUE_DU_ZEPHIR("Statue du Zéphir"),
    CRISTAL_ARDENT("Cristal Ardent"),
    CALICE_DE_ORDRE("Calice de Ordre");        
        
    private String label;
    
    private Tresor(String label) {
        this.label = label;
    }     
    
    @Override
    public String toString() {
        return this.label;
    }
}