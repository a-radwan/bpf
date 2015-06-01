package com.Zeft.zeftproject;

//import com.Zeft.zeftproject.ListView.Products;
import com.Zeft.zeftproject.ListView.Products;
import com.example.bdf.SQLite.SQLiteHelper;
import com.example.bdf.data.Vendor;

import maps.GoogleMapv2;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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
	private SQLiteHelper db;
	private Vendor vendor;
	private int DB_bundle_id;
	Button bAddProduct;
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
		bAddProduct=(Button)findViewById(R.id.bAddProduct);
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
			DB_bundle_id = bundle.getInt("UserID");
			if(!(DB_bundle_id+"").equals(""))
			{
				db=SQLiteHelper.getInstance(this);
				vendor = db.getVendor(DB_bundle_id);
				txt_vendor_name.setText(txt_vendor_name.getText().toString()+" "+vendor.getName());
				txt_vendor_phone.setText(txt_vendor_phone.getText().toString()+" "+vendor.getPhone());
				txt_vendor_email.setText(txt_vendor_email.getText().toString()+" "+vendor.getEmail());
				txt_vendor_location.setText(txt_vendor_location.getText().toString()+" "+vendor.getLatitude()+","+vendor.getLatitude());
			}
		}
		else
		{
			 
		}
		////////////////////////////////// 
		///lISTENERS
		txt_view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), Products.class);
				startActivity(intent);

			}
		});
		txt_vendor_location_view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) 
			{
				if(!(B_data+"").equals("") )
				{
					Intent i = new Intent(getApplicationContext() , GoogleMapv2.class);
					Bundle b = new Bundle();
					//// 0 --> lang //// 1 ---> long ////// 2 ---> title ////////// 3 ----> info  ///// Bundle Identifer
					b.putInt("VendorID", DB_bundle_id );
					i.putExtras(b);
					startActivity(i);
				}
				else
				{
					
				}
			}
		});
		bAddProduct.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), AddProduct.class);
				startActivity(intent);
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
