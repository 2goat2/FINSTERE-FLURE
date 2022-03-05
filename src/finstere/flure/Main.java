/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finstere.flure;

import java.util.Scanner;

/**
 *
 * @author nadim
 */
public class Main {

    //Constantes statiques pour faciliter les entrées:
    public final static Scanner scanner = new Scanner(System.in);
    public final static String msgErreurEntree = "\nErreur, veuillez entrer une valeur correspondant au menu affiché";

    //Partie de jeu
    private static Partie partie;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main.displayMainMenu();
    }

    private static void displayMainMenu() {

        while (true) {

            affichageLOGO();

            System.out.println("1. Nouvelle Partie");
            System.out.println("2. Quitter");

            switch (Main.scanner.nextInt()) {

                case 1:
                    partie = new Partie();
                    Main.partie.start();
                    break;
                case 2:
                    System.exit(0);
                    break;
                default:
                    System.out.println(Main.msgErreurEntree);
                    break;

            }
        }

    }

    private static void affichageLOGO() {
        System.out.println("             .                     .x+=:.        s                                                                   ..                                        ");
        System.out.println("   oec :    @88>                  z`    ^%      :8                                                      oec :  x .d88\"                                         ");
        System.out.println("  @88888    %8P      u.    u.        .   <k    .88                  .u    .                            @88888   5888R      x.    .        .u    .              ");
        System.out.println("  8\"*88%     .     x@88k u@88c.    .@8Ned8\"   :888ooo      .u     .d88B :@8c       .u                  8\"*88%   '888R    .@88k  z88u    .d88B :@8c       .u    ");
        System.out.println("  8b.      .@88u  ^\"8888\"\"8888\"  .@^%8888\"  -*8888888   ud8888.  =\"8888f8888r   ud8888.                8b.       888R   ~\"8888 ^8888   =\"8888f8888r   ud8888.  ");
        System.out.println(" u888888> ''888E`   8888  888R  x88:  `)8b.   8888    :888'8888.   4888>'88\"  :888'8888.              u888888>   888R     8888  888R     4888>'88\"  :888'8888. ");
        System.out.println("  8888R     888E    8888  888R  8888N=*8888   8888    d888 '88%\"   4888> '    d888 '88%\"               8888R     888R     8888  888R     4888> '    d888 '88%\" ");
        System.out.println("  8888P     888E    8888  888R   %8\"    R88   8888    8888.+\"      4888>      8888.+\"                  8888P     888R     8888  888R     4888>      8888.+\"    ");
        System.out.println("  *888>     888E    8888  888R    @8Wou 9%   .8888Lu= 8888L       .d888L .+   8888L        88888888    *888>     888R     8888 ,888B .  .d888L .+   8888L      ");
        System.out.println("  4888      888&   \"*88*\" 8888\" .888888P`    ^%888*   '8888c. .+  ^\"8888*\"    '8888c. .+   88888888    4888     .888B .  \"8888Y 8888\"   ^\"8888*\"    '8888c. .+ ");
        System.out.println("  '888      R888\"    \"\"   'Y\"   `   ^\"F        'Y\"     \"88888%       \"Y\"       \"88888%                 '888     ^*888%    `Y\"   'YP        \"Y\"       \"88888%   ");
        System.out.println("   88R       \"\"                                          \"YP'                    \"YP'                   88R       \"%                                   \"YP'    ");
        System.out.println("   88>                                                                                                  88>                                                    ");
        System.out.println("   48                                                                                                   48                                                     ");
        System.out.println("   '8                                                                                                   '8                                                     ");

    }

}
