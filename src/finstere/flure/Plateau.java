/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finstere.flure;

import java.util.Arrays;

/**
 * Class Plateau qui gére l'environment du jeu
 *
 * @author nadim
 */
public class Plateau {

    private final int hauteur = 11;
    private final int largeur = 16;

    /**
     * Variable qui répresente le plateau 1 => pour dire que cet index n’est pas
     * occupé 0 => pour dire que cet index est occupé
     */
    private final int[][] booleanPlateau = {
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

    private final Espace[][] plateau = new Espace[hauteur][largeur];

    public Plateau() {

        initPlateau();

    }

    /**
     * Méthode pour initialiser un plateau des espace occupés ou pas par rapport
     * au boonleanPlateau
     */
    private void initPlateau() {

        Espace t = new Espace(true);
        Espace f = new Espace(false);

        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                if (this.booleanPlateau[i][j] == 1) {
                    this.plateau[i][j] = t;
                    //System.out.println(this.plateau[i][j].toString());
                    //System.out.println(Arrays.toString(this.plateau[i]));
                } else {
                    this.plateau[i][j] = f;
                    //System.out.println(this.plateau[i][j].toString());

                }
            }
        }

        for (int i = 0; i < hauteur; i++) {
            System.out.println(Arrays.toString(this.plateau[i]));
        }
    }

}
