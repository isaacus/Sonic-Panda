package com.example.pandasonic;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class RouteListActivity extends ListActivity {
	String[] routeList;
	
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		routeList = getIntent().getStringArrayExtra("destinations");
        
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.rowlayout, R.id.label, routeList);
		setListAdapter(adapter);
	}
	
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String item = (String) getListAdapter().getItem(position);
		Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
		
		//Intent intent = new Intent(Intent.ACTION_VIEW);
    	//intent.setData(Uri.parse("market://details?id=" + appsIdList[position]));//com.facebook.katana"));//com.klink"));
    	//startActivity(intent);
	}

}
