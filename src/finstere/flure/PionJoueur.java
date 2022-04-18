/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finstere.flure;

import java.util.Random;

/**
 *
 * @author nadim La classe PionMonstre extends la classe Pion
 */
public class PionJoueur extends Pion {

    //Tableau String des couleurs
    private final String[] tabcouleur = new String[]{"blue", "brown", "gray", "green", "purple", "red", "yellow"};

    private String couleur;

    //true : face foncée ; false : face claire
    private boolean face = false;

    //permet de savoir si le pion est mort ou pas 
    private boolean perdu = false;

    //2 variables qui stockent les valeurs de chaque face du pion
    private int valeurDeFaceFonce, valeurDeFaceClaire;

    //permet de savoir si le pion est utilisé ou pas
    private boolean utilise = false;

    //permet de stocker la valeur de la face utilisée
    private int valeurActuelle;

    //permet de compter les espaces où le pion déplace
    private int compteur;

    //permet de garder les coordonées anciennces
    private int xAncien, yAncien;

    //Permet de savoir le joueur lequel ce pion appartient
    private int numJoueur;

    //permet de savoir si le pion est bien initialisé et il est sur le plateau
    private boolean estSurPlateau;

    public String imageSource;

    //Constructeur
    public PionJoueur(int x, int y, boolean face, int f1, int f2, int i) {

        super(x, y);
        this.face = face;
        this.valeurDeFaceClaire = f1;
        this.valeurDeFaceFonce = f2;
        this.utilise = false;
        this.valeurActuelle = this.valeurDeFaceClaire;
        compteur = this.valeurActuelle;
        this.xAncien = 0;
        this.yAncien = 0;
        this.numJoueur = i;
        this.estSurPlateau = false;
        
        if (this.numJoueur == 0) {
            this.couleur = "blue";
        } else {
            this.couleur = "red";
        }
        this.imageSource = FinFlureGUI.chemin + "pion" + this.couleur + "_" + this.valeurDeFaceFonce + "_" + this.valeurDeFaceClaire + "_" + this.getFace() + ".gif";

    }

    public void flipValeurActuelle() {
        if (this.getValeurActuelle() == this.getValeurDeFaceClaire()) {
            this.setValeurActuelle(this.getValeurDeFaceFonce());
        } else {
            this.setValeurActuelle(this.getValeurDeFaceClaire());
        }
    }
    
    
    public final String getRandomCouleur() {
        Random ra = new Random();
        int x = ra.nextInt(7);
        return this.tabcouleur[x];
    }


    @Override
    public String toString() {
        return "Pion : " + this.valeurDeFaceClaire + "/" + this.valeurDeFaceFonce;
    }

    public final String getFace() {
        if (this.isFace() == false) {
            return "clair";
        } else {
            return "fonce";
        }
    }

    /**
     * @return the face
     */
    public boolean isFace() {
        return face;
    }

    /**
     * @param perdu the perdu to set
     */
    public void setPerdu(boolean perdu) {
        this.perdu = perdu;
    }

    /**
     * @return the valeurDeFaceFonce
     */
    public int getValeurDeFaceFonce() {
        return valeurDeFaceFonce;
    }

    /**
     * @return the valeurDeFaceClaire
     */
    public int getValeurDeFaceClaire() {
        return valeurDeFaceClaire;
    }

    /**
     * @return the valeurActuelle
     */
    public int getValeurActuelle() {
        return valeurActuelle;
    }

    /**
     * @return the xAncien
     */
    public int getxAncien() {
        return xAncien;
    }

    /**
     * @param xAncien the xAncien to set
     */
    public void setxAncien(int xAncien) {
        this.xAncien = xAncien;
    }

    /**
     * @return the yAncien
     */
    public int getyAncien() {
        return yAncien;
    }

    /**
     * @param yAncien the yAncien to set
     */
    public void setyAncien(int yAncien) {
        this.yAncien = yAncien;
    }

    /**
     * @param valeurActuelle the valeurActuelle to set
     */
    public void setValeurActuelle(int valeurActuelle) {
        this.valeurActuelle = valeurActuelle;
    }

    /**
     * @return the numJoueur
     */
    public int getNumJoueur() {
        return numJoueur;
    }

    /**
     * @return the estSurPlateau
     */
    public boolean getEstSurPlateau() {
        return estSurPlateau;
    }

    /**
     * @param estSurPlateau the estSurPlateau to set
     */
    public void setEstSurPlateau(boolean estSurPlateau) {
        this.estSurPlateau = estSurPlateau;
    }

    /**
     * @return the couleur
     */
    public String getCouleur() {
        return couleur;
    }

    /**
     * @param couleur the couleur to set
     */
    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

}
