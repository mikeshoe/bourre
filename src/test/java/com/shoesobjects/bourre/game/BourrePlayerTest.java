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


public class BourrePlayerTest extends BaseBourreGameTest {
    public void testBourrePlayerIsNotDealer() {
        BourrePlayer player = getPlayer("Bradley");
        assertFalse(player.isDealer());
    }

    public void testBourrePlayerIsDealer() {
        BourrePlayer player = getPlayer("Bradley");
        player.setHand(getTwoThroughSixOneSuit(CardSuit.CLUB));
        player.setDealer();
        Card c = player.flipTrumpCard();
        assertNotNull(c);
        assertTrue(player.isDealer());
    }

    public void testBourreDealerCannotChangeTrumpCard() {
        BourrePlayer player = getPlayer("Bradley");
        player.setHand(getTwoThroughSixOneSuit(CardSuit.CLUB));
        player.setDealer();
        Card c1 = player.flipTrumpCard();
        assertNotNull(c1);
        assertTrue(player.isDealer());
        Card c2 = player.flipTrumpCard();
        assertNotNull(c2);
        assertTrue(c1.equals(c2));
    }

    public void testBourrePlayerNotDealer() {
        BourrePlayer player = getPlayer("Bradley");
        assertFalse(player.isDealer());
        player.setHand(getTwoThroughSixOneSuit(CardSuit.CLUB));
        assertFalse(player.isDealer());
    }

    public void testBourrePlayerCannotFlipTrumpCard() {
        BourrePlayer player = getPlayer("Bradley");
        assertFalse(player.isDealer());
        player.setHand(getTwoThroughSixOneSuit(CardSuit.CLUB));
        assertFalse(player.isDealer());
        Card c = player.flipTrumpCard();
        assertNull(c);
    }

    public void testBourrePlayerHasCard() {
        BourrePlayer player = getPlayer("Bradley");
        player.setHand(getTwoThroughSixOneSuit(CardSuit.CLUB));
        boolean hasCard = player.hasCard(new Card(CardSuit.CLUB, CardValue.TWO));
        assertTrue(hasCard);
    }
}
