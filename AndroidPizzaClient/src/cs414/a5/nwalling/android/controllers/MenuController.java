package cs414.a5.nwalling.android.controllers;

import java.util.ArrayList;

import cs414.a5.nwalling.android.data.IDataSource;
import cs414.a5.nwalling.android.data.IModelFactory;
import cs414.a5.nwalling.android.exceptions.StorageException;
import cs414.a5.nwalling.android.models.IItemModel;
import cs414.a5.nwalling.android.models.IMenuModel;
import cs414.a5.nwalling.android.models.ItemModel;
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
			String str = m.getName()+":  $"+m.getPrice();
			if(m.getIsSpecial()) str = str + "   ***Special Price!***";
			temp.add(str);
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
		this.setChanged();
		this.notifyObservers();
	}

}
