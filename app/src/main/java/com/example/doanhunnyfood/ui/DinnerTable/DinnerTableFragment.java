package com.example.doanhunnyfood.ui.DinnerTable;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doanhunnyfood.R;
import com.example.doanhunnyfood.adapter.ItemClickListener;
import com.example.doanhunnyfood.adapter.TableAdapter;
import com.example.doanhunnyfood.entity.Order;
import com.example.doanhunnyfood.entity.Table;

import java.util.List;

public class DinnerTableFragment extends Fragment {
    private RecyclerView recyclerView;
    private TableAdapter mAdapter;

    private DinnerTableViewModel mViewModel;
    private OnTableSelectedListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnTableSelectedListener) {
            mListener = (OnTableSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnTableSelectedListener");
        }
    }

    public static DinnerTableFragment newInstance() {
        return new DinnerTableFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dinner_table, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new TableAdapter(null); // Initialize with null or an empty list
        recyclerView.setAdapter(mAdapter);

        // Initialize ViewModel and observe LiveData
        mViewModel = new ViewModelProvider(this).get(DinnerTableViewModel.class);
        mViewModel.getAllTable().observe(getViewLifecycleOwner(), new Observer<List<Table>>() {
            @Override
            public void onChanged(List<Table> tables) {
                mAdapter.setTables(tables); // Update adapter data
            }
        });

        mViewModel.getAllOrder().observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                mAdapter.setOrders(orders);
            }
        });

        mAdapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Table selectedTable = mAdapter.getTableAtPosition(position);
                mListener.onTableSelected(selectedTable);
            }
        });


    }


}