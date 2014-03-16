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
 * Class representing a single playing card
 *
 * @author Mike Shoemaker (mike@shoesobjects.com)
 */
public class Card {
    private final CardSuit cardSuit;
    private final CardValue cardValue;
    private Status status = Status.UNDECIDED;


    public enum Status {
        UNDECIDED, HOLD
    }

    public Card(CardSuit cardSuit, CardValue cardValue) {
        this.cardSuit = cardSuit;
        this.cardValue = cardValue;
    }

    public CardSuit getCardSuite() {
        return cardSuit;
    }

    public CardValue getCardValue() {
        return cardValue;
    }


    public boolean isHeld() {
        return status == Status.HOLD;
    }

    public boolean isUndecided() {
        return status == Status.UNDECIDED;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return this.status;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (!cardSuit.equals(card.cardSuit)) return false;
        if (!cardValue.equals(card.cardValue)) return false;
        if (status != card.status) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = cardSuit.hashCode();
        result = 31 * result + cardValue.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }

    public boolean isSameSuit(Card card) {
        return (this.cardSuit.equals(card.getCardSuite()));
    }

    public boolean isHigherValue(Card card) {
        return (this.cardValue.isHigher(card.getCardValue()));
    }

    public String toString() {
        return cardValue.toString() + " Of " +  cardSuit.toString();
    }
}
