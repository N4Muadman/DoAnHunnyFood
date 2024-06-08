package com.example.doanhunnyfood.ui.order;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doanhunnyfood.R;
import com.example.doanhunnyfood.adapter.ItemClickListener;
import com.example.doanhunnyfood.adapter.OrderManagerAdapter;
import com.example.doanhunnyfood.dialog.OrderDetailDialog;
import com.example.doanhunnyfood.entity.Order;
import com.example.doanhunnyfood.entity.OrderView;
import com.example.doanhunnyfood.entity.Order_Table_User;

import java.util.ArrayList;
import java.util.List;

public class OrderManagerFragment extends Fragment {

    private OrderManagerViewModel mViewModel;
    private RecyclerView recyclerView;
    private OrderManagerAdapter adapter;
    private List<OrderView> orderViewList = new ArrayList<>();

    public static OrderManagerFragment newInstance() {
        return new OrderManagerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_manager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(OrderManagerViewModel.class);
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new OrderManagerAdapter(null);
        recyclerView.setAdapter(adapter);

        mViewModel.getOtuList().observe(getViewLifecycleOwner(), new Observer<List<Order_Table_User>>() {
            @Override
            public void onChanged(List<Order_Table_User> orderTableUsers) {
                adapter.setOtuList(orderTableUsers);
            }
        });

        mViewModel.getOrderView().observe(getViewLifecycleOwner(), new Observer<List<OrderView>>() {
            @Override
            public void onChanged(List<OrderView> orderViews) {
                orderViewList.clear();
                orderViewList.addAll(orderViews);
            }
        });

        adapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                List<OrderView> odView = new ArrayList<>();
                Order_Table_User order = adapter.getItem(position);
                for (OrderView od: orderViewList) {
                    if (order.id == od.getOrderId()){
                        odView.add(od);
                    }
                }

                OrderDetailDialog dialog = new OrderDetailDialog(getActivity(),OrderManagerFragment.this, odView);
                dialog.show();
            }
        });

    }
}