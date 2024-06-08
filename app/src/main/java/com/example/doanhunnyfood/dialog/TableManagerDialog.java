package com.example.doanhunnyfood.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.doanhunnyfood.R;
import com.example.doanhunnyfood.entity.Food;
import com.example.doanhunnyfood.entity.Table;
import com.example.doanhunnyfood.ui.DinnerTable.TableManagerFragment;
import com.example.doanhunnyfood.ui.DinnerTable.TableManagerViewModel;
import com.example.doanhunnyfood.ui.Food.FoodManagerViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class TableManagerDialog {
    private TableManagerViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;
    private TextInputEditText etID, etName, etStatus,  etDes;
    private boolean mEditMode;

    public TableManagerDialog(Context context, TableManagerFragment fragment, Table ... table) {
        mViewModel = fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.dialog_table_manager, null);

        etID = view.findViewById(R.id.etID);
        etName = view.findViewById(R.id.etName);
        etStatus = view.findViewById(R.id.etStatus);
        etDes = view.findViewById(R.id.etDes);
        if (table != null && table.length > 0) {
            etID.setText(String.valueOf(table[0].id));
            etName.setText(table[0].name);
            etStatus.setText(String.valueOf(table[0].status));
            etDes.setText(table[0].description);
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

                            String name = etName.getText().toString();
                            int status = Integer.parseInt(etStatus.getText().toString());
                            String description = etDes.getText().toString();

                            Table table = new Table(name, status, description);

                            if (mEditMode) {
                                table.id = Integer.parseInt(etID.getText().toString());
                                mViewModel.update(table);
                                Toast.makeText(context, "Bàn đã được cập nhật", Toast.LENGTH_SHORT).show();
                            } else {
                                mViewModel.insert(table);
                                Toast.makeText(context, "Bàn đã được lưu", Toast.LENGTH_SHORT).show();
                            }
                        } catch (NumberFormatException e) {
                            Toast.makeText(context, "Dữ liệu nhập không hợp lệ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        mDialog = builder.create();
    }
    public void show(){
        mDialog.show();
    }
}
