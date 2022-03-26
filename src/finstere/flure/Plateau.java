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
 * Class Plateau qui gére l'environment du jeu.
 *
 * @author nadim
 */
public final class Plateau {

    private int hauteur = 13;
    private int largeur = 18; // 1 ( pour afficher l'index) + 16 (La taille du plateau normal)
    private final int NORTH = 1, EAST = 2, SOUTH = 3, WEST = 4;
    /**
     * Matrice qui répresente le plateau :
     *
     * 2 => pour afficher l'index de chaque ligne, 1 => pour dire que cet index
     * n’est pas occupé, 0 => pour dire que cet index est occupé.
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

    private final Deplacement[] tabDeDeMoves;

    private Espace espaceDeDebut;

    public Plateau() {

        tabDeDeMoves = new Deplacement[]{
            new Deplacement(1, 0),
            new Deplacement(-1, 0),
            new Deplacement(0, 1),
            new Deplacement(0, -1),};

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
                    this.getPlateau()[i][j].setAffichage(i);
                }else if (this.getBooleanPlateau()[i][j] == 5) {
                    this.getPlateau()[i][j] = new Mur(true);

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
        String[] tabIndex = new String[getLargeur() - 1];
        tabIndex[0] = "Y/X";
        for (int i = 1; i < tabIndex.length; i++){

            tabIndex[i] = " " + i + " ";
        }

        //Affichage modifiée!
        System.out.println(Arrays.toString(tabIndex).replace("[", "").replace("]", "").replace(",", "-").replace("  10 -  11 -  12 -  13 -  14 -  15 -  16 ", " 10 - 11 - 12 - 13 - 14 - 15 - 16").replace("  1", " 1").replace("X/Y-", "X/Y "));

        //Affichage modifiée!
        for (int i = 0; i < getHauteur(); i++) {
            System.out.println(Arrays.toString(this.getPlateau()[i]).replace("[", "").replace("]", "").replace(",", "-").replace("10- ", "10-").replace("11- ", "11-").replace("X/Y-", "X/Y ").replace("|X|-  ", "|X|").replace("| |-  ", "| |"));
        }
    }

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
     * @param j le joueuer qu'on a bien pris son nom pour l'afficher dans
     * l'espace.
     * @return true si l'objet a été bien met dans l'espace(x,y,false)
     */
    public boolean setObjet(int y, int x, Object obj) {

        return this.plateau[y][x].ajouterObjet(obj);

    }

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
                    ok = deplacerMonstre(m, largeur - m.getX(), hauteur - m.getY()   );
                } else {
                    ok = deplacerMonstre(m, m.getX(), m.getY() - 1);
                }
                break;
            case EAST:
                if (m.getX() == 17 || (m.getX() == 12 && m.getY() == 1) || (m.getX() == 13 && m.getY() == 2) || (m.getX() == 14 && m.getY() == 3) || (m.getX() == 15 && m.getY() == 4)) {
                    ok = deplacerMonstre(m, largeur - m.getX()- 1, hauteur - m.getY() - 1);
                } else {
                    ok = deplacerMonstre(m, m.getX() + 1, m.getY());
                }
                break;
            case SOUTH:
                if (m.getY() == this.hauteur || (m.getX() == 4 && m.getY() == 10) || (m.getX() == 3 && m.getY() == 9) || (m.getX() == 2 && m.getY() == 8) || (m.getX() == 1 && m.getY() == 7)) {
                    ok = deplacerMonstre(m, largeur - m.getX() -1, hauteur - m.getY() - 1);
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

      //  if(y == 1) if (x == 13 || x == 14 || x == 15 || x == 16) {
       //      y = y;
       // } else y -= 1;

        while (this.plateau[y][x].isOccupee()) {
            if (i > 10) {
                break;
            }
            switch ( m.getDirection() ) {
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

        if (this.plateau[y][x].getObjet() != null && this.plateau[y][x].getObjet().getClass() == PionJoueur.class) {
            this.plateau[y][x].supprimerObject();
            m.ajouterPionsTues();
        }
        ok = setObjet(y, x, m);
        this.plateau[m.getY()][m.getX()].supprimerObject();
        m.setX(x);
        m.setY(y);

        return ok;
    }

    /**
     * This moves a rock object one space
     *
     * @param face The direction to move the rock
     * @param r The rock to be moved
     */
    public void deplacerPierreUneFois(int direction, Pierre r) {
        boolean ok = false;
        switch (direction) {
            case NORTH:
                ok = deplacerPierre(r, r.getX(), r.getY() - 1, direction);
                break;
            case EAST:
                ok = deplacerPierre(r, r.getX() + 1, r.getY(), direction);
                break;
            case SOUTH:
                ok = deplacerPierre(r, r.getX(), r.getY() + 1, direction);
                break;
            case WEST:
                ok = deplacerPierre(r, r.getX() - 1, r.getY(), direction);
                break;
            default:
                ok = false;
        }

    }

    //Moves rock, r to spot (x, y) and performs any necessary actions (killing people, moving other rocks, disappearing, etc)
    public boolean deplacerPierre(Pierre r, int x, int y, int direction) {
        boolean ok = true;
        if (this.plateau[x][y].isOccupee()) {
            this.plateau[r.getX()][r.getY()].supprimerObject();
            return false;
        }
        if (this.plateau[x][y].getObjet() != null && this.plateau[x][y].getObjet().getClass() == PionJoueur.class) {
            this.plateau[r.getX()][r.getY()].supprimerObject();
        }
        if (this.plateau[x][y].getObjet() != null && this.plateau[x][y].getObjet().getClass() == Pierre.class) {
            deplacerPierreUneFois(direction, (Pierre) this.plateau[x][y].getObjet());
        }
        ok = this.setObjet(x, y, this.plateau[r.getX()][r.getY()].supprimerObject());
        r.setX(x);
        r.setY(y);
        return ok;
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

    public Espace getEspaceDeDebut() {
        return espaceDeDebut;
    }

    public void setEspaceDeDebut(Espace p) {
        if (!p.isOccupee()) {
            throw new IllegalArgumentException("Espace Invalide!");
        }

        this.espaceDeDebut.equals(p);
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
