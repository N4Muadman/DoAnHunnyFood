package com.example.doanhunnyfood.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanhunnyfood.R;
import com.example.doanhunnyfood.entity.OrderView;

import java.util.List;

public class UnpaidOrderAdapter extends RecyclerView.Adapter<UnpaidOrderAdapter.UnpaidOrderViewHolder> {

    private List<OrderView> orderViewList;

    public UnpaidOrderAdapter(List<OrderView> orderViewList) {
        this.orderViewList = orderViewList;
    }

    @NonNull
    @Override
    public UnpaidOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyc_unpaid_order, parent, false);
        return new  UnpaidOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UnpaidOrderViewHolder holder, int position) {
        if (orderViewList != null){
            holder.imgFood.setImageResource(orderViewList.get(position).getFoodImg());
            holder.txtFoodName.setText(orderViewList.get(position).getFoodName());
            holder.txtPrice.setText(orderViewList.get(position).getPrice() +" đ");
            holder.txtQtt.setText("Số lượng: " + orderViewList.get(position).getQuantity());
            holder.txtTotal.setText("Tổng: "+orderViewList.get(position).getTotalFood());
        }
    }

    @Override
    public int getItemCount() {
        if (orderViewList != null){
            return orderViewList.size();
        }
        return 0;
    }

    public class UnpaidOrderViewHolder extends RecyclerView.ViewHolder {
        private TextView txtFoodName, txtPrice, txtQtt, txtTotal;
        private ImageView imgFood;

        public UnpaidOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.imgFood);
            txtFoodName = itemView.findViewById(R.id.txtNameFood);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtQtt = itemView.findViewById(R.id.txtQtt);
            txtTotal = itemView.findViewById(R.id.txtTotal);
        }
    }
}
