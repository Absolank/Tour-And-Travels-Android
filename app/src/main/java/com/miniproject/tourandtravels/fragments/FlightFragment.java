package com.miniproject.tourandtravels.fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.miniproject.tourandtravels.FlightActivity;
import com.miniproject.tourandtravels.R;
import com.miniproject.tourandtravels.adapters.FlightViewer;
import com.miniproject.tourandtravels.api.ResponseCallback;
import com.miniproject.tourandtravels.api.TourAndTravelsRepository;
import com.miniproject.tourandtravels.api.model.TourAndFlights;
import com.miniproject.tourandtravels.util.TimeConverter;

import java.util.Calendar;

public class FlightFragment extends Fragment {
    private Calendar calendar;
    private int i = 1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.flight, container, false);

        TextView flightSource = view.findViewById(R.id.flight_source);
        TextView flightDestination = view.findViewById(R.id.flight_destination);
        Button flightSearch = view.findViewById(R.id.flight_search);

        calendar = Calendar.getInstance();

        flightSearch.setOnClickListener(v->{
            String source = flightSource.getText().toString();
            String destination = flightDestination.getText().toString();
            if(!source.equals("") && !destination.equals("")){
                Intent intent = new Intent(getContext(), FlightActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtra("source-city", source);
                intent.putExtra("destination-city", destination);
                intent.putExtra("departure-date", calendar.getTimeInMillis());
                intent.putExtra("num-person", i);
                startActivity(intent);
            }
        });
        TextView departureDate = view.findViewById(R.id.departure_date);
        Button selectDeparture = view.findViewById(R.id.b_departure_date);

        selectDeparture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                calendar.set(year, month, dayOfMonth);
                                if(Calendar.getInstance().compareTo(calendar) < 0)
                                {
                                    departureDate.setText(TimeConverter.formatter.format(calendar.getTime()));
                                }

                            }
                        },
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
        Button dec_person = view.findViewById(R.id.dec_persons);
        Button inc_person = view.findViewById(R.id.inc_persons);
        TextView textView = view.findViewById(R.id.num_person);
        dec_person.setOnClickListener(v -> {
            i = i - 1;
            if(i < 1)
                i =  1;
            textView.setText(String.valueOf(i));
        });
        inc_person.setOnClickListener(v -> {
            i = i + 1;
            textView.setText(String.valueOf(i));
        });

        return view;
    }
}
