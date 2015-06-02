package maps;

import com.Zeft.zeftproject.GPSTracker;
import com.Zeft.zeftproject.R;
import com.example.bdf.SQLite.SQLiteHelper;
import com.example.bdf.data.Vendor;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;


public class GoogleMapv2 extends Activity {

	private GoogleMap map;
	//    private final LatLng YOUR_LAT = new LatLng(latitude, longitude);
	private int DB_bundle_id;
	private SQLiteHelper db;
	private Vendor vendor;
	private Object stringLatitude;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_google_mapv2);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		map.setMyLocationEnabled(true);
		Location userLocation = map.getMyLocation();
		LatLng myLocation = null;
		
		// check if GPS enabled
        GPSTracker gpsTracker = new GPSTracker(this);

        if (gpsTracker.getIsGPSTrackingEnabled())
        {
           
        	map.animateCamera( CameraUpdateFactory.newLatLngZoom( new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude()),10.0f ) ); 
        }
		if (userLocation != null)
		{
			myLocation = new LatLng(userLocation.getLatitude(),
					userLocation.getLongitude()); 
			map.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 12.0f));
		} 
		Bundle bundle = this.getIntent().getExtras();  

		if(bundle !=null)
		{

			DB_bundle_id = bundle.getInt("VendorID");
			if(!(DB_bundle_id+"").equals(""))
			{
				db=SQLiteHelper.getInstance(this);
				vendor = db.getVendor(DB_bundle_id);
				AddMarker(vendor.getLatitude(), vendor.getLongitude(), vendor.getName(), vendor.getEmail());
			}

		}
		else
		{
		 //use DB	
		}

	}
	public GoogleMap getMap()
	{
	 return map;	
	}
	public void AddMarker(double lat , double longitude , String Title , String info)
	{

		Marker perth = map.addMarker(new MarkerOptions()
		.position(new LatLng(lat,longitude))
		.draggable(true)
		.title(Title)
		.snippet(info));
	}
}
