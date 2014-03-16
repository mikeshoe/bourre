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


package com.shoesobjects.bourre.decisionstrategies;

import com.shoesobjects.bourre.BaseBourreGameTest;


public abstract class DecisionStrategiesToTest extends BaseBourreGameTest {

    public abstract void testAceTrumpCanCheck();

    public abstract void testAceTrumpCannotCheck();

    public abstract void testOneHigherTrumpCanCheck();

    public abstract void testOneHigherTrumpFaceCardCanCheck();

    public abstract void testOneHigherTrumpCannotCheck();

    public abstract void testOneHigherTrumpFaceCardCannotCheck();

    public abstract void testTwoHigherTrumpCanCheck();

    public abstract void testTwoHigherTrumpFaceCardCanCheck();

    public abstract void testTwoHigherTrumpCannotCheck();

    public abstract void testTwoHigherTrumpFaceCardCannotCheck();

    public abstract void testOneLowerTrumpCanCheck();

    public abstract void testOneLowerTrumpCannotCheck();

    public abstract void testTwoLowerTrumpCanCheck();

    public abstract void testTwoLowerTrumpCannotCheck();

    public abstract void testNoTrumpCanCheck();

    public abstract void testNoTrumpCannotCheck();


}
