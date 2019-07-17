package com.miniproject.tourandtravels.adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.miniproject.tourandtravels.CheckOutActivity;
import com.miniproject.tourandtravels.R;
import com.miniproject.tourandtravels.api.model.Flight;
import com.miniproject.tourandtravels.api.model.FlightData;
import com.miniproject.tourandtravels.api.model.TourAndFlights;

import java.util.List;


public class FlightViewer extends RecyclerView.Adapter<FlightViewer.FlightViewHolder> {

    private TourAndFlights tourAndFlights;
    private boolean isMinimal;
    private Typeface typeface;
    public FlightViewer(boolean isMinimal, Activity activity)
    {
        super();
        this.typeface = ResourcesCompat.getFont(activity.getApplicationContext(), R.font.amita);
        this.isMinimal = isMinimal;
        this.activity = activity;
    }
    private Activity activity;
    private String source, destination;
    private long departure;
    private int numPerson;
    public void setData(String source, String destination, int numPerson, long departure){
        this.source = source;
        this.numPerson = numPerson;
        this.destination = destination;
        this.departure = departure;
    }
    public void setTourAndFlights(TourAndFlights tourAndFlights) {
        this.tourAndFlights = tourAndFlights;
        notifyDataSetChanged();
    }
    private List<FlightData> flights;
    public void setFlights(List<FlightData> flights)
    {
        this.flights = flights;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new FlightViewHolder(
                LayoutInflater.from(
                        viewGroup.getContext()
                ).inflate(
                        R.layout.flight_item,
                        viewGroup,
                        false));
    }

    @Override
    public void onBindViewHolder(@NonNull FlightViewHolder flightViewHolder, int i) {
        if(isMinimal)
            flightViewHolder.setData(flights.get(i));
        else
            flightViewHolder.setData(tourAndFlights.getFlights().get(i));
    }

    @Override
    public int getItemCount() {
        if(isMinimal && flights != null)
            return flights.size();
        if(tourAndFlights != null && tourAndFlights.getFlights() != null)
            return  tourAndFlights.getFlights().size();
        return 0;
    }

    class FlightViewHolder extends RecyclerView.ViewHolder{

        private TextView FlightID, Cost, DepartureTime, ArrivalTime, SourceCity, DestinationCity, TravelTime;
        private Button BookFlight;

        private Flight flight;

        FlightViewHolder(@NonNull View itemView) {
            super(itemView);


            FlightID = itemView.findViewById(R.id.flight_id);
            Cost = itemView.findViewById(R.id.flight_cost);
            SourceCity = itemView.findViewById(R.id.source_city);
            DestinationCity = itemView.findViewById(R.id.destination_city);
            DepartureTime = itemView.findViewById(R.id.departure_time);
            ArrivalTime = itemView.findViewById(R.id.arrival_time);
            TravelTime = itemView.findViewById(R.id.travel_time);
            BookFlight = itemView.findViewById(R.id.book_flight);

//            FlightID.setTypeface(typeface);
//            Cost.setTypeface(typeface);
//            SourceCity.setTypeface(typeface);
//            DestinationCity.setTypeface(typeface);
//            DepartureTime.setTypeface(typeface);
//            ArrivalTime.setTypeface(typeface);
//            TravelTime.setTypeface(typeface);
//            BookFlight.setTypeface(typeface);

            BookFlight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity.getApplicationContext(), CheckOutActivity.class);
                    intent.putExtra("type", 0);
                    intent.putExtra("source-city", source);
                    intent.putExtra("destination-city", destination);
                    intent.putExtra("id", flight.getID());
                    intent.putExtra("cost", flight.getCost());
                    intent.putExtra("num-person", numPerson);
                    intent.putExtra("departure-date", departure);
                    activity.startActivity(intent);
                }
            });
            if(isMinimal)
            {
//                DepartureTime.setVisibility(View.INVISIBLE);
                ArrivalTime.setVisibility(View.INVISIBLE);
                TravelTime.setVisibility(View.INVISIBLE);
                Cost.setVisibility(View.INVISIBLE);
                BookFlight.setVisibility(View.INVISIBLE);
            }
        }
        void setData(Flight flight){
            this.flight = flight;

            String string = flightId + String.valueOf(flight.getID());
            FlightID.setText(string);
            String cost = rupee + flight.getCost() + perPerson;
            Cost.setText(cost);
            DepartureTime.setText(flight.getDeparture().substring(0, 5));
            TravelTime.setText(flight.getTravelTime().substring(0, 5));
            SourceCity.setText(tourAndFlights.getTour().getSourceCode());
            DestinationCity.setText(tourAndFlights.getTour().getDestinationCode());
        }
        void setData(FlightData flightData)
        {
            String string = flightId + String.valueOf(flightData.getID());
            FlightID.setText(string);
            DepartureTime.setText(flightData.getDeparture().substring(0, 5));
            SourceCity.setText(flightData.getSourceCode());
            DestinationCity.setText(flightData.getDestinationCode());
        }
    }
    private  static  final String flightId = "Flight ID: ";
    private static final String  rupee = "₹ ";
    private static final String perPerson = " / Per Person";
}
