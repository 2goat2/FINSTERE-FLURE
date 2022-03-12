/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finstere.flure;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe Partie qui gére et lance une partie du jeu
 *
 * @author nadim
 */
public class Partie {

    //Plateau de la partie
    private Plateau p;

    //La liste de joueurs dans cette partie 
    private ArrayList<Joueur> listJoueur;

    //La liste des pions morts dans cette partie
    private ArrayList<PionJoueur> pionperdu;

    //La liste d'obstacles dans cette partie
    private ArrayList<Obstacle> obstacle;

    //le monstre dans cette partie
    private final PionMonstre monstre = new PionMonstre(0, 0, 3);

    
    //Constructeur sans paramètres permet de déclarer les liste de la partie
    public Partie() {
        
        listJoueur = new ArrayList<>();

    }

    
    /*
    * Méthode pour init les joueurs avant de commencer la partie
    * permettant d'initiailiser les pions pour chaque joueur en plus! 
    */
    public void initJoueur() {

        for (int i = 0; i < 2; i++) {

            Scanner sc = new Scanner(System.in);
            String nom = "";
            while ((nom == "") || (nom == "\n")) {
                System.out.println("Veuillez entrer le nom du joueur " + (i + 1));
                nom = sc.nextLine();
            }

            listJoueur.add(new Joueur(nom));

            ArrayList<PionJoueur> pionJoueurList = new ArrayList<>();

            pionJoueurList.add(new PionJoueur(false, 6, 1));
            pionJoueurList.add(new PionJoueur(false, 4, 3));
            pionJoueurList.add(new PionJoueur(false, 3, 4));
            pionJoueurList.add(new PionJoueur(false, 2, 5));

            listJoueur.get(i).setPions(pionJoueurList);
        }
        
        start();

    }
    
    
    /*
    * C'est la méthode principale pour commencer une partie du jeu ! 
    */
    public void start() {
        this.p = new Plateau();

    }
}
