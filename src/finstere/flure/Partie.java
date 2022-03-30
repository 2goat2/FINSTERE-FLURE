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

    //Le joueur gagnant de ce partie
    private Joueur gagnant;

    //La liste des pions gagnants dans cette partie
    private ArrayList<PionJoueur> piongagnant;

    //La liste d'obstacles dans cette partie
    private ArrayList<Obstacle> obstacle;

    //le monstre dans cette partie
    private PionMonstre monstre;

    //l'espace de début du tour
    private Espace EspaceDeDebut;

    //La manche
    private boolean manche = true; // TRUE == Manche 1 & FALSE == Manche 2

    //boolean pour savoir si la partie est terminée
    private boolean finish = false;

    //Constructeur sans paramètres permet de déclarer les listes de la partie et le gagnant.
    public Partie() {

        listJoueur = new ArrayList<>();
        pionperdu = new ArrayList<>();
        piongagnant = new ArrayList<>();
        obstacle = new ArrayList<>();
        gagnant = new Joueur();

    }

    /*
    * Méthode pour init les joueurs avant de commencer la partie
    * permettant d'initiailiser les pions pour chaque joueur. 
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

            pionJoueurList.add(new PionJoueur(16, 11, false, 6, 1, i));
            pionJoueurList.add(new PionJoueur(16, 11, false, 4, 3, i));
            pionJoueurList.add(new PionJoueur(16, 11, false, 3, 4, i));
            pionJoueurList.add(new PionJoueur(16, 11, false, 2, 5, i));

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

        //set new Plateau()
        this.setP(new Plateau());

        //set new Monstre(1, 1, 2, this.getP(), this);
        this.setMonstre(new PionMonstre(1, 1, 2, this.getP(), this));

        mettreLeMontreSurPlateau();

        placerPionJoueur();

        //Le tour termine quand il n'y a plus de PionJoueur sur le plateau.
        while (!isFinish() || getFinish()) {

            System.out.println("mouvement");
            this.getMonstre().deplacer(5);

            deplacerPionJoueur();

            this.gagnant = this.gagnant(this.getListJoueur());

            System.out.println("liste de joueurs : " + this.getListJoueur().toString());
            System.out.println("joueur gagnant : " + this.gagnant.getNom());
            System.out.println("liste de Pions perdus : " + this.getPionperdu().toString());
            System.out.println("liste de Pions gagnants : " + this.piongagnant.toString());

        }

        //JOueur Encoree
    }


    /*
    * Méthode permet le joueur de mettre les pions sur le plateau commanceant par l'espace de début
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
                this.setManche(!this.isManche());

            }
        }

    }

    /*
    * Méthode qui permet au joueur de déplacer ou de sortir les pions du plateau 
     */
    private void deplacerPionJoueur() {

        for (int i = 0; i < this.getListJoueur().size(); i++) {
            for (int j = 0; j < this.getListJoueur().get(i).getPions().size(); j++) {

                this.getListJoueur().get(i).getPions().get(j).flipValeurActuelle();

                Scanner scX = new Scanner(System.in);
                Scanner scY = new Scanner(System.in);
                int xScanne = -1;
                int yScanne = -1;

                do {

                    if (this.getListJoueur().get(i).getPions().get(j).getX() == 1 && this.getListJoueur().get(i).getPions().get(j).getY() == 1) {
                        System.out.println(" Félicitation ! vous avez arriver à la sortie :) ");
                        System.out.println("$$$ Pour Sortir du plateau et gagner la partie, il faut aller à la case (0;0) $$$ ");
                        System.out.println("Veuillez entrer --- Y --- du pion " + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.getListJoueur().get(i).getNom());
                        xScanne = scX.nextInt();
                        while ((xScanne < 0) || (xScanne > this.getP().getHauteur())) {
                            System.out.println("Veuillez entrer une valeur vrai entre (1 et 11) pour l'Y du pion " + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.getListJoueur().get(i).getNom());
                            xScanne = scX.nextInt();
                        }

                        System.out.println("Veuillez entrer --- X --- du pion " + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.getListJoueur().get(i).getNom());
                        yScanne = scY.nextInt();
                        while ((yScanne < 0) || (yScanne > this.getP().getLargeur())) {
                            System.out.println("Veuillez entrer une valeur vrai entre (1 et 16) pour l'X du pion " + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceClaire() + "/" + this.getListJoueur().get(i).getPions().get(j).getValeurDeFaceFonce() + " pour : " + this.getListJoueur().get(i).getNom());
                            yScanne = scY.nextInt();
                        }

                        break;
                    }

                    if (this.getListJoueur().get(i).getPions().get(j).getY() == 0) {
                        xScanne = 0;
                        yScanne = this.getListJoueur().get(i).getPions().get(j).getX();
                        break;
                    }

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

                if (xScanne != 0 || yScanne != 0) {

                    this.getListJoueur().get(i).getPions().get(j).setX(xScanne);
                    this.getListJoueur().get(i).getPions().get(j).setY(yScanne);
                    this.getP().setObjet(xScanne, yScanne, this.getListJoueur().get(i).getPions().get(j), this.getListJoueur().get(i));

                } else if (xScanne == 0 && yScanne == 0) {

                    this.piongagnant.add(this.getListJoueur().get(i).getPions().get(j));
                    this.p.getPlateau()[1][1].supprimerObject();
                    this.listJoueur.get(i).getPions().remove(j);
                }

                this.getP().print();
                System.out.println(this.piongagnant.toString());

                this.setManche(!this.isManche());
            }
        }

    }

    /**
     * @param l la liste de joueur
     * @return Retourne le joueur gagnant de la liste l
     */
    private Joueur gagnant(ArrayList<Joueur> l) {

        int pions1 = 0;
        int pions2 = 0;

        for (int i = 0; i < this.piongagnant.size(); i++) {
            if (this.piongagnant.get(i).getNumJoueur() == 0) {
                pions1 += 1;
            } else if (this.piongagnant.get(i).getNumJoueur() == 1) {
                pions2 += 1;
            }
        }

        if (pions1 >= 3) {
            return l.get(0);
        } else if (pions1 >= 3) {
            return l.get(1);
        } else {
            if (pions1 == pions2) {
                return l.get(0);
            } else if (pions1 > pions2) {
                return l.get(0);
            } else {
                return l.get(1);
            }
        }

    }

    //Methode qui permet au monstre à se mettre sur le plateau
    private void mettreLeMontreSurPlateau() {
        System.out.println("\n");
        System.out.println(" !!!!!!!!!!!!!!!!!!!!!!!!!!! ");
        System.out.println(" !!! Le monstre est venu !!! ");
        System.out.println(" !!!!!!!!!!!!!!!!!!!!!!!!!!! ");
        System.out.println("\n");
        this.getP().setObjet(this.getMonstre().getY(), this.getMonstre().getX(), this.getMonstre());
        this.getP().print();
    }

    //Méthode qui retourne true si il y a pas des pions sur le plateau, sinon false
    private boolean isFinish() {
        int nom = 0;

        for (int i = 0; i < this.getListJoueur().size(); i++) {
            if (this.getListJoueur().get(i).getPions().size() == 0) {
                nom += 1;
            }
        }
        return nom == this.getListJoueur().size();
    }

    //Retourne listJoueur to String
    private String listJoueurToString() {
        ArrayList<String> jNoms = new ArrayList<>();
        for (Joueur j : this.getListJoueur()) {
            jNoms.add(j.getNom());
        }

        return jNoms.toString();
    }

    /**
     * Verifier les deplacément des objets
     *
     * @param x la nouvelle X
     * @param y la nouvelle Y
     * @param obj l'objet qui se déplace
     * @return Retourne true si l'objet a été bien se déplacé, sinon false
     */
    private boolean deplacerEtVerifierUnObjetDansUneCase(int x, int y, Object obj) {

        if (this.getP().getPlateau()[x][y].isOccupee()) {
            //Ajouter Des conditions pour chaque objet !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            System.out.println("Case occupée!\n");
            return true;
        } else if (obj.getClass().equals(PionJoueur.class)) {
            PionJoueur pionJoueur = (PionJoueur) obj;
            if (pionJoueur.getX() != y && pionJoueur.getY() != x) {
                System.out.println("Voulez-vous rester sur la même case ? (oui/non)");
                Scanner sc = new Scanner(System.in);
                String s = sc.nextLine();
                return "oui".equals(s);
            }
        } else {
            return false;
        }
        return false;
    }

    
    //GETTERS ET SETTERS
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
        return isManche();
    }

    /**
     * @param manche the manche to set
     */
    public void setManche(boolean manche) {
        this.manche = manche;
    }

    /**
     * @return the manche
     */
    public boolean isManche() {
        return manche;
    }

    /**
     * @return the finish
     */
    public boolean getFinish() {
        return finish;
    }

    /**
     * @param finish the finish to set
     */
    public void setFinish(boolean finish) {
        this.finish = finish;
    }

}
