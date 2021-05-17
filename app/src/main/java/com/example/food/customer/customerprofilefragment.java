package com.example.food.customer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.food.Customer;
import com.example.food.MainMenu;
import com.example.food.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class customerprofilefragment extends Fragment {
    private final String[] Gujarat = {"Surat", "Baroda", "Anand", "Nadiad", "Ahemdabad", "Rajkot", "Bharuch"};
    private final String[] Punjab = {"Amritsar", "Chandigadh", "Ludhiana", "Jalandar", "Patiala"};
    private EditText firstname, address;
    private TextView State, City;
    private TextView mobileno, Email;
    private Button Update;
    private TextView passwordb;
    LinearLayout password, LogOut;
    DatabaseReference databaseReference, data;
    FirebaseDatabase firebaseDatabase;
    private String statee, cityy, email, passwordd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Profile");
        View v = inflater.inflate(R.layout.cust_fragment_profile, null);
        firstname = (EditText) v.findViewById(R.id.fnamee);
        passwordb=(TextView)v.findViewById(R.id.Passwordd);
        // lastname = (EditText) v.findViewById(R.id.lnamee);
        address = (EditText) v.findViewById(R.id.address);
        Email = (TextView) v.findViewById(R.id.emailID);
        State = (TextView) v.findViewById(R.id.State);
        City = (TextView) v.findViewById(R.id.cityy);
        //Suburban = (Spinner) v.findViewById(R.id.sub);
        mobileno = (TextView) v.findViewById(R.id.mobilenumber);
        // Update = (Button) v.findViewById(R.id.update);
        password = (LinearLayout) v.findViewById(R.id.passwordlayout);
        LogOut = (LinearLayout) v.findViewById(R.id.logout_layout);
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Are you sure you want to Logout ?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(getActivity(), MainMenu.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);


                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();


            }

        });

        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Customer").child(userid);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final Customer customer = dataSnapshot.getValue(Customer.class);
                String fN = dataSnapshot.child("Full Name").getValue().toString();
                String mobile = dataSnapshot.child("Mobile No").getValue().toString();
                String em = dataSnapshot.child("EmailId").getValue().toString();
                String add = dataSnapshot.child("Address").getValue().toString();
                String citys = dataSnapshot.child("City").getValue().toString();
                String states = dataSnapshot.child("State").getValue().toString();
                String pass = dataSnapshot.child("Password").getValue().toString();


                firstname.setText(fN);
                mobileno.setText(mobile);
                Email.setText(em);
                address.setText(add);
               passwordb.setText("Password:       "+pass);
//                firstname.setText(customer.getFirstName());
//                //lastname.setText(customer.getLastName());
//               // address.setText(customer.getLocalAddress());
//                mobileno.setText(customer.getMobileno());
//                Email.setText(customer.getEmailID());
                City.setText(citys);
                State.setText(states);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return v;
    }

}

