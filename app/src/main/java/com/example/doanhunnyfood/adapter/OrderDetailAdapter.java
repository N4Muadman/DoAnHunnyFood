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

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.OrderDetailViewHolder>{

    private List<OrderView> orderViewList;

    public OrderDetailAdapter(List<OrderView> orderViewList) {
        this.orderViewList = orderViewList;
    }

    @NonNull
    @Override
    public OrderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyc_unpaid_order, parent, false);
        return new OrderDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailViewHolder holder, int position) {
        if (orderViewList != null){
            holder.imgFood.setImageResource(orderViewList.get(position).getFoodImg());
            holder.txtFoodName.setText(orderViewList.get(position).getFoodName());
            holder.txtPrice.setText(String.format("%.3f", orderViewList.get(position).getPrice()) + " đ");
            holder.txtQtt.setText("Số lượng: " + orderViewList.get(position).getQuantity());
            holder.txtTotal.setText("Tổng: "+ String.format("%.3f", orderViewList.get(position).getTotalFood()) + " đ");
        }
    }

    @Override
    public int getItemCount() {
        if (orderViewList != null){
            return orderViewList.size();
        }
        return 0;
    }

    public void setOrderViewList(List<OrderView> orderViewList) {
        this.orderViewList = orderViewList;
        notifyDataSetChanged();
    }

    public class OrderDetailViewHolder extends RecyclerView.ViewHolder {
        private TextView txtFoodName, txtPrice, txtQtt, txtTotal;
        private ImageView imgFood;
        public OrderDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.imgFood);
            txtFoodName = itemView.findViewById(R.id.txtNameFood);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtQtt = itemView.findViewById(R.id.txtQtt);
            txtTotal = itemView.findViewById(R.id.txtTotal);
        }
    }
}
