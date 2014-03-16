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

public class PlayScenarioTest extends BaseBourreGameTest {

    public void testFirstToAct() {
        PlayScenario scenario = new PlayScenario();
        scenario.setNumberOfPlayers(7);
        assertTrue(scenario.firstToAct());
    }

    public void testNotFirstToAct() {
        PlayScenario scenario = new PlayScenario();
        scenario.setNumberOfPlayers(7);
        scenario.addCardPlayed(new Card(CardSuit.CLUB, CardValue.ACE));
        assertFalse(scenario.firstToAct());
    }

    public void testLastToAct() {
        PlayScenario scenario = new PlayScenario();
        scenario.setNumberOfPlayers(2);
        scenario.addCardPlayed(new Card(CardSuit.CLUB, CardValue.ACE));
        assertTrue(scenario.lastToAct());
    }

    public void testNotLastToAct() {
        PlayScenario scenario = new PlayScenario();
        scenario.setNumberOfPlayers(2);
        assertFalse(scenario.lastToAct());
    }

    public void testHigestCardOfSuitLedWhenSecondToAct() {
        Card jackOfClubs = getJackOfSuit(CardSuit.CLUB);
        PlayScenario scenario = new PlayScenario();
        scenario.setDealerTrumpCard(jackOfClubs);
        scenario.setNumberOfPlayers(7);
        scenario.addCardPlayed(jackOfClubs);
        Card card = scenario.getHigestCardOfSuitLed();
        assertEquals(card, new Card(CardSuit.CLUB, CardValue.JACK));

    }

    public void testHigestCardOfSuitLedWhenLowerCardFollowsHigh() {
        Card jackOfClubs = getJackOfSuit(CardSuit.CLUB);
        Card threeOfClubs = getThreeCardOfSuit(CardSuit.CLUB);
        PlayScenario scenario = new PlayScenario();
        scenario.setNumberOfPlayers(7);
        scenario.addCardPlayed(jackOfClubs);
        scenario.addCardPlayed(threeOfClubs);
        Card card = scenario.getHigestCardOfSuitLed();
        assertEquals(card, new Card(CardSuit.CLUB, CardValue.JACK));

    }

    public void testHigestCardOfSuitLedWhenHighestCardLastPlayed() {
        Card kingOfClubs = getKingOfSuit(CardSuit.CLUB);
        Card threeOfClubs = getThreeCardOfSuit(CardSuit.CLUB);
        Card jackOfClubs = getJackOfSuit(CardSuit.CLUB);
        PlayScenario scenario = new PlayScenario();
        scenario.setDealerTrumpCard(kingOfClubs);
        scenario.setNumberOfPlayers(7);
        scenario.addCardPlayed(jackOfClubs);
        scenario.addCardPlayed(threeOfClubs);
        Card card = scenario.getHigestCardOfSuitLed();
        assertEquals(card, new Card(CardSuit.CLUB, CardValue.JACK));

    }

    public void testTrumpHasNotBeenPlayedFirstToAct() {
        PlayScenario playScenario = new PlayScenario();
        playScenario.setDealerTrumpCard(getTwoCardOfSuit(CardSuit.DIAMOND));

        assertFalse(playScenario.hasTrumpBeenPlayed());
    }

    public void testTrumpHasNotBeenPlayed() {
        PlayScenario playScenario = new PlayScenario();
        playScenario.addCardPlayed(getThreeCardOfSuit(CardSuit.CLUB));
        playScenario.setDealerTrumpCard(getTwoCardOfSuit(CardSuit.DIAMOND));

        assertFalse(playScenario.hasTrumpBeenPlayed());
    }

    public void testTrumpHasBeenPlayed() {
        PlayScenario playScenario = new PlayScenario();
        playScenario.addCardPlayed(getThreeCardOfSuit(CardSuit.CLUB));
        playScenario.setDealerTrumpCard(getTwoCardOfSuit(CardSuit.CLUB));

        assertTrue(playScenario.hasTrumpBeenPlayed());
    }

