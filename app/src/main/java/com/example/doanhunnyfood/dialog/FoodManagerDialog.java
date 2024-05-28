package com.example.doanhunnyfood.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.doanhunnyfood.R;
import com.example.doanhunnyfood.adapter.FoodManagerRecycAdapter;
import com.example.doanhunnyfood.entity.Food;
import com.example.doanhunnyfood.ui.Food.FoodManagerFragment;
import com.example.doanhunnyfood.ui.Food.FoodManagerViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class FoodManagerDialog {
    private FoodManagerViewModel mViewModel;
    private LayoutInflater mlayoutInflater;
    private AlertDialog mDiglog;
    private TextInputEditText edtID, edtName,edtPrice,edtStatus,edtImage,edtDes;

    private boolean mEditMode;

    public FoodManagerDialog(final Context context, FoodManagerFragment fragment) {
        mViewModel = fragment.getViewModel();
        mlayoutInflater = LayoutInflater.from(context);
        View view = mlayoutInflater.inflate(R.layout.dialog_food_manager,null);
        edtID = view.findViewById(R.id.edtID);
        edtName = view.findViewById(R.id.edtName);
        edtImage = view.findViewById(R.id.edtImage);
        edtPrice = view.findViewById(R.id.edtPrice);
        edtStatus = view.findViewById(R.id.edtStatus);
        edtDes = view.findViewById(R.id.edtDescription);
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton("Dong", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        mDiglog.dismiss();
                    }
                })
                .setPositiveButton("Luu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        Food food =new Food(edtName.getText().toString(), edtDes.getText().toString(),edtPrice.getInputType(),edtImage.getInputType(),edtStatus.getInputType());

                        mViewModel.insert(food);
                        Toast.makeText(context, "Món ăn được lưu", Toast.LENGTH_SHORT).show();

                    }
                });
        mDiglog = builder.create();
    }
    public void show(){
        mDiglog.show();
    }
}
