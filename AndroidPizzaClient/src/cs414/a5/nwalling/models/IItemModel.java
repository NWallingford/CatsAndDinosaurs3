/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.models;

import cs414.a5.nwalling.enums.ItemType;

/**
 *
 * @author Jacob
 */
public interface IItemModel extends IModel {
    String getName();
    void setName(String value);
    double getDisplayPrice();
    double getPrice();
    void setPrice(double value);
    double getSpecialPrice();
    void setSpecialPrice(double value);
    boolean getIsSpecial();
    void setIsSpecial(boolean value);
    boolean getIsActive();
    void setIsActive(boolean value);
    
    int getId();
    void setId(int value);
    
    String getDescription();
    void setDescription(String value);
}
