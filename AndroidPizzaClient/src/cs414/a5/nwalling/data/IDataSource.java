/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.data;

import cs414.a5.nwalling.exceptions.LoadException;
import cs414.a5.nwalling.exceptions.StorageException;
import java.util.ArrayList;
import cs414.a5.nwalling.models.*;
import retrofit.Callback;

/**
 *
 * @author Jacob
 */
public interface IDataSource {
    void load() throws LoadException;
    
    void getItem(int id,Callback<ItemModel> callback) throws StorageException;
    void getItems(Callback<ArrayList<ItemModel>> callback) throws StorageException;
    void getMenu(Callback<ArrayList<ItemModel>> callback) throws StorageException;
    IOrderModel getOrder(int id) throws StorageException;
    ArrayList<IOrderModel> getOrders(int id) throws StorageException;
    ArrayList<IOrderModel> getOrders() throws StorageException;//returns only active orders
    IUserModel getUser(int id) throws StorageException;
    IUserModel getUser(String userName, String password) throws StorageException;
    ArrayList<IUserModel> getUsers() throws StorageException;
    ArrayList<IItemModel> getOrderItems(int id) throws StorageException;
    void saveUser(IUserModel model) throws StorageException;
    void saveItem(IItemModel model)throws StorageException;
    void saveMenu(IMenuModel model)throws StorageException;
    void saveOrder(IOrderModel model)throws StorageException;
    void savePayment(IPaymentModel model) throws StorageException;
    
    boolean validateUsername(String name) throws StorageException;
}
