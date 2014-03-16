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


public class DrawPositionTest extends BaseBourreGameTest {

    public void test2PlayersCanDraw5() {
        DrawPosition p = new DrawPosition(2, 2);
        assertEquals(5, p.calcMaxDraw());
    }

    public void test5PlayersCanOnlyDraw5() {
        DrawPosition p = new DrawPosition(5, 5);
        assertEquals(5, p.calcMaxDraw());
    }

    public void test6PlayersCanOnlyDraw3() {
        DrawPosition p = new DrawPosition(6, 6);
        assertEquals(3, p.calcMaxDraw());
    }

    public void test7PlayersCanOnlyDraw2() {
        DrawPosition p = new DrawPosition(7, 7);
        assertEquals(2, p.calcMaxDraw());
    }
}
