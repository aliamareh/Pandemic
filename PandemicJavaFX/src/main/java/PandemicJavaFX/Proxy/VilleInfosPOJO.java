package PandemicJavaFX.Proxy;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VilleInfosPOJO
{
    private String nom;
    private int population;
    private String maladieParDefaut;
    private String centreRecherche;
    private Map<String,Integer> cubesMaladie;
    private List<String> villesAlentours;
    private List<String> joueursDansLaVille;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getMaladieParDefaut() {
        return maladieParDefaut;
    }

    public void setMaladieParDefaut(String maladieParDefaut) {
        this.maladieParDefaut = maladieParDefaut;
    }

    public String getCentreRecherche() {
        return centreRecherche;
    }

    public void setCentreRecherche(String centreRecherche) {
        this.centreRecherche = centreRecherche;
    }

    public Map<String, Integer> getCubesMaladie() {
        return cubesMaladie;
    }

    public void setCubesMaladie(Map<String, Integer> cubesMaladie) {
        this.cubesMaladie = cubesMaladie;
    }

    public List<String> getVillesAlentours() {
        return villesAlentours;
    }

    public void setVillesAlentours(List<String> villesAlentours) {
        this.villesAlentours = villesAlentours;
    }

    public List<String> getJoueursDansLaVille() {
        return joueursDansLaVille;
    }

    public void setJoueursDansLaVille(List<String> joueursDansLaVille) {
        this.joueursDansLaVille = joueursDansLaVille;
    }

    @Override
    public String toString() {
        return "VilleInfosPOJO{" +
                "nom='" + nom + '\'' +
                ", population=" + population +
                ", maladieParDefaut='" + maladieParDefaut + '\'' +
                ", centreRecherche='" + centreRecherche + '\'' +
                ", cubesMaladie=" + cubesMaladie +
                ", villesAlentours=" + villesAlentours +
                ", joueursDansLaVille=" + joueursDansLaVille +
                '}';
    }
}
