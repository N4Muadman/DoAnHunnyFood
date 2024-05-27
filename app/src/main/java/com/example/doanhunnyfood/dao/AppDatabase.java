package com.example.doanhunnyfood.dao;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.doanhunnyfood.Utils.PasswordUtils;
import com.example.doanhunnyfood.entity.Food;
import com.example.doanhunnyfood.entity.Order;
import com.example.doanhunnyfood.entity.Order_detail;
import com.example.doanhunnyfood.entity.Table;
import com.example.doanhunnyfood.entity.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Table.class, Food.class, Order.class, Order_detail.class, User.class}, version = 4, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TableDao tableDao();

    public abstract FoodDao dishDao();

    public abstract OrderDao orderDao();

    public abstract Order_detailDao orderDetailDao();

    public abstract UserDao userDao();

    private static volatile AppDatabase INSTANCE;

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);


    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            databaseWriteExecutor.execute(() ->{
                populateInitiaData(INSTANCE.tableDao());

                populateInitialDishData(INSTANCE.dishDao());
                populateInitialUserData(INSTANCE.userDao());
            });
        }
    };

    public static AppDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (AppDatabase.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "hunny_food_store")
                        .fallbackToDestructiveMigration()
                        .addCallback(callback)
                        .build();
            }
        }
        return INSTANCE;
    }

    private static void populateInitiaData(TableDao tableDao) {

        String[] tables = new String[]{"ban 1", "ban 2", "ban 3", "ban 4", "ban 5"};
        for (String name : tables) {
            Table table = new Table();
            table.name = name;
            tableDao.insert(table);
        }
    }
    private static void populateInitialDishData(FoodDao foodDao) {
        Food[] foods = new Food[]{
                new Food(),
                new Food(),
                new Food(),
                new Food(),
                new Food()

        };
        for (Food food : foods) {
            foodDao.insert(food);
        }
    }
    private static void populateInitialUserData(UserDao userDao){
        User[] users = new User[]{
                new User("nam123", PasswordUtils.hashPassword("123456"), "dau van nam", "nam@gmail.com", "0987654321", "Dien Chau", 1),
                new User("vinh123", PasswordUtils.hashPassword("123456"), "Dang Thi Vinh", "vinh123@gmail.com", "034867345", "Dien Chau", 1),
                new User("suong123", PasswordUtils.hashPassword("123456"), "My Suong", "vinh123@gmail.com", "0368396735", "Nam Dan", 1),
        };

        for (User user: users) {
            userDao.insert(user);
        }


    }
}
