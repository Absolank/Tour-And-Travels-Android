package com.miniproject.tourandtravels.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miniproject.tourandtravels.R;
import com.miniproject.tourandtravels.adapters.FlightInvoiceViewer;
import com.miniproject.tourandtravels.api.ResponseCallback;
import com.miniproject.tourandtravels.api.TourAndTravelsRepository;
import com.miniproject.tourandtravels.api.model.FlightInvoice;

import java.util.List;

public class Invoice extends Fragment {
    private RecyclerView.Adapter adapter;
    public void setAdapter(RecyclerView.Adapter adapter){
        this.adapter = adapter;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.recyle, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }
}
