package com.ahmadZufarJsmartMH;

/**
 * Merupakan interface predicate untuk pengecekkan kondisi
 *
 * @author Zufar
 * @version 19/12/2021
 */
@FunctionalInterface
public interface Predicate<T> {
     boolean predicate (T arg);
}
