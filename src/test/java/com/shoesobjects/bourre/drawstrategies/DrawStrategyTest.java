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


package com.shoesobjects.bourre.drawstrategies;

import com.shoesobjects.bourre.game.BourrePlayer;
import com.shoesobjects.bourre.game.BourrePlayerTest;
import com.shoesobjects.bourre.game.DrawPosition;
import com.shoesobjects.deckofcards.Card;
import com.shoesobjects.deckofcards.CardSuit;
import com.shoesobjects.deckofcards.CardValue;


public class DrawStrategyTest extends BourrePlayerTest {

    public void testNullHand() {
        BourrePlayer p = getPlayer("player1");
        p.setDealerTrumpCard(new Card(CardSuit.CLUB, CardValue.JACK));
        p.setPosition(getFirstToActOfSevenPlayersPosition());
        p.setDrawStrategy(new KeepOnlyTrumpDrawStrategy());

        try {
            p.discard(new DrawPosition(0, 0));
            fail("Hand Not Set");
        } catch (Exception e) {
            // Do Nothing
        }
    }

    public void testNullDealerTrumpCard() {
        BourrePlayer p = getPlayer("player1");
        p.setHand(getOneTrumpCardKingLowOffSuit(CardSuit.CLUB, CardSuit.SPADE));
        p.setPosition(getFirstToActOfSevenPlayersPosition());
        p.setDrawStrategy(new KeepOnlyTrumpDrawStrategy());

        try {
            p.discard(new DrawPosition(1, 1));
            fail("Dealer Trump Card Not Set");
        } catch (Exception e) {
            // Do Nothing
        }
    }

    public void testNullPosition() {
        BourrePlayer p = getPlayer("player1");
        p.setHand(getOneTrumpCardKingLowOffSuit(CardSuit.CLUB, CardSuit.SPADE));
        p.setDealerTrumpCard(new Card(CardSuit.CLUB, CardValue.JACK));
        p.setDrawStrategy(new KeepOnlyTrumpDrawStrategy());

        try {
            p.discard(new DrawPosition(null, null));
            fail("DrawPosition Not Set");
        } catch (Exception e) {
            // Do Nothing
        }
    }

    public void testDiscardMaxOfTwoBecauseOfNumberOfPlayers() {
        BourrePlayer p = getPlayer("player1");
        p.setHand(getOneTrumpCardKingLowOffSuit(CardSuit.HEART, CardSuit.SPADE));
        p.setDealerTrumpCard(new Card(CardSuit.CLUB, CardValue.JACK));
        p.setPosition(getFirstToActOfSevenPlayersPosition());
        p.setDrawStrategy(new KeepOnlyTrumpDrawStrategy());
        assertEquals(5, p.getHand().getNumberOfCards(Card.Status.UNDECIDED));
        assertEquals(0, p.getHand().getNumberOfCards(Card.Status.HOLD));
        p.discard(new DrawPosition(7, 1));
        assertEquals(3, p.getHand().getNumberOfCards(Card.Status.HOLD));
        assertEquals(0, p.getHand().getNumberOfCards(Card.Status.UNDECIDED));
    }
}
