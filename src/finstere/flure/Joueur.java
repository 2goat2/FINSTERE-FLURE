/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finstere.flure;

import java.util.ArrayList;

/**
 *
 * @author nadim
 */
public class Joueur {

    //le nom d'un joueur
    private String nom;
    
    //La liste qui garde les pion d'un joueur
    private ArrayList<PionJoueur> pion = new ArrayList<PionJoueur>();

    
    //Constructeur
    public Joueur(String nom) {
        this.nom = nom;
    }
    
    
    /*
    * GETTERS AND SETTERS
    */
    public String getNom(){
        return nom;
    }
    
    public void setNom(String nom){
        this.nom = nom;
    }
    
    public void setPions(ArrayList<PionJoueur> pion){
        this.pion = pion;
    }
    
    public ArrayList<PionJoueur> getPions(){
        return this.pion;
    }
    
    public String toString(){
        return this.nom;
    }

}
