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


package com.shoesobjects.bourre.playstrategies;

import com.shoesobjects.bourre.BaseBourreGameTest;
import com.shoesobjects.bourre.game.PlayScenario;
import com.shoesobjects.deckofcards.Card;
import com.shoesobjects.deckofcards.CardSuit;
import com.shoesobjects.deckofcards.CardValue;


public class PlayStrategiesTest extends BaseBourreGameTest {


    public void testHasSuitLed() {
        PlayScenario playScenario = new PlayScenario();
        playScenario.addCardPlayed(getOneCardPlayedOnly(CardSuit.DIAMOND, CardValue.TWO));
        playScenario.setDealerTrumpCard(getThreeCardOfSuit(CardSuit.DIAMOND));
        playScenario.setHand(getOneTrumpCardKingLowOffSuit(CardSuit.DIAMOND, CardSuit.SPADE));
        playScenario.setNumberOfPlayers(4);
        assertTrue(playScenario.hasSuitLed());
    }


    public void testDoesNotHaveSuitLed() {
        PlayScenario playScenario = new PlayScenario();
        playScenario.addCardPlayed(getOneCardPlayedOnly(CardSuit.HEART, CardValue.TWO));
        playScenario.setDealerTrumpCard(getThreeCardOfSuit(CardSuit.DIAMOND));
        playScenario.setHand(getOneTrumpCardKingLowOffSuit(CardSuit.DIAMOND, CardSuit.SPADE));
        playScenario.setNumberOfPlayers(4);
        assertFalse(playScenario.hasSuitLed());
    }

    public void testHasOnlyTrumpFirstToAct() {
        PlayScenario playScenario = new PlayScenario();
        playScenario.setDealerTrumpCard(getThreeCardOfSuit(CardSuit.DIAMOND));
        playScenario.setHand(getTwoThroughSixOneSuit(CardSuit.DIAMOND));
        playScenario.setNumberOfPlayers(4);
        assertTrue(playScenario.hasOnlyTrump());
    }

    public void testHasOnlyTrumpNotFirstToAct() {
        PlayScenario playScenario = new PlayScenario();
        playScenario.setDealerTrumpCard(getThreeCardOfSuit(CardSuit.DIAMOND));
        playScenario.setHand(getTwoThroughSixOneSuit(CardSuit.DIAMOND));
        playScenario.addCardPlayed(getOneCardPlayedOnly(CardSuit.HEART, CardValue.TWO));
        playScenario.setNumberOfPlayers(4);
        assertTrue(playScenario.hasOnlyTrump());
    }


    public void testDoesNotHaveOnlyTrumpFalseFirstToAct() {
        PlayScenario playScenario = new PlayScenario();
        playScenario.setDealerTrumpCard(getThreeCardOfSuit(CardSuit.DIAMOND));
        playScenario.setHand(getTwoThroughSixOneSuit(CardSuit.HEART));
        playScenario.setNumberOfPlayers(4);
        assertFalse(playScenario.hasOnlyTrump());
    }

    public void testDoesNotHaveOnlyTrumpFalseNotFirstToAct() {
        PlayScenario playScenario = new PlayScenario();
        playScenario.setDealerTrumpCard(getThreeCardOfSuit(CardSuit.DIAMOND));
        playScenario.addCardPlayed(getOneCardPlayedOnly(CardSuit.HEART, CardValue.TWO));
        playScenario.setHand(getTwoThroughSixOneSuit(CardSuit.HEART));
        playScenario.setNumberOfPlayers(4);
        assertFalse(playScenario.hasOnlyTrump());
    }

    public void testFuckAroundScenarioTwoOfTrumpLedFirstRoundFirstCard() {
        BasicPlayStrategy strategyBasic = getPlayStrategy();
        PlayScenario playScenario = new PlayScenario();
        playScenario.addCardPlayed(getOneCardPlayedOnly(CardSuit.DIAMOND, CardValue.TWO));
        playScenario.setDealerTrumpCard(getThreeCardOfSuit(CardSuit.DIAMOND));
        playScenario.setHand(getOneTrumpCardKingLowOffSuit(CardSuit.DIAMOND, CardSuit.SPADE));
        playScenario.setNumberOfPlayers(4);

        Card card = strategyBasic.playCard(playScenario);
        assertEquals(getKingOfSuit(CardSuit.DIAMOND), card);
    }

