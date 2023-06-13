package PandemicJavaFX.Proxy;

public class CartePOJO {
    private String typeCarte;
    private String nomVilleOuEvenement;
    private String maladieOuDescr;

    public CartePOJO() {
    }

    public String getTypeCarte() {
        return typeCarte;
    }

    public void setTypeCarte(String typeCarte) {
        this.typeCarte = typeCarte;
    }

    public String getNomVilleOuEvenement() {
        return nomVilleOuEvenement;
    }

    public void setNomVilleOuEvenement(String nomVilleOuEvenement) {
        this.nomVilleOuEvenement = nomVilleOuEvenement;
    }

    public String getMaladieOuDescr() {
        return maladieOuDescr;
    }

    public void setMaladieOuDescr(String maladieOuDescr) {
        this.maladieOuDescr = maladieOuDescr;
    }

    @Override
    public String toString() {
        return "CartePOJO{" +
                "typeCarte='" + typeCarte + '\'' +
                ", nomVilleOuEvenement='" + nomVilleOuEvenement + '\'' +
                ", maladieOuDescr='" + maladieOuDescr + '\'' +
                '}';
    }
}
