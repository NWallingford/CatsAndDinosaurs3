/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.controllers;

import cs414.a5.nwalling.data.IDataSource;
import cs414.a5.nwalling.data.IModelFactory;
import cs414.a5.nwalling.enums.OrderStatus;
import cs414.a5.nwalling.exceptions.StorageException;

import java.util.ArrayList;

import cs414.a5.nwalling.models.IItemModel;
import cs414.a5.nwalling.models.IMenuModel;
import cs414.a5.nwalling.models.IOrderModel;
import cs414.a5.nwalling.models.ItemModel;
import cs414.a5.nwalling.models.MenuModel;
import cs414.a5.nwalling.models.OrderModel;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 *
 * @author Jacob
 */
public class OrderController extends AbstractController implements IOrderController, Callback<ArrayList<ItemModel>> {
    public static final transient String PROP_MENU = "menu";
    //This is a hack to get it working.
    private IMenuModel menu;
    private IOrderModel order;
    private OrderStatus status;
    
    private IModelFactory modelFactory;
    public OrderController(IModelFactory modelFactory, IDataSource source)
    {
        super(source);
        this.modelFactory = modelFactory;
        this.menu = modelFactory.getEmptyIMenuModel();
        this.order = modelFactory.getEmptyIOrderModel();
    }
    
    @Override
    public void fetchMenu()
    {
    	try {
			source.getMenu(this);
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public ArrayList<String> getMenu()
    {
//        return (MenuModel)menu;
        return null;
    }
    
    @Override
    public void addToOrder(int[] indices)
    {
        //
    }
    @Override
    public void removeFromOrder(int[] indices)
    {
        //
    }

    @Override
    public ArrayList<String> getOrder() {
    	
    	return null;
    }

    @Override
    public boolean saveOrder() {
    	
    	try {
			source.saveOrder(order, new Callback<OrderModel>() {

				@Override
				public void onFailure(Throwable arg0) {
					// TODO Auto-generated method stub
					//display error
				}

				@Override
				public void onResponse(Response<OrderModel> arg0, Retrofit arg1) {
					// TODO Auto-generated method stub
					//do nothing it was successful
				}
				
			});
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    	return true;
//        return order.save();
    }

    @Override
    public String getOrderStatus() {
        return status.toString();
    }

	@Override
	public void onFailure(Throwable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onResponse(Response<ArrayList<ItemModel>> response, Retrofit arg1) {
		ArrayList<IItemModel> tmp = new ArrayList<IItemModel>();
		for(IItemModel o : response.body())
		{
			tmp.add(o);
		}
		menu = modelFactory.getEmptyIMenuModel();
		menu.setItems(tmp);
	}
}
