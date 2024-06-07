package com.example.doanhunnyfood.ui.Food;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doanhunnyfood.R;
import com.example.doanhunnyfood.SessionManager.SessionLogin;
import com.example.doanhunnyfood.entity.Cart;
import com.example.doanhunnyfood.adapter.CartAdapter;
import com.example.doanhunnyfood.adapter.FoodAdapter;
import com.example.doanhunnyfood.adapter.ItemClickListener;
import com.example.doanhunnyfood.entity.Food;
import com.example.doanhunnyfood.entity.Order;
import com.example.doanhunnyfood.entity.Order_detail;
import com.example.doanhunnyfood.entity.Table;
import com.example.doanhunnyfood.ui.DinnerTable.DinnerTableFragment;

import java.util.ArrayList;
import java.util.List;

public class FoodFragment extends Fragment {
    private RecyclerView recyclerViewCart, recyclerViewDish;
    private Button btnXN, btnHuy;
    private TextView txtNameTable;
    private FoodAdapter foodAdapter;
    private Table mTable;
    private CartAdapter cartAdapter;
    private List<Cart> listCat;
    private FoodViewModel mViewModel;
    private int table_id;
    private SessionLogin sessionLogin;

    public static FoodFragment newInstance(Table table) {
        FoodFragment fragment = new FoodFragment();
        Bundle args = new Bundle();
        args.putParcelable("TABLE", table);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dish, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(FoodViewModel.class);
        mViewModel.getAllDish().observe(getViewLifecycleOwner(), new Observer<List<Food>>() {
            @Override
            public void onChanged(List<Food> foodList) {
                foodAdapter.setDishList(foodList);
            }
        });
        recyclerViewDish = view.findViewById(R.id.recyclerViewDish);
        txtNameTable = view.findViewById(R.id.txtNameTable);
        recyclerViewCart = view.findViewById(R.id.recyclerViewCart);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerViewDish.setLayoutManager(layoutManager);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewCart.setLayoutManager(linearLayoutManager);

        listCat = new ArrayList<>();

        foodAdapter = new FoodAdapter(null);
        recyclerViewDish.setAdapter(foodAdapter);

        if (getArguments() != null) {
            mTable = getArguments().getParcelable("TABLE");
            // Sử dụng dữ liệu bàn ở đây
            if (mTable != null) {
                txtNameTable.setText(mTable.name);
                table_id = mTable.id;
            }
        }
        foodAdapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Food posFood = foodAdapter.getItem(position);
                if (listCat.size() > 0) {
                    boolean flag = false;
                    for (int i = 0; i < listCat.size(); i++) {
                        if (listCat.get(i).getId() == posFood.id) {
                            listCat.get(i).setQtt(listCat.get(i).getQtt() + 1);
                            flag = true;
                        }
                    }
                    if (flag == false) {
                        Cart cart = new Cart();
                        cart.setId(posFood.id);
                        cart.setImg(posFood.image);
                        cart.setQtt(1);
                        cart.setPrice(posFood.price);
                        cart.setName(posFood.name);
                        listCat.add(cart);
                    }

                } else {
                    Cart cart = new Cart();
                    cart.setId(posFood.id);
                    cart.setImg(posFood.image);
                    cart.setQtt(1);
                    cart.setPrice(posFood.price);
                    cart.setName(posFood.name);
                    listCat.add(cart);
                }

                if (cartAdapter == null) {
                    cartAdapter = new CartAdapter(listCat);
                    recyclerViewCart.setAdapter(cartAdapter);
                } else {
                    cartAdapter.setCartList(listCat);
                    cartAdapter.notifyDataSetChanged(); // Hoặc có thể sử dụng notifyDataSetChanged() để thông báo sự thay đổi
                }
            }
        });
        btnXN = view.findViewById(R.id.btnXacNhan);
        btnHuy = view.findViewById(R.id.btnCancel);
        btnHuy.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });
        btnXN.setOnClickListener(v -> {
            if(cartAdapter == null){
                Toast.makeText(getActivity(), "Ban chua chon mon, vui long do an truoc", Toast.LENGTH_SHORT).show();
            }
            else{
                sessionLogin = new SessionLogin(getContext());
                int userId = sessionLogin.getLoggedInUserId();
                Order newOrder = new Order();
                newOrder.table_id = table_id;
                newOrder.user_id = userId;
                newOrder.order_time = System.currentTimeMillis();
                newOrder.total = 0;
                newOrder.status = 0;
                mViewModel.insertOrder(newOrder, orderId -> {
                    List<Order_detail> orderDetails = new ArrayList<>();
                    for (Cart cart : listCat) {
                        Order_detail orderDetail = new Order_detail();
                        orderDetail.food_id = cart.getId();
                        orderDetail.order_id = (int) orderId;  // Chuyển đổi từ long sang int nếu cần
                        orderDetail.qtt = cart.getQtt();
                        orderDetail.status = 0;
                        orderDetail.TotalFood = cart.getQtt() * cart.getPrice();
                        orderDetails.add(orderDetail);
                    }
                    mViewModel.insertOrderDetail(orderDetails, new FoodViewModel.OrderDetailInsertCallback() {
                        @Override
                        public void onOrderDetailInserted() {
                            // Thành công
                            listCat.clear();
                            cartAdapter.setCartList(listCat);
                            cartAdapter.notifyDataSetChanged();

                            Log.d("orderDetail: ", "thanh cong");

                        }

                        @Override
                        public void onOrderDetailInsertFailed() {
                            Log.d("orderDetail: ", "that bai");
                        }
                    });

                });
                FragmentActivity activity = requireActivity();
                if (activity != null) {
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new DinnerTableFragment())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

    }
}