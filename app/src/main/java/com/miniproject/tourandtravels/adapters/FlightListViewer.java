package com.miniproject.tourandtravels.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.miniproject.tourandtravels.R;
import com.miniproject.tourandtravels.api.model.Flight;
import com.miniproject.tourandtravels.api.model.FlightData;

import java.util.List;

public class FlightListViewer extends BaseAdapter {
    private List<FlightData> flightDataList;

    public void setFlightData(List<FlightData> flightData) {
        this.flightDataList = flightData;
    }

    @Override
    public int getCount() {
        if(flightDataList != null)
            return flightDataList.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.flight_item, parent, false);
        }
        FlightData flightData = flightDataList.get(position);
        new FlightViewHolder(convertView).setData(flightData);
        return convertView;
    }

    private static class FlightViewHolder{

        private TextView FlightID, SourceCity, DestinationCity;
        FlightViewHolder(@NonNull View itemView) {
            FlightID = itemView.findViewById(R.id.flight_id);
            SourceCity = itemView.findViewById(R.id.source_city);
            DestinationCity = itemView.findViewById(R.id.destination_city);

            itemView.findViewById(R.id.flight_cost).setVisibility(View.INVISIBLE);
            itemView.findViewById(R.id.departure_time).setVisibility(View.INVISIBLE);
            itemView.findViewById(R.id.arrival_time).setVisibility(View.INVISIBLE);
            itemView.findViewById(R.id.travel_time).setVisibility(View.INVISIBLE);
            itemView.findViewById(R.id.book_flight).setVisibility(View.GONE);
        }
        void setData(FlightData flightData)
        {
            String string = flightId + String.valueOf(flightData.getID());
            FlightID.setText(string);
            SourceCity.setText(flightData.getSourceCode());
            DestinationCity.setText(flightData.getDestinationCode());
        }
    }
    private  static  final String flightId = "Flight ID: ";
}
