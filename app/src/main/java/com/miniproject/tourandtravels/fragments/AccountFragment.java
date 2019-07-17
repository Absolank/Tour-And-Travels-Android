package com.miniproject.tourandtravels.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.miniproject.tourandtravels.LoginActivity;
import com.miniproject.tourandtravels.R;
import com.miniproject.tourandtravels.api.ResponseCallback;
import com.miniproject.tourandtravels.api.TourAndTravelsRepository;
import com.miniproject.tourandtravels.api.model.PackageInvoice;
import com.miniproject.tourandtravels.api.model.Status;
import com.miniproject.tourandtravels.api.model.User;

public class AccountFragment extends Fragment {
    private SharedPreferences sharedPreferences;
    private TourAndTravelsRepository tourAndTravelsRepository;
    private String token, pass;
    private User user;
    private boolean temp = true;
    private EditText email, firstname, lastname, password;
    private TextView t_username, t_email, t_firstname, t_lastname;
    private Button cancel;
    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setTourAndTravelsRepository(TourAndTravelsRepository tourAndTravelsRepository) {
        this.tourAndTravelsRepository = tourAndTravelsRepository;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.account_info, container, false);

        email = view.findViewById(R.id.email);
        firstname = view.findViewById(R.id.firstname);
        lastname = view.findViewById(R.id.lastname);
        password = view.findViewById(R.id.password);
        t_email = view.findViewById(R.id.t_email);
        t_firstname = view.findViewById(R.id.t_firstname);
        t_lastname = view.findViewById(R.id.t_lastname);
        t_username = view.findViewById(R.id.t_username);

        int id = sharedPreferences.getInt("UserID", 0);
        String  username = sharedPreferences.getString("Username", "");
        String  firstName = sharedPreferences.getString("FirstName", "");
        String  lastName= sharedPreferences.getString("LastName", "");
        String  em = sharedPreferences.getString("Email", "");
        user = new User(id, username, firstName, lastName, em, token);

        t_username.setText(user.getUsername());


        setData();

        Button edit = view.findViewById(R.id.edit);
        cancel = view.findViewById(R.id.cancel);
        enabled(View.GONE);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText("Edit");
                enabled(View.GONE);
                temp = true;
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(temp){
                    edit.setText("Save");
                    enabled(View.VISIBLE);
                    temp = false;
                }else{
                    String fname = firstname.getText().toString();
                    String lname = lastname.getText().toString();
                    String em = email.getText().toString();
                    pass = password.getText().toString();
                    firstname.setText("");
                    lastname.setText("");
                    email.setText("");
                    if(!fname.equals("")){
                        user.setFirstName(fname);
                    }
                    if(!lname.equals("")){
                        user.setLastName(lname);
                    }
                    if(!em.equals("")){
                        user.setEmail(em);
                    }
                    if(pass.equals("")){
                       pass = null;
                    }
                    tourAndTravelsRepository.updateUser(user, pass, token , new ResponseCallback(){
                        @Override
                        public void callback(Object param) {
                            Status status = (Status)param;
                            if(status.Code == 200) {
                                sharedPreferences.edit()
                                        .putInt("UserID", user.getID())
                                        .putString("Username", user.getUsername())
                                        .putString("FirstName", user.getFirstName())
                                        .putString("LastName", user.getLastName())
                                        .putString("Email", user.getEmail())
                                        .putString("Token", user.getToken())
                                        .apply();
                                setData();
                            }
                            if(pass != null){
                                sharedPreferences.edit().clear().apply();
                                Intent intent = new Intent(getContext(), LoginActivity.class);
                                if(getActivity() != null) {
                                    getActivity().startActivity(intent);
                                    getActivity().finish();
                                }
                            }
                        }
                    });

                    edit.setText("Edit");
                    enabled(View.GONE);
                    temp = true;
                }
            }
        });
        return  view;
    }
    void enabled(int op){
        email.setVisibility(op);
        firstname.setVisibility(op);
        lastname.setVisibility(op);
        password.setVisibility(op);
        cancel.setVisibility(op);
    }
    private void setData() {
        t_email.setText(user.getEmail());
        t_firstname.setText(user.getFirstName());
        t_lastname.setText(user.getLastName());
    }
}
