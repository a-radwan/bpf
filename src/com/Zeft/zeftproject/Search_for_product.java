package com.Zeft.zeftproject;

import com.example.bdf.SQLite.SQLiteHelper;

import android.R.color;
import android.app.Activity;


import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Search_for_product extends Activity  {

	private TextView txt_header;
	private EditText etxt_pname;
	private EditText etxt_barcode;
	private EditText etxt_price;
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
		etxt_pname = (EditText) findViewById(R.id.etxt_prodctname);
		etxt_barcode = (EditText) findViewById(R.id.etxt_barcode);
		etxt_price = (EditText) findViewById(R.id.etxt_price);
		btn_search = (Button) findViewById(R.id.btn_search_for_products);
		db = SQLiteHelper.getInstance(this);	
		///////////////////////////////
		///////TYPEFACING/////////////
		txt_header.setTypeface(ttf);
		etxt_pname.setTypeface(ttf);
		etxt_barcode.setTypeface(ttf);
		etxt_price.setTypeface(ttf);
		//////////////////////////////
		//LISTENERS///////////////////
		btn_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
			 
			}
		});
	}


}
