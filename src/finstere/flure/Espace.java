/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finstere.flure;

import java.util.LinkedList;

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

    //Permet de garder les espace adjacentes de cette espace
    private final LinkedList<Espace> espacesAdj;

    //Constructeurs

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
        espacesAdj = new LinkedList<>();
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
        espacesAdj = new LinkedList<>();
    }

    /**
     * Ajouter l'objet dans l'espace
     *
     * @param obj l'objet qu'on veut l'ajouter
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
            //affichage = "|" + ((Joueur) obj).getNom().charAt(0) + "|";
            affichage = "|J|";
            this.setOccupee(true);
        } else if (obj.getClass().equals(Pierre.class)) {
            affichage = "|0|";
            this.setOccupee(true);
        } else if (obj.getClass().equals(Flague.class)) {
            affichage = "|⛖|";
            this.setOccupee(true);
        } else if (obj.getClass().equals(Pivot90.class)) {
            affichage = "|⮔|";
            this.setOccupee(true);
        } else if (obj.getClass().equals(Pivot180.class)) {
            affichage = "|⛖|";
            this.setOccupee(true);
        } else if (obj.getClass().equals(PionMonstre.class)) {
            switch (((PionMonstre) obj).getDirection()) {
                case NORTH:
                    affichage = "|⌃|";
                    this.setOccupee(true);
                    break;
                case EAST:
                    affichage = "|🢒|";
                    this.setOccupee(true);
                    break;
                case SOUTH:
                    affichage = "|⌄|";
                    this.setOccupee(true);
                    break;
                case WEST:
                    affichage = "|🢐|";
                    this.setOccupee(true);
                    break;
                default:
                    affichage = "|M|";
                    this.setOccupee(true);
            }
        } else {
            return false;
        }

        return true;
    }
    
        /**
     * Ajouter l'objet dans l'espace
     *
     * @param obj l'objet qu'on veut ajouter
     * @param j permet de prendre le nom d'un joueur et afficher son premier lettre dans l'espace
     * @return Retourne true si l'objet a été bien ajouté, sinon false
     */

    public boolean ajouterObjet(Object obj, Joueur j) {

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
            affichage = "|" + j.getNom().charAt(0) + "|";
            this.setOccupee(true);
        } else if (obj.getClass().equals(Pierre.class)) {
            affichage = "|0|";
            this.setOccupee(true);
        } else if (obj.getClass().equals(Flague.class)) {
            affichage = "|⛖|";
            this.setOccupee(true);
        } else if (obj.getClass().equals(Pivot90.class)) {
            affichage = "|⮔|";
            this.setOccupee(true);
        } else if (obj.getClass().equals(Pivot180.class)) {
            affichage = "|⛖|";
            this.setOccupee(true);
        } else if (obj.getClass().equals(PionMonstre.class)) {
            switch (((PionMonstre) obj).getDirection()) {
                case NORTH:
                    affichage = "|⌃|";
                    this.setOccupee(true);
                    break;
                case EAST:
                    affichage = "|🢒|";
                    this.setOccupee(true);
                    break;
                case SOUTH:
                    affichage = "|⌄|";
                    this.setOccupee(true);
                    break;
                case WEST:
                    affichage = "|🢐|";
                    this.setOccupee(true);
                    break;
                default:
                    affichage = "|M|";
                    this.setOccupee(true);
            }
        } else {
            return false;
        }

        return true;
    }

    // Méthode permet d'ajouter un espace adjacente à l'espace
    public void ajouterEspaceAdj(Espace e) {
        if (e == null) {
            throw new IllegalArgumentException("null espace");
        }
        espacesAdj.add(e);

    }

    /**
     * Getters AND Setters
     */
    // POUR X
    public int getX() {
        return x;
    }

    // POUR Y
    public int getY() {
        return y;
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

    public LinkedList<Espace> getEspacesAdj() {
        return espacesAdj;
    }

    // Méthode toString
    @Override
    public String toString() {
        return affichage;
    }

    /*
    * Méthode pour supprimer l'objet d'un espace
     */
    public Object supprimerObject() {
        Object x = this.obj;
        this.obj = null;
        this.estOccupe = false;
        
        if (!this.estOccupe) {
            affichage = "| |";
        } else {
            affichage = "|X|";
        }
        return x;
    }

    /*
    * Méthode pour modifier l'affichage d'un espace
     */
    public void setAffichage(int x) {
        this.affichage = " " + x;
    }
    public void setAffichage(String x) {
        this.affichage = " " + x;
    }
}
