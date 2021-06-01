public class Player {
    private String name;
    Deck playingDeck;
    Deck winingDeck;

    // constractor
    public Player(String name){
        this.name = name;
        playingDeck = new Deck(false);
        winingDeck = new Deck(false);
    }

    public void setName(String value){
        this.name = value;
    }
    public String getName(){
        return name;
    }

    // taking the top card from 1 deck and adding it to the second deck
    public void addCardToDeck(Deck toDeck, Deck fromDeck){

        toDeck.addCard(fromDeck.removeTopCard());
    }

    public Card drawCard(){

        return playingDeck.removeTopCard();
    }

    public boolean outOfCards(){
        if (playingDeck.isEmpty() && winingDeck.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
