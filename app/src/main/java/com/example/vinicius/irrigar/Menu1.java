package com.example.vinicius.irrigar;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.vinicius.irrigar.Controller.MySingleton;


public class Menu1 extends Fragment {

    private TextView tvTemperaturaAmbiente;
    private TextView tvUmidadeAmbiente;
    private TextView tvUmidadeSolo;
    private Thread thread;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Sensoriamento em tempo real");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu1, container, false);
        tvTemperaturaAmbiente = (TextView) view.findViewById(R.id.tvTemperaturaAmbiente);
        tvUmidadeAmbiente = (TextView) view.findViewById(R.id.tvUmidadeAmbiente);
        tvUmidadeSolo = (TextView) view.findViewById(R.id.tvUmidadeSolo);

        thread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    MySingleton.getInstance(getContext()).addToRequestQueue(getRequestSensoriamentoTempoReal());
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
        return view;
    }

    public StringRequest getRequestSensoriamentoTempoReal() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://192.168.43.37/realtime",
                new Response.Listener<String>() {
                    public void onResponse(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                        tvUmidadeAmbiente.setText(response.split("@")[0] + "%");
                        tvTemperaturaAmbiente.setText(response.split("@")[1] + "°");
                        tvUmidadeSolo.setText(response.split("@")[2]);
                    }
                }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage() + "erro", Toast.LENGTH_SHORT).show();
            }
        });
        return stringRequest;
    }

}
