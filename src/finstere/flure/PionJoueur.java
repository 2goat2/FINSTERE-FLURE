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
    
    //Constructeur
    public PionJoueur(boolean face, int f1, int f2) {
        this.face = face;
        this.valeurDeFaceClaire = f1;
        this.valeurDeFaceFonce = f2;
        this.utilise = false;
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
}
