package com.example.plotmap.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import com.example.plotmap.dao.Repositorio;
import com.example.plotmap.model.Continente;
import com.example.plotmap.model.Pais;
import com.example.plotmap.util.HttpRetrofit2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Continente implements SwipeRefreshLayout.OnRefreshListener {

    private Adapter adapter;
    private List<Pais> paisList;
    private ListView listView;
    private SwipeRefreshLayout swiperefresh;

    //variável responsável pela instância do banco de dados
    Repositorio db;

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

        db = new Repositorio(getBaseContext());
        getDataRetrofit2();


        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplication(), paisList.get(position).toString(), Toast.LENGTH_LONG).show();

                //Adicionando a troca de activity com o mapa
                hasPermission();

                Intent intent = new Intent(MainActivity.this, MapsActivity.class);

                //intent.putExtra("pais",paisList.get(position)); // esse funciona
                //Teste
                intent.putExtra("LIST", (Serializable) paisList);


                startActivity(intent);
            }
        });
    }

    // verifica se há conexão com a internet
    public Boolean isConnected(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if ( cm != null ) {
            NetworkInfo ni = cm.getActiveNetworkInfo();
            return ni != null && ni.isConnected();
        }
        return false;
    }


    private void getDataSqlite() {
        paisList.clear();
        paisList.addAll(db.listarPais());
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onRefresh() {
        getDataRetrofit2();
    }



    // RETROFIT2

    public void getDataRetrofit2() {

        swiperefresh.setRefreshing(true);

        // se tiver conexao faz get, senao pega do sqlite
        if (isConnected()) {
            HttpRetrofit2.getPaisClient().getPais().enqueue(new Callback<List<Pais>>() {
                public void onResponse(Call<List<Pais>> call, Response<List<Pais>> response) {
                    if (response.isSuccessful()) {
                        List<Pais> paisBody = response.body();
                        paisList.clear();

                        db.excluirAll();

                        for (Pais pais : paisBody) {
                            paisList.add(pais);
                            db.inserir(pais);
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

        }else {
            swiperefresh.setRefreshing(false);
            Toast.makeText(this,"Sem Conexão, listando Países do banco...",Toast.LENGTH_SHORT).show();
            getDataSqlite();
        }

    }

    // API DO GOOGLE MAPS
    void hasPermission(){
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
