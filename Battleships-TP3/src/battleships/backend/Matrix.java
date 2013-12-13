 	/** Classe qui appartient à la logique derrière l'application visuelle. Cette classe va construire la matrice des bateaux et
* qui va gérer toute la logique appartenant à la plateforme des bateaux. Elle contient les méthodes ayant rapport à l'information nécéssaire
* à la matrice des bateaux.
*
* @author Kevin Tanguay
*/

package battleships.backend;

import java.util.Random;

import battleships.backend.Game.Boats;


public class Matrix
{
        private MatrixTiles[][] gameMatrix;
        private MatrixTiles[][] computerGameMatrix;
        
        /**
         * Construit la matrice de jeu. Elle place les dimensions, et initialise les cases.
         *@see SetMatrix.
         */
        public Matrix()
        {
                this.gameMatrix = SetMatrix(this.gameMatrix);
        }
        
        /**
         * Même chose, pour la matrice ennemie.
         * @param computer
         * @see SetMatrix.
         */
        public Matrix(boolean computer)
        {
                this.computerGameMatrix = SetMatrix(this.computerGameMatrix);
        }
        
        /**
         * Initialize la matrice en paramètre.
         * @param gameMatrix Soit la matrice du joueur ou de l'ordinateur.
         * @see SetSize.
         */
        public MatrixTiles[][] SetMatrix(MatrixTiles[][] gameMatrix)
        {
        	gameMatrix = null;
            gameMatrix = setSize();
            
            for(int x = 1; x < 11; x++)
            {
                    for(int y= 1 ; y < 11; y++)
                    {
                            MatrixTiles gameTile = new MatrixTiles(0, false, null);
                            gameMatrix[x][y] = gameTile;
                    }
            }
            
            return gameMatrix;
        }
        
        
        /**
         * Construit les dimensions de la matrice, soit 11 par 11.
         * @return la matrice, maintnant dimensionnée.
         */
        public MatrixTiles[][] setSize()
        {
        	MatrixTiles[][] matrix = new MatrixTiles[11][11];
        	return matrix;
        }
        
        public MatrixTiles[][] getGameMatrix() 
        {
        	return this.gameMatrix;
        }

        public void setGameMatrix(MatrixTiles[][] gameMatrix) 
        {
            this.gameMatrix = gameMatrix;
        }

        public MatrixTiles[][] getComputerGameMatrix() 
        {
        	return this.computerGameMatrix;
        }

        /**
         * Méthode pour vérifier si l'emplacement désirée du bateau par le joueur ou l'ordinateur est un espace valide,
         * s'il à assez d'espace pour placer le bateau.
         * 
         * @param boat			Le bateau en question.
         * @param direction		Soit horizontale ou verticale.
         * @param x				La case du début de l'emplacement en X.
         * @param y				La case du début de l'emplacement en Y.
         * @param matrix		La matrice qui est présentement en jeu.
         * @see QuickCheck
         * @return				True si l'espace marche, faux le cas échéant.
         */
        public boolean checkSpace(Boats boat, boolean direction, int x, int y, MatrixTiles matrix[][])
        {
                int size = boat.getSize();
                boolean valid = true;
                
                
                if(x + size > 10 || y + size > 10)
                {
                        valid = false;
                }
                else
                {
                        valid = quickCheck(x, y, size, direction, matrix);
                }
                
                return valid;
        }
        
        /**
         * Vérifie dans la matrice du jeu si l'emplacement du bateau fonctionne.
         * 
         * @param x				La première case du bateau, en X.
         * @param y				La première case du bateau, en Y.
         * @param boatSize		La grosseure du bateau.
         * @param direction		Soit horizontale ou verticale.
         * @param matrix		L'instance de la matrice.
         * @return				Vrai si l'emplacement est possible, faux le cas échéant.
         */
        public boolean quickCheck(int x, int y, int boatSize, boolean direction,MatrixTiles[][] matrix)
        {
                boolean valid = true;
                
                if(direction)
                {
                        for(int space = x; space < x + boatSize; space++)
                        {
                                if(valid)
                                {
                                        if(matrix[space][y].getNumber() != 0)
                                        {
                                                valid = false;
                                        }
                                }
                        }
                }
                else
                {
                        for(int space = y; space < y + boatSize; space++)
                        {
                                if(valid)
                                {
                                        if(matrix[x][space].getNumber() != 0)
                                        {
                                                valid = false;
                                        }
                                }
                        }
                }
                return valid;
        }
        
        /**
         * Retourne le numéro de la case selon son emplacement spécifique sur la grille.
         * @param x                La coordonnée en X de la case spécifiée.
         * @param y                La coordonnée en Y de la case spécifiée.
         * @param computer		   Si on désire la grille du joueur ou de l'ordinateur.
         * @return                 Le numéro de la case spécifique.
         *
         * @see MatrixTiles.getNumber()
         */

        public int getSquareContentNumber(int x, int y, boolean computer)
        {
                if(!computer)
                {
                        return this.gameMatrix[x][y].getNumber();
                }
                else
                        return this.computerGameMatrix[x][y].getNumber();
        }
        
        /**
         * Retourne la condition de la case spécifique, si elle est déjà cochée ou pas. Ceci est
         * vérifié par un boolean.
         *
         * @param x                La coordonnée en X de la case spécifiée.
         * @param y                La coordonnée en Y de la case spécifiée.
         * @param computer		   Si on désire la grille ennemie ou du joueur.
         * @return                vrai si déjà cochée, false le cas échéant.
         *
         * @see MatrixTiles.isClicked()
         */

        public boolean getSquareContentCheck(int x, int y, boolean computer)
        {
                if(!computer)
                {
                        return this.gameMatrix[x][y].isClicked();        
                }
                else
                        return this.computerGameMatrix[x][y].isClicked();
                
        }

        
        /**
         * Place l'information dans la case spécifique de la grille. Soit un numéro, et la condition cochée.
         * @param x            L'emplacement X de la case désirée.
         * @param y            L'emplacement Y de la case désirée.
         * @param boat         Le numéro du bateau de la case spécifique.
         * @param computer	   Si on le place dans la grille ennemie, ou du joueur.
         * @param name		   Le nom du bateau.
         */

        public void setSquareContent(int x, int y, Boats boat, boolean computer, String name)
        {
                if(!computer)
                {
                        MatrixTiles newTile = new MatrixTiles(boat.getSize(), false, name);
                        this.gameMatrix[x][y] = newTile;
                }
                else
                {
                        MatrixTiles newTile = new MatrixTiles(boat.getSize(), false, name);
                        this.computerGameMatrix[x][y] = newTile;
                }
        }

        
        /**
         * Change la condition boolean de la case spécifique. Elle rends la case cochée ou pas, dépendamment du boolean
         * en paramètre. Cette case deviens donc révélée, ou pas, visuellement.
         *
         * @param x                        L'emplacement X de la case.
         * @param y                        L'emplacement Y de la case.
         * @param check                    Le boolean qui décide si la case est cochée ou pas.
         * @param computer				   Si on veux la grille ennemie, ou du joueur.
         */

        public void setSquareCheck(int x, int y, boolean check, boolean computer)
        {
                if(!computer)
                this.gameMatrix[x][y].setClicked(check);
                else
                this.computerGameMatrix[x][y].setClicked(check);
        }
}
