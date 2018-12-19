package com.example.madina.childvac.fragments;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.madina.childvac.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {

    private SupportMapFragment mapFragment;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private GoogleMap mMap;
    private ViewGroup mContainer;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(mContainer.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        mContainer = container;

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.map, mapFragment);
            fragmentTransaction.commit();
        }

        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        locationManager = (LocationManager) mContainer.getContext().getSystemService(Context.LOCATION_SERVICE);
//
//        locationListener = new LocationListener() {
//            @Override
//            public void onLocationChanged(Location location) {
//                mMap.clear();
////                LatLng userLocation = new LatLng(location.getLatitude(),location.getLongitude());
//                LatLng userLocation = new LatLng(43.235334,76.909873);
//                mMap.addMarker(new MarkerOptions().position(userLocation).title("Your Location")
//                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.child_marker)));
////                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 17));
//                drawLocations();
//            }
//
//            @Override
//            public void onStatusChanged(String provider, int status, Bundle extras) {
//
//            }
//
//            @Override
//            public void onProviderEnabled(String provider) {
//
//            }
//
//            @Override
//            public void onProviderDisabled(String provider) {
//
//            }
//        };
//
//        if (Build.VERSION.SDK_INT < 23) {
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
//        } else {
//            if (ContextCompat.checkSelfPermission(mContainer.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(getActivity(), new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, 1);
//            } else {
//                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
//                Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//
//                mMap.clear();
//                LatLng userLocation = new LatLng(lastKnownLocation.getLatitude(),lastKnownLocation.getLongitude());
//                mMap.addMarker(new MarkerOptions().position(userLocation).title("Your Location")
//                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
//                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 17));
//            }
//        }
        LatLng userLocation = new LatLng(43.235334,76.909873);
                mMap.addMarker(new MarkerOptions().position(userLocation).title("Your Location")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.child_marker)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 17));
        drawLocations();

    }

        private void drawLocations() {
            LatLng rakhat = new LatLng(43.241006,76.908696);
            mMap.addMarker(new MarkerOptions().position(rakhat).title("Medical Center \"Rakhat\"")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.clinic_marker)));

            LatLng alta = new LatLng(43.245563,76.916332);
            mMap.addMarker(new MarkerOptions().position(alta).title("Alta Medical Center")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.clinic_marker)));

            LatLng park = new LatLng(43.232972,76.890988);
            mMap.addMarker(new MarkerOptions().position(park).title("Medical Park")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.clinic_marker)));

            LatLng avesta = new LatLng(43.248963,76.902546);
            mMap.addMarker(new MarkerOptions().position(avesta).title("Medical Park")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.clinic_marker)));

            LatLng international = new LatLng(43.249194,76.915379);
            mMap.addMarker(new MarkerOptions().position(international).title("International Medical Center")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.clinic_marker)));

            LatLng rada = new LatLng(43.256456,76.904561);
            mMap.addMarker(new MarkerOptions().position(rada).title("Medical Center Rada")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.clinic_marker)));

            LatLng onClinic = new LatLng(43.229805,76.907422);
            mMap.addMarker(new MarkerOptions().position(onClinic).title("Mecial Center On Clinic")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.clinic_marker)));

            // Move camera to Almaty, so that all the clinics are visible
            LatLng cityView = new LatLng(43.243941,76.907742);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cityView, 13.6F));
        }
}
