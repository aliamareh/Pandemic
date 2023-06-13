package PandemicJavaFX.vues;

import PandemicJavaFX.controleur.Controleur;
import PandemicJavaFX.controleur.ordres.EcouteurOrdre;
import PandemicJavaFX.controleur.ordres.LanceurOrdre;
import PandemicJavaFX.controleur.ordres.TypeOrdre;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class VueDecouvrirUnRemede extends Vue implements VueInteractive, EcouteurOrdre {
    private Controleur controleur;

    @FXML
    VBox mainVbox;



    public Parent getTop() {
        return mainVbox;
    }


    public static VueDecouvrirUnRemede creerVue(GestionnaireVueImpl gestionnaireVue)  {
        FXMLLoader fxmlLoader = new FXMLLoader(VueDecouvrirUnRemede.class.getResource("vues/decouvrirunremede.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Probleme de construction de vue Formulaire");
        }

        VueDecouvrirUnRemede vue = fxmlLoader.getController();
        gestionnaireVue.ajouterVueInteractive(vue);
        gestionnaireVue.ajouterEcouterOrdre(vue);
        vue.setScene(new Scene(vue.getTop()));
        return vue;
    }

    @Override
    public void setControleur(Controleur controleur) {

        this.controleur = controleur;

    }



    @Override
    public Controleur getControleur() {
        return this.controleur;
    }


    @Override
    public void setAbonnement(LanceurOrdre g) {

    }

    @Override
    public void traiter(TypeOrdre e) {

    }
}
