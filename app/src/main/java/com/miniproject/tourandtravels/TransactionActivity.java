package com.miniproject.tourandtravels;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.miniproject.tourandtravels.api.ResponseCallback;
import com.miniproject.tourandtravels.api.TourAndTravelsRepository;
import com.miniproject.tourandtravels.api.model.Status;
import com.miniproject.tourandtravels.util.TimeConverter;

import java.util.Date;
import java.util.Map;

public class TransactionActivity extends AppCompatActivity {
    private ConstraintLayout paymentLayout;
    private ProgressBar progressBar;
    private EditText accountHolderName, cardNumber, expiration, cvv;
    private TextView amount, transactionNumber;
    private int temp = 0;
    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);
        setTitle("Payment");
        Intent intent = getIntent();


        paymentLayout = findViewById(R.id.payment_layout);
        progressBar = findViewById(R.id.progressBar);
        amount = findViewById(R.id.amount);
        transactionNumber = findViewById(R.id.transaction_number);
        accountHolderName = findViewById(R.id.account_holder_name);
        cardNumber = findViewById(R.id.card_number);
        expiration = findViewById(R.id.expiration_date);
        cvv = findViewById(R.id.cvv);
        if(intent != null) {
            int cost = intent.getIntExtra("amount", 0);
            int numPerson = intent.getIntExtra("num-person", 0);
            String c = "Rs. " + cost;
            amount.setText(c);
            int id = intent.getIntExtra("id", 0);
            int type = intent.getIntExtra("type", -1);

            Button pay = findViewById(R.id.pay);
            pay.setOnClickListener(v -> {
                if (accountHolderName.getText().toString().length() == 0) {
                    temp++;
                    accountHolderName.setError("Please enter Account holder's name");
                }
                if (cardNumber.getText().toString().length() != 16) {
                    temp++;
                    cardNumber.setError("Please enter valid Card Number");
                }
                if (cvv.getText().toString().length() != 3) {
                    temp++;
                    cvv.setError("Please enter valid CVV");
                }
                SharedPreferences sharedPreferences = getSharedPreferences("login-data",MODE_PRIVATE);
                String token = sharedPreferences.getString("Token", "");
                if (temp == 0) {
                    hideLayout();
                    TourAndTravelsRepository tourAndTravelsRepository = new TourAndTravelsRepository();
                    if(type == 0) {
                        String departureDate = intent.getStringExtra("departure-date");
                        bookFlight(tourAndTravelsRepository, token, id, numPerson, departureDate);
                    }
                    else if (type == 1){
                        long checkIn = intent.getLongExtra("check-in", 0);
                        long checkOut = intent.getLongExtra("check-out", 0);
                        int numDays = intent.getIntExtra("num-days", 0);
                        bookHotel(
                                tourAndTravelsRepository,
                                token,
                                id,
                                numPerson,
                                numDays,
                                TimeConverter.dmy.format(new Date(checkIn)),
                                TimeConverter.dmy.format(new Date(checkOut))
                        );
                    }
                    else if (type == 2){
                        long checkIn = intent.getLongExtra("check-in", 0);
                        long checkOut = intent.getLongExtra("check-out", 0);
                        int numDays = intent.getIntExtra("num-days", 0);
                        bookPackage(
                                tourAndTravelsRepository,
                                token,
                                id,
                                numPerson,
                                numDays,
                                TimeConverter.dmy.format(new Date(checkIn)),
                                TimeConverter.dmy.format(new Date(checkOut))
                        );
                    }
                } else {
                    temp = 0;
                    showLayout();
                }
            });
        }
    }
    private void bookFlight(TourAndTravelsRepository tourAndTravelsRepository, String token, int id, int numPerson, String departureDate){
        tourAndTravelsRepository.bookFlight(
                token,
                id,
                numPerson,
                departureDate,
                cardNumber.getText().toString(),
                cvv.getText().toString(),
                accountHolderName.getText().toString(),
                param -> {
                    Status status = (Status)param;
                    if(status.Code == 400)
                    {
                        System.out.println();
                    }
                    else {
                        System.out.println();
                    }
                    finish();
                });
    }
    private void bookHotel(TourAndTravelsRepository tourAndTravelsRepository, String token, int id, int numPerson, int numDay, String checkInDate, String checkOutDate){
        tourAndTravelsRepository.bookHotel(
                token,
                id,
                numPerson,
                numDay,
                checkInDate,
                checkOutDate,
                cardNumber.getText().toString(),
                cvv.getText().toString(),
                accountHolderName.getText().toString(),
                param->{
                    Status status = (Status)param;
                    if(status == null)
                    {
                        finish();
                        return;
                    }
                    if(status.Code == 400)
                    {
                        System.out.println();
                    }
                    else {
                        System.out.println();
                    }
                    finish();
                }
        );
    }
    private void bookPackage(TourAndTravelsRepository tourAndTravelsRepository, String token, int id, int numPerson, int numDay, String checkInDate, String checkOutDate){
        tourAndTravelsRepository.bookPackage(
                token,
                id,
                numPerson,
                numDay,
                checkInDate,
                checkOutDate,
                cardNumber.getText().toString(),
                cvv.getText().toString(),
                accountHolderName.getText().toString(),
                param->{
                    Status status = (Status)param;
                    if(status == null)
                    {
                        finish();
                        return;
                    }
                    if(status.Code == 400)
                    {
                        System.out.println();
                    }
                    else {
                        System.out.println();
                    }
                    finish();
                }
        );
    }
    private void showLayout() {
        progressBar.setVisibility(View.GONE);
        paymentLayout.setVisibility(View.VISIBLE);
    }
    private void hideLayout(){
        paymentLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }
}
