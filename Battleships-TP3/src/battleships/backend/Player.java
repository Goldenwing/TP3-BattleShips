package battleships.backend;

import battleships.backend.Game.Boats;


/**
 * Classe permettant d'instancier un joueur dans le jeu Battleships. Un joueur a deux actions possibles:
 * Placer ses bateaux (setBoats) et tirer des coups sur l'ennemi (shootEnemy).
 * Deux classes enfants existent: HumanPlayer et ComputerPlayer
 * @author Annie Belzile
 */
public abstract class Player {
    /**
     * Methode permettant de placer les bateaux dans la grille de jeu en memoire. La source des coordonnees
     * est issue du choix du joueur humain pour HumanPlayer ou aleatoire pour ComputerPlayer.
     * @param gameGrid La grille de jeu en memoire (Matrix) ou doivent etre places les bateaux
     * @param direction un booleen representant la direction dans laquelle est place le bateau (true = horizontal, false = vertical)
     * @param boat un objet de type Boats representant le type de bateau a placer
     */
    public abstract void setBoats(Matrix gameGrid, boolean direction, Boats boat);
    /**
     * Methode permettant de gerer le combat naval. Elle permet de vérifier dans la grille ennemi si la case
     * choisie est occupee par un bateau ou si elle est libre. La tache est automatisee dans le cas de
     * ComputerPlayer.
     * @param gameGrid La grille de jeu en memoire de type Matrix
     */
    public abstract void shootEnemy(Matrix gameGrid);
}
