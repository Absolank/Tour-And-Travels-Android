package com.miniproject.tourandtravels.adapters;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.miniproject.tourandtravels.R;
import com.miniproject.tourandtravels.api.ResponseCallback;
import com.miniproject.tourandtravels.api.TourAndTravelsRepository;
import com.miniproject.tourandtravels.api.model.Status;
import com.miniproject.tourandtravels.api.model.User;

import java.util.List;

public class UserViewer extends RecyclerView.Adapter<UserViewer.UserViewHolder> {
    private TourAndTravelsRepository tourAndTravelsRepository = new TourAndTravelsRepository();
    private String token;
    private List<User> users;

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    public void setToken(String token) {
        this.token = token;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new UserViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.users, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        User user  = users.get(i);
        userViewHolder.setData(user);
    }

    @Override
    public int getItemCount() {
        if(users != null)
            return users.size();
        return 0;
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        boolean temp = true;
        EditText  firstname, lastname, email;
        TextView username;
        User user;
        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            firstname = itemView.findViewById(R.id.firstname);
            lastname = itemView.findViewById(R.id.lastname);
            email = itemView.findViewById(R.id.email);
            enabled(false);
            Button button = itemView.findViewById(R.id.edit);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(temp){
                        button.setText("Save");
                        enabled(true);
                        temp = false;
                    }else{
                        String fname = firstname.getText().toString();
                        String lname = lastname.getText().toString();
                        String em = email.getText().toString();
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
                        tourAndTravelsRepository.updateUser(user, null, token , new ResponseCallback(){
                            @Override
                            public void callback(Object param) {
                                Status status = (Status)param;
                                if(status.Code == 200) {
                                    email.setHint(em);
                                    lastname.setHint(lname);
                                    firstname.setHint(fname);
                                }
                            }
                        });

                        button.setText("Edit");
                        enabled(false);
                        temp = true;
                    }
                }
            });
        }
        private void setData(User user){
            this.user = user;
            username.setText(user.getUsername());
            firstname.setText(user.getFirstName());
            lastname.setText(user.getLastName());
            email.setText(user.getEmail());
        }
        private void enabled(boolean b){
            firstname.setEnabled(b);
            lastname.setEnabled(b);
            email.setEnabled(b);
        }
    }
}
