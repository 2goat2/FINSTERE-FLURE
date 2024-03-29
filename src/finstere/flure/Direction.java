/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finstere.flure;

/**
 *
 * @author nadim
 * La classe Direction permet au monstre de choisir et changer son orientation
 */
public class Direction {

    private final int[][] directions;

    public Direction() {

        int gaucheDroite = 2;
        int directionsPossible = 4;

        directions = new int[directionsPossible][gaucheDroite];
        directions[0][0] = 4;
        directions[0][1] = 2;

        directions[2][0] = 2;
        directions[2][1] = 4;

        directions[3][0] = 3;
        directions[3][1] = 1;

        directions[1][0] = 1;
        directions[1][1] = 3;
    }

    public int getGauche(int droit) {
        return directions[droit - 1][0];
    }

    public int getDroite(int droit) {
        return directions[droit - 1][1];
    }

}
