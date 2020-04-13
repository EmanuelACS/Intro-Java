package app;

/**
 * Card Class, a class that contains all methods to create, modify and generate playing cards
 *
 * @author Emanuel.Dobra
 * @since 2020-2-10
 */
public class Card {

    private int value; // The value of the card, from 1 to 13

    private String suit; // The suit of the card: "d", "s", "c" or "h"

    /**
     * Generate a card with corresponding value and suit
     * 
     * @param cardValue The value of the card
     * @param cardSuit The suit of the card
     */
    public Card(int cardValue, String cardSuit) {
        value = cardValue;
        suit = cardSuit;
    }

    /**
     * The getCardValue method returns the value of the card
     * 
     * @return The card's value
     */
    public int getCardValue() {
        return value;
    }

    /**
     * The setCardValue method sets a new value to the card
     * If value is out of range, sets it to 1
     * 
     * @param newCardValue The new value to be assigned
     */
    public void setCardValue(int newCardValue) {
        if (value >= 1 && value <= 13) {
            value = newCardValue;
        } else {
            value = 1;
            value = newCardValue;
        }
    }

    /**
     * The getCardSuit method, returns the suit of the card
     * 
     * @return The card's suit
     */
    public String getCardSuit() {
        return suit;
    }

    /**
     * The setCardSuit method, sets a new suit to the card
     * 
     * @param newCardSuit The new suit to be assigned
     */
    public void setCardSuit(String newCardSuit) {
        suit = newCardSuit;
    }

    /**
     * The getCardSuitName method, returns the full suit name of the card
     * 
     * @return The full name of the card's suit
     */
    public String getCardSuitName() {
        switch(suit) {
            case "d":   return "Diamonds";
            case "s":   return "Spades";
            case "c":   return "Clubs";
            case "h":   return "Hearts";
            default:    return "Invalid Card Suit";
          }
    }

    /**
     * The getCardValueName, return the string corresponding to the value of the card
     * 
     * @return A string with the card's value
     */
    public String getCardValueName() {
        switch(value) {
            case 1:     return "Ace";
            case 2:     return "2";
            case 3:     return "3";
            case 4:     return "4";
            case 5:     return "5";
            case 6:     return "6";
            case 7:     return "7";
            case 8:     return "8";
            case 9:     return "9";
            case 10:    return "10";
            case 11:    return "Jack";
            case 12:    return "Queen";
            case 13:    return "King";
            default:    return "Invalid Card Value";
          }
    }
}