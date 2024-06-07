package com.example.doanhunnyfood.ui.UnpainOrder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doanhunnyfood.R;
import com.example.doanhunnyfood.adapter.UnpaidOrderAdapter;
import com.example.doanhunnyfood.databinding.FragmentUnpaidOrderDetailBinding;
import com.example.doanhunnyfood.entity.Order;
import com.example.doanhunnyfood.entity.OrderView;
import com.example.doanhunnyfood.ui.DinnerTable.DinnerTableFragment;

import java.util.ArrayList;
import java.util.List;

public class UnpaidOrderDetailFragment extends Fragment {

    private UnpaidOrderDetailViewModel mViewModel;

    private List<OrderView> orderViewList = new ArrayList<>();

    private List<OrderView> bufferedOrderViewList = new ArrayList<>();
    private FragmentUnpaidOrderDetailBinding binding;
    private UnpaidOrderAdapter adapter;
    private Order order;

    public static UnpaidOrderDetailFragment newInstance(Order order) {
        UnpaidOrderDetailFragment fragment = new UnpaidOrderDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("Order", order);
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

        mViewModel = new ViewModelProvider(this).get(UnpaidOrderDetailViewModel.class);

        mViewModel.getAllOrderView().observe(getActivity(), orderViews -> {
            Log.d("UnpaidOrderDetailFragment", "Order views: " + orderViews);
            orderViewList.clear();
            orderViewList.addAll(orderViews);
            if (getArguments() != null) {
            order = getArguments().getParcelable("Order");
                // Sử dụng dữ liệu bàn ở đây
                if (order != null) {
                    double total = 0;
                    List<OrderView> orderViews1 = new ArrayList<>();
                    for (OrderView od: orderViewList) {
                        if (od.getOrderId() == order.id){
                            orderViews1.add(od);
                            total = total + od.getTotalFood();
                            binding.txtNameTable.setText(od.getTableName());
                        }
                    }
                    binding.txtTotal.setText("Tổng cộng: " + String.format("%.3f", total) + " đồng");
                    adapter.setOrderViewList(orderViews1);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        adapter = new UnpaidOrderAdapter();
        binding.recyclerView.setAdapter(adapter);
        binding.btnPay.setOnClickListener(v -> {
            order.status = 1;
            mViewModel.update(order);
            FragmentActivity activity = requireActivity();
            if (activity != null) {
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new DinnerTableFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
        binding.btnCancel.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });
    }


}