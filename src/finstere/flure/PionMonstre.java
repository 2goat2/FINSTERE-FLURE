/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finstere.flure;

import java.util.Objects;

/**
 *
 * @author nadim
 */
public class PionMonstre {

    //Permet de garder la direction 
    private int direction = 2; // 2 = East

    //le plateau
    private Plateau plateau;

    //les directions qu'il faut voir à chaque déplacement
    private Direction directions;

    //Permet de garder les pions tués par ce monstre
    private int pionsTues;

    //Les coordonées
    private int x, y;

    //le nombre de mouvements qui peuvent déplacer le monstre 
    private int mouvement = 1;

    //La partie 
    private final Partie partie;

    //permet de savoir si le monstre a tué un pion
    private boolean aTue = false; // true si le monstre a tué un pion lorsqu'il déplace. La variable est toujours false au debut de chaque déplacement.

    public String imgSource;

    //La constructeur
    public PionMonstre(int x, int y, int direction, Plateau p, Partie partie) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.directions = new Direction();
        this.plateau = p;
        this.pionsTues = 0;
        this.partie = partie;
        this.imgSource = FinFlureGUI.chemin + imageDirections(direction);
    }


    public final String imageDirections(int direction) {

        switch (direction) {
            case 1:
                return "monstre1.gif";
            case 2:
                return "monstre2.gif";
            case 3:
                return "monstre3.gif";
            case 4:
                return "monstre4.gif";
        }
        return null;

    }

    /**
     * La méthode deplacer c'est la méthode principale qui déplace le monstre
     *
     * 1. Le monstre regarde à sa gauche, sa droite et tout droit. 2. Le monstre
     * calcule le chemin pour chaque objet détecté (S'il y a). 3. le monstre
     * choisi le chemin le plus court. 4. le monstre se déplace vers l'objet
     * choisi ou bien se déplace dans sa direction s'il n'a pas trouvé des
     * objets.
     *
     * @param movement le nombre de mouvements
     */
    public void deplacer(int movement) {

        this.setaTue(false);

        if (movement > 0) {
            int gauche = regarder(getDirections().getGauche(this.getDirection()));
            int droite = regarder(getDirections().getDroite(this.getDirection()));
            int toutDroit = regarder(this.getDirection());

            if (gauche > droite && droite != 0) {
                this.setDirection(getDirections().getDroite(this.getDirection()));
                this.imgSource = FinFlureGUI.chemin+ this.imageDirections(direction);
                this.getPlateau().deplacerLeMonstreUneFois(this);
            } else if (droite > gauche && gauche != 0) {
                this.setDirection(this.getDirections().getGauche(this.getDirection()));
                this.imgSource = FinFlureGUI.chemin + this.imageDirections(direction);
                this.getPlateau().deplacerLeMonstreUneFois(this);
            } else {
                //tout droit
                this.imgSource = FinFlureGUI.chemin + this.imageDirections(direction);
                if (regarderObjet(this.getDirection()) != null && Objects.requireNonNull(regarderObjet(this.getDirection())).getClass() == Pierre.class) {

                    this.getPlateau().deplacerPierreUneFois(this.getDirection(), (Pierre) regarderObjet(this.getDirection()));
                    
                    this.getPlateau().deplacerLeMonstreUneFois(this);

                } else {
                    this.getPlateau().deplacerLeMonstreUneFois(this);
                }
            }
            this.getPlateau().print();
            deplacer(movement - 1);
        }
    }

    /**
     * La Méthode regarderObjet
     *
     * @return l'objet qui a été détecté sur la direction choisi
     */
    private Object regarderObjet(int direction) {
        switch (direction) {
            case 1:
                return regarderObjetNorth();
            case 2:
                return regarderObjetEast();
            case 3:
                return regarderObjetSouth();
            case 4:
                return regarderObjetWest();
        }
        return null;
    }

    /**
     * Les Méthodes
     * regarderObjetWest(),regarderObjetNorth(),regarderObjetSouth(),regarderObjetEast()
     *
     * @return l'objet qui a été détecté sur une direction
     */
    private Object regarderObjetWest() {
        return this.getPlateau().getPlateau()[getY()][getX() - this.getMouvement()].getObjet();
    }

    private Object regarderObjetNorth() {
        return this.getPlateau().getPlateau()[getY() - this.getMouvement()][getX()].getObjet();
    }

    private Object regarderObjetSouth() {
        return this.getPlateau().getPlateau()[getY() + this.getMouvement()][getX()].getObjet();
    }

    private Object regarderObjetEast() {
        return this.getPlateau().getPlateau()[getY()][getX() + this.mouvement].getObjet();

    }

    /**
     *
     * @param direction
     * @return ( rien ) s'il n'y a pas d'objet sur direction ou (l'index de
     * l'objet) si l'objet est un PionJoueur ou (this.getPlateau().getLargeur()*
     * this.getPlateau().getHauteur()) si l'objet est un Pierre
     */
    private int regarder(int direction) {
        switch (direction) {
            case 1:
                return regarderNorth();
            case 2:
                return regarderEast();
            case 3:
                return regarderSouth();
            case 4:
                return regarderWest();
        }

        return -1;
    }

    /**
     * Les méthodes
     * regarderNorth(),regarderEast(),regarderSouth(),regarderWest() pour
     * chercher l'objet sur les 4 directions
     *
     * @return ( rien ) s'il n'y a pas d'objet sur une direction ou (l'index de
     * l'objet) si l'objet est un PionJoueur ou (this.getPlateau().getLargeur()*
     * this.getPlateau().getHauteur()) si l'objet est un Pierre
     */
    private int regarderNorth() {

        for (int i = 0; i <= y; i++) {
            if (this.getPlateau().getPlateau()[y - i][x].getObjet() == null) {
                continue;
            } else if (this.getPlateau().getPlateau()[y - i][x].getObjet().getClass() == PionJoueur.class) {
                return i;
            } else if (this.getPlateau().getPlateau()[y - i][x].getObjet() == Pierre.class) {
                return this.getPlateau().getLargeur() * this.getPlateau().getHauteur();//pour assurer que c'est le nombre le plus grand
            }
        }

        return this.getPlateau().getLargeur() * this.getPlateau().getHauteur();
    }

    private int regarderSouth() {

        for (int i = y; i < this.getPlateau().getHauteur(); i++) {
            if (this.getPlateau().getPlateau()[i][x].getObjet() == null) {
                continue;
            } else if (this.getPlateau().getPlateau()[i][x].getObjet().getClass() == PionJoueur.class) {
                return i - y;
            } else if (this.getPlateau().getPlateau()[i][x].getObjet().getClass() == Pierre.class) {
                return this.getPlateau().getLargeur() * this.getPlateau().getHauteur();
            }
        }

        return this.getPlateau().getLargeur() * this.getPlateau().getHauteur();
    }

    private int regarderWest() {

        for (int i = 0; i <= x; i++) {
            if (this.getPlateau().getPlateau()[y][x - i].getObjet() == null) {
                continue;
            } else if (this.getPlateau().getPlateau()[y][x - i].getObjet().getClass() == PionJoueur.class) {
                return i;
            } else if (this.getPlateau().getPlateau()[y][x - i].getObjet().getClass() == Pierre.class) {
                return this.getPlateau().getLargeur() * this.getPlateau().getHauteur();
            }
        }

        return this.getPlateau().getLargeur() * this.getPlateau().getHauteur();
    }

    private int regarderEast() {

        for (int i = x; i < this.getPlateau().getLargeur(); i++) {
            if (this.getPlateau().getPlateau()[y][i].getObjet() == null) {
                continue;
            } else if (this.getPlateau().getPlateau()[y][i].getObjet().getClass() == PionJoueur.class) {
                return i - x;
            } else if (this.plateau.getPlateau()[y][i].getObjet().getClass() == Pierre.class) {
                return this.getPlateau().getLargeur() * this.getPlateau().getHauteur();
            }
        }

        return this.getPlateau().getLargeur() * this.getPlateau().getHauteur();
    }

    /**
     * Methode qui permet au mostre de tuer un PionJoueur, du supprimer de la
     * liste de pion du joueur, et de l'ajouter dans le liste des pions perdus
     * d'une partie
     *
     * si (partie.manche = true) ==> réinitialiser ce pion. sinon si
     * (partie.manche = false) ==> tuer ce pion et l'ajouter dans le liste des
     * pions perdus d'une partie.
     *
     * @param p le pion tué
     */
    public void tuer(PionJoueur p) {
        this.ajouterPionsTues();
        plateau.getPlateau()[p.getY()][p.getX()].setAffichage("X");
        plateau.getPlateau()[p.getY()][p.getX()].supprimerObject();
        p.setxAncien(17);
        p.setyAncien(11);
        this.setaTue(true);

        System.out.println(this.partie.getManche());
        if (this.partie.getManche()) {

            if (!this.partie.getListJoueur().isEmpty()) {

                this.partie.getPionperdu().add(p);
                this.partie.getListJoueur().get(p.getNumJoueur()).getPions().remove(p);

                if (this.partie.getListJoueur().get(p.getNumJoueur()).getPions().isEmpty()) {

                    if (this.partie.getListJoueur().size() == 1) {
                        //  this.partie.getListJoueur().remove(this.partie.getListJoueur().get(1));
                        this.partie.setFinish(true);
                    }
                }

            } else if (this.partie.getListJoueur().isEmpty()) {
                this.mouvement = 0;
                this.partie.setFinish(true);
            }
        }
    }

    /**
     * SETTER et GETTERS
     *
     * @param direction permet de modifier la direction du monstre
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    /**
     * @return the plateau
     */
    public Plateau getPlateau() {
        return plateau;
    }

    /**
     * @return the directions
     */
    public Direction getDirections() {
        return directions;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the mouvement
     */
    public int getMouvement() {
        return mouvement;
    }

    public void ajouterPionsTues() {
        this.pionsTues += 1;
    }

    /**
     * @return the aTue
     */
    public boolean isaTue() {
        return aTue;
    }

    /**
     * @param aTue the aTue to set
     */
    public void setaTue(boolean aTue) {
        this.aTue = aTue;
    }
}
