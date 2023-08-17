package com.example.user_manager_v1.ui.companies;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * This class represents the ViewModel for the CompaniesFragment.
 * @author - Hrishikesha Kyathsandra
 */
public class CompaniesViewModel extends ViewModel {

    /** The text displayed in the fragment. */
    private final MutableLiveData<String> mText;

    /**
     * Constructor for the CompaniesViewModel class.
     */
    public CompaniesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is companies fragment");
    }

    /**
     * Getter method for the text displayed in the fragment.
     * @return The text displayed in the fragment.
     */
    public LiveData<String> getText() {
        return mText;
    }
}


//package com.example.user_manager_v1.ui.companies;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//import androidx.lifecycle.ViewModel;
//
//public class CompaniesViewModel extends ViewModel {
//
//    private final MutableLiveData<String> mText;
//
//    public CompaniesViewModel() {
//        mText = new MutableLiveData<>();
//        mText.setValue("This is companies fragment");
//    }
//
//    public LiveData<String> getText() {
//        return mText;
//    }
//}