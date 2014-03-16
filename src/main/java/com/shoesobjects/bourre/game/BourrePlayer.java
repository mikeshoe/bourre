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

import com.shoesobjects.bourre.decisionstrategies.DecisionStrategy;
import com.shoesobjects.bourre.drawstrategies.DrawStrategy;
import com.shoesobjects.bourre.playstrategies.PlayStrategy;
import com.shoesobjects.deckofcards.Card;

import java.util.Random;
import java.util.List;
import java.util.Iterator;


public class BourrePlayer {
    private String playerName;
    private Card dealerTrumpCard;
    private boolean dealer;
    private BourreHand hand = new BourreHand();
    private DecisionStrategy decisionStrategy;
    private DrawStrategy drawStrategy;
    private Position position;
    private DecisionStrategy.Decision decision = DecisionStrategy.Decision.CHECK;
    private PlayStrategy playStrategy;
    private int discardAmount;
    private Card playedCard;
    private int numberOfWins;


    public BourrePlayer(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void addCardToHand(Card card) {
        hand.addCard(card);
    }

    public boolean isDealer() {
        return dealer;
    }

    public void setDealer() {
        this.dealer = true;
    }

    public BourreHand getHand() {
        return hand;
    }

    public void setHand(BourreHand hand) {
        this.hand = hand;
    }

    public Card flipTrumpCard() {
        if (dealer) {
            if (dealerTrumpCard == null) {
                Random random = new Random();
                int flippedCardPosition = random.nextInt(hand.getMaxNumberCards());
                dealerTrumpCard = hand.getCardAtPosition(flippedCardPosition);
            }
        }
        return dealerTrumpCard;
    }

    public boolean hasCard(Card card) {
        return this.hand.hasCard(card);
    }

    public DecisionStrategy.Decision calcDecision(Position position) {
        this.decision = this.decisionStrategy.decide(this.hand, this.dealerTrumpCard, position);
        return this.decision;
    }

    void setDecision(DecisionStrategy.Decision decision) {
        this.decision = decision;
    }

    public DecisionStrategy.Decision getDecision() {
        return this.decision;
    }


    public void setDecisionStrategy(DecisionStrategy decisionStrategy) {
        this.decisionStrategy = decisionStrategy;
    }

    public DrawStrategy getDrawStrategy() {
        return drawStrategy;
    }

    public void setDrawStrategy(DrawStrategy drawStrategy) {
        this.drawStrategy = drawStrategy;
    }

    public Card getDealerTrumpCard() {
        return dealerTrumpCard;
    }

    public void setDealerTrumpCard(Card dealerTrumpCard) {
        this.dealerTrumpCard = dealerTrumpCard;
    }

    public void discard(DrawPosition drawPosition) {
        this.hand = drawStrategy.removeDiscards(this.hand, this.dealerTrumpCard, drawPosition);
        discardAmount = (5 - this.hand.getCards().size());
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


    public boolean isIn() {
        return DecisionStrategy.Decision.IN.equals(this.decision);
    }


    public boolean isUndecided() {
        if (DecisionStrategy.Decision.CHECK.equals(this.decision)) {
            return true;
        }

        return false;
    }

    public PlayStrategy getPlayStrategy() {
        return playStrategy;
    }

    public void setPlayStrategy(PlayStrategy playStrategy) {
        this.playStrategy = playStrategy;
    }

    //package protection
    Card calcPlayedCard(PlayScenario scenario) {
        scenario.setHand(getHand());
        this.playedCard = playStrategy.playCard(scenario);
        this.dealerTrumpCard = scenario.getDealerTrumpCard();
        this.hand.removePlayedCard(this.playedCard);
        return this.playedCard;
    }

    public Card getPlayedCard() {
        return this.playedCard;
    }

    void setPlayedCard(Card card) {
        this.playedCard = card;
    }

    public int getDiscardAmount() {
        return discardAmount;
    }

    public String toString() {
        return "BourrePlayer{" +
                "playerName='" + playerName + '\'' +
                ", isDealer=" + isDealer() +
                ", hand=" + hand +
                '}';
    }

    public String toCode() {
        StringBuilder sb = new StringBuilder();
        sb.append("BourrePlayer player" + this.playerName + "= new BourrePlayer(" + "\"" + this.playerName + "\");\n");
        sb.append("player" + this.playerName + ".setDecisionStrategy(new " + this.decisionStrategy + "());\n");
        sb.append("player" + this.playerName + ".setDecision(DecisionStrategy.Decision." + getDecision() + ");\n");
        sb.append("player" + this.playerName + ".setDrawStrategy(new " + this.drawStrategy + "());\n");
        sb.append("player" + this.playerName + ".setPlayStrategy(new " + this.playStrategy + "());\n");
        if(this.isDealer()) {
            sb.append("player" + this.playerName + ".setDealer();\n");
        }
        sb.append("BourreHand hand" + this.playerName + " = new BourreHand();\n");
        List<?> list = this.getHand().getCards();
        Iterator<?> iter = list.iterator();
        //int counter = 1;
        while(iter.hasNext()) {
            Card c = (Card) iter.next();
            sb.append("hand" + this.playerName + ".addCard(new Card(CardSuit." + c.getCardSuite().toString() + ", CardValue." + c.getCardValue().toString() +  "));\n");
            //counter++;
        }
        sb.append("player" + this.playerName + ".setHand(hand" + this.playerName + ");\n");
        if(this.isDealer()) {
            //sb.append("player" + this.playerName + ".setDealerTrumpCard(new Card(CardSuit." + this.getDealerTrumpCard().getCardSuite().toString() + ", CardValue." + this.getDealerTrumpCard().getCardValue().toString() +  "));\n\n");
        }
        sb.append("game.addPlayer(player" + this.playerName + ");\n\n");
               
        return sb.toString();
    }

    public void incrementWin() {
        this.numberOfWins++;
    }

    public int getNumberOfWins() {
        return this.numberOfWins;
    }

    public void reset() {
        dealerTrumpCard = null;
        dealer = false;
        hand = new BourreHand();
        position = null;
        decision = DecisionStrategy.Decision.CHECK;
        discardAmount = 0;
        playedCard = null;
        numberOfWins = 0;
    }


}
