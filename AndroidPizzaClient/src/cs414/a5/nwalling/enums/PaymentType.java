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
public enum PaymentType {
        Cash(1)
    ,Credit(2)
    ,Debit(3);
    
    
    
    private int value;
    PaymentType(int value)
    {
        this.value = value;
    }
    public int getValue()
    {
        return value;
    }
    public static PaymentType getPaymentType(int value)
    {
        for(PaymentType type : PaymentType.values())
        {
            if(type.getValue() == value)
                return type;
        }
        return null;
    }
    
}
