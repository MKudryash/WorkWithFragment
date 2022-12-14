package com.example.connectionsqlsever.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.connectionsqlsever.R;
import com.example.connectionsqlsever.models.DataModel;


public class ItemFragment extends Fragment {
    private DataModel dataModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item, container, false);
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText txtTitleMovie = view.findViewById(R.id.txtTitleMovieF);
        EditText txtCountry = view.findViewById(R.id.txtCountryF);
        EditText txtGenreMovie = view.findViewById(R.id.txtGenreMovieF);
        EditText txtYearPremiere = view.findViewById(R.id.txtYearPremiereF);
        dataModel = new ViewModelProvider(requireActivity()).get(DataModel.class);
        dataModel.selectedItem.observe(getViewLifecycleOwner(),item->
        {
            txtTitleMovie.setText(item.Title);
            txtCountry.setText(item.Country);
            txtGenreMovie.setText(item.Genre);
            txtYearPremiere.setText(Integer.toString(item.YearPremiere));
        });
    }
}