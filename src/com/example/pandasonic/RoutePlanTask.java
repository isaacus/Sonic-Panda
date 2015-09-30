package com.example.pandasonic;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

public class RoutePlanTask extends AsyncTask<URL, Void, String> {
	
	Activity activity = null;
	String[] destList = null;
	
	public RoutePlanTask(Activity activity) {
		this.activity = activity;
		destList = new String[]{"A","B","C","D","A"};
	}
	
	@Override
	protected String doInBackground(URL... urls) {
		
		//https://maps.googleapis.com/maps/api/directions/output?parameters
				
		HttpURLConnection urlConnection;
		try {
			System.out.println("start route task: " + urls[0].toString());
			
			urlConnection = (HttpURLConnection) urls[0].openConnection();			

			InputStream in = new BufferedInputStream(urlConnection.getInputStream());
			
			Scanner scanner  = new Scanner(in,"UTF-8").useDelimiter("\\A");
			
			String result = scanner.hasNext() ? scanner.next() : "";
			
			System.out.println(result);
			
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject)parser.parse(result);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return null;
	}
	
	protected void onPostExecute(String result) {
		Intent listActivity = new Intent(activity, RouteListActivity.class);
		listActivity.putExtra("destinations", destList);
        activity.startActivity(listActivity);
	}
}