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

public class VueConnexion extends Vue implements VueInteractive, EcouteurOrdre {

    private Controleur controleur;

    @FXML
    VBox mainVbox;
    @FXML
    Label erreur;
    @FXML
    TextField pseudo;

    @FXML
    TextField password;

    public Parent getTop() {
        return mainVbox;
    }

    public static VueConnexion creerVue(GestionnaireVue gestionnaireVue) {
        FXMLLoader fxmlLoader = new FXMLLoader(VueConnexion.class.getResource("connexion.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Probleme de construction de vue : "+e);
        }
        VueConnexion vue = fxmlLoader.getController();
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

    public void test() {
        System.out.println(getClass().getResource("inscription.fxml"));

    }


    public void goToAcceuil(ActionEvent actionEvent) {
        controleur.goToAcceuil();
    }


    public void connexion(String pseudo, String password) throws Exception {

        if (pseudo.isBlank() || password.isBlank() || pseudo.isEmpty() || password.isEmpty() || pseudo.equals(null) || password.equals(null)) {
            erreur.setText("Login ou mot de passe mal écrit, veuillez vérifier qu'ils ne sont pas vides ou nuls");
            throw new Exception("Login ou mot de passe mal écrit, veuillez vérifier qu'ils ne sont pas vides ou nuls");
        } else {
            erreur.setText("");
            try {
                controleur.connexionControleur(pseudo, password);
            } catch (RuntimeException e) {
                erreur.setText(e.getMessage().substring(15)); // Login ou Mdp incorrect ou Utilisateur déjà connecté
                throw e;
            }
        }
    }

    public void goToCreerOuRejoindrePartie(ActionEvent actionEvent) throws Exception {
        connexion(pseudo.getText(),password.getText());
        controleur.goToCreerOuRejoindrePartie();
    }
}
