package com.miniproject.tourandtravels.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miniproject.tourandtravels.R;
import com.miniproject.tourandtravels.util.TimeConverter;

import java.util.Date;

public class FlightCheckOutFragment extends Fragment {
    private int ID;
    private String source, destination;
    private Date departureDate;
    public void setData(int ID, Date departureDate, String source, String destination) {
        this.ID = ID;
        this.departureDate = departureDate;
        this.source = source;
        this.destination = destination;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.flight_booking_details, container, false);

        TextView itemID, checkInWeek, checkInDay, checkInMonth, flightId, sourceCity, destinationCity;
        itemID = view.findViewById(R.id.flight_id);
        String id = "Flight ID: " + ID;
        itemID.setText(id);
        checkInWeek = view.findViewById(R.id.dep_week);
        checkInWeek.setText(TimeConverter.week.format(departureDate));
        checkInDay = view.findViewById(R.id.dep_day);
        checkInDay.setText(TimeConverter.day.format(departureDate));
        checkInMonth = view.findViewById(R.id.dep_month);
        checkInMonth.setText(TimeConverter.month.format(departureDate));

        sourceCity = view.findViewById(R.id.source_city);
        sourceCity.setText(source);
        destinationCity = view.findViewById(R.id.destination_city);
        destinationCity.setText(destination);
        view.findViewById(R.id.flight_cost).setVisibility(View.GONE);
        view.findViewById(R.id.book_flight).setVisibility(View.GONE);
        view.findViewById(R.id.arrival_time).setVisibility(View.GONE);
        view.findViewById(R.id.departure_time).setVisibility(View.GONE);
        view.findViewById(R.id.travel_time).setVisibility(View.GONE);
        return view;
    }
}
