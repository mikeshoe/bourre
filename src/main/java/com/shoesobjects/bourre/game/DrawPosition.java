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

import java.util.ArrayList;
import java.util.List;


public class DrawPosition {
    private Integer numberOfStartingPlayers;
    private Integer numberOfActivePlayers;
    private List<Integer> drawAmountList = new ArrayList<Integer>();

    public DrawPosition(Integer numberOfStartingPlayers, Integer numberOfActivePlayers) {
        this.numberOfStartingPlayers = numberOfStartingPlayers;
        this.numberOfActivePlayers = numberOfActivePlayers;
    }


    public void addDrawNumber(Integer numberOfCards) {
        this.drawAmountList.add(numberOfCards);
    }

    public int calcMaxDraw() {
        int drawPossible = ((52 / this.numberOfStartingPlayers) - 5);

        if (drawPossible > 5) return 5;

        return drawPossible;
    }
}
