package com.example.vinicius.irrigar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import java.util.Calendar;
import java.util.Random;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class Menu3 extends Fragment {


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(Html.fromHtml("<small>Análise de dados históricos<small>"));
    }

    public DataPoint[] getTempAr() {
        com.jjoe64.graphview.series.DataPoint[] dataPoints = new DataPoint[40];
        Random r = new Random();
        for (int i = 0; i < 40; i++) {
            dataPoints[i] = new DataPoint(i, r.nextInt((35 - 20) + 1) + 20);
        }
        return dataPoints;
    }


    public DataPoint[] getUmidadeAr() {
        com.jjoe64.graphview.series.DataPoint[] dataPoints = new DataPoint[40];
        Random r = new Random();
        for (int i = 0; i < 40; i++) {
            dataPoints[i] = new DataPoint(i, r.nextInt((90 - 50) + 1) + 50);
        }
        return dataPoints;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu3, container, false);

        GraphView graph = (GraphView) view.findViewById(R.id.temperaturaAmbienteGraph2);
        graph.setTitle("Temperatura do solo");
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(getTempAr());
        series.setColor(Color.RED);
        series.setDrawDataPoints(false);
        series.setThickness(8);
        graph.addSeries(series);

        graph.getViewport().setMaxX(41);
        graph.getViewport().setXAxisBoundsManual(true);

        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Dias");
        gridLabel.setVerticalAxisTitle("°C");


        GraphView graph2 = (GraphView) view.findViewById(R.id.umidadeAmbienteGraph2);
        graph2.setTitle("Umidade do solo");
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(getUmidadeAr());
        series2.setColor(Color.YELLOW);
        series2.setDrawDataPoints(false);
        series2.setThickness(8);
        graph2.addSeries(series2);

        graph2.getViewport().setMaxX(41);
        graph2.getViewport().setMaxY(100);
        graph2.getViewport().setYAxisBoundsManual(true);
        graph2.getViewport().setXAxisBoundsManual(true);

        GridLabelRenderer gridLabel2 = graph2.getGridLabelRenderer();
        gridLabel2.setHorizontalAxisTitle("Dias");
        gridLabel2.setVerticalAxisTitle("%");

        return view;

    }


}
