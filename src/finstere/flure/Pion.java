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
public class Pion {
    
    private PionJoueur[] tousPionJoueur = new PionJoueur[50];
    private boolean face;
    
    private void initPionJoueur(){
        String[] tabcouleur = new String[]{"bleu","brun","gris","vert","violet","rouge","jaune"};//Tableau String des couleurs
        int[] tabnombre = new int[]{1,3,4,5};
        for(int i=0; i<7 ; i++){
            for(int j=0 ; j<3 ; j++){
                PionJoueur pj = new PionJoueur();
                pj.init(tabnombre[j],tabcouleur[i],this.face);
            }
        }
    }
    
    public String toString(){
        String res="";
        for(int i=0 ; i<tousPionJoueur.length ; i++){
            res+=tousPionJoueur[i];
            res += "\n";
        }
        return res;
    }
    
}
