package com.uadb.eschool.controller.eschool;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class UniversityActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng uadb = new LatLng(14.696200, -16.478371);//14.696200, -16.478371
        mMap.addMarker(new MarkerOptions().position(uadb)
                .title("UADB")
                .snippet("Bambey, Contact: 33 852 45 45 "));

        //ugb map 16.062679, -16.425843
        LatLng ugb = new LatLng(16.062679,-16.425843);
        mMap.addMarker(new MarkerOptions().position(ugb)
                .title("UGB")
                .snippet("SaintLouis, Contact:")
                .icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
                )
        );
        //ucad map 14.693356, -17.463963
        LatLng ucad = new LatLng(14.693356,-17.463963);
        mMap.addMarker(new MarkerOptions().position(ucad).title("UCAD").snippet("Dakar, Contact:"));
        CircleOptions co = new CircleOptions()
                .center(uadb)
                .radius(500)
                .fillColor(Color.GREEN)
                .strokeColor(Color.BLUE)
                .strokeWidth(5);

        mMap.addCircle(co);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(uadb,6));
    }
}
