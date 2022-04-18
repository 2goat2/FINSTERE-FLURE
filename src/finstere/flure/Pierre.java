/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finstere.flure;

/**
 *
 * @author nadim
 * Classe Pierre extends la classe Obstacle
 */

public class Pierre{

    private int x;//
    private int y;//
    
    private final Plateau plateau;
    
    public String imgSource = FinFlureGUI.chemin + "mur.png";

    public Pierre(int x, int y, Plateau plateau) {
        this.x = x;
        this.y = y;
        this.plateau = plateau;
    }


    //GETTERS ET SETTERS
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

}
