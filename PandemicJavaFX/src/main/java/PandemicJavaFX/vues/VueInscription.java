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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.json.JSONException;

import java.io.IOException;

public class VueInscription extends Vue implements VueInteractive, EcouteurOrdre {
    private Controleur controleur;

    @FXML
    VBox mainVbox;

    @FXML
    TextField pseudo;
    @FXML
    Label erreur;
    @FXML
    TextField password;

    public Parent getTop() {
        return mainVbox;
    }

    public static VueInscription creerVue(GestionnaireVue gestionnaireVue) {
        FXMLLoader fxmlLoader = new FXMLLoader(VueInscription.class.getResource("inscription.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Probleme de construction de vue : "+e);
        }
        VueInscription vue = fxmlLoader.getController();
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


    public void goToAcceuil(ActionEvent actionEvent) {
        controleur.goToAcceuil();
    }

    public void goToMenuActions(ActionEvent actionEvent) throws JSONException,Exception {
        inscription(pseudo.getText(),password.getText());
        controleur.goToCreerOuRejoindrePartie();
    }

    public void inscription(String pseudo, String password ) throws Exception, JSONException{
        if ( pseudo.isBlank() || password.isBlank() || pseudo == null || password == null) {
            erreur.setText("Login ou mot de passe mal écrit, veuillez vérifier qu'ils ne sont pas vides ou nuls");
            throw new Exception("Login ou mot de passe mal écrit, veuillez vérifier qu'ils ne sont pas vides ou nuls");
        }
        else{
            erreur.setText("");
            try {
                controleur.inscriptionControleur(pseudo, password);
            } catch (RuntimeException e) {
                erreur.setText(e.getMessage().substring(15)); // Utilisateur déjà inscrit
                throw e;
            }
        }
    }
}
