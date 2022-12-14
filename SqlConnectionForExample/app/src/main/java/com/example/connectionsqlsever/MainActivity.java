package com.example.connectionsqlsever;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.connectionsqlsever.adapter.AdapterMovie;
import com.example.connectionsqlsever.fragments.ListFragment;
import com.example.connectionsqlsever.models.DataModel;
import com.example.connectionsqlsever.models.Movie;
import com.example.connectionsqlsever.network.ConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DataModel dataModel;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.listFrame, ListFragment.class, null)
                .commit();

        dataModel = new ViewModelProvider(this).get(DataModel.class);
        dataModel.selectedItem.observe(this,
                item -> {
                });
    }

}