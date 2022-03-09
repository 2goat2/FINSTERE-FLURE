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
public class PionJoueur extends Pion{
    
    
    private String[] couleur = new String[]{"bleu","brun","gris","vert","violet","rouge","jaune"};//Tableau String des couleurs
    private boolean face;//true : face claire ; false : face foncée
    
    //Constructeur
    public PionJoueur(int x, int y, String[] c, boolean f){
        super(x,y);
        this.couleur=c;
        this.face=f;
    }
    
    public String[] getCouleur(){
        return this.couleur;
    }
    
    public String getFace(){
        if(this.face==true){
            return "claire";
        }else{
            return "false";
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
