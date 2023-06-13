package PandemicJavaFX.controleur;

import PandemicJavaFX.Proxy.ProxyClient;
import PandemicJavaFX.Proxy.EtatPartiePOJO;
import PandemicJavaFX.controleur.ordres.EcouteurOrdre;
import PandemicJavaFX.controleur.ordres.LanceurOrdre;
import PandemicJavaFX.controleur.ordres.TypeOrdre;

import PandemicJavaFX.vues.GestionnaireVueImpl;
import org.json.JSONException;

import java.util.*;

public class Controleur implements LanceurOrdre {

    private ProxyClient proxyClient;
    private GestionnaireVueImpl gestionnaireVue;
    private Map<TypeOrdre, Collection<EcouteurOrdre>> abonnees;

    String pseudoConnecte;
    long idPartieCrée;
    long idPartieRejoint;


    public Controleur(ProxyClient proxyClient, GestionnaireVueImpl gestionnaireVue) {
        this.proxyClient = proxyClient;
        this.gestionnaireVue = gestionnaireVue;

        abonnees = new HashMap<>();
        for (TypeOrdre ordre : TypeOrdre.values()) {
            abonnees.put(ordre, new ArrayList<>());
        }
        this.gestionnaireVue.setAbonnement(this);
        this.gestionnaireVue.setControleur(this);

    }

    @Override
    public void abonnement(EcouteurOrdre ecouteurOrdre, TypeOrdre... types) {
        for(TypeOrdre ordre:types) {
            abonnees.get(ordre).add(ecouteurOrdre);
        }
    }

    @Override
    public void fireOrdre(TypeOrdre e) {
        for(EcouteurOrdre abonne:abonnees.get(e)) {
            try {
                abonne.traiter(e);
            } catch (JSONException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void run(){
        goToAcceuil();
    }

    public void goToAcceuil() {
        this.fireOrdre(TypeOrdre.SHOW_ACCEUIL);
    }

    public void goToInscription() {
        this.fireOrdre(TypeOrdre.SHOW_INSCRIPTION);
    }

    public void goToConnection() {
        this.fireOrdre(TypeOrdre.SHOW_CONNEXION);
    }

    public void goToInfosPartie() {

        this.fireOrdre(TypeOrdre.IDPARTIE_CREE_REJOINT);
        this.fireOrdre(TypeOrdre.IDPARTIE_REJOINT);
        this.fireOrdre(TypeOrdre.NIVEAUECLOSION);
        this.fireOrdre(TypeOrdre.NIVEAUPROPAGATION);
        this.fireOrdre(TypeOrdre.NOMBRESCUBESMALADIE);
        this.fireOrdre(TypeOrdre.LISTEMALADIEGUERI);
        this.fireOrdre(TypeOrdre.LISTEMALADIEERADIQUE);
        this.fireOrdre(TypeOrdre.LISTECENTREDERECHERCHE);
        this.fireOrdre(TypeOrdre.DEFAUSSEJOUEUR);
        this.fireOrdre(TypeOrdre.DEFAUSSEPROPAGATION);
        this.fireOrdre(TypeOrdre.SHOW_INFOSPARTIE);
    }
    public void goToChargementPartie() {

        this.fireOrdre(TypeOrdre.IDPARTIE_CREE_REJOINT);
        this.fireOrdre(TypeOrdre.IDPARTIE_REJOINT);
        this.fireOrdre(TypeOrdre.LISTEJOUEURS);
        this.fireOrdre(TypeOrdre.SHOW_CHARGEMENTPARTIE);
    }

    public void goToMenuActions() {
        this.fireOrdre(TypeOrdre.IDPARTIE_CREE_REJOINT);
        this.fireOrdre(TypeOrdre.IDPARTIE_REJOINT);
        this.fireOrdre(TypeOrdre.NOMBRESACTIONS);
        this.fireOrdre(TypeOrdre.NOMROLE);
        this.fireOrdre(TypeOrdre.NOMEMPLACEMENT);
        this.fireOrdre(TypeOrdre.SHOW_MENU_ACTIONS);
    }

    public void goToCreerOuRejoindrePartie() {
        this.fireOrdre(TypeOrdre.SHOW_MENU_CREEROUREJOINDREPARTIE);
    }


    public void inscriptionControleur(String pseudo, String password ) throws JSONException {
        proxyClient.inscriptionProxy(pseudo,password);
    }

    public void connexionControleur(String pseudo, String password) throws Exception {
        proxyClient.connexionProxy(pseudo,password);
        pseudoConnecte =pseudo;
    }

    public void deconnexionControleur() {
        proxyClient.deconnexionProxy();
        goToAcceuil();
    }

    public void rejoindrePartie(long idPartie) {

        proxyClient.rejoindrePartieProxy(idPartie,pseudoConnecte);
        this.idPartieRejoint= proxyClient.getIdPartieRejoins();
    }

    public void creerPartie(int nbJoueurs) {
        proxyClient.creerPartieProxy(nbJoueurs);
    }

    public long getIdPartieCrée() {
        idPartieCrée=this.proxyClient.getIdPartieCrée();
        return this.proxyClient.getIdPartieCrée();
    }

    public long getIdPartieRejoint() {
        idPartieRejoint=this.proxyClient.getIdPartieRejoins();
        return this.proxyClient.getIdPartieRejoins();
    }


    public void quitterPartie() {
        proxyClient.quitterPartie(idPartieRejoint,pseudoConnecte);
    }


    public String chargerListeJoueursRejoint() {
        proxyClient.chargerListeJoueurs(idPartieRejoint);
        return proxyClient.getListeJoueurs();
    }

    public void demarrerPartie() {
        proxyClient.demarrerPartie(idPartieRejoint);
    }

    public boolean verifSiPartieDemarré(){
        proxyClient.verifierPartieDemarré(idPartieRejoint);
        return proxyClient.isPartieDemarre();
    }

    public EtatPartiePOJO getEtatPartie(){
        return proxyClient.getEtatPartiePOJO();
    }

    public void recupererInfosPartie() throws JSONException {
        proxyClient.recupererInfosPartie(idPartieRejoint);
    }

    public void recupererInfosJoueur() throws JSONException {
        proxyClient.recupererInfosJoueur(idPartieRejoint);
    }

    public int getNbActions() throws JSONException {
        return proxyClient.getInfosJoueurPOJO().getNbActions();
    }

    public String getRole() {
        return proxyClient.getInfosJoueurPOJO().getNomRole();
    }

    public String getNomEmplacement() {
        return proxyClient.getInfosJoueurPOJO().getEmplacement();
    }
}
