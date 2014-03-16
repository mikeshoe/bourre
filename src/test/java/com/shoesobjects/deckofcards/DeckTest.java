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


import junit.framework.TestCase;

import java.util.Iterator;
import java.util.List;


public class DeckTest extends TestCase {
    public void testDeckExists() {
        Deck deck = getDeck();
        assertNotNull(deck);
    }

    public void testDeckHas52Cards() {
        Deck deck = getDeck();
        assertEquals(52, deck.getNumberOfCards());
    }

    public void testDeckHas13OfEachSuiteBeforeShuffle() {
        Deck deck = getDeck();
        List cards = deck.getCards();
        Iterator i = cards.iterator();
        int hearts = 0;
        int spades = 0;
        int clubs = 0;
        int diamonds = 0;
        while (i.hasNext()) {
            Card c = (Card) i.next();
            if (c.getCardSuite().equals(CardSuit.CLUB)) {
                clubs++;
            } else if (c.getCardSuite().equals(CardSuit.DIAMOND)) {
                diamonds++;
            } else if (c.getCardSuite().equals(CardSuit.HEART)) {
                hearts++;
            } else if (c.getCardSuite().equals(CardSuit.SPADE)) {
                spades++;
            }
        }

        assertEquals(13, clubs);
        assertEquals(13, diamonds);
        assertEquals(13, hearts);
        assertEquals(13, spades);

    }

    public void testDeckHas13OfEachSuiteAfterShuffle() {
        Deck deck = getDeck();
        deck.shuffle();
        List cards = deck.getCards();
        Iterator i = cards.iterator();
        int hearts = 0;
        int spades = 0;
        int clubs = 0;
        int diamonds = 0;
        while (i.hasNext()) {
            Card c = (Card) i.next();
            if (c.getCardSuite().equals(CardSuit.CLUB)) {
                clubs++;
            } else if (c.getCardSuite().equals(CardSuit.DIAMOND)) {
                diamonds++;
            } else if (c.getCardSuite().equals(CardSuit.HEART)) {
                hearts++;
            } else if (c.getCardSuite().equals(CardSuit.SPADE)) {
                spades++;
            }
        }

        assertEquals(13, clubs);
        assertEquals(13, diamonds);
        assertEquals(13, hearts);
        assertEquals(13, spades);

    }

    public void testDeckHas4OfEachCardBeforeShuffle() {
        Deck deck = getDeck();
        List cards = deck.getCards();
        Iterator i = cards.iterator();
        int twos = 0;
        int threes = 0;
        int fours = 0;
        int fives = 0;
        int sixes = 0;
        int sevens = 0;
        int eights = 0;
        int nines = 0;
        int tens = 0;
        int jacks = 0;
        int queens = 0;
        int kings = 0;
        int aces = 0;
        while (i.hasNext()) {
            Card c = (Card) i.next();
            if (c.getCardValue().equals(CardValue.TWO)) {
                twos++;
            } else if (c.getCardValue().equals(CardValue.THREE)) {
                threes++;
            } else if (c.getCardValue().equals(CardValue.FOUR)) {
                fours++;
            } else if (c.getCardValue().equals(CardValue.FIVE)) {
                fives++;
            } else if (c.getCardValue().equals(CardValue.SIX)) {
                sixes++;
            } else if (c.getCardValue().equals(CardValue.SEVEN)) {
                sevens++;
            } else if (c.getCardValue().equals(CardValue.EIGHT)) {
                eights++;
            } else if (c.getCardValue().equals(CardValue.NINE)) {
                nines++;
            } else if (c.getCardValue().equals(CardValue.TEN)) {
                tens++;
            } else if (c.getCardValue().equals(CardValue.JACK)) {
                jacks++;
            } else if (c.getCardValue().equals(CardValue.QUEEN)) {
                queens++;
            } else if (c.getCardValue().equals(CardValue.KING)) {
                kings++;
            } else if (c.getCardValue().equals(CardValue.ACE)) {
                aces++;
            }
        }

        assertEquals(4, twos);
        assertEquals(4, threes);
        assertEquals(4, fours);
        assertEquals(4, fives);
        assertEquals(4, sixes);
        assertEquals(4, sevens);
        assertEquals(4, eights);
        assertEquals(4, nines);
        assertEquals(4, tens);
        assertEquals(4, jacks);
        assertEquals(4, queens);
        assertEquals(4, kings);
        assertEquals(4, aces);

    }

    public void testDeckHas4OfEachCardAfterShuffle() {
        Deck deck = getDeck();
        List cards = deck.getCards();
        Iterator i = cards.iterator();
        int twos = 0;
        int threes = 0;
        int fours = 0;
        int fives = 0;
        int sixes = 0;
        int sevens = 0;
        int eights = 0;
        int nines = 0;
        int tens = 0;
        int jacks = 0;
        int queens = 0;
        int kings = 0;
        int aces = 0;
        while (i.hasNext()) {
            Card c = (Card) i.next();
            if (c.getCardValue().equals(CardValue.TWO)) {
                twos++;
            } else if (c.getCardValue().equals(CardValue.THREE)) {
                threes++;
            } else if (c.getCardValue().equals(CardValue.FOUR)) {
                fours++;
            } else if (c.getCardValue().equals(CardValue.FIVE)) {
                fives++;
            } else if (c.getCardValue().equals(CardValue.SIX)) {
                sixes++;
            } else if (c.getCardValue().equals(CardValue.SEVEN)) {
                sevens++;
            } else if (c.getCardValue().equals(CardValue.EIGHT)) {
                eights++;
            } else if (c.getCardValue().equals(CardValue.NINE)) {
                nines++;
            } else if (c.getCardValue().equals(CardValue.TEN)) {
                tens++;
            } else if (c.getCardValue().equals(CardValue.JACK)) {
                jacks++;
            } else if (c.getCardValue().equals(CardValue.QUEEN)) {
                queens++;
            } else if (c.getCardValue().equals(CardValue.KING)) {
                kings++;
            } else if (c.getCardValue().equals(CardValue.ACE)) {
                aces++;
            }
        }

        assertEquals(4, twos);
        assertEquals(4, threes);
        assertEquals(4, fours);
        assertEquals(4, fives);
        assertEquals(4, sixes);
        assertEquals(4, sevens);
        assertEquals(4, eights);
        assertEquals(4, nines);
        assertEquals(4, tens);
        assertEquals(4, jacks);
        assertEquals(4, queens);
        assertEquals(4, kings);
        assertEquals(4, aces);

    }

    public void test2DecksOfUnshuffledCardsAreEqual() {
        Deck d1 = getDeck();
        List c1 = d1.getCards();
        Deck d2 = getDeck();
        List c2 = d2.getCards();
        assertTrue(c1.equals(c2));
    }

    public void test2DecksOfShuffledCardsAreNotEqual() {
        Deck d1 = getDeck();
        d1.shuffle();
        List c1 = d1.getCards();
        Deck d2 = getDeck();
        d2.shuffle();
        List c2 = d2.getCards();
        assertTrue(!c1.equals(c2));
    }

    public void testCardsRemainingNoShuffle() {
        Deck deck = getDeck();
        assertEquals(52, deck.getNumberOfCardsRemaining());
        deck.draw();
        assertEquals(51, deck.getNumberOfCardsRemaining());
    }

    public void test1stTwoCardsAreNotSame() {
        Deck deck = getDeck();
        deck.shuffle();
        Card c1 = deck.draw();
        Card c2 = deck.draw();
        assertTrue(!c1.equals(c2));

    }

    private Deck getDeck() {
        return new Deck();
    }


}
