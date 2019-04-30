package com.example.plotmap.view;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.plotmap.R;
import com.example.plotmap.model.Continente;
import com.example.plotmap.model.Pais;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends Continente implements OnMapReadyCallback {

    private Pais pais;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //pega paises passada pela main

        pais = (Pais) getIntent().getSerializableExtra("pais");
        //pais


        //inicializa mapFragment
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        //coordenadas da ubs
        LatLng paislatlong = new LatLng(pais.getLatlong().get(0), pais.getLatlong().get(1));

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


        //adiciona marcador e move a camera do mapa p ele
        googleMap.addMarker(new MarkerOptions().position(paislatlong).title(pais.getNome()).icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(paislatlong, 8.0f));
    }
}