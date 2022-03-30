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

public class Pierre extends Obstacle {

    private int x;//
    private int y;//
    private int ax, ay;//précédent

    public Pierre(int x, int y) {
        super(x,y);
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

    /**
     * @return the ax
     */
    public int getAx() {
        return ax;
    }

    /**
     * @param ax the ax to set
     */
    public void setAx(int ax) {
        this.ax = ax;
    }

    /**
     * @return the ay
     */
    public int getAy() {
        return ay;
    }

    /**
     * @param ay the ay to set
     */
    public void setAy(int ay) {
        this.ay = ay;
    }
}