    public void testTwoLowerThanSuitLedShouldPlayLowestCardSinceCannotWin() {
        BasicPlayStrategy strategyBasic = getPlayStrategy();
        PlayScenario playScenario = new PlayScenario();
        playScenario.addCardPlayed(getOneCardPlayedOnly(CardSuit.DIAMOND, CardValue.KING));
        playScenario.setDealerTrumpCard(getTwoCardOfSuit(CardSuit.DIAMOND));
        playScenario.setHand(getTwoTrumpCardThreeAndFourLowOffSuit(CardSuit.DIAMOND, CardSuit.SPADE));
        playScenario.setNumberOfPlayers(4);

        Card card = strategyBasic.playCard(playScenario);
        assertEquals(getThreeCardOfSuit(CardSuit.DIAMOND), card);

    }

    public void testOneHigherThanCardLedButNotHighestCard() {
        BasicPlayStrategy strategyBasic = getPlayStrategy();
        PlayScenario playScenario = new PlayScenario();
        playScenario.setDealerTrumpCard(getThreeCardOfSuit(CardSuit.HEART));
        playScenario.addCardPlayed(getThreeCardOfSuit(CardSuit.DIAMOND));
        playScenario.setHand(getHandKingThroughNine(CardSuit.DIAMOND));
        playScenario.setNumberOfPlayers(4);

        Card card = strategyBasic.playCard(playScenario);
        assertEquals(getKingCardOfSuit(CardSuit.DIAMOND), card);
    }

    public void testOneHigherThanCardLedButNotHighestCardThirdToAct() {
        BasicPlayStrategy strategyBasic = getPlayStrategy();
        PlayScenario playScenario = new PlayScenario();
        playScenario.setDealerTrumpCard(getThreeCardOfSuit(CardSuit.HEART));
        playScenario.addCardPlayed(getThreeCardOfSuit(CardSuit.DIAMOND));
        playScenario.addCardPlayed(getFiveCardOfSuit(CardSuit.DIAMOND));
        playScenario.setHand(getHandKingThroughNine(CardSuit.DIAMOND));
        playScenario.setNumberOfPlayers(4);

        Card card = strategyBasic.playCard(playScenario);
        assertEquals(getKingCardOfSuit(CardSuit.DIAMOND), card);
    }

    public void testOneHigherThanCardLedButNotHighestCardThirdToActWithThrowOffInMiddle() {
        BasicPlayStrategy strategyBasic = getPlayStrategy();
        PlayScenario playScenario = new PlayScenario();
        playScenario.setDealerTrumpCard(getThreeCardOfSuit(CardSuit.HEART));
        playScenario.addCardPlayed(getThreeCardOfSuit(CardSuit.DIAMOND));
        playScenario.addCardPlayed(getFiveCardOfSuit(CardSuit.SPADE));
        playScenario.setHand(getHandKingThroughNine(CardSuit.DIAMOND));
        playScenario.setNumberOfPlayers(4);

        Card card = strategyBasic.playCard(playScenario);
        assertEquals(getKingCardOfSuit(CardSuit.DIAMOND), card);
    }

    public void testTwoHigherThanCardLedButNoneHigherThanHighestCardPlayed() {
        BasicPlayStrategy strategyBasic = getPlayStrategy();
        PlayScenario playScenario = new PlayScenario();
        playScenario.addCardPlayed(getFiveCardOfSuit(CardSuit.DIAMOND));
        playScenario.addCardPlayed(getAceCardOfSuit(CardSuit.DIAMOND));
        playScenario.setDealerTrumpCard(getThreeCardOfSuit(CardSuit.DIAMOND));
        playScenario.setHand(getTwoTrumpCardKingAndTwoLowOffSuit(CardSuit.DIAMOND, CardSuit.SPADE));
        playScenario.setNumberOfPlayers(4);

        Card card = strategyBasic.playCard(playScenario);
        assertEquals(getTwoCardOfSuit(CardSuit.DIAMOND), card);
    }


