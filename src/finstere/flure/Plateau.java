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

    /**
     * Variable qui répresente le plateau 1 => pour dire que cet index n’est pas
     * occupé 0 => pour dire que cet index est occupé
     */
    private final int[][] booleanPlateau = {
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
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

    private final Espace[][] plateau = new Espace[16][12];

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

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 16; j++) {
                if (this.booleanPlateau[i][j] == 1) {
                    this.plateau[i][j] = f;
                    System.out.println(this.plateau[i][j].toString());
                } else {
                    this.plateau[i][j] = t;
                    System.out.println(this.plateau[i][j].toString());

                }
            }
        }
        
        System.out.println(Arrays.deepToString(this.plateau));
    }

}
