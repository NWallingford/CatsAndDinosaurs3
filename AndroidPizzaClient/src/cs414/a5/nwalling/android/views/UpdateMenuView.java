package cs414.a5.nwalling.android.views;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import cs414.a5.nwalling.R;
import cs414.a5.nwalling.android.controllers.IChangeMenuController;
import cs414.a5.nwalling.android.data.AndroidRESTClient;
import cs414.a5.nwalling.android.data.ControllerFactory;
import cs414.a5.nwalling.android.data.IDataSource;
import cs414.a5.nwalling.android.data.ModelFactory;
import cs414.a5.nwalling.android.exceptions.LoadException;
import cs414.a5.nwalling.android.models.IItemModel;

public class UpdateMenuView extends Activity implements Observer {

	IChangeMenuController controller;
	ListView menu;
//	EditText name, price;
	int selectedItemIndex;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_menu_view);
		IDataSource source = new AndroidRESTClient();
		
		try {	source.load();
		} catch (LoadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ModelFactory mf = new ModelFactory(source);
		ControllerFactory cf = new ControllerFactory(mf,source);
		controller = cf.getChangeMenuController();
		((Observable)controller).addObserver(this);
		controller.fetchCurrentMenu();
		menu = (ListView)findViewById(R.id.incompleteList);
		menu.setOnItemClickListener(new OnItemClickListener() {
		      public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
		    	  selectedItemIndex = myItemInt;
		        }
		      });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_menu_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void backButtonPressed(View view){
		Intent i = new Intent(UpdateMenuView.this, MainView.class);
		startActivity(i);
	}
	
	public void addNewMenuItemButtonPressed(View view){
		//Open dialog
	}
	public void removeSelectedItemButtonPressed(View view){
		Object toRemove = ((ArrayAdapter)menu.getAdapter()).getItem(selectedItemIndex);
		((ArrayAdapter)menu.getAdapter()).remove(toRemove);
	}
	public void editSelectedItemButtonPressed(View view){
		
	}
	public void saveButtonPressed(View view){
		controller.save();
		Intent i = new Intent(UpdateMenuView.this, MainView.class);
		startActivity(i);
		finish();
	}
	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		
		ArrayList<IItemModel> items = controller.getMenu();
		ArrayAdapter<IItemModel> adapter = new ArrayAdapter<IItemModel>(this, selectedItemIndex, items);
		menu.setAdapter(adapter);
	}
}
