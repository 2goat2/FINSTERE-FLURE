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
public abstract class Pion {

    // Les coordonées de chaque pion [ joueur - monstre ]
    private int x;
    private int y;
    
    //l'espace de commencer
    private Espace espaceDeCommencer;
    
    public Pion(int x,int y){
        this.x = x;
        this.y = y;
        this.espaceDeCommencer = new Espace(x,y,false);
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
     * @return the espaceDeCommencer
     */
    public Espace getEspaceDeCommencer() {
        return espaceDeCommencer;
    }

    /**
     * @param espaceDeCommencer the espaceDeCommencer to set
     */
    public void setEspaceDeCommencer(Espace espaceDeCommencer) {
        this.espaceDeCommencer = espaceDeCommencer;
    }
}
