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


public class Position {
    private int numberOfPlayers;
    private int numberOfChecks;
    private int numberOfIns;
    private int numberOfOuts;

    public Position() {

    }

    public Position(int numberOfPlayers, int numberOfIns, int numberOfOuts, int numberOfChecks) {
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfChecks = numberOfChecks;
        this.numberOfIns = numberOfIns;
        this.numberOfOuts = numberOfOuts;
    }


    public Position(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfChecks = 0;
        this.numberOfIns = 0;
        this.numberOfOuts = 0;
    }

    public Position(Position position) {
        this.numberOfPlayers = position.numberOfPlayers;
        this.numberOfChecks = position.numberOfChecks;
        this.numberOfIns = position.numberOfIns;
        this.numberOfOuts = position.numberOfOuts;
    }


    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public int getNumberOfChecks() {
        return numberOfChecks;
    }

    public int getNumberOfIns() {
        return numberOfIns;
    }

    public int getNumberOfOuts() {
        return numberOfOuts;
    }

    public String toString() {
        return "Position{" +
                ", numberOfPlayers=" + numberOfPlayers +
                ", numberOfChecks=" + numberOfChecks +
                ", numberOfIns=" + numberOfIns +
                ", numberOfOuts=" + numberOfOuts +
                '}';
    }

    void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    void setNumberOfChecks(int numberOfChecks) {
        this.numberOfChecks = numberOfChecks;
    }

    void setNumberOfIns(int numberOfIns) {
        this.numberOfIns = numberOfIns;
    }

    void setNumberOfOuts(int numberOfOuts) {
        this.numberOfOuts = numberOfOuts;
    }

    public boolean canCheck() {
        return (numberOfIns == 0);
    }

    public boolean isStillOpen() {
        return ((numberOfIns + numberOfOuts) < numberOfPlayers &&
                !(numberOfChecks == numberOfPlayers));
    }


    public void addDecision(DecisionStrategy.Decision decision) {
        if (DecisionStrategy.Decision.CHECK.equals(decision)) {
            this.numberOfChecks++;
        } else if (DecisionStrategy.Decision.OUT.equals(decision)) {
            this.numberOfOuts++;
        } else if (DecisionStrategy.Decision.IN.equals(decision)) {
            this.numberOfIns++;
        }
    }

    /*   public boolean allChecks() {
       return this.numberOfChecks == this.numberOfPlayers;
   } */

}
