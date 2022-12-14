package com.example.connectionsqlsever.models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DataModel extends ViewModel {
  public  final MutableLiveData<Movie> selectedItem = new MutableLiveData<Movie>();
}
