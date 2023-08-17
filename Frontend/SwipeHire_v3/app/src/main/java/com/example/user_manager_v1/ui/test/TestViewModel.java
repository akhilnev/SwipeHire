package com.example.user_manager_v1.ui.test;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * ViewModel for the test fragment.
 * @author - Hrishikesha Kyathsandra
 */
public class TestViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    /**
     * Constructor for the TestViewModel.
     * Sets up the LiveData object with an initial value.
     */
    public TestViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is test fragment");
    }

    /**
     * Get the LiveData object for the text displayed in the fragment.
     *
     * @return LiveData object for the text displayed in the fragment
     */
    public LiveData<String> getText() {
        return mText;
    }
}



//package com.example.user_manager_v1.ui.test;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//import androidx.lifecycle.ViewModel;
//
//public class TestViewModel extends ViewModel {
//
//    private final MutableLiveData<String> mText;
//
//    public TestViewModel() {
//        mText = new MutableLiveData<>();
//        mText.setValue("This is test fragment");
//    }
//
//    public LiveData<String> getText() {
//        return mText;
//    }
//}