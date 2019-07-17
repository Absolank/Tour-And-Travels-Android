package com.miniproject.tourandtravels.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.miniproject.tourandtravels.R;
import com.miniproject.tourandtravels.api.ResponseCallback;
import com.miniproject.tourandtravels.api.TourAndTravelsRepository;
import com.miniproject.tourandtravels.api.model.Status;
import com.miniproject.tourandtravels.api.model.User;

public class CreateAccountFragment extends Fragment {
    private EditText username, password, firstname, lastname, email;
    private Button button;
    private ResponseCallback responseCallback;

    public void setResponseCallback(ResponseCallback responseCallback) {
        this.responseCallback = responseCallback;
    }
    TourAndTravelsRepository tourAndTravelsRepository = new TourAndTravelsRepository();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.create_new_account, container, false);

        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        firstname = view.findViewById(R.id.firstname);
        lastname = view.findViewById(R.id.lastname);
        email = view.findViewById(R.id.email);

        button = view.findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                responseCallback.callback(null);
            }
        });

        Button button1 = view.findViewById(R.id.create_account);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(0, username.getText().toString(), firstname.getText().toString(), lastname.getText().toString(), email.getText().toString(), null);
                tourAndTravelsRepository.addUser(user, password.getText().toString(), new ResponseCallback() {
                    @Override
                    public void callback(Object param) {
                        Status status = (Status)param;
                        if(status.Code == 200){
                            responseCallback.callback(null);
                        }
                    }
                });
            }
        });

        return  view;
    }
}
