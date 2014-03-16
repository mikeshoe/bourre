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


package com.shoesobjects.deckofcards;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete Class representing the cards in a players fiver card hand
 *
 * @author Mike Shoemaker (mike@shoesobjects.com)
 */
public class FiveCardHand implements Hand {
    private static final int MAX_CARDS = 5;
    protected final List<Card> cardList = new ArrayList<Card>();

    public void addCard(Card card) throws TooManyCardsRuntimeException {
        if (cardList.size() < MAX_CARDS) {
            this.cardList.add(card);
        } else {
            throw new TooManyCardsRuntimeException();
        }
    }

    public int getMaxNumberCards() {
        return MAX_CARDS;
    }

    public boolean isComplete() {
        return cardList.size() == MAX_CARDS;
    }

    public boolean hasCard(Card card) {
        return cardList.contains(card);
    }

    public Card getCardAtPosition(int position) {
        Card c = (Card) cardList.get(position);
        return new Card(c.getCardSuite(), c.getCardValue());
    }

    public String toString() {
        return "FiveCardHand{" +
                "cardList=" + cardList +
                '}';
    }
}
