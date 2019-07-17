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

public class BookingFragment extends Fragment {
    private int ID;
    private Date checkIn, checkOut;
    private String idSuff;
    private int numDay;
    public void setData(String idSuff, int ID, int numDay, Date checkIn, Date checkOut){
        this.idSuff = idSuff;
        this.ID = ID;
        this.numDay = numDay;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.order_details, container, false);

        TextView itemID, checkInWeek, checkInDay, checkInMonth, checkOutWeek, checkOutDay, checkOutMonth, numDays;
        itemID = view.findViewById(R.id.item_id);
        idSuff = idSuff + ID;
        itemID.setText(idSuff);
        checkInWeek = view.findViewById(R.id.dep_week);
        checkInWeek.setText(TimeConverter.week.format(checkIn));
        checkInDay = view.findViewById(R.id.dep_day);
        checkInDay.setText(TimeConverter.day.format(checkIn));
        checkInMonth = view.findViewById(R.id.dep_month);
        checkInMonth.setText(TimeConverter.month.format(checkIn));

        numDays = view.findViewById(R.id.num_days);
        String s = numDay + " night(s)";
        numDays.setText(s);
        checkOutWeek = view.findViewById(R.id.check_out_week);
        checkOutWeek.setText(TimeConverter.week.format(checkOut));
        checkOutDay = view.findViewById(R.id.check_out_day);
        checkOutDay.setText(TimeConverter.day.format(checkOut));
        checkOutMonth = view.findViewById(R.id.check_out_month);
        checkOutMonth.setText(TimeConverter.month.format(checkOut));

        return view;
    }
}
