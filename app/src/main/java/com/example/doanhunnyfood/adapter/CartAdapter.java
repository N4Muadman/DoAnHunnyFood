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
import com.example.doanhunnyfood.entity.Cart;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<Cart> cartList;
    private ItemClickListener itemClickListener;

    public CartAdapter(List<Cart> cartList) {
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyc_cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        if (cartList != null){
            holder.imgCart.setImageResource(cartList.get(position).getImg());
            holder.txtName.setText(cartList.get(position).getName().toString());
            holder.txtPrice.setText(String.format("%.3f", cartList.get(position).getPrice()) + "đ");
            holder.txtQtt.setText(cartList.get(position).getQtt() +"");

            holder.btnAdd.setOnClickListener(v ->{
                int newQtt = cartList.get(position).getQtt() + 1;
                cartList.get(position).setQtt(newQtt);
                holder.txtQtt.setText(newQtt + "");
            });
            holder.btnMinus.setOnClickListener(v -> {
                int newQtt = cartList.get(position).getQtt() - 1;
                cartList.get(position).setQtt(newQtt);
                holder.txtQtt.setText(newQtt + "");
            });
            holder.btnDelete.setOnClickListener(v -> {
                cartList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, cartList.size());
            });
        }

    }

    @Override
    public int getItemCount() {
        if (cartList == null)
            return 0;
        else return cartList.size();
    }

    public void setCartList(List<Cart> carts){
        cartList = carts;
        notifyDataSetChanged();
    }


    public class CartViewHolder extends RecyclerView.ViewHolder{
        private ImageButton btnAdd, btnMinus, btnDelete;
        private ImageView imgCart;
        private TextView txtName, txtPrice, txtQtt;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            btnAdd = itemView.findViewById(R.id.btnAdd);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            btnDelete = itemView.findViewById(R.id.btnRemove);
            imgCart = itemView.findViewById(R.id.imgCart);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtQtt = itemView.findViewById(R.id.txtQtt);
        }
    }
}
