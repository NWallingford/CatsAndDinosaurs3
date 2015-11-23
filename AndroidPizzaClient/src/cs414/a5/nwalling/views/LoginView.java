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
import cs414.a5.nwalling.controllers.ILoginController;
import cs414.a5.nwalling.data.AndroidRESTClient;
import cs414.a5.nwalling.data.ControllerFactory;
import cs414.a5.nwalling.data.IDataSource;
import cs414.a5.nwalling.data.ModelFactory;
import cs414.a5.nwalling.exceptions.LoadException;
import cs414.a5.nwalling.models.IUserModel;
import cs414.a5.nwalling.models.UserModel;

public class LoginView extends Activity implements Observer {

	private ILoginController controller;
	private UserModel user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_view);
		IDataSource source = new AndroidRESTClient();
		user = (UserModel)getIntent().getSerializableExtra("user");
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
		controller.setUsername("Nick");
		controller.setPassword("cats");
		controller.login();
		//end log in logic
		
		UserModel user = (UserModel) controller.getUser();		
		Intent i = new Intent(LoginView.this, MainView.class);
		i.putExtra("user",user);
		startActivity(i);
	}
	
	public void backButtonPressed(View view){
		Intent i = new Intent(LoginView.this, MainView.class);
		startActivity(i);
	}

	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		
	}
}
