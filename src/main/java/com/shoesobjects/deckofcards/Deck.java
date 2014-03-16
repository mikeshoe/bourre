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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Class representing a standard Deck of 52 playing cards
 *
 * @author Mike Shoemaker (mike@shoesobjects.com)
 */
public class Deck {
    private static final int NUM_CARDS_IN_DECK = 52;
    private int currentCard = 0;
    private Card[] cards = new Card[NUM_CARDS_IN_DECK];
    private CardSuit[] suits = new CardSuit[4];
    private CardValue[] values = new CardValue[13];
    private Random random = new Random();

    public Deck() {
        initSuites();
        initValues();
        loadDeck();
    }

    public int getNumberOfCards() {
        return cards.length;
    }

    public int getNumberOfCardsRemaining() {
        return cards.length - currentCard;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(Arrays.asList(cards));
    }

    public Card draw() {
        Card card = cards[currentCard];
        currentCard++;
        return card;
    }

    public void shuffle() {
        int numCards = cards.length - 1;
        for (int i = numCards; i > 1; i--) {
            Card c = cards[i];
            int randomInt = random.nextInt(i);
            cards[i] = cards[randomInt];
            cards[randomInt] = c;
        }
    }

    private void loadDeck() {
        int deckPos = 0;
        for (CardSuit suit : suits) {
            for (CardValue value : values) {
                cards[deckPos] = new Card(suit, value);
                deckPos++;
            }
        }
    }

    private void initValues() {
        values[0] = CardValue.TWO;
        values[1] = CardValue.THREE;
        values[2] = CardValue.FOUR;
        values[3] = CardValue.FIVE;
        values[4] = CardValue.SIX;
        values[5] = CardValue.SEVEN;
        values[6] = CardValue.EIGHT;
        values[7] = CardValue.NINE;
        values[8] = CardValue.TEN;
        values[9] = CardValue.JACK;
        values[10] = CardValue.QUEEN;
        values[11] = CardValue.KING;
        values[12] = CardValue.ACE;

    }

    private void initSuites() {
        suits[0] = CardSuit.CLUB;
        suits[1] = CardSuit.DIAMOND;
        suits[2] = CardSuit.HEART;
        suits[3] = CardSuit.SPADE;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Card c : cards) {
            stringBuilder.append(c.toString()).append("\n");
        }
        return stringBuilder.toString();
    }

}
