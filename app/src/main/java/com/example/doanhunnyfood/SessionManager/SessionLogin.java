package com.example.doanhunnyfood.SessionManager;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.doanhunnyfood.entity.User;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class SessionLogin {
    private static final String PREF_NAME = "login_pref";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_USER_EMAIL = "userEmail";
    private static final String KEY_USERNAME = "username";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;
    public SessionLogin(Context context) {
        this.context = context;
        // Khởi tạo SharedPreferences với tên PREF_NAME và chế độ MODE_PRIVATE (chỉ ứng dụng này có quyền truy cập)
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        // Khởi tạo Editor để sửa đổi SharedPreferences
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);  // Lưu giá trị isLoggedIn vào SharedPreferences
        editor.apply();  // Áp dụng các thay đổi (lưu trữ ngay lập tức)
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }
    public void setLoggedInUser(User user) {
        editor.putString(KEY_USER_EMAIL, user.email);
        editor.putString(KEY_USERNAME, user.fullName);
        editor.apply();
    }
    public String getLoggedInEmail() {
        return pref.getString(KEY_USER_EMAIL, "");
    }

    public String getLoggedInFullname() {
        return pref.getString(KEY_USERNAME, "");
    }
    public void logout() {
        editor.clear();
        editor.commit();
    }
}
