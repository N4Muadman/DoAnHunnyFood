package com.example.doanhunnyfood.ui;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doanhunnyfood.R;
import com.example.doanhunnyfood.entydi.OrderView;

import java.util.List;

public class UnpaidOrderDetailFragment extends Fragment {

    private UnpaidOrderDetailViewModel mViewModel;

    public static UnpaidOrderDetailFragment newInstance(List<OrderView> orderViewList) {
        return new UnpaidOrderDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_unpaid_order_detail, container, false);
    }



}