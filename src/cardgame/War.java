/*
 * Evan Robertson
 * April 22 2020
 * War.java
 * This program emulates a card game known as War, using provided classes as well as
 * my own custom class extensions specifcally made for War
 */
package cardgame;

import java.util.Scanner;

/**
 *
 * @author Evan Robertson
 */
public class War {
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Get new input scanner
        Scanner input = new Scanner(System.in);
        
        //Display introduction
        System.out.println("Welcome to the War Card Game");
        System.out.println("-------------");
        System.out.println("To read over the controls and rules, type Rules");
        System.out.println("To start, type Start");
        
        //Loop until valid input is given
        while (true) {
            //Get user input
            String action = input.nextLine();
            //Check if input matches
            if (action.equalsIgnoreCase("rules")) {
                //Call rules method
                rules();
            }
            else if (action.equalsIgnoreCase("start")) {
                //Call game method
                game();
            }
            else {
                //Remind the user of valid entries
                System.out.println("Please enter either 'rules' or 'start'");
            }
        }
    }
    
    
   /*
    * This method displays the rules and controls of the game
    */
    public static void rules() {
        System.out.println("Rules can be viewed anytime during gameplay by typing 'rules'");
        System.out.println("-----------");
        System.out.println("War is a card game where each player simultaneously flips "
                + "the top of card of their respective deck. \nWhoever has the highest "
                + "card after the flip, wins the hand and adds both cards to their deck\n"
                + " this continues until either one player runs out of cards, or the value "
                + "of both cards flipped are the same. \nIf both cards are the same, the next "
                + "set of cards are played face down followed by a third set face up, who ever \n"
                + "has the highest card now wins all six cards layed out and adds to their deck"
                + " if they are equal again, repeat until\nthey aren't and who ever runs out of cards first"
                + " loses the game");
        System.out.println("----------");
        System.out.println("Commands");
        System.out.println("'f' - used to flip a card");
        System.out.println("'d' - displays amount of cards in you and your opponent's deck");
        System.out.println("'s' - shuffles your deck");
        System.out.println("'e' - exits the game");
        System.out.println("----------");
        
    }
    
   /*
    * This method creates a new game object and plays the game
    */
    public static void game() {
        WarGame game = new WarGame("Game");
        game.play();
        
    }
    
}
