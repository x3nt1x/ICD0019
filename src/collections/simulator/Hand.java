package collections.simulator;

import java.util.*;

public class Hand implements Iterable<Card>, Comparable<Hand>
{
    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card)
    {
        cards.add(card);
    }

    @Override
    public String toString()
    {
        return cards.toString();
    }

    public HandType getHandType()
    {
        Collections.sort(cards);

        if (isStraightFlush()) {
            return HandType.STRAIGHT_FLUSH;
        }
        else if (isFourOfKind()) {
            return HandType.FOUR_OF_A_KIND;
        }
        else if (isFullHouse()) {
            return HandType.FULL_HOUSE;
        }
        else if (isFlush()) {
            return HandType.FLUSH;
        }
        else if (isStraight()) {
            return HandType.STRAIGHT;
        }
        else if (isThreeOfKind()) {
            return HandType.TRIPS;
        }
        else if (isTwoPair()) {
            return HandType.TWO_PAIRS;
        }
        else if (isOnePair()) {
            return HandType.ONE_PAIR;
        }

        return HandType.HIGH_CARD;
    }

    private boolean isStraightFlush()
    {
        for (var i = 0; i < cards.size() - 1; i++)
        {
            var current = cards.get(i);
            var next = cards.get(i + 1);

            if (next.getSuit() != current.getSuit()) {
                return false;
            }

            if (next.compareTo(current) != 1) {
                return false;
            }
        }

        return true;
    }

    private boolean isFullHouse()
    {
        if (cards.size() != 5) {
            return false;
        }

        var checked = new HashSet<Card.CardValue>();

        for (var card : cards) {
            checked.add(card.getValue());
        }

        return checked.size() == 2;
    }

    private boolean isFlush()
    {
        for (var i = 0; i < cards.size() - 1; i++)
        {
            var current = cards.get(i);
            var next = cards.get(i + 1);

            if (next.getSuit() != current.getSuit()) {
                return false;
            }
        }

        return true;
    }

    private boolean isStraight()
    {
        if (cards.size() != 5) {
            return false;
        }

        for (var i = 0; i < cards.size() - 1; i++)
        {
            var current = cards.get(i);
            var next = cards.get(i + 1);

            // two aces can't make straight
            if (current.getValue() == Card.CardValue.A && next.getValue() == Card.CardValue.A) {
                return false;
            }

            if (next.getValue() == Card.CardValue.A) {
                continue;
            }

            if (next.compareTo(current) != 1) {
                return false;
            }
        }

        return true;
    }

    private HashMap<Card.CardValue, Integer> getFilteredCards()
    {
        var result = new HashMap<Card.CardValue, Integer>();
        var values = cards.stream().map(Card::getValue).toList();

        for (var value : values)
        {
            if (!result.containsKey(value)) {
                result.put(value, Collections.frequency(values, value));
            }
        }

        return result;
    }

    private boolean isFourOfKind()
    {
        return getFilteredCards().values().stream().anyMatch(value -> value == 4);
    }

    private boolean isThreeOfKind()
    {
        return getFilteredCards().values().stream().anyMatch(value -> value == 3);
    }

    private boolean isTwoPair()
    {
        return getFilteredCards().values().stream().filter(value -> value == 2).count() == 2;
    }

    private boolean isOnePair()
    {
        return getFilteredCards().values().stream().anyMatch(value -> value == 2);
    }

    public boolean contains(Card card)
    {
        return cards.contains(card);
    }

    public boolean isEmpty()
    {
        return cards.isEmpty();
    }

    @Override
    public Iterator<Card> iterator()
    {
        return cards.iterator();
    }

    @Override
    public int compareTo(Hand other)
    {
        return 0;
    }
}