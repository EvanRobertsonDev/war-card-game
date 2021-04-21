/*
 * Evan Robertson
 * April 22 2021
 * GroupOfPlayingCards.java
 * This class is an extension of the GroupOfCards class that creates groups of cards
 * given the size of the deck or an array of card objects
 */
package cardgame;

import java.util.ArrayList;

/**
 *
 * @author Evan Robertson
 */
public class GroupOfPlayingCards extends GroupOfCards {
    //Array of objects that holds arrays to store cards for each deck
    ArrayList<GroupOfCards> hands = new ArrayList<GroupOfCards>();
    
    //Array used to store cards for each deck
    ArrayList<Card> deckA = new ArrayList<Card>();
    ArrayList<Card> deckB = new ArrayList<Card>();
    
    //Group object for each deck
    GroupOfCards playerOneHand;
    GroupOfCards playerTwoHand;
    
    /** Constructor that passes the suit and values to the GroupOfCards class
    * @param size the size of the deck
    * @param suits the amount of suits in the deck
    * @param vallues the amount of values in the deck
    */
    public GroupOfPlayingCards(int size, int suits, int values) {
        super(size, suits, values);
    }
    
    /** Constructor that passes the array of cards object to the GroupOfCards class
    * @param deck the deck itself in an array list
    * @param splitSize the amount of cards in each deck
    */
    public ArrayList<GroupOfCards> SplitDeck(GroupOfCards deck, int splitSize) {
        //Loop through all cards
        for (int i = 0; i < (splitSize*2); i++) {
            //First half goes in one deck
            if (i < splitSize) {
                //Add cards to deck
                deckA.add(deck.getCards().get(i));
                
            }
            //The rest go in the other
            else {
                //Add cards to deck
                deckB.add(deck.getCards().get(i));
                
            }
        }
        //Create group objects with all the cards
        playerOneHand = new GroupOfCards(deckA);
        playerTwoHand = new GroupOfCards(deckB);
        //Add objects to array
        hands.add(playerOneHand);
        hands.add(playerTwoHand);
        //Return 2 decks
        return hands;
        
    }
    
    
    
}
