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


package com.shoesobjects.deckofcards;

/**
 * Typesafe Enum Class representing the suite's of playing card
 *
 * @author Mike Shoemaker (mike@shoesobjects.com)
 */
public class CardSuit {
    private static final int DIAMOND_VAL = 1;
    private static final int CLUB_VAL = 0;
    private static final int HEART_VAL = 2;
    private static final int SPADE_VAL = 3;

    private static final String HEART_NAME = "HEART";
    private static final String SPADE_NAME = "SPADE";
    private static final String CLUB_NAME = "CLUB";
    private static final String DIAMOND_NAME = "DIAMOND";

    public static final CardSuit HEART = new CardSuit(HEART_NAME, HEART_VAL);
    public static final CardSuit DIAMOND = new CardSuit(DIAMOND_NAME, DIAMOND_VAL);
    public static final CardSuit SPADE = new CardSuit(SPADE_NAME, SPADE_VAL);
    public static final CardSuit CLUB = new CardSuit(CLUB_NAME, CLUB_VAL);

    private final int suite_val;
    private final String suite_name;


    private CardSuit(String suite_name, int suite_val) {
        this.suite_name = suite_name;
        this.suite_val = suite_val;
    }

    public String toString() {
        return this.suite_name;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final CardSuit cardSuit = (CardSuit) o;

        if (suite_val != cardSuit.suite_val) return false;
        if (!suite_name.equals(cardSuit.suite_name)) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = suite_val;
        result = 29 * result + suite_name.hashCode();
        return result;
    }

}
