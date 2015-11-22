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
public enum OrderStatus {
    Inactive(1),Cookint(2),Done(3),Closed(4);
    
    private int value;
    OrderStatus(int value)
    {
        this.value = value;
    }
    public int getValue()
    {
        return value;
    }
    public static OrderStatus getStatus(int value)
    {
        for(OrderStatus type : OrderStatus.values())
        {
            if(type.getValue() == value)
                return type;
        }
        return null;
    }
    
}
