package cs414.a5.nwalling.data;

import java.io.IOException;
import java.util.ArrayList;

import cs414.a5.nwalling.exceptions.LoadException;
import cs414.a5.nwalling.exceptions.StorageException;
import cs414.a5.nwalling.models.*;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.Call;
import retrofit.Response;

public class AndroidRESTClient implements IDataSource{
	private PizzaAPI api;
	
	//TODO: NOTE: Unfortunately the system wasn't designed with how this system does it's async.
	// It can be updated to do it correctly but would take more time than we have at the moment
	//and i would leave it for a future iteration.
	@Override
	public void load() throws LoadException {
		Retrofit retrofit = new Retrofit.Builder()
    			.baseUrl("http://localhost:8080/PizzaWebService/webresources/PizzaService/")
    			.addConverterFactory(GsonConverterFactory.create())
    			.build();
    	
    	api = retrofit.create(PizzaAPI.class);
	}

	
	@Override
	public IItemModel getItem(int id) {
		Call<ItemModel> call = api.getItem(id);
		Response<ItemModel> response = null;
		try {
			//For now we'll do things synchronously. Maybe update it later to be async(might require a fair amount of change to the models or system.
			response = call.execute();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return response.body();
	}

	@Override
	public ArrayList<IItemModel> getItems() throws StorageException {
		Call<ArrayList<ItemModel>> call = api.getItems();
		Response<ArrayList<ItemModel>> response = null;
		try {
			//For now we'll do things synchronously. Maybe update it later to be async(might require a fair amount of change to the models or system.
			response = call.execute();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		ArrayList<IItemModel> tmp = new ArrayList<IItemModel>();
		for(IItemModel o : response.body())
		{
			tmp.add(o);
		}
		return tmp;
	}

	@Override
	public IMenuModel getMenu() throws StorageException {
		Call<MenuModel> call = api.getMenu();
		Response<MenuModel> response = null;
		try {
			//For now we'll do things synchronously. Maybe update it later to be async(might require a fair amount of change to the models or system.
			response = call.execute();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return response.body();
	}

	@Override
	public IOrderModel getOrder(int id) throws StorageException {
		Call<OrderModel> call = api.getOrder(id);
		Response<OrderModel> response = null;
		try {
			//For now we'll do things synchronously. Maybe update it later to be async(might require a fair amount of change to the models or system.
			response = call.execute();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return response.body();
	}

	@Override
	public ArrayList<IOrderModel> getOrders(int id) throws StorageException {
		Call<ArrayList<OrderModel>> call = api.getOrders(id);
		Response<ArrayList<OrderModel>> response = null;
		try {
			//For now we'll do things synchronously. Maybe update it later to be async(might require a fair amount of change to the models or system.
			response = call.execute();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		ArrayList<IOrderModel> tmp = new ArrayList<IOrderModel>();
		for(IOrderModel o : response.body())
		{
			tmp.add(o);
		}
		return tmp;
	}

	@Override
	public ArrayList<IOrderModel> getOrders() throws StorageException {
		Call<ArrayList<OrderModel>> call = api.getOrders();
		Response<ArrayList<OrderModel>> response = null;
		try {
			//For now we'll do things synchronously. Maybe update it later to be async(might require a fair amount of change to the models or system.
			response = call.execute();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		ArrayList<IOrderModel> tmp = new ArrayList<IOrderModel>();
		for(IOrderModel o : response.body())
		{
			tmp.add(o);
		}
		return tmp;
	}

	@Override
	public IUserModel getUser(int id) throws StorageException {
		Call<UserModel> call = api.getUser(id);
		Response<UserModel> response = null;
		try {
			//For now we'll do things synchronously. Maybe update it later to be async(might require a fair amount of change to the models or system.
			response = call.execute();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return response.body();
	}

	@Override
	public IUserModel getUser(String username, String password) throws StorageException {
		Call<UserModel> call = api.getUser(username,password);
		Response<UserModel> response = null;
		try {
			//For now we'll do things synchronously. Maybe update it later to be async(might require a fair amount of change to the models or system.
			response = call.execute();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return response.body();
	}

	@Override
	public ArrayList<IUserModel> getUsers() throws StorageException {
		Call<ArrayList<UserModel>> call = api.getUsers();
		Response<ArrayList<UserModel>> response = null;
		try {
			//For now we'll do things synchronously. Maybe update it later to be async(might require a fair amount of change to the models or system.
			response = call.execute();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		ArrayList<IUserModel> tmp = new ArrayList<IUserModel>();
		for(IUserModel o : response.body())
		{
			tmp.add(o);
		}
		return tmp;
	}

	@Override
	public void saveUser(IUserModel model) throws StorageException {
		Call<UserModel> call = api.saveUser(model);
		Response<UserModel> response = null;
		try {
			//For now we'll do things synchronously. Maybe update it later to be async(might require a fair amount of change to the models or system.
			response = call.execute();
		} catch (IOException e) {
			e.printStackTrace();
			throw new StorageException("Failed to save IUserModel!",e);
		}
		if(!response.isSuccess())
			throw new StorageException("Failed to save IUserModel! Unkown reason!");
	}

	@Override
	public void saveItem(IItemModel model) throws StorageException {
		Call<ItemModel> call = api.saveItem(model);
		Response<ItemModel> response = null;
		try {
			//For now we'll do things synchronously. Maybe update it later to be async(might require a fair amount of change to the models or system.
			response = call.execute();
		} catch (IOException e) {
			e.printStackTrace();
			throw new StorageException("Failed to save IItemModel!",e);
		}
		if(!response.isSuccess())
			throw new StorageException("Failed to save IItemModel! Unkown reason!");
		
	}

	@Override
	public void saveMenu(IMenuModel model) throws StorageException {
		Call<MenuModel> call = api.saveMenu(model);
		Response<MenuModel> response = null;
		try {
			//For now we'll do things synchronously. Maybe update it later to be async(might require a fair amount of change to the models or system.
			response = call.execute();
		} catch (IOException e) {
			e.printStackTrace();
			throw new StorageException("Failed to save IMenuModel!",e);
		}
		if(!response.isSuccess())
			throw new StorageException("Failed to save IMenuModel! Unkown reason!");
		
	}

	@Override
	public void saveOrder(IOrderModel model) throws StorageException {
		Call<OrderModel> call = api.saveOrder(model);
		Response<OrderModel> response = null;
		try {
			//For now we'll do things synchronously. Maybe update it later to be async(might require a fair amount of change to the models or system.
			response = call.execute();
		} catch (IOException e) {
			e.printStackTrace();
			throw new StorageException("Failed to save IOrderModel!",e);
		}
		if(!response.isSuccess())
			throw new StorageException("Failed to save IOrderModel! Unkown reason!");
		
	}

	@Override
	public void savePayment(IPaymentModel model) throws StorageException {
		Call<PaymentModel> call = api.savePayment(model);
		Response<PaymentModel> response = null;
		try {
			//For now we'll do things synchronously. Maybe update it later to be async(might require a fair amount of change to the models or system.
			response = call.execute();
		} catch (IOException e) {
			e.printStackTrace();
			throw new StorageException("Failed to save IPaymentModel!",e);
		}
		if(!response.isSuccess())
			throw new StorageException("Failed to save IPaymentModel! Unkown reason!");
		
	}

	
	@Override
	public boolean validateUsername(String name) throws StorageException {
		Call<UserModel> call = api.getUser(name);
		Response<UserModel> response = null;
		try {
			//For now we'll do things synchronously. Maybe update it later to be async(might require a fair amount of change to the models or system.
			response = call.execute();
		} catch (IOException e) {
			e.printStackTrace();
			return true;
		}
		return response.body() == null;
	}


}
