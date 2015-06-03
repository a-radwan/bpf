package com.Zeft.zeftproject.ListView;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.Zeft.zeftproject.R;
import com.Zeft.zeftproject.Vendor_info;
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
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class Products extends Activity implements OnClickListener{
	ListView lvProducts;
	Vendor vendor;
	SQLiteHelper db;
	private int infoPosition;

	LinkedList<VendorHasProduct> vendorProducts;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_products);
		/////////////////////////////////////////////
		///INITALIZATION
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
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
		infoPosition = info.position;
		menu.setHeaderTitle("Product Options");


		getMenuInflater().inflate(R.menu.driver,menu);


	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		if ((item.getTitle().toString())
				.equals("delete")) {
			deleteExp(vendorProducts.get(infoPosition));

			Toast.makeText(getApplicationContext(), "deleted", Toast.LENGTH_SHORT).show();
		}
		else {
			Toast.makeText(getApplicationContext(), item.getTitle() + " wrong",
					Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	private void deleteExp(VendorHasProduct vb) {
		db.deleteVendorHasProduct(vb);
		this.onResume();

	}

	@Override
	protected void onResume() {
		super.onResume();
		lvProducts.setAdapter(null);
		vendor = UserProfile.getCurrntUser();
		vendorProducts=db.getAllVendorsAndProducts(vendor);
		ProductAdapter productAdapter = new ProductAdapter(getApplicationContext(), vendorProducts);
		lvProducts.setAdapter(productAdapter);
		registerForContextMenu(lvProducts);
	}
	@Override
	public void onBackPressed() {
		Intent i = new Intent(getApplicationContext(),Vendor_info.class);
		Bundle b = new Bundle();
		b.putInt("UserID", UserProfile.getCurrntUser().getId());
		i.putExtras(b);
		startActivity(i);
		finish();
		super.onBackPressed();
	}
}