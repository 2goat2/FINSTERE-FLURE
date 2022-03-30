/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finstere.flure;

/**
 *
 * @author nadim
 * La classe Mur extends Espace pour initialiser le contour du plateau comme des espace vide occupées
 */
public class Mur extends Espace{
    
    //Coordonées
    private int x, y;
    
    //l'affichage 
    private String mur = "";
    
    //boolean pour init le mur comme des espaces occupées
    private final boolean s = true;
    
    //Constructer
    public Mur(boolean s){
       super(s);
       super.setAffichage(mur);
    }
    
}
