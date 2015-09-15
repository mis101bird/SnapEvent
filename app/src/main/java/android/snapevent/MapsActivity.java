package android.snapevent;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends AppCompatActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true); //BACK botton which just available on Android 5.0, and have to set parentActivity.

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }


    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                Intent info=getIntent();
                setUpMap(new LatLng(info.getDoubleExtra("latitude",0),info.getDoubleExtra("longitude",0)),info.getStringExtra("name"));
            }
        }
    }


    private void setUpMap(final LatLng place,String name) {

        mMap.addMarker(new MarkerOptions().position(place).title(name)).showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place, 15.0f));

        CameraUpdate zoom = CameraUpdateFactory.zoomTo(13f); //zoom number
        mMap.animateCamera(zoom, 3000, new GoogleMap.CancelableCallback() { //animate velocity
            @Override
            public void onFinish() {

            }

            @Override
            public void onCancel() {

            }
        });
    }


}
