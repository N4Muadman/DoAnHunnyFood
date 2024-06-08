package com.example.doanhunnyfood.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.doanhunnyfood.R;
import com.example.doanhunnyfood.entity.Food;
import com.example.doanhunnyfood.ui.Food.FoodManagerFragment;
import com.example.doanhunnyfood.ui.Food.FoodManagerViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class FoodManagerDialog {
    private FoodManagerViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;
    private TextInputEditText edtID, edtName, edtPrice, edtStatus, edtImage, edtDes;
    private boolean mEditMode;

    public FoodManagerDialog(final Context context, FoodManagerFragment fragment, Food... food) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_food_manager, null);

        edtName = view.findViewById(R.id.edtName);
        edtPrice = view.findViewById(R.id.edtPrice);
        edtStatus = view.findViewById(R.id.edtStatus);
        edtDes = view.findViewById(R.id.edtDescription);
        edtImage = view.findViewById(R.id.edtImage);

        if (food != null && food.length > 0) {
            edtName.setText(food[0].name);
            edtPrice.setText(String.valueOf(food[0].price));
            edtStatus.setText(String.valueOf(food[0].status));
            edtDes.setText(food[0].description);
            edtImage.setText(String.valueOf(food[0].image));
            mEditMode = true;
        } else {
            mEditMode = false;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        mDialog.dismiss();
                    }
                })
                .setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        try {
                            String name = edtName.getText().toString();
                            String description = edtDes.getText().toString();
                            double price = Double.parseDouble(edtPrice.getText().toString());
                            int image = Integer.parseInt(edtImage.getText().toString());
                            int status = Integer.parseInt(edtStatus.getText().toString());

                            Food food = new Food(name, description, price, image, status);

                            if (mEditMode) {
                                mViewModel.update(food);
                                Toast.makeText(context, "Món ăn được cập nhật", Toast.LENGTH_SHORT).show();
                            } else {
                                food.image = R.drawable.img_tea;
                                mViewModel.insert(food);
                                Toast.makeText(context, "Món ăn được lưu", Toast.LENGTH_SHORT).show();
                            }
                        } catch (NumberFormatException e) {
                            Log.e("FoodManagerDialog", "NumberFormatException: " + e.getMessage());
                            Toast.makeText(context, "Dữ liệu nhập không hợp lệ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        mDialog = builder.create();
    }

    public void show() {
        mDialog.show();
    }
}
