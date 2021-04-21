/*
 * Evan Robertson
 * April 22 2021
 * PlayingCard.java
 * This class is an extension of the Card.java class and is used in assigning 
 * values and suits to the cards using enums
 */
package cardgame;


/**
 *
 * @author Evan Robertson
 */
public class PlayingCard extends Card{
    
    /*
    * This enum contains all suits for a playing card
    */
    public enum SUIT {
        DIAMONDS, HEARTS, SPADES, CLUBS
    }
    
    /*
    * This enum contains all values for a playing card
    */
    public enum VALUE {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
    }
    
    
    private SUIT suit;
    private VALUE value;
    
    /** This constructor passes the suit and value to a Card object
    * @param suit the suit of the card
    * @param value the value of the card
    */
    public PlayingCard(SUIT suit, VALUE value) {
        this.suit=suit;
        this.value=value;
    }

    /**
    * @return value of card
    */
    public VALUE getValue() {
        return value;
    }
    
    
    /**
    * @return value and suit in string format
    */
    @Override
    public String toString() {
        return "" + value + " of " + suit;
    }
}
