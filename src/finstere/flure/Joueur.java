/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finstere.flure;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author nadim
 */
public class Joueur {

    //le nom d'un joueur
    private String nom;

    //La liste qui garde les pion d'un joueur
    private ArrayList<PionJoueur> pion = new ArrayList<PionJoueur>();

    //La liste qui garde les pion d'un joueur
    private ArrayList<PionJoueur> pionsReste = new ArrayList<PionJoueur>();

    //booleen pour voir si le joueur est gagnant
    private boolean gagnant = false;

    //Constructeurs
    public Joueur(String nom) {
        this.nom = nom;
    }

    public Joueur() {
    }

    /*
    * GETTERS AND SETTERS
     */
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPions(ArrayList<PionJoueur> pion) {
        this.setPion(pion);
    }

    public ArrayList<PionJoueur> getPions() {
        return this.getPion();
    }

    public String toString() {
        return this.getNom();
    }

    /**
     * @return the pion
     */
    public ArrayList<PionJoueur> getPion() {
        return pion;
    }

    /**
     * @param pion the pion to set
     */
    public void setPion(ArrayList<PionJoueur> pion) {
        this.pion = pion;
    }

    /**
     * @param gagnant the gagnant to set
     */
    public void setGagnant(boolean gagnant) {
        this.gagnant = gagnant;
    }

    /**
     * @param pionsReste the pionsReste to set
     */
    public void setPionsReste(ArrayList<PionJoueur> pionsReste) {
        this.pionsReste = pionsReste;
    }

}
