package com.example.doanhunnyfood;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doanhunnyfood.SessionManager.SessionLogin;
import com.example.doanhunnyfood.databinding.ActivityLoginBinding;
import com.example.doanhunnyfood.entity.User;
import com.example.doanhunnyfood.repository.UserRepository;
import com.google.android.material.textfield.TextInputEditText;

import io.reactivex.rxjava3.core.Single;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding loginBinding;
    private SessionLogin sessionLogin;

    private UserRepository userRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());

        sessionLogin = new SessionLogin(this);

        if (sessionLogin.isLoggedIn()){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }


        Button btnLogin = loginBinding.btnLogin;

        userRepository = new UserRepository(getApplication());

        btnLogin.setOnClickListener(v -> {
            TextInputEditText userName = loginBinding.inputUserName;
            TextInputEditText password = loginBinding.inputPassword;
            Single<User> user = userRepository.login(userName.toString(), password.toString());
            if(user != null){
                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                // Lưu trạng thái đăng nhập
                sessionLogin.setLogin(true);
                // Lưu thông tin người dùng đã đăng nhập
                sessionLogin.setLoggedInUserFromSingle(user);
                // Chuyển sang MainActivity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
