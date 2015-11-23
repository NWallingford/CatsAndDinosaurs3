package cs414.a5.nwalling.android.data;

import java.util.ArrayList;

import cs414.a5.nwalling.android.exceptions.StorageException;
import cs414.a5.nwalling.android.models.*;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
public interface PizzaAPI {


	@GET("Get/Item/{id}")
	Call<ItemModel> getItem(@Path("id") int id);
	@GET("Get/Items")
	Call<ArrayList<ItemModel>> getItems();
	@GET("Get/Menu")
	Call<MenuModel> getMenu();
	@GET("Get/Order/{id}")
	Call<OrderModel> getOrder(@Path("id") int id);
	@GET("Get/Orders/{id}")
	Call<ArrayList<OrderModel>> getOrders(@Path("id") int id);
	@GET("Get/Orders")
	Call<ArrayList<OrderModel>> getOrders();
	@GET("Get/OrderItems")
	Call<ArrayList<ItemModel>> getOrderItems();
	@GET("Get/OrderItems/{id}")
	Call<ArrayList<ItemModel>> getOrderItems(@Path("id") int id);
	@GET("Get/User/{id}")
	Call<UserModel> getUser(@Path("id") int id);
	@GET("Get/User/{username}/{password}")
	Call<UserModel> getUser(@Path("username") String userName,@Path("password") String password);
	@GET("Get/Users")
	Call<ArrayList<UserModel>> getUsers();
	@GET("Get/User/{username}")
	Call<UserModel> getUser(@Path("username") String username);
	@POST("Save/User")
	Call<UserModel> saveUser(@Body UserModel model);
	@POST("Save/Item")
	Call<ItemModel> saveItem(@Body ItemModel model);
	@POST("Save/Menu")
	Call<MenuModel> saveMenu(@Body MenuModel model);
	@POST("Save/Order")
	Call<OrderModel> saveOrder(@Body OrderModel model);
	@POST("Save/Payment")
	Call<PaymentModel> savePayment(@Body PaymentModel model);
	

}
