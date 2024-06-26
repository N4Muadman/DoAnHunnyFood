package com.example.doanhunnyfood;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doanhunnyfood.SessionManager.SessionLogin;
import com.example.doanhunnyfood.databinding.ActivityMainBinding;
import com.example.doanhunnyfood.dialog.FoodManagerDialog;
import com.example.doanhunnyfood.dialog.UserManagerDialog;
import com.example.doanhunnyfood.dialog.TableManagerDialog;
import com.example.doanhunnyfood.entity.Order;
import com.example.doanhunnyfood.entity.OrderView;
import com.example.doanhunnyfood.entity.Table;
import com.example.doanhunnyfood.repository.OrderRepository;
import com.example.doanhunnyfood.ui.DinnerTable.DinnerTableFragment;
import com.example.doanhunnyfood.ui.DinnerTable.OnTableSelectedListener;
import com.example.doanhunnyfood.ui.DinnerTable.TableManagerFragment;
import com.example.doanhunnyfood.ui.Food.FoodFragment;
import com.example.doanhunnyfood.ui.Food.FoodManagerFragment;

import com.example.doanhunnyfood.ui.UnpainOrder.UnpaidOrderDetailFragment;

import com.example.doanhunnyfood.ui.gallery.GalleryFragment;
import com.example.doanhunnyfood.ui.home.HomeFragment;
import com.example.doanhunnyfood.ui.order.OrderManagerFragment;
import com.example.doanhunnyfood.ui.user.UserFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnTableSelectedListener {
    private ActivityMainBinding binding;
    private OrderRepository orderRepository;

    private LiveData<List<Order>> orderListData;

    private List<Order> orderList = new ArrayList<>();


    private SessionLogin sessionLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setValueLogin();

        orderRepository = new OrderRepository(getApplication());
        orderListData = orderRepository.getAllOrder();
        orderListData.observe(this, orders -> {
            if (orders != null) {
                orderList.clear();
                orderList.addAll(orders); // Thêm các phần tử mới vào danh sách
            }
        });
        setSupportActionBar(binding.appBarMain.toolbar);
        final MainActivity currentContext = this;
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Fragment> fragments =getSupportFragmentManager().getFragments();
                Fragment fragment = fragments.get(fragments.size()-1);
                if(fragment instanceof FoodManagerFragment ){
                    FoodManagerDialog dialog = new FoodManagerDialog(currentContext,(FoodManagerFragment) fragment);
                    dialog.show();

                }
                if (fragment instanceof TableManagerFragment){
                    TableManagerDialog dialog= new TableManagerDialog(currentContext,(TableManagerFragment) fragment);
                    dialog.show();
                }
                if (fragment instanceof UserFragment){
                    UserManagerDialog dialog = new UserManagerDialog(currentContext, (UserFragment) fragment);
                    dialog.show();
                }

            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        TabLayout tabLayout = binding.appBarMain.navViewTab;



        // Thêm các tab với custom view
        for (int i = 0; i < 3; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setCustomView(getTabView(i));
            tabLayout.addTab(tab);
        }

        // Đặt listener cho sự kiện chọn tab
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                // Thay đổi nội dung hiển thị dựa trên tab được chọn
                setTabContent(position);
                // Cập nhật trạng thái của tab để áp dụng màu sắc
                updateTabStates(tabLayout);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Không cần xử lý khi tab không được chọn
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                // Thay đổi nội dung hiển thị dựa trên tab được chọn
                setTabContent(position);
                // Cập nhật trạng thái của tab để áp dụng màu sắc
                updateTabStates(tabLayout);
            }
        });

        // Hiển thị nội dung của tab đầu tiên mặc định
        setTabContent(0);


        // Thiết lập NavigationView để điều hướng các Fragment tùy chỉnh
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            Fragment fragment = null;
            if (id == R.id.nav_home) {
                fragment = new HomeFragment();
                binding.appBarMain.toolbar.setTitle("Trang chủ");
            }else if (id == R.id.nav_qlBan) {
                fragment = new TableManagerFragment(); // Thay thế bằng Fragment của bạn
                binding.appBarMain.toolbar.setTitle("Quản lý bàn");
            }else if (id == R.id.nav_qlMonAn) {
                    fragment = new FoodManagerFragment(); // Thay thế bằng Fragment của bạn
                    binding.appBarMain.toolbar.setTitle("Quản lý món ăn");
                }
            else if (id == R.id.nav_qlUser) {
                fragment = new UserFragment(); // Thay thế bằng Fragment của bạn
                binding.appBarMain.toolbar.setTitle("Quản lý nhân viên");
            }
            else if (id == R.id.nav_qlDonHang){
                fragment = new OrderManagerFragment();
                binding.appBarMain.toolbar.setTitle("Quản lý đơn hàng");
            }

            if(id == R.id.nav_logout){
                sessionLogin.setLogin(false);
                sessionLogin.logout();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

            if (fragment != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            }
            drawer.closeDrawer(GravityCompat.END);
            return true;
        });
    }

    private void setValueLogin() {
        sessionLogin = new SessionLogin(this);
        String fullname = sessionLogin.getLoggedInFullname();
        String email = sessionLogin.getLoggedInEmail();
        NavigationView navigationView = binding.navView;
        View headerView = navigationView.getHeaderView(0);
        TextView txtFullName = headerView.findViewById(R.id.txtFullName);
        TextView txtEmail = headerView.findViewById(R.id.txtEmail);

        if (!fullname.isEmpty()  && !email.isEmpty()) {
            txtFullName.setText(fullname);
            txtEmail.setText(email);
        } else {
            // Xử lý trường hợp không có người dùng đã đăng nhập
        }

    }


    private View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_item, null);
        TextView textView = view.findViewById(R.id.tab_title);
        ImageView imageView = view.findViewById(R.id.tab_icon);
        switch (position) {
            case 0:
                textView.setText("Trang chủ");
                imageView.setImageResource(R.drawable.ic_home); // Thiết lập icon của bạn ở đây
                break;
            case 1:
                textView.setText("Chọn bàn");
                imageView.setImageResource(R.drawable.ic_chair); // Thiết lập icon của bạn ở đây
                break;
            case 2:
                textView.setText("Quản lý");
                imageView.setImageResource(R.drawable.ic_user); // Thiết lập icon của bạn ở đây
                break;
        }
        return view;
    }

    private void setTabContent(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                binding.appBarMain.toolbar.setTitle("Trang chủ");
                break;
            case 1:
                fragment = new DinnerTableFragment();
                binding.appBarMain.toolbar.setTitle("Chọn bàn");
                break;
            case 2:
                DrawerLayout drawer = binding.drawerLayout;
                drawer.openDrawer(GravityCompat.END);
                return;
            default:
                fragment = new HomeFragment();
                break;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    private void updateTabStates(TabLayout tabLayout) {

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null && tab.getCustomView() != null) {
                View view = tab.getCustomView();
                ImageView imageView = view.findViewById(R.id.tab_icon);
                TextView textView = view.findViewById(R.id.tab_title);
                boolean isSelected = tab.isSelected();
                imageView.setSelected(isSelected);
                textView.setSelected(isSelected);

                int selectedColor = ContextCompat.getColor(MainActivity.this, R.color.colorTabSelected);
                int unselectedColor = ContextCompat.getColor(MainActivity.this, R.color.colorTabUnselected);

                // Set tint color for imageView based on the selected state
                if (isSelected) {
                    imageView.setColorFilter(selectedColor, PorterDuff.Mode.SRC_IN);
                    textView.setTextColor(selectedColor);
                } else {
                    imageView.setColorFilter(unselectedColor, PorterDuff.Mode.SRC_IN);
                    textView.setTextColor(unselectedColor);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onTableSelected(Table table) {
        boolean foundOrder = false;
        Log.d("mainActivity", "onTableSelected: " + orderList.size());
        for (Order order : orderList) {
            if (order.status == 0 && order.table_id == table.id) {
                foundOrder = true;
                UnpaidOrderDetailFragment unpaidOrderDetailFragment = UnpaidOrderDetailFragment.newInstance(order);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, unpaidOrderDetailFragment)
                        .addToBackStack(null)
                        .commit();
                binding.appBarMain.toolbar.setTitle("Chi tiết hóa đơn chưa thanh toán");
                break;
            }
        }

        if (!foundOrder) {
            FoodFragment foodFragment = FoodFragment.newInstance(table);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, foodFragment)
                    .addToBackStack(null)
                    .commit();

            binding.appBarMain.toolbar.setTitle("Chọn đồ ăn");
        }
    }
}