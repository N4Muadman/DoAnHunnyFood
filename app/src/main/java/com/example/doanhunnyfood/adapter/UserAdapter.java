package com.example.doanhunnyfood.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanhunnyfood.R;
import com.example.doanhunnyfood.entity.Food;
import com.example.doanhunnyfood.entity.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> userList;
    private ItemClickListener itemClickListener;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyc_user_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        if (userList != null){
            holder.txtFullName.setText(userList.get(position).fullName);
            holder.txtEmail.setText(userList.get(position).email);
            holder.txtPhoneNumber.setText(userList.get(position).phoneNumber);
        }

    }

    @Override
    public int getItemCount() {
        if (userList != null)
            return userList.size();
        return 0;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }
    public User getItem(int position) {
        if (userList != null && position >= 0 && position < userList.size()) {
            return userList.get(position);
        }
        return null;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private ImageView btnEdit;
        private TextView txtFullName, txtEmail, txtPhoneNumber;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            txtPhoneNumber = itemView.findViewById(R.id.txtPhoneNumber);
            txtFullName = itemView.findViewById(R.id.txtFullName);

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null){
                        itemClickListener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
