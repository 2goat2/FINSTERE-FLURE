/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finstere.flure;

import java.util.Arrays;
/**
 * Class Plateau qui gére l'environment du jeu.
 *
 * @author nadim
 */
public final class Plateau {

    private int hauteur = 13; // 1 le mur + 11 (L'hauteur du plateau normal) + 1 le mur
    private int largeur = 18; // 1 ( pour afficher l'index) + 16 (Le largeur du plateau normal) + 1 le mur
    private final int NORTH = 1, EAST = 2, SOUTH = 3, WEST = 4;
    /**
     * Matrice qui répresente le plateau :
     *
     * 5 => pour créer le mur(le contour), 2 => pour afficher l'index de chaque
     * ligne, 1 => pour dire que cet index n’est pas occupé, 0 => pour dire que
     * cet index est occupé.
     *
     */
    private int[][] booleanPlateau = {
        {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
        {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 5},
        {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 5},
        {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 5},
        {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 5},
        {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5},
        {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5},
        {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5},
        {2, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5},
        {2, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5},
        {2, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5},
        {2, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5},
        {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}};

    private Espace[][] plateau = new Espace[hauteur][largeur];

    //Garde les déplacement utile sur le plateau
    private final Deplacement[] tabDeDeMoves;

    //Espace de début
    private Espace espaceDeDebut;

    public Plateau() {

        tabDeDeMoves = new Deplacement[]{
            new Deplacement(1, 0),  //South
            new Deplacement(-1, 0),  //North
            new Deplacement(0, 1),   //East
            new Deplacement(0, -1),};//West

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

                switch (this.getBooleanPlateau()[i][j]) {
                    case 2:
                        this.getPlateau()[i][j] = new Espace(true);
                        this.getPlateau()[i][j].setAffichage(i);
                        break;
                    case 5:
                        this.getPlateau()[i][j] = new Mur(true);
                        break;
                    case 1:
                        this.getPlateau()[i][j] = new Espace(false);
                        //System.out.println(this.plateau[i][j].toString());
                        //System.out.println(Arrays.toString(this.plateau[i]));
                        break;
                    default:
                        this.getPlateau()[i][j] = new Espace(true);
                        //System.out.println(this.plateau[i][j].toString());
                        break;
                }
            }
        }

        /*
        * Afficher un ligne des integers correspondant à chaque index de chaque colone.
        * avant d'afficher le plateau.
         */
        String[] tabIndex = new String[getLargeur() - 1];
        tabIndex[0] = "Y/X";
        for (int i = 1; i < tabIndex.length; i++) {

            tabIndex[i] = " " + i + " ";
        }

