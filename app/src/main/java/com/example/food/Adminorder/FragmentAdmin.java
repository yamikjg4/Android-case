package com.example.food.Adminorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.MainMenu;
import com.example.food.R;
import com.example.food.restaurant;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

//import com.example.admin.R;
//import com.example.admin.login;

//import com.example.admin.MainMenu;

public class FragmentAdmin extends Fragment {
    RecyclerView recyclerView;
    private List<UpdateDishModel>updateDishModelList;
    private restaurantHomeAdapter adapter;
    DatabaseReference dataa;
    private String state,city;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_chef_home,null);
        getActivity().setTitle("Home");
        setHasOptionsMenu(true);
        recyclerView=v.findViewById(R.id.Recycle_menu);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        updateDishModelList=new ArrayList<>();
        String userid=FirebaseAuth.getInstance().getCurrentUser().getUid();
        dataa= FirebaseDatabase.getInstance().getReference("restaurant").child(userid);
        dataa.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                restaurant restaurants=snapshot.getValue(restaurant.class);
                state=restaurants.getState();
                city=restaurants.getCity();
                restaurantdishes();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return v;

    }

    private void restaurantdishes() {
        String ueridd=FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("FoodSupplyDetails").child(state).child(city).child(ueridd);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                updateDishModelList.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    UpdateDishModel updateDishModel=snapshot1.getValue(UpdateDishModel.class);
                    updateDishModelList.add(updateDishModel);
                }
                adapter=new restaurantHomeAdapter(getContext(),updateDishModelList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.logout,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int idd=item.getItemId();
        if(idd==R.id.LogOut){
            Logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void Logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent=new Intent(getActivity(), MainMenu.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
    startActivity(intent);
    }
}