public class WarGame {
    Player player_1, player_2;
    Deck waitDeck_1, waitDeck_2,primaryDeck;
    Player startingPlayer, secondPlayer;


    //constructor
    public WarGame(String name_1, String name_2) {
        this.player_1.setName(name_1);
        this.player_2.setName(name_2);
        waitDeck_1 = new Deck(false);
        waitDeck_2 = new Deck(false);
        primaryDeck = new Deck(true);
    }

    public Player getPlayer_1() {
        return player_1;
    }

    public Player getPlayer_2() {
        return player_2;
    }

    public void setPlayer_1(Player player) {
        this.player_1 = player;
    }

    public void setPlayer_2(Player player) {
        this.player_2 = player;
    }

    public void initializeGame() {
        System.out.println("Initializing the Game...");
        primaryDeck.shuffle();

        // decideing who is the starting player
        if (player_1.getName().compareTo(player_2.getName()) > 0) {
            startingPlayer = player_1;
            secondPlayer = player_2;
        }
        // we dont refer to  the case in which the names are identical - because it is given in the question
        else {
            startingPlayer = player_2;
            secondPlayer = player_1;



        }
        // while the game deck is not empty, hand out cards to players
        while (!primaryDeck.deck.isEmpty()) {
            startingPlayer.addCardToDeck(startingPlayer.playingDeck, primaryDeck);
            secondPlayer.addCardToDeck(startingPlayer.playingDeck, primaryDeck);
        }
    }

    public String start () {
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
        return "bla";
    }

}