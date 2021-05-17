package com.example.food;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.food.customer.CustomerHomeFragment;
import com.example.food.customer.customer_fragment_track;
import com.example.food.customer.customercartfragement;
import com.example.food.customer.customerfragmentorder;
import com.example.food.customer.customerprofilefragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;

public class customer_panel extends AppCompatActivity implements OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_panel);
        BottomNavigationView navigationView = findViewById(R.id.cust_bottom_navigation_bar);
        navigationView.setOnNavigationItemSelectedListener(this);
        String name=getIntent().getStringExtra("Page");
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        if(name!=null){
            if(name.equalsIgnoreCase("Home")){
            loadfragment(new CustomerHomeFragment());
            }
            else if(name.equalsIgnoreCase("Preparingpage")){
                loadfragment(new customer_fragment_track());

            }
            else if(name.equalsIgnoreCase("DeliveryOrderPage")){
                loadfragment(new customer_fragment_track());
            }
            else if(name.equalsIgnoreCase("Thankyou")){
                loadfragment(new CustomerHomeFragment());
            }
        }
        else {
            loadfragment(new CustomerHomeFragment());
        }
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.home:
                fragment=new CustomerHomeFragment();
                break;
        }
        switch (item.getItemId()){
            case R.id.Cart:
                fragment=new customercartfragement();
                break;
        }
        switch (item.getItemId()){
            case R.id.profile:
                fragment=new customerprofilefragment();
                break;
        }
        switch (item.getItemId()){
            case R.id.item:
                fragment=new customerfragmentorder();
                break;
        }
        switch (item.getItemId()){
            case R.id.track:
                fragment=new customer_fragment_track();
                break;
        }
        return loadfragment(fragment);

    }

    private boolean loadfragment(Fragment fragment) {

        if(fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container_view_tag,fragment).commit();
            return true;
        }
        return false;
    }


}