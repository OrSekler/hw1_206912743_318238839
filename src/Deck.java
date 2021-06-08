import java.util.ArrayList;

public class Deck{
    ArrayList<Card> deck = new ArrayList<Card>();

    // constractor
    public Deck(boolean createDeck){
        if(createDeck){
            // creating a new deck
            for (Shape shapeValue : Shape.values()){
                // creating 13 cardes from each shape
                for (int i = 0; i < 13; i++){
                    // adding a new card to the deck
                    deck.add(new Card(i+1, shapeValue.toString()));
                }
            }
        }
    }

    // adding the new card to the top of the list
    public void addCard(Card card){
        // adding the new card to the top of the list
        deck.add(0, card);
    }

    // removing the top card in the deck
    public Card removeTopCard(){
        Card lastInDeck = deck.get(deck.size()-1);
        deck.remove(deck.size()-1);

        return lastInDeck;
    }

    public boolean isEmpty(){
        // if the deck size is not 0, than it is not empty
        if (deck.size() != 0){
            return false;
        }

        return true;
    }

    public void shuffle(){
        Card temp;
        int rndIndex_1, rndIndex_2;

        for (int i = 0; i < 50; i++){
            rndIndex_1 = Main.rnd.nextInt();
            rndIndex_2 = Main.rnd.nextInt();

            temp = deck.get(rndIndex_1);
            // replacing the cards
            deck.set(rndIndex_1, deck.get(rndIndex_2));
            deck.set(rndIndex_2, temp);
        }
    }
}
