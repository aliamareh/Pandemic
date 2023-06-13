package PandemicJavaFX.vues;

import PandemicJavaFX.controleur.ordres.LanceurOrdre;
import PandemicJavaFX.controleur.ordres.TypeOrdre;
import javafx.stage.Stage;

public class GestionnaireVueImpl extends GestionnaireVue{

    private VueAccueil vueAccueil;
    private VueInscription vueInscription;

    private VueConnexion vueConnexion;

    private VueMenuActions vueMenuActions;

    private VueCreerOuRejoindrePartie vueCreerOuRejoindrePartie;

    private VueInfosPartie vueInfosPartie;

    private VueChargementPartie vueChargementPartie;

    public GestionnaireVueImpl (Stage stage){
        super(stage);
        vueAccueil = VueAccueil.creerVue(this);
        vueInscription = VueInscription.creerVue(this);
        vueConnexion = VueConnexion.creerVue(this);
        vueMenuActions = VueMenuActions.creerVue(this);
        vueCreerOuRejoindrePartie=VueCreerOuRejoindrePartie.creerVue(this);
        vueInfosPartie=VueInfosPartie.creerVue(this);
        vueChargementPartie=VueChargementPartie.creerVue(this);

    }

    public void setAbonnement(LanceurOrdre g) {
        g.abonnement(this, TypeOrdre.SHOW_ACCEUIL,TypeOrdre.SHOW_INSCRIPTION,TypeOrdre.SHOW_CONNEXION, TypeOrdre.SHOW_MENU_ACTIONS,TypeOrdre.SHOW_MENU_CREEROUREJOINDREPARTIE,TypeOrdre.SHOW_CHARGEMENTPARTIE,TypeOrdre.SHOW_INFOSPARTIE);
        super.setAbonnement(g);
    }

    @Override
    public void traiter(TypeOrdre e) {
        switch (e) {
            case SHOW_ACCEUIL:
                this.getStage().setScene(vueAccueil.getScene());
                break;
            case SHOW_INSCRIPTION:
                this.getStage().setScene(vueInscription.getScene());
                break;
            case SHOW_CONNEXION:
                this.getStage().setScene(vueConnexion.getScene());
                break;
            case SHOW_MENU_ACTIONS:
                this.getStage().setScene(vueMenuActions.getScene());
                break;
            case SHOW_MENU_CREEROUREJOINDREPARTIE:
                this.getStage().setScene(vueCreerOuRejoindrePartie.getScene());
                break;
            case SHOW_INFOSPARTIE:
                this.getStage().setScene(vueInfosPartie.getScene());
                break;
            case SHOW_CHARGEMENTPARTIE:
                this.getStage().setScene(vueChargementPartie.getScene());
                break;
        }

        this.getStage().show();

    }


}
