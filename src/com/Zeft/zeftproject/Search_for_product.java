package com.Zeft.zeftproject;

import java.util.LinkedList;

import com.example.bdf.SQLite.SQLiteHelper;
import com.example.bdf.data.VendorHasProduct;

import android.R.color;
import android.app.Activity;


import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Search_for_product extends Activity  {

	private TextView txt_header;
	private EditText etxt_barcode;
	private Button btn_search;
	private SQLiteHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_for_product);
		///////////////////////////////////
		///INTALIZATION
		Typeface ttf = Typeface.createFromAsset(getAssets(), "abc2.ttf");
		txt_header = (TextView) findViewById(R.id.txt_header_pro);
		etxt_barcode = (EditText) findViewById(R.id.etxt_barcode);
		btn_search = (Button) findViewById(R.id.btn_search_for_products);
		db = SQLiteHelper.getInstance(this);	
		///////////////////////////////
		///////TYPEFACING/////////////
		txt_header.setTypeface(ttf);
		etxt_barcode.setTypeface(ttf);
		//////////////////////////////
		//LISTENERS///////////////////
		btn_search.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v)
			{
			 if(!etxt_barcode.getText().toString().equals(""))
			 {
			    LinkedList<VendorHasProduct> vhas = db.getAllVendorsAndProducts();
			    VendorHasProduct hasing = new VendorHasProduct();
			    if((etxt_barcode.getText().toString().subSequence(0, 3)+"").equalsIgnoreCase("729"))
			    {
			    	Dialog d = new Dialog(Search_for_product.this);
			    	d.requestWindowFeature(Window.FEATURE_NO_TITLE);
			    	d.setContentView(R.layout.alerting_il);
			    	d.show();
			    }
			    else{
			    if(vhas.size() == 0)
		    	{
		    		Toast.makeText(getApplicationContext(), "Empty DB!" , Toast.LENGTH_SHORT).show();
		    	}
			    else
			    {
			    	String found = "not";
			    for(int i=0; i < vhas.size() ; i ++ )
			    {
			    	hasing = vhas.get(i);
			    	if((hasing.getProductBarcode()+"").equals(etxt_barcode.getText().toString()) )
			    	{
			    		found = "yes";
			    		Toast.makeText(getApplicationContext(), "Found IT", Toast.LENGTH_SHORT).show();
			    	}
			    	
			    	
			    }
			    if(found.equalsIgnoreCase("not"))
			    {
			    	Toast.makeText(getApplicationContext(), "DID'nt Found IT", Toast.LENGTH_SHORT).show();	
			    }
			    }
			 }
			 }
			 else
			 {
				 Toast.makeText(getApplicationContext(), "Empty Feild BarCode" , Toast.LENGTH_SHORT).show();
			 }
			}
		});
	}

}
