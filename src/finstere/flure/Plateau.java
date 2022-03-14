/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finstere.flure;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Class Plateau qui gére l'environment du jeu
 *
 * @author nadim
 */
public final class Plateau {

    private int hauteur = 11;
    private int largeur = 17; // 1( pour afficher l'index) + 16(La taille du plateau normal)

    /**
     * Matrice qui répresente le plateau :
     *
     * 2 => pour afficher l'index de chaque ligne, 1 => pour dire que cet index
     * n’est pas occupé, 0 => pour dire que cet index est occupé.
     *
     */
    private int[][] booleanPlateau = {
        {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
        {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
        {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
        {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
        {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {2, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {2, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {2, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {2, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

    private Espace[][] plateau = new Espace[hauteur][largeur];

    public Plateau() {

        initPlateau();

    }

    /**
     * La Méthode pour initialiser un plateau des espace occupés ou pas par
     * rapport au boonleanPlateau
     */
    private void initPlateau() {

        System.out.println("\n");

        for (int i = 0; i < getHauteur(); i++) {
            for (int j = 0; j < getLargeur(); j++) {

                if (this.getBooleanPlateau()[i][j] == 2) {
                    this.getPlateau()[i][j] = new Espace(true);
                    this.getPlateau()[i][j].setAffichage(i + 1);
                } else if (this.getBooleanPlateau()[i][j] == 1) {
                    this.getPlateau()[i][j] = new Espace(false);
                    //System.out.println(this.plateau[i][j].toString());
                    //System.out.println(Arrays.toString(this.plateau[i]));
                } else {
                    this.getPlateau()[i][j] = new Espace(true);
                    //System.out.println(this.plateau[i][j].toString());

                }
            }
        }

        /*
        * Afficher un ligne des integers correspondant à chaque index de chaque colone.
        * avant d'afficher le plateau.
         */
        String[] tabIndex = new String[getLargeur()];
        tabIndex[0] = "Y/X";
        for (int i = 1; i < getLargeur(); i++) {
            tabIndex[i] = " " + i + " ";
        }

        //Affichage modifiée!
        System.out.println(Arrays.toString(tabIndex).replace("[", "").replace("]", "").replace(",", "-").replace("  10 -  11 -  12 -  13 -  14 -  15 -  16 ", " 10 - 11 - 12 - 13 - 14 - 15 - 16").replace("  1", " 1").replace("X/Y-", "X/Y "));

        //Affichage modifiée!
        for (int i = 0; i < getHauteur(); i++) {
            System.out.println(Arrays.toString(this.getPlateau()[i]).replace("[", "").replace("]", "").replace(",", "-").replace("10- ", "10-").replace("11- ", "11-").replace("X/Y-", "X/Y "));
        }
    }

    /**
     * La méthode setObjet permet d'ajouter un objet dans une case specifique
     * sur le plateau
     *
     * @param x
     * @param y
     * @param p
     */
    public boolean setObjet(int x, int y, Object obj) {
        return this.plateau[x][y].ajouterObjet(obj);

    }

    /*
     Les Méthodes : moveupObjet, moveDownObjet, moveLeftObjet, moveRightObjet
        Pour déplacer un objet une case sur le plateau.
    */
    public boolean moveupObjet(int x, int y, Object obj) {
        return this.setObjet(x+1, y, obj)
                && this.plateau[x][y].supprimerObject();
    }

    public boolean moveDownObjet(int x, int y, Object obj) {
        return this.setObjet(x-1, y, obj)
                && this.plateau[x][y].supprimerObject();
    }

    public boolean moveLeftObjet(int x, int y, Object obj) {
        return this.setObjet(x, y-1, obj)
                && this.plateau[x][y].supprimerObject();
    }

    public boolean moveRightObjet(int x, int y, Object obj) {
        return this.setObjet(x, y+1, obj)
                && this.plateau[x][y].supprimerObject();
    }

    /**
     * @return the hauteur
     */
    public int getHauteur() {
        return hauteur;
    }

    /**
     * @param hauteur the hauteur to set
     */
    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    /**
     * @return the largeur
     */
    public int getLargeur() {
        return largeur;
    }

    /**
     * @param largeur the largeur to set
     */
    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    /**
     * @return the booleanPlateau
     */
    public int[][] getBooleanPlateau() {
        return booleanPlateau;
    }

    /**
     * @param booleanPlateau the booleanPlateau to set
     */
    public void setBooleanPlateau(int[][] booleanPlateau) {
        this.booleanPlateau = booleanPlateau;
    }

    /**
     * @return the plateau
     */
    public Espace[][] getPlateau() {
        return plateau;
    }

    /**
     * @param plateau the plateau to set.
     */
    public void setPlateau(Espace[][] plateau) {
        this.plateau = plateau;
    }

    /**
     * Méthode pour afficher le plateau.
     */
    public void print() {

        String[] tabIndex = new String[getLargeur()];
        tabIndex[0] = "X/Y";
        for (int i = 1; i < getLargeur(); i++) {
            tabIndex[i] = " " + i + " ";
        }

        System.out.println(Arrays.toString(tabIndex).replace("[", "").replace("]", "").replace(",", "-").replace("  10 -  11 -  12 -  13 -  14 -  15 -  16 ", " 10 - 11 - 12 - 13 - 14 - 15 - 16").replace("  1", " 1").replace("X/Y-", "X/Y "));

        //Affichage modifiée!
        for (int i = 0; i < getHauteur(); i++) {
            System.out.println(Arrays.toString(this.getPlateau()[i]).replace("[", "").replace("]", "").replace(",", "-").replace("10- ", "10-").replace("11- ", "11-").replace("X/Y-", "X/Y "));
        }

        System.out.println("\n");

    }

}
