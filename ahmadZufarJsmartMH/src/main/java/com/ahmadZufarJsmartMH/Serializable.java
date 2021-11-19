package com.ahmadZufarJsmartMH;

import java.util.HashMap;
import java.util.Map;

/**
 * Write a description of class Recognizable here.
 *
 * @author Zufar
 * @version 27/09/2021
 */
public class Serializable implements Comparable<Serializable>
{
    public final int id;
    public int inc;
    private static Map<Class<?>, Integer> mapCounter = new HashMap();

    protected Serializable()
    {
        Class getClass = getClass();
        if(mapCounter.get(getClass) == null){
            mapCounter.put(getClass,0);
        }
        else{
            mapCounter.put(getClass,mapCounter.get(getClass) + 1);
        }
        this.id = mapCounter.get(getClass);
    }

    @Override
    public int compareTo(Serializable other) {
        if(id == other.id){
            return 1;
        }
        else{
            return 0;
        }
    }

    public static <T extends Serializable> int getClosingId(Class<T> clazz){
        return mapCounter.get(clazz);
    }

    public static <T extends Serializable> int setClosingId(Class<T> clazz, int id){
        return mapCounter.put(clazz, id);
    }

    public boolean equals(Object other){
        if(other instanceof Serializable)
        {
            Serializable recog = (Serializable) other;
            return this.id == recog.id;
        }
        return false;
    }
}
