
package com.example.user_manager_v1.ui.gallery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.user_manager_v1.R;
import com.example.user_manager_v1.TestList;
import com.example.user_manager_v1.databinding.FragmentGalleryBinding;

/**
 * A simple {@link Fragment} subclass representing the gallery screen of the app.
 * @author - Hrishikesha Kyathsandra
 */
public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    Activity context;

    /**
     * Inflates the layout for the gallery fragment.
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        context = getActivity();

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    /**
     * Sets up the button to start the test list activity when clicked.
     */
    public void onStart(){
        super.onStart();
        Button button = context.findViewById(R.id.testbutton);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(context, TestList.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}



//package com.example.user_manager_v1.ui.gallery;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.ViewModelProvider;
//
//import com.example.user_manager_v1.R;
//import com.example.user_manager_v1.TestList;
//import com.example.user_manager_v1.databinding.FragmentGalleryBinding;
//
//public class GalleryFragment extends Fragment {
//
//    private FragmentGalleryBinding binding;
//    Activity context;
//
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//
//        context = getActivity();
//
//        binding = FragmentGalleryBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        return root;
//    }
//
//    public void onStart(){
//        super.onStart();
//        Button button = context.findViewById(R.id.testbutton);
//        button.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                Intent intent = new Intent(context, TestList.class);
//                startActivity(intent);
//            }
//        });
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
//}