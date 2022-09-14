package com.example.group9_hw3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.group9_hw3.databinding.FragmentBacCalculatorBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BacCalculatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BacCalculatorFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM_WEIGHT = "weight";
    private static final String ARG_PARAM_GENDER = "gender";
    private static final String ARG_PARAM_DRINKS = "drinks";


    // TODO: Rename and change types of parameters
    private String weight;
    private String gender;
    private ArrayList<Drink> drinks;

    public BacCalculatorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param weight Parameter 1.
     * @param gender Parameter 2.
     * @return A new instance of fragment BacCalculatorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BacCalculatorFragment newInstance(String weight, String gender, ArrayList<Drink> drinks) {
        BacCalculatorFragment fragment = new BacCalculatorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_WEIGHT, weight);
        args.putString(ARG_PARAM_GENDER, gender);
        args.putParcelableArrayList(ARG_PARAM_DRINKS, drinks);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            weight = getArguments().getString(ARG_PARAM_WEIGHT);
            gender = getArguments().getString(ARG_PARAM_GENDER);
            drinks = getArguments().getParcelableArrayList(ARG_PARAM_DRINKS);
        }
    }

    FragmentBacCalculatorBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBacCalculatorBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    // Function codes go here
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}