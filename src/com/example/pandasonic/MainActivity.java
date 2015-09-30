package com.example.pandasonic;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	static final int NAVIGATION_REQUEST = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	
	@Override
    protected void onResume() {
        super.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}
	
	public void selectCustomerInfo(View view){
		Intent customerInfoIntent = new Intent(this, CustomerInfoActivity.class);
        startActivity(customerInfoIntent);
	}
	
	public void mapNavigation(View view) {
		//Uri gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988");
		Uri gmmIntentUri = Uri.parse("google.navigation:q=1004+w+4th+st,+Arlington+Texas");
		
		Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
		
		mapIntent.setPackage("com.google.android.apps.maps");
		
		if(mapIntent.resolveActivity(getPackageManager()) != null) {
			System.out.println("Navigation start");
			startActivityForResult(mapIntent, NAVIGATION_REQUEST);
		}
	}
	
	public void getRoute(View view) {
		//https://maps.googleapis.com/maps/api/directions/output?parameters
		try {
			URL url = new URL("https://maps.googleapis.com/maps/api/directions/json?origin=Toronto&destination=Montreal&key=AIzaSyDYZurWyusHaKrHIe5RpQ3ooArKSJ-031M");
		
			RoutePlanTask routePlanTask = new RoutePlanTask(this);
			routePlanTask.execute(url);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == NAVIGATION_REQUEST) {
			System.out.println("Navigation Request!");
			if(resultCode == RESULT_OK)
				System.out.println("Navigation Finished!");
			else if(resultCode == RESULT_CANCELED)
				System.out.println("Navigation Canceled!");
		}
	}
}
