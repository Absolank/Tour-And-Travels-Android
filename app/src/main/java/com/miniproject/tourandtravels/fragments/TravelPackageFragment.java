package com.miniproject.tourandtravels.fragments;

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

import com.miniproject.tourandtravels.PackageActivity;
import com.miniproject.tourandtravels.R;
import com.miniproject.tourandtravels.adapters.PackageViewer;
import com.miniproject.tourandtravels.api.ResponseCallback;
import com.miniproject.tourandtravels.api.TourAndTravelsRepository;
import com.miniproject.tourandtravels.api.model.Tour;
import com.miniproject.tourandtravels.api.model.TourPackage;
import com.miniproject.tourandtravels.api.model.TravelPackageData;
import com.miniproject.tourandtravels.util.SizeConverters;

import java.util.List;

public class TravelPackageFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.tour_packages, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.packages_viewer);
        PackageViewer packageViewer = new PackageViewer();
        int width = getActivity().getWindowManager().getDefaultDisplay().getWidth() + 180;
        System.out.println("Width " + width);
        int height = (int)SizeConverters.convertDpToPixel(200, getContext());

        packageViewer.setImageDimension(width, height);
        recyclerView.setAdapter(packageViewer);
        packageViewer.setPackageViewerOnClick(
                tourPackage -> {
                    Intent intent = new Intent(getContext(), PackageActivity.class);
                    intent.putExtra("package-id", tourPackage.getID());
                    startActivity(intent);
                });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        TourAndTravelsRepository apiRepo = new TourAndTravelsRepository();
        apiRepo.searchPackage(new ResponseCallback() {
            @Override
            public void callback(Object param) {
                List<TourPackage> packages = (List<TourPackage>) param;
                packageViewer.setPackages(packages);
            }
        });




        return  view;
    }
}
