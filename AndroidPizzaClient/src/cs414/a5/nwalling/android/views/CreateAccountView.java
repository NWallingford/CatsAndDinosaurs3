package cs414.a5.nwalling.android.views;

import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import cs414.a5.nwalling.R;
import cs414.a5.nwalling.android.controllers.ICreateAccountController;
import cs414.a5.nwalling.android.data.AndroidRESTClient;
import cs414.a5.nwalling.android.data.ControllerFactory;
import cs414.a5.nwalling.android.data.IDataSource;
import cs414.a5.nwalling.android.data.ModelFactory;
import cs414.a5.nwalling.android.exceptions.LoadException;

public class CreateAccountView extends Activity implements Observer {

	TextView firstNameField, lastNameField, usernameField, passwordField, emailField, addressField, zipField;
	
	private ICreateAccountController controller;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account_view);
		
		IDataSource source = new AndroidRESTClient();
		
		try {	source.load();
		} catch (LoadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		firstNameField =(TextView)findViewById(R.id.firstNameField);
		lastNameField =(TextView)findViewById(R.id.lastNameField);
		usernameField =(TextView)findViewById(R.id.usernameField);
		passwordField =(TextView)findViewById(R.id.passwordField);
		emailField =(TextView)findViewById(R.id.emailField);
		addressField =(TextView)findViewById(R.id.addressField);
		zipField =(TextView)findViewById(R.id.zipField);
		ModelFactory mf = new ModelFactory(source);
		ControllerFactory cf = new ControllerFactory(mf,source);
		controller = cf.getCreateAccountController();
		((Observable)controller).addObserver(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_account_view, menu);
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
		Intent i = new Intent(CreateAccountView.this, MainView.class);
		startActivity(i);
	}
	
	public void submitButtonPressed(View view){
		Intent i = new Intent(CreateAccountView.this, MainView.class);
		startActivity(i);
	}
	
	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		
	}
}
