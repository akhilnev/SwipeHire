package com.example.user_manager_v1.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.user_manager_v1.AddReview;
import com.example.user_manager_v1.CompanyList1;
import com.example.user_manager_v1.CompanyList2;
import com.example.user_manager_v1.UploadResume;
import com.example.user_manager_v1.databinding.FragmentHomeBinding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.user_manager_v1.R;
import com.example.user_manager_v1.TestList;
import com.example.user_manager_v1.databinding.FragmentGalleryBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    Activity context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        context = getActivity();

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    public void onStart(){
        super.onStart();
        Button button = context.findViewById(R.id.uploadresume);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(context, UploadResume.class);
                startActivity(intent);
            }
        });

        Button button1 = context.findViewById(R.id.addreview);
        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(context, CompanyList1.class);
                startActivity(intent);
            }
        });

        Button button2 = context.findViewById(R.id.viewallreviews);
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(context, CompanyList2.class);
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