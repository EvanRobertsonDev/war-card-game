/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package cardgame;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A concrete class that represents any grouping of cards for a Game. HINT, you might want to subclass this more than
 * once. The group of cards has a maximum size attribute which is flexible for reuse.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 * @author Evan Robertson April 2021
 */
public class GroupOfCards {
    ArrayList<Card> cards;
    //The group of cards, stored in an ArrayList
    private int size;//the size of the grouping

    public GroupOfCards(int size, int numSuits, int numValues) {
        cards = new ArrayList<Card>();
        
        this.size = size;
        //Loop for suits
        for (int i = 0; i < numSuits; i++) {
            //Loop for values
            for (int j = 0; j < numValues; j++) {
                //Get card suit and value
                PlayingCard.SUIT suit = PlayingCard.SUIT.values()[i];
                PlayingCard.VALUE value = PlayingCard.VALUE.values()[j];
                //Make new card object given suit and value
                PlayingCard card = new PlayingCard(suit, value);
                //Add to array
                cards.add(card);
            }
        }
    }
    
    /** New constructor for creating a group given an array list of card objects
    * @param hand the player hand of cards 
    */
    public GroupOfCards(ArrayList<Card> hand) {
        cards = new ArrayList<Card>();
        //Loop and add each card to new array
        for (int i = 0; i < hand.size(); i++) {
            cards.add(i,hand.get(i));
        }
    }
    /**
     * A method that will get the group of cards as an ArrayList
     *
     * @return the group of cards.
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * @return the size of the group of cards
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the max size for the group of cards
     */
    public void setSize(int size) {
        this.size = size;
    }

}//end class
