
/**
 * This class is part of "World of Zuul" project. It contains Items that
 * could be found within a room
 *
 * @author Olive Tamondong
 * @version 2017.11.24
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String itemDescription;
    private String itemWeight;
    private String item;

    /**
     * Constructor for objects of class Item
     */
    public Item()
    {
        item = null;
        
    }
    public Item(String itemDescription, String itemWeight)
    {
        this.itemDescription = itemDescription;
        this.itemWeight = itemWeight;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public String getItemDescription()
    {
     return "this is item1";   
    }
    
    
}
