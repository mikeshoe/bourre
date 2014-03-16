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

import com.shoesobjects.deckofcards.CardSuit;


public class LooseDecisionStrategyTest extends DecisionStrategiesToTest {
    private DecisionStrategy getDecisionStrategy() {
        return new LooseDecisionStrategy();
    }

    public void testNoTrumpCannotCheck() {
        DecisionStrategy ds = getDecisionStrategy();
        assertEquals(DecisionStrategy.Decision.OUT, ds.decide(getTwoThroughSixOneSuit(CardSuit.CLUB), getJackOfSuit(CardSuit.DIAMOND), getSecondToActOfSevenPlayersOneInPosition()));
    }

    public void testNoTrumpCanCheck() {
        DecisionStrategy ds = getDecisionStrategy();
        assertEquals(DecisionStrategy.Decision.CHECK, ds.decide(getTwoThroughSixOneSuit(CardSuit.CLUB), getJackOfSuit(CardSuit.DIAMOND), getFirstToActOfSevenPlayersPosition()));
    }

    public void testAceTrumpCanCheck() {
        DecisionStrategy ds = getDecisionStrategy();
        assertEquals(DecisionStrategy.Decision.IN, ds.decide(getOneTrumpCardAceLowOffSuit(CardSuit.DIAMOND, CardSuit.CLUB), getJackOfSuit(CardSuit.DIAMOND), getFirstToActOfSevenPlayersPosition()));
    }

    public void testAceTrumpCannotCheck() {
        DecisionStrategy ds = getDecisionStrategy();
        assertEquals(DecisionStrategy.Decision.IN, ds.decide(getOneTrumpCardAceLowOffSuit(CardSuit.DIAMOND, CardSuit.CLUB), getJackOfSuit(CardSuit.DIAMOND), getSecondToActOfSevenPlayersOneInPosition()));
    }

    public void testOneHigherTrumpCanCheck() {
        DecisionStrategy ds = getDecisionStrategy();
        assertEquals(DecisionStrategy.Decision.CHECK, ds.decide(getOneTrumpCardThreeLowOffSuit(CardSuit.DIAMOND, CardSuit.CLUB), getTwoCardOfSuit(CardSuit.DIAMOND), getFirstToActOfSevenPlayersPosition()));
    }

    public void testOneHigherTrumpFaceCardCanCheck() {
        DecisionStrategy ds = getDecisionStrategy();
        assertEquals(DecisionStrategy.Decision.IN, ds.decide(getOneTrumpCardKingLowOffSuit(CardSuit.DIAMOND, CardSuit.CLUB), getJackOfSuit(CardSuit.DIAMOND), getFirstToActOfSevenPlayersPosition()));
    }

    public void testOneHigherTrumpCannotCheck() {
        DecisionStrategy ds = getDecisionStrategy();
        assertEquals(DecisionStrategy.Decision.IN, ds.decide(getOneTrumpCardThreeLowOffSuit(CardSuit.DIAMOND, CardSuit.CLUB), getTwoCardOfSuit(CardSuit.DIAMOND), getSecondToActOfSevenPlayersOneInPosition()));
    }

    public void testOneHigherTrumpFaceCardCannotCheck() {
        DecisionStrategy ds = getDecisionStrategy();
        assertEquals(DecisionStrategy.Decision.IN, ds.decide(getOneTrumpCardKingLowOffSuit(CardSuit.DIAMOND, CardSuit.CLUB), getJackOfSuit(CardSuit.DIAMOND), getSecondToActOfSevenPlayersOneInPosition()));
    }

    public void testTwoHigherTrumpCanCheck() {
        DecisionStrategy ds = getDecisionStrategy();
        assertEquals(DecisionStrategy.Decision.IN, ds.decide(getTwoTrumpCardThreeAndFourLowOffSuit(CardSuit.DIAMOND, CardSuit.CLUB), getTwoCardOfSuit(CardSuit.DIAMOND), getFirstToActOfSevenPlayersPosition()));
    }

    public void testTwoHigherTrumpFaceCardCanCheck() {
        DecisionStrategy ds = getDecisionStrategy();
        assertEquals(DecisionStrategy.Decision.IN, ds.decide(getTwoTrumpCardKingAndQueenLowOffSuit(CardSuit.DIAMOND, CardSuit.CLUB), getTwoCardOfSuit(CardSuit.DIAMOND), getFirstToActOfSevenPlayersPosition()));
    }

    public void testTwoHigherTrumpCannotCheck() {
        DecisionStrategy ds = getDecisionStrategy();
        assertEquals(DecisionStrategy.Decision.IN, ds.decide(getTwoTrumpCardThreeAndFourLowOffSuit(CardSuit.DIAMOND, CardSuit.CLUB), getTwoCardOfSuit(CardSuit.DIAMOND), getSecondToActOfSevenPlayersOneInPosition()));
    }

    public void testTwoHigherTrumpFaceCardCannotCheck() {
        DecisionStrategy ds = getDecisionStrategy();
        assertEquals(DecisionStrategy.Decision.IN, ds.decide(getTwoTrumpCardKingAndQueenLowOffSuit(CardSuit.DIAMOND, CardSuit.CLUB), getTwoCardOfSuit(CardSuit.DIAMOND), getFirstToActOfSevenPlayersPosition()));
    }

    public void testOneLowerTrumpCanCheck() {
        DecisionStrategy ds = getDecisionStrategy();
        assertEquals(DecisionStrategy.Decision.CHECK, ds.decide(getOneTrumpCardTwoLowOffSuit(CardSuit.DIAMOND, CardSuit.CLUB), getJackOfSuit(CardSuit.DIAMOND), getFirstToActOfSevenPlayersPosition()));
    }

    public void testOneLowerTrumpCannotCheck() {
        DecisionStrategy ds = getDecisionStrategy();
        assertEquals(DecisionStrategy.Decision.IN, ds.decide(getOneTrumpCardTwoLowOffSuit(CardSuit.DIAMOND, CardSuit.CLUB), getJackOfSuit(CardSuit.DIAMOND), getSecondToActOfSevenPlayersOneInPosition()));
    }

    public void testTwoLowerTrumpCanCheck() {
        DecisionStrategy ds = getDecisionStrategy();
        assertEquals(DecisionStrategy.Decision.IN, ds.decide(getTwoTrumpCardTwoAndThreeLowOffSuit(CardSuit.DIAMOND, CardSuit.CLUB), getJackOfSuit(CardSuit.DIAMOND), getFirstToActOfSevenPlayersPosition()));
    }

    public void testTwoLowerTrumpCannotCheck() {
        DecisionStrategy ds = getDecisionStrategy();
        assertEquals(DecisionStrategy.Decision.IN, ds.decide(getTwoTrumpCardTwoAndThreeLowOffSuit(CardSuit.DIAMOND, CardSuit.CLUB), getJackOfSuit(CardSuit.DIAMOND), getSecondToActOfSevenPlayersOneInPosition()));
    }
}
