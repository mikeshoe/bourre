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
import com.shoesobjects.deckofcards.Card;
import com.shoesobjects.deckofcards.Deck;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class BourreGame {
    private final static int MIN_PLAYERS = 1;
    private final static int MAX_PLAYERS = 7;

    private List<BourrePlayer> players = new ArrayList();

    private boolean gameStarted;

    private int handsPlayed = 0;
    private Position position = new Position();
    private DrawPosition drawPosition;
    PlayScenario playScenario = new PlayScenario();


    private Deck deck = new Deck();

    private Card dealerTrumpCard;

    private BourrePlayer winner = null;
    private List<BourrePlayer> matchers = new ArrayList();
    private String matchersByName = "";
    private List<BourrePlayer> pushers = new ArrayList();
    private String pushersByName = "";

    private int numberOfStartingPlayers;
    private int gamesPlayed = 0;


    void setDealerTrumpCard(Card card) {
        this.dealerTrumpCard = card;
    }
    public List getPlayers() {
        return players;
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    public int getNumberOfStartingPlayers() {
        return numberOfStartingPlayers;
    }


    public void addPlayer(BourrePlayer bourrePlayer) {
        if (gameStarted) {
            throw new BourreGameRuntimeException("Cannot add players after the game has been started");
        }

        if (hasRoomForAnotherPlayer()) {
            players.add(bourrePlayer);
            position.setNumberOfPlayers(this.getNumberOfPlayers());
        } else {
            throw new BourreGameRuntimeException("InvalidNumberOfPlayers: " + getNumberOfPlayers());
        }
    }

    void startGame(boolean deal) {
        if (hasValidNumberOfPlayers()) {
            position = new Position(this.getNumberOfPlayers());
            gameStarted = true;
            gamesPlayed++;
            numberOfStartingPlayers = players.size();
            this.playScenario.setNumberOfPlayers(this.getNumberOfPlayers());
            markDealer();
            deck.shuffle();
            if (deal) {
                dealCards(deck);
                determineDealerTrumpCard();
                this.playScenario.setDealerTrumpCard(this.dealerTrumpCard);
            }
        } else {
            throw new BourreGameRuntimeException("InvalidPlayerConfiguration: Players:" + getNumberOfPlayers() + " HasDealer:" + hasDealer());
        }
    }

    public void startGame() {
        startGame(true);
    }


    private void markDealer() {
        int numPlayers = this.getNumberOfPlayers();
        BourrePlayer player = players.get(numPlayers - 1);
        player.setDealer();
    }

    private void determineDealerTrumpCard() {
        int numPlayers = this.getNumberOfPlayers();
        BourrePlayer player = players.get(numPlayers - 1);
        this.dealerTrumpCard = player.flipTrumpCard();
    }


    private void dealCards(Deck deck) {
        for (int i = 0; i < 5; i++) {
            Iterator iterator = players.iterator();
            while (iterator.hasNext()) {
                BourrePlayer p = (BourrePlayer) iterator.next();
                p.addCardToHand(deck.draw());
            }
        }


    }

    private boolean hasValidNumberOfPlayers() {
        return (getNumberOfPlayers() >= MIN_PLAYERS &&
                getNumberOfPlayers() <= MAX_PLAYERS &&
                hasDealer());
    }

    private boolean hasRoomForAnotherPlayer() {
        return (getNumberOfPlayers() <= (MAX_PLAYERS - 1));
    }

    public boolean hasGameStarted() {
        return gameStarted;
    }


    public String toString() {
        return "BourreGame{" +
                "players=" + players +
                ", gameStarted=" + gameStarted +
                '}';
    }

    public boolean hasDealer() {
        return this.getNumberOfPlayers() > 0;
    }


    public void getPlayerDecisions() {
        int numChecks = 0;
        Iterator iter = players.iterator();
        while (iter.hasNext() && this.position.isStillOpen()) {
            BourrePlayer player = (BourrePlayer) iter.next();
            if (player.isUndecided()) {
                player.setDealerTrumpCard(this.dealerTrumpCard);
                DecisionStrategy.Decision d = player.calcDecision(this.position);
                if (DecisionStrategy.Decision.CHECK.equals(d)) {
                    numChecks++;
                }
                System.out.println("Player:" + player.getPlayerName() + " Decision:" + d);
                this.position.addDecision(d);

            }
        }

        if (numChecks > 0) {
            getPlayerDecisions();
        }

        System.out.println("\n");
        removeOutPlayers();
    }

    private void removeOutPlayers() {
        Iterator iter = players.iterator();
        while (iter.hasNext()) {
            BourrePlayer bp = (BourrePlayer) iter.next();
            if (DecisionStrategy.Decision.OUT.equals(bp.getDecision())) {
                iter.remove();
            }
        }
    }

    void dealPlayerDrawCards() {

        Iterator iter = getPlayers().iterator();
        drawPosition = new DrawPosition(getNumberOfStartingPlayers(), getNumberOfPlayers());
        while (iter.hasNext()) {
            BourrePlayer player = (BourrePlayer) iter.next();
            System.out.println("Player:" + player.getPlayerName() + " Had:" + player.getHand());
            player.discard(drawPosition);
            System.out.println("Player:" + player.getPlayerName() + " After Discard:" + player.getHand());
            int numCards = player.getDiscardAmount();
            for (int i = 0; i < numCards; i++) {
                player.addCardToHand(this.deck.draw());
            }
            System.out.println("Player:" + player.getPlayerName() + " After Draw:" + player.getHand() + "\n");
        }
    }

    public BourrePlayer getDealer() {
        return players.get(this.players.size() - 1);
    }

    public Position getPosition() {
        return this.position;
    }

    public int getNumberOfHandsPlayed() {
        return this.handsPlayed;
    }


    public int playHand(int firstToAct) {
        for (int i = firstToAct; i < players.size(); i++) {
            BourrePlayer player = players.get(i);
            Card card = player.calcPlayedCard(playScenario);
            System.out.println("Player:" + player.getPlayerName() + " Played:" + card.toString());
            playScenario.addPlayerAndCardPlayed(player);
        }

        if (firstToAct > 0) {
            for (int i = 0; i < firstToAct; i++) {
                BourrePlayer player = players.get(i);
                Card card = player.calcPlayedCard(playScenario);
                System.out.println("Player:" + player.getPlayerName() + " Played:" + card.toString());
                playScenario.addPlayerAndCardPlayed(player);
            }
        }
        handsPlayed++;
        int winningPlayerIndex = playScenario.determineWinningPlayerForHand();
        BourrePlayer winner = players.get(winningPlayerIndex);
        winner.incrementWin();
        System.out.println("Hand Won By: " + winner.getPlayerName().trim() + " Card: " + winner.getPlayedCard() + " Wins:" + winner.getNumberOfWins() + "\n");

        return winningPlayerIndex;
    }

    private boolean allPlayersCheck() {
        boolean allCheck = true;
        Iterator iter = players.iterator();
        while (iter.hasNext()) {
            BourrePlayer p = (BourrePlayer) iter.next();
            if (!p.isUndecided()) {
                return false;
            }
        }
        return true;
    }

    public void playGame() {
        playGame(true);
    }

    void playGame(boolean dealPlayerDrawCards) {
        int firstToActPointer = 0;


        System.out.println("Num Players:" + getNumberOfPlayers());
        System.out.println("Dealer:" + getDealer().getPlayerName());
        System.out.println("Dealer Trump:" + getDealerTrumpCard() + "\n");


        System.out.println("\n");



        if(dealPlayerDrawCards) {
            getPlayerDecisions();
            dealPlayerDrawCards();
        }

        for (int i = 0; i < 5; i++) {
            firstToActPointer = playHand(firstToActPointer);
        }

        calcGameOutcome();

        if (isGameWon()) {
            System.out.println("\n\nGame Winner:" + this.winner.getPlayerName().trim());
        } else {
            System.out.println("No Winner");
        }

        if (isMatcher()) {
            System.out.println("Matchers:" + getMatchersByName());
        }

        if (isPusher()) {
            System.out.println("Pushers:" + getPushersByName());
        }

        if (isGameOver()) {
            System.out.println("Game Over after " + gamesPlayed + " rounds played!");
        } else {
            resetAndReplayGame();
        }

    }


    private void resetAndReplayGame() {
        System.out.println("\n****Rotate Deal and Replay****\n");
        this.dealerTrumpCard = null;
        this.gameStarted = false;
        this.deck = new Deck();
        handsPlayed = 0;
        position = new Position();
        drawPosition = null;
        playScenario = new PlayScenario();
        winner = null;
        matchers = new ArrayList();
        matchersByName = "";
        pushers = new ArrayList();
        pushersByName = "";

        List<BourrePlayer> newPlayers = new ArrayList();
        for (int i = 1; i < players.size(); i++) {
            BourrePlayer bp = players.get(i);
            bp.reset();
            newPlayers.add(bp);
        }

        BourrePlayer newDealer = players.get(0);
        newDealer.reset();
        newPlayers.add(newDealer);
        players = new ArrayList(newPlayers);
        startGame();
        playGame();

    }

    private String getMatchersByName() {
        return matchersByName;
    }

    private String getPushersByName() {
        return pushersByName;
    }


    private void calcGameOutcome() {

        Iterator iter = players.iterator();
        while (iter.hasNext()) {
            BourrePlayer bp = (BourrePlayer) iter.next();
            if (bp.getNumberOfWins() >= 3) {
                winner = bp;
            } else if (bp.getNumberOfWins() > 0) {
                pushersByName = pushersByName + bp.getPlayerName().trim() + ",";
                pushers.add(bp);
            } else if (bp.getNumberOfWins() == 0) {
                matchersByName = matchersByName + bp.getPlayerName().trim() + ",";
                matchers.add(bp);
            }
        }
    }

    public boolean isGameWon() {
        return (winner != null);
    }

    public boolean isGameOver() {
        return isGameWon() && (matchers.size() == 0);
    }


    public boolean isMatcher() {
        return matchers.size() != 0;
    }

    public boolean isPusher() {
        return pushers.size() != 0;
    }

    public Card getDealerTrumpCard() {
        return this.dealerTrumpCard;
    }


    public boolean canContinue() {
        if (allPlayersCheck()) {
            System.out.println("All Players checked - Game Over");
            return false;
        }
        return true;
    }

    public String toCode() {
    StringBuilder sb = new StringBuilder();
        sb.append("///// Unit Test Automated Game Setup - Start /////\n");
        sb.append("BourreGame game = new BourreGame();\n\n");
        Iterator iter = this.getPlayers().listIterator();
        while(iter.hasNext()) {
            BourrePlayer player = (BourrePlayer)iter.next();
            sb.append(player.toCode());
        }

        sb.append("PlayScenario scenario = new PlayScenario();\n");
        sb.append("scenario.setNumberOfPlayers(" + this.playScenario.getNumberOfPlayers() + ");\n");
        sb.append("scenario.setDealerTrumpCard(new Card(CardSuit." + this.getDealerTrumpCard().getCardSuite().toString() + ", CardValue." + this.getDealerTrumpCard().getCardValue().toString() +  "));\n");


        sb.append("game.setPlayScenario(scenario);\n");
        sb.append("game.startGame(false);\n");
        sb.append("game.setDealerTrumpCard(new Card(CardSuit." + this.getDealerTrumpCard().getCardSuite().toString() + ", CardValue." + this.getDealerTrumpCard().getCardValue().toString() +  "));\n\n");
        sb.append("///// Unit Test Automated Game Setup - End /////\n");
        return sb.toString();
    }

    void setPlayScenario(PlayScenario scenario) {
        this.playScenario = scenario;
    }
}