    public void testHasHighestOfSuitLedFirstToAct() {
        PlayScenario playScenario = new PlayScenario();
        playScenario.setHand(getTwoThroughSixOneSuit(CardSuit.CLUB));


        assertFalse(playScenario.hasHighestOfSuitLed());
    }

    public void testHasHighestOfSuitLed() {
        PlayScenario playScenario = new PlayScenario();
        playScenario.setHand(getTwoThroughSixOneSuit(CardSuit.CLUB));
        playScenario.addCardPlayed(getThreeCardOfSuit(CardSuit.CLUB));


        assertTrue(playScenario.hasHighestOfSuitLed());
    }

    public void testDoesNotHaveHighestOfSuitLed() {
        PlayScenario playScenario = new PlayScenario();
        playScenario.setHand(getTwoThroughSixOneSuit(CardSuit.CLUB));
        playScenario.addCardPlayed(getAceOfSuit(CardSuit.CLUB));


        assertFalse(playScenario.hasHighestOfSuitLed());
    }

    public void testGetHighestTrumpPlayed() {
        PlayScenario playScenario = new PlayScenario();
        playScenario.setDealerTrumpCard(getTwoCardOfSuit(CardSuit.CLUB));
        playScenario.addCardPlayed(getJackOfSuit(CardSuit.CLUB));
        playScenario.addCardPlayed(getAceOfSuit(CardSuit.CLUB));


        assertEquals(playScenario.getHighestTrumpPlayed(), getAceOfSuit(CardSuit.CLUB));
    }

    public void testGetHighestTrumpPlayedWhenFirstToAct() {
        PlayScenario playScenario = new PlayScenario();
        playScenario.setDealerTrumpCard(getTwoCardOfSuit(CardSuit.CLUB));

        assertNull(playScenario.getHighestTrumpPlayed());
    }

    public void testHasHigherTrumpThanPlayedFirstToAct() {
        PlayScenario playScenario = new PlayScenario();
        playScenario.setDealerTrumpCard(getTwoCardOfSuit(CardSuit.CLUB));
        playScenario.setHand(getThreeThroughSixOneSuit(CardSuit.CLUB));

        assertTrue(playScenario.hasHigherTrumpThanPlayed());
    }

    public void testHasHigherTrumpThanPlayed() {
        PlayScenario playScenario = new PlayScenario();
        playScenario.addCardPlayed(getThreeCardOfSuit(CardSuit.CLUB));
        playScenario.setDealerTrumpCard(getTwoCardOfSuit(CardSuit.CLUB));
        playScenario.setHand(getThreeThroughSixOneSuit(CardSuit.CLUB));

        assertTrue(playScenario.hasHigherTrumpThanPlayed());
    }

    public void testDoesNotHaveHigherTrumpThanPlayed() {
        PlayScenario playScenario = new PlayScenario();
        playScenario.addCardPlayed(getAceOfSuit(CardSuit.CLUB));
        playScenario.setDealerTrumpCard(getTwoCardOfSuit(CardSuit.CLUB));
        playScenario.setHand(getThreeThroughSixOneSuit(CardSuit.CLUB));

        assertFalse(playScenario.hasHigherTrumpThanPlayed());
    }

    public void testAllOffSuitCardsPlayed() {
        PlayScenario playScenario = new PlayScenario();

        playScenario.setDealerTrumpCard(getKingCardOfSuit(CardSuit.DIAMOND));

        BourrePlayer p1 = getPlayer("p1");
        p1.setPlayedCard(getFiveCardOfSuit(CardSuit.SPADE));
        BourrePlayer p2 = getPlayer("p2");
        p2.setPlayedCard(getNineCardOfSuit(CardSuit.CLUB));
        BourrePlayer p3 = getPlayer("p3");
        p3.setPlayedCard(getEightCardOfSuit(CardSuit.HEART));

        playScenario.addPlayerAndCardPlayed(p1);
        playScenario.addPlayerAndCardPlayed(p2);
        playScenario.addPlayerAndCardPlayed(p3);

        assertEquals(0, playScenario.determineWinningPlayerForHand());


    }


}
