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

    public PionMonstre(int direction) {
        this.direction = direction;
    }

    /**
     * SETTER
     *
     * @param direction permet de modifier la direction du monstre
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

}
