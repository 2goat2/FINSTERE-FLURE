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
public class PionJoueur {
    
    private int nombre;
    private String couleur;
    private boolean face;//true : face claire ; false : face foncée
    
    public void init(int n, String c, boolean f){
        
        this.nombre=n;
        this.couleur=c; 
        this.face=f;
    }
    
    
    public String getCouleur(){
        return couleur;
    }
    
    public String getFace(){
        if(this.face==true){
            return "claire";
        }else{
            return "foncé";
        }
    }
    
    public void setFace(boolean f){
        this.face=f;
    }
    
    @Override
    public String toString(){
        return getCouleur() + " face " + getFace();
    }
}
