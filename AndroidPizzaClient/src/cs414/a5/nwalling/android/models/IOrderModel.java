/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.android.models;

import java.util.ArrayList;

import cs414.a5.nwalling.android.models.ItemModel;

/**
 *
 * @author Jacob
 */
public interface IOrderModel extends IModel{
    double getTotal();
    void setTotal(double value);
    void addItem(ItemModel item);
    void removeItem(ItemModel item);
    String getCustomerName();
    void setCustomerName(String value);
    String getCustomerAddress1();
    void setCustomerAddress1(String value);
    String getCustomerAddress2();
    void setCustomerAddress2(String value);
    String getCustomerCity();
    void setCustomerCity(String value);
    int getCustomerZip();
    void setCustomerZip(int value);
    int getUser();
    void setUser(int value);
    int getStatus();
    void setStatus(int value);
    int getId();
    void setId(int value);
    ArrayList<IItemModel> getItems();
    void setItems(ArrayList<IItemModel> items);
}
