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
 * Typesafe Enum Class representing the value of individual playing cards
 *
 * @author Mike Shoemaker (mike@shoesobjects.com)
 */
public class CardValue {
    private final static int ACE_VAL = 12;
    private final static int KING_VAL = 11;
    private final static int QUEEN_VAL = 10;
    private final static int JACK_VAL = 9;
    private final static int TEN_VAL = 8;
    private final static int NINE_VAL = 7;
    private final static int EIGHT_VAL = 6;
    private final static int SEVEN_VAL = 5;
    private final static int SIX_VAL = 4;
    private final static int FIVE_VAL = 3;
    private final static int FOUR_VAL = 2;
    private final static int THREE_VAL = 1;
    private final static int TWO_VAL = 0;

    public final static CardValue ACE = new CardValue("ACE", ACE_VAL);
    public final static CardValue KING = new CardValue("KING", KING_VAL);
    public final static CardValue QUEEN = new CardValue("QUEEN", QUEEN_VAL);
    public final static CardValue JACK = new CardValue("JACK", JACK_VAL);
    public final static CardValue TEN = new CardValue("TEN", TEN_VAL);
    public final static CardValue NINE = new CardValue("NINE", NINE_VAL);
    public final static CardValue EIGHT = new CardValue("EIGHT", EIGHT_VAL);
    public final static CardValue SEVEN = new CardValue("SEVEN", SEVEN_VAL);
    public final static CardValue SIX = new CardValue("SIX", SIX_VAL);
    public final static CardValue FIVE = new CardValue("FIVE", FIVE_VAL);
    public final static CardValue FOUR = new CardValue("FOUR", FOUR_VAL);
    public final static CardValue THREE = new CardValue("THREE", THREE_VAL);
    public final static CardValue TWO = new CardValue("TWO", TWO_VAL);

    private final int value;
    private final String name;

    private CardValue(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final CardValue cardValue = (CardValue) o;

        if (value != cardValue.value) return false;
        if (!name.equals(cardValue.name)) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = value;
        result = 29 * result + name.hashCode();
        return result;
    }

    public String toString() {
        return name;
    }

    public boolean isHigher(CardValue cardValue) {
        return (this.value > cardValue.value);
    }

    public boolean isLower(CardValue cardValue) {
        return !isHigher(cardValue);
    }
}
