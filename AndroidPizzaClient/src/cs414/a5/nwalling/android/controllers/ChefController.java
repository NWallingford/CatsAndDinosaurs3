/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.android.controllers;

import cs414.a5.nwalling.android.data.IDataSource;
import cs414.a5.nwalling.android.data.IModelFactory;
import cs414.a5.nwalling.android.exceptions.StorageException;
import cs414.a5.nwalling.android.models.IOrderModel;
import cs414.a5.nwalling.android.models.OrderModel;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 *
 * @author Jacob
 */
public class ChefController extends AbstractController implements IChefController, Callback<ArrayList<OrderModel>>{

    private ArrayList<IOrderModel> orders = new ArrayList<IOrderModel>();
    
    private IModelFactory modelFactory;
    public ChefController(IModelFactory modelFactory, IDataSource source)
    {
        super(source);
        this.modelFactory = modelFactory;
        this.source = source;
    }
    @Override
    public void init()
    {
        try {
            source.getOrders(this);
        } catch (StorageException ex) {
//            Logger.getLogger(ChefController.class.getName()).log(Level.SEVERE, null, ex);
            //Do something here to keep it from freezing and vomiting to the user.
        }
    }
    
    @Override
    public ArrayList<String> getOrders(){
        return null;
    }

    @Override
    public void markDone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void submit() {
    	for(IOrderModel order : orders)
    	{
			try {
				source.saveOrder(order, new Callback<OrderModel>(){

					@Override
					public void onFailure(Throwable arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onResponse(Response<OrderModel> arg0, Retrofit arg1) {
						// TODO Auto-generated method stub
						
					}
					
				});
			} catch (StorageException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}

    }

    @Override
    public ArrayList<String> getCompletedOrders() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	@Override
	public void onFailure(Throwable arg0) {
		// TODO Auto-generated method stub
		//Display error to screen if something went wrong
	}
	@Override
	public void onResponse(Response<ArrayList<OrderModel>> response, Retrofit retrofit) {
		this.orders.clear();
		for(IOrderModel m : response.body())
		{
			orders.add(m);
		}
		this.setChanged();
		this.notifyObservers();
	}
}
