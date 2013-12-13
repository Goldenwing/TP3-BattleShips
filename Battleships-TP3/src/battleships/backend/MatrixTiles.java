/**
 /** Classe qui gère les cases de la matrice générale.
 * Chaque MatrixTiles est une tuile, une case de la matrice "Matrix". Cette classe contient les méthodes nécéssaires à 
 * l'obtention des informations de chaque case.
 * 
 * Chaque case contient un Integer, un Boolean et un String. L'Integer est le numéro relié type de bateau, le boolean
 * vérifie la condition de si la case est révélée ou pas sur l'application visuelle et le String, c'est le nom du bateau.
 * 
 * @author Kevin Tanguay
 */

package battleships.backend;

public class MatrixTiles
{
        private int tileNumber = 0;
        private boolean clicked = false;
        private String name = "";
        
        /**
         * Construit chaque case et les initialise par rapport au numéro et au boolean entrés en paramètre.
         * @param tileNumber        Le numéro de la case, par rapport aux bateaux sur la grille.
         * @param check             La condition boolean si la case est révélée ou pas sur le champ visuel.
         * @param boatName			Le nom du bateau en question.
         */
        public MatrixTiles(int tileNumber, boolean check, String boatName) 
        {
                this.tileNumber = tileNumber;
                this.setClicked(check);
                this.name = boatName;
        }

        /**
         * Retourne le numéro spécifique de la case.
         * @return                Le numéro de la case.
         */
        public int getNumber() 
        {
                return this.tileNumber;
        }
        
        public String getName()
        {
                return this.name;
        }

        /**
         * Vérifie si la case est cochée ou pas.
         * @return        vraie si cochée, false le cas échéant.
         */
        public boolean isClicked() 
        {
                return this.clicked;
        }

        public void setClicked(boolean check) 
        {
                this.clicked = check;
        }
}
