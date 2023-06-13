package PandemicJavaFX.Proxy;


public class InfosJoueurPOJO {
    String nomRole;
    String descRole;
    String emplacement;
    int nbActions;

    public String getNomRole() {
        return nomRole;
    }

    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }

    public String getDescRole() {
        return descRole;
    }

    public void setDescRole(String descRole) {
        this.descRole = descRole;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public int getNbActions() {
        return nbActions;
    }

    public void setNbActions(int nbActions) {
        this.nbActions = nbActions;
    }

    @Override
    public String toString() {
        return "InfosJoueurPOJO{" +
                "nomRole='" + nomRole + '\'' +
                ", descRole='" + descRole + '\'' +
                ", emplacement='" + emplacement + '\'' +
                ", nbActions=" + nbActions +
                '}';
    }
}
