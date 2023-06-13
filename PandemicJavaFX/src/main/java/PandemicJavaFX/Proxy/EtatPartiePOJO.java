package PandemicJavaFX.Proxy;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.*;

public class EtatPartiePOJO {

	int idPartie;
	int nbJoueurs;
	Map<String,String> joueurs;
	String jc;
	int etat;
	int nvPropagation;
	int eclosion;
	List<String> centreRecherche;
	List<String> gueri;
	List<String> eradique;
	Map<String,String> cubesMaladie;
	List<CartePOJO> defausseJoueurs;
	List<CartePOJO> defaussePropagation;

	public EtatPartiePOJO(){};

	public int getIdPartie() {
		return idPartie;
	}

	public void setIdPartie(int idPartie) {
		this.idPartie = idPartie;
	}

	public int getNbJoueurs() {
		return nbJoueurs;
	}

	public void setNbJoueurs(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
	}

	public Map<String, String> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(Map<String, String> joueurs) {
		this.joueurs = joueurs;
	}

	public String getJc() {
		return jc;
	}

	public void setJc(String jc) {
		this.jc = jc;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public int getNvPropagation() {
		return nvPropagation;
	}

	public void setNvPropagation(int nvPropagation) {
		this.nvPropagation = nvPropagation;
	}

	public int getEclosion() {
		return eclosion;
	}

	public void setEclosion(int eclosion) {
		this.eclosion = eclosion;
	}

	public List<String> getCentreRecherche() {
		return centreRecherche;
	}

	public void setCentreRecherche(List<String> centreRecherche) {
		this.centreRecherche = centreRecherche;
	}

	public List<String> getGueri() {
		return gueri;
	}

	public void setGueri(List<String> gueri) {
		this.gueri = gueri;
	}

	public List<String> getEradique() {
		return eradique;
	}

	public void setEradique(List<String> eradique) {
		this.eradique = eradique;
	}

	public Map<String, String> getCubesMaladie() {
		return cubesMaladie;
	}

	public void setCubesMaladie(Map<String, String> cubesMaladie) {
		this.cubesMaladie = cubesMaladie;
	}

	public List<CartePOJO> getDefausseJoueurs() {
		return defausseJoueurs;
	}

	public void setDefausseJoueurs(List<CartePOJO> defausseJoueurs) {
		this.defausseJoueurs = defausseJoueurs;
	}

	public List<CartePOJO> getDefaussePropagation() {
		return defaussePropagation;
	}

	public void setDefaussePropagation(List<CartePOJO> defaussePropagation) {
		this.defaussePropagation = defaussePropagation;
	}


}
