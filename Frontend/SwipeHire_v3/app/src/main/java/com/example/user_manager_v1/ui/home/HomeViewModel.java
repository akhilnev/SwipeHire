package com.example.user_manager_v1.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * ViewModel for the HomeFragment
 * @author - Hrishikesha Kyathsandra
 */
public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is homes fragment");
    }

    /**
     * Get the LiveData object containing the text for the HomeFragment
     * @return The LiveData object containing the text for the HomeFragment
     */
    public LiveData<String> getText() {
        return mText;
    }
}


//package com.example.user_manager_v1.ui.home;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//import androidx.lifecycle.ViewModel;
//
//public class HomeViewModel extends ViewModel {
//
//    private final MutableLiveData<String> mText;
//
//    public HomeViewModel() {
//        mText = new MutableLiveData<>();
//        mText.setValue("This is homes fragment");
//    }
//
//    public LiveData<String> getText() {
//        return mText;
//    }
//}