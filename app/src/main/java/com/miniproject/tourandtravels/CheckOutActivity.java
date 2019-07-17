package com.miniproject.tourandtravels;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.miniproject.tourandtravels.api.model.Flight;
import com.miniproject.tourandtravels.fragments.BookingFragment;
import com.miniproject.tourandtravels.fragments.FlightCheckOutFragment;
import com.miniproject.tourandtravels.util.TimeConverter;

import java.util.Date;

public class CheckOutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_out);
        Intent intent = getIntent();
        if(intent != null) {
            Bundle bundle = intent.getExtras();

            if (bundle != null) {
                Intent transactionIntent = new Intent(getApplicationContext(), TransactionActivity.class);
                int type = intent.getIntExtra("type", -1);
                int id = bundle.getInt("id", 0);
                int numPerson = bundle.getInt("num-person", 1);
                int cost = bundle.getInt("cost", 0) ;

                if (type == 0) {
                    String source = bundle.getString("source-city", "");
                    String destination = bundle.getString("destination-city", "");
                    long departure = bundle.getLong("departure-date", 0);

                    FlightCheckOutFragment flightCheckOutFragment = new FlightCheckOutFragment();
                    Date date = new Date(departure);

                    String departureDate = TimeConverter.dmy.format(date);
                    transactionIntent.putExtra("departure-date", departureDate);
                    flightCheckOutFragment.setData(id, date, source, destination);
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, flightCheckOutFragment).commit();

                } if (type == 1){
                    long checkIn = bundle.getLong("check-in", 0);
                    long checkOut = bundle.getLong("check-out", 0);

                    transactionIntent.putExtra("check-in", checkIn);
                    transactionIntent.putExtra("check-out", checkOut);
                    BookingFragment fragment = new BookingFragment();

                    Date dCheckIn = new Date(checkIn);
                    Date dCheckOut = new Date(checkOut);
                    long numDay = dCheckOut.getTime() - dCheckIn.getTime();
                    numDay = numDay/(1000 * 60 * 60 * 24) + 1;
                    transactionIntent.putExtra("num-days", (int)numDay);
                    cost = cost * (int)numDay;
                    transactionIntent.putExtra("amount", cost);
                    fragment.setData("Hotel ID: ", id, (int)numDay, dCheckIn, dCheckOut);
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();

                } if (type == 2){
                    long checkIn = bundle.getLong("check-in", 0);
                    long checkOut = bundle.getLong("check-out", 0);

                    transactionIntent.putExtra("check-in", checkIn);
                    transactionIntent.putExtra("check-out", checkOut);
                    BookingFragment fragment = new BookingFragment();

                    Date dCheckIn = new Date(checkIn);
                    Date dCheckOut = new Date(checkOut);
                    long numDay = dCheckOut.getTime() - dCheckIn.getTime();
                    numDay = numDay/(1000 * 60 * 60 * 24) + 1;
                    transactionIntent.putExtra("num-days", (int)numDay);
                    transactionIntent.putExtra("amount", cost);
                    fragment.setData("Package ID: ", id, (int)numDay, dCheckIn, dCheckOut);
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();
                }

                else {
//                    finish();
                }
                TextView totalPrice, taxes, basePrice;
                totalPrice = findViewById(R.id.total_price);
                taxes = findViewById(R.id.taxes);
                basePrice = findViewById(R.id.base_price);

                taxes.setText(rupee + String.valueOf(0));
                String c = rupee + String.valueOf(cost * numPerson) + ".00";
                basePrice.setText(c);
                totalPrice.setText(c);

                Button button = findViewById(R.id.proceed_to_pay);
                button.setOnClickListener(v -> {

                    transactionIntent.putExtra("type", type);
                    transactionIntent.putExtra("id", id);
                    transactionIntent.putExtra("num-person", numPerson);
                    startActivity(transactionIntent);
                    finish();
                });
            }
        }
    }
    private static final String  rupee = "₹ ";
}
