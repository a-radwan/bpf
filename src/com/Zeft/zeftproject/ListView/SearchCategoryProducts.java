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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class SearchCategoryProducts extends Activity implements OnClickListener{
	ListView lvProducts;
	Vendor vendor;
	SQLiteHelper db;
	LinkedList<VendorHasProduct> vendorProducts;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_products);
		/////////////////////////////////////////////
		///ZINITALIZATION
		String category = (String) getIntent().getSerializableExtra("category");

		db = SQLiteHelper.getInstance(getApplicationContext());

		lvProducts = (ListView) findViewById(R.id.lvProducts);
		vendorProducts = db.getAllVendorsAndProducts();
		LinkedList<VendorHasProduct> vp=new LinkedList<VendorHasProduct>();
		for(int i=0;i<vendorProducts.size();i++)
		{
			if((db.getProduct(vendorProducts.get(i).getProductBarcode())).getCategoryId().equals(category))
				vp.add(vendorProducts.get(i));
		}
		//		vendor = UserProfile.getCurrntUser();
		SearchCategoryProductAdapter productAdapter = new SearchCategoryProductAdapter(getApplicationContext(), vp);


		lvProducts.setAdapter(productAdapter);
		lvProducts.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(getApplicationContext(),GoogleMapv2.class);
				Bundle b = new Bundle();
				String Mark = "";
				Vendor ven = new Vendor();
				ven = db.getVendor(vendorProducts.get(position).getVendorId());
				Mark += ven.getLatitude()+","
						+ven.getLongitude()+","
						+ven.getName()+","
						+vendorProducts.get(position).getPrice();
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

	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(MotionEvent.ACTION_DOWN == event.getActionMasked())
		{
			startActivity(new Intent(getApplicationContext(),GoogleMapv2.class));
		}
		return super.onTouchEvent(event);
	}
}