package PandemicJavaFX;

import PandemicJavaFX.Proxy.ProxyClient;
import PandemicJavaFX.controleur.Controleur;
import PandemicJavaFX.vues.GestionnaireVueImpl;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class JeuApplication extends Application {



    @Override
    public void start(Stage stage) throws IOException {
        GestionnaireVueImpl gestionnaireVue = new GestionnaireVueImpl(stage);
        ProxyClient proxyClient = new ProxyClient();
        Controleur controleur= new Controleur(proxyClient, gestionnaireVue);
        controleur.run();
    }

    public static void main(String[] args) {
        launch();
    }

}
