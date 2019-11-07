package com.saifeeeformula.saif_win10.saifdrawer.ui.jobportal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class JobPortalViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public JobPortalViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}