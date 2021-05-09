package com.example.iwimhub.Models;

import android.widget.ImageView;

public class Professeur {

    private String nom;
    private String prenom;
    private String departement;
    private ImageView image;

    public Professeur() {
    }

    public Professeur(String nom, String prenom, String departement) {
        this.nom = nom;
        this.prenom = prenom;
        this.departement = departement;
       // this.image = image;
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

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
}
