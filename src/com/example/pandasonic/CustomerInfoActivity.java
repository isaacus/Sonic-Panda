package com.example.pandasonic;

import java.util.ArrayList;

import com.example.pandasonic.dummy.DummyContent;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class CustomerInfoActivity extends Activity {

	private PandaSonicContract pandaSonicContract = null;
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customer_info);
		
		pandaSonicContract = new PandaSonicContract(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.customer_info, menu);
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
	
	public void saveCustomerInfo(View view){
		String name = ((EditText)findViewById(R.id.editCustomerName)).getText().toString();
		String phone = ((EditText)findViewById(R.id.editCustomerPhone)).getText().toString();
		String address = ((EditText)findViewById(R.id.editCustomerAddress)).getText().toString();
		String apt = ((EditText)findViewById(R.id.editCustomerApt)).getText().toString();
		
		String[] columns = {
				PandaSonicContract.CustomerInfo.COLUMN_NAME_PERSON_NAME, 
				PandaSonicContract.CustomerInfo.COLUMN_NAME_PHONE,
				PandaSonicContract.CustomerInfo.COLUMN_NAME_ADDRESS,
				PandaSonicContract.CustomerInfo.COLUMN_NAME_APT
		};
		
		String[] columnsValue = {name, phone, address, apt};
		
		long result = pandaSonicContract.saveCustomerInfo(columns, columnsValue);
		
		System.out.println(result);
	}
	
	public void queryCustomerInfo(View view){
		String name = ((EditText)findViewById(R.id.editCustomerName)).getText().toString();
		String phone = ((EditText)findViewById(R.id.editCustomerPhone)).getText().toString();
		String address = ((EditText)findViewById(R.id.editCustomerAddress)).getText().toString();
		String apt = ((EditText)findViewById(R.id.editCustomerApt)).getText().toString();
		
		String[] columns = {
				PandaSonicContract.CustomerInfo.COLUMN_NAME_PERSON_NAME, 
				PandaSonicContract.CustomerInfo.COLUMN_NAME_PHONE,
				PandaSonicContract.CustomerInfo.COLUMN_NAME_ADDRESS,
				PandaSonicContract.CustomerInfo.COLUMN_NAME_APT
		};
		
		String[] columnsValue = {name, phone, address, apt};
		
		queryContent.columnNames = columns;
		queryContent.columnValues = columnsValue;
		queryContent.sortOrder = PandaSonicContract.CustomerInfo.COLUMN_NAME_PHONE;
		queryContent.pandaSonicContract = pandaSonicContract;
		Cursor result = queryContent.query();
		//Cursor result = pandaSonicContract.queryCustomerInfo(columns, columnsValue, PandaSonicContract.CustomerInfo.COLUMN_NAME_PHONE);
		
		if(!result.moveToFirst()){
			System.out.println("No record!");
			return;
		}	
		
		queryContent.result = result;
		queryContent.fromColumns = new String[]{
				PandaSonicContract.CustomerInfo.COLUMN_NAME_PHONE
		};
		
		queryContent.toViews = new int[]{android.R.id.text1};
		
		//SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_activated_1, result, fromColumns, toViews, 0);
		//ListView listView = getListView();
		//listView.setAdapter(adapter);
		//requery
		
		//adapter.changeCursor();
		
		ArrayList<InfoItem> customerInfoList = new ArrayList<InfoItem>();
		
		do{
			String id = result.getString(result.getColumnIndexOrThrow(PandaSonicContract.CustomerInfo._ID));
			String eachName = result.getString(result.getColumnIndexOrThrow(PandaSonicContract.CustomerInfo.COLUMN_NAME_PERSON_NAME));
			String eachPhone = result.getString(result.getColumnIndexOrThrow(PandaSonicContract.CustomerInfo.COLUMN_NAME_PHONE));
			String eachAddress = result.getString(result.getColumnIndexOrThrow(PandaSonicContract.CustomerInfo.COLUMN_NAME_ADDRESS));
			String eachApt = result.getString(result.getColumnIndexOrThrow(PandaSonicContract.CustomerInfo.COLUMN_NAME_APT));
			
			CustomerInfoItem customer = new CustomerInfoItem(id, eachName, eachPhone, eachAddress, eachApt);
			
			customerInfoList.add(customer);
			
			System.out.println(id + " " + eachName + " " + eachPhone + " " + eachAddress + " " + eachApt);
	
		}while(result.moveToNext());
		
		if(customerInfoList.size() > 0){
			queryContent.removeItems();
			queryContent.addItems(customerInfoList);
		}
		
		Intent queryListIntent = new Intent(this, CustomerListActivity.class);
		startActivity(queryListIntent);
	}
}
