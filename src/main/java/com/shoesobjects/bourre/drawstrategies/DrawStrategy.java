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

import com.shoesobjects.bourre.game.BourreGameRuntimeException;
import com.shoesobjects.bourre.game.BourreHand;
import com.shoesobjects.bourre.game.DrawPosition;
import com.shoesobjects.deckofcards.Card;
import com.shoesobjects.deckofcards.CardSuit;

import java.util.Iterator;


public class DrawStrategy {
    protected BourreHand hand;
    protected Card dealerTrumpCard;
    protected DrawPosition drawPosition;
    protected int maxDiscard;


    public BourreHand removeDiscards(BourreHand hand, Card dealerTrumpCard, DrawPosition drawPosition) {
        if (hand == null || dealerTrumpCard == null || drawPosition == null || hand.getNumberOfCards(Card.Status.UNDECIDED) == 0) {
            throw new BourreGameRuntimeException("Invalid Game Situation[hand:" + hand + " dealerTrumpCard:" + dealerTrumpCard + " drawPosition:" + drawPosition + "]");
        }
        this.hand = hand;
        this.dealerTrumpCard = dealerTrumpCard;
        this.drawPosition = drawPosition;
        maxDiscard = drawPosition.calcMaxDraw();

        return hand;
    }

    protected void holdAllTrumpCards(BourreHand hand, CardSuit trumpSuit) {
        Iterator iter = hand.getCards().iterator();
        while (iter.hasNext()) {
            Card c = (Card) iter.next();
            if (c.getCardSuite().equals(trumpSuit)) {
                c.setStatus(Card.Status.HOLD);
            }
        }
    }

    protected void discardRemaining(BourreHand hand) {
        int discardCounter = 0;
        Iterator iter = hand.getCards().iterator();
        while (iter.hasNext()) {
            Card c = (Card) iter.next();
            if (c.isUndecided()) {
                if (this.maxDiscard > discardCounter) {
                    iter.remove();
                    discardCounter++;
                } else {
                    c.setStatus(Card.Status.HOLD);
                }
            }
        }
    }

    public String toString() {
        return "DrawStrategy";
    }

}
