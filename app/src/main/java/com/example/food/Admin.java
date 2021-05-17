package com.example.food;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.food.Adminorder.FragmentAdmin;
import com.example.food.Adminorder.fragment_res_pending;
import com.example.food.Adminorder.fragment_res_profile;
import com.example.food.Adminorder.res_order_fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Admin extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        BottomNavigationView navigationView = findViewById(R.id.chef_bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        String name=getIntent().getStringExtra("Page");
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        if(name!=null){
            if(name.equalsIgnoreCase("OrderPage")){
                loadcheffragment(new fragment_res_pending());
            }
            else if(name.equalsIgnoreCase("ConfirmPage")){
                loadcheffragment(new res_order_fragment());
            }
            else if(name.equalsIgnoreCase("AcceptOrderPage")){
                loadcheffragment(new res_order_fragment());
            }
            else if(name.equalsIgnoreCase("DeliverePage")){
                loadcheffragment(new res_order_fragment());
            }
        }
        else {
            loadcheffragment(new FragmentAdmin());
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.Home:
                fragment=new FragmentAdmin();
                break;
            case R.id.PendingOrders:
                fragment=new fragment_res_pending();
                break;
            case R.id.Orders:
                fragment=new res_order_fragment();
                break;
            case R.id.post:
                fragment=new fragment_res_profile();
                break;
        }
        return loadcheffragment(fragment);
    }

    private boolean loadcheffragment(Fragment fragment) {
        if (fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,fragment).commit();
            return true;
        }
        return false;
    }
}