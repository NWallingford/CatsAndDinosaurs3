package cs414.a5.nwalling.android.views;

import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import cs414.a5.nwalling.R;
import cs414.a5.nwalling.android.controllers.IOrderController;
import cs414.a5.nwalling.android.controllers.IPaymentController;
import cs414.a5.nwalling.android.data.AndroidRESTClient;
import cs414.a5.nwalling.android.data.ControllerFactory;
import cs414.a5.nwalling.android.data.IDataSource;
import cs414.a5.nwalling.android.data.ModelFactory;
import cs414.a5.nwalling.android.exceptions.LoadException;
import cs414.a5.nwalling.android.models.UserModel;

public class CheckoutView extends Activity implements Observer {

	private IPaymentController controller;
	private UserModel user;
	
	EditText firstNameField,
	lastNameField,
	addressField,
	zipField,
	cardFirstNameField,
	cardLastNameField,
	cardNumberField,
	cardExpDateField;
	RadioButton card,cash;
	CheckBox deliver;
	LinearLayout cardLayout, addressLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkout_view);
		IDataSource source = new AndroidRESTClient();
		
		if(getIntent().hasExtra("user")){
			user = (UserModel)getIntent().getSerializableExtra("user");
		}
		
		try {	source.load();
		} catch (LoadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		firstNameField = (EditText)findViewById(R.id.firstNameField);
		lastNameField = (EditText)findViewById(R.id.lastNameField);
		addressField = (EditText)findViewById(R.id.addressField);
		zipField = (EditText)findViewById(R.id.zipField);
		cardFirstNameField = (EditText)findViewById(R.id.cardFirstNameField);
		cardLastNameField = (EditText)findViewById(R.id.cardLastNameField);
		cardNumberField = (EditText)findViewById(R.id.cardNumberField);
		cardExpDateField = (EditText)findViewById(R.id.expDateField);

		card = (RadioButton)findViewById(R.id.card);
		cash = (RadioButton)findViewById(R.id.cash);
		deliver = (CheckBox)findViewById(R.id.deliveryCheck);
		
		cardLayout = (LinearLayout)findViewById(R.id.cardLayout);
		addressLayout = (LinearLayout)findViewById(R.id.addressLayout);
		ModelFactory mf = new ModelFactory(source);
		ControllerFactory cf = new ControllerFactory(mf,source);
		if(user != null)
		{
			controller = cf.getPaymentController();
			controller.setCustomerName(user.getFirstName() + " " +  user.getLastName());
			firstNameField.setText(user.getFirstName());
			lastNameField.setText(user.getLastName());
			controller.setCustomerAddress1(user.getAddress1());
			addressField.setText(user.getAddress1());
			controller.setCustomerZip(user.getZip());
			zipField.setText(user.getZip());
		}
		firstNameField.setOnFocusChangeListener(new OnFocusChangeListener(){
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				controller.setCustomerName(firstNameField.getText().toString()+ " " + lastNameField.getText().toString());
			}
		});
		lastNameField.setOnFocusChangeListener(new OnFocusChangeListener(){

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				controller.setCustomerName(firstNameField.getText().toString() + " " + lastNameField.getText().toString());
			}
			
		});
		addressField.setOnFocusChangeListener(new OnFocusChangeListener(){

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				controller.setCustomerAddress1(addressField.getText().toString());
			}
		});
		zipField.setOnFocusChangeListener(new OnFocusChangeListener(){

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				controller.setCustomerZip(Integer.parseInt(zipField.getText().toString()));
			}
		});
		
		cardFirstNameField.setOnFocusChangeListener(new OnFocusChangeListener(){

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				controller.setCardFirstName(cardFirstNameField.getText().toString());
			}
		});
		cardLastNameField.setOnFocusChangeListener(new OnFocusChangeListener(){

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				controller.setCardLastName(cardLastNameField.getText().toString());
			}
		});
		
		cardNumberField.setOnFocusChangeListener(new OnFocusChangeListener(){

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				controller.setCardNumber(Integer.parseInt(cardNumberField.getText().toString()));
			}
		});
		
		cardExpDateField.setOnFocusChangeListener(new OnFocusChangeListener(){

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				String d = cardExpDateField.getText().toString();
				String[] dsplit = d.split("/");
				controller.setExpMonth(Integer.parseInt(dsplit[1]));
				controller.setExpYear(Integer.parseInt(dsplit[2]));
			}
		});
		
		cardFirstNameField.setOnFocusChangeListener(new OnFocusChangeListener(){

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				controller.setCardFirstName(cardFirstNameField.getText().toString());
			}
		});
		
		card.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				cardLayout.setVisibility(View.VISIBLE);
			}
			
		});
		cash.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				cardLayout.setVisibility(View.INVISIBLE);
			}
			
		});
		
		deliver.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked)
					addressLayout.setVisibility(View.VISIBLE);
				else
					addressLayout.setVisibility(View.INVISIBLE);
				
			}
			
		});
		
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
		
		controller.submit();
		Intent i = new Intent(CheckoutView.this, MainView.class);
		if(user != null) i.putExtra("user",user);
		startActivity(i);
		finish();
	}
	
	public void backButtonPressed(View view){
		Intent i = new Intent(CheckoutView.this, MainView.class);
		if(user != null) i.putExtra("user",user);
		startActivity(i);
		finish();
	}

	@Override
	public void update(Observable observable, Object data) {
		//Refresh your view
	}
}
