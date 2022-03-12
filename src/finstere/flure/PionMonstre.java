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
public class PionMonstre {

    private int direction;
    private final int NORTH = 1, EAST = 2, SOUTH = 3, WEST = 4;
    private int x, y;
    private Plateau plateau;

    public PionMonstre(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public PionMonstre(int x, int y) {
        this.x = x;
        this.y = y;
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

}
