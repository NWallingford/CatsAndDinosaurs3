package cs414.a5.nwalling.android.views;

import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import cs414.a5.nwalling.ItemFragment;
import cs414.a5.nwalling.R;
import cs414.a5.nwalling.android.controllers.IOrderController;
import cs414.a5.nwalling.android.data.AndroidRESTClient;
import cs414.a5.nwalling.android.data.ControllerFactory;
import cs414.a5.nwalling.android.data.IDataSource;
import cs414.a5.nwalling.android.data.ModelFactory;
import cs414.a5.nwalling.android.exceptions.LoadException;
import cs414.a5.nwalling.android.models.UserModel;

public class MakeOrderView extends Activity implements Observer {

	private IOrderController controller;
	private UserModel user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_make_order_view);
		
		IDataSource source = new AndroidRESTClient();
		
		if(getIntent().hasExtra("user")){
			user = (UserModel)getIntent().getSerializableExtra("user");
		}
		
		try {	source.load();
		} catch (LoadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ModelFactory mf = new ModelFactory(source);
		ControllerFactory cf = new ControllerFactory(mf,source);
		controller = cf.getOrderController();
		((Observable)controller).addObserver(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.make_order_view, menu);
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
	
	public void checkoutButtonPressed(View view){
		Intent i = new Intent(MakeOrderView.this, CheckoutView.class);
		if(user != null) i.putExtra("user",user);
		startActivity(i);
		finish();
	}
	
	public void backButtonPressed(View view){
		Intent i = new Intent(MakeOrderView.this, MainView.class);
		if(user != null) i.putExtra("user",user);
		startActivity(i);
		finish();
	}

	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		
	}
}
