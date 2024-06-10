package com.example.doanhunnyfood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanhunnyfood.R;
import com.example.doanhunnyfood.entity.Table;

import java.util.List;

public class TableManagerRecycAdapter extends RecyclerView.Adapter<TableManagerRecycAdapter.TableManagerViewHolder>{
    private LayoutInflater mLayoutInflater;
    private List<Table> mList;

    public static ItemClickListener itemEditClickListener;
    public TableManagerRecycAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public static void setOnItemEditClickListener(ItemClickListener itemEditClickListener) {
        TableManagerRecycAdapter.itemEditClickListener = itemEditClickListener;
    }


    @NonNull
    @Override
    public TableManagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recyc_table_manager_item,parent,false);
        return new TableManagerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TableManagerViewHolder holder, int position) {
        if (mList != null && position < mList.size()) {
            holder.tvTableName.setText(mList.get(position).name);

            // Chuyển đổi giá trị int thành chuỗi trước khi đặt vào TextView
            String statusText = String.valueOf(mList.get(position).description);
            holder.tvStatus.setText(statusText);
        }
    }

    @Override
    public int getItemCount() {
        if(mList == null)
            return 0;
        return mList.size();
    }
    public Table getItem(int position){
        if (mList == null || position >=mList.size()){
            return null;
        }
        return mList.get(position);
    }

    public void setList(List<Table> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class TableManagerViewHolder extends RecyclerView.ViewHolder{
        public TextView tvTableName, tvStatus;
        public ImageView ivEdit;
        public CardView cv;

        public TableManagerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTableName = itemView.findViewById(R.id.tvNameTable);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            cv = (CardView) itemView;

            ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemEditClickListener != null){
                        itemEditClickListener.onItemClick(getAdapterPosition());
                    }

                }
            });
        }
    }
}
