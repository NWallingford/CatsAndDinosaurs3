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
import android.widget.Button;
import android.widget.TextView;
import cs414.a5.nwalling.R;
import cs414.a5.nwalling.android.controllers.IMenuController;
import cs414.a5.nwalling.android.data.AndroidRESTClient;
import cs414.a5.nwalling.android.data.ControllerFactory;
import cs414.a5.nwalling.android.data.IDataSource;
import cs414.a5.nwalling.android.data.ModelFactory;
import cs414.a5.nwalling.android.exceptions.LoadException;
import cs414.a5.nwalling.android.models.UserModel;

public class MainView extends Activity implements Observer {
	
	IMenuController controller;
	ArrayList<String> menu;
	UserModel user;
	
	TextView username;
	Button chefViewButton;
	Button changeMenuButton;
	Button loginButton;
	Button createAccountButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_view);
		IDataSource source;
		username =(TextView)findViewById(R.id.username);
		chefViewButton =(Button)findViewById(R.id.chefViewButton);
		changeMenuButton =(Button)findViewById(R.id.updateMenuButton);
		loginButton = (Button)findViewById(R.id.loginButton);
		createAccountButton = (Button)findViewById(R.id.createAccountButton);
		if(getIntent().hasExtra("user")){
			user = (UserModel)getIntent().getSerializableExtra("user");
		}
		else{
			UserModel guest = new UserModel();
			guest.setUsername("Guest");
			guest.setAuthLevel(5);
			user = guest;
		}
		username.setText("Hello, "+user.getUsername()+"!");	
		applyAuthorization(user);
		source = new AndroidRESTClient();
		
		try {	source.load();
		} catch (LoadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ModelFactory mf = new ModelFactory(source);
		ControllerFactory cf = new ControllerFactory(mf,source);
//		}
//		else
//		{
//			Bundle settings = getIntent().getBundleExtra("settings");
//			source = settings.get
//		}
		controller = cf.getMenuController();		
		((Observable)controller).addObserver(this);
		controller.fetchMenu();		
	}

	public void applyAuthorization(UserModel user){
			changeMenuButton.setEnabled(false);
			chefViewButton.setEnabled(false);
		switch(user.getAuthLevel()){
		case 1:
		case 2:
			changeMenuButton.setVisibility(View.VISIBLE);
			changeMenuButton.setClickable(true);
			changeMenuButton.setEnabled(true);
		case 3:
			chefViewButton.setVisibility(View.VISIBLE);
			chefViewButton.setClickable(true);
			chefViewButton.setEnabled(true);
		case 4:
			loginButton.setText("Log Out");
			createAccountButton.setText("Edit Account");
		default:		
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_view, menu);
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
	
	public void chefViewButtonPressed(View view){
		Intent i = new Intent(MainView.this, ChefView.class);
		if(user != null) i.putExtra("user",user);
		startActivity(i);
		finish();
	}
	
	public void makeOrderButtonPressed(View view){
		Intent i = new Intent(MainView.this, MakeOrderView.class);
		if(user != null) i.putExtra("user",user);
		startActivity(i);
		finish();
	}
	
	public void loginButtonPressed(View view){
		if(user.getUsername().equals("Guest")){	// if you're a guest, go to log in
			Intent i = new Intent(MainView.this, LoginView.class);
			if(user != null) i.putExtra("user",user);
			startActivity(i);
			finish();
		}
		else{	// if you're anybody else, log out (go back to main view without passing user with the intent)
			Intent i = new Intent(MainView.this, MainView.class);
			startActivity(i);
			finish();
		}
		
	}
	
	public void createAccountButtonPressed(View view){
		Intent i = new Intent(MainView.this, CreateAccountView.class);
		if(user != null) i.putExtra("user",user);
		startActivity(i);
		finish();
	}
	
	public void updateMenuButtonPressed(View view){
		Intent i = new Intent(MainView.this, UpdateMenuView.class);
		if(user != null) i.putExtra("user",user);
		startActivity(i);
		finish();
	}
	
	public void viewMenuButtonPressed(View view){
		Intent i = new Intent(MainView.this, MenuView.class);
		if(user != null) i.putExtra("user",user);
		startActivity(i);
		finish();
	}

	@Override
	public void update(Observable observable, Object data) {
		menu = controller.getMenu();
		TextView textField = new TextView(this);
		textField=(TextView)findViewById(R.id.menu);
		String menuStr = "Menu:\n";
		for(String m:menu){
			menuStr = menuStr + m + '\n';
		}
	    textField.setText(menuStr);
	}
	
}
