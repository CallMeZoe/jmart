package ahmadZufarJsmartMH;


/**
 * Write a description of class Coupon here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Coupon extends Recognizable
{
    public enum Type{
        DISCOUNT, REBATE;
    }
    
    public final String name;
    public final int code;
    public final double cut;
    public final Type type;
    public final double minimum;
    private boolean used;
    public double price = 12.500;
    public double discount = 10;
    
    public Coupon(String name, int code, Type type, double cut, double minimum)
    {
        this.name = name;
        this.code = code;
        this.type = type;
        this.cut = cut;
        this.minimum = minimum;
        this.used = false;
    }
    
    public boolean isUsed(){
        return used;
    }

    public boolean canApply(Treasury treasury){
        if(treasury.getAdjustedPrice(price, discount) >= minimum && used == false){
            return true;
        }
        else{
            return false;
        }
    }

    public double apply(Treasury treasury){
        used = true;
        if (type == type.DISCOUNT){
            return (100 - cut) / 100* treasury.getAdjustedPrice(price, discount);
        }
        else{//type == REBATE
            return treasury.getAdjustedPrice(price, discount) - treasury.price;
        }
    }

}
