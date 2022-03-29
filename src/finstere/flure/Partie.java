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

    private boolean manche = true; // TRUE == Manche 1 & FALSE == Manche 2

    //Constructeur sans paramètres permet de déclarer les liste de la partie
    public Partie() {

        listJoueur = new ArrayList<>();
        pionperdu = new ArrayList<>();
        obstacle = new ArrayList<>();
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

            getListJoueur().add(new Joueur(nom));

            ArrayList<PionJoueur> pionJoueurList = new ArrayList<>();

            pionJoueurList.add(new PionJoueur(16, 11, false, 6, 1 ,i+1));
         //   pionJoueurList.add(new PionJoueur(16, 11, false, 4, 3, i+1));
         //   pionJoueurList.add(new PionJoueur(16, 11, false, 3, 4, i+1));
         //   pionJoueurList.add(new PionJoueur(16, 11, false, 2, 5, i+1));

            getListJoueur().get(i).setPions(pionJoueurList);
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
        this.setP(new Plateau());
        this.setMonstre(new PionMonstre(1, 1, 2, this.getP(), this));
        mettreLeMontreSurPlateau();

        placerPionJoueur();

     //   p.print();
        System.out.println("mouvemen1");
        this.getMonstre().deplacer(10);


        getP().print();
        
        deplacerPionJoueur();
        this.getMonstre().deplacer(10);
        System.out.println(this.getMonstre().getPionsTues());

        deplacerPionJoueur();
        this.getMonstre().deplacer(2);
        System.out.println(this.getMonstre().getPionsTues());
    }


    /*
    * Méthode permet le joueur de mettre les pions sur le plateau
     */
    private void placerPionJoueur() {

        for (int i = 0; i < this.getListJoueur().size(); i++) {
            for (int j = 0; j < this.getListJoueur().get(i).getPions().size(); j++) {

                Scanner scX = new Scanner(System.in);
                Scanner scY = new Scanner(System.in);
                int xScanne = 1;
                int yScanne = 1;

                do {

                    System.out.println("Le pion " + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.getListJoueur().get(i).getNom() + " est hors le plateau!");
                    System.out.println(this.getListJoueur().get(i).getPions().get(j).getValeurActuelle() + " cases possible pour ce pion.");

                    System.out.println("Veuillez entrer --- Y --- du pion " + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.getListJoueur().get(i).getNom());
                    xScanne = scX.nextInt();

                    while ((xScanne < 1) || (xScanne > this.getP().getHauteur())) {
                        System.out.println("Veuillez entrer une valeur vrai entre (1 et 11) pour l'Y du pion " + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.getListJoueur().get(i).getNom());
                        xScanne = scX.nextInt();
                    }

                    while (xScanne != this.getListJoueur().get(i).getPions().get(j).getEspaceDeCommencer().getY()) {
                        System.out.println("Le pion doit commencer par la case : " + this.getListJoueur().get(i).getPions().get(j).getEspaceDeCommencer().getX() + "-" + this.getListJoueur().get(i).getPions().get(j).getEspaceDeCommencer().getY());
                        xScanne = scX.nextInt();
                    }

                    System.out.println("Veuillez entrer --- X --- du pion " + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.getListJoueur().get(i).getNom());
                    yScanne = scY.nextInt();
                    while ((yScanne < 0) || (yScanne > this.getP().getLargeur())) {
                        System.out.println("Veuillez entrer une valeur vrai entre (1 et 16) pour l'X du pion " + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.getListJoueur().get(i).getNom());
                        yScanne = scY.nextInt();
                    }
                    while (yScanne != this.getListJoueur().get(i).getPions().get(j).getEspaceDeCommencer().getX()) {
                        System.out.println("Le pion doit commencer par la case : " + this.getListJoueur().get(i).getPions().get(j).getEspaceDeCommencer().getX() + "-" + this.getListJoueur().get(i).getPions().get(j).getEspaceDeCommencer().getY());
                        yScanne = scY.nextInt();
                    }

                } while (deplacerEtVerifierUnObjetDansUneCase(xScanne, yScanne, this.getListJoueur().get(i).getPions().get(j)));

                this.getListJoueur().get(i).getPions().get(j).setX(xScanne);
                this.getListJoueur().get(i).getPions().get(j).setY(yScanne);

                this.getP().setObjet(xScanne, yScanne, this.getListJoueur().get(i).getPions().get(j), this.getListJoueur().get(i));

                this.getP().print();

                this.p.getPlateau()[xScanne][yScanne] = new Espace(false);

                do {

                    System.out.println(this.getListJoueur().get(i).getPions().get(j).getValeurActuelle() - 1 + " cases possible pour ce pion.");

                    System.out.println("Veuillez entrer --- Y --- du pion " + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.getListJoueur().get(i).getNom());
                    xScanne = scX.nextInt();

                    while ((xScanne < 1) || (xScanne > this.getP().getHauteur())) {
                        System.out.println("Veuillez entrer une valeur vrai entre (1 et 11) pour l'Y du pion " + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.getListJoueur().get(i).getNom());
                        xScanne = scX.nextInt();
                    }

                    System.out.println("Veuillez entrer --- X --- du pion " + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.getListJoueur().get(i).getNom());
                    yScanne = scY.nextInt();
                    while ((yScanne < 0) || (yScanne > this.getP().getLargeur())) {
                        System.out.println("Veuillez entrer une valeur vrai entre (1 et 16) pour l'X du pion " + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.getListJoueur().get(i).getNom());
                        yScanne = scY.nextInt();
                    }

                } while (deplacerEtVerifierUnObjetDansUneCase(xScanne, yScanne, this.getListJoueur().get(i).getPions().get(j)));

                this.getListJoueur().get(i).getPions().get(j).setX(xScanne);
                this.getListJoueur().get(i).getPions().get(j).setY(yScanne);

                System.out.println("Ancien2 " + this.getP().getPlateau()[xScanne][yScanne].isOccupee());
                this.getP().setObjet(xScanne, yScanne, this.getListJoueur().get(i).getPions().get(j), this.getListJoueur().get(i));
                System.out.println("new " + this.getP().getPlateau()[xScanne][yScanne].isOccupee());

                this.getP().print();
                this.manche = !this.manche;

            }
        }

    }

    private void deplacerPionJoueur() {

        for (int i = 0; i < this.getListJoueur().size(); i++) {
            for (int j = 0; j < this.getListJoueur().get(i).getPions().size(); j++) {

                this.getListJoueur().get(i).getPions().get(j).flipValeurActuelle();

                Scanner scX = new Scanner(System.in);
                Scanner scY = new Scanner(System.in);
                int xScanne;
                int yScanne;

                do {

                    System.out.println(this.getListJoueur().get(i).getPions().get(j).getValeurActuelle() + " cases possible pour ce pion.");
                    System.out.println(" Place actuelle pour ce pion : " + this.getListJoueur().get(i).getPions().get(j).getX() + " | " + this.getListJoueur().get(i).getPions().get(j).getY());

                    System.out.println("Veuillez entrer --- Y --- du pion " + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.getListJoueur().get(i).getNom());
                    xScanne = scX.nextInt();

                    while ((xScanne < 1) || (xScanne > this.getP().getHauteur())) {
                        System.out.println("Veuillez entrer une valeur vrai entre (1 et 11) pour l'Y du pion " + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.getListJoueur().get(i).getNom());
                        xScanne = scX.nextInt();
                    }

                    System.out.println("Veuillez entrer --- X --- du pion " + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.getListJoueur().get(i).getNom());
                    yScanne = scY.nextInt();
                    while ((yScanne < 0) || (yScanne > this.getP().getLargeur())) {
                        System.out.println("Veuillez entrer une valeur vrai entre (1 et 16) pour l'X du pion " + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.getListJoueur().get(i).getNom());
                        yScanne = scY.nextInt();
                    }

                } while (deplacerEtVerifierUnObjetDansUneCase(xScanne, yScanne, this.getListJoueur().get(i).getPions().get(j)));

                this.p.getPlateau()[this.getListJoueur().get(i).getPions().get(j).getX()][this.getListJoueur().get(i).getPions().get(j).getY()] = new Espace(false);

                this.getListJoueur().get(i).getPions().get(j).setX(xScanne);
                this.getListJoueur().get(i).getPions().get(j).setY(yScanne);

                System.out.println("Ancien " + this.getP().getPlateau()[xScanne][yScanne].isOccupee());
                this.getP().setObjet(xScanne, yScanne, this.getListJoueur().get(i).getPions().get(j), this.getListJoueur().get(i));
                System.out.println("new " + this.getP().getPlateau()[xScanne][yScanne].isOccupee());

                this.getP().print();

                this.manche = !this.manche;
            }
        }

    }

    private void mettreLeMontreSurPlateau() {
        System.out.println("\n");
        System.out.println(" !!!!!!!!!!!!!!!!!!!!!!!!!!! ");
        System.out.println(" !!! Le monstre est venu !!! ");
        System.out.println(" !!!!!!!!!!!!!!!!!!!!!!!!!!! ");
        System.out.println("\n");
        this.getP().setObjet(this.getMonstre().getY(), this.getMonstre().getX(), this.getMonstre());
        this.getP().print();
    }


    private String listJoueurToString() {
        ArrayList<String> jNoms = new ArrayList<>();
        for (Joueur j : this.getListJoueur()) {
            jNoms.add(j.getNom());
        }

        return jNoms.toString();
    }

    private boolean deplacerEtVerifierUnObjetDansUneCase(int x, int y, Object obj) {

        if (this.getP().getPlateau()[x][y].isOccupee()) {
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

    /**
     * @return the p
     */
    public Plateau getP() {
        return p;
    }

    /**
     * @param p the p to set
     */
    public void setP(Plateau p) {
        this.p = p;
    }

    /**
     * @return the listJoueur
     */
    public ArrayList<Joueur> getListJoueur() {
        return listJoueur;
    }

    /**
     * @param listJoueur the listJoueur to set
     */
    public void setListJoueur(ArrayList<Joueur> listJoueur) {
        this.listJoueur = listJoueur;
    }

    /**
     * @return the pionperdu
     */
    public ArrayList<PionJoueur> getPionperdu() {
        return pionperdu;
    }

    /**
     * @param pionperdu the pionperdu to set
     */
    public void setPionperdu(ArrayList<PionJoueur> pionperdu) {
        this.pionperdu = pionperdu;
    }

    /**
     * @return the obstacle
     */
    public ArrayList<Obstacle> getObstacle() {
        return obstacle;
    }

    /**
     * @param obstacle the obstacle to set
     */
    public void setObstacle(ArrayList<Obstacle> obstacle) {
        this.obstacle = obstacle;
    }

    /**
     * @return the monstre
     */
    public PionMonstre getMonstre() {
        return monstre;
    }

    /**
     * @param monstre the monstre to set
     */
    public void setMonstre(PionMonstre monstre) {
        this.monstre = monstre;
    }

    /**
     * @return the EspaceDeDebut
     */
    public Espace getEspaceDeDebut() {
        return EspaceDeDebut;
    }

    /**
     * @param EspaceDeDebut the EspaceDeDebut to set
     */
    public void setEspaceDeDebut(Espace EspaceDeDebut) {
        this.EspaceDeDebut = EspaceDeDebut;
    }

    /**
     * @return the manche
     */
    public boolean getManche() {
        return manche;
    }

    /**
     * @param manche the manche to set
     */
    public void setManche(boolean manche) {
        this.manche = manche;
    }

}
