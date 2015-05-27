package com.Zeft.zeftproject;

import maps.GoogleMapv2;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class Vendor_info extends Activity {

	private TextView txt_view;
	private TextView txt_vendor_name;
	private TextView txt_vendor_phone;
	private TextView txt_vendor_email;
	private TextView txt_vendor_location;
	private TextView txt_vendor_location_view;
	private String B_data;
	private String data[];
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vendor_info);
		///////////////////////////////
		////INTALIZATION
		txt_view = (TextView) findViewById(R.id.txt_view_products);
		txt_vendor_name = (TextView) findViewById(R.id.txt_view_name);
		txt_vendor_phone = (TextView) findViewById(R.id.txt_view_phone);
		txt_vendor_email = (TextView) findViewById(R.id.txt_view_email);
		txt_vendor_location = (TextView) findViewById(R.id.txt_view_Location);
		txt_vendor_location_view = (TextView) findViewById(R.id.txt_view_Location_on_map);
		///////////////////////////////////
		//////TYPEFACING
		Typeface ttf = Typeface.createFromAsset(getAssets(), "abc2.ttf");

		txt_vendor_name.setTypeface(ttf);
		txt_vendor_location.setTypeface(ttf);
		txt_vendor_email.setTypeface(ttf);
		txt_vendor_phone.setTypeface(ttf);
		//////////////////////////
		///Checking Bundle
		Bundle bundle = this.getIntent().getExtras();  

		if(bundle !=null)
		{
			B_data = bundle.getString("Vendor_Data");
			data = B_data.split(","); 
			txt_vendor_name.setText(txt_vendor_name.getText().toString()+" "+data[2]);
			txt_vendor_phone.setText(txt_vendor_phone.getText().toString()+" "+data[4]);
			txt_vendor_email.setText(txt_vendor_email.getText().toString()+" "+data[3]);
			txt_vendor_location.setText(txt_vendor_location.getText().toString()+" "+data[0]+","+data[1]);
		}
		else
		{
			//Use the DB
		}
		//////////////////////////////////
		///lISTENERS
		txt_view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Ahd Sho a7ot han", Toast.LENGTH_SHORT).show();
			}
		});
		txt_vendor_location_view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) 
			{
				if(!B_data.equals(""))
				{
					Intent i = new Intent(getApplicationContext() , GoogleMapv2.class);
					Bundle b = new Bundle();
					//// 0 --> lang //// 1 ---> long ////// 2 ---> title ////////// 3 ----> info  ///// Bundle Identifer
					b.putString("Location", data[0]+","+data[1]+","+data[2]+","+data[3]+","+"view_info" );
					i.putExtras(b);
					startActivity(i);
				}
				else
				{
					///////// No Bundle	
				}
			}
		});
	}
	public void AddInfo(String txt_name , String txt_phone , String txt_email , String txt_location)
	{
		txt_vendor_name.setText(txt_vendor_name.getText()+" "+txt_name);
		txt_vendor_email.setText(txt_vendor_email.getText()+" "+txt_name);
		txt_vendor_phone.setText(txt_vendor_phone.getText()+" "+txt_name);
		txt_vendor_location.setText(txt_vendor_location.getText()+" "+txt_name);
	}
}
