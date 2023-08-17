package com.example.user_manager_v1.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * ViewModel class for the Gallery fragment.
 * @author - Hrishikesha Kyathsandra
 */
public class GalleryViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    /**
     * Constructor for the GalleryViewModel class.
     */
    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is test fragment");
    }

    /**
     * Getter method for the text LiveData object.
     *
     * @return The LiveData object containing the text.
     */
    public LiveData<String> getText() {
        return mText;
    }
}





//package com.example.user_manager_v1.ui.gallery;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//import androidx.lifecycle.ViewModel;
//
//public class GalleryViewModel extends ViewModel {
//
//    private final MutableLiveData<String> mText;
//
//    public GalleryViewModel() {
//        mText = new MutableLiveData<>();
//        mText.setValue("This is test fragment");
//    }
//
//    public LiveData<String> getText() {
//        return mText;
//    }
//}