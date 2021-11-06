package ahmadZufarJsmartMH;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import  java.util.List;
import com.google.gson.*;

/**
 * Write a description of class Jmart here.
 *
 * @author Zufar
 * @version 02/10/2021
 */
public class Jmart
{
    class Country{
        public String name;
        public int population;
        public List<String> listOfStates;
    }

    public static void main(String args[])
    {
        System.out.println("Modul 6 TP_Ahmad Zufar A_1906300662");

        String filepath = "D:/Prak OOP/jmart/city.json";
        Gson gson = new Gson();
        try{
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            Country input = gson.fromJson(br, Country.class);
            System.out.println("name: " + input.name);
            System.out.println("population: " + input.population);
            System.out.println("states: ");
            input.listOfStates.forEach(state -> System.out.println(state));
        }
        catch (IOException e){
            e.printStackTrace();
        }

//        Complaint complaint = new Complaint("Pengiriman tidak cepat, kurir tersesat");
//        System.out.print(complaint);
//        Account account = new Account("Zufar", "zufar.zufar@ui.ac.id", "Zufar123", 10);
//        System.out.println(account.validate());
    }
    

    /*public static Product create(){
        PriceTag priceTag = new PriceTag(80000);
        Product product = new Product("BUMI",500,false,priceTag,
        ProductCategory.BOOK);
        return product;
    }
    
    public static Product createProduct()
    {
        PriceTag priceTag = new PriceTag(80000);
        Product product = new Product("BUMI",500,false,priceTag,
        ProductCategory.BOOK);
        return product;
    }

    public static Coupon createCoupun()
    {
       Coupon coupon = new Coupon("BackToSchool",1, Coupon.Type.DISCOUNT, 10, 1000);
       return coupon;
    }

    public static ShipmentDuration createShipmentDuration (String args[])
    {
        return new ShipmentDuration(ShipmentDuration.INSTANT, ShipmentDuration.REGULER);
    }*/
}
