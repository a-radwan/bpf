package maps;

import com.Zeft.zeftproject.R;
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
   
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_mapv2);
          
      map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
      map.setMyLocationEnabled(true);
      Location userLocation = map.getMyLocation();
              LatLng myLocation = null;
              if (userLocation != null)
              {
                  myLocation = new LatLng(userLocation.getLatitude(),
                          userLocation.getLongitude()); 
                  map.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation,
                          15));
                 
                 
    
              }
             
              
	}
	public void AddMarker(double lat , double longitude , String Title , String info)
	{
		
		Marker perth = map.addMarker(new MarkerOptions()
		                          .position(new LatLng(lat,longitude))
		                          .draggable(true)
		                          .title(info)
		                          .snippet(info));
	}
	}
