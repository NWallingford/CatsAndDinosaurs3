/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs414.a5.nwalling.android.controllers;

import java.util.ArrayList;

import cs414.a5.nwalling.android.data.IDataSource;
import cs414.a5.nwalling.android.data.IModelFactory;
import cs414.a5.nwalling.android.exceptions.StorageException;
import cs414.a5.nwalling.android.models.IItemModel;
import cs414.a5.nwalling.android.models.IMenuModel;
import cs414.a5.nwalling.android.models.ItemModel;
import cs414.a5.nwalling.android.models.MenuModel;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 *
 * @author Jacob
 */
public class ChangeMenuController extends AbstractController implements IChangeMenuController, Callback<ArrayList<ItemModel>>{

    private IMenuModel model;
    private IModelFactory modelFactory;
    public ChangeMenuController(IModelFactory modelFactory, IDataSource source)
    {
        super(source);
        this.model = modelFactory.getEmptyIMenuModel();
    }

    @Override
    public void removeItems(int[] indices)
    {
    }
    
    @Override
    public void newItem(String name, double price)
    {
    }
    
    @Override
    public void revokeSpecial(int[] indecies)
    {
    }
    
    @Override
    public void newSpecial(int index, double price)
    {
    }
    
    @Override
    public void save() {
			try {
				source.saveMenu(model,new Callback<MenuModel>(){

					@Override
					public void onFailure(Throwable arg0) {
						//Display error
					}

					@Override
					public void onResponse(Response<MenuModel> arg0, Retrofit arg1) {
						//DO nothing it was successful! yay
						
					}
					
				});
			} catch (StorageException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
@Override
	public ArrayList<IItemModel> getMenu()
	{
		return model.getItems();
	}
    
    @Override
    public void fetchCurrentMenu() {
        try {
			source.getMenu(this);
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public void onFailure(Throwable ex) {
		//If it fails to pull the items do something in the view
		//display pretty message in lable?
	}

	@Override
	public void onResponse(Response<ArrayList<ItemModel>> response, Retrofit retrofit) {
		ArrayList<IItemModel> tmp = new ArrayList<IItemModel>();
		for(IItemModel o : response.body())
		{
			tmp.add(o);
		}
		model = modelFactory.getEmptyIMenuModel();
		model.setItems(tmp);
		this.setChanged();
		this.notifyObservers();
	}
    
}
