package app;

/**
 * The full game of war with distinct playing cards and deck.
 * The computer and the player compare cards 26 times if
 * there is no tie. If a tie is in place, war is declared if
 * there are enough cards left in the deck. The game is played 
 * until there are no more cards within the deck.
 * 
 * @author Emanuel.Dobra
 * @since 2020-3-23
 */
public class War3 {

    /**
     * Removes a card from the deck by making it unnacessible.
     * Every card above the index of the current card are
     * shifted down one position until the current card is at 
     * the top, and decreases the cardsLeftIndex by one to make
     * that card out of reach.
     * 
     * @param index Index of the card to be removed
     * @param deck FullDeck object containing our deck
     * @param cardsLeftIndex Total usable index of cards within our deck
     * @return the index of the last card within the deck that has not been removed
     */
    public int removeCard(int index, FullDeck deck, int cardsLeftIndex) {
        // Keeping track of the current card to remove
        Card temp = deck.myDeck[index];

        // Shifts the position down of every card above by 1
        for (int i = index; i < cardsLeftIndex; i++) {
            deck.myDeck[i] = deck.myDeck[i+1];
        }
        
       
        cardsLeftIndex--; // Makes the card out of reach

        /**
         * Places the card at the top so that the deck is more asthetic. 
         * I could have chosen to make every card object that was discarded
         * become "Invalid", but this seemed better as it more realistically
         * represents a card deck without the ability to trim down the actual array.
         */
        deck.myDeck[cardsLeftIndex+1] = temp; 

        return cardsLeftIndex;
    }

    /**
     * Makes sure both cards being selected are different.
     * If not,
     * @param computerIndex
     * @param playerIndex
     * @param cardsLeftIndex
     */
    public void checkDuplicate(int computerIndex, int playerIndex, int cardsLeftIndex) {
        while (computerIndex == playerIndex) {
            computerIndex = (int) (Math.random() * cardsLeftIndex+1);
            playerIndex = (int) (Math.random() * cardsLeftIndex+1);
        }
    }

    /**
     * The checkTie method checks if there is a tie, and if so
     * declares war if there are enough cards left in the deck.
     * 
     * @param deck FullDeck object containing the deck 
     * @param cardsLeftIndex Total usable index of cards within our deck
     * @param computerCard AI's card
     * @param humanCard Player's card
     * @return the index of the last usable card
     */
    public int checkTie(FullDeck deck, int cardsLeftIndex, Card computerCard, Card humanCard) {
        War tie = new War();
        War2 printActions = new War2();

        // String containing the result of the current bout
        String winner = tie.warWinner(computerCard, humanCard);

        int computerIndex;
        int playerIndex;

        if (winner == "Tie") {
            // Check if there are enough cards 
            if ( cardsLeftIndex >= 22) { 
                System.out.println("WAR is CALLED!");
                for (int i = 0; i < 11; i++) {
                    // Randomizes index of the cards being chosen
                    computerIndex = (int) (Math.random() * cardsLeftIndex+1);
                    playerIndex = (int) (Math.random() * cardsLeftIndex+1);

                    // Make sure that both players don't get the same card
                    while (computerIndex == playerIndex) { 
                        computerIndex = (int) (Math.random() * cardsLeftIndex+1);
                        playerIndex = (int) (Math.random() * cardsLeftIndex+1);
                    }

                    // Assigns the cards at the correct index
                    computerCard = deck.myDeck[computerIndex];
                    humanCard = deck.myDeck[playerIndex];

                    // Remove the cards and keep record of the index of last playable card 
                    cardsLeftIndex = removeCard(playerIndex, deck, cardsLeftIndex);
                    cardsLeftIndex = removeCard(computerIndex, deck, cardsLeftIndex);    

                    // Discard first 10 cards
                    if (i < 10) {
                        printActions.nextCard(computerCard, humanCard, true);
                    } else { 
                        // Compare the 11th cards of each player
                        winner = tie.warWinner(computerCard, humanCard);
                        printActions.nextCard(computerCard, humanCard, false); 
                    }
                } 
            }
            else {
                // If there are not enough cards, skip declaring war.
                System.out.println("Not enough cards left to declare war!");
                System.out.println("Game will continue, this round is a tie.");
            }
        }
        return cardsLeftIndex;
    }

    /**
     * 
     * @param warDeck current FullDeck object being used containing the deck
     * @param results keeping track of the score
     * @return returns the score
     */
    public int[] fullFight(FullDeck warDeck, int[] results) {
        // War object to use its methods
        War checkWinner = new War();

        // Keep count of usable cards
        int cardsLeftIndex = 51; 

        // Compare cards 26 times if there is no tie
        for (int i = 0; i < 26; i++) {        
            if (cardsLeftIndex > 1) {
                int computerIndex = (int) (Math.random() * cardsLeftIndex+1);
                int playerIndex = (int) (Math.random() * cardsLeftIndex+1);
                
                // Make sure chosen indexes aren't the same
                while (computerIndex==playerIndex) { 
                    computerIndex = (int) (Math.random() * cardsLeftIndex+1);
                    playerIndex = (int) (Math.random() * cardsLeftIndex+1);
                }

                Card computerCard = warDeck.myDeck[computerIndex];
                Card humanCard = warDeck.myDeck[playerIndex];

                checkWinner.warResult(computerCard, humanCard, results);
                cardsLeftIndex = removeCard(playerIndex, warDeck, cardsLeftIndex);
                cardsLeftIndex = removeCard(computerIndex, warDeck, cardsLeftIndex);
                cardsLeftIndex = checkTie(warDeck, cardsLeftIndex, computerCard, humanCard);

            } else if (cardsLeftIndex == 1) {
                // Assigning the last two cards to the user and computer
                // Since the cards are already shuffled, it does not matter
                // that I know the indexes, as either card could be higher than the other.
                Card computerCard = warDeck.myDeck[0];
                Card humanCard = warDeck.myDeck[1];

                checkWinner.warResult(computerCard, humanCard, results);

                // Game has ended, so loop has no more reason to run
                i = 99;

                System.out.println();
                System.out.println("The game has ended, there are no more cards in the deck!");
                System.out.printf("%-20s %-20s %-11s %n", "Computer Wins: ", "Player Wins: ", "Ties: ");
                System.out.printf("%-20s %-20s %-11s %n", results[0], results[1], results[2]);
            }
        }
        return results;
    }

    /**
     * Main method which generates and shuffles the deck,
     * keeps record of results and starts the full war.
     * 
     * @param args
     */
	public static void main(String[] args) {
        FullDeck warDeck = new FullDeck();
        warDeck.shuffleDeck();

        War3 warActions = new War3();
        int[] results = {0,0,0};
        warActions.fullFight(warDeck, results);
    }
}