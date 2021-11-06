package ahmadZufarJsmartMH;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

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
        System.out.println("Modul 6 CS_Ahmad Zufar A_1906300662");

        System.out.println("Account Id: " + new Account(null, null, null, -1).id);
        System.out.println("Account Id: " + new Account(null, null, null, -1).id);
        System.out.println("Account Id: " + new Account(null, null, null, -1).id);

        System.out.println("Paymend Id: " + new Payment(-1, -1, -1,  null).id);
        System.out.println("Paymend Id: " + new Payment(-1, -1, -1,  null).id);
        System.out.println("Paymend Id: " + new Payment(-1, -1, -1,  null).id);

        try{
            List<Product> list = read("D:/Prak OOP/jmart/src/goldenSample/randomProductList.json");
            List<Product> filtered = filterByPrice(list,  13000.0,  15000.0);
            filtered.forEach(product -> System.out.println(product.price));
        }catch (Throwable t)
        {
            t.printStackTrace();
        }

//        String filepath = "D:/Prak OOP/jmart/city.json";
//        Gson gson = new Gson();
//        try{
//            BufferedReader br = new BufferedReader(new FileReader(filepath));
//            Country input = gson.fromJson(br, Country.class);
//            System.out.println("name: " + input.name);
//            System.out.println("population: " + input.population);
//            System.out.println("states: ");
//            input.listOfStates.forEach(state -> System.out.println(state));
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }

//        Complaint complaint = new Complaint("Pengiriman tidak cepat, kurir tersesat");
//        System.out.print(complaint);
//        Account account = new Account("Zufar", "zufar.zufar@ui.ac.id", "Zufar123", 10);
//        System.out.println(account.validate());
    }

    public static List<Product> read(String filepath) throws FileNotFoundException {
        List<Product> products = new ArrayList<>();
        try{
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(filepath));
            reader.beginArray();
            while(reader.hasNext()){
                products.add(gson.fromJson(reader, Product.class));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return products;
    }

    public static List<Product> filterByPrice(List<Product> list, double minPrice, double maxPrice)
    {
        List<Product> newList = new ArrayList<Product>();
        if(minPrice != 0.0 && maxPrice != 0.0)
        {
            for(Product p : list)
            {
                double productPrice = p.price;
                if(productPrice > minPrice && productPrice < maxPrice)
                {
                    newList.add(p);
                }
            }
        }
        else if(minPrice == 0.0)
        {
            for(Product p : list)
            {
                double productPrice = p.price;
                if(productPrice < maxPrice)
                {
                    newList.add(p);
                }
            }
        }
        else if(maxPrice == 0.0)
        {
            for(Product p : list)
            {
                double productPrice = p.price;
                if(productPrice > minPrice)
                {
                    newList.add(p);
                }
            }
        }
        return newList;
    }

    public static List<Product> filterByCategory(List<Product> list, ProductCategory category)
    {
        List<Product> newList = new ArrayList<Product>();
        for(Product p : list)
        {
            if(p.category == category)
            {
                newList.add(p);
            }
        }
        return newList;
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
