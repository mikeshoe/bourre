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


public class PositionTest extends BaseBourreGameTest {
    public void testCanCheck() {
        Position p = getFirstToActOfSevenPlayersPosition();
        assertTrue(p.canCheck());
    }

    public void testCannotCheck() {
        Position p = new Position(7, 1, 0, 0);
        assertFalse(p.canCheck());
    }

    public void testStillOpenNoDecisions() {
        Position p = new Position(7, 0, 0, 0);
        assertTrue(p.isStillOpen());
    }

    public void testStillOpenSomeInOut() {
        Position p = new Position(7, 2, 3, 0);
        assertTrue(p.isStillOpen());
    }

    public void testStillOpenSomeInOutCheck() {
        Position p = new Position(7, 2, 1, 1);
        assertTrue(p.isStillOpen());
    }

    public void testNotStillOpenAllChecks() {
        Position p = new Position(7, 0, 0, 7);
        assertFalse(p.isStillOpen());
    }

    public void testNotStillOpenEveryoneInOrOut() {
        Position p = new Position(7, 3, 4, 0);
        assertFalse(p.isStillOpen());
    }
}
