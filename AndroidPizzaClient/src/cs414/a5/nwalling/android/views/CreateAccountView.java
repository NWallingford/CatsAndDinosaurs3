package cs414.a5.nwalling.android.views;

import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import cs414.a5.nwalling.R;
import cs414.a5.nwalling.android.controllers.ICreateAccountController;
import cs414.a5.nwalling.android.data.AndroidRESTClient;
import cs414.a5.nwalling.android.data.ControllerFactory;
import cs414.a5.nwalling.android.data.IDataSource;
import cs414.a5.nwalling.android.data.ModelFactory;
import cs414.a5.nwalling.android.exceptions.LoadException;

public class CreateAccountView extends Activity implements Observer {

	EditText firstNameField, lastNameField, usernameField, passwordField, emailField, addressField, zipField;
	TextView firstNameError, lastNameError, usernameError, passwordError, emailError, addressError, zipError;
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
		firstNameError = (TextView)findViewById(R.id.firstNameError);
		firstNameField =(EditText)findViewById(R.id.firstNameField);
		firstNameField.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus && !controller.setFirstName(firstNameField.getText().toString()))
					firstNameError.setText("First Name Required.");
				else firstNameError.setText("");
			}
		}
		);
		lastNameError = (TextView)findViewById(R.id.lastNameError);
		lastNameField =(EditText)findViewById(R.id.lastNameField);
		lastNameField.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus && !controller.setLastName(lastNameField.getText().toString()))
					lastNameError.setText("Last Name Required.");
				else lastNameError.setText("");
			}
		});
		usernameError = (TextView)findViewById(R.id.usernameError);
		usernameField =(EditText)findViewById(R.id.usernameField);
		usernameField.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus && !controller.setUsername(usernameField.getText().toString()))
					usernameError.setText("Username Required.");
				else usernameError.setText("");
			}
		});
		passwordError = (TextView)findViewById(R.id.passwordError);
		passwordField =(EditText)findViewById(R.id.passwordField);
		passwordField.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus && !hasFocus && !controller.setPassword(passwordField.getText().toString()))
					passwordError.setText("Password Required.");
				else passwordError.setText("");
			}
		});
		emailError = (TextView)findViewById(R.id.emailError);
		emailField =(EditText)findViewById(R.id.emailField);
		emailField.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus && !controller.setEmailAddress(emailField.getText().toString()))
					emailError.setText("Email Address Required.");
				else emailError.setText("");
			}
		});
		addressError = (TextView)findViewById(R.id.addressError);
		addressField =(EditText)findViewById(R.id.addressField);
		addressField.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus && !controller.setAddress1(addressField.getText().toString()))
					addressError.setText("Address Required.");
				else addressError.setText("");
			}
		});
		zipError = (TextView)findViewById(R.id.zipCodeError);
		zipField =(EditText)findViewById(R.id.zipField);
		zipField.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus && !controller.setZip(zipField.getText().toString()))
					zipError.setText("Invalid Zip Code: Zip Required and must be an integer value.");
				else zipError.setText("");
			}
		});
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
		finish();
	}
	
	public void submitButtonPressed(View view){
		
		controller.createUser();
		Intent i = new Intent(CreateAccountView.this, MainView.class);
		startActivity(i);
		finish();
	}
	
	@Override
	public void update(Observable observable, Object data) {
			if(controller.usernameExists())
				usernameError.setText("Username already exists.");
	}
}
