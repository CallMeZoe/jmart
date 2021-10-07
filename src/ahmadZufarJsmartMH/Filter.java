package ahmadZufarJsmartMH;
import java.util.ArrayList;

/**
 * Write a description of class Filter here.
 *
 * @author Zufar
 * @version 02/10/2021
 */
public class Filter
{
    public static ArrayList<PriceTag> filterPriceTag(PriceTag[]list, 
    double value, boolean less){
        ArrayList<PriceTag> priceTags = new ArrayList<>();
        for (int i=0; i<list.length; i++){
            if (less){
                if(list[i].getAdjustedPrice()<value){
                    priceTags.add(list[i]);
            }
        }
        }
        return priceTags;
    }
    
    public static void filterProductRating(ArrayList<ProductRating>list, 
    double value, boolean less){
        for (int i=0; i<list.size(); ++i){
            final ProductRating j = list.get(i);
            if (less && j.getAverage()<value || !less&&j.getAverage()>= value){
                list.remove(i);
            }
        }
    }
}