    public void testFirstToActWhenNoTrump() {
        BasicPlayStrategy strategyBasic = getPlayStrategy();
        PlayScenario playScenario = new PlayScenario();
        playScenario.setDealerTrumpCard(getTwoCardOfSuit(CardSuit.DIAMOND));
        playScenario.setHand(getNoTrumpHandAceThroughTen(CardSuit.SPADE));

        Card card = strategyBasic.playCard(playScenario);
        assertEquals(getAceOfSuit(CardSuit.SPADE), card);
    }

    public void testFirstToActWhenOneTrumpPresent() {
        BasicPlayStrategy strategyBasic = getPlayStrategy();
        PlayScenario playScenario = new PlayScenario();
        playScenario.setDealerTrumpCard(getTwoCardOfSuit(CardSuit.DIAMOND));
        playScenario.setHand(getOneTrumpHandAceThroughTenOfTrump(CardSuit.SPADE, CardSuit.DIAMOND));

        Card card = strategyBasic.playCard(playScenario);
        assertEquals(getAceOfSuit(CardSuit.SPADE), card);
    }

    public void testFirstToActWithAceOfTrump() {
        BasicPlayStrategy strategyBasic = getPlayStrategy();
        PlayScenario playScenario = new PlayScenario();
        playScenario.setDealerTrumpCard(getTwoCardOfSuit(CardSuit.DIAMOND));
        playScenario.setHand(getOneTrumpHandAceOfTrumpThroughTen(CardSuit.SPADE, CardSuit.DIAMOND));

        Card card = strategyBasic.playCard(playScenario);
        assertEquals(getAceOfSuit(CardSuit.DIAMOND), card);
    }

    public void testLastToActWithHigherTrumpCard() {
        BasicPlayStrategy strategyBasic = getPlayStrategy();
        PlayScenario playScenario = new PlayScenario();
        playScenario.addCardPlayed(getTwoCardOfSuit(CardSuit.DIAMOND));
        playScenario.addCardPlayed(getTwoCardOfSuit(CardSuit.HEART));
        playScenario.setNumberOfPlayers(3);
        playScenario.setDealerTrumpCard(getTwoCardOfSuit(CardSuit.HEART));
        playScenario.setHand(getOneTrumpCardThreeLowOffSuit(CardSuit.HEART, CardSuit.CLUB));

        Card card = strategyBasic.playCard(playScenario);
        assertEquals(getThreeCardOfSuit(CardSuit.HEART), card);
    }


    public void testLastToActWithHigherTrumpCardThanLed() {
        BasicPlayStrategy strategyBasic = getPlayStrategy();
        PlayScenario playScenario = new PlayScenario();
        playScenario.addCardPlayed(getTwoCardOfSuit(CardSuit.DIAMOND));
        playScenario.setNumberOfPlayers(2);
        playScenario.setDealerTrumpCard(getTwoCardOfSuit(CardSuit.DIAMOND));
        playScenario.setHand(getOneTrumpCardThreeLowOffSuit(CardSuit.DIAMOND, CardSuit.CLUB));

        Card card = strategyBasic.playCard(playScenario);
        assertEquals(getThreeCardOfSuit(CardSuit.DIAMOND), card);
    }

    public void testLastToActWithHigherNonTrumpCardThanLed() {
        BasicPlayStrategy strategyBasic = getPlayStrategy();
        PlayScenario playScenario = new PlayScenario();
        playScenario.addCardPlayed(getTwoCardOfSuit(CardSuit.DIAMOND));
        playScenario.setNumberOfPlayers(2);
        playScenario.setDealerTrumpCard(getTwoCardOfSuit(CardSuit.CLUB));
        playScenario.setHand(getOneTrumpCardThreeLowOffSuit(CardSuit.CLUB, CardSuit.DIAMOND));

        Card card = strategyBasic.playCard(playScenario);
        assertEquals(new Card(CardSuit.DIAMOND, CardValue.SIX), card);
    }

