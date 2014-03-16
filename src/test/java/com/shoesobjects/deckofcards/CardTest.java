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

public class CardTest extends TestCase {
    public void testCardExists() {
        Card card = getAceHearts();
        assertNotNull(card);
    }

    public void testSpadeEqualsSpade() {
        assertTrue(CardSuit.SPADE.equals(CardSuit.SPADE));
    }

    public void testSpadeNotEqualToHeart() {
        assertFalse(CardSuit.SPADE.equals(CardSuit.HEART));
    }

    public void testKingEqualsKing() {
        assertTrue(CardValue.KING.equals(CardValue.KING));
    }

    public void testKingNotEqualToQueen() {
        assertFalse(CardValue.KING.equals(CardValue.QUEEN));
    }

    public void testAceHeartsNotEqualToKingHearts() {
        Card ace = getAceHearts();
        Card king = getKingHearts();
        assertTrue(!ace.equals(king));
    }

    public void testAceHeartsEqualsAceHearts() {
        Card ace1 = getAceHearts();
        Card ace2 = getAceHearts();
        assertTrue(ace1.equals(ace2));
    }

    public void testAceHeartsNotEqualToAceSpades() {
        Card aceSpades = getAceSpades();
        Card aceHearts = getAceHearts();
        assertTrue(!aceSpades.equals(aceHearts));
    }

    public void testAceHeartsNotHeld() {
        Card aceSpades = getAceSpades();
        assertFalse(aceSpades.isHeld());
    }

    public void testAceHeartsHeld() {
        Card aceSpades = getAceSpades();
        aceSpades.setStatus(Card.Status.HOLD);
        assertTrue(aceSpades.isHeld());
    }

    public void testAceHeartsSameSuitAsKingHearts() {
        Card ace = getAceHearts();
        Card king = getKingHearts();
        assertTrue(ace.isSameSuit(king));
    }

    public void testKingHeartsNotSameSuitAsAceSpades() {
        Card ace = getAceSpades();
        Card king = getKingHearts();
        assertFalse(ace.isSameSuit(king));
    }

    public void testAceHeartsHasHigherValueThanKingHearts() {
        Card ace = getAceHearts();
        Card king = getKingHearts();
        assertTrue(ace.isHigherValue(king));
    }

    public void testKingHeartsDoesNotHaveHigherValueThanAceHearts() {
        Card ace = getAceHearts();
        Card king = getKingHearts();
        assertFalse(king.isHigherValue(ace));
    }

    public void testKingHeartsToString() {
        Card c = getKingHearts();
        assertEquals("HEART",c.getCardSuite().toString());
        assertEquals("KING",c.getCardValue().toString());
    }

    public void testNotKingHeartsToString() {
        Card c = getAceSpades();
        assertFalse("HEART".equals(c.getCardSuite().toString()));
        assertFalse("KING".equals(c.getCardValue().toString()));
    }

    public void testCardToString() {
        Card c = getAceSpades();
        assertEquals("ACE Of SPADE", c.toString());
    }

    public void testNOTCardToString() {
        Card c = getKingHearts();
        assertFalse("ACE Of SPADE".equals(c.toString()));
    }

    private Card getAceSpades() {
        return new Card(CardSuit.SPADE, CardValue.ACE);
    }

    private Card getKingHearts() {
        return new Card(CardSuit.HEART, CardValue.KING);
    }

    private Card getAceHearts() {
        return new Card(CardSuit.HEART, CardValue.ACE);
    }

}
