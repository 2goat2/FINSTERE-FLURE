/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finstere.flure;

/**
 *
 * @author nadim
 */
public class PionJoueur extends Pion {

    //Tableau String des couleurs
    private String[] couleur = new String[]{"bleu", "brun", "gris", "vert", "violet", "rouge", "jaune"};

    //true : face foncée ; false : face claire
    private boolean face = false;

    //permet de savoir si le pion est mort ou pas 
    private boolean perdu = false;

    //2 variables qui stockent les valeurs de chaque face du pion
    private int valeurDeFaceFonce, valeurDeFaceClaire;

    //permet de savoir si le pion est utilisé ou pas*
    private boolean utilise = false;

    //permet de stocker la valeur de la face utilisée
    private int valeurActuelle;
    
    //permet de compter les espaces où le pion déplace
    private int compteur;
    
    //permet de garder les coordonées anciennces
    private int xAncien, yAncien;

    //Constructeur
    public PionJoueur(int x, int y, boolean face, int f1, int f2) {
        super(x, y);
        this.face = face;
        this.valeurDeFaceClaire = f1;
        this.valeurDeFaceFonce = f2;
        this.utilise = false;
        this.valeurActuelle = this.valeurDeFaceClaire;
        compteur = this.valeurActuelle;
        this.xAncien = 0;
        this.yAncien = 0;
    }

    public String[] getCouleur() {
        return this.couleur;
    }

    public String getFace() {
        if (this.isFace() == true) {
            return "claire";
        } else {
            return "foncée";
        }
    }

    public void setFace(boolean f) {
        this.face = f;
    }

    @Override
    public String toString() {
        return getCouleur() + " face " + getFace();
    }

    /**
     * @param couleur the couleur to set
     */
    public void setCouleur(String[] couleur) {
        this.setCouleur(couleur);
    }

    /**
     * @return the face
     */
    public boolean isFace() {
        return face;
    }

    /**
     * @return the perdu
     */
    public boolean isPerdu() {
        return perdu;
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
     * @param valeurDeFaceFonce the valeurDeFaceFonce to set
     */
    public void setValeurDeFaceFonce(int valeurDeFaceFonce) {
        this.valeurDeFaceFonce = valeurDeFaceFonce;
    }

    /**
     * @return the valeurDeFaceClaire
     */
    public int getValeurDeFaceClaire() {
        return valeurDeFaceClaire;
    }

    /**
     * @param valeurDeFaceClaire the valeurDeFaceClaire to set
     */
    public void setValeurDeFaceClaire(int valeurDeFaceClaire) {
        this.valeurDeFaceClaire = valeurDeFaceClaire;
    }

    /**
     * @return the utilise
     */
    public boolean isUtilise() {
        return utilise;
    }

    /**
     * @param utilise the utilise to set
     */
    public void setUtilise(boolean utilise) {
        this.utilise = utilise;
    }

    /**
     * @return the valeurActuelle
     */
    public int getValeurActuelle() {
        return valeurActuelle;
    }

    /**
     * @param valeurActuelle the valeurActuelle to set
     */
    public void flipValeurActuelle() {
        if (this.valeurActuelle == this.valeurDeFaceClaire) {
            this.valeurActuelle = this.valeurDeFaceFonce;
        } else this.valeurActuelle = this.valeurDeFaceClaire;
    }
    
    public void setCompteur(int i){
        this.compteur = i;
    }
    
    public int getCompteur(){
        return this.compteur;
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
      
    
}
