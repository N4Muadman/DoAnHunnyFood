package com.example.doanhunnyfood.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.doanhunnyfood.dao.AppDatabase;
import com.example.doanhunnyfood.dao.TableDao;
import com.example.doanhunnyfood.entydi.Table;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TableRepository {
    private TableDao mTableDao;
    private LiveData<List<Table>> mAllTable;
    private ExecutorService executorService;

    public TableRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.mTableDao = db.tableDao();
        this.mAllTable = mTableDao.findAll();
        this.executorService = Executors.newFixedThreadPool(4);
    }

    public LiveData<List<Table>> getAllTable() {
        return mAllTable;
    }

    public void insert(Table table){
        executorService.execute(() -> mTableDao.insert(table));
    }


}
