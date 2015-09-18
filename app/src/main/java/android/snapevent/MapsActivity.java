package android.snapevent;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.snapevent.app.AppController;
import android.snapevent.bean.xmlEventBean;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MapsActivity extends AppCompatActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    MarkerOptions markerOptions;
    List<MarkerItem> markers=null;
    LinearLayout markinfo;
    TextView infotitle;
    TextView infodes;
    Marker preClicked=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        markinfo = (LinearLayout) this.findViewById(R.id.show_mark);
        infotitle = (TextView) findViewById(R.id.show_mark_title);
        infodes = (TextView) findViewById(R.id.show_mark_content);

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
                Intent info = getIntent();
                setUpMap(new LatLng(info.getDoubleExtra("latitude", 0), info.getDoubleExtra("longitude", 0)), info.getStringExtra("name"));
            }
        }
    }


    private void setUpMap(final LatLng place, String name) {

        mMap.addMarker(new MarkerOptions().position(place).title(name)).showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place, 15.0f));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(14f); //zoom number
        mMap.animateCamera(zoom, 1000, new GoogleMap.CancelableCallback() { //animate velocity
            @Override
            public void onFinish() {
                Log.i("animateCamera", "onFinish");
                markers = getEventAddress();
                for (int i = 0; i < markers.size(); i++) {
                    new GeocoderTask().execute(markers.get(i));
                }
            }

            @Override
            public void onCancel() {

            }
        });
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker arg0) {

                if(markers !=null){
                    for (MarkerItem it : markers) {
                        if (arg0.getTitle().equals(it.getAddress())) {

                            if(preClicked!=null){
                                preClicked.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.direction_down));
                            }
                            Log.i("clickedIcon",it.getAddress());
                            arg0.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.direction_down2));
                            if (markinfo.getVisibility()== View.GONE) {
                                markinfo.setVisibility(View.VISIBLE);
                            }
                            infotitle.setText(it.getBean().getTitle());
                            infodes.setText(it.getBean().getTimeANDplace());
                            preClicked=arg0;
                        }
                    }
                    return true;
                }else{

                    return false;
                }


            }


        });
    }

    public List<MarkerItem> getEventAddress() {

        HashMap events = AppController.getInstance().getEventbeans();
        List<xmlEventBean> ev = (List<xmlEventBean>) events.get("KKTIX");
        Log.i("getTimeANDplace", "1: " + ev.size());
        List<MarkerItem> address = new ArrayList<MarkerItem>();
        int i = 0;
        for (xmlEventBean e : ev) {
            if (!e.getTitle().contains("測試")) {
                String[] arrays = e.getTimeANDplace().split("/ +");
                if (arrays.length >= 2 && !arrays[1].equals("無")) {
                    if (!arrays[1].contains(" ")) {
                        Log.i("getTimeANDplace:arrays", arrays[1]);
                        address.add(new MarkerItem(i, arrays[1], null));
                    } else {
                        String[] ans = arrays[1].split(" ");
                        if (ans.length >= 1) {
                            Log.i("getTimeANDplace:ans", ans[0]);
                            address.add(new MarkerItem(i, ans[0], null));

                        }
                    }

                }
            }
            i++;
        }
        Log.i("getTimeANDplace", "2: " + address.size());
        return address;
    }

    private class GeocoderTask extends AsyncTask<MarkerItem, Void, MarkerItem> {

        @Override
        protected MarkerItem doInBackground(MarkerItem... locationName) {
            // Creating an instance of Geocoder class
            Geocoder geocoder = new Geocoder(getBaseContext());
            List<Address> addresses = null;
            MarkerItem name = locationName[0];
            try {
                // Getting a maximum of 1 Address that matches the input text
                addresses = geocoder.getFromLocationName(name.getAddress(), 1);
                if (addresses != null && addresses.size() >= 1) {
                    name.setPlaceItem(addresses.get(0));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return name;
        }

        @Override
        protected void onPostExecute(MarkerItem addresses) {

            if (addresses == null || addresses.getPlaceItem() == null) {
                Log.i("FindNoLocation", "on " + addresses.getAddress());
            }

            // Clears all the existing markers on the map
            //googleMap.clear();

            // Adding Markers on Google Map for each matching address

            Address address = (Address) addresses.getPlaceItem();

            // Creating an instance of GeoPoint, to display in Google Map
            if (address != null) {
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                markerOptions = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.direction_down));
                markerOptions.position(latLng);
                mMap.addMarker(markerOptions.title(addresses.getAddress()));

            }

        }
    }

    class MarkerItem {
        private xmlEventBean bean = null;
        private String address = null;
        private Address placeItem = null;

        public MarkerItem(int i, String address, Address placeItem) {
            this.bean = (xmlEventBean) AppController.getInstance().getEventbeans().get("KKTIX").get(i);
            this.address = address;
            this.placeItem = placeItem;
        }

        public xmlEventBean getBean() {
            return bean;
        }

        public String getAddress() {
            return address;
        }

        public Address getPlaceItem() {
            return placeItem;
        }

        public void setPlaceItem(Address placeItem) {
            this.placeItem = placeItem;
        }
    }

}
