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
import com.shoesobjects.bourre.decisionstrategies.DecisionStrategy;
import com.shoesobjects.bourre.decisionstrategies.LooseDecisionStrategy;
import com.shoesobjects.bourre.decisionstrategies.TightDecisionStrategy;
import com.shoesobjects.bourre.drawstrategies.KeepOnlyTrumpDrawStrategy;
import com.shoesobjects.bourre.playstrategies.BasicPlayStrategy;
import com.shoesobjects.deckofcards.Card;
import com.shoesobjects.deckofcards.CardSuit;
import com.shoesobjects.deckofcards.CardValue;


public class BourreGameTest extends BaseBourreGameTest {

    public void testBourreGameHas0Players() {
        BourreGame bg = getBourreGame();
        assertTrue(bg.getNumberOfPlayers() == 0);
    }

    public void testBourreGameHas1Player() {
        BourreGame bg = getBourreGame();
        bg.addPlayer(getPlayer("Mike"));
        assertTrue(bg.getNumberOfPlayers() == 1);
    }


    public void testBourreGameHasTooManyPlayers8() {
        BourreGame bg = getBourreGame();
        try {
            int i;
            for (i = 0; i < 8; i++) {
                bg.addPlayer(getPlayer("Mike" + i));
            }
            fail("Too Many Players " + i + ", only 7 allowed");
        } catch (Exception e) {
            // good 
        }
    }

    public void testBourreGameCannotAddPlayerAfterStartOfGame() {
        BourreGame bg = getBourreGame();
        bg.addPlayer(getPlayer("Mike"));
        bg.addPlayer(getPlayer("Alex"));
        bg.startGame();
        try {
            bg.addPlayer(getPlayer("Mike"));
            fail("Cannot add player after start of game");
        } catch (Exception e) {
            // good   
        }
    }

    public void testBourreGameCannotStartWithInvalidNumberOfPlayers() {
        BourreGame bg = getBourreGame();
        try {
            bg.startGame();
            fail("Cannot start game with invalid number of players(0)");
        } catch (Exception e) {
            // good
        }
    }

    public void testBourreGameCanStartWithValidNumberOfPlayers() {
        BourreGame bg = getBourreGame();
        bg.addPlayer(getPlayer("Mike"));
        bg.addPlayer(getPlayer("Alex"));
        bg.startGame();
        assertTrue(bg.hasGameStarted());
    }


    public void testBourreGameHasDealer() {
        BourreGame bg = getBourreGame();
        bg.addPlayer(getPlayer("Mike"));
        bg.addPlayer(getPlayer("Amanda"));
        bg.addPlayer(getPlayer("Alex"));
        assertFalse(bg.hasGameStarted());
        bg.startGame();
        assertTrue(bg.hasDealer());
    }

    public void testBourreGameCannotStartWithoutDealer() {
        BourreGame bg = getBourreGame();
        assertFalse(bg.hasGameStarted());
        assertFalse(bg.hasDealer());
        try {
            bg.startGame();
            fail("Game cannot start without a dealer");
        } catch (BourreGameRuntimeException e) {
            assertFalse(bg.hasGameStarted());
        }
    }

    public void testBourreGameHasNoDealerBeforeStartOfGame() {
        BourreGame bg = getBourreGame();
        assertFalse(bg.hasGameStarted());
        assertFalse(bg.hasDealer());
    }

    public void testBourreGameReturnsDealerTrumpCardCorrectly() {
        BourreGame bg = getBourreGame();
        bg.addPlayer(getPlayer("Amanda"));
        bg.addPlayer(getPlayer("Alex"));
        assertFalse(bg.hasGameStarted());
        assertTrue(bg.hasDealer());
        BourrePlayer dealer = bg.getDealer();
        Card dealerTrumpCard = dealer.getDealerTrumpCard();
        assertEquals("Alex", dealer.getPlayerName());
        assertEquals(dealerTrumpCard, dealer.getDealerTrumpCard());
    }

    public void testPlayersDecideBourreGame() {
        BourreGame bg = getBourreGame();
        BourrePlayer dealer = getLooseDecisionKeepOnlyTrumpPlayer("Mike");
        bg.addPlayer(dealer);
        BourrePlayer p = getLooseDecisionKeepOnlyTrumpPlayer("Amanda");
        bg.addPlayer(p);
        p = getLooseDecisionKeepOnlyTrumpPlayer("Alex");
        bg.addPlayer(p);
        bg.startGame();
        bg.getPlayerDecisions();
    }

    public void testGetPositionFromBourreGameNoPlayers() {
        BourreGame bg = getBourreGame();
        assertEquals(0, bg.getPosition().getNumberOfPlayers());
        assertEquals(0, bg.getPosition().getNumberOfChecks());
        assertEquals(0, bg.getPosition().getNumberOfIns());
        assertEquals(0, bg.getPosition().getNumberOfOuts());

    }

    public void testGetPositionFromBourreGameOnePlayers() {
        BourreGame bg = getBourreGame();
        BourrePlayer dealer = getLooseDecisionKeepOnlyTrumpPlayer("Mike");
        bg.addPlayer(dealer);
        assertEquals(1, bg.getPosition().getNumberOfPlayers());
        assertEquals(0, bg.getPosition().getNumberOfChecks());
        assertEquals(0, bg.getPosition().getNumberOfIns());
        assertEquals(0, bg.getPosition().getNumberOfOuts());

    }


