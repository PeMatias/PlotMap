package com.example.plotmap.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.plotmap.R;
import com.example.plotmap.adapter.Adapter;
import com.example.plotmap.model.Pais;
import com.example.plotmap.util.HttpRetrofit2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private Adapter adapter;
    private List<Pais> paisList;
    private ListView listView;
    private SwipeRefreshLayout swiperefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swiperefresh = (SwipeRefreshLayout) findViewById((R.id.swiperefresh));
        //seta cores
        swiperefresh.setColorScheme(R.color.colorPrimary, R.color.colorAccent);
        swiperefresh.setOnRefreshListener(this);

        listView = (ListView) findViewById(R.id.listView);
        paisList = new ArrayList<Pais>();
        adapter = new Adapter(this, paisList);

        getDataRetrofit();


        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                //Toast.makeText(getApplication(), paisList.get(position).toString(), Toast.LENGTH_LONG).show();
                hasPermission();

                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("pais",paisList.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRefresh() {
        getDataRetrofit();
    }

    // RETROFIT2

    private void getDataRetrofit(){
        HttpRetrofit2.getPaisClient().getPais().enqueue(new Callback<List<Pais>>() {
            public void onResponse(Call<List<Pais>> call, Response<List<Pais>> response) {
                if (response.isSuccessful()) {
                    List<Pais> paisBody = response.body();

                    paisList.clear();
                    for (Pais pais : paisBody)
                    {
                        paisList.add(pais);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    System.out.println(response.errorBody());
                }
                swiperefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Pais>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    // PERMISSÃO PARA UTILIZAR OS DADOS DO GPS
    void hasPermission()
    {
        //pede permissao de localizacao
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // ja pediu permissao?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {

                // solicita permissao de localizacao
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            }
        }
    }


}
