package com.example.user_manager_v1.ui.companies;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.user_manager_v1.Description;
import com.example.user_manager_v1.R;
import com.example.user_manager_v1.SignInActivity;
import com.example.user_manager_v1.databinding.FragmentCompaniesBinding;
import com.example.user_manager_v1.SignUpActivity;

public class CompaniesFragment extends Fragment {

    private FragmentCompaniesBinding binding;

    Activity context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        CompaniesViewModel companiesViewModel =
//                new ViewModelProvider(this).get(CompaniesViewModel.class);

        context = getActivity();

        binding = FragmentCompaniesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textCompanies;
        //companiesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    public void onStart(){
        super.onStart();
        Button btn = context.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SignUpActivity.isManager == true) {
                    Intent intent = new Intent(context, Description.class);
                    startActivity(intent);
                }
                else{
                    //Toast.makeText(CompaniesFragment.this, "Only managers can enter", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}