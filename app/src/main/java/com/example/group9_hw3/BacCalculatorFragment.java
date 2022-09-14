/**
 * Homework 3
 * Group9_HW3
 * Phi Ha
 * Srinath Dittakavi
 */

package com.example.group9_hw3;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

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
    private static final String ARG_PARAM_DRINKS = "drinks";

    private String weight;
    private Profile profile = new Profile();
    private ArrayList<Drink> drinks;

    public BacCalculatorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param weight Parameter 1.
     * @param drinks Parameter 2.
     * @return A new instance of fragment BacCalculatorFragment.
     */
    public static BacCalculatorFragment newInstance(String weight, ArrayList<Drink> drinks) {
        BacCalculatorFragment fragment = new BacCalculatorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_WEIGHT, weight);
        args.putParcelableArrayList(ARG_PARAM_DRINKS, drinks);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            weight = getArguments().getString(ARG_PARAM_WEIGHT);
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

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IListener){
            mListener = (IListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement IListener");
        }
    }

    IListener mListener;

    public interface IListener{
        void changeFragmentListener(String fragment);
    }

}