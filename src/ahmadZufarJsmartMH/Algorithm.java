package ahmadZufarJsmartMH;
import java.util.*;

public class Algorithm {
    public static <T>int count(T[] array, T value){
        int counter = 0;
        Predicate pre =(arg)-> {
            return value == arg;
        };
        for(T arrayValue : array){
            if (pre.predicate(arrayValue)){
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

    public <T> int count(T[] array, Predicate<T> pred)
    {
        int counter = 0;
        for (T t : array) {
            if (pred.predicate(t)) {
                counter++;
            }
        }
        return counter;
    }

    public <T> int count(Iterable<T> iterable, Predicate<T> pred)
    {
        int counter = 0;
        for (T element : iterable) {
            if (pred.predicate(element)) {
                counter++;
            }
        }
        return counter;
    }

    public <T> int count(Iterator<T> iterator, Predicate<T> pred)
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

    public <T> boolean exists(T[] array, T value)
    {
        Predicate<T> p = value1 -> (value1 == value);
        for (T t : array) {
            if (p.predicate(t)) {
                return true;
            }
        }
        return false;
    }

    public <T> boolean exists(Iterable<T> iterable, T value)
    {
        Predicate<T> p = value1 -> (value1 == value);
        for (T element : iterable) {
            if (p.predicate(element)) {
                return true;
            }
        }
        return false;
    }

    public <T> boolean exists(Iterator<T> iterator, T value)
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

    public <T> boolean exists(T[] array, Predicate<T> pred)
    {
        for (T t : array) {
            if (pred.predicate(t)) {
                return true;
            }
        }
        return false;
    }

    public <T> boolean exists(Iterable<T> iterable, Predicate<T> pred)
    {
        for (T element : iterable) {
            if (pred.predicate(element)) {
                return true;
            }
        }
        return false;
    }

    public <T> boolean exists(Iterator<T> iterator, Predicate<T> pred)
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

    public <T> T find(T[] array, T value)
    {
        Predicate<T> p = value1 -> (value1 == value);
        for (T t : array) {
            if (p.predicate(t)) {
                return t;
            }
        }
        return null;
    }

    public <T> T find(Iterable<T> iterable, T value)
    {
        Predicate<T> p = value1 -> (value1 == value);
        for (T element : iterable) {
            if (p.predicate(element)) {
                return element;
            }
        }
        return null;
    }

    public <T> T find(Iterator<T> iterator, T value)
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

    public <T> T find(T[] array, Predicate<T> pred)
    {
        for (T t : array) {
            if (pred.predicate(t)) {
                return t;
            }
        }
        return null;
    }

    public <T> T find(Iterable<T> iterable, Predicate<T> pred)
    {
        for (T element : iterable) {
            if (pred.predicate(element)) {
                return element;
            }
        }
        return null;
    }

    public <T> T find(Iterator<T> iterator, Predicate<T> pred)
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

    public static<T extends Comparable<? super T>> T max(T first, T second) {
        if(first.compareTo(second) > 0) return first;
        return second;
    }

    public static<T extends Comparable<? super T>> T min(T first, T second) {
        if(first.compareTo(second) < 0) return first;
        return second;
    }

}
