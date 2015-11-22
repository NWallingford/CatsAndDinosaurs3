/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.controllers;

import java.util.ArrayList;
import cs414.a5.nwalling.models.IOrderModel;

/**
 *
 * @author Jacob
 */
public interface IChefController extends IController {
    void markDone();
    void submit();
    ArrayList<String> getOrders();
    ArrayList<String> getCompletedOrders();
    void init();
}
