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


public class TightDecisionStrategy extends BaseDecisionStrategy implements DecisionStrategy {
    protected int OK_TO_OPEN = LOWER_TRUMP_VALUE * 3;
    protected int OK_TO_GO_IN = HIGHER_TRUMP_VALUE * 2;

    protected boolean hasMustGoInHand() {
        return (hand.hasHighestTrumpCard(this.dealerTrumpCard) || calculateDecision() >= OK_TO_OPEN);
    }

    protected boolean hasChooseToGoInHand() {
        return calculateDecision() > OK_TO_GO_IN;
    }

    public String toString() {
        return "TightDecisionStrategy";
    }


}
