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

            pionJoueurList.add(new PionJoueur(0, 0, false, 6, 1));
            pionJoueurList.add(new PionJoueur(0, 0, false, 4, 3));
            pionJoueurList.add(new PionJoueur(0, 0, false, 3, 4));
            pionJoueurList.add(new PionJoueur(0, 0, false, 2, 5));

            listJoueur.get(i).setPions(pionJoueurList);
        }

        start();

    }

    /*
    * C'est la méthode principale pour commencer une partie du jeu ! 
     */
    public void start() {
        this.p = new Plateau();
        placerPionJoueur();
        p.print();

    }

    /*
    * Méthode permet le joueur de mettre les pions sur le plateau
     */
    public void placerPionJoueur() {

        for (int i = 0; i < this.listJoueur.size(); i++) {
            for (int j = 0; j < this.listJoueur.get(i).getPions().size(); j++) {

                Scanner scX = new Scanner(System.in);
                System.out.println("Veuillez entrer --- Y --- du pion " + (j + 1));
                int xScanne = scX.nextInt();
                while ((xScanne < 0) || (xScanne > this.p.getHauteur())) {
                    System.out.println("Veuillez entrer une valeur vrai pour le pion " + (j + 1));
                    xScanne = scX.nextInt();
                }
                this.listJoueur.get(i).getPions().get(i).setX(xScanne);

                Scanner scY = new Scanner(System.in);
                System.out.println("Veuillez entrer --- X --- du pion " + (j + 1));
                int yScanne = scY.nextInt();
                while ((yScanne < 0) || (yScanne > this.p.getLargeur())) {
                    System.out.println("Veuillez entrer une valeur vrai pour le pion " + (j + 1));
                    yScanne = scY.nextInt();
                }
                this.listJoueur.get(i).getPions().get(i).setY(yScanne);

                this.p.setObjet(xScanne-1, yScanne, this.listJoueur.get(i).getPions().get(i));
                this.p.print();
            }
        }
    }

    public void placerUnPion() {

        Scanner scX = new Scanner(System.in);
        System.out.println("Veuillez entrer l'X du pion ");
        int xScanne = scX.nextInt();
        while ((xScanne < 0) || (xScanne > this.p.getLargeur())) {
            System.out.println("Veuillez entrer une valeur vrai pour le pion ");
            xScanne = scX.nextInt();
        }
        this.listJoueur.get(0).getPions().get(0).setX(xScanne);

        Scanner scY = new Scanner(System.in);
        System.out.println("Veuillez entrer l'Y du pion ");
        int yScanne = scY.nextInt();
        while ((yScanne < 0) || (yScanne > this.p.getHauteur())) {
            System.out.println("Veuillez entrer une valeur vrai pour le pion ");
            yScanne = scY.nextInt();
        }
        this.listJoueur.get(0).getPions().get(0).setY(yScanne);

    }


    public void mettreLesJoueursSurPlateau() {
        for (int i = 0; i < this.listJoueur.size(); i++) {
            for (int j = 0; j < this.listJoueur.get(i).getPions().size(); j++) {
                this.p.setObjet(this.listJoueur.get(i).getPions().get(j).getY(), this.listJoueur.get(i).getPions().get(j).getX(), this.listJoueur.get(i).getPions().get(j));
            }
        }
    }
}
