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

public class VuePartagerConnaissances extends Vue implements VueInteractive, EcouteurOrdre {
    private Controleur controleur;

    @FXML
    VBox mainVbox;



    public Parent getTop() {
        return mainVbox;
    }


    public static VuePartagerConnaissances creerVue(GestionnaireVueImpl gestionnaireVue)  {
        FXMLLoader fxmlLoader = new FXMLLoader(VuePartagerConnaissances.class.getResource("vues/partagerConnaissances.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Probleme de construction de vue Formulaire");
        }

        VuePartagerConnaissances vue = fxmlLoader.getController();
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
