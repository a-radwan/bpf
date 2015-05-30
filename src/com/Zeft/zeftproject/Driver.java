package com.Zeft.zeftproject;
import com.Zeft.zeftproject.R;
import com.example.bdf.SQLite.SQLiteHelper;
import com.example.bdf.data.UserProfile;
import com.example.bdf.data.Vendor;
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
	private SQLiteHelper db;
	private EditText loginUsername; 
	private EditText loginPwd;
	private Dialog loginDialog;
	private Button btn_signup;
	private Vendor vendor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_driver);
		/////////////////////////////////////////////
		///ZINITALIZATION
		db=SQLiteHelper.getInstance(this);
		vendor = new Vendor();
		typeface = Typeface.createFromAsset(getAssets(), "abc.TTF");
		typeface2 = Typeface.createFromAsset(getAssets(), "abc2.ttf");
		db.addVendor(vendor);
		btn_log = (Button) findViewById(R.id.btn_login);
		btn_bar = (Button) findViewById(R.id.btn_search_by_barcode);
		btn_signup = (Button) findViewById(R.id.btn_signup);
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
				loginDialog = new Dialog(Driver.this);

				loginDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				loginDialog.setContentView(R.layout.login_layout);
				loginUsername = (EditText) loginDialog.findViewById(R.id.etxt_username);
				loginPwd = (EditText) loginDialog.findViewById(R.id.etxt_pwd);
				TextView txt_header = (TextView) loginDialog.findViewById(R.id.txt_Credintals);
				Button btn_login = (Button) loginDialog.findViewById(R.id.btn_login_in);
				btn_login.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Vendor vendor=db.getVendor(loginUsername.getText().toString());
						if(vendor.getId()!=0 &&vendor.getPassword().equals(loginPwd.getText().toString()))

						{
							UserProfile.login(vendor.getId(), getApplicationContext());
							startActivity(new Intent(getApplicationContext(),Vendor_info.class));
							finish();
							loginDialog.cancel();
						}
						else
						{
							Toast.makeText(getApplicationContext(), "Wrong username or password",Toast.LENGTH_SHORT).show();

						}

					} 
				});
				loginUsername.setTypeface(typeface2);
				loginPwd.setTypeface(typeface2);
				txt_header.setTypeface(typeface2);
				loginDialog.show();

			}
		});

		btn_signup.setOnClickListener(this);


	}

	@Override
	public void onClick(View v)
	{
		if(v.getId() == R.id.btn_search_by_barcode)
		{
			IntentIntegrator scanIntegrator = new IntentIntegrator(this);
			scanIntegrator.initiateScan();
		}
		if(v.getId() == R.id.btn_signup)
		{
			Dialog d = new Dialog(Driver.this);
			//////////////////////
			//ADDING VIEW
			d.requestWindowFeature(Window.FEATURE_NO_TITLE);
			d.setContentView(R.layout.sign_up);
			////////////////////////////
			///INTALIZATION
			TextView txt_header = (TextView) d.findViewById(R.id.txt_Credintals);
			final EditText etxt_name = (EditText) d.findViewById(R.id.etxt_name);
			final EditText etxt_pwd = (EditText) d.findViewById(R.id.etxt_pwd);
			final EditText etxt_pwd_conf = (EditText) d.findViewById(R.id.etxt_pwd_conf);
			final EditText etxt_email = (EditText) d.findViewById(R.id.etxt_pwd_email);
			final EditText etxt_phone = (EditText) d.findViewById(R.id.etxt_pwd_phone);
			final EditText etxt_lat = (EditText) d.findViewById(R.id.etxt_lat);
			final EditText etxt_long = (EditText) d.findViewById(R.id.etxt_longitude);
			Button btn_sign = (Button) d.findViewById(R.id.btn_login_in);
			///////////////////////////////
			////TYPEFACING
			txt_header.setTypeface(typeface2);
			etxt_name.setTypeface(typeface2);
			etxt_pwd.setTypeface(typeface2);
			etxt_pwd_conf.setTypeface(typeface2);
			etxt_email.setTypeface(typeface2);
			etxt_phone.setTypeface(typeface2);
			etxt_lat.setTypeface(typeface2);
			etxt_long.setTypeface(typeface2);
			///////////////////
			//LISTENER
			btn_sign.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {


					if(!(etxt_name.getText()+"").equals("") && !(etxt_phone.getText()+"").equals("") 
							&&!(etxt_pwd.getText()+"").equals("")&&!(etxt_pwd_conf.getText()+"").equals("") 
							&&!(etxt_email.getText()+"").equals("") &&!(etxt_lat.getText()+"").equals("")
							&&!(etxt_long.getText()+"").equals(""))
					{
						if(etxt_pwd.getText().toString().equals(etxt_pwd_conf.getText().toString()))
						{
							Intent i = new Intent(getApplicationContext() , Vendor_info.class);
							Bundle b = new Bundle();
							//// 0 --> lang //// 1 ---> long ////// 2 ---> title ////////// 3 ----> info  ///// 4 ---> phone
							Double Locations[] = {Double.parseDouble(etxt_lat.getText().toString()) , Double.parseDouble(etxt_long.getText().toString())}; 
							String title = etxt_name.getText().toString();
							String info = etxt_email.getText().toString();
							String phone = etxt_phone.getText().toString();
							//adding vendor info to the DB
							vendor.setName(title);
							vendor.setPassword(etxt_pwd.getText().toString());
							vendor.setLatitude(Locations[0]);
							vendor.setLongitude(Locations[1]);
							vendor.setEmail(info);
							vendor.setPhone(phone);
							b.putString("Vendor_Data", Locations[0]+","+Locations[1]+","+title+","+info+","+phone );
							i.putExtras(b);
							startActivity(i);
						}
						else
						{
							Toast.makeText(getApplicationContext(), "PassWord Dos'nt Match!", Toast.LENGTH_SHORT).show();
						}
					}
					else{
						Toast.makeText(getApplicationContext(), "Some Feilds Are Empty", Toast.LENGTH_SHORT).show();
					}

				}
			});
			//////////////////
			///STARTING VIEW
			d.show();
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
		else
		{
			Toast toast = Toast.makeText(getApplicationContext(), 
					"No scan data received!", Toast.LENGTH_SHORT);
			toast.show();
		}
	}

}