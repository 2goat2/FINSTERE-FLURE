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
public class Espace {

    //Permet de savoir si la casse est occupée par un autre pion ou non.
    private boolean estOccupe = true;

    //coordonnée de l'espace
    private int x;
    private int y;

    //Permet de créer un objet et le mettre dans l'espace
    private Object obj = null;

    //Permet d'afficher l'espace
    private String affichage = "|X|";

    //Orientations
    private final int NORTH = 1, EAST = 2, SOUTH = 3, WEST = 4;

    //Constructeurs
    /**
     * permet de créer un espace vide standard
     */
    public Espace() {
    }

    /**
     * permet de créer un espace vide standard mais le définit comme utilisable
     * ou non
     *
     * @param estOccupe True si l'espace est occupée, sinon false
     */
    public Espace(boolean estOccupe) {
        this.estOccupe = estOccupe;
        if (!estOccupe) {
            affichage = "| |";
        }

    }

    /**
     *
     * permet de créer un espace vide standard par rapport à ses x et y
     *
     * @param x
     * @param y
     * @param estOccupe True si l'espace est occupée, sinon false
     */
    public Espace(int x, int y, boolean estOccupe) {

        this.x = x;
        this.y = y;
        this.estOccupe = estOccupe;
        if (!estOccupe) {
            affichage = "| |";
        }

    }

    /**
     *
     * permet de créer un espace qui contient un objet par rapport à ses x et y
     *
     * @param x
     * @param y
     * @param obj l'objet lequel on vas le mettre dans cet espace
     */
    public Espace(int x, int y, Object obj) {

        this.x = x;
        this.y = y;
        ajouterObjet(obj);

    }

    /**
     * Ajouter l'objet dans l'espace
     *
     * @param obj The object to be added
     * @return Retourne true si l'objet a été bien ajouté, sinon false
     */
    public boolean ajouterObjet(Object obj) {

        if (this.obj != null) {
            return false;
        } else if (estOccupe) {
            this.obj = obj;
            return false;
        }
        this.obj = obj;
        if (obj == null) {
            affichage = "| |";
        }// A place de :obj instanceof Joueur joueur 
        else if (obj.getClass().equals(PionJoueur.class)) {
            // affichage = "|" + ((Joueur) obj).getNom().charAt(0) + "|";
            affichage = "|J|";
        } else if (obj.getClass().equals(Pierre.class)) {
            affichage = "|?|";
        } else if (obj.getClass().equals(Flague.class)) {
            affichage = "|⛖|";
        } else if (obj.getClass().equals(Pivot90.class)) {
            affichage = "|⮔|";
        } else if (obj.getClass().equals(Pivot180.class)) {
            affichage = "|⛖|";
        } else if (obj.getClass().equals(PionMonstre.class)) {
            switch (((PionMonstre) obj).getDirection()) {
                case NORTH:
                    affichage = "|⌃|";
                    break;
                case EAST:
                    affichage = "|🢒|";
                    break;
                case SOUTH:
                    affichage = "|⌄|";
                    break;
                case WEST:
                    affichage = "|🢐|";
                    break;
                default:
                    affichage = "|M|";
            }
        } else {
            return false;
        }

        return true;
    }

    /**
     * Getters AND Setters
     */
    // POUR X
    public int getX() {
        return x;
    }

    public void setX(int a) {
        this.x = a;
    }

    // POUR Y
    public int getY() {
        return y;
    }

    public void setY(int a) {
        this.y = a;
    }

    //POUR estOccupe
    public boolean isOccupee() {
        return estOccupe;
    }

    public void setOccupee(boolean occupe) {
        this.estOccupe = occupe;
    }

    //POUR l'objet
    public Object getObjet() {
        return obj;
    }

    public void setObjet(Object a) {
        this.obj = a;
    }

    // Méthode toString
    @Override
    public String toString() {
        return affichage;
    }

    /*
    * Méthode pour supprimer l'objet d'un espace
    */
    public boolean supprimerObject() {
        Object temp = obj;
        obj = null;
        if (!estOccupe) {
            affichage = "| |";
        } else {
            affichage = "|X|";
        }
        return true;
    }

    /*
    * Méthode pour modifier l'affichage d'un espace
     */
    public void setAffichage(int x) {
        this.affichage = " " + x;
    }
}
