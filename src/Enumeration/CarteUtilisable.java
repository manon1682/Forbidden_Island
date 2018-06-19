package Enumeration;

public enum CarteUtilisable {
    HELICO("Helico"),
    SAC_SABLE("Sac De Sable"),
    MONTEE_EAU("Montee Des Eaux"),
    PIERRE_SACRE("Pierre Sacré"),
    STATUE_DU_ZEPHIR("Statue du Zéphir"),
    CRISTAL_ARDENT("Cristal Ardent"),
    CALICE_DE_ORDRE("Calice de Ordre");

    private String label;

    public CarteUtilisable getNext() {
        return this.ordinal() < CarteUtilisable.values().length - 1
                ? CarteUtilisable.values()[this.ordinal() + 1]
                : null;
    }

    private CarteUtilisable(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }

}
