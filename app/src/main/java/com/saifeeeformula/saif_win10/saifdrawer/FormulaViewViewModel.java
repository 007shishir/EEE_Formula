package com.saifeeeformula.saif_win10.saifdrawer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class FormulaViewViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    MutableLiveData<String> url_1, url_2, url_3, url_4,url_5;
    MutableLiveData<List<String>> url;

    public FormulaViewViewModel() {
        url = new MutableLiveData<>();
        url_1 = new MutableLiveData<>();
        url_2 = new MutableLiveData<>();
        url_3 = new MutableLiveData<>();
        url_4 = new MutableLiveData<>();
        url_5 = new MutableLiveData<>();
    }

    public LiveData<List<String>> chOne (){
        List<String> urls = new ArrayList<>();
        urls.add("https://i.imgur.com/OGUgCmY.jpg");
        urls.add("https://i.imgur.com/6hbjhli.jpg");
        urls.add("https://i.imgur.com/LeUO0mi.png");
        urls.add("https://i.imgur.com/6hbjhli.jpg");
        urls.add("https://i.imgur.com/LeUO0mi.png");
        url.postValue(urls);
        return url;
    }
}
