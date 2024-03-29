package com.example.eval9maps;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.eval9maps.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
        LatLng start = DataServices.getPath().get(0);
        googleMap.addMarker(new MarkerOptions()
                .position(start)
                .title("Start location"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(start));

        LatLng end = DataServices.getPath().get(DataServices.getPath().size() - 1);
        googleMap.addMarker(new MarkerOptions()
                .position(end)
                .title("End location"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(start));

        googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                PolylineOptions polylineOptions = new PolylineOptions();
                polylineOptions.clickable(true);
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                for (LatLng latLng : DataServices.getPath()){
                    polylineOptions.add(latLng);
                    builder.include(latLng);
                }
                Polyline polyLine = googleMap.addPolyline(polylineOptions);

                LatLngBounds bounds = builder.build();
                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));


            }
        });
    }
}