module PandemicJavaFX {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires android.json;
    requires com.google.gson;

    opens PandemicJavaFX to javafx.fxml, com.google.gson;
    opens PandemicJavaFX.Proxy to javafx.fxml, com.google.gson;
    opens PandemicJavaFX.vues to javafx.fxml, com.google.gson;

    exports PandemicJavaFX;
    exports PandemicJavaFX.Proxy;
}
