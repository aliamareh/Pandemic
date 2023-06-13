package PandemicJavaFX.vues;

import PandemicJavaFX.controleur.Controleur;
import PandemicJavaFX.controleur.ordres.EcouteurOrdre;
import PandemicJavaFX.controleur.ordres.LanceurOrdre;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;

public abstract class GestionnaireVue implements EcouteurOrdre,VueInteractive{

    private Stage stage;
    private Collection<VueInteractive> vuesInteractives;
    private Collection<EcouteurOrdre> ecouteurOrdres;
    private Controleur controleur;

    public GestionnaireVue (Stage stage) {
        this.stage=stage;
        vuesInteractives= new ArrayList<>();
        ecouteurOrdres= new ArrayList<>();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void ajouterEcouterOrdre(EcouteurOrdre ecouteurOrdre){
        ecouteurOrdres.add(ecouteurOrdre);
    }

    public void setAbonnement(LanceurOrdre g) {
        for(EcouteurOrdre e:this.ecouteurOrdres)
            e.setAbonnement(g);}

    public void ajouterVueInteractive(VueInteractive vueInteractive) {
        this.vuesInteractives.add(vueInteractive);
    }

    public void setControleur(Controleur c) {
        this.controleur=c;
        this.vuesInteractives.stream()
                .forEach(v -> {v.setControleur(c);});
    }


    public Controleur getControleur() {
        return this.controleur;
    }
}
