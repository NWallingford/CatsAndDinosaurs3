package cs414.a5.nwalling.controllers;

import java.util.ArrayList;

import cs414.a5.nwalling.data.IDataSource;
import cs414.a5.nwalling.data.IModelFactory;
import cs414.a5.nwalling.exceptions.StorageException;
import cs414.a5.nwalling.models.IItemModel;
import cs414.a5.nwalling.models.IMenuModel;
import cs414.a5.nwalling.models.ItemModel;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MenuController extends AbstractController implements IMenuController, Callback<ArrayList<ItemModel>>{
	
	IMenuModel model;
	private IModelFactory modelFactory;
	
	public MenuController(IModelFactory modelFactory, IDataSource source) {
		super(source);
		this.modelFactory = modelFactory;
		model = modelFactory.getEmptyIMenuModel();
	}

	public void fetchMenu(){
		try {
			source.getMenu(this);
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	@Override
	public ArrayList<String> getMenu() {
		ArrayList<String> temp = new ArrayList<String>();
		for(IItemModel m:model.getItems()){
			temp.add(m.getName());
		}
		return temp;
	}

	@Override
	public void onFailure(Throwable arg0) {
		int x = 10;
		x = 5;
		
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
	}

}
