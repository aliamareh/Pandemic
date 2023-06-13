package PandemicJavaFX.vues;

import PandemicJavaFX.controleur.Controleur;
import PandemicJavaFX.controleur.ordres.EcouteurOrdre;
import PandemicJavaFX.controleur.ordres.LanceurOrdre;
import PandemicJavaFX.controleur.ordres.TypeOrdre;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.json.JSONException;

import java.io.IOException;

public class VueMenuActions extends Vue implements VueInteractive, EcouteurOrdre {

    public Label labelMenuActions;
    public Label infosJoueur;
    private Controleur controleur;

    @FXML
    VBox mainVbox;

    public Parent getTop() {
        return mainVbox;
    }

    public static VueMenuActions creerVue(GestionnaireVue gestionnaireVue) {
        FXMLLoader fxmlLoader = new FXMLLoader(VueMenuActions.class.getResource("menuActions.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Probleme de construction de vue : "+e);
        }
        VueMenuActions vue = fxmlLoader.getController();
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
    public void setAbonnement(LanceurOrdre g) { g.abonnement(this,
            TypeOrdre.IDPARTIE_CREE_REJOINT,TypeOrdre.IDPARTIE_REJOINT, TypeOrdre.NOMBRESACTIONS,TypeOrdre.NOMEMPLACEMENT,
            TypeOrdre.NOMROLE);}

    @Override
    public void traiter(TypeOrdre e) throws JSONException {
        switch (e) {
            case IDPARTIE_CREE_REJOINT:
                valeurIdPartie();
                break;
            case IDPARTIE_REJOINT:
                valeurIdPartie2();
                break;
            case NOMROLE:
                valeurInfosJoueur();
                break;
            case NOMBRESACTIONS:
                valeurInfosJoueur();
                break;
            case NOMEMPLACEMENT:
                valeurInfosJoueur();
                break;
        }

    }

    private void valeurIdPartie() {
        labelMenuActions.setText("Menu pour la partie numéro "+controleur.getIdPartieCrée());
    }
    private void valeurIdPartie2() {
        labelMenuActions.setText("Menu pour la partie numéro "+controleur.getIdPartieRejoint());
    }


    private void valeurInfosJoueur() throws JSONException {
        infosJoueur.setText("Infos du joueur: Actions restants="+controleur.getNbActions()+" Role="+controleur.getRole()+
                " Emplacement="+controleur.getNomEmplacement());
    }

    private void valeurMenuLabel() {
        labelMenuActions.setText("Menu pour la partie numéro "+controleur.getIdPartieCrée());
    }


    public void goToDeconnexion(ActionEvent actionEvent) {
        controleur.deconnexionControleur();
        controleur.goToAcceuil();
    }


    public void goToInfosPartie(ActionEvent actionEvent) {
        controleur.goToInfosPartie();
    }

    public void goToCreerOuRejoindrePartie(ActionEvent actionEvent) {
        controleur.goToCreerOuRejoindrePartie();
    }
}
