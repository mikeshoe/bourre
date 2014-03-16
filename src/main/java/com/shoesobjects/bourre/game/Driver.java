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
import com.shoesobjects.bourre.decisionstrategies.LooseDecisionStrategy;
import com.shoesobjects.bourre.decisionstrategies.TightDecisionStrategy;
import com.shoesobjects.bourre.drawstrategies.KeepOnlyTrumpDrawStrategy;
import com.shoesobjects.bourre.playstrategies.BasicPlayStrategy;


public class Driver {

    public static void main(String[] args) {
        BourrePlayer mitt = getPlayer("Mitt", new TightDecisionStrategy());
        BourrePlayer john = getPlayer("John", new TightDecisionStrategy());
        BourrePlayer sarah = getPlayer("Sarah", new TightDecisionStrategy());
        BourrePlayer barack = getPlayer("Barack", new LooseDecisionStrategy());
        BourrePlayer joe = getPlayer("Joe", new LooseDecisionStrategy());
        BourrePlayer hillary = getPlayer("Hillary", new LooseDecisionStrategy());
        BourrePlayer huck = getPlayer("Huck", new TightDecisionStrategy());

        BourreGame game = new BourreGame();
        game.addPlayer(mitt);
        game.addPlayer(john);
        game.addPlayer(sarah);
        game.addPlayer(barack);
        game.addPlayer(joe);
        game.addPlayer(hillary);
        game.addPlayer(huck);
        System.out.println("Starting Game");
        game.startGame();
        game.getPlayerDecisions();
        System.out.println(game.toCode());
        game.playGame(false);


    }


    private static BourrePlayer getPlayer(String name, DecisionStrategy strategy) {
        BourrePlayer player = new BourrePlayer(name);
        player.setDecisionStrategy(strategy);
        player.setDrawStrategy(new KeepOnlyTrumpDrawStrategy());
        player.setPlayStrategy(new BasicPlayStrategy());
        return player;
    }
}
