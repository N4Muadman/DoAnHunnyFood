package com.example.doanhunnyfood.ui.DinnerTable;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.doanhunnyfood.R;
import com.example.doanhunnyfood.adapter.ItemClickListener;
import com.example.doanhunnyfood.adapter.TableManagerRecycAdapter;
import com.example.doanhunnyfood.dialog.TableManagerDialog;
import com.example.doanhunnyfood.entity.Food;
import com.example.doanhunnyfood.entity.Table;

import java.util.List;

public class TableManagerFragment extends Fragment {

    private RecyclerView mRv;
    private TableManagerRecycAdapter mAdapter;
    private TableManagerViewModel mViewModel;

    public static TableManagerFragment newInstance() {
        return new TableManagerFragment();
    }

    public TableManagerViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_table_manager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView3);
        mAdapter = new TableManagerRecycAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        final TableManagerFragment currentFragment = this;
        mRv.setAdapter(mAdapter);
        mAdapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Table table = mAdapter.getItem(position);
                TableManagerDialog dialog = new TableManagerDialog(getActivity(),currentFragment,table);
                dialog.show();
            }
        });
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
                ) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Table table = mAdapter.getItem(position);

                        Toast.makeText(getActivity(),"Bàn đã được xoá",Toast.LENGTH_SHORT).show();
                        mViewModel.delete(table);

                    }
                }
        );
        helper.attachToRecyclerView(mRv);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TableManagerViewModel.class);
        mViewModel.getAllTable().observe(getActivity(), new Observer<List<Table>>() {
            @Override
            public void onChanged(List<Table> tables) {
                mAdapter.setList(tables);
            }
        });

    }

}