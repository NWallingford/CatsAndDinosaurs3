package cs414.a5.nwalling.views;

import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import cs414.a5.nwalling.R;
import cs414.a5.nwalling.controllers.IOrderController;
import cs414.a5.nwalling.controllers.IPaymentController;
import cs414.a5.nwalling.data.AndroidRESTClient;
import cs414.a5.nwalling.data.ControllerFactory;
import cs414.a5.nwalling.data.IDataSource;
import cs414.a5.nwalling.data.ModelFactory;
import cs414.a5.nwalling.exceptions.LoadException;

public class CheckoutView extends Activity implements Observer {

	private IPaymentController controller;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkout_view);
		IDataSource source = new AndroidRESTClient();
		
		try {	source.load();
		} catch (LoadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ModelFactory mf = new ModelFactory(source);
		ControllerFactory cf = new ControllerFactory(mf,source);
		controller = cf.getPaymentController();
		((Observable)controller).addObserver(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.checkout_view, menu);
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
	
	public void confirmButtonPressed(View view){
		Intent i = new Intent(CheckoutView.this, MainView.class);
		startActivity(i);
	}
	
	public void backButtonPressed(View view){
		Intent i = new Intent(CheckoutView.this, MainView.class);
		startActivity(i);
	}

	@Override
	public void update(Observable observable, Object data) {
		//Refresh your view
	}
}
