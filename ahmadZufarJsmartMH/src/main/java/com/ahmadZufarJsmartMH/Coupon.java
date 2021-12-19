package com.ahmadZufarJsmartMH;


import com.ahmadZufarJsmartMH.dbjson.Serializable;

/**
 * Merupakan Class Coupon untuk mengatur coupon pada Jmart
 *
 * @author Zufar
 * @version 19/12/2021
 */
public class Coupon extends Serializable
{

    // instance variables - replace the example below with your own
    public  final int code;
    public final double cut;
    public final String name;
    public final double minimum;
    private boolean used = false;
    public final Type type;

    public enum Type
    {
        DISCOUNT,REBATE
    }

    public Coupon( String name, int code, Type type, double cut, double minimum)
    {

        this.used = false;
        this.name = name;
        this.code = code;
        this.type = type;
        this.cut = cut;
        this.minimum = minimum;
    }


    public boolean isUsed()
    {
        return used;
    }

    public boolean canApply (double price, double discount)
    {

        if (Treasury.getAdjustedPrice(price, discount)>=minimum && used == false)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public double apply (double price, double discount)
    {
        this.used =  true;

        if (type == Type.DISCOUNT)
        {
            return Treasury.getAdjustedPrice(price, discount)* (1-(cut/100));
        }
        else //type == type.REBATE
        {
            return Treasury.getAdjustedPrice(price, discount)- price;
        }
    }

}
