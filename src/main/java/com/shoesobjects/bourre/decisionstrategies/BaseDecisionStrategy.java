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

package com.shoesobjects.bourre.decisionstrategies;

import com.shoesobjects.bourre.game.BourreHand;
import com.shoesobjects.bourre.game.Position;
import com.shoesobjects.deckofcards.Card;


public class BaseDecisionStrategy {
    protected int LOWER_TRUMP_VALUE = 20;
    protected int HIGHER_TRUMP_VALUE = 25;
    protected int HIGHEST_TRUMP_CARD_VALUE = 25;  // ACE

    protected Position position;
    protected BourreHand hand;
    protected Card dealerTrumpCard;

    protected int calcDecision = -1;


    /**
     * Each Decision Strategy should override this method
     *
     * @return
     */
    protected boolean hasChooseToGoInHand() {
        return hand.hasHighestTrumpCard(this.dealerTrumpCard);
    }


    protected boolean hasMustGoInHand() {
        return hand.hasHighestTrumpCard(this.dealerTrumpCard);
    }

    protected boolean canCheck() {
        return position.canCheck();
    }

    // used only in unit tests so position isn't needed for calculations
    protected int calculateDecision(BourreHand hand, Card dealerTrumpCard) {
        this.hand = hand;
        this.dealerTrumpCard = dealerTrumpCard;
        return calculateDecision();
    }

    protected int calculateDecision() {
        int decisionValue = 0;

        decisionValue += (hand.getNumberOfLowerTrumpCards(this.dealerTrumpCard) * LOWER_TRUMP_VALUE);
        decisionValue += (hand.getNumberOfHigherTrumpCards(this.dealerTrumpCard) * HIGHER_TRUMP_VALUE);
        if (hand.hasHighestTrumpCard(this.dealerTrumpCard)) {
            decisionValue += HIGHEST_TRUMP_CARD_VALUE;
        }


        return decisionValue;
    }

    public DecisionStrategy.Decision decide(BourreHand hand, Card dealerTrumpCard, Position position) {
        this.position = position;
        this.hand = hand;
        this.dealerTrumpCard = dealerTrumpCard;

        if (hasMustGoInHand()) {
            return DecisionStrategy.Decision.IN;
        }

        if (canCheck()) {
            return DecisionStrategy.Decision.CHECK;
        }

        if (hasChooseToGoInHand()) {
            return DecisionStrategy.Decision.IN;
        }

        return DecisionStrategy.Decision.OUT;
    }

    public String toString() {
        return "BaseDecisionStrategy";
    }

}
