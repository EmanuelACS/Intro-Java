package app;

public class War2 {
    /**
     * Either discard or compare the next card that is shown.
     * 
     * @param computerCard AI's card
     * @param humanCard User's card
     * @param discard Whether the card is to be discarded or not
     */
    public void nextCard(Card computerCard, Card humanCard, Boolean discard) {
        War next = new War();

        String cardOneString = next.printCard(computerCard.getCardValueName(), computerCard.getCardSuitName());
        String cardTwoString = next.printCard(humanCard.getCardValueName(), humanCard.getCardSuitName());
        String winner = next.warWinner(computerCard, humanCard);

        if (discard) {
            System.out.printf("%-20s %-20s %-11s %n", cardOneString, cardTwoString, "Discard");
        } else {
            System.out.printf("%-20s %-20s %-11s %n", cardOneString, cardTwoString, winner);
        }

    }

    /**
     * Declare war if there is a tie, the two players discard 10 cards each and compare the 11th card.
     * If there is another tie, declare war again until a player wins
     * 
     * @param computerCard AI's card
     * @param humanCard User's card
     */
    public void declareWar(Card computerCard, Card humanCard) {
        War tie = new War();

        String winner = tie.warWinner(computerCard, humanCard);

        while (winner == "Tie") {

            System.out.println("WAR is CALLED!");

            // Loop through 11 cards
            for (int i = 0; i < 11; i++) {
                // Select new cards
                computerCard.setCardSuit(tie.getSuit(Integer.toString(tie.randomValue(1,4))));
                computerCard.setCardValue(tie.randomValue(1,13));
                humanCard.setCardSuit(tie.getSuit(Integer.toString(tie.randomValue(1,4))));
                humanCard.setCardValue(tie.randomValue(1,13));

                // Check for duplicates
                tie.checkDuplicate(computerCard, humanCard);

                // Discard the first 10 cards and compare the 11th
                if (i < 10) {
                    nextCard(computerCard, humanCard, true);
                } else {
                    winner = tie.warWinner(computerCard, humanCard);
                    nextCard(computerCard, humanCard, false);
                }
            } 
        }
    }
    /**
     * Main method, pits the computer against the user by comparing their cards. 
     * If there is a tie, declares war until there is a winner.
     * 
     * @param args
     */
	public static void main(String[] args) {
        War war2 = new War();
        War2 declaration = new War2();

        // First and second playing card's values
        int computerCardValue = war2.randomValue(1,13), humanCardValue = war2.randomValue(1,13);

        // First and second playing card's suits
        String computerCardSuit = war2.getSuit(Integer.toString(war2.randomValue(1,4))), humanCardSuit = war2.getSuit(Integer.toString(war2.randomValue(1,4)));   

        // Generating the two card objects
        Card computerCard = new Card(computerCardValue, computerCardSuit);
        Card humanCard = new Card(humanCardValue, humanCardSuit);

        // Check for duplicate cards
        war2.checkDuplicate(computerCard, humanCard);

        // Start simplified war game
        war2.warResult(computerCard, humanCard);

        // If there is a tie, declare war t'ill there is a winner 
        declaration.declareWar(computerCard, humanCard);
    }
}