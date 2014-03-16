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

import com.shoesobjects.deckofcards.Card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class PlayScenario {
    private BourreHand hand;
    private Card dealerTrumpCard;
    private List<Card> cardsPlayed = new ArrayList<Card>();
    private List<BourrePlayer> players = new ArrayList<BourrePlayer>();
    private int numberOfPlayers;


    public PlayScenario() {
    }


    public BourreHand getHand() {
        return hand;
    }

    public void setHand(BourreHand hand) {
        this.hand = hand;
    }

    public Card getDealerTrumpCard() {
        return dealerTrumpCard;
    }

    public void setDealerTrumpCard(Card dealerTrumpCard) {
        this.dealerTrumpCard = dealerTrumpCard;
    }

    public List<Card> getCardsPlayed() {
        return cardsPlayed;
    }


    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public Card getHigestCardOfSuitLed() {
        Card highestCard = null;
        if (cardsPlayed.size() > 0) {
            highestCard = this.cardsPlayed.get(0);
        }
        Iterator<Card> iter = cardsPlayed.iterator();
        while (iter.hasNext()) {
            Card c = (Card) iter.next();
            if (c.getCardSuite().equals(highestCard.getCardSuite())) {
                if (c.getCardValue().isHigher(highestCard.getCardValue())) {
                    highestCard = c;
                }
            }

        }


        return highestCard;
    }

    public boolean firstToAct() {
        return cardsPlayed.size() == 0;
    }

    public boolean lastToAct() {
        return cardsPlayed.size() == (getNumberOfPlayers() - 1);
    }

    public boolean hasSuitLed() {
        Card c = (Card) this.getCardsPlayed().get(0);
        return this.hand.hasSuit(c.getCardSuite());
    }

    public boolean hasTrumpBeenPlayed() {
        Iterator<Card> iter = cardsPlayed.iterator();
        while (iter.hasNext()) {
            Card c = (Card) iter.next();
            if (c.getCardSuite().equals(this.dealerTrumpCard.getCardSuite())) {
                return true;
            }

        }


        return false;
    }

    public boolean hasHighestOfSuitLed() {
        BourreHand hand = this.hand;
        if (this.cardsPlayed.size() > 0) {
            Card highestOfSuit = hand.getHighestCardOfSuit(this.cardsPlayed.get(0).getCardSuite());
            if (highestOfSuit == null) {
                return false;
            }

            Card card = getHigestCardOfSuitLed();

            return highestOfSuit.getCardValue().isHigher(card.getCardValue());
        }

        return false;

    }

    public Card getHighestTrumpPlayed() {
        Card highestTrump = null;
        Iterator<Card> iter = cardsPlayed.iterator();
        while (iter.hasNext()) {
            Card c = (Card) iter.next();
            if (c.getCardSuite().equals(this.dealerTrumpCard.getCardSuite())) {
                if (highestTrump == null || c.getCardValue().isHigher(highestTrump.getCardValue())) {
                    highestTrump = c;
                }
            }

        }


        return highestTrump;
    }

    public boolean hasHigherTrumpThanPlayed() {
        Card highestTrump = this.getHand().getHighestCardOfSuit(this.dealerTrumpCard.getCardSuite());
        Card highestTrumpPlayed = getHighestTrumpPlayed();
        if (highestTrumpPlayed == null) {
            if (highestTrump != null) {
                return true;
            } else {
                return false;
            }
        }
        if (highestTrump != null) {
            if (highestTrumpPlayed.getCardValue().isLower(highestTrump.getCardValue())) {
                return true;
            }
        }
        return false;
    }


    public void addPlayerAndCardPlayed(BourrePlayer player) {
        this.cardsPlayed.add(player.getPlayedCard()); // ToDo: line should be removed once addCardPlayed is removed
        this.players.add(player);

    }

    public int determineWinningPlayerForHand() {
        Card winningCard = null;
        //BourrePlayer winningPlayer = null;
        //Iterator<BourrePlayer> iter = this.players.iterator();
        int winningPlayerIndex = 0;
        for (int i = 0; i < this.players.size(); i++) {
            BourrePlayer p = (BourrePlayer) this.players.get(i);
            Card cardPlayed = p.getPlayedCard();

            //Card Played
            if (winningCard == null) {
                winningCard = cardPlayed;
                //winningPlayer = p;
                winningPlayerIndex = i;
            }

            //Higher of Same Suit
            if (cardPlayed.isSameSuit(winningCard)) {
                if (cardPlayed.isHigherValue(winningCard)) {
                    winningCard = cardPlayed;
                    //winningPlayer = p;
                    winningPlayerIndex = i;
                }
            } else if (cardPlayed.isSameSuit(this.dealerTrumpCard)) {  //Trumped Card Led
                winningCard = cardPlayed;
                //winningPlayer = p;
                winningPlayerIndex = i;
            }

        }

        return winningPlayerIndex;
    }

    // ToDo: Refactor and remove this method. Lot's of unit
    // tests will need updated
    // Used only by unit tests, needs to be refactored out
    // a line exists in method addPlayerAndCardPlayed
    public void addCardPlayed(Card card) {
        this.cardsPlayed.add(card);
    }

    public boolean hasOnlyTrump() {
        Iterator<?> iter = this.getHand().getCards().iterator();
        while (iter.hasNext()) {
            Card c = (Card) iter.next();
            if (!c.getCardSuite().equals(this.dealerTrumpCard.getCardSuite())) {
                return false;
            }

        }


        return true;
    }
}
