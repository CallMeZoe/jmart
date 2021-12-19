package com.ahmadZufarJsmartMH;


/**
 * Merupakan Class Shipment Duration untuk durasi shipment yang dipilih
 *
 * @author Zufar
 * @version 19/12/2021
 */
public class ShipmentDuration
{
    public static final ShipmentDuration INSTANT = new ShipmentDuration(1 << 0);
    public static final ShipmentDuration SAME_DAY = new ShipmentDuration(1 << 1);
    public static final ShipmentDuration NEXT_DAY = new ShipmentDuration(1 << 2);
    public static final ShipmentDuration REGULER = new ShipmentDuration(1 << 3);
    public static final ShipmentDuration KARGO = new ShipmentDuration(1 << 4);
    private final int bit;

    private ShipmentDuration(int bit){
        this.bit = bit;
    }

    public ShipmentDuration(ShipmentDuration... args) {
        int tmp = args[0].bit;
        for (int i = 1; i < args.length; i++) {
            tmp = tmp | args[i].bit;
        }
        this.bit = tmp;
    }

    public boolean isDuration(ShipmentDuration reference) {
        return (this.bit & reference.bit) == reference.bit;
    }
 
}
