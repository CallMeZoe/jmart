package com.ahmadZufarJsmartMH;

/**
 * Merupakan Class Pair yang me-return pasangan first dan second
 *
 * @author Zufar
 * @version 19/12/2021
 */
public class Pair<T, U> {
    public T first;
    public U second;

    public Pair() {
        this.first = null;
        this.second = null;
    }

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }
}
