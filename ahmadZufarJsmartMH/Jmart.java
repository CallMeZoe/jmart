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
        System.out.println("Modul 2_Ahmad Zufar A_1906300662");
    }

    public static Product create(){
        PriceTag priceTag = new PriceTag(80000);
        Product product = new Product("BUMI",500,false,priceTag,
        ProductCategory.BOOK);
        return product;
    }
}
