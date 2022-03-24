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
    private PionMonstre monstre;

    private Espace EspaceDeDebut;

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
            while (("".equals(nom)) || ("\n".equals(nom))) {
                System.out.println("Veuillez entrer le nom du joueur " + (i + 1));
                nom = sc.nextLine();
            }

            listJoueur.add(new Joueur(nom));

            ArrayList<PionJoueur> pionJoueurList = new ArrayList<>();

            pionJoueurList.add(new PionJoueur(16, 11, false, 6, 1));
            pionJoueurList.add(new PionJoueur(16, 11, false, 4, 3));
            pionJoueurList.add(new PionJoueur(16, 11, false, 3, 4));
            pionJoueurList.add(new PionJoueur(16, 11, false, 2, 5));

            listJoueur.get(i).setPions(pionJoueurList);
        }

        System.out.println("Les joueurs : " + listJoueurToString());
        start();

    }

    /*
    * C'est la méthode principale pour commencer une partie du jeu ! 
     */
    public void start() {
        System.out.println("\n");
        System.out.println("^ Plateau du jeu ^");
        this.p = new Plateau();
        this.monstre = new PionMonstre(1, 1, 2, this.p);
        mettreLeMontreSurPlateau();

        placerPionJoueur();

        p.print();
        this.monstre.deplacer(6);


        p.print();
        
        deplacerPionJoueur();

        p.print();

        this.monstre.deplacer(4);

    }


    /*
    * Méthode permet le joueur de mettre les pions sur le plateau
     */
    private void placerPionJoueur() {

        for (int i = 0; i < this.listJoueur.size(); i++) {
            for (int j = 0; j < this.listJoueur.get(i).getPions().size(); j++) {

                Scanner scX = new Scanner(System.in);
                Scanner scY = new Scanner(System.in);
                int xScanne = 1;
                int yScanne = 1;

                do {

                    System.out.println("Le pion " + this.listJoueur.get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.listJoueur.get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.listJoueur.get(i).getNom() + " est hors le plateau!");
                    System.out.println(this.listJoueur.get(i).getPions().get(j).getValeurActuelle() + " cases possible pour ce pion.");

                    System.out.println("Veuillez entrer --- Y --- du pion " + this.listJoueur.get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.listJoueur.get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.listJoueur.get(i).getNom());
                    xScanne = scX.nextInt();

                    while ((xScanne < 1) || (xScanne > this.p.getHauteur())) {
                        System.out.println("Veuillez entrer une valeur vrai entre (1 et 11) pour l'Y du pion " + this.listJoueur.get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.listJoueur.get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.listJoueur.get(i).getNom());
                        xScanne = scX.nextInt();
                    }

                    while (xScanne != this.listJoueur.get(i).getPions().get(j).getEspaceDeCommencer().getY()) {
                        System.out.println("Le pion doit commencer par la case : " + this.listJoueur.get(i).getPions().get(j).getEspaceDeCommencer().getX() + "-" + this.listJoueur.get(i).getPions().get(j).getEspaceDeCommencer().getY());
                        xScanne = scX.nextInt();
                    }

                    System.out.println("Veuillez entrer --- X --- du pion " + this.listJoueur.get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.listJoueur.get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.listJoueur.get(i).getNom());
                    yScanne = scY.nextInt();
                    while ((yScanne < 0) || (yScanne > this.p.getLargeur())) {
                        System.out.println("Veuillez entrer une valeur vrai entre (1 et 16) pour l'X du pion " + this.listJoueur.get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.listJoueur.get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.listJoueur.get(i).getNom());
                        yScanne = scY.nextInt();
                    }
                    while (yScanne != this.listJoueur.get(i).getPions().get(j).getEspaceDeCommencer().getX()) {
                        System.out.println("Le pion doit commencer par la case : " + this.listJoueur.get(i).getPions().get(j).getEspaceDeCommencer().getX() + "-" + this.listJoueur.get(i).getPions().get(j).getEspaceDeCommencer().getY());
                        yScanne = scY.nextInt();
                    }

                } while (deplacerEtVerifierUnObjetDansUneCase(xScanne - 1, yScanne, this.listJoueur.get(i).getPions().get(j)));

                this.listJoueur.get(i).getPions().get(j).setX(xScanne);
                this.listJoueur.get(i).getPions().get(j).setY(yScanne);

                this.p.setObjet(xScanne - 1, yScanne, this.listJoueur.get(i).getPions().get(j), this.listJoueur.get(i));

                this.p.print();

                this.p.getPlateau()[xScanne - 1][yScanne] = new Espace(false);

                do {

                    System.out.println(this.listJoueur.get(i).getPions().get(j).getValeurActuelle() - 1 + " cases possible pour ce pion.");

                    System.out.println("Veuillez entrer --- Y --- du pion " + this.listJoueur.get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.listJoueur.get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.listJoueur.get(i).getNom());
                    xScanne = scX.nextInt();

                    while ((xScanne < 1) || (xScanne > this.p.getHauteur())) {
                        System.out.println("Veuillez entrer une valeur vrai entre (1 et 11) pour l'Y du pion " + this.listJoueur.get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.listJoueur.get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.listJoueur.get(i).getNom());
                        xScanne = scX.nextInt();
                    }

                    System.out.println("Veuillez entrer --- X --- du pion " + this.listJoueur.get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.listJoueur.get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.listJoueur.get(i).getNom());
                    yScanne = scY.nextInt();
                    while ((yScanne < 0) || (yScanne > this.p.getLargeur())) {
                        System.out.println("Veuillez entrer une valeur vrai entre (1 et 16) pour l'X du pion " + this.listJoueur.get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.listJoueur.get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.listJoueur.get(i).getNom());
                        yScanne = scY.nextInt();
                    }

                } while (deplacerEtVerifierUnObjetDansUneCase(xScanne - 1, yScanne, this.listJoueur.get(i).getPions().get(j)));

                this.listJoueur.get(i).getPions().get(j).setX(xScanne);
                this.listJoueur.get(i).getPions().get(j).setY(yScanne);

                System.out.println("Ancien2 " + this.p.getPlateau()[xScanne - 1][yScanne].isOccupee());
                this.p.setObjet(xScanne - 1, yScanne, this.listJoueur.get(i).getPions().get(j), this.listJoueur.get(i));
                System.out.println("new " + this.p.getPlateau()[xScanne - 1][yScanne].isOccupee());

                this.p.print();

            }
        }

    }

    private void deplacerPionJoueur() {

        for (int i = 0; i < this.listJoueur.size(); i++) {
            for (int j = 0; j < this.listJoueur.get(i).getPions().size(); j++) {

                this.listJoueur.get(i).getPions().get(j).flipValeurActuelle();

                Scanner scX = new Scanner(System.in);
                Scanner scY = new Scanner(System.in);
                int xScanne;
                int yScanne;

                do {

                    System.out.println(this.listJoueur.get(i).getPions().get(j).getValeurActuelle() + " cases possible pour ce pion.");
                    System.out.println(" Place actuelle pour ce pion : " + this.listJoueur.get(i).getPions().get(j).getX() + " | " + this.listJoueur.get(i).getPions().get(j).getY());

                    System.out.println("Veuillez entrer --- Y --- du pion " + this.listJoueur.get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.listJoueur.get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.listJoueur.get(i).getNom());
                    xScanne = scX.nextInt();

                    while ((xScanne < 1) || (xScanne > this.p.getHauteur())) {
                        System.out.println("Veuillez entrer une valeur vrai entre (1 et 11) pour l'Y du pion " + this.listJoueur.get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.listJoueur.get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.listJoueur.get(i).getNom());
                        xScanne = scX.nextInt();
                    }

                    System.out.println("Veuillez entrer --- X --- du pion " + this.listJoueur.get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.listJoueur.get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.listJoueur.get(i).getNom());
                    yScanne = scY.nextInt();
                    while ((yScanne < 0) || (yScanne > this.p.getLargeur())) {
                        System.out.println("Veuillez entrer une valeur vrai entre (1 et 16) pour l'X du pion " + this.listJoueur.get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.listJoueur.get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.listJoueur.get(i).getNom());
                        yScanne = scY.nextInt();
                    }

                } while (deplacerEtVerifierUnObjetDansUneCase(xScanne - 1, yScanne, this.listJoueur.get(i).getPions().get(j)));

                this.p.getPlateau()[this.listJoueur.get(i).getPions().get(j).getX() - 1][this.listJoueur.get(i).getPions().get(j).getY()] = new Espace(false);

                this.listJoueur.get(i).getPions().get(j).setX(xScanne);
                this.listJoueur.get(i).getPions().get(j).setY(yScanne);

                System.out.println("Ancien " + this.p.getPlateau()[xScanne - 1][yScanne].isOccupee());
                this.p.setObjet(xScanne - 1, yScanne, this.listJoueur.get(i).getPions().get(j), this.listJoueur.get(i));
                System.out.println("new " + this.p.getPlateau()[xScanne - 1][yScanne].isOccupee());

                this.p.print();

            }
        }

    }

    private void mettreLeMontreSurPlateau() {
        System.out.println("\n");
        System.out.println(" !!!!!!!!!!!!!!!!!!!!!!!!!!! ");
        System.out.println(" !!! Le monstre est venu !!! ");
        System.out.println(" !!!!!!!!!!!!!!!!!!!!!!!!!!! ");
        System.out.println("\n");
        this.p.setObjet(this.monstre.getY() - 1, this.monstre.getX(), this.monstre);
        this.p.print();
    }


    private String listJoueurToString() {
        ArrayList<String> jNoms = new ArrayList<>();
        for (Joueur j : this.listJoueur) {
            jNoms.add(j.getNom());
        }

        return jNoms.toString();
    }

    private boolean deplacerEtVerifierUnObjetDansUneCase(int x, int y, Object obj) {

        if (this.p.getPlateau()[x][y].isOccupee()) {
            //Ajouter Des conditions pour chaque objet !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            System.out.println("Case occupée!\n");
            return true;
        } else if (obj.getClass().equals(PionJoueur.class)) {
            PionJoueur pionJoueur = (PionJoueur) obj;
            if (pionJoueur.getX() == y && pionJoueur.getY() == x) {
                System.out.println("Voulez-vous rester sur la même case ? (oui/non)");
                Scanner sc = new Scanner(System.in);
                String s = sc.nextLine();
                return "oui".equals(s);
            }// else {
            //   return !((x > pionJoueur.getX() + 1 || x < pionJoueur.getX() - 1) && (y > pionJoueur.getY() + 1 || y < pionJoueur.getY() - 1));
            //}
        } else {
            return false;
        }
        return false;
    }

}
