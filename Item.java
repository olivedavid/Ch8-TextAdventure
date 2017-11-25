
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
    private int itemWeight;
    private String name;
    private boolean canBePickedUp;

    /**
     * Constructor for objects of class Item
     */
    
    public Item(String itemDescription, int itemWeight)
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
     return itemDescription;   
    }
    
    public int getItemWeight()
    {
     return itemWeight;   
    }
    
    public String getName()
    {
     return name;   
    }
    
    
    
    
    
    
}
