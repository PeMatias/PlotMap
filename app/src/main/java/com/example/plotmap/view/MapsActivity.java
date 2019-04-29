package com.example.plotmap.view;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.plotmap.R;
import com.example.plotmap.model.Pais;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private Pais pais;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // pega os paises passados pela main
                pais = (Pais) getIntent().getSerializableExtra("pais");

        // inicializa mapFragment
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

        //coordenadas dos países
        LatLng paisLatLong = new LatLng(pais.getLatlong().get(0),pais.getLatlong().get(1));

        //adiciona marcador e move a camera do mapa p ele
        googleMap.addMarker(new MarkerOptions().position(paisLatLong).title(pais.getNome()));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(paisLatLong, 12.0f));
    }
}
