package com.example.doanhunnyfood.ui.UnpainOrder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doanhunnyfood.adapter.UnpaidOrderAdapter;
import com.example.doanhunnyfood.databinding.FragmentUnpaidOrderDetailBinding;
import com.example.doanhunnyfood.entity.OrderView;

import java.util.ArrayList;
import java.util.List;

public class UnpaidOrderDetailFragment extends Fragment {

    private UnpaidOrderDetailViewModel mViewModel;
    private List<OrderView> orderViewList;
    private FragmentUnpaidOrderDetailBinding binding;
    private UnpaidOrderAdapter adapter;

    public static UnpaidOrderDetailFragment newInstance(List<OrderView> orderViewList) {
        UnpaidOrderDetailFragment fragment = new UnpaidOrderDetailFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("order_views", new ArrayList<>(orderViewList));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentUnpaidOrderDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerView.setLayoutManager(layoutManager);
        if (getArguments() != null) {
            orderViewList = getArguments().getParcelableArrayList("order_views");
            updateUI();
        } else {
            // Handle case when getArguments() is null
            binding.txtNameTable.setText("Arguments are null");
        }
    }

    private void updateUI() {
        if (orderViewList != null && !orderViewList.isEmpty()) {
            adapter = new UnpaidOrderAdapter(orderViewList);
            binding.recyclerView.setAdapter(adapter);
            binding.txtNameTable.setText("OrderViewList size: " + orderViewList.size());
        } else {
            // Handle case when orderViewList is null or empty
            binding.txtNameTable.setText("OrderViewList is null or empty");
        }
    }
}