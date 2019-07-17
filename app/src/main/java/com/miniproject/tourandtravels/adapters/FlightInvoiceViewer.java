package com.miniproject.tourandtravels.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miniproject.tourandtravels.R;
import com.miniproject.tourandtravels.api.model.FlightInvoice;
import com.miniproject.tourandtravels.util.TimeConverter;

import java.util.Date;
import java.util.List;

public class FlightInvoiceViewer extends RecyclerView.Adapter<FlightInvoiceViewer.FlightInvoiceViewHolder> {


    private List<FlightInvoice> flightInvoiceList;

    public void setFlightInvoiceList(List<FlightInvoice> flightInvoiceList) {
        this.flightInvoiceList = flightInvoiceList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FlightInvoiceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new FlightInvoiceViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.flight_invoice, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FlightInvoiceViewHolder viewHolder, int i) {
        FlightInvoice flightInvoice = flightInvoiceList.get(i);
        viewHolder.setData(flightInvoice);
    }

    @Override
    public int getItemCount() {
        if(flightInvoiceList != null)
            return flightInvoiceList.size();
        return 0;
    }

    class FlightInvoiceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView FlightID, SourceCity, DestinationCity, TotalPrice, DepWeek, DepDay, DepMonth, Transaction, BasePrice, Taxes, NumPersons;

        FlightInvoiceViewHolder(@NonNull View itemView) {
            super(itemView);
            FlightID = itemView.findViewById(R.id.flight_id);
            SourceCity = itemView.findViewById(R.id.source_city);
            DestinationCity = itemView.findViewById(R.id.destination_city);
            TotalPrice = itemView.findViewById(R.id.total_price);
            DepWeek = itemView.findViewById(R.id.dep_week);
            DepDay = itemView.findViewById(R.id.dep_day);
            DepMonth = itemView.findViewById(R.id.dep_month);
            Transaction = itemView.findViewById(R.id.transaction_id);
            BasePrice = itemView.findViewById(R.id.base_price);
            Taxes = itemView.findViewById(R.id.taxes);
            NumPersons = itemView.findViewById(R.id.numPerson);
//            itemView.findViewById(R.id.textView23).setVisibility(View.GONE);
            itemView.findViewById(R.id.textView30).setVisibility(View.GONE);
            itemView.findViewById(R.id.textView17).setVisibility(View.GONE);
//            itemView.findViewById(R.id.textView20).setVisibility(View.GONE);
//            itemView.findViewById(R.id.textView18).setVisibility(View.GONE);
//            itemView.findViewById(R.id.base_price).setVisibility(View.GONE);
//            itemView.findViewById(R.id.taxes).setVisibility(View.GONE);

            itemView.findViewById(R.id.flight_cost).setVisibility(View.INVISIBLE);
            itemView.findViewById(R.id.departure_time).setVisibility(View.INVISIBLE);
            itemView.findViewById(R.id.arrival_time).setVisibility(View.INVISIBLE);
            itemView.findViewById(R.id.travel_time).setVisibility(View.INVISIBLE);
            itemView.findViewById(R.id.book_flight).setVisibility(View.GONE);
        }
        void setData(FlightInvoice flightInvoice)
        {
            String string = flightId + String.valueOf(flightInvoice.getFlightID());
            FlightID.setText(string);
            SourceCity.setText(flightInvoice.getSourceCode());
            DestinationCity.setText(flightInvoice.getDestinationCode());
            String ruppe = "₹ " + flightInvoice.getAmount();
            TotalPrice.setText(ruppe);
            BasePrice.setText(ruppe);
            Taxes.setText("₹ 0");
            NumPersons.setText("Number of person = " + flightInvoice.getNumPerson());

            try {
                Date date = TimeConverter.dmy.parse(flightInvoice.getDeparture());
                date.getTime();
                DepWeek.setText(TimeConverter.week.format(date));
                DepMonth.setText(TimeConverter.month.format(date));
                DepDay.setText(TimeConverter.day.format(date));
            }catch (Exception e){}


            String trans = "Transaction ID : " + flightInvoice.getTransactionID() + "   Date : " + flightInvoice.getTransactionDate();
            Transaction.setText(trans);
        }

        @Override
        public void onClick(View v) {

        }
    }
    private  static  final String flightId = "Flight ID: ";
}
