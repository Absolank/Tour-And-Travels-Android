package com.miniproject.tourandtravels;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.miniproject.tourandtravels.adapters.FlightInvoiceViewer;
import com.miniproject.tourandtravels.adapters.HotelInvoiceViewer;
import com.miniproject.tourandtravels.adapters.PackageInvoiceViewer;
import com.miniproject.tourandtravels.api.ResponseCallback;
import com.miniproject.tourandtravels.api.TourAndTravelsRepository;
import com.miniproject.tourandtravels.api.model.Flight;
import com.miniproject.tourandtravels.api.model.FlightInvoice;
import com.miniproject.tourandtravels.api.model.Hotel;
import com.miniproject.tourandtravels.api.model.HotelInvoice;
import com.miniproject.tourandtravels.api.model.PackageInvoice;
import com.miniproject.tourandtravels.fragments.AccountFragment;
import com.miniproject.tourandtravels.fragments.FlightFragment;
import com.miniproject.tourandtravels.fragments.HotelFragment;
import com.miniproject.tourandtravels.fragments.Invoice;
import com.miniproject.tourandtravels.fragments.TravelPackageFragment;

import java.util.List;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TourAndTravelsRepository repository = new TourAndTravelsRepository();
    SharedPreferences sharedPreferences;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FlightFragment flightFragment = new FlightFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, flightFragment).commit();

        sharedPreferences = getSharedPreferences("login-data", MODE_PRIVATE);
        token = sharedPreferences.getString("Token", "");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_account) {
            setTitle("Account Info");

            AccountFragment accountFragment = new AccountFragment();
            accountFragment.setToken(token);
            accountFragment.setSharedPreferences(sharedPreferences);
            accountFragment.setTourAndTravelsRepository(repository);
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, accountFragment).commit();

        } else if (id == R.id.nav_flight) {
            setTitle("Flights");

            FlightFragment flightFragment = new FlightFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, flightFragment).commit();
        } else if (id == R.id.nav_hotel) {
            setTitle("Hotels");

            HotelFragment hotelFragment = new HotelFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, hotelFragment).commit();
        } else if (id == R.id.nav_package) {
            setTitle("Holiday Package");
            TravelPackageFragment travelPackageFragment = new TravelPackageFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, travelPackageFragment).commit();

        } else if (id == R.id.nav_invoice) {
            flightInvoice();
            setTitle("Flight Invoice");

        }
        else if (id == R.id.nav_hinvoice) {
            hotelInvoice();
            setTitle("Hotel Invoice");
        }
        else if (id == R.id.nav_pinvoice) {
            packageInvoice();
            setTitle("Package Invoice");

        }
        else if (id == R.id.nav_logout) {
            sharedPreferences.edit().clear().apply();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void flightInvoice(){
        Invoice invoice = new Invoice();
        String token = sharedPreferences.getString("Token", "");
        FlightInvoiceViewer flightInvoiceViewer = new FlightInvoiceViewer();
        invoice.setAdapter(flightInvoiceViewer);
        repository.getFlightInvoice(token, new ResponseCallback() {
            @Override
            public void callback(Object param) {
                List<FlightInvoice> flightInvoice = (List<FlightInvoice>)param;
                if(flightInvoice != null){
                    flightInvoiceViewer.setFlightInvoiceList(flightInvoice);
                }
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, invoice).commit();
    }
    private void hotelInvoice(){
        Invoice invoice = new Invoice();
        String token = sharedPreferences.getString("Token", "");
        HotelInvoiceViewer hotelInvoiceViewer = new HotelInvoiceViewer();
        invoice.setAdapter(hotelInvoiceViewer);
        repository.getHotelInvoice(token, new ResponseCallback() {
            @Override
            public void callback(Object param) {
                List<HotelInvoice> hotelInvoice = (List<HotelInvoice>)param;
                if(hotelInvoice != null){
                    hotelInvoiceViewer.setHotelInvoice(hotelInvoice);
                }
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, invoice).commit();
    }
    private void packageInvoice(){
        Invoice invoice = new Invoice();
        String token = sharedPreferences.getString("Token", "");
        PackageInvoiceViewer packageInvoiceViewer = new PackageInvoiceViewer();
        invoice.setAdapter(packageInvoiceViewer);
        repository.getPackageInvoice(token, new ResponseCallback() {
            @Override
            public void callback(Object param) {
                List<PackageInvoice> packageInvoice = (List<PackageInvoice>)param;
                if(packageInvoice != null){
                    packageInvoiceViewer.setPackageInvoice(packageInvoice);
                }
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, invoice).commit();
    }
}
