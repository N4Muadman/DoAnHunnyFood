package com.example.doanhunnyfood;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doanhunnyfood.SessionManager.SessionLogin;
import com.example.doanhunnyfood.Utils.PasswordUtils;
import com.example.doanhunnyfood.databinding.ActivityLoginBinding;
import com.example.doanhunnyfood.entity.User;
import com.example.doanhunnyfood.repository.UserRepository;
import com.google.android.material.textfield.TextInputEditText;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.rxjava3.core.Single;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding loginBinding;
    private SessionLogin sessionLogin;

    private ExecutorService executorService;
    private Handler mainHandler;

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
        executorService = Executors.newSingleThreadExecutor();
        mainHandler = new Handler(Looper.getMainLooper());
        userRepository = new UserRepository(getApplication());

        btnLogin.setOnClickListener(v -> {
            TextInputEditText userNameEditText = loginBinding.inputUserName;
            TextInputEditText passwordEditText = loginBinding.inputPassword;

            String userName = userNameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            password = PasswordUtils.hashPassword(password);

            if (userName.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Nhan mat khau va ten dang nhap", Toast.LENGTH_SHORT).show();
                return;
            }
            userRepository.login(userName, password, user -> runOnUiThread(() -> {
                if (user != null) {
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    sessionLogin.setLogin(true);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "tai khoan vs mat khau sai", Toast.LENGTH_SHORT).show();
                }
            }));
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}
