/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finstere.flure;

import java.util.Objects;

/**
 *
 * @author nadim
 */
public class PionMonstre {

    private int direction = 2;
    private int NORTH = 1;
    private int EAST = 2;
    private int SOUTH = 3;
    private int WEST = 4;
    private Plateau plateau;
    private Direction directions;
    private int pionsTues;
    private int x,y;
    private int mouvement = 1;

    public PionMonstre(int x, int y, int direction, Plateau p) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.directions = new Direction();
        this.plateau = p;
        this.pionsTues = 0;
    }


    public void deplacer(int move) {
        if (move > 0) {
            int gauche = regarder(getDirections().getGauche(this.getDirection()));
            int droite = regarder(getDirections().getDroite(this.getDirection()));
            int droit = regarder(this.getDirection());


                if (gauche > droite && droite != 0) {
                    this.setDirection(getDirections().getDroite(this.getDirection()));
                    this.getPlateau().deplacerLeMonstreUneFois(this);
                } else if(droite > gauche && gauche != 0){
                    this.setDirection(this.getDirections().getGauche(this.getDirection()));
                    this.getPlateau().deplacerLeMonstreUneFois(this);
            } else {
                //straight
                if (regarderObjet(this.getDirection()) != null && Objects.requireNonNull(regarderObjet(this.getDirection())).getClass() == Pierre.class) {
                    this.getPlateau().deplacerPierreUneFois(this.getDirection(), (Pierre) regarderObjet(this.getDirection()));
                    this.getPlateau().deplacerLeMonstreUneFois(this);
                } else {
                    this.getPlateau().deplacerLeMonstreUneFois(this);
                }
            }
            this.getPlateau().print();
            deplacer(move - 1);
        }
    }

    private Object regarderObjet(int direction) {
        switch (direction) {
            case 1:
                return regarderObjetNorth();
            case 2:
                return regarderObjetEast();
            case 3:
                return regarderObjetSouth();
            case 4:
                return regarderObjetWest();
        }
        return null;
    }

    private Object regarderObjetWest() {
        return this.getPlateau().getPlateau()[getY()][getX() - this.getMouvement()].getObjet();
    }

    private Object regarderObjetNorth() {
        return this.getPlateau().getPlateau()[getY() - this.getMouvement()][getX()].getObjet();
    }

    private Object regarderObjetSouth() {
        return this.getPlateau().getPlateau()[getY() + this.getMouvement()][getX()].getObjet();
    }

    private Object regarderObjetEast() {
        return this.getPlateau().getPlateau()[getY()][getX() + this.mouvement].getObjet();

    }

    /**
     * SETTER et GETTERS
     *
     * @param direction permet de modifier la direction du monstre
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    private int regarder(int direction) {
        switch (direction) {
            case 1:
                return regarderNorth();
            case 2:
                return regarderEast();
            case 3:
                return regarderSouth();
            case 4:
                return regarderWest();
        }

        return -1;
    }

    private int regarderNorth() {

        for (int i = 0; i <= y; i++) {
            if (this.getPlateau().getPlateau()[y-i][x].getObjet() == null) {
                continue;
            } else if (this.getPlateau().getPlateau()[y-i][x].getObjet().getClass() == PionJoueur.class) {
                return i;
            } else if (this.getPlateau().getPlateau()[y-i][x].getObjet() == Pierre.class) {
                return this.getPlateau().getLargeur() * this.getPlateau().getHauteur();//Ensures that it is the biggest number.
            }
        }

        return this.getPlateau().getLargeur() * this.getPlateau().getHauteur();
    }

    private int regarderSouth() {

        for (int i = y; i < this.getPlateau().getHauteur(); i++) {
            if (this.getPlateau().getPlateau()[i][x].getObjet() == null) {
                continue;
            } else if (this.getPlateau().getPlateau()[i][x].getObjet().getClass() == PionJoueur.class) {
                return i - y;
            } else if (this.getPlateau().getPlateau()[i][x].getObjet().getClass() == Pierre.class) {
                return this.getPlateau().getLargeur() * this.getPlateau().getHauteur();//Ensures that it is the biggest number.
            }
        }

        return this.getPlateau().getLargeur() * this.getPlateau().getHauteur();
    }

    private int regarderWest() {

        for (int i = 0; i <= x ; i++) {
            if (this.getPlateau().getPlateau()[y][x-i].getObjet() == null) {
                continue;
            } else if (this.getPlateau().getPlateau()[y][x-i].getObjet().getClass() == PionJoueur.class) {
                return i;
            } else if (this.getPlateau().getPlateau()[y][x-i].getObjet().getClass() == Pierre.class) {
                return this.getPlateau().getLargeur() * this.getPlateau().getHauteur();//Ensures that it is the biggest number.
            }
        }

        return this.getPlateau().getLargeur() * this.getPlateau().getHauteur();
    }

    private int regarderEast() {

        for (int i = x; i < this.getPlateau().getLargeur(); i++) {
            if (this.getPlateau().getPlateau()[y][i].getObjet() == null) {
                continue;
            } else
                if (this.getPlateau().getPlateau()[y][i].getObjet().getClass() == PionJoueur.class) {
                return i - x;
            } else
                if (this.plateau.getPlateau()[y][i].getObjet().getClass() == Pierre.class) {
                return this.getPlateau().getLargeur() * this.getPlateau().getHauteur();//Ensures that it is the biggest number.
            }
        }

        return this.getPlateau().getLargeur() * this.getPlateau().getHauteur();
    }

    /**
     * @return the plateau
     */
    public Plateau getPlateau() {
        return plateau;
    }

    /**
     * @param plateau the plateau to set
     */
    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    /**
     * @return the NORTH
     */
    public int getNORTH() {
        return NORTH;
    }

    /**
     * @param NORTH the NORTH to set
     */
    public void setNORTH(int NORTH) {
        this.NORTH = NORTH;
    }

    /**
     * @return the EAST
     */
    public int getEAST() {
        return EAST;
    }

    /**
     * @param EAST the EAST to set
     */
    public void setEAST(int EAST) {
        this.EAST = EAST;
    }

    /**
     * @return the SOUTH
     */
    public int getSOUTH() {
        return SOUTH;
    }

    /**
     * @param SOUTH the SOUTH to set
     */
    public void setSOUTH(int SOUTH) {
        this.SOUTH = SOUTH;
    }

    /**
     * @return the WEST
     */
    public int getWEST() {
        return WEST;
    }

    /**
     * @param WEST the WEST to set
     */
    public void setWEST(int WEST) {
        this.WEST = WEST;
    }

    /**
     * @return the directions
     */
    public Direction getDirections() {
        return directions;
    }

    /**
     * @param directions the directions to set
     */
    public void setDirections(Direction directions) {
        this.directions = directions;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the mouvement
     */
    public int getMouvement() {
        return mouvement;
    }

    /**
     * @param mouvement the mouvement to set
     */
    public void setMouvement(int mouvement) {
        this.mouvement = mouvement;
    }

    public void ajouterPionsTues(){ this.pionsTues += 1;}

}
