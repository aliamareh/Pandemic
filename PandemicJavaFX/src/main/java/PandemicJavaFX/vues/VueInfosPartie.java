package PandemicJavaFX.vues;

import PandemicJavaFX.Proxy.CartePOJO;
import PandemicJavaFX.Proxy.EtatPartiePOJO;
import PandemicJavaFX.controleur.Controleur;
import PandemicJavaFX.controleur.ordres.EcouteurOrdre;
import PandemicJavaFX.controleur.ordres.LanceurOrdre;
import PandemicJavaFX.controleur.ordres.TypeOrdre;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class VueInfosPartie extends Vue implements VueInteractive, EcouteurOrdre {

    public Label idDeLaPartie;
    public Label niveauEclosion;
    public Label niveauPropagation;
    public Label idCubesRestant;
    public Label eradiqueMaladie;
    public Label gueriMaladie;
    public Label listeCentresDeRecherche;
    public ComboBox<CartePOJO> defausseJoueur;
    public ComboBox<CartePOJO> defaussePropagation;
    private Controleur controleur;

    private EtatPartiePOJO etatPartiePOJO;



    @FXML
    VBox mainVbox;

    public Parent getTop() {
        return mainVbox;
    }

    public static VueInfosPartie creerVue(GestionnaireVue gestionnaireVue) {
        FXMLLoader fxmlLoader = new FXMLLoader(VueInfosPartie.class.getResource("infosPartie.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Probleme de construction de vue : "+e);
        }
        VueInfosPartie vue = fxmlLoader.getController();
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
        g.abonnement(this, TypeOrdre.IDPARTIE_CREE_REJOINT,TypeOrdre.IDPARTIE_REJOINT,TypeOrdre.NIVEAUECLOSION,TypeOrdre.NIVEAUPROPAGATION,
                TypeOrdre.NOMBRESCUBESMALADIE, TypeOrdre.LISTEMALADIEGUERI, TypeOrdre.LISTEMALADIEERADIQUE,
                TypeOrdre.LISTECENTREDERECHERCHE, TypeOrdre.DEFAUSSEPROPAGATION, TypeOrdre.DEFAUSSEJOUEUR);
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
            case NIVEAUECLOSION:
                valeurNiveauEclosion();
                break;
            case NIVEAUPROPAGATION:
                valeurNiveauPropagation();
                break;
            case NOMBRESCUBESMALADIE:
                valeurNombresCubesMaladie();
                break;
            case LISTEMALADIEGUERI:
                valeurGuerirMaladie();
            case LISTEMALADIEERADIQUE:
                valeurEradiqueMaladie();
                break;
            case LISTECENTREDERECHERCHE:
                valeurListeCentreDeRecherche();
                break;
            case DEFAUSSEJOUEUR:
                valeurDefausseJoueur();
                break;
            case DEFAUSSEPROPAGATION:
                valeurDefaussePropagation();
                break;
        }
    }

    private void valeurDefausseJoueur() {
        
        List<CartePOJO> liste= etatPartiePOJO.getDefausseJoueurs();
        if (liste!=null){
            System.out.println("oui rentré dans if joueur");
            this.defausseJoueur.setItems(FXCollections.observableList(new ArrayList<>(liste)));
            this.defausseJoueur.setPromptText("Défausse Joueur: ");
        }
        else
            System.out.println("oui rentré dans else joueur");
            this.defausseJoueur.setPromptText("Défausse Joueur: Vide");

    }

    private void valeurDefaussePropagation() {

        List<CartePOJO> liste= etatPartiePOJO.getDefaussePropagation();
        if (liste==null){
            this.defaussePropagation.setPromptText("Défausse Propagation: Vide");
        }
        else
            this.defaussePropagation.setItems(FXCollections.observableList(new ArrayList<>(liste)));
            this.defaussePropagation.setPromptText("Défausse Propagation: ");}



    private void valeurListeCentreDeRecherche() {
        List<String> listecentre=etatPartiePOJO.getCentreRecherche();
        if (listecentre==null ){
            listeCentresDeRecherche.setText("Liste de centre de recherche sur 6 possibles: Aucune");
        }
        else
            listeCentresDeRecherche.setText("Liste de centre de recherche sur 6 possibles: "+
                    etatPartiePOJO.getCentreRecherche());
    }

    private void valeurGuerirMaladie() {
        List<String> listegueri=etatPartiePOJO.getGueri();
        if (listegueri==null ){
            gueriMaladie.setText("Liste des maladies guéri: Aucune");
        }
        else
            gueriMaladie.setText("Liste des maladies guéri: "+
                    etatPartiePOJO.getGueri());
    }

    private void valeurEradiqueMaladie() {
        List<String> listeradique=etatPartiePOJO.getEradique();
        if (listeradique==null ){
            eradiqueMaladie.setText("Liste des maladies eradiqué: Aucune");
        }
        else
            eradiqueMaladie.setText("Liste des maladies eradiqué: "+
                etatPartiePOJO.getEradique());
    }

    private void valeurNombresCubesMaladie() {
        String cubesRestantBleu=etatPartiePOJO.getCubesMaladie().get("Bleue");
        String cubesRestantNoir=etatPartiePOJO.getCubesMaladie().get("Noire");
        String cubesRestantJaune=etatPartiePOJO.getCubesMaladie().get("Jaune");
        String cubesRestantRouge=etatPartiePOJO.getCubesMaladie().get("Rouge");

        idCubesRestant.setText("Nombres de cubes maladies restant sur 24 chacun:     Bleu= "+ cubesRestantBleu+
                " Noir= "+ cubesRestantNoir+
                " Jaune= "+ cubesRestantJaune+
                " Rouge= "+ cubesRestantRouge);
    }

    public void valeurIdPartie() {
        setEtatPartiePOJO();
        idDeLaPartie.setText("Infos de la partie numéro: "+ controleur.getIdPartieCrée());
    }
    public void valeurIdPartie2() {
        setEtatPartiePOJO();
        idDeLaPartie.setText("Infos de la partie numéro: "+controleur.getIdPartieRejoint());
    }

    public void goToDeconnexion(ActionEvent actionEvent) {
        controleur.deconnexionControleur();
        controleur.goToAcceuil();
    }

    public void goToMenuActions(ActionEvent actionEvent) {
        controleur.goToMenuActions();
    }

    public void goToQuitterPartie(ActionEvent actionEvent) {
        controleur.quitterPartie();
        controleur.goToCreerOuRejoindrePartie();
    }

    public void setEtatPartiePOJO() {
        this.etatPartiePOJO = controleur.getEtatPartie();
    }

    public void valeurNiveauPropagation() {
        niveauPropagation.setText("Niveau de propagation: "+ etatPartiePOJO.getNvPropagation());;
    }

    private void valeurNiveauEclosion() {
        niveauEclosion.setText("Niveau de l'éclosion: "+ etatPartiePOJO.getEclosion());
    }

}
