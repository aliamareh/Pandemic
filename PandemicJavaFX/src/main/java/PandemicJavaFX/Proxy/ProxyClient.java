package PandemicJavaFX.Proxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ProxyClient {

    private HttpClient httpClient = HttpClient.newHttpClient();

    private ObjectMapper objectMapper = new ObjectMapper();

    private static String BASE_URI="http://localhost:8080/pandemic";

    private LoginPOJO loginPOJO;
    private String joueur;

    private long idPartieCrée;

    private long idPartieRejoins;


    private String listeJoueurs;

    private int nbJoueurs;

    private boolean partieDemarre=false;

    private static String token;

    private EtatPartiePOJO etatPartiePOJO;

    private InfosJoueurPOJO infosJoueurPOJO;


    public String inscriptionProxy(String pseudo, String password ) throws JSONException {

        loginPOJO= new LoginPOJO();
        loginPOJO.setPseudo(pseudo);
        loginPOJO.setPassword(password);
        joueur= new Gson().toJson(loginPOJO);
        HttpRequest request= HttpRequest.newBuilder().
                uri(URI.create(BASE_URI+"/inscription")).
                setHeader("Content-Type","application/json").
                POST(HttpRequest.BodyPublishers.ofString(joueur)).build();

        try {
            HttpResponse<String> response= httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            switch (response.statusCode()) {
                case HttpURLConnection.HTTP_CREATED:
                    token=response.headers().firstValue("Authorization").get();
                    token=token.substring(7);
                    return token;
                default: throw new RuntimeException("Code HTTP_"+response.statusCode()+" :"+response.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public String connexionProxy(String pseudo, String password ) throws Exception {

            loginPOJO = new LoginPOJO();
            loginPOJO.setPseudo(pseudo);
            loginPOJO.setPassword(password);
            joueur = new Gson().toJson(loginPOJO);
            HttpRequest request = HttpRequest.newBuilder().
                    uri(URI.create(BASE_URI + "/connexion")).
                    setHeader("Content-Type", "application/json").
                    POST(HttpRequest.BodyPublishers.ofString(joueur)).build();

            try {
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                switch (response.statusCode()) {
                    case HttpURLConnection.HTTP_OK:
                        token=response.headers().firstValue("Authorization").get();
                        token=token.substring(7);
                        return token;
                    default:
                        throw new RuntimeException("Code HTTP_"+response.statusCode()+" :"+response.body());
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
    }

    public int deconnexionProxy() {
        HttpRequest request= HttpRequest.newBuilder().
                uri(URI.create(BASE_URI+"/deconnexion")).
                setHeader("Content-Type","application/x-www-form-urlencoded").
                setHeader("Authorization","Bearer "+token).
                POST(HttpRequest.BodyPublishers.noBody()).build();

        try {
            HttpResponse<String> response= httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            switch (response.statusCode()) {
                case HttpURLConnection.HTTP_OK:
                    token=null;
                    return response.statusCode();
                default: throw new RuntimeException("Code HTTP_"+response.statusCode()+" :"+response.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public String creerPartieProxy(int nbJoueurs)  {

        HttpRequest request= HttpRequest.newBuilder().
                uri(URI.create(BASE_URI+"/partie/create")).
                setHeader("Content-Type","application/x-www-form-urlencoded").
                setHeader("Authorization","Bearer "+token).
                POST(HttpRequest.BodyPublishers.ofString("nbJoueurs="+nbJoueurs)).build();

        try {
            HttpResponse<String> response= httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            switch (response.statusCode()) {
                case HttpURLConnection.HTTP_CREATED:
                    String idPartiev1=response.body();
                    idPartieCrée =Integer.parseInt(idPartiev1.substring(13,idPartiev1.length()-2));
                    return response.body();
                default: throw new RuntimeException("Code HTTP_"+response.statusCode()+" :"+response.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public String rejoindrePartieProxy(long id,String pseudoConnecté) {
        HttpRequest request= HttpRequest.newBuilder().
                uri(URI.create(BASE_URI+"/partie/"+id+"/join")).
                setHeader("Content-Type","application/x-www-form-urlencoded").
                setHeader("Authorization","Bearer "+token).
                POST(HttpRequest.BodyPublishers.ofString("pseudo="+pseudoConnecté)).build();

        try {
            HttpResponse<String> response= httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            switch (response.statusCode()) {
                case HttpURLConnection.HTTP_OK:
                    idPartieRejoins= id;
                    return response.body();
                default: throw new RuntimeException("Code HTTP_"+response.statusCode()+" :"+response.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public long getIdPartieCrée() {
        return idPartieCrée;
    }

    public void setIdPartieCrée(int idPartieCrée) {
        this.idPartieCrée = idPartieCrée;
    }

    public long getIdPartieRejoins() {
        return idPartieRejoins;
    }

    public void setIdPartieRejoins(int idPartieRejoins) {
        this.idPartieRejoins = idPartieRejoins;
    }

    public String chargerListeJoueurs(long id) {
        HttpRequest request= HttpRequest.newBuilder().
                uri(URI.create(BASE_URI+"/partie/"+id+"/joueurs-connectes")).
                setHeader("Content-Type","application/x-www-form-urlencoded").
                setHeader("Authorization","Bearer "+token).
                GET().build();

        try {
            HttpResponse<String> response= httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            switch (response.statusCode()) {
                case HttpURLConnection.HTTP_OK:
                    listeJoueurs=response.body();
                    return response.body();
                default: throw new RuntimeException("Code HTTP_"+response.statusCode()+" :"+response.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getListeJoueurs() {
        return listeJoueurs;
    }

    public void setListeJoueurs(String listeJoueurs) {
        this.listeJoueurs = listeJoueurs;
    }

    public int getNbJoueurs() {
        return nbJoueurs;
    }

    public void setNbJoueurs(int nbJoueurs) {
        this.nbJoueurs = nbJoueurs;
    }

    public void setIdPartieRejoins(long idPartieRejoins) {
        this.idPartieRejoins = idPartieRejoins;
    }

    public String quitterPartie(long id, String pseudoConnecté) {
        HttpRequest request= HttpRequest.newBuilder().
                uri(URI.create(BASE_URI+"/partie/"+id+"/quitter")).
                setHeader("Content-Type","application/x-www-form-urlencoded").
                setHeader("Authorization","Bearer "+token).
                POST(HttpRequest.BodyPublishers.ofString("pseudo="+pseudoConnecté)).build();

        try {
            HttpResponse<String> response= httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            switch (response.statusCode()) {
                case HttpURLConnection.HTTP_OK:
                    return response.body();
                default: throw new RuntimeException("Code HTTP_"+response.statusCode()+" :"+response.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String demarrerPartie(long id) {
        HttpRequest request= HttpRequest.newBuilder().
                uri(URI.create(BASE_URI+"/partie/"+id+"/start")).
                setHeader("Content-Type","application/x-www-form-urlencoded").
                setHeader("Authorization","Bearer "+token).
                POST(HttpRequest.BodyPublishers.noBody()).build();

        try {
            HttpResponse<String> response= httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            switch (response.statusCode()) {
                case HttpURLConnection.HTTP_OK:
                    return response.body();
                default: throw new RuntimeException("Code HTTP_"+response.statusCode()+" :"+response.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verifierPartieDemarré(long id) {
        HttpRequest request= HttpRequest.newBuilder().
                uri(URI.create(BASE_URI+"/partie/"+id+"/encours")).
                setHeader("Content-Type","application/x-www-form-urlencoded").
                setHeader("Authorization","Bearer "+token).
                GET().build();
        try {
            HttpResponse<String> response= httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            switch (response.statusCode()) {
                case HttpURLConnection.HTTP_OK:
                    return partieDemarre=true;
                default:
                    partieDemarre=false;
                    return partieDemarre=false;
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isPartieDemarre() {
        return partieDemarre;
    }

    public String recupererInfosPartie(long id) throws JSONException {

        HttpRequest request= HttpRequest.newBuilder().
                uri(URI.create(BASE_URI+"/partie/"+id)).
                setHeader("Content-Type","application/json").
                setHeader("Authorization","Bearer "+token).GET().build();

        try {
            HttpResponse<String> response= httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            switch (response.statusCode()) {
                case HttpURLConnection.HTTP_OK:
                    String info= response.body();
                    Gson g = new Gson();
                    etatPartiePOJO = g.fromJson(info, EtatPartiePOJO.class);
                    return response.body();
                default: throw new RuntimeException("Code HTTP_"+response.statusCode()+" :"+response.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }



    public EtatPartiePOJO getEtatPartiePOJO() {
        return etatPartiePOJO;
    }

    public void setEtatPartiePOJO(EtatPartiePOJO etatPartiePOJO) {
        this.etatPartiePOJO = etatPartiePOJO;
    }

    public String recupererInfosJoueur(long id) throws JSONException {

        HttpRequest request= HttpRequest.newBuilder().
                uri(URI.create(BASE_URI+"/partie/"+id+"/"+loginPOJO.getPseudo()+"/mesinfos")).
                setHeader("Content-Type","application/json").
                setHeader("Authorization","Bearer "+token).GET().build();

        try {
            HttpResponse<String> response= httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            switch (response.statusCode()) {
                case HttpURLConnection.HTTP_OK:
                    String info= response.body();
                    Gson g = new Gson();
                    infosJoueurPOJO = g.fromJson(info, InfosJoueurPOJO.class);
                    return response.body();
                default: throw new RuntimeException("Code HTTP_"+response.statusCode()+" :"+response.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public InfosJoueurPOJO getInfosJoueurPOJO() {
        return infosJoueurPOJO;
    }

    public void setInfosJoueurPOJO(InfosJoueurPOJO infosJoueurPOJO) {
        this.infosJoueurPOJO = infosJoueurPOJO;
    }
}
