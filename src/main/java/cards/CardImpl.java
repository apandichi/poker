package cards;

import java.io.Serializable;

public class CardImpl implements Card, Serializable {
    private static final long serialVersionUID = 347670127999292367L;
    private final Rank rank;
    private final Suit suit;

    public CardImpl(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public Rank getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardImpl card = (CardImpl) o;

        if (rank != card.rank) return false;
        if (suit != card.suit) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rank.hashCode();
        result = 31 * result + suit.hashCode();
        return result;
    }

    @Override
    public Suit getSuit() {
        return suit;
    }

    @Override
    public int compareTo(Card o) {
        return getRank().compareTo(o.getRank());
    }
    
    @Override
    public String toString() {
    	return getRank() + "." + getSuit();
    }
}
