/*
 * Evan Robertson
 * April 22 2021
 * WarGame.java
 * This class is an extension of the Game.java class and is used to run the game
 * of War.
 */
package cardgame;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Evan Robertson
 */
public class WarGame extends Game{
    
    //Declare Player objects
    Player p1;
    Player p2;
    Player winner;
    
    //Get input scanner
    Scanner input = new Scanner(System.in);
    
    /** This constructor takes the name of the game and 
    *   passes it to the parent class
    * @param name the name of the game
    */
    public WarGame(String name) {
        super(name);
    }

    /*
    * This method simulates playing the game of war
    */
    @Override
    public void play() {
        boolean debugMode = false;
        //Loop until valid entry is made
        while (true) {
            //Ask user for amount of players
            System.out.println("Enter amount of players (1 or 2): ");
            //Get it
            String action = input.nextLine();
            //Try converting string to integer
            try {
                String name;
                
                //Check if only 1 player is playing
                if (Integer.parseInt(action) == 1) {
                    //Enter player name
                    System.out.println("Enter your name");
                    name = input.nextLine();
                    //Set player one name
                    p1 = new Player(name);
                    //Set player two to computer
                    p2 = new Player("Computer");
                    //Break out of loop
                    break;
                }
                //Check for 2 players
                else if (Integer.parseInt(action) == 2) {
                    //Enter player names
                    System.out.println("Enter name of first player");
                    name = input.nextLine();
                    //Set player one to name
                    p1 = new Player(name);
                    System.out.println("Enter name of second player");
                    name = input.nextLine();
                    //Set player two name
                    p2 = new Player(name);
                    //Break out of loop
                    break;
                }
                //Ask to re-enter
                else {
                    System.out.println("Invalid player number entered.");
                }
            }
            //Ask to re-enter
            catch (Exception e) {
                System.out.println("Invalid Player number entered.");
            }
        }
        
        //Make a deck
        GroupOfPlayingCards deck = new GroupOfPlayingCards(52, 4, 13);
        
        //Shuffle
        System.out.println("Shuffling...");
        deck.shuffle();
        
        //Split and deal
        System.out.println("Dealing...");
        ArrayList<GroupOfCards> hands = deck.SplitDeck(deck, 26);
        GroupOfCards playerOneDeck = hands.get(0);
        GroupOfCards playerTwoDeck = hands.get(1);
        
        //Announce the start of the game
        System.out.println("Game has started. Type 'rules' to view rules at anytime");
        
        //Loop until a player no longer has cards left
        while (playerOneDeck.getCards().size() > 0 && playerTwoDeck.getCards().size() > 0) {
            String command;
            //Hidden debug feature to play through entire game instantly
            if (debugMode)
                command = "f";
            else
            command = input.nextLine();
            if (command.equalsIgnoreCase("f")) {
                //Announce what cards were flipped up
                System.out.println("Flipping...");
                System.out.println(p1.getName() + " flipped: " + playerOneDeck.getCards().get(0).toString()
                + "\n" + p2.getName() +" flipped: " + playerTwoDeck.getCards().get(0).toString());
                //Check card values against each other
                if (playerOneDeck.getCards().get(0).getValue().compareTo(playerTwoDeck.getCards().get(0).getValue()) > 0) {
                    //Announce winner of hand
                    System.out.println(p1.getName() + " won the hand!");
                    //Add new cards to deck
                    playerOneDeck.getCards().add(playerOneDeck.getCards().get(0));
                    playerOneDeck.getCards().add(playerTwoDeck.getCards().get(0));
                    //Remove cards at front of deck
                    playerOneDeck.getCards().remove(0);
                    playerTwoDeck.getCards().remove(0);
                }
                //Check card values against each other
                else if (playerOneDeck.getCards().get(0).getValue().compareTo(playerTwoDeck.getCards().get(0).getValue()) < 0) {
                    //Announce winner of hand
                    System.out.println(p2.getName() + " won the hand!");
                    //Add new cards to deck
                    playerTwoDeck.getCards().add(playerTwoDeck.getCards().get(0));
                    playerTwoDeck.getCards().add(playerOneDeck.getCards().get(0));
                    //Remove cards at front of deck
                    playerOneDeck.getCards().remove(0);
                    playerTwoDeck.getCards().remove(0);
                }
                //If card values are equal
                else {
                    //Declare war
                    War(playerOneDeck, playerTwoDeck);
                }
            }
            else if (command.equalsIgnoreCase("d")) {
                //Display amount of cards in each deck
                System.out.println(p1.getName() + "'s Deck: " + playerOneDeck.getCards().size() + 
                        "\n" + p2.getName() +"'s Deck: " + playerTwoDeck.getCards().size());
            }
            else if (command.equalsIgnoreCase("s")) {
                //Announce the shuffle
                System.out.println("Shuffling decks...");
                //Shuffle decks
                playerOneDeck.shuffle();
                playerTwoDeck.shuffle();
                System.out.println("Cards have been shuffled");
            }
            else if (command.equalsIgnoreCase("e")) {
                //Exit to start menu
                System.out.println("Exiting...");
                break;
            }
            else if (command.equalsIgnoreCase("rules")) {
                //Call rules method from war class
                War.rules();
            }
            else if (command.equalsIgnoreCase("debug")) {
                //Enables fast play
                debugMode = true;
            }
            else {
                //Remind user of commands
                System.out.println("Please enter a valid command, commands can "
                        + "be viewed by typing 'rules'");
            }
        }
        //Declare winner after loop has been broken
        winCheck(playerOneDeck, playerTwoDeck);
        declareWinner(winner);
    }

