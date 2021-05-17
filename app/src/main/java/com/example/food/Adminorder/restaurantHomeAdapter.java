package com.example.food.Adminorder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food.R;

import java.util.List;

public class restaurantHomeAdapter extends RecyclerView.Adapter<restaurantHomeAdapter.ViewHolder> {
    private Context mcont;
    private List<UpdateDishModel> updateDishModelList;

    public restaurantHomeAdapter(Context mcont, List<UpdateDishModel> updateDishModelList) {
        this.mcont = mcont;
        this.updateDishModelList = updateDishModelList;
    }

    @NonNull
    @Override
    public restaurantHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcont).inflate(R.layout.admin_menu_updatedelete,parent,false);
        return new restaurantHomeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull restaurantHomeAdapter.ViewHolder holder, int position) {
    final UpdateDishModel updateDishModel=updateDishModelList.get(position);
    holder.dishes.setText(updateDishModel.getDishes());
    updateDishModel.getRandomUID();
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(mcont,update_deletedish.class);
            intent.putExtra("updatedeletedish",updateDishModel.getRandomUID());
            mcont.startActivity(intent);
        }
    });
    }

    @Override
    public int getItemCount() {
        return updateDishModelList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
TextView dishes;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dishes=itemView.findViewById(R.id.dishnames);
        }
    }
}
