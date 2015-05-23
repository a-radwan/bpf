package com.Zeft.zeftproject;
import com.Zeft.zeftproject.R;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_driver);
		/////////////////////////////////////////////
		///ZINITALIZATION
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
				Dialog d = new Dialog(Driver.this);
				d.requestWindowFeature(Window.FEATURE_NO_TITLE);
				d.setContentView(R.layout.login_layout);
				EditText username = (EditText) d.findViewById(R.id.etxt_username);
				EditText pwd = (EditText) d.findViewById(R.id.etxt_pwd);
				TextView txt_header = (TextView) d.findViewById(R.id.txt_Credintals);
				Button btn_trie = (Button) d.findViewById(R.id.btn_login_in);
				username.setTypeface(typeface2);
				pwd.setTypeface(typeface2);
				txt_header.setTypeface(typeface2);
				d.show();
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