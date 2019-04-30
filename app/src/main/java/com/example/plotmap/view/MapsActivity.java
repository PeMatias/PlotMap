package com.example.plotmap.view;

import android.content.Intent;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.plotmap.R;
import com.example.plotmap.model.Continente;
import com.example.plotmap.model.Pais;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MapsActivity extends Continente implements OnMapReadyCallback {

    private Pais pais;
    private List<Pais> paisList = new ArrayList<Pais>();

    private static final int ZOOM_LEVEL = 15;
    private static final int TILT_LEVEL = 0;
    private static final int BEARING_LEVEL = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //pega paises passada pela main

        //pais = (Pais) getIntent().getSerializableExtra("pais");  // Esse funciona

        //Teste

        Intent intent = getIntent();
        paisList = (List<Pais>) intent.getSerializableExtra("LIST");



        //inicializa mapFragment
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {

      /*  try {
            for (int i = 0; i < paisList.size(); i++) {

                Pais pais = paisList.get(i);
                LatLng position = new LatLng(pais.getLatlong().get(0), pais.getLatlong().get(1));
                googleMap.addMarker(new MarkerOptions().position(position));

                if (i == 0) {
                    CameraPosition camPos = new CameraPosition(position, ZOOM_LEVEL, TILT_LEVEL, BEARING_LEVEL);
                    googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(camPos));
                    //fillTextViews(pais);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println("IndexOutOfBoundsException: " + e.getMessage());
        }*/


        try
        {
            for (Pais pais : paisList)
            {
                //coordenadas do pais
                LatLng paislatlong = new LatLng(pais.getLatlong().get(0), pais.getLatlong().get(1));

                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


                //adiciona marcador e move a camera do mapa p ele
                googleMap.addMarker(new MarkerOptions().position(paislatlong).title(pais.getNome()).icon(
                        BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(paislatlong, 8.0f));

            }

        }catch (IndexOutOfBoundsException e) {
            System.err.println("IndexOutOfBoundsException: " + e.getMessage());
        }


    }
}