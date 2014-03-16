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

import com.shoesobjects.bourre.BaseBourreGameTest;
import com.shoesobjects.deckofcards.CardSuit;


public class BaseDecisionStrategyTest extends BaseBourreGameTest {
    public void testNoTrump() {
        BaseDecisionStrategy strategy = new BaseDecisionStrategy();
        int value = strategy.calculateDecision(getTwoThroughSixOneSuit(CardSuit.CLUB), getJackOfSuit(CardSuit.DIAMOND));

        assertEquals(0, value);
    }

    public void testOneLowerTrump() {
        BaseDecisionStrategy strategy = new BaseDecisionStrategy();
        int value = strategy.calculateDecision(getOneTrumpCardTwoLowOffSuit(CardSuit.DIAMOND, CardSuit.CLUB), getJackOfSuit(CardSuit.DIAMOND));

        assertEquals(20, value);
    }

    public void testTwoLowerTrump() {
        BaseDecisionStrategy strategy = new BaseDecisionStrategy();
        int value = strategy.calculateDecision(getTwoTrumpCardTwoAndThreeLowOffSuit(CardSuit.DIAMOND, CardSuit.CLUB), getJackOfSuit(CardSuit.DIAMOND));

        assertEquals(40, value);
    }

    public void testFiveLowerTrump() {
        BaseDecisionStrategy strategy = new BaseDecisionStrategy();
        int value = strategy.calculateDecision(getTwoThroughSixOneSuit(CardSuit.DIAMOND), getJackOfSuit(CardSuit.DIAMOND));

        assertEquals(100, value);
    }


    public void testOneHigherTrump() {
        BaseDecisionStrategy strategy = new BaseDecisionStrategy();
        int value = strategy.calculateDecision(getOneTrumpCardKingLowOffSuit(CardSuit.DIAMOND, CardSuit.CLUB), getJackOfSuit(CardSuit.DIAMOND));

        assertEquals(25, value);
    }

    public void testTwoHigherTrump() {
        BaseDecisionStrategy strategy = new BaseDecisionStrategy();
        int value = strategy.calculateDecision(getTwoTrumpCardKingAndQueenLowOffSuit(CardSuit.DIAMOND, CardSuit.CLUB), getJackOfSuit(CardSuit.DIAMOND));

        assertEquals(50, value);
    }


    public void testFiveHigherTrump() {
        BaseDecisionStrategy strategy = new BaseDecisionStrategy();
        int value = strategy.calculateDecision(getThreeThroughSixOneSuit(CardSuit.DIAMOND), getTwoCardOfSuit(CardSuit.DIAMOND));

        assertEquals(125, value);
    }

    public void testFiveHigherIncludingAceTrump() {
        BaseDecisionStrategy strategy = new BaseDecisionStrategy();
        int value = strategy.calculateDecision(getThreeThroughSixPlusAceOneSuit(CardSuit.DIAMOND), getTwoCardOfSuit(CardSuit.DIAMOND));

        assertEquals(150, value);
    }

    public void testOneHigherOneLowerTrump() {
        BaseDecisionStrategy strategy = new BaseDecisionStrategy();
        int value = strategy.calculateDecision(getTwoTrumpCardKingAndTwoLowOffSuit(CardSuit.DIAMOND, CardSuit.CLUB), getJackOfSuit(CardSuit.DIAMOND));

        assertEquals(45, value);
    }

    public void testAceTrump() {
        BaseDecisionStrategy strategy = new BaseDecisionStrategy();
        int value = strategy.calculateDecision(getOneTrumpCardAceLowOffSuit(CardSuit.DIAMOND, CardSuit.CLUB), getJackOfSuit(CardSuit.DIAMOND));

        assertEquals(50, value);
    }

    public void testAceTrumpPlusOneLowerTrump() {
        BaseDecisionStrategy strategy = new BaseDecisionStrategy();
        int value = strategy.calculateDecision(getTwoTrumpCardAceAndTwoLowOffSuit(CardSuit.DIAMOND, CardSuit.CLUB), getJackOfSuit(CardSuit.DIAMOND));

        assertEquals(70, value);
    }

    public void testAceTrumpPlusAnotherHigherTrump() {
        BaseDecisionStrategy strategy = new BaseDecisionStrategy();
        int value = strategy.calculateDecision(getTwoTrumpCardAceAndKingLowOffSuit(CardSuit.DIAMOND, CardSuit.CLUB), getJackOfSuit(CardSuit.DIAMOND));

        assertEquals(75, value);
    }
}
