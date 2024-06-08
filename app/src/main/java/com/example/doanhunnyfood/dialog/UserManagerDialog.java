package com.example.doanhunnyfood.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.doanhunnyfood.R;
import com.example.doanhunnyfood.Utils.PasswordUtils;
import com.example.doanhunnyfood.entity.User;
import com.example.doanhunnyfood.ui.user.UserFragment;
import com.example.doanhunnyfood.ui.user.UserViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class UserManagerDialog {
    private UserViewModel mViewModel;
    private AlertDialog mDialog;
    private TextInputEditText eUserName, eFullName, eEmail, eAddress,  ePhoneNumber;
    private TextInputLayout ipLyUserName;
    private boolean mEditMode;
    private String userName, fullName, Email, phoneNumber, address;

    public UserManagerDialog(final Context context, UserFragment fragment, User ... user){
        mViewModel = fragment.getViewModel();

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_user_manager, null);
        eUserName = view.findViewById(R.id.inputUserName);
        eFullName = view.findViewById(R.id.inputFullName);
        eEmail = view.findViewById(R.id.inputEmail);
        eAddress = view.findViewById(R.id.inputAddress);
        ePhoneNumber = view.findViewById(R.id.inputPhoneNumber);
        ipLyUserName = view.findViewById(R.id.txtLyUserName);

        if (user != null && user.length > 0) {
            eUserName.setVisibility(View.GONE);
            ipLyUserName.setVisibility(View.GONE);
            eFullName.setText(user[0].fullName);
            eEmail.setText(user[0].email);
            eAddress.setText(user[0].address);
            ePhoneNumber.setText(user[0].phoneNumber);
            mEditMode = true;
        } else {
            mEditMode = false;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialog.dismiss();
                    }
                })
                .setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String passWord = PasswordUtils.hashPassword("123456");
                        getInformation();
                        if (mEditMode){
                            user[0].fullName = fullName;
                            user[0].email = Email;
                            user[0].phoneNumber = phoneNumber;
                            user[0].address = address;
                            mViewModel.update(user[0]);
                        }
                        else {
                            if (userName.isEmpty() || fullName.isEmpty() || Email.isEmpty() || phoneNumber.isEmpty()|| address.isEmpty() ){
                                Toast.makeText(context, "Vui lòng nhập đủ các thông tin", Toast.LENGTH_SHORT).show();
                                mDialog.dismiss();
                            }else {
                                User user = new User(userName, passWord, fullName, Email, phoneNumber, address, 1);
                                mViewModel.insert(user);
                            }
                        }
                    }
                });
        mDialog = builder.create();
    }

    public void show(){
        mDialog.show();
    }

    private void getInformation(){
        userName = eUserName.getText().toString();
        fullName = eFullName.getText().toString();
        Email = eEmail.getText().toString();
        phoneNumber = ePhoneNumber.getText().toString();
        address = eAddress.getText().toString();
    }



}
