package cs414.a5.nwalling.data;

import java.io.IOException;
import java.util.ArrayList;

import cs414.a5.nwalling.exceptions.LoadException;
import cs414.a5.nwalling.exceptions.StorageException;
import cs414.a5.nwalling.models.*;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class AndroidRESTClient implements IDataSource{
	private PizzaAPI api;
	
	//TODO: NOTE: Unfortunately the system wasn't designed with how this system does it's async.
	// It can be updated to do it correctly but would take more time than we have at the moment
	//and i would leave it for a future iteration.
	@Override
	public void load() {
		Retrofit retrofit = new Retrofit.Builder()
    			.baseUrl("http://10.0.2.2:8080/PizzaWebService/webresources/PizzaService/")
    			.addConverterFactory(GsonConverterFactory.create())
    			.build();
    	
    	api = retrofit.create(PizzaAPI.class);
	}

	
	@Override
	public void getItem(int id, Callback<ItemModel> callback) {
		Call<ItemModel> call = api.getItem(id);
		Response<ItemModel> response = null;
		//For now we'll do things synchronously. Maybe update it later to be async(might require a fair amount of change to the models or system.
		call.enqueue(callback);
	}

	@Override
	public void getItems(Callback<ArrayList<ItemModel>> callback) throws StorageException {
		Call<ArrayList<ItemModel>> call = api.getItems();
		//For now we'll do things synchronously. Maybe update it later to be async(might require a fair amount of change to the models or system.
		call.enqueue(callback);
		
//		return tmp;
	}

	@Override
	public void getMenu(Callback<ArrayList<ItemModel>> callback) throws StorageException {
		getItems(callback);
//		
//		MenuModel m = new MenuModel();
//		m.setItems(items);
//		return m;
	}

	public void getOrderItems(int id, Callback<ArrayList<ItemModel>> callback) throws StorageException
	{
		Call<ArrayList<ItemModel>> call = api.getOrderItems(id);
		//For now we'll do things synchronously. Maybe update it later to be async(might require a fair amount of change to the models or system.
		call.enqueue(callback);
	}
	
	public void getOrderItems(Callback<ArrayList<ItemModel>> callback) throws StorageException
	{
		Call<ArrayList<ItemModel>> call = api.getOrderItems();
		//For now we'll do things synchronously. Maybe update it later to be async(might require a fair amount of change to the models or system.
		call.enqueue(callback);
	}
	@Override
	public void getOrder(int id, Callback<OrderModel> callback) throws StorageException {
		Call<OrderModel> call = api.getOrder(id);
		//For now we'll do things synchronously. Maybe update it later to be async(might require a fair amount of change to the models or system.
		call.enqueue(callback);
	}

	@Override
	public void getOrders(int id,Callback<ArrayList<OrderModel>> callback) throws StorageException {
		Call<ArrayList<OrderModel>> call = api.getOrders(id);
			call.enqueue(callback);
//		ArrayList<IOrderModel> tmp = new ArrayList<IOrderModel>();
		//I originally wanted to do this using a sql join but i've run into trouble getting the order object
		//to properly serialize with items in it. So instead we'll get the shell of all orders here and fetch the items
		//we need when we need them by calling getOrderItems
		//No order should have a number of items significant enough to cause any real problems.
//		ArrayList<IItemModel> allItems = getOrderItems();
//		for(IOrderModel o : response.body())
//		{
//
//			tmp.add(o);
//		}
	}

	@Override
	public void getOrders(Callback<ArrayList<OrderModel>> callback) throws StorageException {
		Call<ArrayList<OrderModel>> call = api.getOrders();
		call.enqueue(callback);
	}

	@Override
	public void getUser(int id, Callback<UserModel> callback) throws StorageException {
		Call<UserModel> call = api.getUser(id);
		call.enqueue(callback);
	}

	@Override
	public void getUser(String username, String password,Callback<UserModel> callback) throws StorageException {
		Call<UserModel> call = api.getUser(username,password);
		call.enqueue(callback);
	}

	@Override
	public void getUsers(Callback<ArrayList<UserModel>> callback) throws StorageException {
		Call<ArrayList<UserModel>> call = api.getUsers();
		call.enqueue(callback);
	}

	@Override
	public void saveUser(IUserModel model, Callback<UserModel> callback) throws StorageException {
		Call<UserModel> call = api.saveUser(model);
		call.enqueue(callback);
	}

	@Override
	public void saveItem(IItemModel model, Callback<ItemModel> callback) throws StorageException {
		Call<ItemModel> call = api.saveItem(model);
		call.enqueue(callback);
		
	}

	@Override
	public void saveMenu(IMenuModel model, Callback<MenuModel> callback) throws StorageException {
		Call<MenuModel> call = api.saveMenu(model);
		call.enqueue(callback);
		
	}

	@Override
	public void saveOrder(IOrderModel model, Callback<OrderModel> callback) throws StorageException {
		Call<OrderModel> call = api.saveOrder(model);
		call.enqueue(callback);
		
	}

	@Override
	public void savePayment(IPaymentModel model, Callback<PaymentModel> callback) throws StorageException {
		Call<PaymentModel> call = api.savePayment(model);
		call.enqueue(callback);
		
	}

	
	@Override
	public void validateUsername(String name, Callback<UserModel> callback) throws StorageException {
		Call<UserModel> call = api.getUser(name);
		call.enqueue(callback);
	}


}
