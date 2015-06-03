package com.Zeft.zeftproject.ListView;
import java.util.Date;
import java.util.LinkedList;

import maps.GoogleMapv2;

import com.Zeft.zeftproject.R;
import com.example.bdf.SQLite.SQLiteHelper;
import com.example.bdf.data.UserProfile;
import com.example.bdf.data.Vendor;
import com.example.bdf.data.VendorHasProduct;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.R.integer;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class SearchProducts extends Activity implements OnClickListener{
	ListView lvProducts;
	Vendor vendor;
	SQLiteHelper db;
	LinkedList<VendorHasProduct> vendorProducts;
	LinkedList<VendorHasProduct>threeVB=new LinkedList<VendorHasProduct>();
	private String ids;
	VendorHasProduct min;
	private String barcode;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_products);
		/////////////////////////////////////////////
		///INITALIZATION
		 barcode = (String) getIntent().getSerializableExtra("barcode");
		db = SQLiteHelper.getInstance(getApplicationContext());
		ids = "";
		lvProducts = (ListView) findViewById(R.id.lvProducts);
		vendorProducts = db.getAllVendorsAndProducts(db.getProduct(barcode));
		
		
		if(vendorProducts.size()>0){
		 min=vendorProducts.get(0);
		for(int i=0;i<vendorProducts.size();i++)
		{
			if(vendorProducts.get(i).getPrice()<min.getPrice())
				min=vendorProducts.get(i);
			    if(ids.equals(""))
			    {
			    	ids += ""+min.getId();
			    }
			    else
			    {
			      ids += ids+","+min.getId();	
			    }
		}
		threeVB.add(min);
		vendorProducts.remove(min);
		}
		if(vendorProducts.size()>0){

		min=vendorProducts.get(0);
		for(int i=0;i<vendorProducts.size();i++)
		{
			if(vendorProducts.get(i).getPrice()<min.getPrice())
				min=vendorProducts.get(i);
		}
		threeVB.add(min);
		vendorProducts.remove(min);

		}
		if(vendorProducts.size()>0){
			 min=vendorProducts.get(0);

		for(int i=0;i<vendorProducts.size();i++)
		{
			if(vendorProducts.get(i).getPrice()<min.getPrice())
				min=vendorProducts.get(i);
		}
		threeVB.add(min);
		vendorProducts.remove(min);
		}
//		vendor = UserProfile.getCurrntUser();
		if(threeVB.size()==0)
		{
			this.finish();
			Toast.makeText(getApplicationContext(), "There is no such products", Toast.LENGTH_LONG).show();
		}
			SearchProductAdapter productAdapter = new SearchProductAdapter(getApplicationContext(), threeVB);
		
	
		
		lvProducts.setAdapter(productAdapter);
		lvProducts.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(getApplicationContext(),GoogleMapv2.class);
				Bundle b = new Bundle();
				String Mark = "";
				Vendor ven = new Vendor();
				for(int x = 0 ;x <threeVB.size() ; x++ )
				{
					if(Mark.equals(""))
					{
						ven = db.getVendor(threeVB.get(x).getVendorId());
						Mark += ven.getLatitude()+","
								+ven.getLongitude()+","
								+ven.getName()+","
								+threeVB.get(x).getPrice();
					}
					else
					{
						ven = db.getVendor(threeVB.get(x).getVendorId());
						Mark += ";"+ven.getLatitude()+","
								+ven.getLongitude()+","
								+ven.getName()+","
								+threeVB.get(x).getPrice();	
					}
				}
				b.putString("MarkThem", Mark);
				i.putExtras(b);
				startActivity(i);
				finish();
			}
		});
		registerForContextMenu(lvProducts);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
    public LinkedList<VendorHasProduct> getVendors()
    {
     return threeVB; 	
    }
}
