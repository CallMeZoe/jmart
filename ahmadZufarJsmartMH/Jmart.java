package ahmadZufarJsmartMH;


/**
 * Write a description of class Jmart here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Jmart
{
    public static void main(String args[])
    {
        System.out.println("Warm-Up Program_Ahmad Zufar A_1906300662");
        
        System.out.println("getPromo : " + getPromo());
        System.out.println("getCustomer : " + getCustomer());
        System.out.println("getDiscountPercentage : " + getDiscountPercentage(1000, 0));
        System.out.println("getDiscountedPrice : " + getDiscountPrice(1000,10.0f));
        System.out.println("getOriginalPrice : " + getOriginalPrice(1000, 0.0f));
        System.out.println("getComissionMultiplier : " + getComissionMultiplier());
        System.out.println("getAdjustedPrice : " + getAdjustedPrice(1000));
        System.out.println("getAdminFee : " + getAdminFee(1000));
    }

    public static int getPromo(){
        return 0;
    }
    
    public static String getCustomer(){
        return "oop";
    }
    
    public static float getDiscountPercentage(int before, int after){
        if(before < after){
            return 0.0f;
        }
        else{
            float floatB = before;
            float floatA = after;
            float percen = (floatB-floatA)/before*100;
            return (percen);
        }
    }
    
    public static int getDiscountPrice(int price, float discountPercentage){
        if(discountPercentage > 100.0f){
            return 0;
        }
        else{
            float floatPrice = price;
            float finalPrice = price - ((discountPercentage * floatPrice)/100);
            return (int)finalPrice;
        }
    }
    
    public static int getOriginalPrice(int discountedPrice, float discountPercentage){
        float floatPrice = discountedPrice;
        float originalPrice = (100/(100-(discountPercentage)))*floatPrice;
        return (int)originalPrice;
    }
    
    public static float getComissionMultiplier(){
        return 0.05f;
    }
    
    public static int getAdjustedPrice(int price){
        float floatPrice = price;
        float finalPrice = floatPrice+(floatPrice*getComissionMultiplier());
        return (int)finalPrice;
    }
    
    public static int getAdminFee(int price){
        float floatPrice = price;
        float finalFee = floatPrice*getComissionMultiplier();
        return (int)finalFee;
    }
}
