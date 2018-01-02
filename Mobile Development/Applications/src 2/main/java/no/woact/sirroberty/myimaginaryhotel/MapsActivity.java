package no.woact.sirroberty.myimaginaryhotel;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * This class takes the user to the imaginary hotel
 * located in Okinawa Island Japan (Hamahiga Island)
 * the location is shown with a marker pin.
 *
 * Inspired by example by Google Maps APIs
 * source: https://github.com/googlemaps/android-samples
 * @author Robert Mattias Molin
 * version: 2017.05.26
 */

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker on Hamahiga Island, Japan and move the camera
        LatLng hamahiga = new LatLng(26, 127);
        mMap.addMarker(new MarkerOptions().position(hamahiga).title("Marker Hamahiga Island"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hamahiga));
    }
}

