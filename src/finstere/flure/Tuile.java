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
public class Tuile {

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

    private int mouvement;

    public Tuile(int m){
        this.mouvement = m;
    }

    public Tuile(){
    }
    
}
