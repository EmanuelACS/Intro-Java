package app;

/**
 * War Class, a class that randomly selects two playing cards and displays their values
 * using the Card class
 *
 * @author Emanuel.Dobra
 * @since 2020-2-10
 */
public class War {
    /**
     * The getSuit method takes in a int card value and translates it to it's sequential suit string
     * 
     * @param cardValue The card's suit value
     * @return The corresponding suit string of the value
     */
    public String getSuit(String cardValue){
        // Four possible values that correspond to a card suit
        switch(cardValue) {
            case "1":     return "d";
            case "2":     return "s";
            case "3":     return "c";
            case "4":     return "h";
            // If the a value doesn't correspond to any suit
            default:    return "Invalid Card Suit"; 
        }
    } 

    /**
     * The randomValue method takes in the two ints, one for the upper bound and one for 
     * the lower bound. It then generates a random number between those two (inclusive)
     * 
     * @param lowerBound Minimum value for the random int generator
     * @param upperBound Maximum value for the random int generator
     * @return The value randomly generated between the lower and upper bounds 
     */
    public int randomValue(int lowerBound, int upperBound) {
        return (int) (Math.random() * (upperBound + 1 - lowerBound)) + lowerBound;
    }

    /**
     * Determine who wins the war between the computer and human
     * by comparing their cards
     * 
     * @param computerCard The comptuer's card
     * @param humanCard The users' card
     * @return The winner after comparing cards
     */
    public String warWinner(Card computerCard, Card humanCard) {
        if (computerCard.getCardValue() > humanCard.getCardValue()) {
            return "Computer Wins";
        } else if (humanCard.getCardValue() > computerCard.getCardValue()) {
            return "User Wins";
        } else {
            return "Tie";
        }
    }

    /**
     * If both players have the same card, change the suit of the user's suit to a random one.
     * 
     * @param computerCardValue AI's card value
     * @param humanCardValue User's card value
     * @param computerCardSuit AI's card suit
     * @param humanCardSuit User's card suit
     */
    public void checkDuplicate(Card computerCard, Card humanCard) {
        while (computerCard.getCardValue() == humanCard.getCardValue() && computerCard.getCardSuit() == humanCard.getCardSuit()) {
            humanCard.setCardSuit(getSuit(Integer.toString(randomValue(1,4)))); 
        }
    } 

    /**
     * The printCard method takes in a value and a suit and returns the card
     * correspoding to the given values
     * 
     * @param value value of the card
     * @param suit  suit of the card
     * @return card string output
     */
    public String printCard(String value, String suit) {
        return (value + " of " + suit);
    }

    public void warResult(Card computerCard, Card humanCard) {
        String cardOneString = printCard(computerCard.getCardValueName(), computerCard.getCardSuitName());
        String cardTwoString = printCard(humanCard.getCardValueName(), humanCard.getCardSuitName());
        String winner = warWinner(computerCard, humanCard);

        System.out.printf("%-20s %-20s %-11s %n", "Computer Player", "Human Player", "Result");
        System.out.printf("%-20s %-20s %-11s %n", cardOneString, cardTwoString, winner);
    }

    /**
     * Main method, pits the computer against the user by comparing their cards.  
     * 
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        War war1 = new War();

        // First and second playing card's values
        int computerCardValue = war1.randomValue(1,13), humanCardValue = war1.randomValue(1,13);

        // First and second playing card's suits
        String computerCardSuit = war1.getSuit(Integer.toString(war1.randomValue(1,4))), humanCardSuit = war1.getSuit(Integer.toString(war1.randomValue(1,4)));   

        // Generating the two card objects
        Card computerCard = new Card(computerCardValue, computerCardSuit);
        Card humanCard = new Card(humanCardValue, humanCardSuit);

        // Check for duplicate cards
        war1.checkDuplicate(computerCard, humanCard);

        // Start simplified war game
        war1.warResult(computerCard, humanCard);
    }
}

