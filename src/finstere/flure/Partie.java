/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finstere.flure;


import java.util.ArrayList;
import java.util.Objects;
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
    private ArrayList<Pierre> obstacle;

    //le monstre dans cette partie
    private PionMonstre monstre;

    private boolean premierDeplacementDeJoueurs;

    //l'espace de début du tour
    private Espace EspaceDeDebut;

    //La manche courante
    private boolean manche = false; // false == Manche 1 & true == Manche 2

    //boolean pour savoir si la partie est terminée
    private boolean finish = false;

    //Constructeur sans paramètres permet de déclarer les listes de la partie et le gagnant.
    public Partie() {

        listJoueur = new ArrayList<>();
        pionperdu = new ArrayList<>();
        piongagnant = new ArrayList<>();
        obstacle = new ArrayList<>();
        gagnant = new Joueur();

        this.premierDeplacementDeJoueurs = true;

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

        this.ajouterObstacles();

        //créer un paquet de tuiles
        //le paquet de tuiles qui deplace le monstre
        PaquetDeTuiles paquet = new PaquetDeTuiles();

        //Mettre le monstre sur le plateau
        mettreLeMontreSurPlateau();

        System.out.println("MANCHE 1");
        //Mettre les pions de joueurs sur le plateau
        placer2pionsAuDebut();

        //Le tour termine quand il n'y a plus de PionJoueur sur le plateau.
        while (!isFinish() || getFinish()) {

            System.out.println("mouvement");

            Tuile t = paquet.donnerTuile();
            if (this.isPremierDeplacementDeJoueurs()) {
                while (Objects.requireNonNull(t).getMouvement() == 1) {
                    t = paquet.donnerTuile();
                }

                this.getMonstre().deplacer(t.getMouvement());
                System.out.println(t.getMouvement());

            } else {

                if (Objects.requireNonNull(t).getMouvement() == 1) {

                    int m = 0;

                    while (!this.monstre.isaTue()) {

                        this.getMonstre().deplacer(t.getMouvement());

                        m+=1;
                        if (m > 20)
                            break;
                    }

                } else {

                    this.getMonstre().deplacer(t.getMouvement());
                    System.out.println(t.getMouvement());
                }
            }

            deplacerPionJoueur();

            this.setGagnant(this.gagnant(this.getListJoueur()));

        }

        System.out.println("liste de joueurs : " + this.getListJoueur().toString());
        System.out.println("joueur gagnant : " + this.getGagnant().getNom());
        System.out.println("liste de Pions perdus : " + this.getPionperdu().toString());
        System.out.println("liste de Pions gagnants : " + this.getPiongagnant());

    }

    public void ajouterObstacles(){

        this.getObstacle().add(new Pierre(4,3, this.getP()));
        this.getObstacle().add(new Pierre(5,8, this.getP()));
        this.getObstacle().add(new Pierre(6,10, this.getP()));
        this.getObstacle().add(new Pierre(7,7, this.getP()));
        this.getObstacle().add(new Pierre(8,5, this.getP()));
        this.getObstacle().add(new Pierre(9,6, this.getP()));
        this.getObstacle().add(new Pierre(9,10, this.getP()));
        this.getObstacle().add(new Pierre(13,4, this.getP()));
        this.getObstacle().add(new Pierre(13,8, this.getP()));
        this.getObstacle().add(new Pierre(14,6, this.getP()));
        this.getObstacle().add(new Pierre(15,9, this.getP()));

        for (Pierre pierre : this.getObstacle()) {
            this.getP().setObjet(pierre.getY(), pierre.getX(), pierre);
        }
    }


    /*
    * Méthode permet le joueur de choisir et mettre 2 pions sur le plateau commanceant par l'espace de début
     */
    private void placer2pionsAuDebut() {

        for (int i = 0; i < this.getListJoueur().size(); i++) {

            ArrayList<PionJoueur> temp = new ArrayList<>(this.getListJoueur().get(i).getPions());

            Scanner s, s2;
            int p1, p2;

            System.out.println("Le joueur : "+ this.getListJoueur().get(i).getNom() +". Veuillez choisir 2 pions pour commencer la partie\n");

                for (int k = 0; k < temp.size(); k++) {
                    System.out.println(k + " : " + temp.get(k).toString() + "\n");
                }

                while (true) {
                    System.out.println("Veuillez entrer un nombre entre 0 et 3 pour choisir le 1er pion : ");
                    s = new Scanner(System.in);
                    p1 = s.nextInt();
                    if (p1 == 0 || p1 == 1 || p1 == 2 || p1 == 3) {
                        break;
                    }
                }

                for (int k = 0; k < temp.size(); k++) {
                    System.out.println(k + " : " + temp.get(k).toString() + "\n");
                }

                while (true) {
                    System.out.println("Veuillez entrer un nombre entre 0 et 3 pour choisir le 2em pion 2: ");
                    s2 = new Scanner(System.in);
                    p2 = s2.nextInt();
                    if (p2 == 0 || p2 == 1 || p2 == 2|| p1 == 3) {
                        if (p1 == p2){
                            System.out.println("vous avez déjà choisi ce pion");
                        }else
                        break;
                    }
                }

                this.getListJoueur().get(i).setPionsReste(temp);
                this.getP().print();

                for (int j = 0; j < this.getListJoueur().get(i).getPions().size(); j++) {

                    if (j == p1 || j == p2) {

                        //Garder les coordonées anciennes
                        this.getListJoueur().get(i).getPions().get(j).setxAncien(this.getListJoueur().get(i).getPions().get(j).getX());
                        this.getListJoueur().get(i).getPions().get(j).setyAncien(this.getListJoueur().get(i).getPions().get(j).getY());

                        Scanner scX = new Scanner(System.in);
                        Scanner scY = new Scanner(System.in);
                        int xScanne = 1;
                        int yScanne = 1;

                        if (this.isManche()) {
                            System.out.println("MANCHE 1");
                        } else {
                            System.out.println("MANCHE 2");
                        }

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

                        //Garder les coordonées anciennes
                        this.getListJoueur().get(i).getPions().get(j).setxAncien(this.getListJoueur().get(i).getPions().get(j).getX());
                        this.getListJoueur().get(i).getPions().get(j).setyAncien(this.getListJoueur().get(i).getPions().get(j).getY());

                        this.getP().setObjet(xScanne, yScanne, this.getListJoueur().get(i).getPions().get(j), this.getListJoueur().get(i));

                        this.getListJoueur().get(i).getPion().get(j).setValeurActuelle(this.getListJoueur().get(i).getPion().get(j).getValeurActuelle() - 1);
                        this.getP().print();

                        this.p.getPlateau()[xScanne][yScanne] = new Espace(false);

                        do {

                            System.out.println(this.getListJoueur().get(i).getPions().get(j).getValeurActuelle() + " cases possible pour ce pion.");

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
                        System.out.println("Place ancienne pour ce pion : " + this.getListJoueur().get(i).getPions().get(j).getxAncien() + " | " + this.getListJoueur().get(i).getPions().get(j).getyAncien());
                        System.out.println("Ancien2 " + this.getP().getPlateau()[xScanne][yScanne].isOccupee());
                        this.getP().setObjet(xScanne, yScanne, this.getListJoueur().get(i).getPions().get(j), this.getListJoueur().get(i));
                        System.out.println("new " + this.getP().getPlateau()[xScanne][yScanne].isOccupee());
                        this.getListJoueur().get(i).getPion().get(j).setEstSurPlateau(true);
                        this.getListJoueur().get(i).getPion().get(j).setValeurActuelle(this.getListJoueur().get(i).getPion().get(j).getValeurActuelle() + 1);

                        this.getP().print();
                    }
                }
            }
        
        this.setManche(!this.isManche());

    }

    /*
     * Méthode permet le joueur de mettre les 2 pions restants sur le plateau commançant par l'espace de début
     */
    private void placer2pionsALaFin() {

        for (int i = 0; i < this.getListJoueur().size(); i++) {
            for (int j = 0; j < this.getListJoueur().get(i).getPions().size(); j++) {

                if (!this.getListJoueur().get(i).getPion().get(j).getEstSurPlateau()) {

                    //Garder les coordonées anciennes
                    this.getListJoueur().get(i).getPions().get(j).setxAncien(this.getListJoueur().get(i).getPions().get(j).getX());
                    this.getListJoueur().get(i).getPions().get(j).setyAncien(this.getListJoueur().get(i).getPions().get(j).getY());

                    Scanner scX = new Scanner(System.in);
                    Scanner scY = new Scanner(System.in);
                    int xScanne = 1;
                    int yScanne = 1;

                    if (this.isManche()) {
                        System.out.println("MANCHE 1");
                    } else {
                        System.out.println("MANCHE 2");
                    }

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

                    //Garder les coordonées anciennes
                    this.getListJoueur().get(i).getPions().get(j).setxAncien(this.getListJoueur().get(i).getPions().get(j).getX());
                    this.getListJoueur().get(i).getPions().get(j).setyAncien(this.getListJoueur().get(i).getPions().get(j).getY());

                    this.getP().setObjet(xScanne, yScanne, this.getListJoueur().get(i).getPions().get(j), this.getListJoueur().get(i));

                    this.getListJoueur().get(i).getPion().get(j).setValeurActuelle(this.getListJoueur().get(i).getPion().get(j).getValeurActuelle() - 1);

                    this.getP().print();

                    this.p.getPlateau()[xScanne][yScanne] = new Espace(false);

                    do {

                        System.out.println(this.getListJoueur().get(i).getPions().get(j).getValeurActuelle() + " cases possible pour ce pion.");

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
                    System.out.println("Place ancienne pour ce pion : " + this.getListJoueur().get(i).getPions().get(j).getxAncien() + " | " + this.getListJoueur().get(i).getPions().get(j).getyAncien());
                    System.out.println("Ancien2 " + this.getP().getPlateau()[xScanne][yScanne].isOccupee());
                    this.getP().setObjet(xScanne, yScanne, this.getListJoueur().get(i).getPions().get(j), this.getListJoueur().get(i));
                    System.out.println("new " + this.getP().getPlateau()[xScanne][yScanne].isOccupee());
                    this.getListJoueur().get(i).getPion().get(j).setValeurActuelle(this.getListJoueur().get(i).getPion().get(j).getValeurActuelle() + 1);


                    this.getP().print();
                }
            }
        }

        this.setManche(!this.isManche());

    }



    /*
    * Méthode qui permet au joueur de déplacer ou de sortir les pions du plateau 
     */
    private void deplacerPionJoueur() {

        if (this.isPremierDeplacementDeJoueurs()){
            placer2pionsALaFin();
        }

        this.setPremierDeplacementDeJoueurs(false);

        for (int i = 0; i < this.getListJoueur().size(); i++) {
            for (int j = 0; j < this.getListJoueur().get(i).getPions().size(); j++) {

                this.getListJoueur().get(i).getPions().get(j).flipValeurActuelle();

                //Garder les coordonées anciennes
                this.getListJoueur().get(i).getPions().get(j).setxAncien(this.getListJoueur().get(i).getPions().get(j).getX());
                this.getListJoueur().get(i).getPions().get(j).setyAncien(this.getListJoueur().get(i).getPions().get(j).getY());

                Scanner scX = new Scanner(System.in);
                Scanner scY = new Scanner(System.in);
                int xScanne = -1;
                int yScanne = -1;

                do {

                    if (this.getListJoueur().get(i).getPions().get(j).getX() == 1 && this.getListJoueur().get(i).getPions().get(j).getY() == 1) {
                        System.out.println("Félicitation ! vous avez arriver à la sortie :) ");
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
                    System.out.println("Place actuelle pour ce pion : " + this.getListJoueur().get(i).getPions().get(j).getX() + " | " + this.getListJoueur().get(i).getPions().get(j).getY());
                    System.out.println("Place ancienne pour ce pion : " + this.getListJoueur().get(i).getPions().get(j).getxAncien() + " | " + this.getListJoueur().get(i).getPions().get(j).getyAncien());
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

                    this.getPiongagnant().add(this.getListJoueur().get(i).getPions().get(j));
                    this.getP().getPlateau()[1][1].supprimerObject();
                    this.getListJoueur().get(i).getPions().remove(j);
                }

                this.getP().print();
                System.out.println(this.getPiongagnant());

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

        for (int i = 0; i < this.getPiongagnant().size(); i++) {
            if (this.getPiongagnant().get(i).getNumJoueur() == 0) {
                pions1 += 1;
            } else if (this.getPiongagnant().get(i).getNumJoueur() == 1) {
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
    public void mettreLeMontreSurPlateau() {
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
    public boolean deplacerEtVerifierUnObjetDansUneCase(int x, int y, Object obj) {

        if (this.getP().getPlateau()[x][y].isOccupee()) {

            if (obj.getClass().equals(PionJoueur.class) && this.getP().getPlateau()[x][y].getObjet() == PionJoueur.class) {

                PionJoueur pionJoueur = (PionJoueur) obj;

                if (pionJoueur.getX() == y && pionJoueur.getY() == x) {

                    System.out.println("Voulez-vous rester sur la même case ? (oui/non)");
                    Scanner sc = new Scanner(System.in);
                    String s = sc.nextLine();
                    return !s.equals("oui");

                } else {
                    return true;
                }

            } else {

                System.out.println("Case occupée!\n");
                return true;
            }
        } else if (obj.getClass().equals(PionJoueur.class)) {

            PionJoueur pionJoueur = (PionJoueur) obj;

            if (pionJoueur.getX() == y && pionJoueur.getY() == x) {

                System.out.println("\n");

            } else if (Math.abs(x - pionJoueur.getxAncien()) + Math.abs(y - pionJoueur.getyAncien()) > pionJoueur.getValeurActuelle()) {

                System.out.println("Vous avez passé le nombre maximal de mouvement pour ce pion ! ");
                System.out.println("Veuillez entrer une case valide ! ");
                return true;

            } else if (x == 1 && y == 1) {     // si le joueur a encore (n != 0) de mouvements et veut sortir pendant le même tour

                if (obj.getClass().equals(PionJoueur.class)) {

                    PionJoueur pion = (PionJoueur) obj;

                    if (Math.abs(x - pion.getxAncien()) + Math.abs(y - pion.getyAncien()) < pion.getValeurActuelle()) {

                        pion.setX(1);
                        pion.setY(1);
                        this.getP().setObjet(1, 1, pion);
                        this.getP().getPlateau()[pion.getxAncien()][pion.getyAncien()].supprimerObject();
                        this.getP().print();
                        return true;

                    }
                }
            }
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
     * @return the pionperdu
     */
    public ArrayList<PionJoueur> getPionperdu() {
        return pionperdu;
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
        return isFinish();
    }

    /**
     * @param finish the finish to set
     */
    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    /**
     * @param listJoueur the listJoueur to set
     */
    public void setListJoueur(ArrayList<Joueur> listJoueur) {
        this.listJoueur = listJoueur;
    }

    /**
     * @param pionperdu the pionperdu to set
     */
    public void setPionperdu(ArrayList<PionJoueur> pionperdu) {
        this.pionperdu = pionperdu;
    }

    /**
     * @return the gagnant
     */
    public Joueur getGagnant() {
        return gagnant;
    }

    /**
     * @param gagnant the gagnant to set
     */
    public void setGagnant(Joueur gagnant) {
        this.gagnant = gagnant;
    }

    /**
     * @return the piongagnant
     */
    public ArrayList<PionJoueur> getPiongagnant() {
        return piongagnant;
    }

    /**
     * @param piongagnant the piongagnant to set
     */
    public void setPiongagnant(ArrayList<PionJoueur> piongagnant) {
        this.piongagnant = piongagnant;
    }

    /**
     * @return the obstacle
     */
    public ArrayList<Pierre> getObstacle() {
        return obstacle;
    }

    /**
     * @param obstacle the obstacle to set
     */
    public void setObstacle(ArrayList<Pierre> obstacle) {
        this.obstacle = obstacle;
    }

    /**
     * @return the premierDeplacementDeJoueurs
     */
    public boolean isPremierDeplacementDeJoueurs() {
        return premierDeplacementDeJoueurs;
    }

    /**
     * @param premierDeplacementDeJoueurs the premierDeplacementDeJoueurs to set
     */
    public void setPremierDeplacementDeJoueurs(boolean premierDeplacementDeJoueurs) {
        this.premierDeplacementDeJoueurs = premierDeplacementDeJoueurs;
    }

    /**
     * @return the EspaceDeDebut
     */
    public Espace getEspaceDeDebut() {
        return EspaceDeDebut;
    }

}
