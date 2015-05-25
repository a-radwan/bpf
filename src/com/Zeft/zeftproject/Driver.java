package com.Zeft.zeftproject;
import com.Zeft.zeftproject.R;
import com.example.bdf.SQLite.SQLiteHelper;
import com.example.bdf.data.UserProfile;
import com.example.bdf.data.Vendor;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.*;

public class Driver extends Activity implements OnClickListener{
	private Button btn_log;
	private Button btn_bar;
	private Typeface typeface;
	private TextView txt_welcome;
	private Spinner spinner;
	private Typeface typeface2;
	private SQLiteHelper db;
	EditText loginUsername; 
	EditText loginPwd;
	Dialog loginDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_driver);
		/////////////////////////////////////////////
		///ZINITALIZATION
		db=SQLiteHelper.getInstance(this);
		Vendor vendor =new Vendor();
		vendor.setLatitude(32.258);
		vendor.setLongitude(12.12565);
		vendor.setName("a123");
		vendor.setPassword("a123");
		db.addVendor(vendor);

		typeface = Typeface.createFromAsset(getAssets(), "abc.TTF");
		typeface2 = Typeface.createFromAsset(getAssets(), "abc2.ttf");
		btn_log = (Button) findViewById(R.id.btn_login);
		btn_bar = (Button) findViewById(R.id.btn_search_by_barcode);
		txt_welcome = (TextView)  findViewById(R.id.txt_welcome);
		spinner = (Spinner) findViewById(R.id.cat_spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
				R.array.planets_array, R.layout.spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		/////////////////////////////////////////  
		///TYPEFACE
		btn_bar.setTypeface(typeface);
		txt_welcome.setTypeface(typeface2);
		////////////////////////////////////////
		///LISTENERS
		btn_bar.setOnClickListener(this);
		btn_log.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				 loginDialog = new Dialog(Driver.this);
			
				loginDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				loginDialog.setContentView(R.layout.login_layout);
				loginUsername = (EditText) loginDialog.findViewById(R.id.etxt_username);
				loginPwd = (EditText) loginDialog.findViewById(R.id.etxt_pwd);
				TextView txt_header = (TextView) loginDialog.findViewById(R.id.txt_Credintals);
				Button btn_login = (Button) loginDialog.findViewById(R.id.btn_login_in);
				btn_login.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Vendor vendor=db.getVendor(loginUsername.getText().toString());
						if(vendor.getId()!=0 &&vendor.getPassword().equals(loginPwd.getText().toString()))
						
						{
							UserProfile.login(vendor.getId(), getApplicationContext());
							Toast.makeText(getApplicationContext(), "welcome "+vendor.getName(),Toast.LENGTH_SHORT).show();						
							loginDialog.cancel();
						}
						else
						{
							Toast.makeText(getApplicationContext(), "Wrong username or password",Toast.LENGTH_SHORT).show();

						}
					
				} 
					});
				loginUsername.setTypeface(typeface2);
				loginPwd.setTypeface(typeface2);
				txt_header.setTypeface(typeface2);
				loginDialog.show();

			}
		});




	}

	@Override
	public void onClick(View v)
	{
		if(v.getId() == R.id.btn_search_by_barcode)
		{
			IntentIntegrator scanIntegrator = new IntentIntegrator(this);
			scanIntegrator.initiateScan();
		}

	}
	public void onActivityResult(int requestCode, int resultCode, Intent intent) 
	{
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		if (scanningResult != null)
		{
			String scanContent = scanningResult.getContents();
			String scanFormat = scanningResult.getFormatName();
			Toast.makeText(getApplicationContext(), "Formate BarCode :"+scanFormat+" Content "+scanContent, Toast.LENGTH_LONG).show();
		}
		else{
			Toast toast = Toast.makeText(getApplicationContext(), 
					"No scan data received!", Toast.LENGTH_SHORT);
			toast.show();
		}
	}

}