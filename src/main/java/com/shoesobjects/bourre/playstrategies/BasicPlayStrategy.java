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

import com.shoesobjects.bourre.game.BourreHand;
import com.shoesobjects.bourre.game.PlayScenario;
import com.shoesobjects.deckofcards.Card;
import com.shoesobjects.deckofcards.CardSuit;
import com.shoesobjects.deckofcards.CardValue;


public class BasicPlayStrategy implements PlayStrategy {


    /**
     * Basic Play that accounts for player having a card of the suit
     * that was led.  Does not account for leading or what to throw when player
     * does not have suit that was led.
     *
     * @param playScenario
     * @return
     */
    public Card playCard(PlayScenario playScenario) {
        if (playScenario.firstToAct()) {
            return playFirstToAct(playScenario);
        }

        if (playScenario.lastToAct()) {
            return playLastToAct(playScenario);
        }


        if (playScenario.hasSuitLed()) {
            return playFollowSuit(playScenario);
        }

        if (playScenario.hasOnlyTrump()) {
            return playHighestTrump(playScenario);
        }

        return getLowestOffSuit(playScenario);
    }

    private Card playHighestTrump(PlayScenario playScenario) {
        BourreHand hand = playScenario.getHand();
        return hand.getHighestCardOfSuit(playScenario.getDealerTrumpCard().getCardSuite());
    }


    protected Card playFirstToAct(PlayScenario playScenario) {
        BourreHand hand = playScenario.getHand();
        Card dealerTrumpCard = playScenario.getDealerTrumpCard();

        // Play nutz if you have it
        if (hand.hasHighestTrumpCard(new Card(dealerTrumpCard.getCardSuite(), CardValue.ACE))) {
            return hand.getHighestCardOfSuit(dealerTrumpCard.getCardSuite());
        } else {   // Play highest offsuit
            return playHighestOffSuit(playScenario);
        }
    }

    protected Card playLastToAct(PlayScenario playScenario) {
        BourreHand hand = playScenario.getHand();
        Card highestOfSuitLed = playScenario.getHigestCardOfSuitLed();
        boolean hasSuitLed = hand.hasSuit(highestOfSuitLed.getCardSuite());
        boolean hasTrumpBeenPlayed = playScenario.hasTrumpBeenPlayed();
        boolean hasHighestOfSuitLed = playScenario.hasHighestOfSuitLed();
        boolean hasHigherTrump = playScenario.hasHigherTrumpThanPlayed();

        if (hasSuitLed) {
            if (hasTrumpBeenPlayed) {
                return getLowestOfSuit(hand, highestOfSuitLed.getCardSuite());
            } else {
                if (hasHighestOfSuitLed) {
                    return getHighestOfSuit(hand, highestOfSuitLed.getCardSuite());
                }
            }
        } else {
            if (hasTrumpBeenPlayed && hasHigherTrump) {
                return getHighestOfSuit(hand, playScenario.getDealerTrumpCard().getCardSuite());
            } else if (hasHigherTrump) {
                return getLowestOfSuit(hand, playScenario.getDealerTrumpCard().getCardSuite());
            }

        }

        return getLowestOffSuit(playScenario);


    }


    protected Card playFollowSuit(PlayScenario playScenario) {
        BourreHand hand = playScenario.getHand();
        Card highestOfSuitLed = playScenario.getHigestCardOfSuitLed();
        if (highestOfSuitLed.getCardValue().isHigher(hand.getHighestCardOfSuit(highestOfSuitLed.getCardSuite()).getCardValue())) {
            return hand.getLowestCardOfSuit(highestOfSuitLed.getCardSuite());
        }

        return hand.getHighestCardOfSuit(highestOfSuitLed.getCardSuite());
    }

    protected Card playHighestOffSuit(PlayScenario playScenario) {
        return playScenario.getHand().getHighestOffSuitCard(playScenario.getDealerTrumpCard().getCardSuite());
    }


    protected Card getLowestOffSuit(PlayScenario playScenario) {
        return playScenario.getHand().getLowestOffSuitCard(playScenario.getDealerTrumpCard().getCardSuite());
    }

    protected Card getLowestOfSuit(BourreHand hand, CardSuit suit) {
        return hand.getLowestCardOfSuit(suit);
    }

    protected Card getHighestOfSuit(BourreHand hand, CardSuit suit) {
        return hand.getHighestCardOfSuit(suit);
    }

    public String toString() {
        return "BasicPlayStrategy";
    }


}
