/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.enums;

/**
 *
 * @author Jacob
 */
/*
This is something i would normally read in from the database
but in the interest of time and because it's not likely to change
much in the life of the program we're just going to do this for now
*/
public enum ItemType {
    Base(1)
    ,Topping(2)
    ,Sauce(3)
    ,Side(4)
    ,Beverage(5)
    ,Dessert(6);
    
    
    
    private int value;
    ItemType(int value)
    {
        this.value = value;
    }
    public int getValue()
    {
        return value;
    }
    public static ItemType getItemType(int value)
    {
        for(ItemType type : ItemType.values())
        {
            if(type.getValue() == value)
                return type;
        }
        return null;
    }
}
