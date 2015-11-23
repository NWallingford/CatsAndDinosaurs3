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
    void getOrder(int id, Callback<OrderModel> callback) throws StorageException;
    void getOrders(int id,Callback<ArrayList<OrderModel>> callback) throws StorageException;
    void getOrders(Callback<ArrayList<OrderModel>> callback) throws StorageException;//returns only active orders
    void getUser(int id, Callback<UserModel> callback) throws StorageException;
    void getUser(String userName, String password,Callback<UserModel> callback) throws StorageException;
    void getUsers(Callback<ArrayList<UserModel>> callback) throws StorageException;
    void getOrderItems(int id, Callback<ArrayList<ItemModel>> callback) throws StorageException;
    void saveUser(IUserModel model, Callback<UserModel> callback) throws StorageException;
    void saveItem(IItemModel model, Callback<ItemModel> callback)throws StorageException;
    void saveMenu(IMenuModel model, Callback<MenuModel> callback)throws StorageException;
    void saveOrder(IOrderModel model, Callback<OrderModel> callback)throws StorageException;
    void savePayment(IPaymentModel model, Callback<PaymentModel> callback) throws StorageException;
    
    void validateUsername(String name, Callback<UserModel> callback) throws StorageException;

}
