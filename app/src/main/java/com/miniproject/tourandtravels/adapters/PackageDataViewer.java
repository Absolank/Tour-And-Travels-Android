package com.miniproject.tourandtravels.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.miniproject.tourandtravels.R;
import com.miniproject.tourandtravels.api.model.Flight;
import com.miniproject.tourandtravels.api.model.FlightData;
import com.miniproject.tourandtravels.api.model.Hotel;
import com.miniproject.tourandtravels.api.model.HotelData;
import com.miniproject.tourandtravels.api.model.Sight;
import com.miniproject.tourandtravels.api.model.TravelPackageData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class PackageDataViewer extends BaseAdapter {
    private Map<Integer, DayData> map;
    public void setData(TravelPackageData travelPackageData)
    {
        map = new TreeMap<>();
        for(FlightData flightData : travelPackageData.getFlights())
        {
            if(!map.containsKey(flightData.getDayNum()))
                map.put(flightData.getDayNum(), new DayData());
            map.get(flightData.getDayNum()).flightList.add(flightData);
        }
        for(Sight sight : travelPackageData.getSights())
        {
            if(!map.containsKey(sight.getDayNum()))
                map.put(sight.getDayNum(), new DayData());
            map.get(sight.getDayNum()).sightList.add(sight);
        }
        for (HotelData hotel : travelPackageData.getHotels())
        {
            if(!map.containsKey(hotel.getDayNum()))
                map.put(hotel.getDayNum(), new DayData());
            map.get((hotel.getDayNum())).hotelList.add(hotel);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(map != null)
            return map.size();
        return 0;
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holiday, parent, false);
        }
        new PackageDataViewHolder(convertView)
                .setData(position + 1, map.get(position + 1));
        return convertView;
    }

    class PackageDataViewHolder{
        FlightListViewer flightViewer = new FlightListViewer();
        HotelListViewer hotelViewer = new HotelListViewer();
        SightViewer sightViewer = new SightViewer();
        CardView flightCard, hotelCard, sightCard;
        TextView day;
        PackageDataViewHolder(@NonNull View itemView) {
            ListView flightsListView, hotelsView, sightList;

            flightsListView = itemView.findViewById(R.id.flights_list);
            flightsListView.setAdapter(flightViewer);

            hotelsView = itemView.findViewById(R.id.hotels_list);
            hotelsView.setAdapter(hotelViewer);


            sightList = itemView.findViewById(R.id.sights_list);
            sightList.setAdapter(sightViewer);

            flightCard = itemView.findViewById(R.id.flights_card);
            flightCard.setVisibility(View.GONE);

            hotelCard = itemView.findViewById(R.id.hotels_card);
            hotelCard.setVisibility(View.GONE);

            sightCard = itemView.findViewById(R.id.sights_card);
            sightCard.setVisibility(View.GONE);

            day = itemView.findViewById(R.id.day_num);

        }
        void setData(int i, DayData dayData)
        {
            String daystr = "Day " + i;
            day.setText(daystr);
            if(dayData != null) {
                if(dayData.flightList.size()  == 0)
                    flightCard.setVisibility(View.GONE);
                else {
                    flightCard.setVisibility(View.VISIBLE);
                    flightViewer.setFlightData(dayData.flightList);
                }
                if(dayData.hotelList.size()  == 0)
                    hotelCard.setVisibility(View.GONE);
                else {
                    hotelCard.setVisibility(View.VISIBLE);
                    hotelViewer.setHotelDataList(dayData.hotelList);
                }
                if(dayData.sightList.size()  == 0)
                    sightCard.setVisibility(View.GONE);
                else {
                    sightCard.setVisibility(View.VISIBLE);
                    sightViewer.setSights(dayData.sightList);
                }
            }
        }
    }

    private static class DayData{
        List<FlightData> flightList;
        List<Sight> sightList;
        List<HotelData> hotelList;
        DayData() {
            this.flightList = new ArrayList<>();
            this.sightList = new ArrayList<>();
            this.hotelList = new ArrayList<>();
        }
    }
}
