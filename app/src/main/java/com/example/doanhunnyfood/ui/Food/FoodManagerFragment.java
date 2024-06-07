package com.example.doanhunnyfood.ui.Food;

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

import com.example.doanhunnyfood.R;
import com.example.doanhunnyfood.adapter.FoodManagerRecycAdapter;
import com.example.doanhunnyfood.entity.Food;

import java.util.List;

public class FoodManagerFragment extends Fragment {
    private RecyclerView mRv;
    private FoodManagerRecycAdapter mAdapter;
    private FoodManagerViewModel mViewModel;

    public FoodManagerViewModel getViewModel() {
        return mViewModel;
    }

    public static FoodManagerFragment newInstance() {
        return new FoodManagerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_food_manager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView2);
        mAdapter = new FoodManagerRecycAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FoodManagerViewModel.class);
        mViewModel.getAllFoodManager().observe(getActivity(), new Observer<List<Food>>() {
            @Override
            public void onChanged(List<Food> foods) {
                mAdapter.setList(foods);
            }
        });
    }

}