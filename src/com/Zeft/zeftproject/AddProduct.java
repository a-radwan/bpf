package com.Zeft.zeftproject;

import com.example.bdf.SQLite.SQLiteHelper;
import com.example.bdf.data.Product;
import com.example.bdf.data.UserProfile;
import com.example.bdf.data.VendorHasProduct;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddProduct extends Activity {

	EditText etName;
	EditText etBarcode;
	EditText etPrice;
	Spinner spCategory;
	Button bAdd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_product);
		etBarcode=(EditText)findViewById(R.id.etBarcode);
		etName=(EditText)findViewById(R.id.etName);
		etPrice=(EditText)findViewById(R.id.etPrice);
		spCategory=(Spinner)findViewById(R.id.sCategory);
		bAdd=(Button)findViewById(R.id.bOk);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
				R.array.planets_array, R.layout.spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spCategory.setAdapter(adapter);
		bAdd.setOnClickListener(new OnClickListener() {
			SQLiteHelper db=SQLiteHelper.getInstance(getApplicationContext());
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Product product=new Product();
				product.setBarcode(etBarcode.getText().toString());
				product.setName(etName.getText().toString());
				product.setCategoryId((String) spCategory.getSelectedItem());
				double price=Double.parseDouble(etPrice.getText().toString());
				db.addVendorHasProduct(UserProfile.getCurrntUser(), product,price);
		
				Toast.makeText(getApplicationContext(),"Added Successfuly",Toast.LENGTH_SHORT).show();;
			finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.add_product, menu);
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
}
