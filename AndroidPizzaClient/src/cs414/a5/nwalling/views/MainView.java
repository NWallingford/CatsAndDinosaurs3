package cs414.a5.nwalling.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import cs414.a5.nwalling.R;

public class MainView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_view);
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
		startActivity(i);
	}
	
	public void makeOrderButtonPressed(View view){
		Intent i = new Intent(MainView.this, MakeOrderView.class);
		startActivity(i);
	}
	
	public void loginButtonPressed(View view){
		Intent i = new Intent(MainView.this, LoginView.class);
		startActivity(i);
	}
	
	public void createAccountButtonPressed(View view){
		Intent i = new Intent(MainView.this, CreateAccountView.class);
		startActivity(i);
	}
	
	public void updateMenuButtonPressed(View view){
		Intent i = new Intent(MainView.this, UpdateMenuView.class);
		startActivity(i);
	}
	
	public void viewMenuButtonPressed(View view){
		Intent i = new Intent(MainView.this, MenuView.class);
		startActivity(i);
	}
}
