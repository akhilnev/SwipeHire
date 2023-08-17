package com.example.user_manager_v1.ui.companies;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CompaniesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CompaniesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is companies fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}