    public void testReplaceDiscardedCards() {
        BourreGame bg = getBourreGame();
        BourrePlayer dealer = getLooseDecisionKeepOnlyTrumpPlayer("Dealer");
        bg.addPlayer(dealer);
        bg.startGame();
        bg.getPlayerDecisions();
        bg.dealPlayerDrawCards();
        assertEquals(0, bg.getNumberOfHandsPlayed());
    }


    public void testHandsPlayedAreZero() {
        BourreGame bg = getBourreGame();
        BourrePlayer dealer = getLooseDecisionKeepOnlyTrumpPlayer("Dealer");
        bg.addPlayer(dealer);
        bg.startGame();
        bg.getPlayerDecisions();
        assertEquals(0, bg.getNumberOfHandsPlayed());
    }

    public void testHandsPlayedAreOne() {
        BourreGame bg = getBourreGame();
        BourrePlayer dealer = getLooseDecisionKeepOnlyTrumpPlayer("Dealer");
        dealer.setPlayStrategy(new BasicPlayStrategy());
        bg.addPlayer(dealer);
        bg.startGame();
        bg.getPlayerDecisions();
        bg.dealPlayerDrawCards();
        bg.playHand(0);
        assertEquals(1, bg.getNumberOfHandsPlayed());
    }

    public void testGameCannotContinueIfAllChecks() {
        BourreGame bg = getBourreGame();

        BourrePlayer dealer = getTightDecisionKeepOnlyTrumpPlayer("Dealer");
        dealer.setPlayStrategy(new BasicPlayStrategy());

        BourrePlayer player = getTightDecisionKeepOnlyTrumpPlayer("Player");
        player.setPlayStrategy(new BasicPlayStrategy());


        bg.addPlayer(dealer);
        bg.addPlayer(player);

        bg.startGame(false);

        bg.getPlayerDecisions();

        assertFalse(bg.canContinue());
    }


    public void testNPE() {

///// Unit Test Automated Game Setup - Start /////
        BourreGame game = new BourreGame();

        BourrePlayer playerBarack= new BourrePlayer("Barack");
        playerBarack.setDecisionStrategy(new LooseDecisionStrategy());
        playerBarack.setDecision(DecisionStrategy.Decision.IN);
        playerBarack.setDrawStrategy(new KeepOnlyTrumpDrawStrategy());
        playerBarack.setPlayStrategy(new BasicPlayStrategy());
        BourreHand handBarack = new BourreHand();
        handBarack.addCard(new Card(CardSuit.DIAMOND, CardValue.QUEEN));
        handBarack.addCard(new Card(CardSuit.CLUB, CardValue.EIGHT));
        handBarack.addCard(new Card(CardSuit.HEART, CardValue.NINE));
        handBarack.addCard(new Card(CardSuit.CLUB, CardValue.JACK));
        handBarack.addCard(new Card(CardSuit.SPADE, CardValue.ACE));
        playerBarack.setHand(handBarack);
        game.addPlayer(playerBarack);

        BourrePlayer playerHillary= new BourrePlayer("Hillary");
        playerHillary.setDecisionStrategy(new LooseDecisionStrategy());
        playerHillary.setDecision(DecisionStrategy.Decision.IN);
        playerHillary.setDrawStrategy(new KeepOnlyTrumpDrawStrategy());
        playerHillary.setPlayStrategy(new BasicPlayStrategy());
        BourreHand handHillary = new BourreHand();
        handHillary.addCard(new Card(CardSuit.CLUB, CardValue.FOUR));
        handHillary.addCard(new Card(CardSuit.HEART, CardValue.JACK));
        handHillary.addCard(new Card(CardSuit.DIAMOND, CardValue.KING));
        handHillary.addCard(new Card(CardSuit.CLUB, CardValue.NINE));
        handHillary.addCard(new Card(CardSuit.SPADE, CardValue.FIVE));
        playerHillary.setHand(handHillary);
        game.addPlayer(playerHillary);

        BourrePlayer playerHuck= new BourrePlayer("Huck");
        playerHuck.setDecisionStrategy(new TightDecisionStrategy());
        playerHuck.setDecision(DecisionStrategy.Decision.IN);
        playerHuck.setDrawStrategy(new KeepOnlyTrumpDrawStrategy());
        playerHuck.setPlayStrategy(new BasicPlayStrategy());
        playerHuck.setDealer();
        BourreHand handHuck = new BourreHand();
        handHuck.addCard(new Card(CardSuit.CLUB, CardValue.FIVE));
        handHuck.addCard(new Card(CardSuit.SPADE, CardValue.EIGHT));
        handHuck.addCard(new Card(CardSuit.SPADE, CardValue.SEVEN));
        handHuck.addCard(new Card(CardSuit.CLUB, CardValue.THREE));
        handHuck.addCard(new Card(CardSuit.SPADE, CardValue.FOUR));
        playerHuck.setHand(handHuck);
        game.addPlayer(playerHuck);

        PlayScenario scenario = new PlayScenario();
        scenario.setNumberOfPlayers(7);
        scenario.setDealerTrumpCard(new Card(CardSuit.SPADE, CardValue.SEVEN));
        game.setPlayScenario(scenario);
        game.startGame(false);
        game.setDealerTrumpCard(new Card(CardSuit.SPADE, CardValue.SEVEN));
        game.playGame(false);
///// Unit Test Automated Game Setup - End /////

    }


}
