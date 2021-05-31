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

   //TO DO: public addCardToDeck(Deck toDeck, Deck fromDeck, Card card){

   // }

    public void drawCard(){
        playingDeck.removeTopCard();
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
