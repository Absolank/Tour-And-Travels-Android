package com.miniproject.tourandtravels.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.miniproject.tourandtravels.R;
import com.miniproject.tourandtravels.api.model.TourPackage;
import com.miniproject.tourandtravels.util.TimeConverter;
import com.squareup.picasso.Picasso;

import java.util.List;


public class PackageViewer extends RecyclerView.Adapter<PackageViewer.PackageViewHolder> {
    private PackageViewerOnClick packageViewerOnClick;
    private List<TourPackage> packages;
    private static int width = 0, height = 0;
    public void setPackages(List<TourPackage> packages) {
        this.packages = packages;
        notifyDataSetChanged();
    }
    public void setImageDimension(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void setPackageViewerOnClick(PackageViewerOnClick packageViewerOnClick) {
        this.packageViewerOnClick = packageViewerOnClick;
    }

    @NonNull
    @Override
    public PackageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PackageViewHolder(
                LayoutInflater.from(
                        viewGroup.getContext()
                ).inflate(
                        R.layout.package_item,
                        viewGroup,
                        false));
    }

    @Override
    public void onBindViewHolder(@NonNull PackageViewHolder packageViewHolder, int i) {
        packageViewHolder.setData(packages.get(i));
    }

    @Override
    public int getItemCount() {
        if(packages != null)
            return packages.size();
        return 0;
    }

    class PackageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView countryName, packageDescription, daysAndNights, Cost;
        private ImageView package_images;

        private TourPackage tourPackage;
        PackageViewHolder(@NonNull View itemView) {
            super(itemView);

            countryName = itemView.findViewById(R.id.country);
            packageDescription = itemView.findViewById(R.id.package_desciption);
            daysAndNights = itemView.findViewById(R.id.days_and_nights);
            Cost = itemView.findViewById(R.id.cost);
            package_images = itemView.findViewById(R.id.package_images);

            itemView.setOnClickListener(this);
        }
        void setData(TourPackage tourPackage){
            this.tourPackage = tourPackage;
            Picasso.get().load(tourPackage.getImageUrl()).centerCrop().resize(width, height).into(package_images);
            String days_nights = tourPackage.getNumDays() +
                    " Days " + tourPackage.getNumNights() +
                    " Nights departing on " +
                    TimeConverter.formatter.format(tourPackage.getDepartureDate());

            daysAndNights.setText(days_nights);
            Cost.setText(String.valueOf(rupee + tourPackage.getTravelCost()));
//            packageDescription.setText(tourPackage.getDescription());
        }

        @Override
        public void onClick(View v) {
            packageViewerOnClick.getSelectedPackage(tourPackage);
        }
    }
    public interface PackageViewerOnClick{
        void getSelectedPackage(TourPackage tourPackage);
    }
    private static final String  rupee = "₹ ";
}
