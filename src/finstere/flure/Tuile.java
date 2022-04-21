/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finstere.flure;

/**
 * La classe qui gére les tuiles qui donnent le monstre le nombre de ses
 * mouvements
 *
 * @author nadim
 */
public class Tuile {

    private int mouvement;
    public String imgSource;

    public Tuile(int m) {
        this.mouvement = m;
        this.imgSource = FinFlureGUI.chemin + "lauriers" + this.mouvement + ".gif";

    }

    /**
     * @return the mouvement
     */
    public int getMouvement() {
        return mouvement;
    }

}
