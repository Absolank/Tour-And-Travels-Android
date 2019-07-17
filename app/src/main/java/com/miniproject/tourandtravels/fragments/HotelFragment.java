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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.miniproject.tourandtravels.HotelActivity;
import com.miniproject.tourandtravels.R;
import com.miniproject.tourandtravels.adapters.HotelViewer;
import com.miniproject.tourandtravels.api.ResponseCallback;
import com.miniproject.tourandtravels.api.TourAndTravelsRepository;
import com.miniproject.tourandtravels.api.model.Hotel;
import com.miniproject.tourandtravels.util.TimeConverter;

import java.util.Calendar;
import java.util.List;

public class HotelFragment extends Fragment {

    private EditText searchLocation;
    private Button searchHotel, checkInButton, checkOutButton;
    private TextView hotelName, hotelLocation, price;
    Calendar checkInDate, checkOutDate;
    private int i = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.hotels, container, false);

        searchLocation = view.findViewById(R.id.search_location);
        searchHotel = view.findViewById(R.id.search_hotels);
        checkInButton = view.findViewById(R.id.b_check_in);
        checkOutButton = view.findViewById(R.id.b_check_out);
        checkInDate = Calendar.getInstance();
        checkOutDate = Calendar.getInstance();

        final TextView checkIn = view.findViewById(R.id.check_in_date);
        checkIn.setText(TimeConverter.formatter.format(checkInDate.getTime()));
        final TextView checkOut = view.findViewById(R.id.check_out_date);
        checkOut.setText(TimeConverter.formatter.format(checkOutDate.getTime()));
        final DatePickerDialog dialog = new DatePickerDialog(
                getContext(),
                (DatePickerDialog.OnDateSetListener) (view1, year, month, dayOfMonth) -> {
                    checkInDate.set(year, month, dayOfMonth);
                    if(Calendar.getInstance().compareTo(checkInDate) < 0 && checkInDate.compareTo(checkOutDate) < 0) {
                        checkIn.setText(TimeConverter.formatter.format(checkInDate.getTime()));
                    }

                },
                checkInDate.get(Calendar.YEAR), checkInDate.get(Calendar.MONTH), checkInDate.get(Calendar.DAY_OF_MONTH));

        checkInButton.setOnClickListener(v -> {
            dialog.show();
        });
        final DatePickerDialog dialoge = new DatePickerDialog(
                getContext(),
                (DatePickerDialog.OnDateSetListener) (view1, year, month, dayOfMonth) -> {
                    checkOutDate.set(year, month, dayOfMonth);
                    if(Calendar.getInstance().compareTo(checkOutDate) < 0 && checkInDate.compareTo(checkOutDate) < 0) {
                        checkOut.setText(TimeConverter.formatter.format(checkOutDate.getTime()));
                    }
                },
                checkOutDate.get(Calendar.YEAR), checkInDate.get(Calendar.MONTH), checkOutDate.get(Calendar.DAY_OF_MONTH));
        checkOut.setOnClickListener(v -> {
            dialoge.show();
        });

        searchHotel.setOnClickListener(v -> {
            String cityName = searchLocation.getText().toString();

            Intent intent = new Intent(getContext(), HotelActivity.class);
            intent.putExtra("city-name", cityName);
            intent.putExtra("num-person", i);
            intent.putExtra("check-in", checkInDate.getTimeInMillis());
            intent.putExtra("check-out", checkOutDate.getTimeInMillis());
            startActivity(intent);
        });
        final TextView textView = view.findViewById(R.id.num_person);
        Button dec_person = view.findViewById(R.id.dec_persons);
        Button inc_person = view.findViewById(R.id.inc_persons);

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
