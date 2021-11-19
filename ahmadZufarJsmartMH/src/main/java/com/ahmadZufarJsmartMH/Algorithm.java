package com.ahmadZufarJsmartMH;
import java.util.*;

/**
 * Write a description of class Algorithm here.
 *
 * @author Zufar
 * @version 11/10/2021
 */
public class Algorithm {


    //Collect
    public static <T> List<T> collect (T[] array, T value){
        Predicate<T> prd = val -> val.equals(value);
        List<T> list = new ArrayList<>();
        for (T element : array) {
            if (prd.predicate(element)) {
                list.add(element);
            }
        }
        return list;
    }

    public static <T> List<T> collect (Iterable<T> iterable, T value){
        Predicate<T> prd = val -> val.equals(value);
        List<T> list = new ArrayList<>();

        for (T element : iterable) {
            if (prd.predicate(element)) {
                list.add(element);
            }
        }
        return list;
    }

    public static <T> List<T> collect (Iterator<T> iterator, T value){
        Predicate<T> prd = val -> val.equals(value);
        List<T> list = new ArrayList<>();

        while (iterator.hasNext()) {
            T element = iterator.next();
            if (prd.predicate(element)) {
                list.add(element);
            }
        }
        return list;
    }

    public static <T> List<T> collect (T[] array, Predicate<T> pred){
        List<T> list = new ArrayList<>();
        for (T element : array) {
            if (pred.predicate(element)) {
                list.add(element);
            }
        }
        return list;
    }

    public static <T> List<T> collect (Iterable<T> iterable, Predicate<T> pred){
        List<T> list = new ArrayList<>();

        for (T element : iterable) {
            if (pred.predicate(element)) {
                list.add(element);
            }
        }
        return list;
    }

    public static <T> List<T> collect (Iterator<T> iterator, Predicate<T> pred){
        List<T> list = new ArrayList<>();

        while (iterator.hasNext()) {
            T element = iterator.next();
            if (pred.predicate(element)) {
                list.add(element);
            }
        }
        return list;
    }

    public static <T>int count(T[] array, T value){
        int counter = 0;
        Predicate pre =(arg)-> value == arg;
        for(T t : array){
            if (pre.predicate(t)){
                counter++;
            }
        }
        return counter;
    }

    public static <T>int count(Iterable<T> iterable, T value){
        int counter = 0;
        for(T t : iterable){
            if(t.equals(value)){
                counter++;
            }
        }
        return counter;
    }

    public static <T>int count(Iterator<T> iterator, T value){
        int counter = 0;
        while(iterator.hasNext()){
            if(iterator.next().equals(value)){
                counter++;
            }
        }
        return counter;
    }

    public static <T> int count(T[] array, Predicate<T> pred)
    {
        int counter = 0;
        for (T t : array) {
            if (pred.predicate(t)) {
                counter++;
            }
        }
        return counter;
    }

    public static <T> int count(Iterable<T> iterable, Predicate<T> pred)
    {
        int counter = 0;
        for (T element : iterable) {
            if (pred.predicate(element)) {
                counter++;
            }
        }
        return counter;
    }

