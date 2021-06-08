import java.util.Collection;
import java.util.Collections;

public class WarGame {
    //Player player_1, player_2;
    Deck turnDeck_1, turnDeck_2, primaryDeck;
    Player startingPlayer, secondPlayer, tempPlayer, gameWinner, roundWinner;


    //constructor
    public WarGame(String name_1, String name_2) {
        startingPlayer = new Player(name_1);
        secondPlayer = new Player(name_2);
        gameWinner = new Player("winner");
        roundWinner = new Player("winner");
        turnDeck_1 = new Deck(false);
        turnDeck_2 = new Deck(false);
        primaryDeck = new Deck(true);
    }

    public Player getStartingPlayer() {

        return startingPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public void setStartingPlayer(Player player) {
        this.startingPlayer = player;
    }

    public void setSecondPlayer(Player player) {
        this.secondPlayer = player;
    }

    public void initializeGame() {
        System.out.println("Initializing the game...");
        primaryDeck.shuffle();

        // decideing who is the starting player
        if (startingPlayer.getName().compareTo(secondPlayer.getName()) > 0) {
            tempPlayer = startingPlayer;
            setStartingPlayer(secondPlayer);
            setSecondPlayer(tempPlayer);
        }
        // we dont refer to  the case in which the names are identical - because it is given in the question
        // in case the players order ig good - do nothing

        // while the game deck is not empty, hand out cards to players
        while (!primaryDeck.deck.isEmpty()) {
            startingPlayer.addCardToDeck(startingPlayer.playingDeck, primaryDeck);
            secondPlayer.addCardToDeck(secondPlayer.playingDeck, primaryDeck);
        }
    }


    // checks if the game ends
    public boolean checkEndGame(Player player_1, Player player_2, boolean isStartingPlayer){
        // this function checks if the player's decks are empty:
        if (player_1.outOfCards()) {
            gameWinner = player_2;
            return false;
        }
        // if the playing deck is empty and the winning deck is not empty, it puts the cards from the winning deck in the playing deck
        if (player_1.playingDeck.isEmpty()) {
            player_1.winingDeck.shuffle();
            player_1.playingDeck = player_1.winingDeck;
            player_1.winingDeck = new Deck(false);
        }
        // if they are not empty, the game can go on and the player draw a card
        if (isStartingPlayer) {
            player_1.addCardToDeck(turnDeck_1, player_1.playingDeck);
        }
        else {
            player_1.addCardToDeck(turnDeck_2, player_1.playingDeck);
        }
        return true;
    }

    public boolean isInWar(Card card_1, Card card_2) {
        //prints the cards the players drew
        System.out.println(startingPlayer.getName() + " drew " + card_1);
        System.out.println(secondPlayer.getName() + " drew " + card_2);

        // if starting player won the round
        if (card_1.compare(card_2) == 1) {
            // adding the cards in the turn decks to starting player winning deck
            while (!(turnDeck_2.isEmpty() && turnDeck_1.isEmpty())) {
                startingPlayer.addCardToDeck(startingPlayer.winingDeck, turnDeck_2);
                startingPlayer.addCardToDeck(startingPlayer.winingDeck, turnDeck_1);
            }
            //changing round winner to starting player
            roundWinner = startingPlayer;
            return false;
        }

        // if second player won the round
        else if (card_1.compare(card_2) == -1) {
            // adding the cards in the turn decks to second player winning deck
            while (!(turnDeck_2.isEmpty() && turnDeck_1.isEmpty())) {
                secondPlayer.addCardToDeck(secondPlayer.winingDeck, turnDeck_2);
                secondPlayer.addCardToDeck(secondPlayer.winingDeck, turnDeck_1);
            }

            //changing the round winner to the second player
            roundWinner = secondPlayer;
            return false;
        }

        // if the cards are equal - entering a WAR!!
        else {
            warRound();
            return true;
        }
    }

    public void warRound() {
        System.out.println("Starting a war...");

        // drawing the 2 war cards for each player
        for(int i = 0; i < 2; i++) {
            // check if the game didn't end during the drawing of the cards
            if(checkEndGame(startingPlayer, secondPlayer, true)) {
                System.out.println(startingPlayer.getName() + " drew a war card");
            }
            else {
                return;
            }

            // check if the game didn't end during the drawing of the cards
            if(checkEndGame(secondPlayer, startingPlayer, false)) {
                System.out.println(secondPlayer.getName() + " drew a war card");
            }
            else {
                return;
            }
        }

        // checks if the game didn't end in the 3rd card draw
        if(checkEndGame(startingPlayer, secondPlayer, true)) {
            if (checkEndGame(secondPlayer,startingPlayer, false)){

                //checks who won the current war
                isInWar(turnDeck_1.deck.get(turnDeck_1.deck.size()-1),
                        turnDeck_2.deck.get(turnDeck_2.deck.size()-1));
            }
        }
    }

    public String start() {
        int roundCounter = 0;

        // initializing the game
        initializeGame();

        // while no one won the game, play another turn
        while (gameWinner.getName().equals("winner")) {

            // checks if no one of the players ran out of cards
            if(checkEndGame(startingPlayer, secondPlayer, true)) {
                if (checkEndGame(secondPlayer,startingPlayer, false)){
                    roundCounter++;
                    System.out.println("------------------------- Round number " +
                            roundCounter + " -------------------------");

                    // checks if we entered a war and decide who won the turn
                    boolean inWar = isInWar(turnDeck_1.deck.get(turnDeck_1.deck.size()-1),
                            turnDeck_2.deck.get(turnDeck_2.deck.size()-1));

                    // if no one ran out of cards in last turn, print who won it
                    if (gameWinner.getName().equals("winner")) {
                        if (!(inWar)) {
                            System.out.println(roundWinner.getName() + " won");
                        }
                        else{
                            System.out.println(roundWinner.getName() + " won the war");
                        }
                    }
                }
            }
        }

        // return the winner of the game
        return gameWinner.getName();
    }


















    /*public String start () {
        Card playingCard_1;
        Card playingCard_2;
        initializeGame();

        while (!(player_1.outOfCards() || player_2.outOfCards())) {
        // draw top card from the player playing deck and put in his pile
            playingCard_1 = startingPlayer.drawCard();
            waitDeck_1.addCard(playingCard_1);
            System.out.println(startingPlayer.getName() + " drew " + playingCard_1.toString());
            playingCard_2 = secondPlayer.drawCard();
            waitDeck_2.addCard(secondPlayer.drawCard());
            System.out.println(secondPlayer.getName() + " drew " + playingCard_2.toString());
            // changing the winning deck to the playing deck if the playing deck is empty
            if (startingPlayer.playingDeck.isEmpty()){
                startingPlayer.winingDeck.shuffle();
                startingPlayer.playingDeck = startingPlayer.winingDeck;
            }
            if (secondPlayer.playingDeck.isEmpty()){
                secondPlayer.winingDeck.shuffle();
                secondPlayer.playingDeck = startingPlayer.winingDeck;
            }
            // not a war turn
            // starting player won the turn
            if (playingCard_1.getNumber() > playingCard_2.getNumber()) {
                startingPlayer.addCardToDeck(startingPlayer.winingDeck, waitDeck_2);
                startingPlayer.addCardToDeck(startingPlayer.winingDeck, waitDeck_1);
                System.out.println(startingPlayer.getName() + " won ");
            }
            // second player won the turn
            else if (playingCard_1.getNumber() < playingCard_2.getNumber()) {
                secondPlayer.addCardToDeck(startingPlayer.winingDeck, waitDeck_2);
                secondPlayer.addCardToDeck(startingPlayer.winingDeck, waitDeck_1);
                System.out.println(secondPlayer.getName() + " won ");
            }
            // we entered WAR GAME
            else {
                while (playingCard_1.getNumber() == playingCard_2.getNumber()) {
                    System.out.println("Starting a war...");
                    // drawing the three war cards
                    for (int i = 0; i < 3; i++) {
                        playingCard_1 = startingPlayer.drawCard();
                        waitDeck_1.addCard(playingCard_1);
                        playingCard_2 = secondPlayer.drawCard();
                        waitDeck_2.addCard(secondPlayer.drawCard());
                    }
                    // printing the last war card
                    System.out.println(startingPlayer.getName() + " drew " + playingCard_1.toString());
                    System.out.println(secondPlayer.getName() + " drew " + playingCard_2.toString());

                    // starting player won the turn
                    if (playingCard_1.getNumber() > playingCard_2.getNumber()) {
                        for (int j = 0; j < 3; j++){
                            startingPlayer.addCardToDeck(startingPlayer.winingDeck, waitDeck_2);
                            startingPlayer.addCardToDeck(startingPlayer.winingDeck, waitDeck_1);
                        }
                    }
                    // second player won the turn
                    else if (playingCard_1.getNumber() < playingCard_2.getNumber()) {
                        for (int j = 0; j < 3; j++){
                            secondPlayer.addCardToDeck(startingPlayer.winingDeck, waitDeck_2);
                            secondPlayer.addCardToDeck(startingPlayer.winingDeck, waitDeck_1);
                        }
                    }
                }
            }
        }

    }*/

}