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

import com.shoesobjects.bourre.BaseBourreGameTest;
import com.shoesobjects.deckofcards.Card;
import com.shoesobjects.deckofcards.CardSuit;
import com.shoesobjects.deckofcards.CardValue;


public class BourreHandTest extends BaseBourreGameTest {

    public void testBourreHandHasFiveCards() {
        BourreHand hand = new BourreHand();
        assertEquals(0, hand.getNumberOfCards(Card.Status.UNDECIDED));
        hand.addCard(new Card(CardSuit.CLUB, CardValue.EIGHT));
        hand.addCard(new Card(CardSuit.HEART, CardValue.EIGHT));
        hand.addCard(new Card(CardSuit.SPADE, CardValue.EIGHT));
        hand.addCard(new Card(CardSuit.DIAMOND, CardValue.EIGHT));
        hand.addCard(new Card(CardSuit.CLUB, CardValue.NINE));
        assertEquals(5, hand.getNumberOfCards(Card.Status.UNDECIDED));
    }

    public void testBourreHandCannotHaveMoreThanFiveCards() {
        BourreHand hand = new BourreHand();
        assertEquals(0, hand.getNumberOfCards(Card.Status.UNDECIDED));

        try {
            hand.addCard(new Card(CardSuit.CLUB, CardValue.EIGHT));
            hand.addCard(new Card(CardSuit.HEART, CardValue.EIGHT));
            hand.addCard(new Card(CardSuit.SPADE, CardValue.EIGHT));
            hand.addCard(new Card(CardSuit.DIAMOND, CardValue.EIGHT));
            hand.addCard(new Card(CardSuit.CLUB, CardValue.NINE));
            hand.addCard(new Card(CardSuit.CLUB, CardValue.TEN));
            fail("Cannot have 6 cards in a hand");
        } catch (Exception e) {
            // do nothing   
        }
    }


    public void testBourreHandHasSuit() {
        BourreHand hand = new BourreHand();
        hand.addCard(new Card(CardSuit.CLUB, CardValue.EIGHT));
        hand.addCard(new Card(CardSuit.HEART, CardValue.EIGHT));
        hand.addCard(new Card(CardSuit.CLUB, CardValue.TEN));
        hand.addCard(new Card(CardSuit.CLUB, CardValue.JACK));
        hand.addCard(new Card(CardSuit.CLUB, CardValue.NINE));

        assertTrue(hand.hasSuit(CardSuit.HEART));
    }

    public void testBourreHandDoesNotHaveSuit() {
        BourreHand hand = new BourreHand();
        hand.addCard(new Card(CardSuit.CLUB, CardValue.SEVEN));
        hand.addCard(new Card(CardSuit.CLUB, CardValue.EIGHT));
        hand.addCard(new Card(CardSuit.CLUB, CardValue.TEN));
        hand.addCard(new Card(CardSuit.CLUB, CardValue.JACK));
        hand.addCard(new Card(CardSuit.CLUB, CardValue.NINE));

        assertFalse(hand.hasSuit(CardSuit.HEART));
    }

    public void testBourreHandHighestOffSuitWhenNoTrumpPresent() {
        BourreHand hand = new BourreHand();
        hand.addCard(new Card(CardSuit.CLUB, CardValue.SEVEN));
        hand.addCard(new Card(CardSuit.CLUB, CardValue.EIGHT));
        hand.addCard(new Card(CardSuit.CLUB, CardValue.TEN));
        hand.addCard(new Card(CardSuit.CLUB, CardValue.JACK));
        hand.addCard(new Card(CardSuit.CLUB, CardValue.NINE));

        Card card = hand.getHighestOffSuitCard(CardSuit.DIAMOND);
        assertEquals(new Card(CardSuit.CLUB, CardValue.JACK), card);
    }

    public void testBourreHandHighestOffSuitWhenTrumpPresent() {
        BourreHand hand = new BourreHand();
        hand.addCard(new Card(CardSuit.CLUB, CardValue.SEVEN));
        hand.addCard(new Card(CardSuit.CLUB, CardValue.EIGHT));
        hand.addCard(new Card(CardSuit.DIAMOND, CardValue.TEN));
        hand.addCard(new Card(CardSuit.CLUB, CardValue.JACK));
        hand.addCard(new Card(CardSuit.CLUB, CardValue.NINE));

        Card card = hand.getHighestOffSuitCard(CardSuit.DIAMOND);
        assertEquals(new Card(CardSuit.CLUB, CardValue.JACK), card);
    }


}