    public static <T> int count(Iterator<T> iterator, Predicate<T> pred)
    {
        int counter = 0;
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                counter++;
            }
        }
        return counter;
    }

    public static <T> boolean exists(T[] array, T value)
    {
        Predicate<T> p = value1 -> (value1 == value);
        for (T t : array) {
            if (p.predicate(t)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean exists(Iterable<T> iterable, T value)
    {
        Predicate<T> p = value1 -> (value1 == value);
        for (T element : iterable) {
            if (p.predicate(element)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean exists(Iterator<T> iterator, T value)
    {
        Predicate<T> p = value1 -> (value1 == value);
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(p.predicate(element))
            {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean exists(T[] array, Predicate<T> pred)
    {
        for (T t : array) {
            if (pred.predicate(t)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean exists(Iterable<T> iterable, Predicate<T> pred)
    {
        for (T element : iterable) {
            if (pred.predicate(element)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean exists(Iterator<T> iterator, Predicate<T> pred)
    {
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                return true;
            }
        }
        return false;
    }

    public static <T> T find(T[] array, T value)
    {
        Predicate<T> p = value1 -> (value1 == value);
        for (T t : array) {
            if (p.predicate(t)) {
                return t;
            }
        }
        return null;
    }

    public static <T> T find(Iterable<T> iterable, T value)
    {
        Predicate<T> p = value1 -> (value1 == value);
        for (T element : iterable) {
            if (p.predicate(element)) {
                return element;
            }
        }
        return null;
    }

    public static <T> T find(Iterator<T> iterator, T value)
    {
        Predicate<T> p = value1 -> (value1 == value);
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(p.predicate(element))
            {
                return element;
            }
        }
        return null;
    }

    public static <T> T find(T[] array, Predicate<T> pred)
    {
        for (T t : array) {
            if (pred.predicate(t)) {
                return t;
            }
        }
        return null;
    }

    public static <T> T find(Iterable<T> iterable, Predicate<T> pred)
    {
        for (T element : iterable) {
            if (pred.predicate(element)) {
                return element;
            }
        }
        return null;
    }

    public static <T> T find(Iterator<T> iterator, Predicate<T> pred)
    {
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                return element;
            }
        }
        return null;
    }

    //Max
    public static <T extends Comparable<? super T>> T max(T first, T  second){
        if(first.compareTo(second) > 0){
            return first;
        } else {
            return second;
        }
    }

    public static <T extends Comparable<? super T>> T max(T[] array) {
        T max = null;
        for (T element : array) {
            if (max == null) max = element;
            else if (element.compareTo(max) > 0) {
                max = element;
            }
        }
        return max;
    }

    public static <T extends Comparable<? super T>> T max(Iterable<T> iterable) {
        T max = null;
        Iterator<T> iterator = iterable.iterator();

        while (iterator.hasNext()) {
            T element = iterator.next();
            if (max == null) max = element;
            else if (element.compareTo(max) > 0) max = element;
        }
        return max;
    }

    public static <T extends Comparable<? super T>> T max(Iterator<T> iterator) {
        T max = null;
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (max == null) max = element;
            else if (element.compareTo(max) > 0) max = element;
        }
        return max;
    }

    public static <T extends Comparable<? super T>> T max(T first, T second, Comparator<? super T> comparator) {
        int compare = comparator.compare(first, second);
        if (compare > 0) {
            return first;
        } else {
            return second;
        }
    }

    public static <T extends Comparable<? super T>> T max(T[] array, Comparator<? super T> comparator) {
        T max = null;
        for (T element : array) {
            if (max == null) max = element;
            else {
                int compare = comparator.compare(element, max);
                if (compare > 0) max = element;
            }
        }
        return max;
    }

    public static <T extends Comparable<? super T>> T max(Iterable<T> iterable, Comparator<? super T> comparator) {
        T max = null;
        for (T element : iterable) {
            if (max == null) max = element;
            else {
                int compare = comparator.compare(element, max);
                if (compare > 0) max = element;
            }
        }
        return max;
    }

    public static <T extends Comparable<? super T>> T max(Iterator<T> iterator, Comparator<? super T> comparator) {
        T max = null;
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (max == null) max = element;
            else {
                int compare = comparator.compare(element, max);
                if (compare > 0) max = element;
            }
        }
        return max;
    }

    //Min
    public static <T extends Comparable<? super T>> T min(T  first, T  second){
        if(first.compareTo(second) < 0){
            return first;
        } else {
            return second;
        }
    }

    public static <T extends Comparable<? super T>> T min(T[] array) {
        T min = null;
        for (T element : array) {
            if (min == null) min = element;
            else if (element.compareTo(min) < 0) {
                min = element;
            }
        }
        return min;
    }

    public static <T extends Comparable<? super T>> T min(Iterable<T> iterable) {
        T min = null;
        for (T element : iterable) {
            if (min == null) min = element;
            else if (element.compareTo(min) < 0) min = element;
        }
        return min;
    }

    public static <T extends Comparable<? super T>> T min(Iterator<T> iterator) {
        T min = null;
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (min == null) min = element;
            else if (element.compareTo(min) < 0) min = element;
        }
        return min;
    }

    public static <T extends Comparable<? super T>> T min(T first, T second, Comparator<? super T> comparator) {
        int compare = comparator.compare(first, second);
        if (compare < 0) {
            return first;
        } else {
            return second;
        }
    }

    public static <T extends Comparable<? super T>> T min(T[] array, Comparator<? super T> comparator) {
        T min = null;
        for (T element : array) {
            if (min == null) min = element;
            else {
                int compare = comparator.compare(element, min);
                if (compare < 0) min = element;
            }
        }
        return min;
    }

    public static <T extends Comparable<? super T>> T min(Iterable<T> iterable, Comparator<? super T> comparator) {
        T min = null;
        for (T element : iterable) {
            if (min == null) min = element;
            else {
                int compare = comparator.compare(element, min);
                if (compare < 0) min = element;
            }
        }
        return min;
    }

    public static <T extends Comparable<? super T>> T min(Iterator<T> iterator, Comparator<? super T> comparator) {
        T min = null;
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (min == null) min = element;
            else {
                int compare = comparator.compare(element, min);
                if (compare < 0) min = element;
            }
        }
        return min;
    }

    public static <T> List<T> paginate(T[] array, int page, int pageSize, Predicate<T> pred){
        List<T> newList = new ArrayList<>();
        for(T obj : array)
        {
            if(pred.predicate(obj))
            {
                newList.add(obj);
            }
        }
        int startIndex = page * pageSize;
        int endIndex = startIndex + pageSize;
        List<T> paginatedList;
        if(endIndex > newList.size())
        {
            paginatedList = newList.subList(startIndex, newList.size());
        }
        else
        {
            paginatedList = newList.subList(startIndex, endIndex);
        }
        return paginatedList;
    }

    public static <T> List<T> paginate(Iterable<T> iterable, int page, int pageSize, Predicate<T> pred){
        List<T> newList = new ArrayList<>();
        Iterator<T> iterator = iterable.iterator();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                newList.add(element);
            }
        }
        int startIndex = page * pageSize;
        int endIndex = startIndex + pageSize;
        List<T> paginatedList;
        if(endIndex > newList.size())
        {
            paginatedList = newList.subList(startIndex, newList.size());
        }
        else
        {
            paginatedList = newList.subList(startIndex, endIndex);
        }
        return paginatedList;
    }

}
