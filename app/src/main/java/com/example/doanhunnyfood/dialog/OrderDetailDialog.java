package com.example.doanhunnyfood.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanhunnyfood.R;
import com.example.doanhunnyfood.adapter.OrderDetailAdapter;
import com.example.doanhunnyfood.entity.OrderView;
import com.example.doanhunnyfood.ui.order.OrderManagerFragment;

import java.util.List;

public class OrderDetailDialog {
    private AlertDialog mDialog;
    private RecyclerView recyclerView;
    private OrderDetailAdapter adapter;


    public OrderDetailDialog(final Context context, OrderManagerFragment fragment, List<OrderView> orderView){
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_order_detail, null);
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new OrderDetailAdapter(orderView);
        recyclerView.setAdapter(adapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(view)
                .setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialog.dismiss();
                    }
                });
        mDialog = builder.create();
    }
    public void show(){
        mDialog.show();
    }
}
