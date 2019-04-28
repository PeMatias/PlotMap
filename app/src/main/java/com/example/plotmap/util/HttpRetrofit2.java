package com.example.plotmap.util;

import com.example.plotmap.model.Pais;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class HttpRetrofit2
{
    private static final String BASE_URL = "https://restcountries.eu";

    //Inicializa Retrofit
    public static PaisInterface getPaisClient() {
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return restAdapter.create(PaisInterface.class);
    }

    // Interface com métodos de requisicao
    public interface PaisInterface {
        @GET("/rest/v1/all")
        Call<List<Pais>> getPais();
    }
}
