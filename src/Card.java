public class Card {
    private int number;
    private String cardShape;
    int or_sekler = 5;

    // constractor
    public Card(int number, String cardShape){
        setNumber(number);
        setCardShape(cardShape);
    }

    public void setNumber(int value){
        this.number = value;
    }
    public int getNumber(){
        return number;
    }

    public void setCardShape(String value){
        this.cardShape = value;
    }
    public String getCardShape(){
        return cardShape;
    }

    public int Compare(Card other){
        // return -1 if the current card is smaller than the other card
        if (this.number < other.number){
            return -1;
        }
        // return -1 if the current card is equal than the other card
        else if(this.number == other.number){
            return 0;
        }
        // return -1 if the current card is bigger than the other card
        return 1;
    }

    @Override
    public String toString() {
        // if the card is a prince
        if (this.number == 11){
            return "Prince of " + cardShape + '\'';
        }
        // if the card is a queen
        if (this.number == 12){
            return "Queen of " + cardShape + '\'';
        }
        // if the card is a king
        if (this.number == 13){
            return "King of " + cardShape + '\'';
        }
        // if the card is an ace
        if (this.number == 1){
            return "Ace of " + cardShape + '\'';
        }
        // if the card is a regular number
        return number + " of " + cardShape + '\'';
    }
}