        //Affichage modifiée!
        for (int i = 0; i < getHauteur(); i++) {
            if (i == 1) {
                System.out.println(Arrays.toString(tabIndex).replace("[", "").replace("]", "").replace(",", "-").replace("  10 -  11 -  12 -  13 -  14 -  15 -  16 ", " 10 - 11 - 12 - 13 - 14 - 15 - 16").replace("  1", " 1").replace("X/Y-", "X/Y "));
            }
            System.out.println(Arrays.toString(this.getPlateau()[i]).replace("[", "").replace("]", "").replace(",", "-").replace("10- ", "10-").replace("11- ", "11-").replace("X/Y-", "X/Y ").replace("|X|-  ", "|X|").replace("| |-  ", "| |"));
        }
    }

    /**
     * Méthode permet à l'espace de génerer les espace adjacentes
     */
    private void genererAdj() {
        for (int i = 0; i < this.plateau.length; i++) {
            for (int j = 0; j < this.plateau[i].length; i++) {
                for (Deplacement tabDeDeMove : tabDeDeMoves) {
                    Espace p = new Espace(j + tabDeDeMove.getX(), i + tabDeDeMove.getY(), false);
                    if (!p.isOccupee()) {
                        this.plateau[i][j].ajouterEspaceAdj(this.plateau[p.getY()][p.getX()]);
                    }
                }
            }
        }

    }

    /**
     * La méthode setObjet permet d'ajouter un objet dans une case specifique
     * sur le plateau
     *
     * @param x
     * @param y
     * @param obj
     * @return true si l'objet a été bien met dans l'espace(x,y,false)
     */
    public boolean setObjet(int y, int x, Object obj) {

        return this.plateau[y][x].ajouterObjet(obj);

    }

    /**
     * La méthode setObjet permet d'ajouter un objet dans une case specifique
     * sur le plateau
     *
     * @param x
     * @param y
     * @param obj
     * @param j le joueuer qu'on a bien pris son nom pour l'afficher dans
     * l'espace.
     * @return true si l'objet a été bien met dans l'espace(x,y,false)
     */
    public boolean setObjet(int y, int x, Object obj, Joueur j) {

        return this.plateau[y][x].ajouterObjet(obj, j);

    }


    /*
     Les Méthodes : moveupObjet, moveDownObjet, moveLeftObjet, moveRightObjet
        Pour déplacer un objet une case sur le plateau.
     */
    /**
     * La méthode deplacerLeMonstreUneFois permet de deplacer un monstre m une
     * case.
     *
     * @param m le monstre déplacé
     * @return true si le monstre a été bien déplacé.
     */
    public boolean deplacerLeMonstreUneFois(PionMonstre m) {
        boolean ok = false;
        switch (m.getDirection()) {
            case NORTH:
                if (m.getY() == 0 || (m.getX() == 13 && m.getY() == 1) || (m.getX() == 14 && m.getY() == 2) || (m.getX() == 15 && m.getY() == 3) || (m.getX() == 16 && m.getY() == 4)) {
                    ok = deplacerMonstre(m, largeur - m.getX(), hauteur - m.getY());
                } else {
                    ok = deplacerMonstre(m, m.getX(), m.getY() - 1);
                }
                break;
            case EAST:
                if (m.getX() == 17 || (m.getX() == 12 && m.getY() == 1) || (m.getX() == 13 && m.getY() == 2) || (m.getX() == 14 && m.getY() == 3) || (m.getX() == 15 && m.getY() == 4)) {
                    ok = deplacerMonstre(m, largeur - m.getX() - 1, hauteur - m.getY() - 1);
                } else {
                    ok = deplacerMonstre(m, m.getX() + 1, m.getY());
                }
                break;
            case SOUTH:
                if (m.getY() == this.hauteur || (m.getX() == 4 && m.getY() == 10) || (m.getX() == 3 && m.getY() == 9) || (m.getX() == 2 && m.getY() == 8) || (m.getX() == 1 && m.getY() == 7)) {
                    ok = deplacerMonstre(m, largeur - m.getX() - 1, hauteur - m.getY() - 1);
                } else {
                    ok = deplacerMonstre(m, m.getX(), m.getY() + 1);
                }
                break;
            case WEST:
                if (m.getX() == 0 || (m.getX() == 1 && m.getY() == 8) || (m.getX() == 2 && m.getY() == 9) || (m.getX() == 3 && m.getY() == 10) || (m.getX() == 4 && m.getY() == 11)) {
                    ok = deplacerMonstre(m, largeur - m.getX(), hauteur - m.getY());
                } else {
                    ok = deplacerMonstre(m, m.getX() - 1, m.getY());
                }
                break;
            default:
                ok = false;
        }

        return ok;
    }

    /**
     * La méthode deplacerMonstre permet de déplacer le monstre m dans un espace
     * de x et y et faire les actions nécessaire (déplacer un Pierre ou tuer un
     * pion...)
     *
     * @param m le monstre déplacé
     * @param x l'x de l'espace cible
     * @param y l'y de l'espace cible
     *
     * @return true si le monstre a été bien déplacé .
     */
    public boolean deplacerMonstre(PionMonstre m, int x, int y) {
        boolean ok = false;
        int i = 0;

        while (this.plateau[y][x].isOccupee()) {
            if (this.plateau[y][x].getObjet() != null && this.plateau[y][x].getObjet().getClass().equals(PionJoueur.class)) {
                break;
            }
            switch (m.getDirection()) {
                case NORTH:
                    if (y == 0 || (x == 13 && y == 1) || (x == 14 && y == 2) || (x == 15 && y == 3) || (x == 16 && y == 4)) {
                        x = largeur - m.getX() - 1;
                        y = hauteur - m.getY();
                    } else {
                        y -= 1;
                    }
                    break;
                case EAST:
                    if (x == 17 || (x == 12 && y == 1) || (x == 13 && y == 2) || (x == 14 && y == 3) || (x == 15 && y == 4)) {
                        x = largeur - m.getX() - 1;
                        y = hauteur - m.getY() - 1;
                    } else {
                        x += 1;
                    }
                    break;
                case SOUTH:
                    if (y == 12 || (x == 4 && y == 10) || (x == 3 && y == 9) || (x == 2 && y == 8) || (x == 1 && y == 7)) {
                        x = largeur - m.getX() - 1;
                        y = hauteur - m.getY() - 1;
                    } else {
                        y += 1;
                    }
                    break;
                case WEST:
                    if (x == 0 || (x == 1 && y == 8) || (x == 2 && y == 9) || (x == 3 && y == 10) || (x == 4 && y == 11)) {
                        x = largeur - m.getX();
                        y = hauteur - m.getY() - 1;
                    } else {
                        x -= 1;
                    }
                    break;
                default:
                    System.out.println("Invalide!");
                    break;
            }

            i += 1;
        }

        if (this.plateau[y][x].getObjet() != null && this.plateau[y][x].getObjet().getClass().equals(PionJoueur.class)) {
            PionJoueur p = (PionJoueur) this.plateau[y][x].getObjet();
            m.tuer(p);
        }

        ok = setObjet(y, x, m);
        this.plateau[m.getY()][m.getX()].supprimerObject();
        m.setX(x);
        m.setY(y);

        return ok;
    }

    public void deplacerPierreUneFois(int direction, Pierre r) {
        boolean ok = false;

        System.out.println("1 x : " + r.getX());
        System.out.println("1 y : " + r.getY());

        switch (direction) {
            case NORTH:
                ok = deplacerPierre(r, r.getX() , r.getY() -1, direction);
                break;
            case EAST:
                ok = deplacerPierre(r, r.getX()+1, r.getY(), direction);
                break;
            case SOUTH:
                ok = deplacerPierre(r, r.getX(), r.getY() +1, direction);
                break;
            case WEST:
                ok = deplacerPierre(r, r.getX()-1 , r.getY(), direction);
                break;
            default:
                ok = false;
        }
        System.out.println("2 x : " + r.getX());
        System.out.println("2 y : " + r.getY());

    }

    public boolean deplacerPierre(Pierre r, int x, int y, int direction) {

        if (this.plateau[y][x].isOccupee() || this.plateau[y][x].getClass() == Mur.class) {

            if (this.plateau[y][x].getObjet() != null && this.plateau[y][x].getObjet().getClass() == Pierre.class) {
                deplacerPierreUneFois(direction, (Pierre) this.plateau[y][x].getObjet());

            } else if (this.plateau[y][x].getObjet() != null && this.plateau[y][x].getObjet().getClass() == PionJoueur.class) {
                this.plateau[r.getY()][r.getX()].supprimerObject();

            } else {
                this.plateau[r.getY()][r.getX()].supprimerObject();
                return false;
            }
        }

        boolean ok = this.setObjet(y, x, r);
        this.plateau[r.getY()][r.getX()].supprimerObject();
        r.setX(x);
        r.setY(y);

        return ok;
    }

        /**
     * Méthode pour afficher le plateau.
     */
    public void print() {

        String[] tabIndex = new String[getLargeur() - 1];
        tabIndex[0] = "Y/X";
        for (int i = 1; i < tabIndex.length; i++) {

            tabIndex[i] = " " + i + " ";
        }

        //Affichage modifiée!
        for (int i = 0; i < getHauteur(); i++) {
            if (i == 1) {
                System.out.println(Arrays.toString(tabIndex).replace("[", "").replace("]", "").replace(",", "-").replace("  10 -  11 -  12 -  13 -  14 -  15 -  16 ", " 10 - 11 - 12 - 13 - 14 - 15 - 16").replace("  1", " 1").replace("X/Y-", "X/Y "));
            }
            System.out.println(Arrays.toString(this.getPlateau()[i]).replace("[", "").replace("]", "").replace(",", "-").replace("10- ", "10-").replace("11- ", "11-").replace("X/Y-", "X/Y ").replace("|X|-  ", "|X|").replace("| |-  ", "| |"));
        }
        System.out.println("\n");

    }
    /**
     * @return the hauteur
     */
    public int getHauteur() {
        return hauteur;
    }

    /**
     * @return the largeur
     */
    public int getLargeur() {
        return largeur;
    }

    /**
     * @return the booleanPlateau
     */
    public int[][] getBooleanPlateau() {
        return booleanPlateau;
    }

    /**
     * @return the plateau
     */
    public Espace[][] getPlateau() {
        return plateau;
    }

    public Espace getEspaceDeDebut() {
        return espaceDeDebut;
    }


    public void ajoutFlague() {
        Flague[] tabflague = new Flague[8];
        tabflague[0] = new Flague(3, 9);
        tabflague[1] = new Flague(3, 10);
        tabflague[2] = new Flague(4, 9);
        tabflague[3] = new Flague(4, 10);
        tabflague[4] = new Flague(9, 5);
        tabflague[5] = new Flague(9, 6);
        tabflague[6] = new Flague(9, 7);
        tabflague[7] = new Flague(9, 8);

    }

}
