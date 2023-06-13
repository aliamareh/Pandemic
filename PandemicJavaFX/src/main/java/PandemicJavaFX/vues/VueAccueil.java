package PandemicJavaFX.vues;

import PandemicJavaFX.controleur.Controleur;
import PandemicJavaFX.controleur.ordres.EcouteurOrdre;
import PandemicJavaFX.controleur.ordres.LanceurOrdre;
import PandemicJavaFX.controleur.ordres.TypeOrdre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.io.IOException;


public class VueAccueil extends Vue implements VueInteractive, EcouteurOrdre {

    private Controleur controleur;

    @FXML
    VBox mainVbox;

    public Parent getTop() {
        return mainVbox;
    }

    public static VueAccueil creerVue(GestionnaireVue gestionnaireVue) {
        FXMLLoader fxmlLoader = new FXMLLoader(VueAccueil.class.getResource("accueil.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Probleme de construction de vue : "+e);
        }
        VueAccueil vue = fxmlLoader.getController();
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
        return this.controleur;    }





    @Override
    public void setAbonnement(LanceurOrdre g) {
    }

    @Override
    public void traiter(TypeOrdre e) {

    }


    public void goToInscription(ActionEvent actionEvent) {
        controleur.goToInscription();
    }

    public void goToConnection(ActionEvent actionEvent) {
        controleur.goToConnection();
    }
}
