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
public class Mur extends Espace{
    
    private int x,y;
    private String lettre = "A";
    private final boolean s = false;
    
    public Mur(int x, int y, boolean s){
       super(s);
       
    }
}