package PandemicJavaFX.vues;

import PandemicJavaFX.controleur.Controleur;

public interface VueInteractive {
    void setControleur(Controleur controleur);
    Controleur getControleur();
}
