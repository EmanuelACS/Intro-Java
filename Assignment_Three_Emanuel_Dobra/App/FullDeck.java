package app;

/**
 * Generate a deck of playing cards, with the 
 * ability to print and shuffle the deck
 *
 * @author Emanuel.Dobra
 * @since 2020-3-23
 */
public class FullDeck {

    // Alocatting space for the deck of cards
    public Card[] myDeck = new Card[52];

    // Generating the deck
    public FullDeck() {

        int k = 0;

        // Loop over the 4 suits the deck contains
        for (int i = 0; i < 4; i++) {
            String[] suits = {"d", "s", "c", "h"};

            // Assigning the value (1-13) to the card and appending it to the array
            for (int j = 0; j < 13; j++) {
                // Card object to append
                Card cardSample = new Card(1, "");

                // Setting the Card's suit and value
                cardSample.setCardSuit(suits[i]);
                cardSample.setCardValue(j+1);

                // Starting from the k'th (0-51) index, append to deck
                myDeck[k] = cardSample;
                k++; 
            }
        }
    }

    /**
     * The cardToString method generates the text of the card object
     * 
     * @param value value of the card
     * @param suit suit of the card
     * @return returns the string representing the card
     */
    public String cardToString(String value, String suit) {
        return (value + " of " + suit);
    }

    /**
     * The printDeck method outputs to console every card within the deck
     */
    public void printDeck() {
        // Loop through the 52 cards and print them
        for (int i = 0; i < 52; i++) {
            System.out.println(cardToString(myDeck[i].getCardValueName(), myDeck[i].getCardSuitName()));
        }
    }

    /**
     * The shuffleDeck method randomizes the positons of the
     * cards within the deck by swapping 2 distinct cards 
     * 500 times. 
     */
    public void shuffleDeck()
    { 
        // The amount of times two cards should be swaped 
        // 500 seems good to obtain a fully shuffled deck
        for (int i = 0; i < 500; i++) {

            // From index 0 to 51, select two random cards within the deck
            int firstIndex = (int) (Math.random() * 52);     
            int secondIndex = (int) (Math.random() * 52);

 
            // Swap selected cards
            Card replace = myDeck[firstIndex];

            myDeck[firstIndex] = myDeck[secondIndex];
            myDeck[secondIndex] = replace;
        }
    }

    /**
     * Main method, generates the playing cards deck, outputs it
     * to console, shuffles it and prints it again.
     * @param args
     */
    public static void main(String[] args) {
        FullDeck deck = new FullDeck();

        System.out.println("\n" + "<<<<<<<<<< Before Shuffling >>>>>>>>>>");
        deck.printDeck();
       
        System.out.println("\n" + "<<<<<<<<<< After Shuffling >>>>>>>>>>");
        deck.shuffleDeck();
        deck.printDeck();
    }
}