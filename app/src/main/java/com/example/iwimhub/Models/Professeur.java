package com.example.iwimhub.Models;

import android.widget.ImageView;

public class Professeur {

    private String id;
    private String nom;
    private String prenom;
    private String departement;
    private String imageurl;
    private String idProf;

    public Professeur() {
    }

    public Professeur(String nom, String prenom, String departement, String idProf) {
        this.nom = nom;
        this.prenom = prenom;
        this.departement = departement;
        this.idProf = idProf;
        //this.imageurl = imageurl;
    }

    public void setIdProf(String idProf) {
        this.idProf = idProf;
    }

    public String getIdProf() {
        return idProf;
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

    public String getImage() {
        return imageurl;
    }

    public void setImage(String  image) {
        this.imageurl = imageurl;
    }
}
