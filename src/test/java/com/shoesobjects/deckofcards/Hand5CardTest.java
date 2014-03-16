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


public class Hand5CardTest extends TestCase {

    public void testHandWithTooManyCards() {
        Deck deck = new Deck();
        deck.shuffle();

        Hand hand = new FiveCardHand();
        try {
            for (int i = 0; i < 6; i++) {
                hand.addCard(deck.draw());
            }
            fail("TooManyCardsRuntimeException should have occurred");
        } catch (TooManyCardsRuntimeException e) {    //NOPMD
            // RuntimeException Should be thrown
        }
    }

    public void testMaxNumberCardsInHand() {
        FiveCardHand hand = new FiveCardHand();
        assertEquals(5, hand.getMaxNumberCards());
    }

    public void testHandIsCompleteFalse() {
        FiveCardHand hand = new FiveCardHand();
        Deck deck = new Deck();
        deck.shuffle();

        // add just one card
        hand.addCard(deck.draw());

        assertFalse(hand.isComplete());
    }

    public void testHandIsCompleteTrue() {
        FiveCardHand hand = new FiveCardHand();
        Deck deck = new Deck();
        deck.shuffle();

        // add five cards
        for (int i = 0; i < 5; i++) {
            hand.addCard(deck.draw());
        }
        assertTrue(hand.isComplete());
    }

    public void testHandHasCard() {
        FiveCardHand hand = new FiveCardHand();
        hand.addCard(new Card(CardSuit.CLUB, CardValue.ACE));
        assertTrue(hand.hasCard(new Card(CardSuit.CLUB, CardValue.ACE)));
    }

    public void testHandDoesNotHaveCard() {
        FiveCardHand hand = new FiveCardHand();
        hand.addCard(new Card(CardSuit.CLUB, CardValue.ACE));
        assertFalse(hand.hasCard(new Card(CardSuit.SPADE, CardValue.ACE)));

    }
}
