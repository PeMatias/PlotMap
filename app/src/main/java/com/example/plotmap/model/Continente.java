package com.example.plotmap.model;

import java.util.List;

public class Continente
{
    private List<Pais> continente;

    public Continente(List<Pais> continente) {
        this.continente = continente;
    }

    public List<Pais> getContinente() {
        return continente;
    }

    public void setContinente(List<Pais> continente) {
        this.continente = continente;
    }
}
