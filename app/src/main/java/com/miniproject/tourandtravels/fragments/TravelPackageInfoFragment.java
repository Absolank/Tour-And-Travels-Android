package com.miniproject.tourandtravels.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.miniproject.tourandtravels.CheckOutActivity;
import com.miniproject.tourandtravels.R;
import com.miniproject.tourandtravels.adapters.PackageDataViewer;
import com.miniproject.tourandtravels.api.model.TravelPackageData;
import com.miniproject.tourandtravels.util.SizeConverters;
import com.miniproject.tourandtravels.util.TimeConverter;
import com.squareup.picasso.Picasso;

import java.util.Date;

public class TravelPackageInfoFragment extends Fragment {
    private int i = 1;
    private TravelPackageData travelPackageData;
    public void setData(TravelPackageData travelPackageData) {
        this.travelPackageData = travelPackageData;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.travel_package_info, container, false);
        ListView recyclerView = view.findViewById(R.id.holiday_details);
        PackageDataViewer packageDataViewer = new PackageDataViewer();
        ImageView imageView = view.findViewById(R.id.imageView11);
        recyclerView.setAdapter(packageDataViewer);

        int width = getActivity().getWindowManager().getDefaultDisplay().getWidth() + 180;
        System.out.println("Width " + width);
        int height = (int) SizeConverters.convertDpToPixel(280, getContext());

        Picasso.get().load(travelPackageData.getTourPackage().getImageUrl()).resize(width, height).centerCrop().into(imageView);
        packageDataViewer.setData(travelPackageData);
        TextView details = view.findViewById(R.id.textView7);
        String str = travelPackageData.getTourPackage().getNumDays() + " and day(s) " + travelPackageData.getTourPackage().getNumNights() + " night(s) Departing on " + TimeConverter.formatter.format(travelPackageData.getTourPackage().getDepartureDate());
        details.setText(str);
        TextView description = view.findViewById(R.id.description);
        description.setText(travelPackageData.getTourPackage().getDescription());

        Button bookPackage = view.findViewById(R.id.book_package);
        bookPackage.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), CheckOutActivity.class);
            intent.putExtra("id", travelPackageData.getTourPackage().getID());
            Date checkInDate = travelPackageData.getTourPackage().getDepartureDate();
            Date checkOutDate = travelPackageData.getTourPackage().getReturnDate();

            intent.putExtra("type", 2);
            intent.putExtra("cost", travelPackageData.getTourPackage().getTravelCost() + travelPackageData.getTourPackage().getOtherCost());
            intent.putExtra("num-person", i);
            intent.putExtra("check-in", checkInDate.getTime());
            intent.putExtra("check-out", checkOutDate.getTime());
            intent.putExtra("num-days", Math.max(travelPackageData.getTourPackage().getNumDays(), travelPackageData.getTourPackage().getNumNights()));
            startActivity(intent);
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
