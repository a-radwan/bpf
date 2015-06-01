package com.Zeft.zeftproject.ListView;
import java.util.Date;
import java.util.LinkedList;

import com.Zeft.zeftproject.R;
import com.example.bdf.SQLite.SQLiteHelper;
import com.example.bdf.data.Product;
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

public class Products extends Activity implements OnClickListener{
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

		//int vendorId = Integer.parseInt((String) getIntent().getSerializableExtra("vendorId"));

		db = SQLiteHelper.getInstance(getApplicationContext());

		lvProducts = (ListView) findViewById(R.id.lvProducts);

		vendor = UserProfile.getCurrntUser();
		vendorProducts=db.getAllVendorsAndProducts(vendor);
		ProductAdapter productAdapter = new ProductAdapter(getApplicationContext(), vendorProducts);

		lvProducts.setAdapter(productAdapter);
		lvProducts.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(), "mdnmcdgabadb", Toast.LENGTH_SHORT)
				.show();
			}
		});
		registerForContextMenu(lvProducts);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}