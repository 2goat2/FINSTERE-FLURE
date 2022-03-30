/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finstere.flure;

/**
 *
 * @author nadim
 * La classe abstracte Obstacle hérite les classes : Pière , Pivot , Teleport
 */
public abstract class Obstacle {
    
    //Coordonées
    private int x,y;
    
    //Constructeur
    public Obstacle(int x, int y){
        this.x = x;
        this.y = y;
    }
}
