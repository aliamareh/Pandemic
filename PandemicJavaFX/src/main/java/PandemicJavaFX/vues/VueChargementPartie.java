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
import javafx.scene.layout.VBox;
import org.json.JSONException;

import java.io.IOException;

public class VueChargementPartie extends Vue implements VueInteractive, EcouteurOrdre {

    public Label idDeLaPartie;
    public Label listeJoueursConn;
    private Controleur controleur;
    @FXML
    Label erreur;
    @FXML
    VBox mainVbox;


    public Parent getTop() {
        return mainVbox;
    }

    public static VueChargementPartie creerVue(GestionnaireVue gestionnaireVue) {
        FXMLLoader fxmlLoader = new FXMLLoader(VueAccueil.class.getResource("chargementPartie.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Probleme de construction de vue : "+e);
        }
        VueChargementPartie vue = fxmlLoader.getController();
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
        g.abonnement(this,TypeOrdre.IDPARTIE_CREE_REJOINT,TypeOrdre.IDPARTIE_REJOINT,TypeOrdre.LISTEJOUEURS);
    }

    @Override
    public void traiter(TypeOrdre e) {
        switch (e) {
            case IDPARTIE_CREE_REJOINT:
                valeurIdPartie();
                break;
            case IDPARTIE_REJOINT:
                valeurIdPartie2();
                break;
            case LISTEJOUEURS:
                chargerListeJoueurs();
                break;
        }
    }


    private void chargerListeJoueurs() {
         listeJoueursConn.setText(
                 "Infos Joueurs Partie: "+controleur.chargerListeJoueursRejoint());
    }

    public void valeurIdPartie() {
        idDeLaPartie.setText("Id de la partie = "+controleur.getIdPartieCrée());
    }

    public void valeurIdPartie2() {
        idDeLaPartie.setText("Id de la partie = "+controleur.getIdPartieRejoint());
    }

    public void goToRafraichirListeJoueur(ActionEvent actionEvent) {
        erreur.setText("");
        controleur.goToChargementPartie();
    }

    public void goToQuitterPartie(ActionEvent actionEvent) {
        erreur.setText("");
        controleur.quitterPartie();
        controleur.goToCreerOuRejoindrePartie();
    }

    public void goToDemarrerPartie(ActionEvent actionEvent) throws JSONException {
        if(controleur.verifSiPartieDemarré()) {
            try {
                controleur.recupererInfosPartie();
                controleur.recupererInfosJoueur();
                controleur.goToInfosPartie();
            } catch (RuntimeException e) {
                erreur.setText(e.getMessage().substring(15)); // Les joueurs sont manquants
                throw e;
            }
        }
        else{
            try {
                controleur.demarrerPartie();
                controleur.recupererInfosPartie();
                controleur.recupererInfosJoueur();
                controleur.goToInfosPartie();
            } catch (RuntimeException e) {
                erreur.setText(e.getMessage().substring(15)); // Les joueurs sont manquants
                throw e;
            }
        }
    }

}