    /** Takes the winner as a player object and announces that they won
    * @param winner the winner of the match
    */
    @Override
    public void declareWinner(Player winner) {
        System.out.println("The winner of the match is " + winner.getName() + "!");
    }
    
    /** Compares cards for a War event
    * @param playerOneDeck the deck of player one
    * @param playerTwoDeck the deck of player two
    */
    public void War(GroupOfCards playerOneDeck, GroupOfCards playerTwoDeck) {
        //Set the current position of the deck to zero
        int deckPos = 0;
        //Check if each player has enough cards to do a War
        if (playerOneDeck.getCards().size() > 3 && playerTwoDeck.getCards().size() > 3) {
            //Loop until someone has one the hand
            while (true) {
                //Compare values of next flipped cards
                if (playerOneDeck.getCards().get(deckPos).getValue().compareTo(playerTwoDeck.getCards().get(deckPos).getValue()) > 0) {
                    //Announce the flip up
                    System.out.println("Flipping cards face up...");
                    //Announce cards and winner of hand
                    System.out.println(p1.getName() + " flipped: " + playerOneDeck.getCards().get(deckPos).toString()
                    + "\n" + p2.getName() +" flipped: " + playerTwoDeck.getCards().get(deckPos).toString());
                    System.out.println(p1.getName() + " won the hand!");
                    //Loop through all cards (face up and face down) and add to winners deck
                    for (int i = 0; i < deckPos+1; i++) {
                        //Add cards to back of deck
                        playerOneDeck.getCards().add(playerOneDeck.getCards().get(0));
                        playerOneDeck.getCards().add(playerTwoDeck.getCards().get(0));
                        //Remove from front of deck
                        playerOneDeck.getCards().remove(0);
                        playerTwoDeck.getCards().remove(0);
                    }
                    //break the loop
                    break;
                }
                //Compare values of next flipped cards
                else if (playerOneDeck.getCards().get(deckPos).getValue().compareTo(playerTwoDeck.getCards().get(deckPos).getValue()) < 0) {
                    //Announce the flip up
                    System.out.println("Flipping cards face up...");
                    //Announce cards and winner of hand
                    System.out.println(p1.getName() + " flipped: " + playerOneDeck.getCards().get(deckPos).toString()
                    + "\n" + p2.getName() +" flipped: " + playerTwoDeck.getCards().get(deckPos).toString());
                    System.out.println(p2.getName() + " won the hand!");
                    //Loop through all cards (face up and face down) and add to winners deck
                    for (int i = 0; i < deckPos+1; i++) {
                        //Add cards to back of deck
                        playerTwoDeck.getCards().add(playerTwoDeck.getCards().get(0));
                        playerTwoDeck.getCards().add(playerOneDeck.getCards().get(0));
                        //Remove from front of deck
                        playerOneDeck.getCards().remove(0);
                        playerTwoDeck.getCards().remove(0);
                    }
                    //break the loop
                    break;
                }
                //Announce another war if cards are the same again
                else {
                    System.out.println("War");
                    System.out.println("Flipping cards face down...");
                    //increase by 2 toa ccount for face down cards
                    deckPos+=2;
                }


            }
        }
        //If player does not have enough cards
        else {
            winCheck(playerOneDeck, playerTwoDeck);
        } 
    }
    
    public void winCheck(GroupOfCards playerOneDeck, GroupOfCards playerTwoDeck) {
        if (playerOneDeck.getCards().size() < 3) {
                //Announce loss
                System.out.println("Card is unable to be played, " + p1.getName() + 
                        " does not have enough cards and therefore forfeits");
                playerOneDeck.getCards().clear();
                winner = p2;
            }
            else {
                //Announce loss
                System.out.println("Card is unable to be played, " + p2.getName() +
                        " does not have ennough cards and therefore forfeits");
                playerTwoDeck.getCards().clear();
                winner = p1;
            }
    }
    
}
