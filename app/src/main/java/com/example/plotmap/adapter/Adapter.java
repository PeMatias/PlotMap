package com.example.plotmap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.plotmap.R;
import com.example.plotmap.model.Pais;

import java.util.List;

public class Adapter extends ArrayAdapter<Pais>
{
    public Adapter(Context context, List<Pais> objects) {
        super(context, 0, objects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Pais pais = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_pais, parent, false);
        }
        TextView txtNome = (TextView) convertView.findViewById(R.id.nome);
        TextView txtCapital = (TextView) convertView.findViewById(R.id.capital);
        TextView txtContinente = (TextView) convertView.findViewById(R.id.continente);
        TextView txtSubcontinente = (TextView) convertView.findViewById(R.id.subcontinente);

        txtNome.setText(pais.getNome());
        txtCapital.setText(pais.getCapital());
        txtContinente.setText(pais.getContinente());
        txtSubcontinente.setText(pais.getSubcontinente());

        return convertView;
    }
}
