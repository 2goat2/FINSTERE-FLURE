/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finstere.flure;

/**
 * Cette classe permet de gérer l'algo DFS sur le plateau
 * @author nadim
 */
public class PlateauOutil {
    
    /**
     *                                     L'algorithme DFS: 
     * Un DFS touche récursivement chaque espace accessible une fois avec les étapes suivantes :
     * 
     *   1. Marquer l'espace actuelle comme visitée.
     *   2. Boucle à travers chaque espace adjacente.
     *   3. Si l'espace n'a pas déjà été visitée, DFS cette cellule et ajouter le nombre des espaces adjacentes à cette espace au décompte actuel.
     *   4. Retourner le nombre d'espaces adjacentes à l'espace actuelle + 1.
     *
    **/
    

    public static int compterEspacesPossibles(Plateau p) {
        if (p == null){
            throw new IllegalArgumentException("NON VALIDE!!");
        }
        boolean[][] EspacesPasse = new boolean[p.getHauteur()][p.getLargeur()];

        return dfs(p.getEspaceDeDebut(), EspacesPasse) - 1;
    }

    private static int dfs(Espace espaceCurrente, boolean[][] passe) {
        passe[espaceCurrente.getY()][espaceCurrente.getX()] = true;
        
        //Espace touchée par l'algorithme    
        int espaceTouche = 0;
        for (Espace espaceAdjs : espaceCurrente.getEspacesAdj()) {
            if (!espaceAdjs.isOccupee() && !passe[espaceAdjs.getY()][espaceAdjs.getX()]) {
                espaceTouche += dfs(espaceAdjs, passe);
            }
        }

        return ++espaceTouche;
    }

}
