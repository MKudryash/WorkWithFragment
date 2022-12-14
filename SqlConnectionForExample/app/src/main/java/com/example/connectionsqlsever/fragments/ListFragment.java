package com.example.connectionsqlsever.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.connectionsqlsever.MainActivity;
import com.example.connectionsqlsever.R;
import com.example.connectionsqlsever.adapter.AdapterMovie;
import com.example.connectionsqlsever.models.DataModel;
import com.example.connectionsqlsever.models.Movie;
import com.example.connectionsqlsever.network.ConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ListFragment extends Fragment {
    private DataModel dataModel;
    Connection connection;
    List<Movie> data;
    ListView listView;
    AdapterMovie pAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        GetDataFromSql(view);
    }
    public void GetDataFromSql(View v) {
        dataModel = new ViewModelProvider(requireActivity()).get(DataModel.class);
        data = new ArrayList<Movie>();
        listView = v.findViewById(R.id.lvData);

        pAdapter = new AdapterMovie(v.getContext(), data,dataModel,getActivity());
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();
            if (connection != null) {
                String query = "SELECT ID,Title,YearPremiere,Country,Genre,Image\n" +
                        "FROM InformationMovies\n" +
                        "LEFT JOIN Country  on InformationMovies.ID_country = Country.ID_country\n" +
                        "LEFT JOIN Genre  on InformationMovies.ID_genre = Genre.ID_genre";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    Movie tempMovie = new Movie
                            (   resultSet.getInt("ID"),
                                    resultSet.getString("Title"),
                                    resultSet.getString("Genre"),
                                    resultSet.getString("Country"),
                                    resultSet.getString("Image"),
                                    Integer.parseInt(resultSet.getString("YearPremiere"))
                            );
                    data.add(tempMovie);
                }
                connection.close();
            } else {
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        pAdapter.notifyDataSetInvalidated();
        listView.setAdapter(pAdapter);

    }
}