    public void testLastToActWithHigherCardThanLedButTrumpPlayedInMiddle() {
        BasicPlayStrategy strategyBasic = getPlayStrategy();
        PlayScenario playScenario = new PlayScenario();
        playScenario.addCardPlayed(getTwoCardOfSuit(CardSuit.DIAMOND));
        playScenario.addCardPlayed(getAceOfSuit(CardSuit.CLUB));
        playScenario.setNumberOfPlayers(3);
        playScenario.setDealerTrumpCard(getTwoCardOfSuit(CardSuit.CLUB));
        playScenario.setHand(getOneTrumpCardThreeLowOffSuit(CardSuit.CLUB, CardSuit.DIAMOND));

        Card card = strategyBasic.playCard(playScenario);
        assertEquals(getThreeCardOfSuit(CardSuit.DIAMOND), card);
    }

    public void testLastToActWithTrumpButNoneOfSuitLed() {
        BasicPlayStrategy strategyBasic = getPlayStrategy();
        PlayScenario playScenario = new PlayScenario();
        playScenario.addCardPlayed(getTwoCardOfSuit(CardSuit.DIAMOND));
        playScenario.addCardPlayed(getAceOfSuit(CardSuit.DIAMOND));
        playScenario.setNumberOfPlayers(3);
        playScenario.setDealerTrumpCard(getTwoCardOfSuit(CardSuit.CLUB));
        playScenario.setHand(getOneTrumpCardThreeLowOffSuit(CardSuit.CLUB, CardSuit.SPADE));

        Card card = strategyBasic.playCard(playScenario);
        assertEquals(getThreeCardOfSuit(CardSuit.CLUB), card);
    }

    public void testLastToActWithMoreThanOneLowerCardThanLed() {
        BasicPlayStrategy strategyBasic = getPlayStrategy();
        PlayScenario playScenario = new PlayScenario();
        playScenario.addCardPlayed(getAceOfSuit(CardSuit.DIAMOND));
        playScenario.setNumberOfPlayers(2);
        playScenario.setDealerTrumpCard(getTwoCardOfSuit(CardSuit.CLUB));
        playScenario.setHand(getOneTrumpCardThreeLowOffSuit(CardSuit.CLUB, CardSuit.DIAMOND));

        Card card = strategyBasic.playCard(playScenario);
        assertEquals(getThreeCardOfSuit(CardSuit.DIAMOND), card);
    }

    public void testLastToActWithHigherTrumpButNoneOfSuitLed() {
        BasicPlayStrategy strategyBasic = getPlayStrategy();
        PlayScenario playScenario = new PlayScenario();
        playScenario.addCardPlayed(getAceOfSuit(CardSuit.DIAMOND));
        playScenario.addCardPlayed(getTwoCardOfSuit(CardSuit.CLUB));
        playScenario.setNumberOfPlayers(3);
        playScenario.setDealerTrumpCard(getTwoCardOfSuit(CardSuit.CLUB));
        playScenario.setHand(getOneTrumpCardThreeLowOffSuit(CardSuit.CLUB, CardSuit.SPADE));

        Card card = strategyBasic.playCard(playScenario);
        assertEquals(getThreeCardOfSuit(CardSuit.CLUB), card);
    }


    public void testNoTrumpNoSuitLed() {
        BasicPlayStrategy strategyBasic = getPlayStrategy();
        PlayScenario playScenario = new PlayScenario();
        playScenario.addCardPlayed(getAceOfSuit(CardSuit.DIAMOND));
        playScenario.setNumberOfPlayers(2);
        playScenario.setDealerTrumpCard(getTwoCardOfSuit(CardSuit.CLUB));
        playScenario.setHand(getNoTrumpHandAceThroughTen(CardSuit.SPADE));

        Card card = strategyBasic.playCard(playScenario);
        assertEquals(new Card(CardSuit.SPADE, CardValue.TEN), card);
    }


    protected Card getFiveCardOfSuit(CardSuit suit) {
        return new Card(suit, CardValue.FIVE);
    }

    protected Card getAceCardOfSuit(CardSuit suit) {
        return new Card(suit, CardValue.ACE);
    }


    protected BasicPlayStrategy getPlayStrategy() {
        return new BasicPlayStrategy();
    }

    protected Card getOneCardPlayedOnly(CardSuit suit, CardValue value) {
        return new Card(suit, value);
    }


}
