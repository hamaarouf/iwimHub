package com.example.iwimhub.Models;

public class etudiantModel {
    String nom, prenom,filiere, Annee;

    public etudiantModel() {
    }

    public etudiantModel(String nom, String prenom, String filiere, String annee) {
        this.nom = nom;
        this.prenom = prenom;
        this.filiere = filiere;
        Annee = annee;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public String getAnnee() {
        return Annee;
    }

    public void setAnnee(String annee) {
        Annee = annee;
    }
}
