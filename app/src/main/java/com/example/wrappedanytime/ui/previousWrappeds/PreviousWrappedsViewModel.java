package com.example.wrappedanytime.ui.previousWrappeds;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PreviousWrappedsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PreviousWrappedsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is previous wrappeds fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}