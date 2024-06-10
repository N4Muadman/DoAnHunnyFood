package com.example.doanhunnyfood.ui.user;

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
import com.example.doanhunnyfood.SessionManager.SessionLogin;
import com.example.doanhunnyfood.adapter.ItemClickListener;
import com.example.doanhunnyfood.adapter.UserAdapter;
import com.example.doanhunnyfood.databinding.FragmentUserBinding;
import com.example.doanhunnyfood.dialog.FoodManagerDialog;
import com.example.doanhunnyfood.dialog.UserManagerDialog;
import com.example.doanhunnyfood.entity.Food;
import com.example.doanhunnyfood.entity.User;
import com.example.doanhunnyfood.ui.Food.FoodManagerFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class UserFragment extends Fragment {

    private UserViewModel mViewModel;
    private FragmentUserBinding binding;
    private UserAdapter userAdapter;
    private SessionLogin sessionLogin;

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    public UserViewModel getViewModel(){
        return mViewModel;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentUserBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userAdapter = new UserAdapter(null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(userAdapter);
        sessionLogin = new SessionLogin(getActivity());


        userAdapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                User user = userAdapter.getItem(position);
                UserManagerDialog dialog = new UserManagerDialog(getActivity(), UserFragment.this ,user);
                dialog.show();
            }
        });

        mViewModel.getAllUser().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                List<User> userList = new ArrayList<>();
                for (User u: users) {
                    if (u.status == 1 && u.id != sessionLogin.getLoggedInUserId()){
                        userList.add(u);
                    }
                }
                userAdapter.setUserList(userList);
            }
        });

        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                User user = userAdapter.getItem(pos);
                user.status = 0;
                Toast.makeText(getActivity(), "Nhân viên "+ user.fullName+ " đã được xóa", Toast.LENGTH_SHORT).show();
                mViewModel.update(user);
            }
        });
        helper.attachToRecyclerView(binding.recyclerView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}