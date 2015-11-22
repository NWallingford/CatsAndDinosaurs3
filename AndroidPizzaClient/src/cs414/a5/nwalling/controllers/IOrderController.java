/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.controllers;

import cs414.a5.nwalling.enums.OrderStatus;
import java.util.ArrayList;

/**
 *
 * @author Jacob
 */
public interface IOrderController extends IController{
    ArrayList<String> getMenu();
    ArrayList<String> getOrder();
    
    void addToOrder(int[] indicies);
    void removeFromOrder(int[] indicies);
    
    String getOrderStatus();
    boolean saveOrder();
}
