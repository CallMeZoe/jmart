package ahmadZufarJsmartMH;


/**
 * Write a description of class ProductRating here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ProductRating
{
    private long total;
    private long count;
    
    public ProductRating(){
        this.total = 0;
        this.count = 0;
    }
    
    public void insert(int rating){
        total += rating;
        count++;
    }
    
    public double getAverage(){
        return ((double)total/count);
    }
    
    public long getCount(){
        return count;
    }
    
    public long getTotal(){
        return total;
    }
}
