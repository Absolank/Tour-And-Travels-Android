package com.miniproject.tourandtravels.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.miniproject.tourandtravels.R;
import com.miniproject.tourandtravels.api.model.FlightData;
import com.miniproject.tourandtravels.api.model.Hotel;
import com.miniproject.tourandtravels.api.model.HotelData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HotelListViewer extends BaseAdapter {

    private List<HotelData> hotelDataList;

    public void setHotelDataList(List<HotelData> hotelDataList) {
        this.hotelDataList = hotelDataList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(hotelDataList != null)
            return hotelDataList.size();
        return  0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_item, parent, false);
        }
        HotelData hotelData = hotelDataList.get(position);
        new HotelViewHolder(convertView).setData(hotelData);
        return convertView;
    }

    private static class HotelViewHolder{
        private ImageView hotelImage;
        private TextView hotelName, hotelLocation;
        private ImageView star_view;
        HotelViewHolder(@NonNull View itemView) {
            hotelImage = itemView.findViewById(R.id.hotel_image);
            hotelName = itemView.findViewById(R.id.hotel_name);
            hotelLocation = itemView.findViewById(R.id.hotel_location);
            star_view = itemView.findViewById(R.id.stars);
            itemView. findViewById(R.id.hotel_price).setVisibility(View.GONE);
            itemView.findViewById(R.id.book_hotel).setVisibility(View.GONE);
        }
        private void setData(HotelData hotelData)
        {
            if(hotelImage.getMeasuredWidth() > 0 &&  hotelImage.getMeasuredHeight() > 0)
                Picasso.get().load(hotelData.getImageUrl()).centerCrop().resize(hotelImage.getMeasuredWidth(), hotelImage.getMeasuredHeight()).into(hotelImage);
            else
                Picasso.get().load(hotelData.getImageUrl()).into(hotelImage);

            hotelName.setText(hotelData.getName());
            hotelLocation.setText(hotelData.getPlaceName());
            star_view.setImageResource(HotelViewer.stars[hotelData.getStar() - 1]);
        }
    }
}
