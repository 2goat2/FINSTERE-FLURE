/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finstere.flure;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe PaquetDeTuiles gère le paquet qui contient les tuiles qui déplace le monstre
 *
 * @author nadim
 */
public final class PaquetDeTuiles {

    private final ArrayList<Tuile> paquet = new ArrayList<>();

    public PaquetDeTuiles() {
        this.initPaquet();
        this.melanger(100);
    }

    private void initPaquet() {
        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 0,1:
                    this.paquet.add(new Tuile(1));
                    break;
                case 2:
                    this.paquet.add(new Tuile(5));
                    break;
                case 3,4:
                    this.paquet.add(new Tuile(7));
                    break;
                case 5,6:
                    this.paquet.add(new Tuile(8));
                    break;
                case 7:
                    this.paquet.add(new Tuile(10));
                    break;

            }
        }
    }

    public Tuile donnerTuile() {
        melanger(100);
        if (this.paquet.size() > 0) {
            return this.paquet.get(0);
        } else {
            System.out.println("paquet vide");
            return null;
        }
    }

    public void echangerDeuxTuiles() {
        int i = (int) (Math.random() * 8.0);
        int j = (int) (Math.random() * 8.0);
        Collections.swap(paquet, i, j);
    }

    public void melanger(int n) {
        for (int i = 0; i < n; i++) {
            this.echangerDeuxTuiles();
        }
    }
}
