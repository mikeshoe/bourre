/*
 * ---------------------------------------------------------------------------------
 * Copyright (c) 2014  Mike Shoemaker (mike@shoesobjects.com)
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; If not, see <http://www.gnu.org/licenses/>
 * ---------------------------------------------------------------------------------
 *
 */


package com.shoesobjects.bourre.game;

import com.shoesobjects.deckofcards.Card;
import com.shoesobjects.deckofcards.CardSuit;
import com.shoesobjects.deckofcards.CardValue;
import com.shoesobjects.deckofcards.FiveCardHand;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class BourreHand extends FiveCardHand {


    public int getNumberOfHigherTrumpCards(Card trumpCard) {
        int numHigherTrump = 0;
        Iterator iter = cardList.iterator();
        while (iter.hasNext()) {
            Card c = (Card) iter.next();
            if (c.getCardSuite() == trumpCard.getCardSuite() &&
                    c.getCardValue().isHigher(trumpCard.getCardValue())) {
                numHigherTrump++;
            }
        }

        return numHigherTrump;
    }

    public int getNumberOfLowerTrumpCards(Card trumpCard) {
        int numLowerTrump = 0;
        Iterator iter = cardList.iterator();
        while (iter.hasNext()) {
            Card c = (Card) iter.next();
            if (c.getCardSuite() == trumpCard.getCardSuite() &&
                    c.getCardValue().isLower(trumpCard.getCardValue())) {
                numLowerTrump++;
            }
        }

        return numLowerTrump;
    }

    public boolean hasHighestTrumpCard(Card trumpCard) {
        Iterator iter = cardList.iterator();
        while (iter.hasNext()) {
            Card c = (Card) iter.next();
            if (c.getCardSuite() == trumpCard.getCardSuite() &&
                    c.getCardValue().equals(CardValue.ACE)) {
                return true;
            }
        }

        return false;

    }

    public boolean hasFaceTrumpCard(Card trumpCard) {
        Card highestTrump = null;
        Iterator iter = cardList.iterator();
        while (iter.hasNext()) {
            Card c = (Card) iter.next();
            if (c.getCardSuite() == trumpCard.getCardSuite()) {
                if (highestTrump == null) {
                    highestTrump = c;
                } else if (c.getCardValue().isHigher(highestTrump.getCardValue())) {
                    highestTrump = c;
                }
            }
        }

        if (highestTrump == null) return false;

        return highestTrump.getCardValue().isHigher(CardValue.TEN);
    }

    public List getCards() {
        return cardList;
    }

    public List getCards(Card.Status status) {
        List cards = new ArrayList();
        Iterator iter = cardList.iterator();
        while (iter.hasNext()) {
            Card c = (Card) iter.next();
            if (c.getStatus().equals(status)) {
                cards.add(c);
            }
        }

        return cards;
    }

    public int getNumberOfCards(Card.Status status) {
        return getCards(status).size();
    }


    public boolean hasSuit(CardSuit suit) {
        Iterator iter = cardList.iterator();
        while (iter.hasNext()) {
            Card c = (Card) iter.next();
            if (c.getCardSuite().equals(suit)) {
                return true;
            }
        }

        return false;
    }

    public Card getHighestCardOfSuit(CardSuit suit) {
        Card highest = null;
        Iterator iter = cardList.iterator();
        while (iter.hasNext()) {
            Card c = (Card) iter.next();
            if (c.getCardSuite().equals(suit)) {
                if (highest == null) {
                    highest = c;
                } else if (c.getCardValue().isHigher(highest.getCardValue())) {
                    highest = c;
                }
            }
        }

        return highest;
    }

    public Card getLowestCardOfSuit(CardSuit suit) {
        Card lowest = null;
        Iterator iter = cardList.iterator();
        while (iter.hasNext()) {
            Card c = (Card) iter.next();
            if (c.getCardSuite().equals(suit)) {
                if (lowest == null) {
                    lowest = c;
                } else if (lowest.getCardValue().isHigher(c.getCardValue())) {
                    lowest = c;
                }
            }
        }

        return lowest;
    }

    public Card getHighestOffSuitCard(CardSuit trumpSuit) {
        Card highest = null;
        Iterator iter = cardList.iterator();
        while (iter.hasNext()) {
            Card c = (Card) iter.next();
            if (!c.getCardSuite().equals(trumpSuit)) {
                if (highest == null) {
                    highest = c;
                } else if (c.getCardValue().isHigher(highest.getCardValue())) {
                    highest = c;
                }
            }
        }

        return highest;
    }

    public Card getLowestOffSuitCard(CardSuit trumpSuit) {
        Card lowest = null;
        Iterator iter = cardList.iterator();
        while (iter.hasNext()) {
            Card c = (Card) iter.next();
            if (!c.getCardSuite().equals(trumpSuit)) {
                if (lowest == null) {
                    lowest = c;
                } else if (c.getCardValue().isLower(lowest.getCardValue())) {
                    lowest = c;
                }
            }
        }

        return lowest;
    }


    public String toString() {
        StringBuilder builder = new StringBuilder();
        List list = this.getCards();
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            Card c = (Card) iter.next();
            builder.append(c.toString());
            builder.append(" ");
        }
        return builder.toString();
    }

    public void removePlayedCard(Card playedCard) {
        this.cardList.remove(playedCard);
    }


}
