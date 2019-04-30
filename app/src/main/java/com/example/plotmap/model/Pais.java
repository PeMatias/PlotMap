package com.example.plotmap.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pedro on 26/04/19.
 */
public class Pais implements Serializable
{
    @SerializedName("name")
    private String nome;

    public long id;

    @SerializedName("capital")
    private String capital;

    @SerializedName("region")
    private String continente;

    @SerializedName("subregion")
    private String subcontinente;

    @SerializedName("latlng")
    private List<Double> latlong = new ArrayList<Double>(2);

    public Pais(String pais, String capital, String continente, String subcontinente, List<Double>latlong)
    {
        this.nome = pais;
        this.capital = capital;
        this.continente = continente;
        this.subcontinente = subcontinente;
        this.latlong = latlong;
    }

    public String getNome() {
        return nome;
    }

    public void setPais(String pais) {
        this.nome = nome;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public String getSubcontinente() {
        return subcontinente;
    }

    public void setSubcontinente(String subcontinente) {
        this.subcontinente = subcontinente;
    }

    public List<Double> getLatlong() {
        return latlong;
    }

    public void setLatlong(List<Double> latlong) {
        this.latlong = latlong;
    }

    @Override
    public String toString()
    {
        return "Pais{" +
                " nome='" + nome + '\'' +
                " capital='" + capital + '\'' +
                '}';
    }
}
