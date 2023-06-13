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

import java.io.IOException;

public class VueCreerOuRejoindrePartie extends Vue implements VueInteractive, EcouteurOrdre {

    private Controleur controleur;



    @FXML
    VBox mainVbox;

    @FXML
    TextField idPartieARejoindre;
    @FXML
    Label erreurCreerPartie;
    @FXML
    Label erreurRejoindrePartie;
    @FXML
    TextField nombreDeJoueursDeLaPartie;

    public Parent getTop() {
        return mainVbox;
    }

    public static VueCreerOuRejoindrePartie creerVue(GestionnaireVue gestionnaireVue) {
        FXMLLoader fxmlLoader = new FXMLLoader(VueCreerOuRejoindrePartie.class.getResource("creerourejoindrepartie.fxml"));
        try {
            fxmlLoader.load(); // le problème semble venir d'ici
        } catch (IOException e) {
            System.out.println("Probleme de construction de vue : "+e);
        }
        VueCreerOuRejoindrePartie vue = fxmlLoader.getController();
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





    // ils sont abonnées à des méthodes spécifique à cette classe qu'ils traitent ensuite.
    @Override
    public void setAbonnement(LanceurOrdre g) {
    }

    @Override
    public void traiter(TypeOrdre e) {

    }


    public void rejoindrePartie(ActionEvent actionEvent) throws Exception {
        String idPartieARejoindreText = idPartieARejoindre.getText();
        if (idPartieARejoindreText.isEmpty()) {
            erreurRejoindrePartie.setText("Renseignez un numero de partie");
            throw new Exception("Aucune Partie renseignée");
        } else if (!idPartieARejoindreText.matches("\\d+")) {
            erreurRejoindrePartie.setText("Renseignez un chiffre");
            throw new Exception("Aucun chiffre n'a été trouvé");
        } else {
            int idPartie=Integer.parseInt(idPartieARejoindre.getText());
            erreurRejoindrePartie.setText("");
            try {
                controleur.rejoindrePartie(idPartie);
                controleur.goToChargementPartie();
            } catch (RuntimeException e) {
                erreurRejoindrePartie.setText(" Partie inexistante");
                throw e;
            }
        }
    }

    public void creerPartie(ActionEvent actionEvent)throws Exception {
        String nbJoueursPartieText = nombreDeJoueursDeLaPartie.getText();
        if (nbJoueursPartieText.isEmpty()) {
            erreurCreerPartie.setText("Aucune information n'a été transmise");
            throw new Exception("Aucune information n'a été transmise");
        } else if (!nbJoueursPartieText.matches("\\d")) {
            erreurCreerPartie.setText("Renseignez un chiffre");
            throw new Exception("Aucun nombre de joueurs raisonnable renseigné");
        } else {
            int nbJoueurs=Integer.parseInt(nbJoueursPartieText);
            if (nbJoueurs < 2 || nbJoueurs > 4) {
                erreurCreerPartie.setText("Le nombre de joueurs doit être compris entre 2 et 4");
                throw new Exception("Le nombre de joueurs est incorrect");
            } else {
                erreurCreerPartie.setText("");
                controleur.creerPartie(nbJoueurs);
                long idPartie= controleur.getIdPartieCrée();
                controleur.rejoindrePartie(idPartie);
                controleur.goToChargementPartie();
            }
        }
    }


    public void goToDeconnexion(ActionEvent actionEvent) {
        controleur.deconnexionControleur();
        controleur.goToAcceuil();
    }
}
