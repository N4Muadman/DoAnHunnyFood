package com.example.doanhunnyfood.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanhunnyfood.R;
import com.example.doanhunnyfood.entity.Order;
import com.example.doanhunnyfood.entity.Order_Table_User;
import com.example.doanhunnyfood.entity.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderManagerAdapter extends RecyclerView.Adapter<OrderManagerAdapter.OrderManagerViewHolder>{
    private List<Order_Table_User> otuList;
    private ItemClickListener itemClickListener;


    public OrderManagerAdapter(List<Order_Table_User> otuList){
        this.otuList = otuList;
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public OrderManagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyc_order_item, parent, false);

        return new OrderManagerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderManagerViewHolder holder, int position) {
        if (otuList != null){
            holder.txtUserName.setText(otuList.get(position).userName);
            holder.txtTableName.setText(otuList.get(position).tableName);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                LocalDateTime orderDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(otuList.get(position).orderTime), ZoneId.systemDefault());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedOrderTime = orderDateTime.format(formatter);
                holder.txtOrderTime.setText(formattedOrderTime);
            } else {
                // Xử lý trường hợp phiên bản SDK < O nếu cần thiết
                holder.txtOrderTime.setText("Unsupported SDK version");
            }
            if (otuList.get(position).status == 0){
                holder.txtStatus.setText("Chưa thanh toán");
                holder.txtStatus.setTextColor(Color.parseColor("#F54749"));
            }
            if (otuList.get(position).status == 1){
                holder.txtStatus.setText("Đã thanh toán");
            }
        }
    }

    @Override
    public int getItemCount() {
        if (otuList != null){
            return otuList.size();
        }
        return 0;
    }

    public void setOtuList(List<Order_Table_User> otuList) {
        this.otuList = otuList;
        notifyDataSetChanged();
    }
    public Order_Table_User getItem(int position) {
        if (otuList != null && position >= 0 && position < otuList.size()) {
            return otuList.get(position);
        }
        return null;
    }


    public class OrderManagerViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTableName, txtUserName, txtOrderTime, txtStatus;
        public OrderManagerViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTableName = itemView.findViewById(R.id.txtNameTable);
            txtUserName = itemView.findViewById(R.id.txtUserName);
            txtOrderTime = itemView.findViewById(R.id.txtOrderTime);
            txtStatus = itemView.findViewById(R.id.txtStatus);

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
