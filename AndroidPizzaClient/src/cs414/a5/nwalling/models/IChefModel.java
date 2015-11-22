/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.models;

import java.util.ArrayList;

/**
 *
 * @author Jacob
 */
public interface IChefModel extends IModel{
    ArrayList<String> getOrders();
    ArrayList<String> getCompletedOrders();
    void setOrders(ArrayList<IOrderModel> value);
    
    void markDone(int orderIdex);
    
}
