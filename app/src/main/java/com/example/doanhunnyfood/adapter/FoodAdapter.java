package com.example.doanhunnyfood.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanhunnyfood.R;
import com.example.doanhunnyfood.entity.Food;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.DishViewHolder> {
    private List<Food> foodList;
    private static ItemClickListener itemClickListener;

    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public FoodAdapter(List<Food> foodList) {
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyc_food_item, parent, false);
        return new DishViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DishViewHolder holder, int position) {
        if (foodList != null){
            holder.imgDish.setImageResource(foodList.get(position).image);
            holder.txtName.setText(foodList.get(position).name);
            holder.txtPrice.setText(String.format("%.3f", foodList.get(position).price) + " đồng");
        }else holder.txtName.setText("No Dish");
    }

    @Override
    public int getItemCount() {
        if (foodList ==null)
            return 0;
        return foodList.size();
    }
    public Food getItem(int position) {
        if (foodList != null && position >= 0 && position < foodList.size()) {
            return foodList.get(position);
        }
        return null;
    }
    public void setDishList(List<Food> foods){
        foodList = foods;
        notifyDataSetChanged();

    }

    public class DishViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgDish;
        private TextView txtName, txtPrice;
        public DishViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDish = itemView.findViewById(R.id.imgDish);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null){
                        itemClickListener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
