package com.miniproject.tourandtravels.adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.miniproject.tourandtravels.CheckOutActivity;
import com.miniproject.tourandtravels.R;
import com.miniproject.tourandtravels.api.model.Hotel;
import com.miniproject.tourandtravels.api.model.HotelData;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.w3c.dom.Text;

import java.util.List;

public class HotelViewer extends RecyclerView.Adapter<HotelViewer.HotelViewHolder> {
    public static final int stars[] = {
            R.drawable.ic_1star,
            R.drawable.ic_2stars,
            R.drawable.ic_3stars,
            R.drawable.ic_4stars,
            R.drawable.ic_5stars
    };
    private Activity activity;
    private List<Hotel> hotels;
    private int numPerson;
    private long checkIn, checkOut;
    public HotelViewer(int numPerson, long checkIn, long checkOut, Activity activity){
        this.activity = activity;
        this.numPerson = numPerson;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }


    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HotelViewHolder(
                LayoutInflater.from(
                        viewGroup.getContext()
                ).inflate(
                        R.layout.hotel_item,
                        viewGroup,
                        false));
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder hotelViewHolder, int i) {
        Hotel hotel = hotels.get(i);
        if(hotel.getImageUrl() != null)
            hotelViewHolder.setData(hotel);


    }

    @Override
    public int getItemCount() {
        if(hotels != null)
            return hotels.size();
        return 0;
    }

    class HotelViewHolder extends RecyclerView.ViewHolder{
        private ImageView hotelImage;
        private TextView hotelName, hotelLocation, hotelPrice;
        private ImageView star_view;
        private Button bookHotel;
        private Hotel hotel;
        HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            hotelImage = itemView.findViewById(R.id.hotel_image);
            hotelName = itemView.findViewById(R.id.hotel_name);
            hotelLocation = itemView.findViewById(R.id.hotel_location);
            star_view = itemView.findViewById(R.id.stars);
            hotelPrice = itemView. findViewById(R.id.hotel_price);
            bookHotel = itemView.findViewById(R.id.book_hotel);

            bookHotel.setOnClickListener(v -> {
                Intent intent = new Intent(activity.getApplicationContext(), CheckOutActivity.class);
                intent.putExtra("type", 1);
                intent.putExtra("num-person", numPerson);
                intent.putExtra("check-in", checkIn);
                intent.putExtra("check-out", checkOut);
                intent.putExtra("id", hotel.getID());
                intent.putExtra("cost", hotel.getPerPersonCost());

                activity.startActivity(intent);
            });
        }
        private void setData(Hotel hotel){
            this.hotel = hotel;
            if(hotelImage.getMeasuredWidth() > 0 &&  hotelImage.getMeasuredHeight() > 0)
                Picasso.get().load(hotel.getImageUrl()).centerCrop().resize(hotelImage.getMeasuredWidth(), hotelImage.getMeasuredHeight()).into(hotelImage);
            else
                Picasso.get().load(hotel.getImageUrl()).into(hotelImage);

            hotelName.setText(hotel.getName());
                hotelPrice.setText(ruppe + hotel.getPerPersonCost());
            hotelLocation.setText(hotel.getPlace());
            star_view.setImageResource(stars[hotel.getStar() - 1]);
        }
    }
    private static String ruppe = "₹ ";
}
