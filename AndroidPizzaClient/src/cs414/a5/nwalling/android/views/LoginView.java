package cs414.a5.nwalling.android.views;

import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import cs414.a5.nwalling.R;
import cs414.a5.nwalling.android.controllers.ILoginController;
import cs414.a5.nwalling.android.data.AndroidRESTClient;
import cs414.a5.nwalling.android.data.ControllerFactory;
import cs414.a5.nwalling.android.data.IDataSource;
import cs414.a5.nwalling.android.data.ModelFactory;
import cs414.a5.nwalling.android.exceptions.LoadException;
import cs414.a5.nwalling.android.models.UserModel;

public class LoginView extends Activity implements Observer {

	private ILoginController controller;
	EditText username, password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_view);
		IDataSource source = new AndroidRESTClient();
		username =(EditText)findViewById(R.id.editText);
		password =(EditText)findViewById(R.id.address);
		try {	source.load();
		} catch (LoadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ModelFactory mf = new ModelFactory(source);
		ControllerFactory cf = new ControllerFactory(mf,source);
		controller = cf.getLoginController();
		((Observable)controller).addObserver(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_view, menu);
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
	
	public void submitButtonPressed(View view){
		//log in logic
		controller.setUsername(username.getText().toString());
		controller.setPassword(password.getText().toString());
		controller.login();
		//end log in logic
	}
	
	public void backButtonPressed(View view){
		Intent i = new Intent(LoginView.this, MainView.class);
		startActivity(i);
		finish();
	}

	@Override
	public void update(Observable observable, Object data) {
		UserModel user = (UserModel) controller.getUser();		
		Intent i = new Intent(LoginView.this, MainView.class);
		if(user != null) i.putExtra("user",user);
		startActivity(i);
		finish();
	}
}
